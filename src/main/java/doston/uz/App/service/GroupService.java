package doston.uz.App.service;

import doston.uz.App.dto.groupDTO.GroupForm;
import doston.uz.App.dto.groupDTO.GroupResponseDTO;
import doston.uz.App.model.Group;
import doston.uz.App.model.Teacher;
import doston.uz.App.repository.GroupRepository;
import doston.uz.App.repository.StudentRepository;
import doston.uz.App.repository.TeacherRepository;
import doston.uz.App.util.EntityDTOConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private StudentRepository studentRepository;

    public void addGroup(GroupForm groupForm) {


        Group group = new Group();

        group.setInfoAboutGroup(groupForm.getInfoAboutGroup());
        group.setTeacher(groupForm.getTeacher());
        group.setLevel(groupForm.getLevel());
        group.setLessonTime(groupForm.getLessonTime());
        group.setActive(true);


        groupRepository.save(group);
    }

    public List<GroupResponseDTO> getAllGroups() {

        List<Group> groups = groupRepository.findAll();

        List<GroupResponseDTO> groupResponseDTOS = new ArrayList<>();


        for (Group group : groups) {
            GroupResponseDTO groupResponseDTO = EntityDTOConverter.convertToGroupResponseDTO(group);
            groupResponseDTO.setStudentCount(studentRepository.countByGroupId(group.getId()));
            groupResponseDTOS.add(groupResponseDTO);
        }

        return groupResponseDTOS;


    }

    public List<GroupResponseDTO> getAllGroupsWithTeacherId(Integer teacherId) {
        List<Group> groups = groupRepository.findAllByTeacherId(teacherId);

        List<GroupResponseDTO> groupResponseDTOS = new ArrayList<>();


        for (Group group : groups) {
            GroupResponseDTO groupResponseDTO = EntityDTOConverter.convertToGroupResponseDTO(group);
            // Buyoqda studentlarni countini o'zim qo'shyabman.
            groupResponseDTO.setStudentCount(studentRepository.countByGroupId(group.getId()));
            groupResponseDTOS.add(groupResponseDTO);
        }

        return groupResponseDTOS;

    }

    public List<Group> findByTeacher(Teacher teacher) {

        return groupRepository.findAllByTeacher(teacher);
    }

    public GroupForm findGroupById(Integer groupId) {

        Group existingGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        return EntityDTOConverter.converToGroupForm(existingGroup);

    }

    public void editGroup(Integer groupId, GroupForm groupForm) {

        Group existingGroup = groupRepository.findById(groupId).
                orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        existingGroup.setInfoAboutGroup(groupForm.getInfoAboutGroup());
        existingGroup.setTeacher(groupForm.getTeacher());
        existingGroup.setLevel(groupForm.getLevel());
        existingGroup.setLessonTime(groupForm.getLessonTime());

        groupRepository.save(existingGroup);

    }

    @Transactional
    public void deleteGroup(Integer groupId) {
        groupRepository.deleteById(groupId);
    }
}
