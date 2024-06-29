package doston.uz.App.controller;

import doston.uz.App.dto.studentDTO.StudentResponseDTO;
import doston.uz.App.model.Group;
import doston.uz.App.model.Teacher;
import doston.uz.App.model.enums.Level;
import doston.uz.App.model.Student;
import doston.uz.App.dto.studentDTO.StudentDTO;
import doston.uz.App.service.EnrollmentService;
import doston.uz.App.service.StudentService;
import doston.uz.App.service.TeacherService;
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

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EnrollmentService enrollmentService;

    @ModelAttribute("teachers")
    public List<Teacher> teachers() {
        return teacherService.getTeachers();
    }

    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }

    @GetMapping("/teachers/{teacherId}/groups")
    @ResponseBody
    public List<Group> getTeacherGroups(@PathVariable Integer teacherId) {
        return teacherService.getGroupsByTeacherId(teacherId);
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

    @GetMapping("/showFormForAddWithEnrollment")
    public String showFormForAdd(@RequestParam(value = "enrollmentId", required = false) Integer enrollmentId,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "surname", required = false) String surname,
                                 @RequestParam(value = "level" , required = false) String level,
                                 @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                 Model model) {
        // Create a new Student object and populate it with the enrollment data
        StudentDTO student = new StudentDTO();
        student.setName(name);
        student.setSurname(surname);
        student.setLevel(Level.valueOf(level));
        student.setPhoneNumber(phoneNumber);

        System.out.println(student);


        model.addAttribute("student", student);


        model.addAttribute("enrollmentId", enrollmentId);


        return "tracker/forms/student-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") Integer studentId, Model model) {
        System.out.println("Student Id " + studentId);
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
    public String saveStudent(@ModelAttribute("student") StudentDTO student,
                              @RequestParam(value = "enrollmentId", required = false) Integer enrollmentId) {

        System.out.println("Enrollment Id is: " + enrollmentId);

        try {
            studentService.addStudent(student);

            if (enrollmentId != null) {
                System.out.println("Marking enrollment as added to group: " + enrollmentId);
                enrollmentService.markAsAddedToGroup(enrollmentId);
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return "redirect:/error-page";
        }

        return "redirect:/api/v1/students/list";
    }

    @GetMapping("/end-date")
    public String updateStudent(@RequestParam("studentId") Integer studentId) {
        studentService.endStudentJourney(studentId);

        return "redirect:/api/v1/students/list";
    }

}
