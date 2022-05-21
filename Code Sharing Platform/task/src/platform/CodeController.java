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
import java.util.List;

@Controller
@RequestMapping("/code")
public class CodeController {
    @Resource
    private CodeRepository codeRepository;

    public CodeController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(value = "/{n}", produces = MediaType.TEXT_HTML_VALUE)
    public String getNthCode(@PathVariable long n, Model model) {
        List<Code> snippet = List.of(codeRepository.findById(n)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        model.addAttribute("snippets", snippet);
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
