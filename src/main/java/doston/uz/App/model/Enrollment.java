package doston.uz.App.model;

import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "lesson_time")
    @Enumerated(EnumType.STRING)
    private LessonTime lessonTime;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "coming_date")
    private LocalDate comingDate;

    @Column(name = "active")
    private boolean active;



}
