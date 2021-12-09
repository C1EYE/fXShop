package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.SpecKeyDO;

import java.util.List;

public interface SpecKeyMapper extends BaseMapper<SpecKeyDO> {

    /**
     * 根据spuId获取规格键
     * @param spuId spuId
     * @return List
     */
    List<SpecKeyDO> getBySpuId(Integer spuId);

}
