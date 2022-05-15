package platform;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/code")
public class CodeController {
    CodeRepository codeRepository;

    public CodeController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(value = "/{n}", produces = MediaType.TEXT_HTML_VALUE)
    public String getNthCode(@PathVariable int n, Model model) {
        model.addAttribute("snippets", List.of(codeRepository.getById(n)));
        model.addAttribute("title", "Code");
        return "code";
    }

    @GetMapping(value = "/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode() {
        return "new-code";
    }

    @GetMapping(value = "/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatestNCodes(Model model) {
        model.addAttribute("snippets", codeRepository.getRecentlyUploaded(10));
        model.addAttribute("title", "Latest");
        return "code";
    }
}
