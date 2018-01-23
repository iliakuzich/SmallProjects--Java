package ru.habrahabr.arlidor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeatherParser {

    private Document page;
    private Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");
    private List<WeatherForecast> list;
    private WeatherForecast forecast;

    public static void main(String args[]) throws Exception {
        WeatherParser ww = new WeatherParser();
        ww.start();
    }

    public void start() throws Exception {
        page = getPage();
        Element tableWeather = page.select("table[class=wt]").first();
        Elements names = tableWeather.select("tr[class=wth]");
        Elements values = tableWeather.select("tr[valign=top]");
        int index = 0;
        list = new ArrayList<>();
        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text().split("\\w+", 1)[0];
            String date = getDateFromString(dateString);
            forecast = new WeatherForecast(date);
            System.out.println(date + "   Явление   Температура    Давление    Влажность   Ветер");
            int iterationCount = setValues(values, index, forecast);
            index = index + iterationCount;
            list.add(forecast);
        }

    }

    private Document getPage() throws IOException {
        String url = "http://pogoda.spb.ru";
        Document page = Jsoup.parse(new URL(url), 6000);
        return page;
    }

    private String getDateFromString(String date) throws Exception {
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("can`t exctract data from string");
    }

    public int setValues(Elements values, int index, WeatherForecast forecast) {
        int iterationCount = 4;
        if (index == 0) {
            iterationCount = check(values);
        }
        for (int i = 0; i < iterationCount; i++) {
            Element valueLine = values.get(index + i);
            ArrayList<String> dt = new ArrayList<>();
            for (Element td : valueLine.select("td")) {

                dt.add(td.text() + " ");
                forecast.setForecastData(dt);

            }
            System.out.print(dt.get(0));
            System.out.println();
        }

        return iterationCount;
    }

    private int printFourValues(Elements values, int index) {
        int iterationCount = 4;
        if (index == 0) {
            iterationCount = check(values);
        }
        for (int i = 0; i < iterationCount; i++) {
            Element valueLine = values.get(index + i);
            for (Element td : valueLine.select("td")) {
                System.out.print(td.text() + " ");
            }
            System.out.println();
        }

        return iterationCount;
    }

    public int check(Elements values) {
        int iterationCount;
        Element valueLn = values.get(3);
        boolean isMorning = valueLn.text().contains("Утро");
        boolean isDay = valueLn.text().contains("День");
        boolean isEvening = valueLn.text().contains("Вечер");

        if (isMorning) {
            iterationCount = 3;
        } else if (isDay) {
            iterationCount = 2;
        } else if (isEvening) {
            iterationCount = 1;
        } else {
            iterationCount = 4;
        }
        return iterationCount;
    }
}
