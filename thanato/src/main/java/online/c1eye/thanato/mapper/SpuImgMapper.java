package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.SpuImgDO;
import org.apache.ibatis.annotations.Param;

public interface SpuImgMapper extends BaseMapper<SpuImgDO> {

    /**
     * 物理删除spu轮播图
     * @param spuId Integer
     */
    void hardDeleteImgsBySpuId(@Param("spuId") Integer spuId);

}
