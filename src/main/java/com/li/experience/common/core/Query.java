package com.li.experience.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-05-17 17:43
 **/
public class Query extends HashMap {
    public Query(Map map){
        super(map);
        if (this.get("limit") != null){
            this.put("limit",Integer.parseInt(this.get("limit").toString()));
        }
        if (this.get("offset") != null){
            this.put("offset",Integer.parseInt(this.get("offset").toString()));
        }
    }
}
