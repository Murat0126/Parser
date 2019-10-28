package com.company.StrokaKg;

import com.company.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private List<List<String>> rows = new ArrayList<>();
    int count;


    public Parser(Element element) throws IOException {

        parsing(element);
        for(int i = 0; i < 15; i++){
            if(i > 0){
                Document document = (Document) Jsoup.connect("https://stroka.kg/kupit-kvartiru/?&p=" + i + "#paginator").get();
                Element element1 = document.getElementsByAttributeValue("class", "topics-list").first();
                parsing(element1);
            }

        }

        Main main = new Main();
        main.setRows(rows);

    }

    private void parsing(Element element) throws IOException {

        Elements h1Elements = element.select("tbody");

        for (Element e: h1Elements){
            String id = e.attr("data-id");

            if(!id.equals("")){
                Document doc = (Document) Jsoup.connect("https://stroka.kg/?page=topic-view&topic_id=" + id).get();
                org.jsoup.select.Elements title = doc.getElementsByClass("topic-view-best-topic_cost");
                org.jsoup.select.Elements phoneNumber = doc.getElementsByClass("topic-view-best-phones");
                org.jsoup.select.Elements countRooms = doc.getElementsByClass("topic-view-best-topic_rooms");
                org.jsoup.select.Elements places = doc.getElementsByClass("topic-view-best-topic_area");
                org.jsoup.select.Elements serie = doc.getElementsByClass("topic-view-best-topic_series");
                org.jsoup.select.Elements floors = doc.getElementsByClass("topic-view-best-topic_floor");
                org.jsoup.select.Elements floorOf = doc.getElementsByClass("topic-view-best-topic_floor_of");
                org.jsoup.select.Elements dateUpdate = doc.getElementsByClass("topic-view-date_list-item topic-view-topic_date_up ic_date_range_9c9c9c_24dp_2x");
                org.jsoup.select.Elements dateCreate = doc.getElementsByClass("topic-view-date_list-item topic-view-topic_date_create_row");
                org.jsoup.select.Elements viewName = doc.getElementsByClass("topic-best-view-name-parent");
                String floorof = floorOf.text();

                String price =title.text();
                String rooms = countRooms.text();
                String phoneNumbers =phoneNumber.text().replace(',', ' ');
                String series =serie.text();
                String floor =floors.text() + "/" + floorof;
                String updateDate =  extractDigits(dateUpdate.text());
                String createDate = extractDigits(dateCreate.text());
                String headText = viewName.text().replace(',', ' ');
                String place;
                if (places.text().equals("")){
                    place = "                 ";
                }else{
                    place =places.text();
                }


                rows.add(Arrays.asList(price, phoneNumbers, rooms, series, place, floor,createDate,updateDate, headText));
                count = count + 1;
                System.out.println("Строка: " + count);

//                System.out.println(price + "       " + rooms + "       " + series+ "       " + place+ "       " + floor + "           " + createDate+ "        "+ updateDate+ "                          " +phoneNumbers+ "        " + headText);
            }

        }

    }

    public String extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }


}
