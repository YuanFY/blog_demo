(function($) {
	$.PaginationCalculator = function(maxentries, opts) {
		this.maxentries = maxentries == 0 ? 1 : maxentries;
		this.opts = opts;
	};
	$.extend($.PaginationCalculator.prototype, {
		numPages: function() {
			return Math.ceil(this.maxentries / this.opts.items_per_page);
		},
		getInterval: function(current_page) {
			var ne_half = Math.floor(this.opts.num_display_entries / 2);
			var np = this.numPages();
			var upper_limit = np - this.opts.num_display_entries;
			var start = current_page > ne_half ? Math.max(Math.min(current_page - ne_half, upper_limit), 0) : 0;
			var end = current_page > ne_half ? Math.min(current_page + ne_half + (this.opts.num_display_entries % 2), np) : Math.min(this.opts.num_display_entries, np);
			return {
				start: start,
				end: end
			};
		}
	});
	$.PaginationRenderers = {};
	$.PaginationRenderers.defaultRenderer = function(maxentries, opts) {
		this.maxentries = maxentries;
		this.opts = opts;
		this.pc = new $.PaginationCalculator(maxentries, opts);
	};
	$.extend($.PaginationRenderers.defaultRenderer.prototype, {
		createLink: function(page_id, current_page, appendopts) {
			var lnk, np = this.pc.numPages();
			page_id = page_id < 0 ? 0 : (page_id < np ? page_id: np - 1);
			appendopts = $.extend({
				text: page_id + 1,
				classes: ""
			},
			appendopts || {});
			if (page_id == current_page) {
				if (isNaN(appendopts.text)) {
					lnk = $("<li class='disabled'><a>" + appendopts.text + "</a></li>");
				} else {
					lnk = $("<li class='active'><a>" + appendopts.text + "</a></li>");
				}
			} else {
				lnk = $("<li><a href='" + this.opts.link_to.replace(/__id__/, page_id) + "'>" + appendopts.text + "</a></li>");
			}
			if (appendopts.classes) {
				lnk.addClass(appendopts.classes);
			}
			lnk.data("page_id", page_id);
			return lnk;
		},
		appendRange: function(container, current_page, start, end, opts) {
			var i;
			for (i = start; i < end; i++) {
				this.createLink(i, current_page, opts).appendTo(container);
			}
		},
		getLinks: function(current_page, eventHandler,pressHandler,buttonHandler) {
			current_page = parseInt(current_page);
			var begin, end, interval = this.pc.getInterval(current_page),
			np = this.pc.numPages(),
			fragment = $("<ul></ul>");
			fragment.append(this.createLink(0, current_page, {
					text: "首页",
					classes: "prev"
				}));
			if (this.opts.prev_text && (current_page > 0 || this.opts.prev_show_always)) {
				fragment.append(this.createLink(current_page - 1, current_page, {
					text: this.opts.prev_text,
					classes: "sp"
				}));
			}
			if (interval.start > 0 && this.opts.num_edge_entries > 0) {
				end = Math.min(this.opts.num_edge_entries, interval.start);
				this.appendRange(fragment, current_page, 0, end, {
					classes: "sp"
				});
				if (this.opts.num_edge_entries < interval.start && this.opts.ellipse_text) {
					$("<li><a>" + this.opts.ellipse_text + "</a></li>").appendTo(fragment);
				}
			}
			this.appendRange(fragment, current_page, interval.start, interval.end);
			if (interval.end < np && this.opts.num_edge_entries > 0) {
				if (np - this.opts.num_edge_entries > interval.end && this.opts.ellipse_text) {
					$("<li><a>" + this.opts.ellipse_text + "</a></li>").appendTo(fragment);
				}
				begin = Math.max(np - this.opts.num_edge_entries, interval.end);
				this.appendRange(fragment, current_page, begin, np, {
					classes: "ep"
				});
			}
			if (this.opts.next_text && (current_page < np - 1 || this.opts.next_show_always)) {
				fragment.append(this.createLink(current_page + 1, current_page, {
					text: this.opts.next_text,
					classes: "sp"
				}));
			}

			var endPage  = Math.ceil(this.maxentries/this.opts.items_per_page);

			if(current_page != 0 && current_page >= this.maxentries/this.opts.items_per_page ){
				var goto_page_index = Math.floor(this.maxentries/this.opts.items_per_page) - 1 < 0 ? 0 : (Math.floor(this.maxentries/this.opts.items_per_page) - 1);
				this.opts.callback(goto_page_index);
			}
			

			fragment.append(this.createLink(endPage, current_page, {
					text: "末页",
					classes: "next"
				}));

				var searchNumber = this.opts.search_number;
				if(endPage <= 1){
					fragment.append("<div class=\"input-append goto-page\"> <input value=\'"+searchNumber+"\' class=\"pagination_input\" type=\"text\" onkeyup=\"this.value=this.value.replace(\/\\D\/g,\'\')\" onafterpaste=\"this.value=this.value.replace(\/\\D\/g,\'\')\"> <span disabled=\"disabled\"class=\"page-btn\" type=\"button\">跳转</span></div> ");
				}else{
					fragment.append("<div class=\"input-append goto-page\"> <input value=\'"+searchNumber+"\' class=\"pagination_input\" type=\"text\" onkeyup=\"this.value=this.value.replace(\/\\D\/g,\'\')\" onafterpaste=\"this.value=this.value.replace(\/\\D\/g,\'\')\"> <span class=\"page-btn\" type=\"button\">跳转</span></div> ");
					$("span:not(.muted)",fragment).bind("click",buttonHandler);
				}

				fragment.append("<span class=\"muted\" >共"+endPage+"页，"+this.maxentries+"条记录</span> ");
				$("li:not(.disabled, .active) a", fragment).click(eventHandler);
				return fragment;
		}
	});
	$.fn.pagination = function(maxentries, opts) {
		opts = $.extend({
			items_per_page: 12,
			num_display_entries: 10,
			current_page: 0,
			search_number:getPaginSearchInfo(this.attr("id")),
			num_edge_entries: 0,
			link_to: "javascript:;",
			prev_text: "\<",
			next_text: "\>",
			ellipse_text: "...",
			prev_show_always: true,
			next_show_always: true,
			renderer: "defaultRenderer",
			load_first_page: false,
			callback: function() {
				return false;
			}
		},

		opts || {});
		var containers = this, renderer, links, current_page;
		function paginationClickHandler(evt) {
			opts.search_number = ""; 
			window.jqueryclick = true;
			var links, new_current_page = $(evt.target).parent().data("page_id"),
			continuePropagation = selectPage(new_current_page);
			if (!continuePropagation) {
				evt.stopPropagation();
			}
			return continuePropagation;
		}
		function paginationPressHandler(evt) {
			if(evt.which == 13){
				var links, new_current_page = $(evt.target).val()-1;
				if(isNaN(new_current_page)) return ;
				var endPage  = Math.ceil(maxentries/opts.items_per_page);
				if(new_current_page < 0 || new_current_page > endPage) return;
				var continuePropagation = selectPage(new_current_page);
				if (!continuePropagation) {
					evt.stopPropagation();
				}
				return continuePropagation;
			}
		}
		function paginationButtonHandler(evt) {
			opts.search_number = ""; 
			var links, new_current_page = $(evt.target).parent().find("input").get(0).value-1;
			if(isNaN(new_current_page)){
				$(evt.target).parent().children(".pagination_input").css("border-color","#E9332D");
				$(evt.target).parent().children(".pagination_input").css("box-shadow","0 0 6px #F8B9B7");
				
				return ;
			} 
			var endPage  = Math.ceil(maxentries/opts.items_per_page);
			if(new_current_page < 0 || new_current_page >= endPage){
				$(evt.target).parent().children(".pagination_input").css("border-color","#E9332D");
				$(evt.target).parent().children(".pagination_input").css("box-shadow","0 0 6px #F8B9B7");
				return ;
			} 
			var continuePropagation = selectPage(new_current_page);
			if (!continuePropagation) {
				evt.stopPropagation();
			}
			return continuePropagation;
			
		}
		function selectPage(new_current_page) {
			containers.data("current_page", new_current_page);
			links = renderer.getLinks(new_current_page, paginationClickHandler,paginationPressHandler,paginationButtonHandler);
			containers.empty();
			links.appendTo(containers);
			var continuePropagation = opts.callback(new_current_page, containers);
			return continuePropagation;
		}
		function getPaginSearchInfo(id){
		    var val = $("#"+id+" .goto-page input").val()
		    if(!isNaN(val)){
		        return val;
		    }
		    else{
		        return "";
		    }
		}
		current_page = opts.current_page;
		containers.data("current_page", current_page);
		maxentries = (!maxentries || maxentries < 0) ? 0 : maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0) ? 1 : opts.items_per_page;
		if (!$.PaginationRenderers[opts.renderer]) {
			throw new ReferenceError("Pagination renderer '" + opts.renderer + "' was not found in jQuery.PaginationRenderers object.");
		}
		renderer = new $.PaginationRenderers[opts.renderer](maxentries, opts);
		var pc = new $.PaginationCalculator(maxentries, opts);
		var np = pc.numPages();
		containers.bind("setPage", {
			numPages: np
		},
		function(evt, page_id) {
			if (page_id >= 0 && page_id < evt.data.numPages) {
				selectPage(page_id);
				return false;
			}
		});
		containers.bind("prevPage",
		function(evt) {
			var current_page = $(this).data("current_page");
			if (current_page > 0) {
				selectPage(current_page - 1);
			}
			return false;
		});
		containers.bind("nextPage", {
			numPages: np
		},
		function(evt) {
			var current_page = $(this).data("current_page");
			if (current_page < evt.data.numPages - 1) {
				selectPage(current_page + 1);
			}
			return false;
		});
		containers.bind("currentPage",
		function(evt) {
			var current_page = $(this).data("current_page");
			selectPage(current_page);
			return false;
		});
		links = renderer.getLinks(current_page, paginationClickHandler,paginationPressHandler,paginationButtonHandler);
		containers.empty();
		links.appendTo(containers);
		if (opts.load_first_page) {
			opts.callback(current_page, containers);
		}
	};
})(jQuery);


var jquery_pagination_demo_data = [];
for(var i = 0; i < 100; i++){
  var date = new Date();
  jquery_pagination_demo_data.push({
    number: i + 1,
    _date: date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds()
  });
}