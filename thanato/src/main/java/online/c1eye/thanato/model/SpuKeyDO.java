package online.c1eye.thanato.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("spu_key")
public class SpuKeyDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer spuId;

    private Integer specKeyId;


}
