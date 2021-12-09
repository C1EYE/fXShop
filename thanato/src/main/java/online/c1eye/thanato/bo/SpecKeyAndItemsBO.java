package online.c1eye.thanato.bo;


import lombok.Data;
import online.c1eye.thanato.model.SpecKeyDO;
import online.c1eye.thanato.model.SpecValueDO;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class SpecKeyAndItemsBO {

    private Integer id;

    private String name;

    private String unit;

    private Integer standard;

    private String description;

    private List<SpecValueDO> items;

    public SpecKeyAndItemsBO(SpecKeyDO specKey, List<SpecValueDO> items) {
        BeanUtils.copyProperties(specKey, this);
        this.setItems(items);
    }

}
