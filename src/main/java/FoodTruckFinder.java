import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodTruckFinder {
  public static void main(String[] args) throws IOException {
    /**
     * Making a call to the Socrata API and saving the XML response line by line in a StringBuilder.
     * which start time<currenttime&&endTime>currenttime then parse the response
     */
    final String socrataKey = "NCh9wpMSprt07VhZjYB8j9qgy";
    // get current time.
    Date now = new Date();
    String currentTime = getCurrentTime(now);
    String currentWeekDay = getCurrentWeekDay(now);
    /* Building a string from our response data */
    BufferedReader rd = null;

    try {
      StringBuilder result = new StringBuilder();
      String request =
          "https://data.sfgov.org/resource/jjew-r69b.xml?$$app_token="
              + socrataKey
              + "&dayofweekstr="
              + currentWeekDay
              + "&$where=start24%3C=%27"
              + currentTime
              + "%27%20AND%20end24%3E=%27"
              + currentTime
              + "%27";
      URL url = new URL(request);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      /* Building a string from our response data */
      String line;
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      ResponseParser responseParser = new ResponseParser();
      responseParser.buildResultsList(result.toString());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      rd.close();
    }
  }

  /**
   * Getting the current day of the week and the current time to narrow our foodtruck query. this
   * into my request above. I noticed this at the last minute, after already implementing
   * FoodTruckLister.
   */
  private static String getCurrentWeekDay(Date now) {
    SimpleDateFormat day = new SimpleDateFormat("EEEE");
    return day.format(now);
  }

  /**
   * get current time by the HH:mm format
   *
   * @param now
   * @return
   */
  private static String getCurrentTime(Date now) {
    DateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(now);
  }
}
