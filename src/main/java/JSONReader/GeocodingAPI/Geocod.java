package JSONReader.GeocodingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Geocod {
    private GInfo info;
    private GOptions options;
    private List<GResult> results;

    public GInfo getInfo() {
        return info;
    }

    public void setInfo(GInfo info) {
        this.info = info;
    }

    public GOptions getOptions() {
        return options;
    }

    public void setOptions(GOptions options) {
        this.options = options;
    }

    public List<GResult> getResults() {
        return results;
    }

    public void setResults(List<GResult> results) {
        this.results = results;
    }
}
