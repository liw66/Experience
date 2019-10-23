<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head th:include="include :: header"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="ibox ibox-body">
        <div class="fixed-table-toolbar">
            <div class="columns pull-left">
                <button shiro:hasPermission="app:${lowerName}:add" type="button" class="btn  btn-primary"
                        onclick="edit(0)">
                    <i class="fa fa-plus" aria-hidden="true"></i>添加
                </button>
                <button shiro:hasPermission="app:${lowerName}:batchRemove" type="button" class="btn  btn-danger"
                        onclick="batchRemove()">
                    <i class="fa fa-trash" aria-hidden="true"></i>删除
                </button>
            </div>
            <div class="columns pull-right">
                <button shiro:hasPermission="app:${lowerName}:${lowerName}" type="button" class="btn btn-info"
                        onclick="reLoad()">
                    <i class="fa fa-search"></i> 查询
                </button>
            </div>
            <div class="columns pull-right col-md-2 nopadding">
                <input id="searchName" type="text" class="form-control"
                       placeholder="">
            </div>
        </div>
        <table id="bootstrap-table" data-mobile-responsive="true">
        </table>
    </div>
</div>
<div>
    <script type="text/javascript">
        var s_edit_h = 'hidden';
        var s_remove_h = 'hidden';
    </script>
</div>
<div shiro:hasPermission="app:${lowerName}:edit">
    <script type="text/javascript">
        s_edit_h = '';
    </script>
</div>
<div shiro:hasPermission="app:${lowerName}:remove">
    <script type="text/javascript">
        var s_remove_h = '';
    </script>
</div>
</div>
<div th:include="include :: footer"></div>
<script src="/js/common/moment.js"></script>
<script src="/js/modules/app/${lowerName}/index.js"></script>
</body>
</html>