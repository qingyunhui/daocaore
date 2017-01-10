﻿/*!DYApp.js
 * ================
 * @Author  
 * @Support 
 * @Email   
 * @version 
 * @license 
 */
'use strict';
//判断是否引用Jquery
if (typeof jQuery === "undefined") {
    throw new Error("AdminLTE requires jQuery");
}

$.DY = {};

$(function () {
    initDY();
    //窗口大小改变时重新计算jqgrid的宽度
    $.DY.layout();
    $(':input,select', '.form-table').each(function () {
        if ($(this).attr('type') != "button" && $(this).attr('type') != "submit"
            && $(this).attr('type') != "reset" && $(this).attr('type') != "image" &&
            $(this).attr("disabled") != 'disabled') {
            if ($(this).val() == "") {
                $(this).css('background-color', '#dbeadd');
            } else {
                $(this).attr('data-oldvalue', $(this).val())
            }
            $(this).bind('blur', function () {
                if ($(this).val() != "" && $(this).val() != $(this).data("oldvalue")) {
                    $(this).css('background-color', '#dbeadd');
                }
                else if ($(this).val() != "" && $(this).val() == $(this).data("oldvalue")) {
                    $(this).css('background-color', '#fff');
                }
            })
        }
    });
})

function initDY() {
    /*
     * DY
     * @type Object
     * @description $.DY 为自定义js的主要命名空间
     */
    $.DY = {
        /* 测试方法
         * @method Test
         * @type function 
         * @description  测试方法
         * @param  
         * @example $.DY.Test()
         */
        Test: function () {
            alert("Hello！");
        },
        /* 测试对象
         * @type object 
         * @description  测试方法
         */
        TestObject: {
            /* 测试对象内方法
             * @method Hello
             * @type function 
             * @description  测试对象内方法
             * @param  {string} msg 消息内容
             * @example $.DY.TestObject.Hello('hello!')
             */
            Hello: function (msg) {
                alert(msg);
            }
        },
        /* 页面布局及重绘对象
         * @method resize_jqgrid
         * @type function 
         * @description  页面布局及重绘对象
         * @example $.DY.layout()
         */
        layout: function () {
            //if ($('.jqgrid', '.content').length > 0) {
            //    $('.jqgrid').jqGrid('setGridWidth', $('.content').width() - 2)
            //}
            $(window, '.content').resize(function () {
                if ($('.jqgrid', '.content').length > 0) {
	                /*jqgrid自适应*/
                	$(".null-line").css("height",$(".modal-footer").height() + 14);
                	var gPrevLen = $(".ui-jqgrid").prevAll().length;
					var jHeight = 0;
					if(gPrevLen > 1){
						for (var i = 0; i < gPrevLen; i++) {
							var j = $(".ui-jqgrid").prevAll().eq(i).height() +13;
							jHeight += j;
						};
					}else{
						jHeight=$(".ui-jqgrid").prevAll().height() + 13;
					};
                	var tableHeight = $(window).height() - $(".content-header").height() - jHeight - $(".ui-jqgrid-titlebar").height() - $(".ui-jqgrid-hdiv").height()-$(".null-line").height();
					if($(".ui-jqgrid").parent(".content").length > 0){
                    	if($(".ui-jqgrid + div.bottongroup").length > 0){
	                		$(".ui-jqgrid-bdiv").css("height",tableHeight - $(".ui-jqgrid").nextAll().height()  - 66.6);
	                	}
	                	else{
	            	 		$(".ui-jqgrid-bdiv").css("height",tableHeight - 52.6);
	            	 		if($(".ui-jqgrid").parent("section").prev("section.content").length > 0){
								$(".ui-jqgrid-bdiv").css("height",tableHeight - ( $(".ui-jqgrid").parent("section").prev("section.content").height() + 10 ) - ($(".content-header").height() * 2 ) - 38.6);
							}
	                	};
                	 	$(".jqgrid").jqGrid("setGridWidth", $(".content").width() - 2);
                	 }
					else if($(".ui-jqgrid").parent(".box-body").length > 0){
	            	 	$(".ui-jqgrid-bdiv").css("height",tableHeight - $(".ui-jqgrid-bdiv").parents(".box").prev().height() - ($(".content-header").height() * 2 ) - 86.6);
                	 	$(".jqgrid").jqGrid("setGridWidth", $(".box-body:first").width() - 2);
                	};
                }
            });
            if (typeof $.fn.slimScroll != 'undefined') {
                $(".modal-body").slimScroll({
                    alwaysVisible: false,
                    height: "260px",
                    size: "3px"
                });
            }
        },
        
        /* 页面布局及重绘对象
         * @type object 
         * @description  页面布局及重绘对象
         */
        message: {
        	
            /* 弹窗消息
             * @method alert
             * @type function 
             * @description  在页面内弹出消息
             * @param {string} message 消息内容
             * @param {string} title 弹窗题头
             * @param {string} classify 弹窗类型，类型有info(i)，warn(w)，error(e)，success(s)
             * @example $.DY.message.alert()
             */
            alert: function (message, title, classify, callback) {
            	$.alert(message, classify, callback);
            },
            /* confirm弹窗
             * @method confirm
             * @type function 
             * @description  在页面内弹出确认信息
             * @param  {string} message 消息内容
             * @param {string} title 弹窗题头
             * @param {string} oktext 确认按钮文本，默认为“确认”
             * @example $.DY.message.confirm("确认吗？","","").on(function(e){ 取消：e为false,确认：e为true})
             */
            confirm: function (message, title, oktext) {
                var modalcontent = $("<div class='modal-content'></div>")
                var modalheader = $("<div class='modal-header'></div>")
                    .append($('<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>'));

                title = (title == "" || title == NaN) ? "提示信息" : title;
                oktext = (oktext == "" || oktext == NaN) ? "确定" : oktext;

                $('<h4 class="modal-title text-info"><i class="fa fa-question-circle"></i>&nbsp;&nbsp;' + title + '</h4>').appendTo(modalheader);
                modalheader.appendTo(modalcontent);
                $('<div class="modal-body"> <p>' + message + '</p> </div>').appendTo(modalcontent);

                var modelfooter = $("<div class='modal-footer'></div>")
                .append($('<button type="button" class="btn btn-primary btn-ok btn-sm" data-dismiss="modal">' + oktext + '</button>'))
                .append($('<button type="button" class="btn btn-default btn-cancel btn-sm" data-dismiss="modal">取消</button>'));
                    
                modelfooter.appendTo(modalcontent);
            	if(typeof(top.$('.modal.fade.confirm').html()) != "undefined"){
					return {
	                    on: function () {}
	                };
				}
                var modal = top.$("<div class='modal fade confirm'></div>").append($("<div class='modal-dialog modal-sm'></div>").append(modalcontent));
                modal.modal({ keyboard: true }).on('hidden.bs.modal', function (e) {
                    $(this).detach();
                });
                return {
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.btn-ok').one("click",function () {
                            	 callback(true);
                            });
                            modal.find('.btn-cancel').click(function () { callback(false) });
                        }
                    }
                };
            },
            
            withoutConfirm: function (message, title, oktext) {
                var modalcontent = $("<div class='modal-content'></div>")
                var modalheader = $("<div class='modal-header'></div>")
                    .append($('<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>'));

                title = (title == "" || title == NaN) ? "提示信息" : title;
                oktext = (oktext == "" || oktext == NaN) ? "确定" : oktext;

                $('<h4 class="modal-title text-info"><i class="fa fa-question-circle"></i>&nbsp;&nbsp;' + title + '</h4>').appendTo(modalheader);
                modalheader.appendTo(modalcontent);
                $('<div class="modal-body"> <p>' + message + '</p> <input id="tags" type="checkbox" value="1" /><label for="tags"><span style="color:red">不再弹出提示框<span></label></div>').appendTo(modalcontent);

                var modelfooter = $("<div class='modal-footer'></div>")
                    .append($('<button type="button" class="btn btn-default btn-cancel btn-sm" data-dismiss="modal">取消</button>'))
                    .append($('<button type="button" class="btn btn-primary btn-ok btn-sm" data-dismiss="modal">' + oktext + '</button>'));
                modelfooter.appendTo(modalcontent);

                var modal = top.$("<div class='modal fade '></div>").append($("<div class='modal-dialog modal-sm'></div>").append(modalcontent));
                modal.modal({ keyboard: true }).on('hidden.bs.modal', function (e) {
                    $(this).detach();
                });

                return {
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.btn-ok').click(function () {  
                            	if(modal.find('#tags').is(":checked")){
                            		$('#myTags').val(modal.find('#tags').val());
                            	}
                            	callback(true) });
                            modal.find('.btn-cancel').click(function () { callback(false) });
                        }
                    }
                };
            }
        },
      
        /* confirm弹窗
         * @method confirm
         * @type function 
         * @description  在页面内弹出确认信息
         */
        dialog:{
        	/* 
             * @method open
             * @type function 
             * @description  弹出页面
             * @param  {json} opt 页面参数
             * //$.DY.dialog.open({url:"",title:"",style:""});
             */
        	open:function(opt){
        		 /*
        	     *opt.url:弹出页请求的Url
        	     *opt.title:弹出页标题
        	     *opt.width:弹出页宽度
        	     *opt.height:弹出页高度
        	     *opt.top:弹出页上边距
        	     *opt.left:弹出页左边距
        	    */
        		top.dialog({
                    id: opt.id,
                    title: opt.title,
                    url: opt.url,
                    data: opt.data,
                    width: opt.width,
                    height: opt.height,
                    padding: "2px",
                    onclose: function () {
                        if (opt.closefunc != "" && typeof (opt.closefunc) == "function") {
                        	opt.closefunc(this.returnValue);
                        }
                        if(opt.autoClose){
                        	dialog({id:opt.id}).remove();
                    	}
                    }
                }).showModal();
        	},
        	/* 
             * @method opendialog
             * @type function 
             * @description  弹出页面
             * @param  {string} opt 页面参数
             * //$.dialog.opendialog('test-dialog','测试','example/list1.html','800px','400px',"");
             */
        	opendialog: function (id,title, url, width, height,closefunc) {
        		// 固定物流信息弹框宽度以便展示, 后期重构时重写每个物流信息的弹框调用
        		width = '800px';
                top.dialog({
                    id: id,
                    title: title,
                    url: url,
                    width: width,
                    height: height,
                    onclose:function () {
                    	if(closefunc!=""&&typeof(closefunc)=="function"){
                    	closefunc(this.returnValue);
                    	}
        			},
                    padding: "2px"
                }).showModal();
            }
        },
        //	弹窗口
        window : {
        	open : function(opts) {
            	if (opts) {
            		var id = opts.id || "";
            		var title = opts.title || "";
            		var url = opts.url || "";
            		var callback = opts.callback || null;
            		var $window = $("<div class='ccl-window'></div>");
                	var $header = $("<div class='ccl-header'><span class='ccl-title'>" + title + "</span></div>");
                	var $close = $("<button type='button' class='close' data-dismiss='modal'><span aria-hidden='true'>&times;</span><span class='sr-only'>关闭</span></button>");
                	var $iframe = $("<iframe class='ccl-iframe' src='" + url + "'></iframe>");
                	$header.append($close);
                	$window.append($header).append($iframe);
                	var modal = top.$("<div id='" + id + "' class='modal fade'></div>").append($window);
                	modal.data("callback", callback);
                    modal.modal({ keyboard: true }).on('hidden.bs.modal', function (e) {
                        $(this).detach();
                    });
                    $close.click(function() {
                    	if (callback instanceof Function) {
                        	callback();
                        }
                    });
            	}
            },
            close : function(id, args) {
            	top.window.$("div.modal-backdrop.fade.in").remove();
            	var $modal = top.$("#" + id);
            	var callback = $modal.data("callback");
            	$modal.detach();
            	if (callback instanceof Function) {
                	callback(args);
                }
            }
        }
    }
}

//日期格式化
Date.prototype.format = function(format) {
    var o = { 
        "M+" : this.getMonth()+1, //month 
        "d+" : this.getDate(), //day 
        "H+" : this.getHours(), //hour 
        "m+" : this.getMinutes(), //minute 
        "s+" : this.getSeconds(), //second 
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter 
        "S"  : this.getMilliseconds() //millisecond 
    };

    if (/(y+)/.test(format)) { 
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }

    for (var k in o) { 
        if(new RegExp("("+ k +")").test(format)) { 
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
        }
    }
    return format;
}

//js获取项目根路径，如： http://localhost:8080/diaoyu
function getRootPath() {
    //获取当前网址，如： http://localhost:8080/diaoyu/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： diaoyu/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:diaoyu
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/diaoyu
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

function create(){//jqGrid高度自适应
	var heightAll = $(window).height();//获取整个页面window的高度
	var heightSearch = $('.box').height();//获取查询条件的高度
	var heightButt = $('.bottongroup').height();//获取jqgrid下方操作按钮的高度
	var heightHeard = $('.jqgrid', '.content').offset();//获取页面题头的高度
	var heightTable = 255;//默认jqGrid的高度
	if (heightHeard != undefined){//判断题头
		heightTable = heightAll - heightHeard.top - heightSearch - 10 -20;
		if(heightButt != null){
			heightTable = heightAll - heightHeard.top - heightSearch - 10 -20 - heightButt;
		}
	}
	if (heightTable > 600){//jqGrid的高度不能大于600，大于的固定600
		heightTable = 600;
	}
	return heightTable;
}
function changeSelectChecked(gridlist,num,value,event){
	 var ids = $(gridlist).jqGrid('getDataIDs');
 	  if(!$('#jqg_gridlist_'+num).is(':checked')){
 		  if(event.which==13 || (event.which==8)){
    		  return;
    	  }
 		  $(gridlist).setSelection(ids[--num],true);
 	  } if(value.length==0 && $('#jqg_gridlist_'+num).is(':checked')){
		  $(gridlist).setSelection(ids[--num],true);
 	  }
  }

$.extend({
	alert : function(msg, type, callback, delayTime) {
		top.$.topAlert(msg, type, callback, delayTime);
	},
	topAlert : function(msg, type, callback, delayTime) {
		if(delayTime) {
		} else {
			delayTime = 2000;
		}
		var $div = top.$("div.msg-div");
		if ($div == null || $div.length == 0) {
			$div = $("<div class='msg-div'></div>");
			top.$("body").append($div);
		}
		var $text = $("<div class='msg-div-text'></div>");
		if (type == "success") {
			$text.append("<i class='fa fa-check-circle' style='margin-right:8px;'></i>");
			$text.css("background-color", "#00c0ef");
		} else if (type == "error") {
			alert(msg);
			if (typeof callback == "function") {
				callback();
			}
			return;
		} else if (type == "warn") {
			$text.append("<i class='fa fa-exclamation-triangle' style='margin-right:8px;'></i>");
			$text.css("background-color", "#f39c12");
		}
		$text.append(msg || "");
		$div.append($text);
		$div.show();
		$text.animate({"margin-left":"0px"});
		$text.delay(2000).animate({"margin-left":"250px"}, function() {
			$text.remove();
			if ($div.children().length == 0) {
				$div.hide();
				if (typeof callback == "function") {
					callback();
				}
			}
		});
	}
});
