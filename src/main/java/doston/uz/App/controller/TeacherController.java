package doston.uz.App.controller;

import doston.uz.App.entity.Student;
import doston.uz.App.entity.Teacher;
import doston.uz.App.entity.TeacherDTO;
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

    @RequestMapping
    public String tracker() {
        return "Tracker is working";
    }

    @GetMapping("/list")
    public String getTeachers(Model model) {
        List<Teacher> teachers = teacherService.getTeachers();

        model.addAttribute("teachers", teachers);
        return "tracker/list-teachers";
    }

    @GetMapping("/listForTeacher")
    public String getStudentForTeacher(@RequestParam("teacherId") Integer teacherId, Model model) {

        Teacher teacher = teacherService.findTeacherById(teacherId);
        List<Student> students = teacher.getStudents();

        model.addAttribute("students", students);
        return "tracker/list-students";

    }

    @PostMapping
    public Teacher addTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.addTeacher(teacherDTO);
    }
}
