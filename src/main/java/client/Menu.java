package client;

import JSONReader.GeocodingAPI.GLocation;
import JSONReader.GeocodingAPI.GResult;
import JSONReader.GeocodingAPI.Geocod;
import JSONReader.GeocodingAPI.GeocodeAPI;
import JSONReader.OneCallAPI.JHourlyWeather;
import JSONReader.OneCallAPI.JWeatherD;
import JSONReader.OneCallAPI.JWeatherStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Menu {
    private static final String APIkey = "htwx2dUIMt7wIJwFqmaYRxoOG37cTrQ6";
    private static final String ONECALLAPIkey = "60df5896c63cd431db2d7b3c0af2e658";


    public static void main(String[] args) throws IOException {
        String option;
        do {
            option = "";
            Geocod geo = getGeo();
            List<GLocation> locations = getLocationsList(geo);
            printLocationsList(locations);
            int locIndex = getLocIndex(locations);
            GLocation chosenLocation;
            chosenLocation = getLocationByIndex(locations, locIndex);
            Coordinates coods = getCoordinates(chosenLocation);
            URL url = getURL(coods);
            ObjectMapper oMapper = new ObjectMapper();
            JWeatherStatus weatherStatus = oMapper.readValue(url, JWeatherStatus.class);
            int weatherType = getWeatherType();
            getWeather(weatherStatus, weatherType);

            System.out.println("Press \"q\" for close the program, any for continue");
            Scanner scanner = new Scanner(System.in);
            option = scanner.next().toLowerCase();
        } while (!option.equals("q"));

    }

    private static Geocod getGeo() {
        Geocod geo = new Geocod();
        String location = "";
        Scanner sc = new Scanner(System.in);
        System.out.printf("%-10s", "Your city?: ");
        location += sc.next();
        try {
            geo = GeocodeAPI.getLoc(location, APIkey);
        } catch (IOException e) {
            System.out.printf("%s%n", "Something wrong! Check location name: use only english symbols");
            geo = getGeo();
        }
        return geo;
    }

    private static List<GLocation> getLocationsList(Geocod g) {
        List<GLocation> newList = new ArrayList<>();
        for (GResult gr : g.getResults()) {
            newList.addAll(gr.getLocations());
        }
        return newList;
    }

    private static void printLocationsList(List<GLocation> list) {
        int counter = 0;
        for (GLocation l : list) {
            System.out.printf("%d. City: %-20s %-5s %-10s%n",
                    counter++, l.getAdminArea5(), l.getAdminArea1Type(), l.getAdminArea1());
        }
    }


    private static int getLocIndex(List<GLocation> list) {
        int index = -1;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Choose your city. Use only non-negative integers: ");
                index = sc.nextInt();
            } catch (InputMismatchException ignored) {
            }
        } while (index < 0 || index >= list.size());
        return index;
    }


    private static GLocation getLocationByIndex(List<GLocation> loc, int index) {
        return loc.get(index);
    }

    private static Coordinates getCoordinates(GLocation location) {
        double latitude = location.getLatLng().getLat();
        double longitude = location.getLatLng().getLng();
        return new Coordinates(latitude, longitude);
    }

    private static URL getURL(Coordinates coods) throws MalformedURLException {
        String oneCallApiURL = "https://api.openweathermap.org/data/2.5/onecall?";
        String apiKey = "&appid=" + ONECALLAPIkey;
        String parameters = "&units=metric";
        double latitude = coods.getLat();
        double longitude = coods.getLon();
        String lat = "lat=" + latitude;
        String lon = "&lon=" + longitude;
        String finaleStr = oneCallApiURL + lat + lon + apiKey + parameters;
        return new URL(finaleStr);
    }

    private static int getWeatherType() {
        int type = -1;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Which weather you need?");
                System.out.println("0. Current.");
                System.out.println("1. Hourly");
                System.out.println("2. Daily");
                System.out.println("Use only non-negative integers: ");
                type = sc.nextInt();
            } catch (InputMismatchException ignored) {
            }
        } while (type < 0 || type > 2);
        return type;

    }

    private static void getWeather(JWeatherStatus weatherStatus, int type) {
        switch (type) {
            case 0:
                printCurrent(weatherStatus);
                break;
            case 1:
                printHourly(weatherStatus);
                break;
            case 2:
                printDaily(weatherStatus);
                break;
            default:
                System.out.println("Something wrong!");
        }
    }

    private static void printHourly(JWeatherStatus weatherStatus) {
        long dt;
        double temp;
        double feelsLike;
        int pressure;
        int humidity;
        String description;
        Date date;
        String title = String.format("%-35s %-6s %-12s %-12s %-12s %-15s%n",
                "Date", "temp", "feelsLike", "pressure", "humidity", "description");
        System.out.print(title);
        for (JHourlyWeather w : weatherStatus.getHourly()) {
            dt = w.getDt();
            date = new Date(dt * 1000);
            temp = w.getTemp();
            feelsLike = w.getFeels_like();
            pressure = w.getPressure();
            humidity = w.getHumidity();
            description = w.getWeather().get(0).getDescription();
            String body = String.format("%-35s %-6.1f %-12.1f %-12d %-12d %-15s%n",
                    date.toString(), temp, feelsLike, pressure, humidity, description);
            System.out.print(body);
        }

    }

    private static void printCurrent(JWeatherStatus weatherStatus) {
        // dt sunrise sunset temp feels_like pressure humidity rain_status

        long dt = weatherStatus.getCurrent().getDt();
        Date date = new Date(dt * 1000);
        long sunrise = weatherStatus.getCurrent().getSunrise();
        Date sunriseTime = new Date(sunrise * 1000);
        long sunset = weatherStatus.getCurrent().getSunset();
        Date sunsetTime = new Date(sunset * 1000);
        double temp = weatherStatus.getCurrent().getTemp();
        double feelsLike = weatherStatus.getCurrent().getFeels_like();
        int pressure = weatherStatus.getCurrent().getPressure();
        int humidity = weatherStatus.getCurrent().getHumidity();
        String description = weatherStatus.getCurrent().getWeather().get(0).getDescription();
        String title = String.format("%-35s %-6s %-12s %-12s %-12s %-15s%n",
                "Date", "temp", "feelsLike", "pressure", "humidity", "description");
        String body = String.format("%-35s %-6.1f %-12.1f %-12d %-12d %-15s",
                date.toString(), temp, feelsLike, pressure, humidity, description);
        System.out.println(title + body);
    }

    private static void printDaily(JWeatherStatus weatherStatus) {
        // dt  temp feels_like pressure humidity rain_status
        long dt;
        double tempD;
        double tempN;
        double feelsLikeD;
        double feelsLikeN;
        int pressure;
        int humidity;
        String description;

        String title = String.format("%-40s %-13s %-13s %-20s %-20s %-12s %-12s %-15s%n",
                "Date", "tempDay", "tempNight", "feelsLikeDay", "feelsLikeNight", "pressure", "humidity", "description");
        System.out.print(title);
        for (JWeatherD w : weatherStatus.getDaily()) {
            dt = w.getDt();
            Date date = new Date(dt * 1000);
            tempD = w.getTemp().getDay();
            tempN = w.getTemp().getNight();
            feelsLikeD = w.getFeels_like().getDay();
            feelsLikeN = w.getFeels_like().getNight();
            pressure = w.getPressure();
            humidity = w.getHumidity();
            description = w.getWeather().get(0).getDescription();
            String body = String.format("%-40s %-13.1f %-13.1f %-20.1f %-20.1f %-12d %-12d %-15s%n",
                    date.toString(), tempD, tempN, feelsLikeD, feelsLikeN, pressure, humidity, description);
            System.out.print(body);
        }
    }

    private static class Coordinates {
        private double lat;
        private double lon;

        public Coordinates() {
            this.lat = -99.999999;
            this.lon = -99.999999;
        }

        public Coordinates(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }
}
