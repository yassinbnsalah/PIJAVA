/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import models.Seance;

/**
 *
 * @author yacin
 */
public class CsvExporter {
    public void exportSeances(List<Seance> seances, String filePath) throws FileNotFoundException {
    try (PrintWriter writer = new PrintWriter(filePath)) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID,");
        sb.append("Nom,");
        sb.append("Description,");
        sb.append("Durée,");
        sb.append("Niveau,");
        
        for (Seance seance : seances) {
            sb.append(seance.getId());
            sb.append(",");
            sb.append(seance.getNom());
            sb.append(",");
            sb.append(seance.getDescription());
            sb.append(",");
            sb.append(seance.getDuree());
            sb.append(",");
            sb.append(seance.getNiveau());
            sb.append(",");
          
        }

        writer.write(sb.toString());

    }}

}
