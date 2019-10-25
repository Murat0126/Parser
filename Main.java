package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Article> listArticle = new ArrayList<>();

        Document document = (Document) Jsoup.connect("http://stroka.kg/").get();

        Elements h1Elements = document.getElementsByAttributeValue("class","topics-item-view");

        h1Elements.forEach(h1Element ->{
            Element element = h1Element;
            String name = element.text();
            listArticle.add(new Article(name));
        });


        listArticle.forEach(System.out::println);

    }


}


class Article {


    private String view;

    public Article(String view) {

        this.view = view;
    }




    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Article{" +
                ", view='" + view + '\'' +
                '}';
    }
}