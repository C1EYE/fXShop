package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.SpuDetailImgDO;
import org.apache.ibatis.annotations.Param;

public interface SpuDetailImgMapper extends BaseMapper<SpuDetailImgDO> {

    /**
     * 物理删除spu详情图
     * @param spuId Integer
     */
    void hardDeleteImgsBySpuId(@Param("spuId") Integer spuId);

}
