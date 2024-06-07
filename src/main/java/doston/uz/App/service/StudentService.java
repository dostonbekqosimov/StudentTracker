package doston.uz.App.service;

import doston.uz.App.entity.Student;
import doston.uz.App.entity.StudentDTO;
import doston.uz.App.entity.Teacher;
import doston.uz.App.repository.StudentRepository;
import doston.uz.App.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).get();
    }

    public Student addStudent(StudentDTO studentDTO) {

        Teacher teacher = teacherRepository.findById(studentDTO.getTeacherId()).get();

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setLevel(studentDTO.getLevel());
        student.setTeacher(teacher);
        student.setActive(true);

        return studentRepository.save(student);
    }

    public Student endStudentJourney(Integer studentId) {

        Student student = studentRepository.findById(studentId).get();

        student.setEndDate(new Timestamp(System.currentTimeMillis()));
        student.setActive(false);
        return studentRepository.save(student);
    }



    public StudentDTO findStudentById(Integer studentId) {

        Student student = studentRepository.findById(studentId).get();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setLevel(student.getLevel());
        studentDTO.setTeacherId(student.getTeacher().getId());
        return studentDTO;
    }
}
