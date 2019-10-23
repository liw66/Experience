<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.li.experience.modules.app.dao.${className}Dao">

    <sql id="base_column_list">
        <#list columns as c>`${c.columnName}`<#if c_has_next>,</#if></#list>
	</sql>

    <sql id="base_where_list">
        <#list columns as c><#assign columnName="${c.columnName}">
        <if test="${c.columnName} != null and ${c.columnName} != ''">and ${c.columnName} = ${r'#{'+columnName+'}'}</if>
        </#list>
    </sql>

    <select id="get" resultType="com.li.experience.modules.app.domain.${className}DO">
        select
        <include refid="base_column_list"></include>
        from ${tableName} where ${pk} = ${r'#{value}'}
    </select>

    <select id="list" resultType="com.li.experience.modules.app.domain.${className}DO">
        select
        <include refid="base_column_list"></include>
        from ${tableName}
        <where>
            <include refid="base_where_list"></include>
        </where>
        <choose>
            <when test="sort != null and sort.trim() != ''">
                order by ${r'${sort}'} ${r'${order}'}
            </when>
            <otherwise>
                order by createdat desc
            </otherwise>
        </choose>
    </select>

    <select id="count" resultType="int">
        select count(*) from ${tableName}
        <where>
            <include refid="base_where_list"></include>
        </where>
    </select>

    <insert id="save" parameterType="com.li.experience.modules.app.domain.${className}DO" useGeneratedKeys="true"
            keyProperty="${pk}">
		insert into ${tableName}
        (<#list columns as c><#if c.columnName != pk>`${c.columnName}`<#if c_has_next>,</#if></#if></#list>)
		values
        (<#list columns as c><#assign columnName="${c.columnName}"><#if c.columnName != pk>${r'#{'+columnName+'}'}<#if c_has_next>,</#if></#if></#list>)
	</insert>

    <update id="update" parameterType="com.li.experience.modules.app.domain.${className}DO">
        update ${tableName}
        <set>
            <#list columns as c>
            <#if c.columnName != pk><#assign columnName="${c.columnName}"><if test="${c.columnName} != null">`${c.columnName}` = ${r'#{'+columnName+'}'}<#if c_has_next>,</#if></if></#if>
            </#list>
        </set>
        where ${pk} = ${r'#{'+pk+'}'}
    </update>

    <delete id="remove">
		delete from ${tableName} where ${pk} = ${r'#{value}'}
	</delete>

    <delete id="batchRemove">
        delete from ${tableName} where ${pk} in
        <foreach item="${pk}" collection="array" open="(" separator="," close=")">
            ${r'#{'+pk+'}'}
        </foreach>
    </delete>

</mapper>