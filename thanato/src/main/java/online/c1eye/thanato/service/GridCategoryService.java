package online.c1eye.thanato.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import online.c1eye.thanato.dto.GridCategoryDTO;
import online.c1eye.thanato.mapper.CategoryMapper;
import online.c1eye.thanato.mapper.GridCategoryMapper;
import online.c1eye.thanato.model.CategoryDO;
import online.c1eye.thanato.model.GridCategoryDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GridCategoryService extends ServiceImpl<GridCategoryMapper, GridCategoryDO> {
    @Value("${sleeve.grid-category-maximum-quantity}")
    private int maximumQuality;

    @Autowired
    private CategoryMapper categoryMapper;

    public void createGridCategory(GridCategoryDTO dto) {
        Integer count = this.getBaseMapper().selectCount(null);
        if (count >= maximumQuality) {
            throw new ForbiddenException(50001);
        }
        CategoryDO category = categoryMapper.selectById(dto.getCategoryId());
        if (category == null) {
            throw new NotFoundException(40000);
        }
        GridCategoryDO gridCategory = new GridCategoryDO();
        BeanUtils.copyProperties(dto, gridCategory);
        this.save(gridCategory);
    }

    public void updateGridCategory(GridCategoryDTO dto, Integer id) {
        GridCategoryDO gridCategory = this.getById(id);
        if (gridCategory == null) {
            throw new NotFoundException(50000);
        }
        CategoryDO category = categoryMapper.selectById(dto.getCategoryId());
        if (category == null) {
            throw new NotFoundException(40000);
        }
        setGridCategoryByCondition(dto, gridCategory, category);
        this.updateById(gridCategory);
    }

    public void deleteGridCategory(Integer id) {
        GridCategoryDO gridCategory = this.getById(id);
        if (gridCategory == null) {
            throw new NotFoundException(50000);
        }
        this.getBaseMapper().deleteById(id);
    }

    private void setGridCategoryByCondition(GridCategoryDTO dto, GridCategoryDO gridCategory, CategoryDO category) {
        // 如果存在 title，赋值 title，否则填充 name
        if (dto.getTitle() == null) {
            gridCategory.setTitle(category.getName());
        } else {
            gridCategory.setTitle(dto.getTitle());
        }
        if (dto.getName() != null) {
            gridCategory.setName(dto.getName());
        } else {
            gridCategory.setName(category.getName());
        }
        gridCategory.setImg(dto.getImg());
        // 如果当前绑定的分类无父分类，则绑定到rootCategoryId
        // 否则绑定父分类绑定到rootCategoryId，当前id绑定到categoryId
        if (category.getParentId() == null) {
            gridCategory.setRootCategoryId(category.getId());
        } else {
            gridCategory.setRootCategoryId(category.getParentId());
            gridCategory.setCategoryId(category.getId());
        }
    }
}
