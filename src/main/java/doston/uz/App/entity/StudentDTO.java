package doston.uz.App.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class StudentDTO {

    private Integer id;
    @NotEmpty
    private String name;

    private String surname;

    private String phoneNumber;

    @NotNull
    private Level level;

    @NotNull
    private Integer teacherId;


}
