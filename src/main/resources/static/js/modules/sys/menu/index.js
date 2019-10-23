var prefix = "/sys/menu";
$(function () {
    layui.use('layer', function () {
        var layer = layui.layer;
    });
    load();
});

function load() {
    $('#bootstrap-table').bootstrapTreeTable(
            {
                id : 'menuid',
                code : 'menuid',
                parentCode : 'parentid',
                type : "GET", // 请求数据的ajax类型
                url : prefix + '/list', // 请求数据的ajax的url
                ajaxParams : {}, // 请求数据的ajax的data属性
                expandColumn : '1', // 在哪一列上面显示展开按钮
                striped : true, // 是否各行渐变色
                bordered : true, // 是否显示边框
                expandAll : false, // 是否全部展开
                columns : [
                    {
                        title : '编号',
                        field : 'menuid',
                        visible : false,
                        align : 'center',
                        valign : 'center',
                        width : '50px',
                        checkbox : true
                    },
                    {
                        field : 'menuname',
                        title : '菜单名称',
                        valign : 'center',
                    },
                    {
                        field : 'icon',
                        title : '菜单图标',
                        align : 'center',
                        valign : 'center',
                        width :'100px',
                        formatter : function(item, index) {
                            return item.icon == null ? ''
                                : '<i class="' + item.icon
                                + ' fa-lg"></i>';
                        }
                    },
                    {
                        field : 'type',
                        title : '菜单类型',
                        align : 'center',
                        valign : 'center',
                        width :'100px',
                        formatter : function(item, index) {
                            if (item.type == 0) {
                                return '<span class="label label-primary">目录</span>';
                            } else if (item.type == 1) {
                                return '<span class="label label-success">菜单</span>';
                            } else if (item.type == 2) {
                                return '<span class="label label-warning">按钮</span>';
                            }
                        }
                    },
                    {
                        field : 'url',
                        title : '菜单地址',
                        valign : 'center',
                        width :'20%'
                    },
                    {
                        field : 'perms',
                        title : '权限标志',
                        valign : 'center',
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        valign : 'center',
                        width : '15%',
                        formatter : function(item, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '"  mce_ title="编辑" onclick="edit(\''
                                + item.menuid
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '"  title="增加下級"  mce_ onclick="edit(\''
                                + item.menuid+'$2'
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '"  title="删除"  mce_ onclick="remove(\''
                                + item.menuid
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + a + d;
                        }
                    } ]
            });
}

function reLoad() {
    load();
}
function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['820px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'menuid': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

