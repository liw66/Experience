package com.li.experience.modules.sys.domain;

import io.swagger.models.auth.In;

import java.util.List;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-02 10:12
 **/
public class RoleVO extends RoleDO {

    //角色拥有菜单
    private List<Long> menuids;

    public List<Long> getMenuids() {
        return menuids;
    }

    public void setMenuids(List<Long> menuids) {
        this.menuids = menuids;
    }
}
