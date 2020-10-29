package parser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PageLoader {

  private List<String> resultList;

  private Document getPage() {
    String url = "https://www.gismeteo.ru/weather-nizhny-novgorod-4355/";
    Document document = null;
    try {
      document = Jsoup.parse(new URL(url), 20000);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return document;
  }

  private Element getTable() {
    Document document = getPage();
    return document.select("[class=forecast_frame hw_wrap]").first();
  }

  private Element getDay() {
    return getTable().select("[class=tab-content]").first();
  }

  private void formatInfo() {
    String temp = getDay().text();
    String[] arr = temp.split(" ");
    List<String> list = new ArrayList<String>(Arrays.asList(arr));
    list.remove(3);
    list.remove(6);
    list.set(0, list.get(0) + " " + list.get(1));
    list.remove(1);
    list.set(2, list.get(2) + " " + list.get(3));
    list.remove(3);
    resultList = list;
  }

  public List<String> getResultList() {
    formatInfo();
    return resultList;
  }
}
