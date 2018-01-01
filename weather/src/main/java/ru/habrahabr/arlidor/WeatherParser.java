/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Tony
 */
public class WeatherParser {

    private Document document;
    private Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    public static void main(String args[]) throws IOException, Exception {
        WeatherParser pars = new WeatherParser();
        Document page = pars.getPage();
        Element tableWeather = page.select("table[class=wt]").first();
        Elements names = tableWeather.select("tr[class=wth]");
        Elements values = tableWeather.select("tr[valign=top]");
        int index = 0;

        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text().split("\\w+", 1)[0];
            String date = pars.getDateFromString(dateString);
            System.out.println(date + "   Явление   Температура    Давление    Влажность   Ветер");
            int iterationCount = pars.printFourValues(values, index);
            index = index + iterationCount;
        }

    }

    private int printFourValues(Elements values, int index) {
        int iterationCount = 4;
        if (index == 0) {
            Element valueLn = values.get(3);
            boolean isMorning = valueLn.text().contains("Утро");
            if (isMorning) {
                iterationCount = 3;
            }
            for (int i = 0; i < iterationCount; i++) {
                Element valueLine = values.get(index + i);
                for (Element td : valueLine.select("td")) {
                    System.out.print(td.text() + "     ");
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i < iterationCount; i++) {
                Element valueLine = values.get(index + i);
                for (Element td : valueLine.select("td")) {
                    System.out.print(td.text() + "     ");
                }
                System.out.println();
            }
        }
        return iterationCount;
    }

    private Document getPage() throws IOException {
        String url = "http://pogoda.spb.ru";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private String getDateFromString(String date) throws Exception {
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("can`t exctract data from string");
    }
}
