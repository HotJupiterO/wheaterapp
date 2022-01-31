package JSONReader.OneCallAPI;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JWeatherStatus {
    private double lat;
    private double lon;
    private String timezone;
    private JCurrentWeather current;
    private JMinutelyWeather minutely;  // just for US
    private List<JHourlyWeather> hourly;
    private List<JWeatherD> daily;

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

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public JCurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(JCurrentWeather current) {
        this.current = current;
    }

    public List<JHourlyWeather> getHourly() {
        return hourly;
    }

    public void setHourly(List<JHourlyWeather> hourly) {
        this.hourly = hourly;
    }

    public List<JWeatherD> getDaily() {
        return daily;
    }

    public void setDaily(List<JWeatherD> daily) {
        this.daily = daily;
    }
}
