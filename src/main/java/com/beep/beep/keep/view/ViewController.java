package com.beep.beep.keep.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Controller() (x)
@RequestMapping("/excel")
public class ViewController {
    @GetMapping("/no")
    public String main() {
        System.out.println(".");
        return "main"; //메인화면
    }

    @GetMapping("/success")
    public String sucess() {
        return "success"; //성공화면
    }
}
