package allqueue.auth.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("auth")
public class Sample {

    @GetMapping("/sample")
    public String getMethodName() {
        return "hello!";
    }

}
