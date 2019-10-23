var prefix = "/sys/user";
$(function () {
    layui.use('layer', function () {
        var layer = layui.layer;
    });
    getTreeData();
    load('');
});
function getTreeData() {
    $.ajax({
        type : "GET",
        url : "/sys/dept/getTree",
        success : function(tree) {
            loadTree(tree);
        }
    });
}
function loadTree(tree) {
    $('#deptTree').jstree({
        'core' : {
            'data' : tree.data
        }
    });
}
$('#deptTree').on("changed.jstree", function(e, data) {
    load(data.selected[0]);
});
function load(deptid) {
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
                        deptid:deptid,
                        username:$('#searchName').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'username',
                        title: '用户名'
                    },
                    {
                        field: 'realname',
                        title: '姓名'
                    },
                    {
                        field: 'sex',
                        title: '性别',
                        formatter : function(value,row, index){
                            if (row.sex == 0) {
                                return '<span class="ant-tag ant-tag-cyan">未知的性别</span>';
                            } else if (row.sex == 1){
                                return '<span class="ant-tag ant-tag-blue">男性</span>'
                            }else if (row.sex == 2){
                                return '<span class="ant-tag ant-tag-pink">女性</span>'
                            }else if (row.sex == 9){
                                return '<span class="ant-tag ant-tag-orange">未说明的性别</span>'
                            }
                        }
                    },
                    {
                        field: 'moblie',
                        title: '电话'
                    },
                    {
                        field: 'email',
                        title: '邮箱'
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
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '"  mce_ title="编辑" onclick="edit(\''
                                + row.userid
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '"  title="删除"  mce_ onclick="remove(\''
                                + row.userid
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm"  title="重置密码"  mce_ onclick="resetPwd(\''
                                + row.userid
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d + f;
                        }
                    }]
            });
    //在表格体渲染完成，并在 DOM 中可见后触发 替换checkbox样式
    $("#bootstrap-table").on('post-body.bs.table',function(data){
        // $(this).prop('class','ant-table-fixed');
        // $(this).find('thead').addClass('ant-table-thead');
        // $(this).find('tbody').addClass('ant-table-tbody');
        // $(this).find("input:checkbox").each(function (i) {
        //     var $check = $(this);
        //     if ($check.attr("id") && $check.next("label")) {
        //         return;
        //     }
        //     $check.next().remove();
        //     var name = $check.attr("name");
        //     var id = name + "-" + i;
        //     var $label = $('<label for="'+ id +'"></label>');
        //     $check.attr("id", id).parent().addClass("checkbox-custom").append($label);
        // });
        // $('.float-left').prop('class','pull-left pagination')
        // $('.float-right').prop('class','pull-right pagination');
        // $(this).find('th').css('padding', '8px');
        // $(this).find('td').css('padding', '8px');

        //$("div[class='fixed-table-pagination']").empty();
        //
        // $("div[class='pull-left pagination-detail']").append('<span>Total rows</span>&nbsp;&nbsp;');
        // $("div[class='pull-left pagination-detail']").append('<span id="totalCount">'+data.total+'</span>;&nbsp;&nbsp;');
        // $("div[class='pull-left pagination-detail']").append('<input id="pageSize" name="pageSize" value="'+temp+'" style="text-align:center;width:30px"/>&nbsp;&nbsp;records per page');
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
                'userid': id
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

function resetPwd(id) {
    layer.open({
        type: 2,
        title: '修改密码',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['520px', '320px'],
        content: prefix + '/resetPwd/' + id // iframe的url
    });
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
            ids[i] = row['userid'];
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

function exportExcel() {
    var url = "/sys/user/export";
    var args = {
        username:$('#searchName').val()
    };
    var params = Object.keys(args).map(function (key) {
        return encodeURIComponent(key) + "=" + encodeURIComponent(args[key]);
    }).join("&");
    window.location.href = url+"?"+params;
}