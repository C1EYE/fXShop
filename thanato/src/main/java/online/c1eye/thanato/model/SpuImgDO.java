package online.c1eye.thanato.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("spu_img")
public class SpuImgDO extends BaseModel {


    private String img;

    private Integer spuId;


}
