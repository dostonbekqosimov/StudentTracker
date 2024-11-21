package doston.uz.App.controller;

import doston.uz.App.dto.studentDTO.StudentResponseDTO;
import doston.uz.App.model.Enrollment;
import doston.uz.App.model.Teacher;
import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import doston.uz.App.service.EnrollmentService;
import doston.uz.App.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private TeacherService teacherService;

    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }

    @ModelAttribute("teachers")
    public List<Teacher> teachers() {
        return teacherService.getTeachers();
    }

    @ModelAttribute("lessonTimes")
    public LessonTime[] lessonTimes() {
        return LessonTime.values();
    }

    @GetMapping("/list")
    public String listEnrollments(Model model) {

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();


        enrollments.sort(Comparator.comparing(Enrollment::isActive).reversed());

        model.addAttribute("enrollmentList", enrollments);

        return "tracker/lists/list-enrollments";
    }

    @GetMapping("/showFormForCreateEnrollment")
    public String showFormForAdd(Model model) {

        Enrollment enrollment = new Enrollment();
        model.addAttribute("enrollment", enrollment);

        return "tracker/forms/enrollment-form";


    }

    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") Integer id, Model model){

        Enrollment enrollment = enrollmentService.findEnrollmentById(id);

        model.addAttribute("enrollment", enrollment);

        return "tracker/forms/enrollment-form";

    }

    @PostMapping("/save")
    public String saveEnrollment(Enrollment enrollment) {

        enrollmentService.saveEnrollment(enrollment);


        return "redirect:/api/v1/enrollments/list";
    }

    @PostMapping("/addToGroup")
    public String addEnrollmentToGroup(){


        return "Hello";
    }

    @PostMapping("/delete")
    public String deleteEnrollmentById(@RequestParam("id") Integer id){
        logger.info("Delete method called with id: {}", id);
        enrollmentService.deleteEnrollmentById(id);

        return "redirect:/api/v1/enrollments/list";

    }


}
