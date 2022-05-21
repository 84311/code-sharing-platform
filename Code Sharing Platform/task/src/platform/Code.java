package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "code")
@Table(name = "code")
@Getter
@Setter
public class Code {
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "code_value")
    @JsonProperty("code")
    private String codeValue;

    @Column(name = "date_time")
    @JsonIgnore
    private LocalDateTime dateTime;

    public Code(String codeValue, LocalDateTime dateTime) {
        this.codeValue = codeValue;
        this.dateTime = dateTime;
    }

    public Code() {

    }

    @JsonProperty("date")
    public String getDateTimeAsString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }
}
