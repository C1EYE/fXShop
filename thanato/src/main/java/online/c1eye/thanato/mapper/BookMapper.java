package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.BookDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper extends BaseMapper<BookDO> {

    List<BookDO> selectByTitleLikeKeyword(@Param("q") String q);

    List<BookDO> selectByTitle(@Param("title") String title);
}
