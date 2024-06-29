package doston.uz.App.repository;

import doston.uz.App.model.Group;
import doston.uz.App.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByTeacherId(Integer teacherId);

    List<Group> findAllByTeacher(Teacher teacher);

    List<Group> findByTeacher(Teacher teacher);
}
