package JSONReader.CurrentWeatherAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherAPIReader {
    private int cod;
    private String massage;
    private int cnt;
    private List<WeatherInf> list;
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<WeatherInf> getList() {
        return list;
    }

    public void setList(List<WeatherInf> list) {
        this.list = list;
    }

/*
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
*/

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
