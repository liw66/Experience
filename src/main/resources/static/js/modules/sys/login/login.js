$(function () {
    if (window != top){
        top.location.href = location.href;
    }
    //得到焦点
    $("#password").focus(function () {
        $("#left_hand").animate({
            left: "150",
            top: " -38"
        }, {
            step: function () {
                if (parseInt($("#left_hand").css("left")) > 140) {
                    $("#left_hand").attr("class", "left_hand");
                }
            }
        }, 2000);
        $("#right_hand").animate({
            right: "-64",
            top: "-38px"
        }, {
            step: function () {
                if (parseInt($("#right_hand").css("right")) > -70) {
                    $("#right_hand").attr("class", "right_hand");
                }
            }
        }, 2000);
    });
    //失去焦点
    $("#password").blur(function () {
        $("#left_hand").attr("class", "initial_left_hand");
        $("#left_hand").attr("style", "left:100px;top:-12px;");
        $("#right_hand").attr("class", "initial_right_hand");
        $("#right_hand").attr("style", "right:-112px;top:-12px");
    });
    $('#login').click(function () {
        //1.$.ajax带json数据的异步请求
        var aj = $.ajax({
            url: 'login',// 跳转到 action
            data: $('#signupForm').serialize(),
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (data) {
                if (data.code === 0) {
                    parent.location.href = 'index';
                } else {
                    alert(data.msg);
                }
            }
        });
    })
});