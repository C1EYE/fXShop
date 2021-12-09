package online.c1eye.thanato.controller.v1;


import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import online.c1eye.thanato.dto.BannerItemDTO;
import online.c1eye.thanato.model.BannerItemDO;
import online.c1eye.thanato.service.BannerItemService;
import online.c1eye.thanato.vo.CreatedVO;
import online.c1eye.thanato.vo.DeletedVO;
import online.c1eye.thanato.vo.UpdatedVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/banner-item")
@PermissionModule("Banner item")
public class BannerItemController {

    @Autowired
    private BannerItemService bannerItemService;

    @PostMapping("")
    @PermissionMeta(value = "创建Banner item")
    public CreatedVO create(@Validated @RequestBody BannerItemDTO dto) {
        BannerItemDO bannerItemDO = new BannerItemDO();
        BeanUtils.copyProperties(dto, bannerItemDO);
        bannerItemService.save(bannerItemDO);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    @PermissionMeta(value = "更新Banner item")
    public UpdatedVO update(
            @PathVariable @Positive(message = "{id.positive}") Integer id,
            @Validated @RequestBody BannerItemDTO dto) {
        bannerItemService.update(dto, id);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除Banner item")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        bannerItemService.delete(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    @PermissionMeta(value = "查询Banner item")
    public BannerItemDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        BannerItemDO bannerItem = bannerItemService.getById(id);
        if (bannerItem == null) {
            throw new NotFoundException(20001);
        }
        return bannerItem;
    }

}
