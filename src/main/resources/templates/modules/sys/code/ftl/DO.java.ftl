package com.li.experience.modules.app.domain;

import java.io.Serializable;
import java.util.Date;
import java.math.BigInteger;

/**
 * ${tableComment}
 *
 * @author Liwei
 * @email liw66@163.com
 * @date ${createTime}
 */
public class ${className}DO implements Serializable {
    private static final long serialVersionUID = 1L;

    <#list columns as c>

    //${c.columnComment}
    private ${c.dataType} ${c.columnName};
    </#list>

    <#list columns as c>
    <#assign extra="${c.extra}">

    /**
     * 获取${c.columnComment}
     */
    public ${c.dataType} ${r'get'+extra}() {
        return ${c.columnName};
    }

    /**
     * 设置${c.columnComment}
     */
    public void ${r'set'+extra}(${c.dataType} ${c.columnName}) {
        this.${c.columnName} = ${c.columnName};
    }
    </#list>

}