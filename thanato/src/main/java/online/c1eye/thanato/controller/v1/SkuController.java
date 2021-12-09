package online.c1eye.thanato.controller.v1;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import online.c1eye.thanato.common.util.PageUtil;
import online.c1eye.thanato.dto.SkuDTO;
import online.c1eye.thanato.model.SkuDO;
import online.c1eye.thanato.model.SkuDetailDO;
import online.c1eye.thanato.service.SkuService;
import online.c1eye.thanato.service.SkuSpecService;
import online.c1eye.thanato.vo.CreatedVO;
import online.c1eye.thanato.vo.DeletedVO;
import online.c1eye.thanato.vo.PageResponseVO;
import online.c1eye.thanato.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/sku")
@Validated
@PermissionModule("SKU")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @Autowired
    private SkuSpecService skuSpecService;

    @PostMapping("")
    @PermissionMeta("创建SKU")
    @GroupRequired
    public CreatedVO create(@RequestBody @Validated SkuDTO dto) {
        skuService.create(dto);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    @PermissionMeta("更新SKU")
    @GroupRequired
    public UpdatedVO update(@RequestBody @Validated SkuDTO dto,
                            @PathVariable @Positive(message = "{id.positive}") Integer id) {
        skuService.update(dto, id);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @PermissionMeta("删除SKU")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        skuService.delete(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}/detail")
    @LoginRequired
    public SkuDetailDO getDetail(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        return skuService.getBaseMapper().getDetail(id);
    }

    @GetMapping("/by/spu/{id}")
    @LoginRequired
    public List<SkuDO> getBySpuId(@PathVariable(value = "id") @Positive Integer spuId) {
        return this.skuService.lambdaQuery().eq(SkuDO::getSpuId, spuId).list();
    }

    @GetMapping("/page")
    @LoginRequired
    public PageResponseVO<SkuDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page
                                     ) {
        Page<SkuDO> pager = new Page<>(page, count);
        IPage<SkuDO> paging = skuService.getBaseMapper().selectPage(pager, null);
        return PageUtil.build(paging);
    }

    @GetMapping("/spec-value-id")
    @LoginRequired
    public Map<String, Integer> getSpecValueId(
            @RequestParam(name = "key_id", required = false)
            @Positive(message = "{id}") Integer keyId,
            @RequestParam(name = "sku_id", required = false)
            @Positive(message = "{id}") Integer skuId
                                              ) {
        // 在spu下选择 spec_key 后，在相关 sku 在spec_key下选择 spec_value
        Integer specValueId = skuSpecService.getBaseMapper().getSpecValueId(keyId, skuId);
        HashMap<String, Integer> result = new HashMap<>(1);
        result.put("value_id", specValueId);
        return result;
    }

}
