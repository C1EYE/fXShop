package online.c1eye.thanato.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import online.c1eye.thanato.bo.GroupPermissionBO;
import online.c1eye.thanato.common.enumeration.GroupLevelEnum;
import online.c1eye.thanato.mapper.GroupMapper;
import online.c1eye.thanato.mapper.UserGroupMapper;
import online.c1eye.thanato.model.GroupDO;
import online.c1eye.thanato.model.PermissionDO;
import online.c1eye.thanato.model.UserGroupDO;
import online.c1eye.thanato.service.GroupService;
import online.c1eye.thanato.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public List<GroupDO> getUserGroupsByUserId(Integer userId) {
        return this.baseMapper.selectUserGroups(userId);
    }

    @Override
    public List<Integer> getUserGroupIdsByUserId(Integer userId) {
        return this.baseMapper.selectUserGroupIds(userId);
    }

    @Override
    public IPage<GroupDO> getGroupPage(int page, int count) {
        Page pager = new Page(page, count);
        return this.baseMapper.selectPage(pager, null);
    }

    @Override
    public boolean checkGroupExistById(Integer id) {
        return this.baseMapper.selectCountById(id) > 0;
    }

    @Override
    public GroupPermissionBO getGroupAndPermissions(Integer id) {
        GroupDO group = this.baseMapper.selectById(id);
        List<PermissionDO> permissions = permissionService.getPermissionByGroupId(id);
        return new GroupPermissionBO(group, permissions);
    }

    @Override
    public boolean checkGroupExistByName(String name) {
        QueryWrapper<GroupDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(GroupDO::getName, name);
        return this.baseMapper.selectCount(wrapper) > 0;
    }

    @Override
    public boolean checkIsRootByUserId(Integer userId) {
        QueryWrapper<UserGroupDO> wrapper = new QueryWrapper<>();
        Integer rootGroupId = this.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        wrapper.lambda().eq(UserGroupDO::getUserId, userId)
                .eq(UserGroupDO::getGroupId, rootGroupId);
        UserGroupDO relation = userGroupMapper.selectOne(wrapper);
        return relation != null;
    }

    @Override
    public boolean deleteUserGroupRelations(Integer userId, List<Integer> deleteIds) {
        if (deleteIds == null || deleteIds.isEmpty()) {
            return true;
        }
        if (checkIsRootByUserId(userId)) {
            throw new ForbiddenException("can't modify the root user's group", 10078);
        }
        QueryWrapper<UserGroupDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(UserGroupDO::getUserId, userId)
                .in(UserGroupDO::getGroupId, deleteIds);
        return userGroupMapper.delete(wrapper) > 0;
    }

    @Override
    public boolean addUserGroupRelations(Integer userId, List<Integer> addIds) {
        if (addIds == null || addIds.isEmpty()) {
            return true;
        }
        boolean ok = checkGroupExistByIds(addIds);
        if (!ok) {
            throw new ForbiddenException("cant't add user to non-existent group", 10077);
        }
        List<UserGroupDO> relations = addIds.stream().map(it -> new UserGroupDO(userId, it)).collect(Collectors.toList());
        return userGroupMapper.insertBatch(relations) > 0;
    }

    @Override
    public List<Integer> getGroupUserIds(Integer id) {
        QueryWrapper<UserGroupDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserGroupDO::getGroupId, id);
        List<UserGroupDO> list = userGroupMapper.selectList(wrapper);
        return list.stream().map(UserGroupDO::getUserId).collect(Collectors.toList());
    }

    @Override
    public GroupDO getParticularGroupByLevel(GroupLevelEnum level) {
        if (GroupLevelEnum.USER.getValue().equals(level.getValue())) {
            return null;
        } else {
            QueryWrapper<GroupDO> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(GroupDO::getLevel, level);
            GroupDO groupDO = this.baseMapper.selectOne(wrapper);
            return groupDO;
        }
    }

    @Override
    public Integer getParticularGroupIdByLevel(GroupLevelEnum level) {
        GroupDO group = this.getParticularGroupByLevel(level);
        return group == null ? 0 : group.getId();
    }

    private boolean checkGroupExistByIds(List<Integer> ids) {
        return ids.stream().allMatch(this::checkGroupExistById);
    }
}
