package com.example.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @RequestMapping(
            value = "/send",
            // RequestMapping에 method 인자를 추가하면
            // 특정 method에 대해서만 작동
            method = RequestMethod.GET
    )
    public String getForm() {
        return "send";
    }

//    @RequestMapping("/receive") 두개가 같은 동작을 수행함, 좀더 명시적
    @PostMapping("/receive")
    public String receive(
            @RequestParam("msg")
            String msg,
            @RequestParam("email")
            String email
    ) {
        System.out.println(msg);
        System.out.println(email);
        return "send";
    }
}
