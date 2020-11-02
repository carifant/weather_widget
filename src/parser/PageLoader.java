package parser;

/**
 * class for parse date from site of weather
 */

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


  /**
   * Here we get date from all page in html
   */

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

  /**
   * this method get only part of page with necessary information
   */

  private Element getTable() {
    Document document = getPage();
    return document.select("[class=forecast_frame hw_wrap]").first();
  }

  /**
   * This method get more detail information from page
   */

  private Element getDay() {
    return getTable().select("[class=tab-content]").first();
  }

  /**
   * This method get and change date for next step
   */

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

  /**
   * This method returns list with date about weather
   */

  public List<String> getResultList() {
    formatInfo();
    return resultList;
  }
}
