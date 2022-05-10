
function get(url, success){
    $.ajax({
        type: "get",
        url: url,
        async: true,
        dataType: 'json',
        success: success
    });
}



function post(url, data, success){
    $.ajax({
        type: "post",
        url: url,
        async: true,
        data: data,
        dataType: 'json',
        success: success
    });
}



function initAfterChange() {
    get("/api/student?type=all",function (data){
        if(data.code === 200){
            flushListBeforeEmpty(data.data);
        }
    });
}

function flushListBeforeEmpty(stuList) {
    let index = 0;
    $("#stuList").empty();
    for (let student of stuList) {
        $("#stuList").html($("#stuList").html()
            + '<tr>'
            + '<td>'
            + '<div class="custom-control custom-checkbox">'
            + '<input type="checkbox" class="custom-control-input ids" name="ids[]" value="' + student.id + '" id="ids-' + student.id + '">'
            + '<label class="custom-control-label" for="ids-' + student.id + '"></label>'
            + '</div>'
            + '</td>'
            + '<td>' + student.id + '</td>'
            + '<td>' + student.name + '</td>'
            + '<td>' + student.sex + '</td>'
            + '<td>' + student.age + '</td>'
            + '<td>' + student.phone + '</td>'
            + '<td>' + student.department + '</td>'
            + '<td>' + student.address + '</td>'
            + '<td>'
            + '<div class="btn-group">'
            + '<a class="btn btn-xs btn-default" data-toggle="modal" data-target="#exampleModalChange" data-index="' + index++ + '" href="javascript:void(0);" title=""'
            + 'data-original-title="编辑"><i class="mdi mdi-18px mdi-pencil"></i></a>'
            + '<a class="btn btn-xs btn-default" href="javascript:void(0);" onclick="delStu(' + student.id + ')" title=""'
            + 'data-toggle="tooltip" data-original-title="删除"><i class="mdi mdi-18px mdi-window-close"></i></a>'
            + '</div>'
            + '</td>'
            + '</tr>')
    }
}


/*
 * 提取通用的通知消息方法
 * 这里只采用简单的用法，如果想要使用回调或者更多的用法，请查看lyear_js_notify.html页面
 * @param $msg 提示信息
 * @param $type 提示类型:'info', 'success', 'warning', 'danger'
 * @param $delay 毫秒数，例如：1000
 * @param $icon 图标，例如：'fa fa-user' 或 'glyphicon glyphicon-warning-sign'
 * @param $from 'top' 或 'bottom' 消息出现的位置
 * @param $align 'left', 'right', 'center' 消息出现的位置
 */
function showNotify($msg, $type,$onShow, $delay, $icon, $from, $align) {
    $type  = $type || 'info';
    $delay = $delay || 1000;
    $from  = $from || 'top';
    $align = $align || 'center';
    $enter = $type == 'danger' ? 'animated shake' : 'animated fadeInUp';

    jQuery.notify({
            icon: $icon,
            message: $msg
        },
        {
            element: 'body',
            type: $type,
            allow_dismiss: true,
            newest_on_top: true,
            showProgressbar: false,
            placement: {
                from: $from,
                align: $align
            },
            offset: 20,
            spacing: 10,
            z_index: 10800,
            delay: $delay,
            animate: {
                enter: $enter,
                exit: 'animated fadeOutDown'
            },
            onShow:$onShow
        });
}

/**
 * 登录  异步请求
 */
function login(data){
    $.post("/api/login",data,
        function(data){
            if(data.code === 200) {
                window.location.href = 'index.html'; // 页面跳转
            }else{
                alert(data.message);
            }
        },
        false
    );
}

/**
 * 添加学生信息
 */
function addStu(data){
    $.post("/api/student/save",data,
        function(data){
            if(data.code === 200) {
                showNotify(data.message,"success",function (){
                    // 重置表单
                    $('#add-form')[0].reset();
                });
            }else{
                showNotify(data.message,"warning");
            }
        },
    );
}

/**
 * 修改学生信息
 */
function updateStu(data){
    $.post("/api/student/update",data,
        function(data){
            if(data.code === 200) {
                showNotify(data.message,"success",function (){
                   $("#btnClose").trigger("click");
                   initAfterChange();
                });
            }else{
                showNotify(data.message,"warning");
            }
        },
    );
}

/**
 * 删除学生信息
 */
function delStu(id){
    $.post("/api/student/del",{
            id:id
        },
        function(data){
            if(data.code === 200) {
                showNotify(data.message,"success",initAfterChange);
            }else{
                showNotify(data.message,"warning");
            }
        },
    );
}

/**
 * 通过id查询学生信息
 */
function searchStuById(id){
    $.get("/api/student?type=searchById&id="+id,
        function(data){
            if(data.code === 200) {
                let list = [data.data];
                showNotify(data.message,"success",flushListBeforeEmpty(list));
            }else{
                showNotify(data.message,"warning");
            }
        }
    );
}

/**
 * 通过name模糊查询学生信息
 */
function searchStuByName(name){
    $.get("/api/student?type=searchByName&name="+name,
        function(data){
            if(data.code === 200) {
                showNotify(data.message,"success",flushListBeforeEmpty(data.data));
            }else{
                showNotify(data.message,"warning");
            }
        }
    );
}

/**
 * 登出  同步请求
 */
function logout(){
    $.get("/logout",
        function(data){
            if(data.code === 200) {
                window.location.href = 'login.html'; // 页面跳转
            }else{
                alert(data.message);
            }
        },
        false
    );
}






