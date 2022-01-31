package JSONReader.CurrentWeatherAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

public class Test {
    private static final String APIID = "60df5896c63cd431db2d7b3c0af2e658";

    public static void main(String[] args) throws IOException {
        String json = JSONBuilder.getJSON("http://api.openweathermap.org/data/2.5/forecast" +
                "?q=wroclaw&APPID=60df5896c63cd431db2d7b3c0af2e658&units=metric&lang=en");

        ObjectMapper objectMapper = new ObjectMapper();

        CurrentWeatherAPIReader test = objectMapper.readValue(makeUrl(), CurrentWeatherAPIReader.class);

//        Date date = new Date(test.getDt());
//        System.out.println("temp = " + test.getList().get(1) + " time: ");
        System.out.printf("%-20s%-5s/%-20s%3s/%3s%n", "Date and time", "City", "Country", "Cur temp", "Feels like");
        for (WeatherInf w : test.getList()) {
            System.out.printf("%-20s%-5s/%-20s%3.0f\u2103/%3.0f\u2103%n", w.getDt_txt(), test.getCity().getName(), test.getCity().getCountry(), w.getMain().getTemp(), w.getMain().getFeels_like());
        }
    }

    public static URL makeUrl() throws MalformedURLException {
        Scanner sc = new Scanner(System.in);
//        String name = "";
        System.out.print("Your City? : ");
        String name = sc.next();
        return new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&APPID=" + APIID + "&units=metric&lang=en");
    }
}
