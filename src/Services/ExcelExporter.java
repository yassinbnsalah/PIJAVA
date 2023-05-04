package Services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Entities.Seance;

public class ExcelExporter {
    
    public static void exportSeancesToExcel(List<Seance> seances, String filename) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Seances");
        
        // create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Nom", "Description", "Durée", "Niveau", "Date"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        
        // create data rows
        int rowNum = 1;
        for (Seance seance : seances) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(seance.getId());
            row.createCell(1).setCellValue(seance.getNom());
            row.createCell(2).setCellValue(seance.getDescription());
            row.createCell(3).setCellValue(seance.getDuree());
            row.createCell(4).setCellValue(seance.getNiveau());
            if (seance.getDate() != null) {
                row.createCell(5).setCellValue(seance.getDate().toString());
            } else {
                row.createCell(5).setCellValue("");
            }
        }
        
        // autosize columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // write to file
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("Error exporting seances to Excel: " + e.getMessage());
        }
    }
}
