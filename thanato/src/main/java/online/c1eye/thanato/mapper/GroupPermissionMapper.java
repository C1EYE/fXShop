package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.c1eye.thanato.model.GroupPermissionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupPermissionMapper extends BaseMapper<GroupPermissionDO> {

    int insertBatch(@Param("relations") List<GroupPermissionDO> relations);

    int deleteBatchByGroupIdAndPermissionId(@Param("groupId") Integer groupId, @Param("permissionIds") List<Integer> permissionIds);
}
