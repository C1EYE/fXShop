package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.SkuDO;
import online.c1eye.thanato.model.SkuDetailDO;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuMapper extends BaseMapper<SkuDO> {

    /**
     * 根据 skuId 获取 sku 详情
     * @param id skuId
     * @return SkuDetailDO
     */
    SkuDetailDO getDetail(Integer id);

}
