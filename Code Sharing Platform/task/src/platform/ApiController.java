package platform;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.time.Duration;
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

    @GetMapping("/{uuid}")
    public Code getNthCode(@PathVariable String uuid) {
        Code code = codeRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (code.isTimeRestricted()) {
            long elapsedTime = Math.abs(Duration.between(
                            LocalDateTime.now(), code.getDateTime())
                    .toSeconds());
            long remainingTime = code.getAvailableDuring() - elapsedTime;

            if (remainingTime <= 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            code.setRemainingTime(remainingTime);
        }
        if (code.isViewRestricted()) {
            if (code.getViews() <= 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            code.setViews(code.getViews() - 1);
        }
        codeRepository.save(code);
        return code;
    }

    @PostMapping("/new")
    public Map<String, String> setCode(@RequestBody CodeDTO codeDTO) {
        Code code = Code.createFromDTO(codeDTO);
        codeRepository.save(code);
        return Map.of("id", code.getId());
    }

    @GetMapping("/latest")
    public List<Code> getLatestNCodes() {
        return codeRepository.findLastNAdded(10);
    }
}
