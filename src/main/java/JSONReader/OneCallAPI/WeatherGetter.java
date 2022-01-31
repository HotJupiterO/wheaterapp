package JSONReader.OneCallAPI;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WeatherGetter {
    //    private static final String APIkey = "60df5896c63cd431db2d7b3c0af2e658";
    private static final String APIkey = "bd76b9805b859215f00db10ea9dae60b";
//api.openweathermap.org/data/2.5/forecast/daily?q={city name}&cnt={cnt}&appid={your api key}
    public static final String TESTURL =
            "http://api.openweathermap.org/data/2.5/forecast" +
                    "?q=wrolaw&APPID=60df5896c63cd431db2d7b3c0af2e658&mode=xml&units=metric&lang=en"; // id=756151 OR q=warsaw
    public static String OneCallApiURL = "https://api.openweathermap.org/data/2.5/onecall?" +
            "lat=52.297080&lon=20.939556&appid=60df5896c63cd431db2d7b3c0af2e658&units=metric";

    private static StringBuilder result;

    public static String getXML() {
        String urlToRead = createUrl();
        result = new StringBuilder();
        try {
            URL url = new URL(urlToRead);
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

    private static String getCity() {
        String cityName = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Which city? ");
        return cityName = sc.next();
    }

    private static String createUrl(){
        return "http://api.openweathermap.org/data/2.5/forecast?q="
        + getCity() + "&APPID=60df5896c63cd431db2d7b3c0af2e658&mode=xml&units=metric&lang=en";
    }

    public static String getJSON() {
        result = new StringBuilder();
        try {
            URL url = new URL(OneCallApiURL);
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
