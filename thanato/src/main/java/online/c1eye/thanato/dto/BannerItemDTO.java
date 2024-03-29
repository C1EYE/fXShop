package online.c1eye.thanato.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class BannerItemDTO {

    @NotBlank
    @Length(min = 1, max = 255)
    private String img;

    @Length(min = 1, max = 255)
    private String name;

    @NotNull
    @Positive
    private Integer type;

    @NotNull
    @Positive
    private Integer bannerId;

    @Length(min = 1, max = 50)
    private String keyword;

}
