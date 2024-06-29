package doston.uz.App.controller;

import doston.uz.App.dto.groupDTO.GroupForm;
import doston.uz.App.dto.groupDTO.GroupResponseDTO;
import doston.uz.App.dto.studentDTO.StudentDTO;
import doston.uz.App.dto.studentDTO.StudentResponseDTO;
import doston.uz.App.model.Group;
import doston.uz.App.model.Teacher;
import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import doston.uz.App.service.GroupService;
import doston.uz.App.service.StudentService;
import doston.uz.App.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @ModelAttribute("teachers")
    public List<Teacher> teachers() {
        return teacherService.getTeachers();
    }


    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }

    @ModelAttribute("lessonTimes")
    public LessonTime[] lessonTimes() {
        return LessonTime.values();
    }

    @GetMapping("/list")
    public String listGroup(Model model) {

        List<GroupResponseDTO> groupList = groupService.getAllGroups();

        model.addAttribute("groupList", groupList);

        return "tracker/lists/list-groups";
    }

    @GetMapping("/studentsListForGroup")
    public String getAllStudentWithGroupId(@RequestParam("groupId") Integer groupId, Model model) {
        List<StudentResponseDTO> students = studentService.getAllStudentWithGroupId(groupId);

        model.addAttribute("students", students);
        model.addAttribute("groupId", groupId);

        return "tracker/lists/list-students";


    }


    @GetMapping("/showFormForCreateGroup")
    public String formGroup(Model model) {

        GroupForm newGroup = new GroupForm();

        model.addAttribute("group", newGroup);


        return "tracker/forms/group-form";
    }

    @GetMapping("/showFormForUpdateGroup")
    public String updateGroup(@RequestParam("groupId") Integer groupId, Model model) {

        GroupForm existingGroup = groupService.findGroupById(groupId);

        model.addAttribute("group", existingGroup);
        model.addAttribute("groupId", groupId);


        return "tracker/forms/group-form-update";
    }

    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("group") GroupForm groupForm) {

        groupService.addGroup(groupForm);

        return "redirect:/api/v1/groups/list";
    }

    @PutMapping("/edit")
    public String editGroup(@RequestParam("groupId") Integer groupId, @ModelAttribute("group") GroupForm groupForm) {

        System.out.println("groupId: " + groupId);

        groupService.editGroup(groupId, groupForm);


        return "redirect:/api/v1/groups/list";
    }

    @PostMapping("/delete")
    public String deleteGroup(@RequestParam("groupId") Integer groupId){
        groupService.deleteGroup(groupId);
        return "redirect:/api/v1/groups/list";
    }


}
