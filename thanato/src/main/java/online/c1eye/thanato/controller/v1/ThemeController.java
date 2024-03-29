package online.c1eye.thanato.controller.v1;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import online.c1eye.thanato.common.util.PageUtil;
import online.c1eye.thanato.dto.ThemeDTO;
import online.c1eye.thanato.dto.ThemeSpuDTO;
import online.c1eye.thanato.model.SimplifySpuDO;
import online.c1eye.thanato.model.SpuDO;
import online.c1eye.thanato.model.ThemeDO;
import online.c1eye.thanato.service.ThemeService;
import online.c1eye.thanato.vo.CreatedVO;
import online.c1eye.thanato.vo.DeletedVO;
import online.c1eye.thanato.vo.PageResponseVO;
import online.c1eye.thanato.vo.UpdatedVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/theme")
@Validated
@PermissionModule("主题")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @PostMapping("")
    @PermissionMeta("创建主题")
    @GroupRequired
    public CreatedVO create(@Validated @RequestBody ThemeDTO dto) {
        ThemeDO theme = new ThemeDO();
        BeanUtils.copyProperties(dto, theme);
        themeService.save(theme);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    @PermissionMeta("更新主题")
    @GroupRequired
    public UpdatedVO update(
            @Validated @RequestBody ThemeDTO dto,
            @PathVariable @Positive(message = "{id.positive}") Integer id) {
        ThemeDO theme = themeService.getById(id);
        if (theme == null) {
            throw new NotFoundException(30000);
        }
        BeanUtils.copyProperties(dto, theme);
        themeService.updateById(theme);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @PermissionMeta("删除主题")
    @GroupRequired
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        ThemeDO theme = themeService.getById(id);
        if (theme == null) {
            throw new NotFoundException(30000);
        }
        themeService.getBaseMapper().deleteById(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    @LoginRequired
    public ThemeDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        ThemeDO theme = themeService.getById(id);
        if (theme == null) {
            throw new NotFoundException(30000);
        }
        return theme;
    }

    @GetMapping("/page")
    @LoginRequired
    public PageResponseVO<ThemeDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page
                                       ) {
        Page<ThemeDO> pager = new Page<>(page, count);
        IPage<ThemeDO> paging = themeService.getBaseMapper().selectPage(pager, null);
        return PageUtil.build(paging);
    }

    /**
     * 选择 theme/spus?id=1 作为 规则
     * 而没有选择 theme/1/spus 作为路由规则的主要原因是
     * theme下的spus以后可能会通过其它的属性进行筛选，例如 name
     */
    @GetMapping("/spus")
    @LoginRequired
    public List<SimplifySpuDO> getSpus(@RequestParam(name = "id") @Positive(message = "{id}") Integer id) {
        return themeService.getSpus(id);
    }

    @GetMapping("/spu/list")
    @LoginRequired
    public List<SpuDO> getSpuList(@RequestParam(name = "id") @Positive(message = "{id}") Integer id) {
        return themeService.getSimplifySpus(id);
    }

    @PostMapping("/spu")
    @PermissionMeta("创建专题下的spu")
    @GroupRequired
    public CreatedVO addThemeSpu(@RequestBody @Validated ThemeSpuDTO dto) {
        themeService.addThemeSpu(dto);
        return new CreatedVO();
    }

    @DeleteMapping("/spu/{id}")
    @PermissionMeta("删除专题下的spu")
    @GroupRequired
    public DeletedVO deleteThemeSpu(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        themeService.deleteThemeSpu(id);
        return new DeletedVO();
    }

}
