package online.c1eye.thanato.dto;


import io.github.talelin.autoconfigure.validator.Enum;
import lombok.Data;
import online.c1eye.thanato.common.enumeration.CategoryRootOrNotEnum;
import online.c1eye.thanato.common.enumeration.CouponTypeEnum;
import online.c1eye.thanato.common.enumeration.OnlineOrNotEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class CouponTemplateDTO {
    @NotBlank
    @Length(min = 1, max = 100)
    private String title;

    @Length(min = 1, max = 255)
    private String description;

    @DecimalMin(value = "0.00")
    private BigDecimal fullMoney;

    @DecimalMin(value = "0.00")
    private BigDecimal minus;

    /**f
     * 国内多是打折，国外多是百分比 off
     */
    @DecimalMin(value = "0.00")
    private BigDecimal discount;

    /**
     * 1. 满减券 2.折扣券 3.无门槛券 4.满金额折扣券
     */
    @Enum(allowNull = false, target = CouponTypeEnum.class)
    private Integer type;
}
