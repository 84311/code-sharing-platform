package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
    Code code;

    public ApiController(@Autowired Code code) {
        this.code = code;
    }

    @GetMapping("/api/code")
    @ResponseBody
    public ResponseEntity<Code> getCode() {
        return ResponseEntity.ok().
                header("Content-Type", "application/json")
                .body(code);
    }
}
