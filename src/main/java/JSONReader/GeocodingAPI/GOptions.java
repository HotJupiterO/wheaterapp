package JSONReader.GeocodingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GOptions {
    private int maxResults;
    private boolean thumbMaps;
    private boolean ignoreLatLngInput;

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public boolean isThumbMaps() {
        return thumbMaps;
    }

    public void setThumbMaps(boolean thumbMaps) {
        this.thumbMaps = thumbMaps;
    }

    public boolean isIgnoreLatLngInput() {
        return ignoreLatLngInput;
    }

    public void setIgnoreLatLngInput(boolean ignoreLatLngInput) {
        this.ignoreLatLngInput = ignoreLatLngInput;
    }
}
