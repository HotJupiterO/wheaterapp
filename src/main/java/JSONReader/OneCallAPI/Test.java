package JSONReader.OneCallAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JWeatherStatus weatherStatus = objectMapper.readValue(WeatherGetter.getJSON(), JWeatherStatus.class);
        System.out.print(weatherStatus.getCurrent().getFeels_like() + " ");
        Date date = new Date(weatherStatus.getCurrent().getDt() * 1000);
        System.out.println(date);
        System.out.printf("Current temp in %-6s is %.1f \u2103%n", weatherStatus.getTimezone(), weatherStatus.getCurrent().getTemp());
        System.out.printf("    Region         Temp day/night        Feels like day/night~\n");
        for (JWeatherD d : weatherStatus.getDaily()) {
//            System.out.println(weatherStatus.getTimezone() + " Temp = " + d.getTemp() + " Feels like ~ " + d.getFeels_like());
            System.out.printf("%-20s  %-2.0f/%-20.0f  %-2.0f/%-2.0f rain volume:%-3.2f%n", weatherStatus.getTimezone(), d.getTemp().getDay(), d.getTemp().getNight(),
                     d.getFeels_like().getDay(), d.getFeels_like().getNight(),d.getRain());

        }

    }
}
