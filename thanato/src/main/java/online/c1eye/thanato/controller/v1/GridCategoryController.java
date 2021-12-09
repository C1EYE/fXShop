package online.c1eye.thanato.controller.v1;


import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import online.c1eye.thanato.dto.GridCategoryDTO;
import online.c1eye.thanato.model.GridCategoryDO;
import online.c1eye.thanato.service.GridCategoryService;
import online.c1eye.thanato.vo.CreatedVO;
import online.c1eye.thanato.vo.DeletedVO;
import online.c1eye.thanato.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/grid-category")
@Validated
@PermissionModule("六宫格")
public class GridCategoryController {

    @Autowired
    private GridCategoryService gridCategoryService;

    @PostMapping("")
    @PermissionMeta(value = "创建六宫格")
    @GroupRequired
    public CreatedVO create(@Validated @RequestBody GridCategoryDTO dto) {
        gridCategoryService.createGridCategory(dto);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    @PermissionMeta(value = "更新六宫格")
    @GroupRequired
    public UpdatedVO update(
            @Validated @RequestBody GridCategoryDTO dto,
            @PathVariable @Positive(message = "{id.positive}") Integer id) {
        gridCategoryService.updateGridCategory(dto, id);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除六宫格")
    @GroupRequired
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        gridCategoryService.deleteGridCategory(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    @LoginRequired
    public GridCategoryDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        GridCategoryDO gridCategory = gridCategoryService.getById(id);
        if (gridCategory == null) {
            throw new NotFoundException(50000);
        }
        return gridCategory;
    }

    @GetMapping("/list")
    @LoginRequired
    public List<GridCategoryDO> getList() {
        return gridCategoryService.list();
    }
}
