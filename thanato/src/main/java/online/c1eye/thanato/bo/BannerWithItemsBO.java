package online.c1eye.thanato.bo;


import lombok.Data;
import lombok.NoArgsConstructor;
import online.c1eye.thanato.model.BannerDO;
import online.c1eye.thanato.model.BannerItemDO;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class BannerWithItemsBO {

    private Integer id;

    private String name;

    private String title;

    private String img;

    private String description;

    List<BannerItemDO> items;

    public BannerWithItemsBO(BannerDO banner, List<BannerItemDO> items) {
        BeanUtils.copyProperties(banner, this);
        this.setItems(items);
    }
}
