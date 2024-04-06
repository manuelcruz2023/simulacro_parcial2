package co.edu.uptc.test_parcial.controllers;
import java.io.IOException;

import co.edu.uptc.test_parcial.services.readFileCSV;
import co.edu.uptc.text.ManagerProperties;

public class showReadFilesCSV {
    public void show() {
        ManagerProperties managerProperties = new ManagerProperties();
        managerProperties.setFileName("data.properties");
        readFileCSV readFileCSV = new readFileCSV();
        readFileCSV.setPath(managerProperties.getValue("content"));
        try {
            readFileCSV.setFileName("regions.txt");
            readFileCSV.createFile(readFileCSV.deleteDuplicate(readFileCSV.removePoint(readFileCSV.extractRegion())));
            readFileCSV.setFileName("departaments.json");
            readFileCSV.createFile(readFileCSV.departamentListToJsonList());
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}