package JSONReader.GeocodingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GResult {
    private ProvidedLocation providedLocation;
    private List<GLocation> locations;

    public ProvidedLocation getProvidedLocation() {
        return providedLocation;
    }

    public void setProvidedLocation(ProvidedLocation providedLocation) {
        this.providedLocation = providedLocation;
    }

    public List<GLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<GLocation> locations) {
        this.locations = locations;
    }
}
