package online.c1eye.thanato.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class SkuSelector {

    @Positive
    @NotNull
    private Integer keyId;

    @Positive
    @NotNull
    private Integer valueId;

}
