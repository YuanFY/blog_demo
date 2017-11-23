/**
 * 自定义弹出框组件
 */
;(function(global, $){
	global.jCustom = {
		info : function (){debugger
			var args = resolveArgs(arguments);
			new Hint().msg(args.title,args.msg).show();
		}
	};
	/**
	 * 消息提示组件<br>
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
		 * 显示消息
		 * @param title 标题
		 * @param msg 具体消息
		 */
		msg : function (title, msg){
			var opts = this.options;
			if (title || msg) {
				if (!su.isNotNull(title)) {
					title = "系统提示";
				}
				this.$el.append('<div class="alert '+opts.className+'" style="max-width:30%;margin:3px auto;padding:10px;"><strong>'+title+'</strong>：'+msg+'</div>');
			}
			return this;
		},
		show : function (){
			this.$el.appendTo('body');
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
	
	global.jc = global.jCustom;
})(typeof window !== "undefined" ? window : this,jQuery);