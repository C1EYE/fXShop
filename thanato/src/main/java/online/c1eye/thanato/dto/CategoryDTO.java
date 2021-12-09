package online.c1eye.thanato.dto;


import io.github.talelin.autoconfigure.validator.Enum;
import lombok.Data;
import online.c1eye.thanato.common.enumeration.CategoryRootOrNotEnum;
import online.c1eye.thanato.common.enumeration.OnlineOrNotEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CategoryDTO {
    @NotBlank
    @Length(min = 1, max = 100)
    private String name;

    @Length(min = 1, max = 255)
    private String description;

    @Enum(allowNull = true, target = CategoryRootOrNotEnum.class)
    private Integer isRoot;

    @Positive
    private Integer parentId;

    @Length(min = 1, max = 255)
    private String img;

    @Positive
    private Integer index;

    @Enum(allowNull = true, target = OnlineOrNotEnum.class)
    private Integer online;

    @Positive
    private Integer level;

}
