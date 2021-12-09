package online.c1eye.thanato.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import online.c1eye.thanato.dto.SpecValueDTO;
import online.c1eye.thanato.mapper.SkuMapper;
import online.c1eye.thanato.mapper.SkuSpecMapper;
import online.c1eye.thanato.mapper.SpecValueMapper;
import online.c1eye.thanato.model.SkuDO;
import online.c1eye.thanato.model.SkuSpecDO;
import online.c1eye.thanato.model.SpecKeyValueDO;
import online.c1eye.thanato.model.SpecValueDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecValueService extends ServiceImpl<SpecValueMapper, SpecValueDO> {

    @Autowired
    private SkuSpecMapper skuSpecMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SpecValueMapper specValueMapper;

    public void create(SpecValueDTO dto) {
        SpecValueDO specValueDO = new SpecValueDO();
        BeanUtils.copyProperties(dto, specValueDO);
        this.save(specValueDO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SpecValueDTO dto, Integer id) {
        SpecValueDO specValue = this.getById(id);
        if (specValue == null) {
            throw new NotFoundException(60002);
        }
        BeanUtils.copyProperties(dto, specValue);
        this.updateById(specValue);

        List<Integer> skuIds = skuSpecMapper.getSkuIdsByValueId(id);
        if (skuIds.isEmpty()) {
            return;
        }
        List<SkuDO> skuList = skuMapper.selectBatchIds(skuIds);
        skuList.forEach(sku -> {
            QueryWrapper<SkuSpecDO> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(SkuSpecDO::getValueId, id);
            wrapper.lambda().eq(SkuSpecDO::getSkuId, sku.getId());
            List<SkuSpecDO> skuSpecs = skuSpecMapper.selectList(wrapper);
            List<SpecKeyValueDO> specs = new ArrayList<>();
            skuSpecs.forEach(skuSpec -> {
                SpecKeyValueDO specKeyAndValue = specValueMapper.getSpecKeyAndValueById(skuSpec.getKeyId(), skuSpec.getValueId());
                specs.add(specKeyAndValue);
            });
            sku.setSpecs(specs);
            skuMapper.updateById(sku);
        });
    }

    public void delete(Integer id) {
        SpecValueDO specValue = this.getById(id);
        if (specValue == null) {
            throw new NotFoundException(60002);
        }
        this.getBaseMapper().deleteById(id);
    }

}
