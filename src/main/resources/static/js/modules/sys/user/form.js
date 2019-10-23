$(function () {
    layui.use('form', function () {
        var form = layui.form;
        //监听指定开关
        form.on('switch(switch)', function (data) {
            if (this.checked) {
                $('#state').val('1');
            } else {
                $('#state').val('0');
            }
        });
    });
    $('.layui-form-label').css('width', '100px');
    if ($('#userid').val() != "") {
        $('#username').removeAttr('name').attr('disabled',true);
        $('#password').removeAttr('name').attr('disabled',true);
    }
    setRoleState();
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function setRoleState() {
    var roleids = $('#roleids').val().replace("[", "").replace("]", "").split(",")
    var elems = $('.role input');
    $.each(elems, function (index) {
        var elem = elems.eq(index);
        $.each(roleids, function (i, v) {
            var roleid = v.trim();
            if (roleid == elem.attr('id')) {
                elem.attr('checked', 'true');
            }
        });
    });
}

function getCheckBoxVal() {
    var values = [];
    var elems = $('.layui-form-checked');
    $.each(elems, function (index) {
        var elem = elems.eq(index);
        var input = elem.prev('input');
        values.push(input.attr('id'));
    });
    return values;
}

function save() {
    $('#roleids').val(getCheckBoxVal());
    $.ajax({
        cache: true,
        type: "POST",
        url: "/sys/user/save",
        data: $('#layuiForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("保存成功");
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
            username: {
                required: true,
                remote: {
                    type: "POST",
                    url: "/sys/user/isExist",
                    data: {
                        username: function () {
                            return $('#username').val()
                        }
                    }
                }
            }
        },
        messages: {
            username: {
                required: icon + "请输入登录名",
                remote: icon + "用户名已存在"
            }
        }
    })
}

function openDept() {
    layer.open({
        type: 2,
        title: "选择部门",
        area: ['300px', '450px'],
        content: '/sys/dept/tree'
    })
}

function loadDept(id, text) {
    $('#deptid').val(id);
    $('#deptname').val(text);
}