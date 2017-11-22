(function( global,$) {
	var ids = 0; //公共序号
	global.jForest = {
		_modals:[],
		getId:function(){return (ids++);},
		getAjax:function(options){return new Ajax(options);},
		request:function(url){if(url){global.document.location.href=url;}},
		submit:function(){
			var formEl = arguments[0],opts,callback;
			if(arguments.length == 3){opts = arguments[1];callback = arguments[2];
			}else{callback = arguments[1];}
			if(jf.validity(formEl)){
				var url = $(formEl).attr('action');
				var method = $(formEl).attr('method') || 'post';
				if(method == 'post'){
					opts =$.extend({
						url:url,
						data:$(formEl).serializeArray(),
						async:true,
						type: "POST",
						dataType:'json',
						success:callback
					},opts||{})
					$.ajax(opts);
				}else{
					$.getJSON(url,$(formEl).serializeArray(),callback);
				}
				return true;
			}
			return false;
		},
		isNotNull:function(value){return value != null && value != undefined},
		toggleClass:function(el,c1,c2){
			if($(el).hasClass(c1)){
				$(el).removeClass(c1);$(el).addClass(c2);
			}else{
				$(el).removeClass(c2);$(el).addClass(c1);
			}
		},
		replaceClass:function(el,oldClass,newClass){$(el).removeClass(oldClass);$(el).addClass(newClass);},
		alert:function(title,msg,click,open){
			var installGuide = (title=='安装向导提示');
			jf.alertFlag=false,click=$.isFunction(click)?click:function(flag){};
			jf.alertModal = null;
			if(!jf.alertModal){
				jf.alertModal=$('<div class="modal fade" id="jforestModal"  tabindex="-1"><div class="modal-dialog" style="width:400px;top:150px;">'
				+'<div class="modal-content"><div class="modal-header">'+
				(installGuide?'':'<button type="button" class="close" data-dismiss="modal">&times;</button>')+
				'<h4 class="modal-title pull-left">'+title+'</h4></div><div class="modal-body" style="padding:20px;text-align:left">'+msg+'</div>'
				+'<div class="modal-footer"><button type="button" class="btn btn-info btn-sm">' +(installGuide?'安装':'确定') +
				'</button><button type="button" class="btn btn-default btn-sm" data-dismiss="modal">'+(installGuide?'升级':'取消') +'</button>'
				+'</div></div></div></div>');
			jf.alertModal.find('.btn-info').click(function(){jf.alertFlag=true,jf.alertModal.modal('hide');
				if(!(typeof(open) == "undefined")){
					window.open(open);
				}
			});
			}
			else{
				jf.alertModal.find('.modal-title').html(title);jf.alertModal.find('.modal-body').html(msg);
				jf.alertModal.find('.btn-info').click(function(){jf.alertFlag=true,jf.alertModal.modal('hide');
				if(!(typeof(open) == "undefined")){
					window.open(open);
				}
				});
			}
			jf.alertModal.one('hidden.bs.modal',function(){click.call(this,jf.alertFlag)});jf.alertModal.modal('show');
		},
		/**
		 * 只需要提示框（将确定和取消按钮  修改为关闭按钮）
		 */
		alert1:function(title,msg,click,open){
			jf.alertFlag=false,click=$.isFunction(click)?click:function(flag){};


			if(!jf.alertModal1){
				jf.alertModal1=$('<div class="modal fade" tabindex="-1"><div class="modal-dialog" style="width:400px;top:150px;">'

				+'<div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button>'
				+'<h4 class="modal-title">'+title+'</h4></div><div class="modal-body" style="padding:20px;text-align:center">'+msg+'</div>'
				+'<div class="modal-footer"><button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>'
				+'</div></div></div></div>');
				jf.alertModal1.find('.btn-info').click(function(){jf.alertFlag=true,jf.alertModal1.modal('hide');
					if(!(typeof(open) == "undefined")){
						window.open(open);
					}
				});
			}
			else{
				jf.alertModal1.find('.modal-title').html(title);jf.alertModal1.find('.modal-body').html(msg);
				jf.alertModal1.find('.btn-info').click(function(){jf.alertFlag=true,jf.alertModal1.modal('hide');
					if(!(typeof(open) == "undefined")){
						window.open(open);
					}
				});
			}
			jf.alertModal1.one('hidden.bs.modal',function(){click.call(this,jf.alertFlag)});jf.alertModal1.modal('show');
		},
		/*参数：title，msg，time，hideCallback*/
		error:function(){
			var args = _args(arguments);
			jf.hint({className:'alert-danger'}).msg(args.title,args.msg).showError(args.time,args.hideCallback);
		},
		warn:function(){
			var args = _args(arguments);
			jf.hint({className:'alert-warning'}).msg(args.title,args.msg).show(args.time,args.hideCallback);
		},
		/*参数：title，msg，time，hideCallback*/
		info:function(){
			var args = _args(arguments);
			jf.hint({className:'alert-info',type:args.title,bottom:args.bottom}).msg(args.title,args.msg).show(args.time,args.hideCallback);
		},
		hint:function(options){
			var title,msg;
			if(arguments.length > 0 && typeof(arguments[0]) === "object"){
				title = options.title;delete options.title;
				msg = options.msg;delete options.msg;
			} 
			return new Hint(options).msg(title,msg);
		},
		modal:function(title,url,data,callback){
			if(typeof(arguments[0]) === "object"){
				var opts = arguments[0];
				var modal=jf._modals.shift();if(!modal){modal=new Modal({width:opts.width});}
				modal.load(opts.title,opts.url,opts.data,opts.callback);
			}else{
				if($.isFunction(data)){callback=data;data={};}
				var modal=jf._modals.shift();if(!modal){modal=new Modal();}
				modal.load(title,url,data,callback);
			}
			
		},
		modalCallback:function(el,result){
			var modalEl=$(el).parents('.modal:first'),callback=modalEl.data('callback');
			if($.isFunction(callback)){callback.apply(modalEl,[result]);}
		},
		mask:function(){
			var el='body',click;
			if(arguments.length==2){el=arguments[0];click=arguments[1];}
			if(arguments.length==1){if($.isFunction(el=arguments[0])){click=arguments[0];}else{el=arguments[0];}}
			var $el = $('<div class="modal-backdrop fade in" style="z-index:1050;"></div>').appendTo(el);
			if($.isFunction(click)){$el.click(function(e){click.apply($el,[e])});}return $el;
		},
		validity:function(el){
			if(!el){el = 'body';}
			var flag = true,hint=jf.hint(),showHint = false;
			//data-minLen data-maxLen data-pattern data-msg
			//required,ss,number,email,phone
			$(el).find('[data-toggle="valid"]').each(function(){
				var isOk = true;
				var val = $(this).val();
				var name = $(this).attr('data-name') || $(this).attr('name');
				var msg = $(this).attr('data-msg') || '';
				var minLen = $(this).attr('data-minLen');
				var maxLen = $(this).attr('data-maxLen');
				var pattern = $(this).attr('data-pattern');
				if(minLen && (!val || minLen>val.length)){
					isOk = false;msg = name+'长度必须大于等于'+minLen;
				}else if(maxLen && (val && maxLen<val.length)){
					isOk = false;msg = name+'长度必须小于等于'+maxLen; 
				}else if(pattern){
					var arrayPattern = pattern.split(',');
					for(var i=0;i<arrayPattern.length && isOk;i++){
						try{
							pattern = arrayPattern[i];
							if(pattern == 'required'){
								if(!val){if(!msg){msg = '请填写'+name} isOk = false;break;}
							}else if(pattern == 'number'){
								if(val && !/^[0-9]+$/.test(val)){
									if(!msg){msg = name+'必须是数字'} isOk = false;break;}
							}else if(pattern == 'integer'){
								if(val && !/^[0-9]+$/.test(val)){
									if(!msg){msg = name+'必须是整数'} isOk = false;break;}
							}else if(pattern == 'email'){
								if(val && !/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(val)){
									if(!msg){msg = '请输入正确的电子邮箱'} isOk = false;break;}
							}else if(pattern == 'phone'){ 
								if(val && !/^[0-9]{11}$/.test(val)){
									if(!msg){msg = '请输入正确的手机号'} isOk = false;break;}
							}else if(pattern == 'phone1'){ 
								if(val && !(/^1[3|4|5|8][0-9]\d{8}$/.test(val))){
									msg = '请输入正确的手机号'; isOk = false;break;}
							}else if(pattern == 'url'){ 
								var RegUrl = new RegExp(); 
								RegUrl.compile("^((https|http|ftp|rtsp|mms)?://)"
									+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
									+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
									+ "|" // 允许IP和DOMAIN（域名）
									+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
									+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
									+ "[a-z]{2,6})" // first level domain- .com or .museum
									+ "(:[0-9]{1,4})?" // 端口- :80
									+ "((/?)|" // a slash isn't required if there is no file name
									+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
								if(val && !RegUrl.test(val)){
									if(!msg){msg = '请输入正确的URL地址'} isOk = false;break;}
							}else if(pattern == 'ip'){ 
								//if(val && !/^[1-2]?[0-9]?[0-9]{1}\.[1-2]?[0-9]?[0-9]{1}\.[1-2]?[0-9]?[0-9]{1}\.[1-2]?[0-9]?[0-9]{1}$/.test(val)){
								if(val && !/^((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))$/.test(val)){
									if(!msg){msg = '请输入正确的IP地址'} isOk = false;break;}
							}/*else if(pattern == 'ss' && (!val || /^[0-9,a-Z]+$/.test(val))){
								if(msg){jf.error(msg)}else{jf.error(name+'只能输入字母或数字')}
							}*/
							else if(val && !eval(pattern).test(val)){
								if(!msg){msg = name+'格式不正确'} isOk = false; break;
							}
						}catch(error){}
					}
				}
				var id = $(this).attr('data-msgTo');
				if(isOk == false){
					flag = false;
					if('next' == id){$(this).next().html(msg);
					}else if(id){$(id).html(msg);
					}else {hint.msg(msg);showHint = true;}
				}else if(id){
					if('next' == id){$(this).next().html('');}else{$(id).html('');}
				}
			});
			if(showHint == true){hint.show();}
			return flag;
		},
		//美化标签
		nice:function(){
			// <label class="nice-checkbox js"><input type="checkbox"></label>
			$('.nice-checkbox input[type="checkbox"]').each(function(i,el){
				if(el.checked){$(el).parent().addClass('on');}
			});
			$('.nice-checkbox.js').mousedown(function(event){
				$(this).toggleClass('on');
			});
		}
	}
	
	var _args = function(args){
		var title,msg,time,hideCallback,bottom;
		if(args.length === 1){
			msg = args[0];
		}else if(args.length === 2){
			if($.isFunction(args[1])){msg = args[0];hideCallback = args[1];
			}else if($.isNumeric(args[1])){msg = args[0];time = args[1];
			}else{title = args[0];msg = args[1];}
		}else if(args.length === 3){
			if($.isFunction(args[2])){hideCallback = args[2];
				if($.isNumeric(args[1])){msg = args[0];time = args[1]
				}else{title = args[0];msg = args[1];}
			}else if($.isNumeric(args[2])){
				title = args[0];msg = args[1];time = args[2];
			}else{title = args[0];msg = args[1];}
		}else if(args.length ===4){
		    title = args[0];msg = args[1];time = args[2];hideCallback = args[3];
		}else{title = args[0];msg = args[1];time = args[2];hideCallback = args[3];bottom = args[4];}
		return {title:title,msg:msg,time:time,hideCallback:hideCallback,bottom:bottom}
	}
	
	var Modal=function(options){
		this.options = $.extend({
			html:'',
			width:600
		},options || {});
		var opts = this.options;
		this.$el=$('<div class="modal fade"  id="jforestModal" tabindex="-1"><div class="modal-dialog" style="width:'+opts.width+'px;"><div class="modal-content">'
			+'<div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button>'
			+'<h4 class="modal-title  pull-left"></h4></div><div class="modal-body">'+opts.html+'</div></div></div></div>');
		var modal=this;this.$el.on('hidden.bs.modal',function(e){jf._modals.push(modal);}).appendTo($('body'));
	}
	
	Modal.prototype = {
		load:function(title,url,data,callback){
			if($.isFunction(callback)){this.$el.data('callback',callback);}
			this.$el.find('.modal-title').html(title);var modal=this;
			this.$el.find('.modal-body').load(url,data,function(){
				modal.$el.modal('show');
			});
			//清除modal中的内容
			$(this.$el).bind("hidden",function(){
				$('.loading').hide();
				$(this).html("");
			});
		}
	}
	
	var Hint = function(options){
		this.options = $.extend({
			intervalTime:null,
			className:'alert-danger',
			zIndex:1060,
			hideCallback:function(){}
		},options)
		var my=this;
		if (options!= null && options.type != null && options.type == "right_bottom"){
		    if (!options.bottom){options.bottom = 25;}
			this.$el = $('<div class="made fade in" style="opacity: 1;display:block;right: 10px;bottom: '+options.bottom+'px;position: fixed;z-index: 9998;"></div>');
		}else {
			this.$el = $('<div class="modal fade in" style="display:block;padding-top:'+(document.documentElement.scrollTop+document.body.scrollTop - 50)+'px;z-index:'+this.options.zIndex+';"></div>')
			.click(function(){my.hide.apply(my)});
		}
	}

	Hint.prototype = {
		msg:function(title,msg){
			var opts = this.options;
			if (title == "right_bottom"){
				var my=this;
				this.$el.append('<div class="alert '+opts.className+'" style="width:300px;margin: 5px 0px; background:rgba(0, 0, 0, 0.80); color: white;">'+
						'<a class="close pull-right" onclick="$(this).parent().parent().remove()" style="color:white">×</a>' +
						'<strong>【任务】</strong><br/><a href="#/monitor/maintenance/task/list.html" onclick="clearLiActive(this)" style="word-break: break-all;color:#0088cc">'+msg+'</a></div>');
			} else {
				if(title || msg){
					if(!msg){msg=title,title="系统提示";}else if(!title){title="系统提示";}
					this.$el.append('<div class="alert '+opts.className+'" style="max-width:30%;margin:3px auto;padding:10px;"><strong>'+title+'</strong>：'+msg+'</div>');
				}
			}
			return this;
		},
		/*
		* 参数：title hideCallback
		*/
		show:function(){//一般提示信息 按照停留时间停留，如果没有指定时间，就默认为3s
			var args = arguments;
			if(args.length === 1 && $.isFunction(args[0])){
				args[1] = args[0]; args[0] = null;
			}
			var my=this,opts=this.options,time = args[0] || opts.intervalTime,hideCallback = args[1];
			this.$mask = jf.mask();
			this.$el.appendTo('body');
			if (time == null || time == '') {
				time = 3000;
			}
			if(time){
				//time=1000;
				this.timer = setInterval(function(){
					my.hide.apply(my,[hideCallback]);
				},time);
			}else if($.isFunction(hideCallback)){
				opts.hideCallback = hideCallback;
			}
			if($.isFunction(hideCallback)){
				opts.hideCallback = hideCallback;
			}
		},
		showError:function(){//错误或警告信息停留3秒后消失
			var args = arguments;
			if(args.length === 1 && $.isFunction(args[0])){
				args[1] = args[0]; args[0] = null;
			}
			var my=this,opts=this.options,time = args[0] || opts.intervalTime,hideCallback = args[1];
			this.$mask = jf.mask();
			this.$el.appendTo('body');
			/*if(time){
				this.timer = setInterval(function(){
					my.hide.apply(my,[hideCallback]);
				},3000);
			}else if($.isFunction(hideCallback)){
				opts.hideCallback = hideCallback;
			}*/
			this.timer = setInterval(function(){
				my.hide.apply(my,[hideCallback]);
			},3000);
			if($.isFunction(hideCallback)){
				opts.hideCallback = hideCallback;
			}
		},
		hide:function(hideCallback){
			hideCallback = hideCallback || this.options.hideCallback;
			if(this.timer){
				clearInterval(this.timer);
				delete this.timer;
			}
			this.$mask.remove();
			this.$el.remove();
			hideCallback.apply();
		}
	}
	
	var Ajax = function(options){
		this.options = $.extend({
			bindObj:null,	//绑定的对象
			method:'post',
			dataType:'json',
			url:'',
			param:null,  //方法或对象
			callback:function(){}
		},options);
		this.loadedParam = {}; //上一次加载时用的参数
	}
	
	Ajax.prototype = {
		getParam:function(param){
			var opts=this.options;
			if($.isFunction(opts.param)){
				return opts.param.apply(opts.bindObj||this,[this.loadedParam,param]);
			}else if(param){ return param;
			}else{return this.loadedParam;}
		},
		getCallback:function(callback){
			if($.isFunction(callback)){return callback;}
			else if($.isFunction(this.options.callback)){return this.options.callback;}
			else{return function(result){};}
		},
		isValid:function(){return this.options.url?true:false;},
		load:function(param,callback){
			if(!callback && $.isFunction(param)){callback=param;param=null;}
			var obj = this.options.bindObj||{},opts=this.options;
			this.loadedParam = this.getParam(param);callback = this.getCallback(callback);
			$.ajax({url:opts.url,data:this.loadedParam,dataType:opts.dataType,type:opts.method,
				success:function(result){callback.apply(obj,[result||{}]);}})
		}
	}
	
	global.jf = global.jForest;
})(typeof window !== "undefined" ? window : this,jQuery);