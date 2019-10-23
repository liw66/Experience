var prefix = "/sys/online";
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
                pagination: false, // 设置为true会在底部显示分页条
                striped : true, // 设置为true会有隔行变色效果
                columns: [
                    {
                        field: 'sessionId',
                        title: '序号'
                    },
                    {
                        field: 'userName',
                        title: '用户名称'
                    },
                    {
                        field: 'host',
                        title: '主机'
                    },
                    {
                        field: 'startAccessTime',
                        title: '登录时间',
                        formatter: function (value, row, index) {
                            return value == null?"":moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        field: 'lastAccessTime',
                        title: '访问时间',
                        formatter: function (value, row, index) {
                            return value == null?"":moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '"  title="用户下线"  mce_ onclick="remove(\''
                                + row.sessionId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return d;
                        }
                    }]
            });
}

function reLoad() {
    $('#bootstrap-table').bootstrapTable('refresh');
}

function remove(id) {
    layer.confirm('确定要下线选中的用户？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'sessionId': id
            },
            success: function () {
                layer.msg("下线成功");
            }
        });
    })
}
