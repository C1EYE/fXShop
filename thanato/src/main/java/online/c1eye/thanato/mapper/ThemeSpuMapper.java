package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.SpuDO;
import online.c1eye.thanato.model.ThemeSpuDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeSpuMapper extends BaseMapper<ThemeSpuDO> {

    /**
     * 获取指定专题下可选spu列表
     * @param id 专题id
     * @return SpuDO
     */
    List<SpuDO> getSimplifySpus(Integer id);

}
