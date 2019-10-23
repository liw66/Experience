$(function() {
    layui.use('form', function(){
        var form = layui.form;
    });

    $('.layui-form-label').css('width', '100px');
	validateRule();
    getTreeData();
});

$.validator.setDefaults({
	submitHandler : function() {
        save();
	}
});

function getCheckedVal(){
	var menuIds = $('#menuTree').jstree('get_checked');
	$('#menuTree').find('.jstree-undetermined').each(function (i,e) {
		var menuId = $(e).closest('.jstree-node').attr('id');
		if (menuId != 0){
            menuIds.push(menuId);
		}
    })
	return menuIds;
}

function save() {
    var menuIds = getCheckedVal();
	$('#menuids').val(menuIds);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/role/save",
		data : $('#layuiForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
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
		rules : {
            rolename : {
				required : true
			}
		},
		messages : {
            rolename : {
				required : icon + "请输入角色名称"
			}
		}
	})
}

function getTreeData() {
    $.ajax({
        type : "GET",
        url : "/sys/role/getRoleMenu",
        data: {"roleid":$('#roleid').val()},
        success : function(tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#menuTree').jstree({
        'core' : {
            'data' : tree.data
        },
        plugins: ["wholerow","checkbox"],
        "checkbox": {
            "three_state": true,//父子级别级联选择
        },
    });
    $('#menuTree').jstree().open_all();
}
