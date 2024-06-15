package doston.uz.App.service;

import doston.uz.App.dto.teacherDTO.TeacherPostDTO;
import doston.uz.App.dto.teacherDTO.TeacherUpdateDTO;
import doston.uz.App.model.Student;
import doston.uz.App.model.Teacher;
import doston.uz.App.repository.StudentRepository;
import doston.uz.App.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;


    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public void addTeacher(TeacherPostDTO teacherDTO) {

        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setSurname(teacherDTO.getSurname());
        teacher.setPhoneNumber(teacherDTO.getPhoneNumber());
        teacherRepository.save(teacher);
    }

    public Teacher findTeacherById(Integer teacherId) {
        System.out.println("Received teacherId: " + teacherId);
        return teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));

    }

    public List<Student> getStudentsForTeacher(Integer teacherId) {



        return studentRepository.findAllByTeacherId(teacherId);
    }

    public TeacherUpdateDTO getTeacherForUpdate(Integer teacherId) {

        Teacher existingTeacher = teacherRepository.findById(teacherId).get();

        TeacherUpdateDTO teacherUpdateDTO = new TeacherUpdateDTO();

        teacherUpdateDTO.setId(existingTeacher.getId());
        teacherUpdateDTO.setName(existingTeacher.getName());
        teacherUpdateDTO.setSurname(existingTeacher.getSurname());
        teacherUpdateDTO.setPhoneNumber(existingTeacher.getPhoneNumber());

        return teacherUpdateDTO;


    }

  

    public void updateTeacher(TeacherUpdateDTO teacherUpdateDTO) {
        Teacher existingTeacher = teacherRepository.findById(teacherUpdateDTO.getId()).get();
        System.out.println("Teacher id " + teacherUpdateDTO.getId() + " in teacher Service");
        existingTeacher.setId(teacherUpdateDTO.getId());
        existingTeacher.setName(teacherUpdateDTO.getName());
        existingTeacher.setSurname(teacherUpdateDTO.getSurname());
        existingTeacher.setPhoneNumber(teacherUpdateDTO.getPhoneNumber());
        teacherRepository.save(existingTeacher);
    }
}
