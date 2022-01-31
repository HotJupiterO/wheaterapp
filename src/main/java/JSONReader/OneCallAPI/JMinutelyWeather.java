package JSONReader.OneCallAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JMinutelyWeather {
    private long dt;
    private int precipitation;
}
