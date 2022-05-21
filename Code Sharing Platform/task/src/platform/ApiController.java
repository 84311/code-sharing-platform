package platform;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/code")
public class ApiController {
    @Resource
    private CodeRepository codeRepository;

    public ApiController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/{n}")
    public Code getNthCode(@PathVariable long n) {
        return codeRepository.findById(n)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    public Map<String, String> setCode(@RequestBody Map<String, String> codeJSON) {
        Code code = new Code(codeJSON.get("code"), LocalDateTime.now());
        codeRepository.save(code);

        String id = String.valueOf(codeRepository.count());
        return Map.of("id", id);
    }

    @GetMapping("/latest")
    public List<Code> getLatestNCodes() {
        return codeRepository.findLastNAdded(10);
    }
}
