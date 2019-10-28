package com.company;

import com.company.StrokaKg.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<List<String>> rows = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        FileWriter csvWriter = new FileWriter("strok.csv");
        csvWriter.append("Цена $");
        csvWriter.append(",");
        csvWriter.append("Телефон");
        csvWriter.append(",");
        csvWriter.append("Серия");
        csvWriter.append(",");
        csvWriter.append("Комнаты");
        csvWriter.append(",");
        csvWriter.append("Площадь (м'2')");
        csvWriter.append(",");
        csvWriter.append("Этаж");
        csvWriter.append(",");
        csvWriter.append("Дата создания");
        csvWriter.append(",");
        csvWriter.append("Дата продления");
        csvWriter.append(",");
        csvWriter.append("Описание");

        csvWriter.append("\n");




        Document document = (Document) Jsoup.connect("http://stroka.kg").get();
        Element tableVerb = document.getElementsByAttributeValue("class", "topics-list").first();

        new Parser(tableVerb);
//        listStrokaKg.forEach(System.out::println);

        List<List<String>> rows = new ArrayList<>();
        rows = getRows();

        for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();


    }

    private static List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows){
        Main.rows = rows;
    }

}


