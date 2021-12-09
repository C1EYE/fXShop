package online.c1eye.thanato.dto;


import lombok.Data;
import io.github.talelin.autoconfigure.validator.Enum;
import online.c1eye.thanato.common.enumeration.StandardOrNotEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
@Data
public class SpecKeyDTO {

    @NotBlank
    @Length(min = 1, max = 100)
    private String name;

    @Length(min = 1, max = 255)
    private String description;

    @Length(min = 1, max = 30)
    private String unit;

    @Enum(target = StandardOrNotEnum.class, allowNull = true)
    private Integer standard;

}
