package online.c1eye.thanato.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import online.c1eye.thanato.dto.BannerItemDTO;
import online.c1eye.thanato.mapper.BannerItemMapper;
import online.c1eye.thanato.model.BannerItemDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BannerItemService extends ServiceImpl<BannerItemMapper, BannerItemDO> {

    public void delete(Integer id) {
        BannerItemDO bannerItem = this.getById(id);
        if (bannerItem == null) {
            throw new NotFoundException(20001);
        }
        this.getBaseMapper().deleteById(id);
    }

    public void update(BannerItemDTO dto, Integer id) {
        BannerItemDO bannerItemDO = this.getById(id);
        if (bannerItemDO == null) {
            throw new NotFoundException(20001);
        }
        BeanUtils.copyProperties(dto, bannerItemDO);
        this.updateById(bannerItemDO);
    }
}
