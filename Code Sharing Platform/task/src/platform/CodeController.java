package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CodeController {
    Code code;

    public CodeController(@Autowired Code code) {
        this.code = code;
    }

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public String getCode(Model model, HttpServletResponse response) {
        model.addAttribute("code", code.toString());
        response.addHeader("Content-Type", "text/html");
        return "code";
    }
}
