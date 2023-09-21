package com.example.crud;

import com.example.crud.model.StudentDTO;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) {
        System.out.println(name);
        System.out.println(email);
        StudentDTO studentDTO = studentService.createStudent(name, email);
        System.out.println(studentDTO.toString());
//        return "redirect:/create-view";
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute(
                "studentList",
                studentService.readStudentAll());
        return "home";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id")Long id,
            Model model
    ) {
        System.out.println(id);
        model.addAttribute(
                "student",
                studentService.readStudent(id)
        );

        return "read";
    }

    @GetMapping("/{id}/update-view")
    public String updateView(
           @PathVariable("id") Long id, Model model
    ) {
        model.addAttribute("student", studentService.readStudent(id));
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable("id") Long id,
            // 이름 받아오고
            @RequestParam("name") String name,
            // email 받아오고
            @RequestParam("email") String email
    ) {
        // service 활용하기
        studentService.updateStudent(id, name, email);
        // 상세보기 페이지로 redirect
        return String.format("redirect:/%s", id);
    }

    // deleteView 메소드 만들기
    // GetMapping을 써서...

    @GetMapping("/{id}/delete-view")
    public String deleteView(
            @PathVariable("id") Long id, Model model
    ) {
        StudentDTO studentDTO = studentService.readStudent(id);
        model.addAttribute("student", studentDTO);
        return "delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id") Long id
    ) {
        studentService.deleteStudent(id);
        // update 때는 데이터가 남아있지만
        // delete는 돌아갈 상세페이지가 없다.
        // 그래서 home으로 돌아간다.
        return "redirect:/home";
    }
}

