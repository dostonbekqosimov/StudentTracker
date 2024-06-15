package doston.uz.App.dto.groupDTO;

import doston.uz.App.model.Teacher;
import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import lombok.Data;

@Data
public class GroupForm {

    private String infoAboutGroup;
    private Integer teacherId;
    private Level level;
    private LessonTime lessonTime;



}
