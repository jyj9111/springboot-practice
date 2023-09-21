package com.example.mvc;

import com.example.mvc.model.LottoService;
import com.example.mvc.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class MvcController {
//    private int hitCount = 0;
    private final LottoService lottoService;

    public MvcController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @RequestMapping("/hits")
    public String hits(Model model) {
        int hitCount = lottoService.addHit();
        model.addAttribute("hits", hitCount);

        return "hits";
    }

    @RequestMapping("/lotto")
    public String lotto(Model model) {
        model.addAttribute("lottoList", lottoService.createLottoNum());
        return "lotto";
    }

    @RequestMapping("/history")
    public String history(Model model) {

        return "history";
    }




    @RequestMapping("/")
    public String home(Model model){

        model.addAttribute("message", "Hello, Thymeleaf!");
        return "home";
    }

    @RequestMapping("/student")
    public String student(Model model) {
        model.addAttribute("object", new Student("Hong GilDong", "fatherbrother@gojeon.com"));
        return "student";
    }

    @RequestMapping("/is-logged-in")
    public String isLoggedIn(Model model) {
        model.addAttribute("isLoggedIn", false);

        return "if-unless";
    }

    @RequestMapping("/each")
    public String items(Model model) {
//        List<String> listOfStrings = new ArrayList<>();
//        listOfStrings.add("An");
//        listOfStrings.add("Nyeong");
//        listOfStrings.add("Ha");
//        listOfStrings.add("Say");
//        listOfStrings.add("Yo");
//        model.addAttribute("listOfStrings", listOfStrings);
        List<Student> studentsList = Arrays.asList(
                new Student("Alex", "alex@gmail.com"),
                new Student("Brad", "brad@gmail.com"),
                new Student("Chad", "chad@gmail.com")
        );
        model.addAttribute("studentList", studentsList);

        return "each";
    }
}
