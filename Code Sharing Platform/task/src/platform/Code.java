package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Code {
    @JsonProperty("code")
    private String codeValue;
    @JsonIgnore
    private LocalDateTime dateTime;

    public Code(String codeValue, LocalDateTime dateTime) {
        this.codeValue = codeValue;
        this.dateTime = dateTime;
    }

    @JsonProperty("date")
    public String getDateTimeAsString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }
}
