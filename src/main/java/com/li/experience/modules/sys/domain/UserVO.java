package com.li.experience.modules.sys.domain;

import java.util.List;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-05 16:16
 **/
public class UserVO extends UserDO {

    private List<Long> roleids;

    public List<Long> getRoleids() {
        return roleids;
    }

    public void setRoleids(List<Long> roleids) {
        this.roleids = roleids;
    }
}
