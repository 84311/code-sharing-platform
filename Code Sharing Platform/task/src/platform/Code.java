package platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Code {
    @JsonProperty("code")
    private String codeValue;

    public Code() {
        this.codeValue = "public templates void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n";
    }

    @Override
    public String toString() {
        return codeValue;
    }
}
