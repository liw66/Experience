var prefix = "/sys/${lowerName}";
$(function () {
    layui.use('layer', function () {
        var layer = layui.layer;
    });
    load();
});

function load() {
    $('#bootstrap-table').bootstrapTable('destroy');//先销毁表格再加载
    $('#bootstrap-table').bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                pagination: true, // 设置为true会在底部显示分页条
                striped : true, // 设置为true会有隔行变色效果
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        name:$('#searchName').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'state',
                        title: '状态',
                        formatter : function(value,row, index){
                            if (row.state == 0) {
                                return '<span class="ant-tag ant-tag-pink">禁用</span>';
                            } else if (row.state == 1){
                                return '<span class="ant-tag ant-tag-cyan">启用</span>'
                            }
                        }
                    },
                    {
                        field: 'createdat',
                        title: '创建时间',
                        formatter: function (value, row, index) {
                            return value == null?"":moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        field: 'updatedat',
                        title: '更新时间',
                        formatter: function (value, row, index) {
                            return value == null?"":moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" mce_ title="编辑" onclick="edit(\''
                                + row.${pk}
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" mce_ title="删除" onclick="remove(\''
                                + row.${pk}
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}

function reLoad() {
    $('#bootstrap-table').bootstrapTable('refresh');
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
                '${pk}': id
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

function batchRemove() {
    var rows = $('#bootstrap-table').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['${pk}'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}
