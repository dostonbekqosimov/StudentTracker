package doston.uz.App.service;

import doston.uz.App.entity.Teacher;
import doston.uz.App.entity.TeacherDTO;
import doston.uz.App.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher addTeacher(TeacherDTO teacherDTO) {

        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setSurname(teacherDTO.getSurname());
        teacher.setPhoneNumber(teacherDTO.getPhoneNumber());
        return teacherRepository.save(teacher);
    }

    public Teacher findTeacherById(Integer teacherId) {

        return teacherRepository.findById(teacherId).get();
    }
}
