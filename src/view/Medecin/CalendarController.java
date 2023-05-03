/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Medecin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.Rendezvous;
import models.User;
import services.RendezvousService;
import services.UserService;
import util.Routage;
import util.SessionManager;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;
    RendezvousService rendezvousService;
    UserService userService;
    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;
    @FXML
    private ComboBox<User> doctorCB;
    @FXML
    private Label userEmaillbl;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnOrders;
    @FXML
    private Button RendezVousbtn;
    @FXML
    private Button btnOrdennance;
    @FXML
    private Button btnSignout;
    @FXML
    private Button ClientDashboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rendezvousService = new RendezvousService();
        userService = new UserService();
        ObservableList<User> list = FXCollections.observableArrayList();
        list.addAll(userService.userListe());
        doctorCB.setItems(list);

        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();

    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<Rendezvous>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<Rendezvous> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            createRendezvous(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createRendezvous(List<Rendezvous> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getHour_rv());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<Rendezvous>> createCalendarMap(List<Rendezvous> calendarActivities) {
        Map<Integer, List<Rendezvous>> calendarActivityMap = new HashMap<>();

        for (Rendezvous activity : calendarActivities) {
            int activityDate = activity.getDate_rv().toLocalDateTime().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, Arrays.asList(activity));
            } else {
                List<Rendezvous> OldListByDate = calendarActivityMap.get(activityDate);

                List<Rendezvous> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

    private Map<Integer, List<Rendezvous>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<Rendezvous> calendarActivities;
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        if (doctorCB.getSelectionModel().getSelectedItem() != null) {
            calendarActivities = rendezvousService.rendezVousDateYear(year, month, doctorCB.getSelectionModel().getSelectedItem().getId());
        } else {
            calendarActivities = rendezvousService.rendezVousDateYear(year, month, 0);
        }

        System.out.println(calendarActivities);

        return createCalendarMap(calendarActivities);
    }

    @FXML
    private void reload(ActionEvent event) {
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    private void GoToClientListe(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void GoToRendezVous(ActionEvent event) {
        
         Routage.getInstance().GOTO(RendezVousbtn, "/view/Medecin/AddRendezvous.fxml");
    }

    @FXML
    private void GoToOrdennanceListe(ActionEvent event) {
                     Routage.getInstance().GOTO(btnSignout, "/view/Medecin/AddOrdennance.fxml");
    }

    @FXML
    private void logout(ActionEvent event) {
         SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

    @FXML
    private void GoToClientDashboard(ActionEvent event) {
         Routage.getInstance().GOTO(ClientDashboard, "/view/client/subscription/subscriptionhistory.fxml");
    }
}
