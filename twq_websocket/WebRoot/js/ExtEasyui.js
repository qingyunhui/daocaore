/**
 * 扩展treegrid，使其支持平滑数据格式
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});
/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
var tree = {
	loadFilter : function(data, parent) {
		var opt = $(this).data().tree.options;
		var idField, textField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
};
$.extend($.fn.combotree.defaults, tree);
$.extend($.fn.tree.defaults, tree);

/**
 * 防止panel/window/dialog组件超出浏览器边界
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
var onMove = {
	onMove : function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	}
};
$.extend($.fn.dialog.defaults, onMove);
$.extend($.fn.window.defaults, onMove);
$.extend($.fn.panel.defaults, onMove);
/**
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.panel.defaults, {
	onBeforeDestroy : function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for (var i = 0; i < frame.length; i++) {
					frame[i].src = '';
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
					try {
						CollectGarbage();
					} catch (e) {
					}
				}
			}
		} catch (e) {
		}
	}
});
/**
 * 创建一个模式化的dialog
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
var modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 400,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};



/**
 * 扩展两个方法
 */
$.extend($.fn.datagrid.methods, {
    /**
     * 开打提示功能
     * @param {} jq
     * @param {} params 提示消息框的样式
     * @return {}
     */
    doCellTip: function(jq, params){
        function showTip(data, td, e){
            if ($(td).text() == "") 
                return;
            data.tooltip.text($(td).text()).css({
                top: (e.pageY + 10) + 'px',
                left: (e.pageX + 20) + 'px',
                'z-index': $.fn.window.defaults.zIndex,
                display: 'block'
            });
        };
        return jq.each(function(){
            var grid = $(this);
            var options = $(this).data('datagrid');
            if (!options.tooltip) {
                var panel = grid.datagrid('getPanel').panel('panel');
                var defaultCls = {
                    'border': '1px solid #333',
                    'padding': '2px',
                    'color': '#333',
                    'background': '#f7f5d1',
                    'position': 'absolute',
                    'max-width': '200px',
					'border-radius' : '4px',
					'-moz-border-radius' : '4px',
					'-webkit-border-radius' : '4px',
                    'display': 'none'
                }
                var tooltip = $("<div id='celltip'></div>").appendTo('body');
                tooltip.css($.extend({}, defaultCls, params.cls));
                options.tooltip = tooltip;
                panel.find('.datagrid-body').each(function(){
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;
                    $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td', {
                        'mouseover': function(e){
                            if (params.delay) {
                                if (options.tipDelayTime) 
                                    clearTimeout(options.tipDelayTime);
                                var that = this;
                                options.tipDelayTime = setTimeout(function(){
                                    showTip(options, that, e);
                                }, params.delay);
                            }
                            else {
                                showTip(options, this, e);
                            }
                            
                        },
                        'mouseout': function(e){
                            if (options.tipDelayTime) 
                                clearTimeout(options.tipDelayTime);
                            options.tooltip.css({
                                'display': 'none'
                            });
                        },
                        'mousemove': function(e){
							var that = this;
                            if (options.tipDelayTime) 
                                clearTimeout(options.tipDelayTime);
                            //showTip(options, this, e);
							options.tipDelayTime = setTimeout(function(){
                                    showTip(options, that, e);
                                }, params.delay);
                        }
                    });
                });
                
            }
            
        });
    },
    /**
     * 关闭消息提示功能
     *
     * @param {}
     *            jq
     * @return {}
     */
    cancelCellTip: function(jq){
        return jq.each(function(){
            var data = $(this).data('datagrid');
            if (data.tooltip) {
                data.tooltip.remove();
                data.tooltip = null;
                var panel = $(this).datagrid('getPanel').panel('panel');
                panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')
            }
            if (data.tipDelayTime) {
                clearTimeout(data.tipDelayTime);
                data.tipDelayTime = null;
            }
        });
    }
});
/**
 * layout全屏效果
 */
$.extend($.fn.layout.methods, {
	full : function (jq) {
		return jq.each(function () {
			var layout = $(this);
			var center = layout.layout('panel', 'center');
			center.panel('maximize');
			center.parent().css('z-index', 10);

			$(window).on('resize.full', function () {
				layout.layout('unFull').layout('resize');
			});
		});
	},
	unFull : function (jq) {
		return jq.each(function () {
			var center = $(this).layout('panel', 'center');
			center.parent().css('z-index', 'inherit');
			center.panel('restore');
			$(window).off('resize.full');
		});
	}
});

$.extend($.fn.datagrid.methods, {      
    /**
     * 开打提示功能    
     * @param {} jq    
     * @param {} params 提示消息框的样式    
     * @return {}    
     */     
    doCellTip:function (jq, params) {      
        function showTip(showParams, td, e, dg) {      
            //无文本，不提示。      
            if ($(td).text() == "") return;      
               
            params = params || {};   
            var options = dg.data('datagrid');      
            showParams.content = '<div class="tipcontent">' + showParams.content + '</div>';      
            $(td).tooltip({      
                content:showParams.content,      
                trackMouse:true,      
                position:params.position,      
                onHide:function () {      
                    $(this).tooltip('destroy');      
                },      
                onShow:function () {      
                    var tip = $(this).tooltip('tip');      
                    if(showParams.tipStyler){      
                        tip.css(showParams.tipStyler);      
                    }      
                    if(showParams.contentStyler){      
                        tip.find('div.tipcontent').css(showParams.contentStyler);      
                    }   
                }      
            }).tooltip('show');      
     
        };      
        return jq.each(function () {      
            var grid = $(this);      
            var options = $(this).data('datagrid');      
            if (!options.tooltip) {      
                var panel = grid.datagrid('getPanel').panel('panel');      
                panel.find('.datagrid-body').each(function () {      
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;      
                    $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td[field]', {      
                        'mouseover':function (e) {   
                            //if($(this).attr('field')===undefined) return;      
                            var that = this;   
                            var setField = null;   
                            if(params.specialShowFields && params.specialShowFields.sort){   
                                for(var i=0; i<params.specialShowFields.length; i++){   
                                    if(params.specialShowFields[i].field == $(this).attr('field')){   
                                        setField = params.specialShowFields[i];   
                                    }   
                                }   
                            }   
                            if(setField==null){   
                                options.factContent = $(this).find('>div').clone().css({'margin-left':'-5000px', 'width':'auto', 'display':'inline', 'position':'absolute'}).appendTo('body');      
                                var factContentWidth = options.factContent.width();      
                                params.content = $(this).text();      
                                if (params.onlyShowInterrupt) {      
                                    if (factContentWidth > $(this).width()) {      
                                        showTip(params, this, e, grid);      
                                    }      
                                } else {      
                                    showTip(params, this, e, grid);      
                                }    
                            }else{   
                                panel.find('.datagrid-body').each(function(){   
                                    var trs = $(this).find('tr[datagrid-row-index="' + $(that).parent().attr('datagrid-row-index') + '"]');   
                                    trs.each(function(){   
                                        var td = $(this).find('> td[field="' + setField.showField + '"]');   
                                        if(td.length){   
                                            params.content = td.text();   
                                        }   
                                    });   
                                });   
                                showTip(params, this, e, grid);   
                            }   
                        },      
                        'mouseout':function (e) {      
                            if (options.factContent) {      
                                options.factContent.remove();      
                                options.factContent = null;      
                            }      
                        }      
                    });      
                });      
            }      
        });      
    },      
    /**
     * 关闭消息提示功能    
     * @param {} jq    
     * @return {}    
     */     
    cancelCellTip:function (jq) {      
        return jq.each(function () {      
            var data = $(this).data('datagrid');      
            if (data.factContent) {      
                data.factContent.remove();      
                data.factContent = null;      
            }      
            var panel = $(this).datagrid('getPanel').panel('panel');      
            panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')      
        });      
    }      
});  

/**
 * 扩展easyui的validator插件rules，支持更多类型验证
 */
$.extend($.fn.validatebox.defaults.rules, {
			minLength : { // 判断最小长度
				validator : function(value, param) {
					return value.length >= param[0];
				},
				message : '最少输入 {0} 个字符'
			},
			length : { // 长度
				validator : function(value, param) {
					var len = $.trim(value).length;
					return len >= param[0] && len <= param[1];
				},
				message : "输入内容长度必须介于{0}和{1}之间"
			},
			phone : {// 验证电话号码
				validator : function(value) {
					return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
				},
				message : '格式不正确,请使用下面格式:020-88888888'
			},
			mobile : {// 验证手机号码
				validator : function(value) {
					return /^(13|15|18)\d{9}$/i.test(value);
				},
				message : '手机号码格式不正确'
			},
			phoneAndMobile : {// 电话号码或手机号码
				validator : function(value) {
					return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^(13|15|18)\d{9}$/i.test(value);
				},
				message : '电话号码或手机号码格式不正确'
			},
			idcard : {// 验证身份证
				validator : function(value) {
					return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value) || /^\d{18}(\d{2}[A-Za-z0-9])?$/i.test(value);
				},
				message : '身份证号码格式不正确'
			},
			intOrFloat : {// 验证整数或小数
				validator : function(value) {
					return /^\d+(\.\d+)?$/i.test(value);
				},
				message : '请输入数字，并确保格式正确'
			},
			currency : {// 验证货币
				validator : function(value) {
					return /^\d+(\.\d+)?$/i.test(value);
				},
				message : '货币格式不正确'
			},
			qq : {// 验证QQ,从10000开始
				validator : function(value) {
					return /^[1-9]\d{4,9}$/i.test(value);
				},
				message : 'QQ号码格式不正确'
			},
			integer : {// 验证整数
				validator : function(value) {
					return /^[+]?[1-9]+\d*$/i.test(value);
				},
				message : '请输入整数'
			},
			chinese : {// 验证中文
				validator : function(value) {
					return /^[\u0391-\uFFE5]+$/i.test(value);
				},
				message : '请输入中文'
			},
			chineseAndLength : {// 验证中文及长度
				validator : function(value) {
					var len = $.trim(value).length;
					if (len >= param[0] && len <= param[1]) {
						return /^[\u0391-\uFFE5]+$/i.test(value);
					}
				},
				message : '请输入中文'
			},
			english : {// 验证英语
				validator : function(value) {
					return /^[A-Za-z]+$/i.test(value);
				},
				message : '请输入英文'
			},
			englishAndLength : {// 验证英语及长度
				validator : function(value, param) {
					var len = $.trim(value).length;
					if (len >= param[0] && len <= param[1]) {
						return /^[A-Za-z]+$/i.test(value);
					}
				},
				message : '请输入英文'
			},
			englishUpperCase : {// 验证英语大写
				validator : function(value) {
					return /^[A-Z]+$/.test(value);
				},
				message : '请输入大写英文'
			},
			unnormal : {// 验证是否包含空格和非法字符
				validator : function(value) {
					return /.+/i.test(value);
				},
				message : '输入值不能为空和包含其他非法字符'
			},
			username : {// 验证用户名
				validator : function(value) {
					return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
				},
				message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
			},
			faxno : {// 验证传真
				validator : function(value) {
					return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
				},
				message : '传真号码不正确'
			},
			zip : {// 验证邮政编码
				validator : function(value) {
					return /^[1-9]\d{5}$/i.test(value);
				},
				message : '邮政编码格式不正确'
			},
			ip : {// 验证IP地址
				validator : function(value) {
					return /d+.d+.d+.d+/i.test(value);
				},
				message : 'IP地址格式不正确'
			},
			name : {// 验证姓名，可以是中文或英文
				validator : function(value) {
					return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
				},
				message : '请输入姓名'
			},
			engOrChinese : {// 可以是中文或英文
				validator : function(value) {
					return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
				},
				message : '请输入中文'
			},
			engOrChineseAndLength : {// 可以是中文或英文
				validator : function(value) {
					var len = $.trim(value).length;
					if (len >= param[0] && len <= param[1]) {
						return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
					}
				},
				message : '请输入中文或英文'
			},
			carNo : {
				validator : function(value) {
					return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
				},
				message : '车牌号码无效（例：粤B12350）'
			},
			carenergin : {
				validator : function(value) {
					return /^[a-zA-Z0-9]{16}$/.test(value);
				},
				message : '发动机型号无效(例：FG6H012345654584)'
			},
			same : {
				validator : function(value, param) {
					if ($("#" + param[0]).val() != "" && value != "") {
						return $("#" + param[0]).val() == value;
					} else {
						return true;
					}
				},
				message : '两次输入的密码不一致!'
			}
		});


/*该方法使日期列的显示符合阅读习惯*/
//datagrid中用法：{ field:'StartDate',title:'开始日期', formatter: formatDatebox, width:80}
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt = parseToDate(value);
    return dt.format("yyyy-MM-dd");
}

/*转换日期字符串为带时间的格式*/
function formatDateBoxFull(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt = parseToDate(value);
    return dt.format("yyyy-MM-dd hh:mm:ss");
}

//辅助方法(转换日期)
function parseToDate(value) {
    if (value == null || value == '') {
        return undefined; 
    }

    var dt;
    if (value instanceof Date) {
        dt = value;
    }
    else {
        if (!isNaN(value)) {
            dt = new Date(value);
        }
        else if (value.indexOf('/Date') > -1) {
            value = value.replace(/\/Date\((-?\d+)\)\//, '$1');
            dt = new Date();
            dt.setTime(value);
        } else if (value.indexOf('/') > -1) {
            dt = new Date(Date.parse(value.replace(/-/g, '/')));
        } else {
            dt = new Date(value);
        }
    }
    return dt;
}

//为Date类型拓展一个format方法，用于格式化日期
Date.prototype.format = function (format) //author: meizz 
{
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(),    //day 
        "h+": this.getHours(),   //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
        "S": this.getMilliseconds() //millisecond 
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1,
                (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
                    RegExp.$1.length == 1 ? o[k] :
                        ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};

(function ($) {
	$.fn.my97 = function (options, params) {
		if (typeof options == "string") {
			return $.fn.my97.methods[options](this, params);
		}
		options = options || {};
		if (!WdatePicker) {
			alert("未引入My97js包！");
			return;
		}
		return this.each(function () {
			var data = $.data(this, "my97");
			var newOptions;
			if (data) {
				newOptions = $.extend(data.options, options);
				data.opts = newOptions;
			} else {
				newOptions = $.extend({}, $.fn.my97.defaults, $.fn.my97.parseOptions(this), options);
				$.data(this, "my97", {
					options : newOptions
				});
			}
			$(this).addClass('Wdate').click(function () {
				WdatePicker(newOptions);
			});
		});
	};
	$.fn.my97.methods = {
		setValue : function (target, params) {
			target.val(params);
		},
		getValue : function (target) {
			return target.val();
		},
		clearValue : function (target) {
			target.val('');
		}
	};
	$.fn.my97.parseOptions = function (target) {
		return $.extend({}, $.parser.parseOptions(target, ["el", "vel", "weekMethod", "lang", "skin", "dateFmt", "realDateFmt", "realTimeFmt", "realFullFmt", "minDate", "maxDate", "startDate", {
						doubleCalendar : "boolean",
						enableKeyboard : "boolean",
						enableInputMask : "boolean",
						autoUpdateOnChanged : "boolean",
						firstDayOfWeek : "number",
						isShowWeek : "boolean",
						highLineWeekDay : "boolean",
						isShowClear : "boolean",
						isShowToday : "boolean",
						isShowOthers : "boolean",
						readOnly : "boolean",
						errDealMode : "boolean",
						autoPickDate : "boolean",
						qsEnabled : "boolean",
						autoShowQS : "boolean",
						opposite : "boolean"
					}
				]));
	};
	$.fn.my97.defaults = {
		dateFmt : 'yyyy-MM-dd HH:mm:ss'
	};

	$.parser.plugins.push('my97');
})(jQuery);

//以下拓展是为了datagrid的日期列在编辑状态下显示正确日期
$.extend(
    $.fn.datagrid.defaults.editors, {
        datebox: {
            init: function (container, options) {
                var input = $('<input type="text">').appendTo(container);
                input.datebox(options);
                return input;
            },
            destroy: function (target) {
                $(target).datebox('destroy');
            },
            getValue: function (target) {
                return $(target).datebox('getValue');
            },
            setValue: function (target, value) {
                $(target).datebox('setValue', formatDatebox(value));
            },
            resize: function (target, width) {
                $(target).datebox('resize', width);
            }
        },
        datetimebox:{
            init: function (container, options) {
                var input = $('<input type="text">').appendTo(container);
                input.datetimebox(options);
                return input;
            },
            destroy: function (target) {
                $(target).datetimebox('destroy');
            },
            getValue: function (target) {
                return $(target).datetimebox('getValue');
            },
            setValue: function (target, value) {
                $(target).datetimebox('setValue', formatDateBoxFull(value));
            },
            resize: function (target, width) {
                $(target).datetimebox('resize', width);
            }
        },
        my97DateTime : {
    		init : function(container, options) {
    			var input = $('<input class="Wdate" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',readOnly:true});"  />').appendTo(container);
    			input.my97(options);
    			return input;
    		},
    		getValue : function(target) {
    			return $(target).my97('getValue');
    		},
    		setValue : function(target, value) {
    			$(target).my97('setValue', formatDateBoxFull(value));
    		},
    		resize : function(target, width) {
    			var input = $(target);
    			if ($.boxModel == true) {
    				input.width(width - (input.outerWidth() - input.width()));
    			} else {
    				input.width(width);
    			}
    		}
    	},
        my97Date : {
    		init : function(container, options) {
    			var input = $('<input class="Wdate" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\',readOnly:true});"  />').appendTo(container);
    			input.my97(options);
    			return input;
    		},
    		getValue : function(target) {
    			return $(target).my97('getValue');
    		},
    		setValue : function(target, value) {
    			$(target).my97('setValue', formatDatebox(value));
    		},
    		resize : function(target, width) {
    			var input = $(target);
    			if ($.boxModel == true) {
    				input.width(width - (input.outerWidth() - input.width()));
    			} else {
    				input.width(width);
    			}
    		}
    	}
    });

