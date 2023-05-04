package Services;

import Entities.Seance;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvExporter {

    public void exportSeances(List<Seance> seances, String filePath) throws FileNotFoundException {
    try (PrintWriter writer = new PrintWriter(filePath)) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID,");
        sb.append("Nom,");
        sb.append("Description,");
        sb.append("Durée,");
        sb.append("Niveau,");
        sb.append("Date\n");

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
            sb.append(seance.getDate());
            sb.append("\n");
        }

        writer.write(sb.toString());

    }}


}
