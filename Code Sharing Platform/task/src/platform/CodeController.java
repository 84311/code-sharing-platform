package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/code")
public class CodeController {
    @Resource
    private CodeRepository codeRepository;

    public CodeController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodeById(@PathVariable String uuid, Model model) {
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

        model.addAttribute("snippets", List.of(code));
        model.addAttribute("time", "The code will be available for " + code.getRemainingTime() + " seconds");
        model.addAttribute("views", code.getViews() + " more views allowed");
        model.addAttribute("title", "Code");
        return "code";
    }

    @GetMapping(value = "/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode() {
        return "new-code";
    }

    @GetMapping(value = "/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatestNCodes(Model model) {
        model.addAttribute("snippets", codeRepository.findLastNAdded(10));
        model.addAttribute("title", "Latest");
        return "code";
    }
}
