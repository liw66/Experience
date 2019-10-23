$().ready(function () {
    layui.use('form', function(){
        var form = layui.form;
    });

    $('.layui-form-label').css('width', '100px');
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/sys/menu/save",
        data: $('#layuiForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#layuiForm").validate({
        rules: {
            menuname: {
                required: true
            }
        },
        messages: {
            menuname: {
                required: icon + "请输入菜单名称"
            }
        }
    })
}

function iconList() {
    layer.open({
        type: 2,
        title:'图标列表',
        content: '/iconlist.html',
        area: ['480px', '90%'],
    });
}