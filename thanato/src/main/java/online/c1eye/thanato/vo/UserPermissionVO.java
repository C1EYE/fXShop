package online.c1eye.thanato.vo;


import lombok.Data;
import online.c1eye.thanato.model.UserDO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

@Data
public class UserPermissionVO {

    private Long id;

    private String nickname;

    private String avatar;

    private Boolean admin;

    private String email;

    private List<Map<String, List<Map<String, String>>>> permissions;

    public UserPermissionVO() {
    }

    public UserPermissionVO(UserDO userDO, List<Map<String, List<Map<String, String>>>> permissions) {
        BeanUtils.copyProperties(userDO, this);
        this.permissions = permissions;
    }

    public UserPermissionVO(UserDO userDO) {
        BeanUtils.copyProperties(userDO, this);
    }
}
