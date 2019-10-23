<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head th:include="include :: header"></head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <form class="layui-form" id="layuiForm">
        <input id="${pk}" name="${pk}" th:value="${r'${'+lowerName+'.'+pk+'}'}" type="hidden">
        <#list columns as c><#if c.columnName != '${pk}'><#if c.columnName != 'state'><#assign columnName="${c.columnName}">
        <div class="layui-form-item">
            <label class="layui-form-label">${c.columnComment}：</label>
            <div class="layui-input-block">
                <input id="${c.columnName}" name="${c.columnName}" th:value="${r'${'+lowerName+'.'+columnName+'}'}" class="layui-input" type="text">
            </div>
        </div>
        <#else>
        <div class="layui-form-item">
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-block">
                <input type="checkbox" name="switch" lay-skin="switch" lay-filter="switch" lay-text="启用|禁用"
                       th:checked="${r'${'+lowerName+'.state==1}'}">
                <input id="state" name="state" th:value="${r'${'+lowerName+'.state}'}" class="layui-input hidden"
                       type="text">
            </div>
        </div>
        </#if>
        </#if>
        </#list>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit>立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<div th:include="include::footer"></div>
<script src="/js/modules/app/${lowerName}/form.js"></script>
</body>
</html>
