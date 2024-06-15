package doston.uz.App.controller;

import doston.uz.App.dto.studentDTO.StudentResponseDTO;
import doston.uz.App.model.enums.Level;
import doston.uz.App.model.Student;
import doston.uz.App.dto.studentDTO.StudentDTO;
import doston.uz.App.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }


    @GetMapping("/list")
    public String getStudents(Model model) {
        System.out.println("I am called from getStudents");

        List<StudentResponseDTO> students = studentService.getStudents();

        students.sort(Comparator.comparing(StudentResponseDTO::isActive).reversed());


        model.addAttribute("students", students);
        return "tracker/lists/list-students";
    }


    @GetMapping("/showFormForAdd")
    public String addEmployee(Model model) {

        StudentDTO student = new StudentDTO();

        model.addAttribute("student", student);


        return "tracker/forms/student-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") Integer studentId, Model model) {

        StudentDTO studentDTO = studentService.findStudentById(studentId);

        model.addAttribute("student", studentDTO);
        return "tracker/forms/student-form-update";
    }

    @PutMapping("/update")
    public String updateStudent(@ModelAttribute("student") StudentDTO studentDTO) {

        studentService.updateStudent(studentDTO);

        return "redirect:/api/v1/students/list";
    }

    // I have to write another method for updating!!!
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") StudentDTO studentDTO) {

        studentService.addStudent(studentDTO);

        return "redirect:/api/v1/students/list";
    }

    // UTC time zone (+00:00), so we need to convert it to UTC+5 (+05:30)

    @GetMapping("/end-date")
    public String updateStudent(@RequestParam("studentId") Integer studentId) {
        studentService.endStudentJourney(studentId);

        return "redirect:/api/v1/students/list";
    }

}
