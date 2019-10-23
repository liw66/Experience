
$(function () {
    displayDateInit();
    //layuiTableInit();
    bootstrapTableInit();
})

function displayDateInit(){
    var sDate = '数据获取时间 ' + moment().format('YYYY年MM月DD日 HH时mm分ss秒');
    $("#disDate").html(sDate);
}

function layuiTableInit() {

    layui.use('table', function(){
        var table = layui.table;
        table.render({
            skin: 'line' //表格风格
            ,elem: '#layui-table'
            ,url:'/actuator/httptrace'
            ,parseData: function(res) { //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": "", //解析提示文本
                    "count": res.traces.length, //解析数据长度
                    "data": res.traces //解析数据列表
                };
            }
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {type:'checkbox'}//开启复选框
                , {
                    field: '', width: '15%', title: '请求时间', sort: true, templet:
                        function (d) {
                            return moment(d.timestamp).format('YYYY-MM-DD HH:mm:ss');
                        }
                }
                , {
                    field: '', width: '10%', title: '请求方法', sort: true, templet:
                        function (d) {
                            switch (d.request.method) {
                                case 'GET':
                                    return '<span class="ant-tag ant-tag-has-color" style="background-color: #87d068; ">' + d.request.method + '</span>';
                                case 'POST':
                                    return '<span class="ant-tag ant-tag-has-color" style="background-color: #2db7f5; ">' + d.request.method + '</span>';
                                case 'PUT':
                                    return '<span class="ant-tag ant-tag-has-color" style="background-color: #ffba5a; ">' + d.request.method + '</span>';
                                case 'DELETE':
                                    return '<span class="ant-tag ant-tag-has-color" style="background-color: #f50; ">' + d.request.method + '</span>';
                                default:
                                    return d.request.method
                            }
                        }
                }
                ,{field:'', width: '35%', title: '请求URL', sort: true, templet: '<div>{{d.request.uri}}</div>'}
                , {
                    field: '', width: '12%', title: '响应状态', sort: true, templet:
                        function (d) {
                            if (d.response.status < 200) {
                                return '<span class="ant-tag ant-tag-pink">' + d.response.status + '</span>';
                            } else if (d.response.status < 201) {
                                return '<span class="ant-tag ant-tag-green">' + d.response.status + '</span>';
                            } else if (d.response.status < 399) {
                                return '<span class="ant-tag ant-tag-cyan">' + d.response.status + '</span>';
                            } else if (d.response.status < 403) {
                                return '<span class="ant-tag ant-tag-orange">' + d.response.status + '</span>';
                            } else if (d.response.status < 501) {
                                return '<span class="ant-tag ant-tag-red">' + d.response.status + '</span>';
                            } else {
                                return d.response.status
                            }
                        }
                }
                , {
                    field: '', width: '12%', title: '请求耗时', sort: true, templet:
                        function (d) {
                            if (d.timeTaken < 500) {
                                return '<span class="ant-tag ant-tag-green">' + d.timeTaken + ' ms</span>';
                            } else if (d.timeTaken < 1000) {
                                return '<span class="ant-tag ant-tag-cyan">' + d.timeTaken + ' ms</span>';
                            } else if (d.timeTaken < 1500) {
                                return '<span class="ant-tag ant-tag-orange">' + d.timeTaken + ' ms</span>';
                            } else {
                                return '<span class="ant-tag ant-tag-red">' + d.timeTaken + ' ms</span>';
                            }
                        }
                }
            ]]
            ,done:function(res, curr, count){
                var $table = $('.layui-table-box table');
                $table.prop('class','ant-table-fixed');
                $table.find('thead').addClass('ant-table-thead');
                $table.find('tbody').addClass('ant-table-tbody');
            }
        });
    });


}

function bootstrapTableInit(){
    $('#bootstrap-table').bootstrapTable({
        url: '/actuator/httptrace',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        queryParams: queryParams,           //传递参数（*）
        columns: [{
            checkbox: true
        }, {
            field: 'timestamp',
            title: '请求时间',
            width: '17%',
            formatter: function (value, row, index) {
                return moment(value).format('YYYY-MM-DD HH:mm:ss');
            }
        }, {
            field: '',
            title: '请求方法',
            width: '10%',
            formatter : function(value,row, index){
                switch (row.request.method) {
                    case 'GET':
                        return  '<span class="ant-tag ant-tag-has-color" style="background-color: #87d068;height: ">'+row.request.method+'</span>';
                    case 'POST':
                        return '<span class="ant-tag ant-tag-has-color" style="background-color: #2db7f5; ">'+row.request.method+'</span>';
                    case 'PUT':
                        return '<span class="ant-tag ant-tag-has-color" style="background-color: #ffba5a; ">'+row.request.method+'</span>';
                    case 'DELETE':
                        return '<span class="ant-tag ant-tag-has-color" style="background-color: #f50; ">'+row.request.method+'</span>';
                    default:
                        return row.request.method
                }
            }
        }, {
            field: '',
            title: '请求URL',
            width: '45%',
            formatter : function(value,row, index){
                return row.request.uri;
            }
        }, {
            field: '',
            title: '响应状态',
            width: '12%',
            formatter : function(value,row, index){
                if (row.response.status < 200) {
                    return '<span class="ant-tag ant-tag-pink">'+row.response.status+'</span>';
                } else if (row.response.status < 201) {
                    return '<span class="ant-tag ant-tag-green">'+row.response.status+'</span>';
                } else if (row.response.status < 399) {
                    return '<span class="ant-tag ant-tag-cyan">'+row.response.status+'</span>';
                } else if (row.response.status < 403) {
                    return '<span class="ant-tag ant-tag-orange">'+row.response.status+'</span>';
                } else if (row.response.status < 501) {
                    return '<span class="ant-tag ant-tag-red">'+row.response.status+'</span>';
                } else {
                    return row.response.status
                }
            }
        }, {
            field: '',
            title: '请求耗时',
            width: '12%',
            formatter : function(value,row, index){
                if (row.timeTaken < 500) {
                    return '<span class="ant-tag ant-tag-green">'+row.timeTaken+' ms</span>';
                } else if (row.timeTaken < 1000) {
                    return '<span class="ant-tag ant-tag-cyan">'+row.timeTaken+' ms</span>';
                } else if (row.timeTaken < 1500) {
                    return '<span class="ant-tag ant-tag-orange">'+row.timeTaken+' ms</span>';
                } else {
                    return '<span class="ant-tag ant-tag-red">'+row.timeTaken+' ms</span>';
                }
            }
        }]
        ,responseHandler:function (res) {
            return {
                "total":res.traces.length,
                "rows":res.traces
            }
        }
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
        $('.float-left').prop('class','pull-left pagination')
        $('.float-right').prop('class','pull-right pagination');
        //$("div[class='fixed-table-pagination']").empty();
        //
        // $("div[class='pull-left pagination-detail']").append('<span>Total rows</span>&nbsp;&nbsp;');
        // $("div[class='pull-left pagination-detail']").append('<span id="totalCount">'+data.total+'</span>;&nbsp;&nbsp;');
        // $("div[class='pull-left pagination-detail']").append('<input id="pageSize" name="pageSize" value="'+temp+'" style="text-align:center;width:30px"/>&nbsp;&nbsp;records per page');
    });

}

//得到查询的参数
function queryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
    };
    return temp;
};
