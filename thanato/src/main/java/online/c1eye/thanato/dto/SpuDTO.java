package online.c1eye.thanato.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class SpuDTO {

    @NotBlank
    @Length(min = 1, max = 128)
    private String title;

    @Length(min = 1, max = 255)
    private String subtitle;

    @Length(min = 1, max = 255)
    private String img;

    @Length(min = 1, max = 255)
    private String forThemeImg;

    @Positive
    @NotNull
    private Integer categoryId;

    @Max(1)
    @Min(0)
    private Integer online;

    @Positive
    private Integer sketchSpecId;

    @Positive
    private Integer defaultSkuId;

    @NotBlank
    @Length(min = 1, max = 20)
    private String price;

    @Length(min = 1, max = 20)
    private String discountPrice;

    @Length(min = 1, max = 255)
    private String description;

    @Length(min = 1, max = 255)
    private String tags;

    private List<Integer> specKeyIdList;

    private List<String> spuImgList;

    private List<String> spuDetailImgList;

}
