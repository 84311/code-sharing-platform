package platform;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/api/code")
public class ApiController {
    Code code;

    public ApiController(@Autowired Code code) {
        this.code = code;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Code> getCode() {
        return ResponseEntity.ok().
                header("Content-Type", "application/json")
                .body(code);
    }

    @PostMapping("/new")
    @ResponseBody
    public EmptyJson setCode(@RequestBody Map<String, String> codeJSON) {
        code.setCodeValue(codeJSON.get("code"));
        code.setDateTime(LocalDateTime.now());

        return new EmptyJson();
    }

    @JsonSerialize
    static
    class EmptyJson {
    }
}
