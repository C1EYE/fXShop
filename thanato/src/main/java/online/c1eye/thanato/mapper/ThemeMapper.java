package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.SimplifySpuDO;
import online.c1eye.thanato.model.ThemeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThemeMapper extends BaseMapper<ThemeDO> {

    /**
     * 获取主题下的spu
     * @param id 主题id
     * @return spu列表
     */
    List<SimplifySpuDO> getSpus(@Param("id") Integer id);

}
