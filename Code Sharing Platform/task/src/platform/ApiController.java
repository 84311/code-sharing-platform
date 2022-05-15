package platform;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/code")
public class ApiController {
    CodeRepository codeRepository;

    public ApiController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/{n}")
    public Code getNthCode(@PathVariable int n) {
        return codeRepository.getById(n);
    }

    @PostMapping("/new")
    public Map<String, String> setCode(@RequestBody Map<String, String> codeJSON) {
        codeRepository.add(new Code(codeJSON.get("code"), LocalDateTime.now()));
        return Map.of("id", String.valueOf(codeRepository.size()));
    }

    @GetMapping("/latest")
    public List<Code> getLatestNCodes() {
        return codeRepository.getRecentlyUploaded(10);
    }
}
