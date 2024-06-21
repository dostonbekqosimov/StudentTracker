package doston.uz.App.controller;

import doston.uz.App.dto.groupDTO.GroupResponseDTO;
import doston.uz.App.dto.studentDTO.StudentResponseDTO;
import doston.uz.App.dto.teacherDTO.TeacherPostDTO;
import doston.uz.App.dto.teacherDTO.TeacherUpdateDTO;
import doston.uz.App.model.enums.Level;
import doston.uz.App.model.Student;
import doston.uz.App.model.Teacher;
import doston.uz.App.service.GroupService;
import doston.uz.App.service.StudentService;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;


    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }

    @GetMapping("/list")
    public String getTeachers(Model model) {
        List<Teacher> teachers = teacherService.getTeachers();

        model.addAttribute("teachers", teachers);
        return "tracker/lists/list-teachers";
    }

    @GetMapping("/groupListForTeacher")
    public String getStudentsForTeacher(@RequestParam("teacherId") Integer teacherId, Model model) {

// keyinchalik shuni o'rniga guruhlarni chiqazamiz birinchi keyin o'sha guruhdagi o'quvchilarni chiqazamiz
        // bu bo'ldi yani yuqoridagi

        Teacher teacher = teacherService.findTeacherById(teacherId);

        List<GroupResponseDTO> groups = groupService.getAllGroupsWithTeacherId(teacherId);


        model.addAttribute("groupList", groups);
        model.addAttribute("teacher", teacher);
        return "tracker/lists/list-groups";

    }

    @GetMapping("/showFormForAdd")
    public String addNewTeacher(Model model) {

        TeacherPostDTO teacherDTO = new TeacherPostDTO();

        model.addAttribute("teacher", teacherDTO);


        return "tracker/forms/teacher-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("teacherId") Integer teacherId, Model model) {

        TeacherUpdateDTO teacherUpdateDTO = teacherService.getTeacherForUpdate(teacherId);


        model.addAttribute("teacher", teacherUpdateDTO);
        return "tracker/forms/teacher-form-update";
    }

    @PutMapping("/update")
    public String updateTeacher(@ModelAttribute("teacher") TeacherUpdateDTO teacherUpdateDTO) {

        System.out.println("Teacher id " + teacherUpdateDTO.getId() + " in teacher controller");


        teacherService.updateTeacher(teacherUpdateDTO);


        return "redirect:/api/v1/teachers/list";
    }

    @PostMapping("/save")
    public String addTeacher(@ModelAttribute("teacher") TeacherPostDTO teacherPostDTO) {


         teacherService.addTeacher(teacherPostDTO);

        return "redirect:/api/v1/teachers/list";
    }
}
