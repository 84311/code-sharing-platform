package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "code")
@Table(name = "code")
@Getter
@Setter
@NoArgsConstructor
public class Code {

    @Id
    @JsonIgnore
    private String id;

    @Column(name = "date_time")
    @JsonIgnore
    private LocalDateTime dateTime;

    @Column(name = "time_restricted")
    @JsonIgnore
    private boolean timeRestricted;

    @Column(name = "view_restricted")
    @JsonIgnore
    private boolean viewRestricted;

    @Column(name = "available_during")
    @JsonIgnore
    private Long availableDuring;

    @Column(name = "code_value")
    @JsonProperty("code")
    private String codeValue;

    private Long views;

    @Column(name = "remaining_time")
    @JsonProperty("time")
    private Long remainingTime;

    @JsonProperty("date")
    public String getDateTimeAsString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }

    public static Code createFromDTO(CodeDTO codeDTO) {
        Code code = new Code();
        code.setId(UUID.randomUUID().toString());
        code.setDateTime(LocalDateTime.now());

        code.codeValue = codeDTO.getCode();
        code.availableDuring = codeDTO.getTime();
        code.remainingTime = codeDTO.getTime();
        code.views = codeDTO.getViews();

        code.timeRestricted = codeDTO.getTime() != 0;
        code.viewRestricted = codeDTO.getViews() != 0;

        return code;
    }
}

@Setter
@Getter
@NoArgsConstructor
class CodeDTO {
    private String code;
    private Long time;
    private Long views;
}
