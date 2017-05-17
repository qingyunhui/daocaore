/**
 * Created by xuxueli on 16/8/12.
 * dependency, jquery + bootstrap
 */
// 通用提示
var callAlert = {
    html:function(){
        var html =
            '<div class="modal fade" id="callAlert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header hidden"><h4 class="modal-title"><strong>提示:</strong></h4></div>' +
            '<div class="modal-body"><div class="alert alert-success"></div></div>' +
            '<div class="modal-footer">' +
            '<div class="text-center" >' +
            '<button type="button" class="btn btn-default ok" data-dismiss="modal" >确认</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        return html;
    },
    show:function(type, msg, callback){
        // dom init
        if ($('#callAlert').length == 0){
            $('body').append(callAlert.html());
        }
        // 弹框初始
        if (type == 1) {
            $('#callAlert .alert').attr('class', 'alert alert-success');
        } else {
            $('#callAlert .alert').attr('class', 'alert alert-warning');
        }
        $('#callAlert .alert').html(msg);
        $('#callAlert').modal('show');
        $('#callAlert .ok').click(function(){
            $('#callAlert').modal('hide');
            if(typeof callback == 'function') {
                callback();
            }
        });
        // $("#callAlert").on('hide.bs.modal', function () {	});	// 监听关闭
    }
};

// 通用确认弹框
var callConfirm = {
    html:function(){
        var html =
            '<div class="modal fade" id="callConfirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-body"><div class="alert alert-success"></div></div>' +
            '<div class="modal-footer">' +
            '<div class="text-center" >' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal" >确认</button>' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal" >取消</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        return html;
    },
    show:function(msg, callback){
        // dom init
        if ($('#callConfirm').length == 0){
            $("body").append(callConfirm.html());
        }
        // 弹框初始
        $('#callConfirm .alert').attr('class', 'alert alert-warning');
        $('#callConfirm .alert').html(msg);
        $('#callConfirm').modal('show');
        $('#callConfirm .ok').unbind("click");	// 解绑陈旧事件
        $('#callConfirm .ok').click(function(){
            $('#callConfirm').modal('hide');
            if(typeof callback == 'function') {
                callback();
                return;
            }
        });
        $('#callConfirm .cancel').click(function(){
            $('#callConfirm').modal('hide');
            return;
        });
    }
};
// 提示-科技主题
var callAlertTec = {
    html:function(){
        var html =
            '<div class="modal fade" id="callAlertTec" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content-tec">' +
            '<div class="modal-body"><div class="alert" style="color:#fff;"></div></div>' +
            '<div class="modal-footer">' +
            '<div class="text-center" >' +
            '<button type="button" class="btn btn-info ok" data-dismiss="modal" >确认</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        return html;
    },
    show:function(msg, callback){
        // dom init
        if ($('#callAlertTec').length == 0){
            $('body').append(callAlertTec.html());
        }
        // 弹框初始
        $('#callAlertTec .alert').html(msg);
        $('#callAlertTec').modal('show');
        $('#callAlertTec .ok').click(function(){
            $('#callAlertTec').modal('hide');
            if(typeof callback == 'function') {
                callback();
            }
        });
    }
};