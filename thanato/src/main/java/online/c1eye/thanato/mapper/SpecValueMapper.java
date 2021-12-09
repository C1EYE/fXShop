package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.SpecKeyValueDO;
import online.c1eye.thanato.model.SpecValueDO;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecValueMapper extends BaseMapper<SpecValueDO> {

    /**
     * 根据规格名id和规格值id，获取规格名和规格值
     * @param keyId 规格名id
     * @param valueId 规格值id
     * @return SpecKeyValueDO
     */
    SpecKeyValueDO getSpecKeyAndValueById(Integer keyId, Integer valueId);

}
