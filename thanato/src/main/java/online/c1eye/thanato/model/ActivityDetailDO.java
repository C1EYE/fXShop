package online.c1eye.thanato.model;

import lombok.Data;

import java.util.List;


@Data
public class ActivityDetailDO extends ActivityDO {

    private List<Integer> couponIds;

}
