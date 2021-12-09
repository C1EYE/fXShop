package online.c1eye.thanato.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("theme_spu")
public class ThemeSpuDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer themeId;

    private Integer spuId;

}
