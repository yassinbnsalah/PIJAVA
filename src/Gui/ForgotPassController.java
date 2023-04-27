/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.User;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.EmailManager;
import util.Routage;

import util.SharedData;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class ForgotPassController implements Initializable {

    @FXML
    private PasswordField newpass;
    @FXML
    private Button save;

    @FXML
    private TextField mail;
    @FXML
    private Button sendmail;
    @FXML
    private TextField mailcode;
    @FXML
    private Button back;
    @FXML
    private PasswordField tfMdp2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) throws IOException {
        SharedData da = new SharedData();

        int code = Integer.parseInt(mailcode.getText());
        
        UserService us = new UserService();
        String mailin = mail.getText();
        String pass = newpass.getText();
        String mdp2 = tfMdp2.getText();
        if (newpass.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "les champs sont vides!", ButtonType.OK);
            a.showAndWait();
        } else if (us.existeMail2(mailin) == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Email", ButtonType.OK);
            a.showAndWait();
            mail.setText("");

            mail.requestFocus();

        } else if (code != da.GenratedCode) {

            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Code de verification", ButtonType.OK);
            a.showAndWait();
            mail.setText("");

            mail.requestFocus();
        }else if (!(mdp2.equals(pass))) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Password", ButtonType.OK);
            a.showAndWait();
            newpass.setText("");
            tfMdp2.setText("");
            newpass.requestFocus();}
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Comfirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de modifier ?");
            Optional<ButtonType> action = alert.showAndWait();
            System.out.println("User Updated");
            User U = new User();
            String mailinE = mail.getText();
            U = us.getByEmail(mailinE);
            System.out.println(U);
            U.setPassword(pass);
            System.out.println(U.getId());
            System.out.println(U.getPassword());
            us.modifier2(U.getId(), U);
            String tilte = "Resset Password Success ";
            String message = "un message a etes envoyer ";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            EmailManager m = new EmailManager();

            String sub = "Reset-Password Affected ";
            String sub2 = " Votre nouveaux Mot de pass est: " + U.getPassword();
            String content = "Bonjour Mr/Mme " + U.getName() + ". Au nom de tous les membres du FITHAELTH, je vous informer que le mot de passe est change avec succes ";
            String finalcontant = sub + content + sub2;
            m.sendEmail(U.getEmail(), finalcontant);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"))));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();

        }

    }

   
    @FXML
    private void sendmail(ActionEvent event) throws IOException {

        Random rand = new Random();
        int upperbound = 25555;
        int numer = rand.nextInt(upperbound);

        String mailin = mail.getText();
        EmailManager m = new EmailManager();
        String content = "<!DOCTYPE html>\n" +
"\n" +
"<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
"<head>\n" +
"<title></title>\n" +
"<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
"<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
"<style>\n" +
"		* {\n" +
"			box-sizing: border-box;\n" +
"		}\n" +
"\n" +
"		body {\n" +
"			margin: 0;\n" +
"			padding: 0;\n" +
"		}\n" +
"\n" +
"		a[x-apple-data-detectors] {\n" +
"			color: inherit !important;\n" +
"			text-decoration: inherit !important;\n" +
"		}\n" +
"\n" +
"		#MessageViewBody a {\n" +
"			color: inherit;\n" +
"			text-decoration: none;\n" +
"		}\n" +
"\n" +
"		p {\n" +
"			line-height: inherit\n" +
"		}\n" +
"\n" +
"		.desktop_hide,\n" +
"		.desktop_hide table {\n" +
"			mso-hide: all;\n" +
"			display: none;\n" +
"			max-height: 0px;\n" +
"			overflow: hidden;\n" +
"		}\n" +
"\n" +
"		.image_block img+div {\n" +
"			display: none;\n" +
"		}\n" +
"\n" +
"		@media (max-width:660px) {\n" +
"\n" +
"			.desktop_hide table.icons-inner,\n" +
"			.social_block.desktop_hide .social-table {\n" +
"				display: inline-block !important;\n" +
"			}\n" +
"\n" +
"			.icons-inner {\n" +
"				text-align: center;\n" +
"			}\n" +
"\n" +
"			.icons-inner td {\n" +
"				margin: 0 auto;\n" +
"			}\n" +
"\n" +
"			.image_block img.big,\n" +
"			.row-content {\n" +
"				width: 100% !important;\n" +
"			}\n" +
"\n" +
"			.mobile_hide {\n" +
"				display: none;\n" +
"			}\n" +
"\n" +
"			.stack .column {\n" +
"				width: 100%;\n" +
"				display: block;\n" +
"			}\n" +
"\n" +
"			.mobile_hide {\n" +
"				min-height: 0;\n" +
"				max-height: 0;\n" +
"				max-width: 0;\n" +
"				overflow: hidden;\n" +
"				font-size: 0px;\n" +
"			}\n" +
"\n" +
"			.desktop_hide,\n" +
"			.desktop_hide table {\n" +
"				display: table !important;\n" +
"				max-height: none !important;\n" +
"			}\n" +
"		}\n" +
"	</style>\n" +
"</head>\n" +
"<body style=\"background-color: #f8f8f9; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f8f8f9;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #1aa19c;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #1aa19c; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 4px solid #1AA19C;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +

"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f8f8f9; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"20\" cellspacing=\"0\" class=\"divider_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:12px;padding-top:60px;\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-left:40px;padding-right:40px;width:100%;\">\n" +
"<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"I'm an image\" class=\"big\" src=\"https://scontent.ftun4-2.fna.fbcdn.net/v/t1.15752-9/342788466_264408862681271_8275323110673503596_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=ae9488&_nc_ohc=kWzM9kXQA2EAX9nXRCj&_nc_ht=scontent.ftun4-2.fna&oh=03_AdRCk6uhOfatJa2wbcb02ixGMl13AMdI96oWAHxL2R6L3w&oe=646FACDE\" style=\"display: block; height: auto; border: 0; width: 352px; max-width: 100%;\" title=\"I'm an image\" width=\"352\"/></div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-top:50px;\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px;\">\n" +
"<div style=\"font-family: sans-serif\">\n" +
"<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">\n" +
"<p style=\"margin: 0; font-size: 16px; text-align: center; mso-line-height-alt: 19.2px;\"><span style=\"font-size:30px;color:#2b303a;\"><strong>Reset your Password with the activation code</strong></span></p>\n" +
"</div>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px;\">\n" +
"<div style=\"font-family: sans-serif\">\n" +
"<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px; color: #555555; line-height: 1.5;\">\n" +
"<p style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 22.5px;\"><span style=\"color:#808389;font-size:15px;\">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmodati mat tempor incididunt ut labore et dolore magna aliqua.</span></p>\n" +
"</div>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-top:50px;\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3fafa; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; border-left: 30px solid #FFFFFF; border-right: 30px solid #FFFFFF; vertical-align: top; border-top: 0px; border-bottom: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 4px solid #1AA19C;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-top:25px;\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;padding-top:10px;\">\n" +
"<div style=\"font-family: sans-serif\">\n" +
"<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">\n" +
"<p style=\"margin: 0; font-size: 16px; text-align: center; mso-line-height-alt: 19.2px;\"><span style=\"color:#2b303a;font-size:18px;\"><strong>Use this Code</strong></span></p>\n" +
"</div>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:32px;\">\n" +
"<div style=\"font-family: sans-serif\">\n" +
"<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">\n" +
"<p style=\"margin: 0; font-size: 16px; text-align: center; mso-line-height-alt: 19.2px;\"><span style=\"color:#1aa19c;font-size:38px;\"><span style=\"\"><strong>"+numer+"</strong></span></span></p>\n" +
"</div>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;padding-top:40px;text-align:center;\">\n" +
"<div align=\"center\" class=\"alignment\"><!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" style=\"height:62px;width:173px;v-text-anchor:middle;\" arcsize=\"97%\" stroke=\"false\" fillcolor=\"#1aa19c\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#ffffff; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]-->\n" +
"<div style=\"text-decoration:none;display:inline-block;color:#ffffff;background-color:#1aa19c;border-radius:60px;width:auto;border-top:0px solid transparent;font-weight:undefined;border-right:0px solid transparent;border-bottom:0px solid transparent;border-left:0px solid transparent;padding-top:15px;padding-bottom:15px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\"><span style=\"padding-left:30px;padding-right:30px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span style=\"margin: 0; word-break: break-word; line-height: 32px;\"><strong>Activate Code</strong></span></span></div><!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:12px;padding-top:60px;\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f8f8f9; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"20\" cellspacing=\"0\" class=\"divider_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-8\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #2b303a; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 4px solid #1AA19C;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
"<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"I'm an image\" class=\"big\" src=\"https://scontent.ftun4-2.fna.fbcdn.net/v/t1.15752-9/342829852_179479991666568_5447174891346601634_n.png?_nc_cat=101&ccb=1-7&_nc_sid=ae9488&_nc_ohc=O0sPL5KcOVEAX_SzoFa&_nc_ht=scontent.ftun4-2.fna&oh=03_AdTEeeMyVihaFYbfkdBE93czygEx-GXjiwWvzzje_MzZWg&oe=646FB709\" style=\"display: block; height: auto; border: 0; width: 640px; max-width: 100%;\" title=\"I'm an image\" width=\"640\"/></div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +

"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:28px;text-align:center;\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social-table\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block;\" width=\"208px\">\n" +
"<tr>\n" +
"<td style=\"padding:0 10px 0 10px;\"><a href=\"https://www.facebook.com/\" target=\"_blank\"><img alt=\"Facebook\" height=\"32\" src=\"https://scontent.ftun4-2.fna.fbcdn.net/v/t1.15752-9/270748220_466252305067686_1642407111244211439_n.png?stp=cp0_dst-png&_nc_cat=108&ccb=1-7&_nc_sid=ae9488&_nc_ohc=q98aUmbFF8IAX_FzQqo&_nc_ht=scontent.ftun4-2.fna&oh=03_AdRWD-1tEDrPwtPN9qWLR6u2RULhTherRMPtSYi9HKqLUA&oe=646FAE89\" style=\"display: block; height: auto; border: 0;\" title=\"Facebook\" width=\"32\"/></a></td>\n" +
"<td style=\"padding:0 10px 0 10px;\"><a href=\"https://twitter.com/\" target=\"_blank\"><img alt=\"Twitter\" height=\"32\" src=\"https://scontent.ftun4-2.fna.fbcdn.net/v/t1.15752-9/304912745_5739170919468885_2410400311628084521_n.png?stp=cp0_dst-png&_nc_cat=103&ccb=1-7&_nc_sid=ae9488&_nc_ohc=NCaeeYvP5IcAX-k2mqd&_nc_ht=scontent.ftun4-2.fna&oh=03_AdRUJfRB-L3pR0Ev71bxEMWyF8g-DQvTs3AxsfuzWfOzyg&oe=646FC363\" style=\"display: block; height: auto; border: 0;\" title=\"Twitter\" width=\"32\"/></a></td>\n" +
"<td style=\"padding:0 10px 0 10px;\"><a href=\"https://instagram.com/\" target=\"_blank\"><img alt=\"Instagram\" height=\"32\" src=\"https://scontent.ftun4-2.fna.fbcdn.net/v/t1.15752-9/305529412_2909133912553616_8321761725448585250_n.png?stp=cp0_dst-png&_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=6-Vn-Nz7554AX_XaKIA&_nc_ht=scontent.ftun4-2.fna&oh=03_AdTaN_ZV_VG1-7nGxzcvGGPqwfhvknG96_qLWBdRERyqsg&oe=646FC2E9\" style=\"display: block; height: auto; border: 0;\" title=\"Instagram\" width=\"32\"/></a></td>\n" +
"<td style=\"padding:0 10px 0 10px;\"><a href=\"https://www.linkedin.com/\" target=\"_blank\"><img alt=\"LinkedIn\" height=\"32\" src=\"https://scontent.ftun4-2.fna.fbcdn.net/v/t1.15752-9/342891559_767180058263216_203357990129524657_n.png?stp=cp0_dst-png&_nc_cat=106&ccb=1-7&_nc_sid=ae9488&_nc_ohc=rUNOvwC_A5kAX8ALCQ7&_nc_ht=scontent.ftun4-2.fna&oh=03_AdSHFVFBhjOC6mqwmmcJxZOX0PNKMTNP-3UTgvGuKpUqbQ&oe=646FA535\" style=\"display: block; height: auto; border: 0;\" title=\"LinkedIn\" width=\"32\"/></a></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:15px;\">\n" +
"<div style=\"font-family: sans-serif\">\n" +
"<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px; color: #555555; line-height: 1.5;\">\n" +
"<p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 18px;\"><span style=\"color:#95979c;font-size:12px;\">Etiam quis tempus ex. Sed vitae ipsum suscipit, ultricies odio vitae, suscipit massa. Sed tempus ipsum eget diam aliquam maximus. Cras accumsan urna vel rutrum lobortis. Maecenas tristique purus vel ex tempor consequat. Curabitur dui massa, congue sed sem at, rhoncus imperdiet sem. Fusce ac orci fermentum, malesuada dolor a, cursus augue. Quisque porttitor sapien arcu, quis iaculis nisi faucibus eget. Vestibulum eu velit rhoncus, aliquam ante eget, tristique diam dui massa, congue sed sem at, rhoncus usce ac orci fermentum,.</span></p>\n" +
"</div>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:25px;\">\n" +
"<div align=\"center\" class=\"alignment\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #555961;\"><span> </span></td>\n" +
"</tr>\n" +
"</table>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"padding-bottom:30px;padding-left:40px;padding-right:40px;padding-top:20px;\">\n" +
"<div style=\"font-family: sans-serif\">\n" +
"<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">\n" +
"<p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 16.8px;\"><span style=\"color:#95979c;font-size:12px;\">Companify Copyright © 2020</span></p>\n" +
"</div>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-9\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px;\" width=\"640\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"pad\" style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
"<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
"<!--[if !vml]><!-->\n" +
"<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\"><!--<![endif]-->\n" +
"<tr>\n" +
"<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"https://www.designedwithbee.com/\" style=\"text-decoration: none;\" target=\"_blank\"><img align=\"center\" alt=\"Designed with BEE\" class=\"icon\" height=\"32\" src=\"https://scontent.ftun4-2.fna.fbcdn.net/v/t1.15752-9/270242539_947745915947057_4308952021364621234_n.png?_nc_cat=100&ccb=1-7&_nc_sid=ae9488&_nc_ohc=_nSfOKXa0AkAX9cajBr&_nc_ht=scontent.ftun4-2.fna&oh=03_AdSMqSgAx_BsiLmCytEgg12Fv_6WFX9Lz2_jrjizLlB7cg&oe=646FAB36\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\" width=\"34\"/></a></td>\n" +
"<td style=\"font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; font-size: 15px; color: #9d9d9d; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"https://www.designedwithbee.com/\" style=\"color: #9d9d9d; text-decoration: none;\" target=\"_blank\">Designed with BEE</a></td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table><!-- End -->\n" +
"</body>\n" +
"</html>";
        String finalcontant = content;
        System.out.println(numer);
        m.sendEmail(mailin, finalcontant);
        SharedData da = new SharedData();
        da.GenratedCode = numer;
        System.out.println(da.GenratedCode);

    }

    @FXML
    private void textfieldDesign(MouseEvent event) {
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Routage.getInstance().GOTO(back, "/view/LoginPage.fxml");
    }
    
    
}
