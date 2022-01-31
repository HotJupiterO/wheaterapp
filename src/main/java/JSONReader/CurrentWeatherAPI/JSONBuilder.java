package JSONReader.CurrentWeatherAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONBuilder {
    private static StringBuilder result;

    public static String getJSON(String strurl) {
         result = new StringBuilder();
        try {
            URL url = new URL(strurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            System.out.println("Invalid City name");
        }
        return result.toString();
    }

    public static String getJSON(URL url) {
       result = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            System.out.println("Invalid City name");
        }
        return result.toString();
    }
}
