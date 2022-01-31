package JSONReader.GeocodingAPI;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GeocodeAPI {
    private static StringBuilder result;
    private static String textURL = "http://www.mapquestapi.com/geocoding/v1/address?key=htwx2dUIMt7wIJwFqmaYRxoOG37cTrQ6&location=dubno&ignoreLatLngInput=true&maxResults=10";

    public static void main(String[] args) throws IOException {
//        System.out.println(getJSON());

        Geocod g = getLoc();
        for (GResult gr : g.getResults()) {
            for (GLocation l : gr.getLocations()) {
                System.out.printf("City: %-20s %-5s %-10s %-10.6f %-4.6f%n", l.getAdminArea5(), l.getAdminArea1Type(), l.getAdminArea1(), l.getLatLng().getLat(), l.getLatLng().getLng());
            }
        }
    }


    public static Geocod getLoc() throws IOException {
        String url = "http://www.mapquestapi.com/geocoding/v1/address?key=";
        String APIkey = "htwx2dUIMt7wIJwFqmaYRxoOG37cTrQ6";
        String location = "&location=";
        String properties = "&ignoreLatLngInput=true";
        Scanner sc = new Scanner(System.in);
        System.out.printf("%-10s", "Your city?: ");
        location += sc.next();
        ObjectMapper objectMapper = new ObjectMapper();
        String textUrl = url + APIkey + location + properties;
        URL generatedUrl = new URL(textUrl);
        Geocod geo = objectMapper.readValue(generatedUrl, Geocod.class);
        return geo;
    }

    public static Geocod getLoc(String name, String APIk) throws IOException {
        String url = "http://www.mapquestapi.com/geocoding/v1/address?key=";
//        String APIkey = "htwx2dUIMt7wIJwFqmaYRxoOG37cTrQ6";
        String location = "&location=" + name;
        String properties = "&ignoreLatLngInput=true";
        ObjectMapper objectMapper = new ObjectMapper();
        String textUrl = url + APIk + location + properties;
        URL generatedUrl = new URL(textUrl);
        Geocod geo = objectMapper.readValue(generatedUrl, Geocod.class);
        return geo;
    }

    private static String getJSON() {
        result = new StringBuilder();
        try {
            URL url = new URL(textURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
