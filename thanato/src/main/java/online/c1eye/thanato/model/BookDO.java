package online.c1eye.thanato.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@TableName("book")
@EqualsAndHashCode(callSuper = true)
public class BookDO extends BaseModel implements Serializable {

    private static final long serialVersionUID = 3531805912578317266L;

    private String title;

    private String author;

    private String summary;

    private String image;
}
