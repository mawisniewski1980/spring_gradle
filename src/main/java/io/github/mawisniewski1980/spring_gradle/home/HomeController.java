package io.github.mawisniewski1980.spring_gradle.home;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
@NoArgsConstructor
public class HomeController {

    @GetMapping({"", "/", "/index", "index"})
    public String home(Model model) {
        log.info("HomeController, timestamp: {}", LocalDateTime.now());
        model.addAttribute("grettings", "Hello World!!!");
        return "index";
    }
}
