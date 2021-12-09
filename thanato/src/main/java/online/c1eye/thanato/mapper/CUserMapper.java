package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import online.c1eye.thanato.model.CUserDO;

public interface CUserMapper extends BaseMapper<CUserDO> {

    /**
     * 模糊查询C端用户
     * @param pager   分页对象
     * @param keyword 关键字
     * @return IPage
     */
    IPage<CUserDO> searchCUserByKeyword(Page<CUserDO> pager, String keyword);

}
