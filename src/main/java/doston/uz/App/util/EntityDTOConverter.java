package doston.uz.App.util;

import doston.uz.App.dto.groupDTO.GroupResponseDTO;
import doston.uz.App.dto.studentDTO.StudentResponseDTO;
import doston.uz.App.model.Group;
import doston.uz.App.model.Student;

public class EntityDTOConverter {



    public static GroupResponseDTO convertToGroupResponseDTO(Group group) {

        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();



        groupResponseDTO.setId(group.getId());
        groupResponseDTO.setInfoAboutGroup(group.getInfoAboutGroup());
        groupResponseDTO.setTeacherId(group.getTeacher().getId());
        groupResponseDTO.setLevel(group.getLevel());
        groupResponseDTO.setLessonTime(group.getLessonTime());
        groupResponseDTO.setStartDate(group.getStartDate());
        groupResponseDTO.setActive(group.isActive());
        return groupResponseDTO;

    }

    public static StudentResponseDTO convertToStudentResponseDTO(Student student) {

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setSurname(student.getSurname());
        studentResponseDTO.setPhoneNumber(student.getPhoneNumber());
        studentResponseDTO.setLevel(student.getLevel());
        studentResponseDTO.setTeacherId(student.getTeacher().getId());

        // Check if the student has a group before getting the group ID
        if (student.getGroup() != null) {
            studentResponseDTO.setGroupId(student.getGroup().getId());
        } else {
            studentResponseDTO.setGroupId(null);
        }
        studentResponseDTO.setStartDate(student.getStartDate());
        studentResponseDTO.setEndDate(student.getEndDate());
        studentResponseDTO.setActive(student.isActive());
        return studentResponseDTO;

    }


}
