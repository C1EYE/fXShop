package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.FileDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper extends BaseMapper<FileDO> {

    FileDO selectByMd5(@Param("md5") String md5);

    int selectCountByMd5(@Param("md5") String md5);
}
