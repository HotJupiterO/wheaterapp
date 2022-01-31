package JSONReader.GeocodingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GInfo {
    private int statuscode;
    private Copyryght copyryght;
    List<String> messages;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public Copyryght getCopyryght() {
        return copyryght;
    }

    public void setCopyryght(Copyryght copyryght) {
        this.copyryght = copyryght;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
