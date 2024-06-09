package doston.uz.App.dto;

import doston.uz.App.model.Level;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


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
