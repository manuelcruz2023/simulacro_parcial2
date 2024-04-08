package co.edu.uptc.test_parcial.controllers;
import java.io.IOException;

import co.edu.uptc.test_parcial.services.readFileCSV;
import co.edu.uptc.text.ManagerProperties;

public class showReadFilesCSV {
    ManagerProperties managerProperties = new ManagerProperties();
    
    public void show() {
        readFileCSV readFileCSV = new readFileCSV();
        managerProperties.setFileName("data.properties");
        readFileCSV.setPath(managerProperties.getValue("content"));
        try {
            readFileCSV.setFileName("regions.txt");
            readFileCSV.createFile(readFileCSV.deleteDuplicate(readFileCSV.removePoint(readFileCSV.extractRegion())));
            readFileCSV.setFileName("departaments.json");
            readFileCSV.createFile(readFileCSV.departamentListToJsonList());
            readFileCSV.setFileName("cities.xml");
            readFileCSV.createFile(readFileCSV.cityListToXmlList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}