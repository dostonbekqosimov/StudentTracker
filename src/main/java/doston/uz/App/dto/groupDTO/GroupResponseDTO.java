package doston.uz.App.dto.groupDTO;


import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class GroupResponseDTO {

    private Integer id;
    private String infoAboutGroup;
    private Integer teacherId;
    private Level level;
    private LessonTime lessonTime;
    private Timestamp startDate;
    // keyinchalik end data va update date larni qo'shish kerak
    private Integer studentCount;
    private boolean active;


}
