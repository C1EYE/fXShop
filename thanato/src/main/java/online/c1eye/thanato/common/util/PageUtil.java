package online.c1eye.thanato.common.util;


import com.baomidou.mybatisplus.core.metadata.IPage;
import online.c1eye.thanato.vo.PageResponseVO;

import java.util.List;

public class PageUtil {

    public static <T> PageResponseVO<T> build(IPage<T> iPage) {
        return new PageResponseVO<>(iPage.getTotal(), iPage.getRecords(), iPage.getCurrent(), iPage.getSize());
    }

    public static <K, T> PageResponseVO<K> build(IPage<T> iPage, List<K> records) {
        return new PageResponseVO<>(iPage.getTotal(), records, iPage.getCurrent(), iPage.getSize());
    }

}
