package doston.uz.App.model;

import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "info_about_group")
    private String infoAboutGroup;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "lesson_time")
    @Enumerated(EnumType.STRING)
    private LessonTime lessonTime;


    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "update_date")
    private Timestamp updateDate;


    @Column(name = "end_date")
    private Timestamp endDate;


    @Column(name = "active")
    private boolean active;

    @PrePersist
    protected void onCreate() {
        this.startDate = new Timestamp(System.currentTimeMillis());
    }


}
