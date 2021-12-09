package online.c1eye.thanato.service;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import online.c1eye.thanato.bo.BannerWithItemsBO;
import online.c1eye.thanato.dto.BannerDTO;
import online.c1eye.thanato.mapper.BannerItemMapper;
import online.c1eye.thanato.mapper.BannerMapper;
import online.c1eye.thanato.model.BannerDO;
import online.c1eye.thanato.model.BannerItemDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService extends ServiceImpl<BannerMapper, BannerDO> {
    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private BannerItemMapper bannerItemMapper;

    public BannerWithItemsBO getWithItems(Integer id) {
        BannerDO banner = this.getById(id);
        if (banner == null) {
            throw new NotFoundException(20000);
        }

//        LambdaQueryWrapper<BannerItemDO> wrapper = new QueryWrapper<BannerItemDO>().lambda();
//        wrapper.eq("banner_id", id);
//        LambdaQueryWrapper<BannerItemDO> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(BannerItemDO::getBannerId, id);
//        List<BannerItemDO> bannerItems = bannerItemMapper.selectList(wrapper);

        List<BannerItemDO> bannerItems =
                new LambdaQueryChainWrapper<>(bannerItemMapper)
                        .eq(BannerItemDO::getBannerId, id)
                        .list();

        return new BannerWithItemsBO(banner, bannerItems);
    }

    public void delete(Integer id) {
        BannerDO banner = this.getById(id);
        if (banner == null) {
            throw new NotFoundException(20000);
        }
        this.getBaseMapper().deleteById(id);
    }

    public void update(BannerDTO dto, Integer id) {
        BannerDO bannerDO = this.getById(id);
        if (bannerDO == null) {
            throw new NotFoundException(20000);
        }
        BeanUtils.copyProperties(dto, bannerDO);
        this.updateById(bannerDO);
    }

}
