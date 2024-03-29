package online.c1eye.thanato.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;


@Data
public class CouponDTO extends CouponTemplateDTO {

    @Positive
    @NotNull
    private Integer activityId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @NotNull
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @NotNull
    private Date endTime;

}
