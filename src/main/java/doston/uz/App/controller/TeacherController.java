package doston.uz.App.controller;

import doston.uz.App.model.Level;
import doston.uz.App.model.Student;
import doston.uz.App.model.Teacher;
import doston.uz.App.dto.TeacherDTO;
import doston.uz.App.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }

    @GetMapping("/list")
    public String getTeachers(Model model) {
        List<Teacher> teachers = teacherService.getTeachers();

        model.addAttribute("teachers", teachers);
        return "tracker/list-teachers";
    }

    @GetMapping("/listForTeacher")
    public String getStudentForTeacher(@RequestParam("teacherId") Integer teacherId, Model model) {
        System.out.println("Received teacherId: " + teacherId);


        Teacher teacher = teacherService.findTeacherById(teacherId);
        System.out.println("After finding teacher by id: " + teacher);

// teacher ga tegishli studentlarni topa olmadim BUG bor here

        List<Student> students = teacher.getStudents();
        System.out.println("Students for teacher: " + students);
        System.out.println(students);

        model.addAttribute("students", students);
        return "tracker/list-students";

    }

    @PostMapping
    public Teacher addTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.addTeacher(teacherDTO);
    }
}
