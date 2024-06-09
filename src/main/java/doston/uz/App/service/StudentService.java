package doston.uz.App.service;

import doston.uz.App.model.Student;
import doston.uz.App.dto.StudentDTO;
import doston.uz.App.model.Teacher;
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
        Student student = studentRepository.findById(studentId).orElseThrow();

        if (student.getEndDate() == null && student.isActive()) {
            student.setEndDate(new Timestamp(System.currentTimeMillis()));
            student.setActive(false);
            return studentRepository.save(student);
        }

        // If the endDate is already set and active is already false, return the student object without any changes
        return student;
    }


    // we have problem with updating student

    public void updateStudent( StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(studentDTO.getId()).orElseThrow();
        existingStudent.setName(studentDTO.getName());
        existingStudent.setSurname(studentDTO.getSurname());
        existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        existingStudent.setLevel(studentDTO.getLevel());

        // Update the teacher if it has changed
        Teacher teacher = teacherRepository.findById(studentDTO.getTeacherId()).orElseThrow();
        existingStudent.setTeacher(teacher);

        studentRepository.save(existingStudent);
    }

    public StudentDTO findStudentById(Integer studentId) {

        Student student = studentRepository.findById(studentId).get();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setLevel(student.getLevel());
        studentDTO.setTeacherId(student.getTeacher().getId());
        return studentDTO;
    }
}
