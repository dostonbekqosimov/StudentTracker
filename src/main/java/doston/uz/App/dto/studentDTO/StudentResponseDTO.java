package doston.uz.App.dto.studentDTO;

import doston.uz.App.model.Student;
import doston.uz.App.model.enums.Level;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    // keyinchalik agar finish bosilsa u student ni alohida ro'yhatga qo'shib, uni qancha o'qiganini ham chiqarib berishimiz kerak(optional)


    private Integer id;

    private String name;

    private String surname;

    private String phoneNumber;

    private Level level;

    private String  teacherName;

    private Integer groupId;

    private Timestamp startDate;

    private Timestamp endDate;

    private boolean active;



}
