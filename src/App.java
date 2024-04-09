import java.io.IOException;
import co.edu.uptc.test_parcial.services.readFileCSV;
import co.edu.uptc.text.ManagerProperties;

public class App {
    public static void main(String[] args) throws Exception {
        ManagerProperties managerProperties = new ManagerProperties();
        managerProperties.setFileName("data.properties");
        readFileCSV readFileCSV = new readFileCSV();
        readFileCSV.setPath(managerProperties.getValue("content"));
        readFileCSV.setComma(managerProperties.getValue("comma"));
        readFileCSV.setLineBreak(managerProperties.getValue("lineBreak"));
        readFileCSV.setDoublePoints(managerProperties.getValue("doublePoints"));
        readFileCSV.setQuote(managerProperties.getValue("quote"));
        try {
            readFileCSV.setFileName(managerProperties.getValue("region"));
            readFileCSV.createFile(readFileCSV.deleteDuplicate(readFileCSV.removePoint(readFileCSV.extractRegion())));
            readFileCSV.setFileName(managerProperties.getValue("departament"));
            readFileCSV.createFile(readFileCSV.departamentListToJsonList());
            readFileCSV.setFileName(managerProperties.getValue("cities"));
            readFileCSV.createFile(readFileCSV.cityListToXmlList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
