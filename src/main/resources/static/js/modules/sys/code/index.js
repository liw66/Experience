var prefix = "/sys/code";
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
                        tableName:$('#searchName').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'tableName',
                        title: '表名称'
                    },
                    {
                        field: 'engine',
                        title: 'engine'
                    },
                    {
                        field: 'tableComment',
                        title: '表描述'
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        formatter: function (value, row, index) {
                            return value == null?"":moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm"  mce_ title="生成代码" onclick="code(\''
                                + row.tableName
                                + '\')"><i class="fa fa-code"></i></a> ';
                            return e;
                        }
                    }]
            });
}

function code(tableName) {
    location.href = prefix + "/code/" + tableName;
}

function batchCode() {
    var rows = $('#bootstrap-table').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    layer.confirm("确认要生成选中的'" + rows.length + "'个表吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['tableName'];
        });
        location.href = prefix + "/batchCode/" + ids;
        layer.closeAll('dialog');
    });
}
