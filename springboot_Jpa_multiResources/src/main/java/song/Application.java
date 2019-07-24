package song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(basePackages = "song")
public class Application {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/index")
    String index() {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
