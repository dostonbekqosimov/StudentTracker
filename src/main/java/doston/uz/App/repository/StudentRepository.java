package doston.uz.App.repository;

import doston.uz.App.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.group")
    List<Student> findAllWithGroup();

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.group WHERE s.teacher.id = :teacherId")
    List<Student> findAllByTeacherIdWithGroup(@Param("teacherId") Integer teacherId);

    List<Student> findAllByTeacherId(Integer teacherId);

    Integer countByGroupId(Integer id);

    List<Student> findAllByGroupId(Integer groupId);
}
