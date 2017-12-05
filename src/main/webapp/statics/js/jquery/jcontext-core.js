/**
 * 自定义弹出框组件
 */
;(function(global, $){
	global.jContext = {
		//------------------------------提示组件相关方法------------------------------------
		/**
		 * 正常信息提示函数
		 * title, msg, time, hideCallback;
		 */
		info : function (){
			var args = resolveArgs(arguments);
			new Hint(args).msg(args.title,args.msg).show();
		},
		/**
		 * 警告信息提示函数
		 * title, msg, time, hideCallback;
		 */
		warn : function (){
			var args = resolveArgs(arguments);
			args.className = "alert-warning";
			new Hint(args).msg(args.title,args.msg).show();
		},
		/**
		 * 错误信息提示函数
		 * title, msg, time, hideCallback;
		 */
		error : function (){
			var args = resolveArgs(arguments);
			args.className = "alert-danger";
			new Hint(args).msg(args.title,args.msg).show();
		},
		/**
		 * 遮罩层函数
		 */
		mask : function(){
			var el = "body";
			var $el = $('<div class="modal-backdrop fade in" style="z-index:1050;"></div>').appendTo(el);
			return $el;
		},
		/**
		 * 确认框（形式上就是modal）
		 * title标题，msg 主体内容，click 点击确认处理函数
		 */
		confirm : function (title, msg, click){
			//初始化处理
			click=$.isFunction(click)?click:function(flag){};
			jc.alertModal = $('<div class="modal fade" id="jcontextModal" tabindex="-1" role="dialog">'
					+ '<div class="modal-dialog" style="width:400px;">'
					+ '<div class="modal-content"><div class="modal-header">'
					+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
					+ '<h4 class="modal-title pull-left">' + title + '</h4></div>'
					+ '<div class="modal-body" style="padding:20px;text-align:left">' + msg + '</div>'
					+ '<div class="modal-footer"><button type="button" class="btn btn-info btn-sm">确定</button>'
					+ '<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>'
					+ '</div></div></div></div>');
			//为确定按钮添加点击事件，然后回调click函数
			jc.alertModal.find('.btn-info').click(function(){
				jc.alertModal.modal('hide');
				click.call(this, true);//回调
			});
			//最后一步显示摸态框
			jc.alertModal.modal("show");
		},
		/**
		 * 单纯alert提示框（形式上也是modal）
		 * title标题，msg 主体内容，click 点击确认处理函数
		 */
		alert : function (title, msg){
			//初始化处理
			jc.alertModal = $('<div class="modal fade" id="jcontextModal" tabindex="-1" role="dialog">'
					+ '<div class="modal-dialog" style="width:400px;">'
					+ '<div class="modal-content"><div class="modal-header">'
					+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
					+ '<h4 class="modal-title pull-left">' + title + '</h4></div>'
					+ '<div class="modal-body" style="padding:20px;text-align:left">' + msg + '</div>'
					+ '<div class="modal-footer"><button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>'
					+ '</div></div></div></div>');
			//最后一步显示摸态框
			jc.alertModal.modal("show");
		},
		//------------------------------ajax相关方法------------------------------------
		get : function(options){
			options.type = 'GET';
			return new Ajax(options).load();
		},
		post : function(options){
			return new Ajax(options).load();
		},
		sync: function(options){
			options.async = false;
			return new Ajax(options).syncLoad();
		},
	};
	/*********************************************封装ajax组件**************************************************************/
	var Ajax = function (options){
		this.options = $.extend({
			url : '',
		    data : null,
		    type : 'post',
		    async : true,
		    dataType: "json",
		    callback:function(){},
		    error : function(){
		    	jc.error("系统异常，请联系管理员");
		    }
		}, options);
	}
	Ajax.prototype = {
		load : function(){
			var successMsg = this.options.successMsg, errorMsg = this.options.successMsg,callback = this.options.callback;
			if (this.options.success == null) {
				this.options.success = function(result){
					var msg = null;
			        if (result.error == 1) {
			        	msg = (successMsg != null ? successMsg : result.msg);
			        	jc.info(msg);
			        }else if (result.error == 0) {
			        	msg = (errorMsg != null ? errorMsg : result.msg);
			        	jc.error(msg);
			        }
				}
			}
			$.ajax(this.options);
		},
		syncLoad : function(){
			var resultData = null;
			this.options.success = function(result){
				resultData = result;
			}
			$.ajax(this.options);
			return resultData;
		}
	}
	/*********************************************消息提示组件**************************************************************/
	/**
	 * 消息提示组件
	 * 功能：继承boostrap modal的特性(class="modal fade in")<br>
	 * 特点：指定时间消失、点击也会消失<br>
	 * time: 显示时间,默认为3s<br>
	 * className: class对象,默认为alert-info<br>
	 * location: 显示位置，默认为center 正中央<br>
	 * zIndex:堆叠顺序,默认为1060<br>
	 * hideCallback:回调函数，默认为function(){}<br>
	 */
	var Hint = function (options){
		//合并参数
		var defults = {
			time : 3000,
			className : "alert-info",
			location : "center",
			zIndex : 1060,
			hideCallback : function(){}
		};
		this.options = $.extend(defults, options);
		if (this.options.location == "center") {
			this.$el = $('<div class="modal fade in" style="display:block;padding-top:'+(document.documentElement.scrollTop+document.body.scrollTop - 50)+'px;z-index:'+this.options.zIndex+';"></div>')
		}
	}
	//添加方法
	Hint.prototype = {
		/**
		 * 添加消息
		 * @param title 标题
		 * @param msg 具体消息
		 */
		msg : function (title, msg){
			var opts = this.options;
			var my = this;
			if (title || msg) {
				if (!su.isNotNull(title)) {
					title = "系统提示";
				}
				this.$el.append('<div class="alert '+opts.className+'" style="max-width:30%;margin:3px auto;padding:10px;"><strong>'+title+'</strong>：'+msg+'</div>')
				.click(function (){my.hide.apply(my)});
			}
			return this;
		},
		//显示信息
		show : function (){
			var my = this;
			//添加遮罩层
			this.$mask = jc.mask();
			//将信息添加至body
			this.$el.appendTo('body');
			//固定时间隐藏
			if (this.options.time){
				this.timer = setInterval(function(){
					my.hide.apply(my);
				}, this.options.time);
			}
		},
		/**
		 * 隐藏hint信息
		 */
		hide : function (){
			if (this.timer) {
				clearInterval(this.timer);
				delete this.timer;
			}
			this.$mask.remove();
			this.$el.remove();
			this.options.hideCallback.apply();
		}
	}
	
	/**
	 * 解析参数：<br>
	 * 1、当只有一个参数时，那么就是msg
	 * 2、当有两个参数时，其中一个要赋值给msg,然后在判断另一个
	 * 3、当有三个参数时，判断第三个参数时什么，然后跟第二步一样
	 * 4、当有四个参数时，按照顺序返回。
	 */
	var resolveArgs = function (args) {
		var title, msg, time, hideCallback;
		if (args.length == 1) {
			msg = args[0];
		}else if (args.length == 2) {
			if($.isFunction(args[1])) {
				msg = args[0]; hideCallback = args[1];
			}else if ($.isNumeric(args[1])){
				msg = args[0]; time = args[1];
			}else {//顺序返回
				title = args[0]; msg = args[1];
			}
		} else if (args.length == 3){
			if ($.isFunction(args[2])) {
				hideCallback = args[2];
				if ($.isNumeric(args[1])) {
					msg = args[0];time = args[1]
				} else {//顺序返回
					title = args[0]; msg = args[1];
				}
			}else if($.isNumeric(args[2])){//顺序返回
				title = args[0]; msg = args[1]; time = args[2];
			}else{//顺序返回
				title = args[0]; msg = args[1];
			}
		}else if(args.length ===4){
			title = args[0];msg = args[1];time = args[2];hideCallback = args[3];
		}
		return {title:title,msg:msg,time:time,hideCallback:hideCallback};
	}
	//全局定义
	global.jc = global.jContext;
})(typeof window !== "undefined" ? window : this,jQuery);