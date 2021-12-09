package online.c1eye.thanato.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import online.c1eye.thanato.mapper.CUserMapper;
import online.c1eye.thanato.model.CUserDO;
import org.springframework.stereotype.Service;

@Service
public class CUserService extends ServiceImpl<CUserMapper, CUserDO> {

    public IPage<CUserDO> getUserByPage(Integer count, Integer page) {
        Page pager = new Page(page, count);
        IPage<CUserDO> iPage = this.getBaseMapper().selectPage(pager, null);
        return iPage;
    }

    public CUserDO getParsedUserById(Integer id) {
        CUserDO user = this.getBaseMapper().selectById(id);
        if (user == null) {
            throw new NotFoundException(120000);
        }
        return user;
    }

    public IPage<CUserDO> search(Integer page, Integer count, String keyword) {
        Page<CUserDO> pager = new Page<>(page, count);
        keyword = "".equals(keyword) ? null : "%" + keyword + "%";
        IPage<CUserDO> iPage = this.getBaseMapper().searchCUserByKeyword(pager, keyword);
        return iPage;
    }


}
