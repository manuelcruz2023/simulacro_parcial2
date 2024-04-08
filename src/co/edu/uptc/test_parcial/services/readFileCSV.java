package co.edu.uptc.test_parcial.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;

public class readFileCSV {
    String path;
    BufferedReader br;
    String fileName;

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readTxt() throws IOException {
        List<String> content = new ArrayList<>();
        br = new BufferedReader(new FileReader(path));
        String line = "";
        while ((line = br.readLine()) != null) {
            content.add(line);
        }
        content.remove(0);
        return content;
    }

    public List<String> extractRegion() throws IOException {
        List<String> content = readTxt();
        List<String> region = new ArrayList<>();
        for (String string : content) {
            String temp = "";
            for (int i = 0; i < string.length(); i++) {
                temp += string.substring(i, i + 1);
                if (string.substring(i, i + 1).equals(",")) {
                    break;
                }
            }
            region.add(temp);
        }
        return region;
    }

    public List<String> extractDepartament() throws IOException {
        List<String> content = readTxt();
        List<String> departament = new ArrayList<>();
        for (String string : content) {
            if (numberQuote(string) == 4) {
                String[] datos = string.split(",");
                departament.add(datos[2]);
            } else if (numberQuote(string) == 5) {
                String[] datos = string.split(",");
                departament.add(removeQuote(datos[2] + datos[3]));
            }
        }
        return departament;
    }

    public List<String> extractCity() throws IOException {
        List<String> content = readTxt();
        List<String> city = new ArrayList<>();
        for (String string : content) {
            if (numberQuote(string) == 4) {
                String[] datos = string.split(",");
                city.add(datos[4]);
            } else if (numberQuote(string) == 5) {
                String[] datos = string.split(",");
                city.add(datos[5]);
            }
        }
        return city;
    }

    public int numberQuote(String string) throws IOException {
        int contQuote = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.substring(i, i + 1).equals(",")) {
                contQuote++;
            }
        }
        return contQuote;
    }

    public List<String> removePoint(List<String> content) throws IOException {
        List<String> list = new ArrayList<>();
        for (String string : content) {
            String temp = "";
            for (int i = 0; i < string.length() - 1; i++) {
                temp += string.substring(i, i + 1);
            }
            list.add(temp);
        }
        return list;
    }

    public String removeQuote(String string) {
        String temp = "";
        for (int i = 1; i < string.length() - 1; i++) {
            temp += string.substring(i, i + 1);
        }
        return temp;
    }

    public List<String> deleteDuplicate(List<String> contentDuplicate) throws IOException {
        Set<String> set = new LinkedHashSet<>(contentDuplicate);
        List<String> list = new ArrayList<>(set);
        return list;
    }

    public List<String> departamentListToJsonList() throws IOException {
        List<String> departament = deleteDuplicate(extractDepartament());
        List<String> jsonList = new ArrayList<>();
        jsonList.add("[");
        for (String string : departament) {
            jsonList.add("{" + "\"" + "departament:" + "\"" + ":" + "\"" + string + "\"" + "}" + ",");
            if (departament.indexOf(string) == departament.size() - 1) {
                jsonList.add("{" + "\"" + "departament:" + "\"" + ":" + "\"" + string + "\"" + "}");
            }
        }
        jsonList.add("]");
        return jsonList;
    }

    public List<String> cityListToXmlList() throws IOException {
        List<String> city = extractCity();
        List<String> xmlList = new ArrayList<>();
        xmlList.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlList.add("<cities>");
        for (String string : city) {
            xmlList.add("<city>" + "<nombre>" + string + "</nombre>" +"</city>");
        }
        xmlList.add("</cities>");
        return xmlList;
    }

    public void createFile(List<String> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String string : list) {
            writer.write(string + "\n");
        }
        writer.close();
    }
}