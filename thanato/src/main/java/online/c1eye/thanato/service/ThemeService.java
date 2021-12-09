package online.c1eye.thanato.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.c1eye.thanato.dto.ThemeSpuDTO;
import online.c1eye.thanato.mapper.ThemeMapper;
import online.c1eye.thanato.mapper.ThemeSpuMapper;
import online.c1eye.thanato.model.SimplifySpuDO;
import online.c1eye.thanato.model.SpuDO;
import online.c1eye.thanato.model.ThemeDO;
import online.c1eye.thanato.model.ThemeSpuDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService extends ServiceImpl<ThemeMapper, ThemeDO> {

    @Autowired
    private ThemeSpuMapper themeSpuMapper;

    public List<SimplifySpuDO> getSpus(Integer id) {
        return this.getBaseMapper().getSpus(id);
    }

    public void addThemeSpu(ThemeSpuDTO dto) {
        ThemeSpuDO themeSpu = new ThemeSpuDO();
        themeSpu.setThemeId(dto.getThemeId());
        themeSpu.setSpuId(dto.getSpuId());
        themeSpuMapper.insert(themeSpu);
    }

    public void deleteThemeSpu(Integer id) {
        themeSpuMapper.deleteById(id);
    }

    public List<SpuDO> getSimplifySpus(Integer id) {
        return themeSpuMapper.getSimplifySpus(id);
    }

}
