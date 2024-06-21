package doston.uz.App.service;

import doston.uz.App.dto.studentDTO.StudentResponseDTO;
import doston.uz.App.model.Group;
import doston.uz.App.model.Student;
import doston.uz.App.dto.studentDTO.StudentDTO;
import doston.uz.App.model.Teacher;
import doston.uz.App.repository.GroupRepository;
import doston.uz.App.repository.StudentRepository;
import doston.uz.App.repository.TeacherRepository;
import doston.uz.App.util.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<StudentResponseDTO> getStudents() {
        List<Student> students = studentRepository.findAllWithGroup();
        List<StudentResponseDTO> studentResponseDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentResponseDTO studentResponseDTO = EntityDTOConverter.convertToStudentResponseDTO(student);
            studentResponseDTOS.add(studentResponseDTO);
        }
        return studentResponseDTOS;
    }

    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).get();
    }

    public void addStudent(StudentDTO studentDTO) {

        Teacher teacher = teacherRepository.findById(studentDTO.getTeacherId()).get();
        Group group = groupRepository.findById(studentDTO.getGroupId()).get();

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setLevel(studentDTO.getLevel());
        student.setTeacher(teacher);
        student.setGroup(group);
        student.setActive(true);

        studentRepository.save(student);
    }

    public void endStudentJourney(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        if (student.getEndDate() == null && student.isActive()) {
            student.setEndDate(new Timestamp(System.currentTimeMillis()));
            student.setActive(false);
            studentRepository.save(student);
        }

        // If the endDate is already set and active is already false, return the student object without any changes
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
        Group group = groupRepository.findById(studentDTO.getGroupId()).orElseThrow();

        existingStudent.setTeacher(teacher);
        existingStudent.setGroup(group);

        studentRepository.save(existingStudent);
    }

    public StudentDTO findStudentById(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setLevel(student.getLevel());
        studentDTO.setTeacherId(student.getTeacher().getId());

        // Get the groupId from the associated Group object
        Group group = student.getGroup();
        if (group != null) {
            studentDTO.setGroupId(group.getId());
        } else {
            studentDTO.setGroupId(null); // or set a default value if desired
        }

        return studentDTO;
    }

    public List<StudentResponseDTO> getStudentsForTeacher(Integer teacherId) {
        List<Student> students = studentRepository.findAllByTeacherIdWithGroup(teacherId);
        List<StudentResponseDTO> studentResponseDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentResponseDTO studentResponseDTO = EntityDTOConverter.convertToStudentResponseDTO(student);
            studentResponseDTOS.add(studentResponseDTO);
        }
        return studentResponseDTOS;
    }

    public List<StudentResponseDTO> getAllStudentWithGroupId(Integer groupId) {

        List<Student> students = studentRepository.findAllByGroupId(groupId);

        List<StudentResponseDTO> studentResponseDTOS = new ArrayList<>();

        for (Student student : students) {
            StudentResponseDTO studentResponseDTO = EntityDTOConverter.convertToStudentResponseDTO(student);
            studentResponseDTOS.add(studentResponseDTO);
        }
        return studentResponseDTOS;


    }
}
