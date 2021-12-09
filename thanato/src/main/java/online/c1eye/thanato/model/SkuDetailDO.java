package online.c1eye.thanato.model;


import lombok.Data;


@Data
public class SkuDetailDO extends SkuDO {

    private Integer spuId;

    private String spuName;

    private String currency;

}
