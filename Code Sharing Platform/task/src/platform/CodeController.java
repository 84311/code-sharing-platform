package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/code")
public class CodeController {
    Code code;

    public CodeController(@Autowired Code code) {
        this.code = code;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getCode(Model model, HttpServletResponse response) {
        model.addAttribute("code", code.getCodeValue());
        model.addAttribute("date", code.getDateTimeAsString());

        response.addHeader("Content-Type", "text/html");
        return "code";
    }

    @GetMapping(value = "/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode() {
        return "new-code";
    }
}
