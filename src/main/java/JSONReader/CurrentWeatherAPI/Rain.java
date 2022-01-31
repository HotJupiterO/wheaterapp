package JSONReader.CurrentWeatherAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain {
    private int oneHourVolume;
    private int threeHoursVolume;

    public int getOneHourVolume() {
        return oneHourVolume;
    }

    public void setOneHourVolume(int oneHourVolume) {
        this.oneHourVolume = oneHourVolume;
    }

    public int getThreeHoursVolume() {
        return threeHoursVolume;
    }

    public void setThreeHoursVolume(int threeHoursVolume) {
        this.threeHoursVolume = threeHoursVolume;
    }
}
