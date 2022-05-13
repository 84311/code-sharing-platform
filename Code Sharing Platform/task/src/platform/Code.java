package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Getter
@Setter
public class Code {
    @JsonProperty("code")
    private String codeValue;
    @JsonIgnore
    private LocalDateTime dateTime;

    public Code() {
        this.codeValue = "public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}";
        this.dateTime = LocalDateTime.now();
    }

    @JsonProperty("date")
    public String getDateTimeAsString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }
}
