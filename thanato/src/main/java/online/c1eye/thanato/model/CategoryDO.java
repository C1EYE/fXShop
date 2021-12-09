package online.c1eye.thanato.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;


@Data
@TableName("category")
public class CategoryDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private Integer isRoot;

    private Integer parentId;

    private String img;

    @TableField(value = "`index`" )
    private Integer index;

    private Integer online;

    private Integer level;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    @TableLogic
    @JsonIgnore
    private Date deleteTime;


}
