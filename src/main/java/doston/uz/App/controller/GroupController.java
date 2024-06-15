package doston.uz.App.controller;

import doston.uz.App.dto.groupDTO.GroupForm;
import doston.uz.App.dto.groupDTO.GroupResponseDTO;
import doston.uz.App.dto.studentDTO.StudentDTO;
import doston.uz.App.model.Group;
import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import doston.uz.App.service.GroupService;
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


    @GetMapping("/showFormForCreateGroup")
    public String formGroup(Model model) {

        GroupForm newGroup = new GroupForm();

        model.addAttribute("group", newGroup);


        return "tracker/forms/group-form";
    }

    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("group") GroupForm groupForm) {

        groupService.addGroup(groupForm);

        return "redirect:/api/v1/groups/list";
    }

}
