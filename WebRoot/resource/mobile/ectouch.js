// JavaScript Document

/*
* 公共部分
*/

/*返回上一页*/  
function return_prepage(){  
	history.back()
}

/*头部搜索点击关闭或者弹出搜索框*/  
function showSearch( ){
	document.getElementById("search_box").style.display="block";
}
function closeSearch(){
	document.getElementById("search_box").style.display="none";
}

/* 搜索验证 */
function check(Id){
	var strings = document.getElementById(Id).value;
	if(strings.replace(/(^\s*)|(\s*$)/g, "").length == 0){
		return false;
	}
	return true;
}

/*
* 首页部分
*/
//轮播效果开始
function createPicMove(a, b, c) {
    var g = function(j) {
        return "string" == typeof j ? document.getElementById(j) : j
    };
    var d = function(j, l) {
        for (var k in l) {
            j[k] = l[k]
        }
        return j
    };
    var f = function(j) {
        return j.currentStyle || document.defaultView.getComputedStyle(j, null)
    };
    var i = function(l, j) {
        var k = Array.prototype.slice.call(arguments).slice(2);
        return function() {
            return j.apply(l, k.concat(Array.prototype.slice.call(arguments)))
        }
    };
    var e = {
        Quart: {
            easeOut: function(k, j, m, l) {
                return - m * ((k = k / l - 1) * k * k * k - 1) + j
            }
        },
        Back: {
            easeOut: function(k, j, n, m, l) {
                if (l == undefined) {
                    l = 1.70158
                }
                return n * ((k = k / m - 1) * k * ((l + 1) * k + l) + 1) + j
            }
        },
        Bounce: {
            easeOut: function(k, j, m, l) {
                if ((k /= l) < (1 / 2.75)) {
                    return m * (7.5625 * k * k) + j
                } else {
                    if (k < (2 / 2.75)) {
                        return m * (7.5625 * (k -= (1.5 / 2.75)) * k + 0.75) + j
                    } else {
                        if (k < (2.5 / 2.75)) {
                            return m * (7.5625 * (k -= (2.25 / 2.75)) * k + 0.9375) + j
                        } else {
                            return m * (7.5625 * (k -= (2.625 / 2.75)) * k + 0.984375) + j
                        }
                    }
                }
            }
        }
    };
    var h = function(k, n, m, l) {
        this._slider = g(n);
        this._container = g(k);
        this._timer = null;
        this._count = Math.abs(m);
        this._target = 0;
        this._t = this._b = this._c = 0;
        this.Index = 0;
        this.SetOptions(l);
        this.Auto = !!this.options.Auto;
        this.Duration = Math.abs(this.options.Duration);
        this.Time = Math.abs(this.options.Time);
        this.Pause = Math.abs(this.options.Pause);
        this.Tween = this.options.Tween;
        this.onStart = this.options.onStart;
        this.onFinish = this.options.onFinish;
        var j = !!this.options.Vertical;
        this._css = j ? "top": "left";
        var o = f(this._container).position;
        o == "relative" || o == "absolute" || (this._container.style.position = "relative");
        this._container.style.overflow = "hidden";
        this._slider.style.position = "absolute";
        this.Change = this.options.Change ? this.options.Change: this._slider[j ? "offsetHeight": "offsetWidth"] / this._count
    };
    h.prototype = {
        SetOptions: function(j) {
            this.options = {
                Vertical: true,
                Auto: true,
                Change: 0,
                Duration: 50,
                Time: 10,
                Pause: 4000,
                onStart: function() {},
                onFinish: function() {},
                Tween: e.Quart.easeOut
            };
            d(this.options, j || {})
        },
        Run: function(j) {
            j == undefined && (j = this.Index);
            j < 0 && (j = this._count - 1) || j >= this._count && (j = 0);
            this._target = -Math.abs(this.Change) * (this.Index = j);
            this._t = 0;
            this._b = parseInt(f(this._slider)[this.options.Vertical ? "top": "left"]);
            this._c = this._target - this._b;
            this.onStart();
            this.Move()
        },
        Move: function() {
            clearTimeout(this._timer);
            if (this._c && this._t < this.Duration) {
                this.MoveTo(Math.round(this.Tween(this._t++, this._b, this._c, this.Duration)));
                this._timer = setTimeout(i(this, this.Move), this.Time)
            } else {
                this.MoveTo(this._target);
                this.Auto && (this._timer = setTimeout(i(this, this.Next), this.Pause))
            }
        },
        MoveTo: function(j) {
            this._slider.style[this._css] = j + "px"
        },
        Next: function() {
            this.Run(++this.Index)
        },
        Previous: function() {
            this.Run(--this.Index)
        },
        Stop: function() {
            clearTimeout(this._timer);
            this.MoveTo(this._target)
        }
    };
    return new h(a, b, c, {
        Vertical: false
    })
}
//轮播效果结束

//热卖区滑动效果开始
var tX = [];
var tY = [];
var x;
var y;
var isSlide = true;
var clientX, clientY;
function touchstart(a) {
    x = a.touches[0].pageX;
    y = a.touches[0].pageY;
    clientX = a.touches[0].clientX;
    clientY = a.touches[0].clientY;
    tX.push(x);
    tY.push(y);
    var b = $("#func").css("margin-left");
    y = parseInt(b.split("px").shift())
}
function touchmove(c) {
    isSlide = true;
    var d = c.touches;
    var e = d[0];
    tX.push(e.pageX);
    tY.push(e.pageY);
    if (tX != undefined && tX.length > 1) {
        var b = Math.abs(e.clientX - clientX);
        if (tY != undefined && tY.length > 1) {
            var a = Math.abs(e.clientY - clientY);
            if (b > a) {
                if (y == -298) {
                    c.preventDefault();
                    $("#func").css("margin-left", (e.pageX - 298 - x) + "px")
                } else {
                    c.preventDefault();
                    $("#func").css("margin-left", (e.pageX - x) + "px")
                }
            } else {
                isSlide = false
            }
        }
    }
}
function touchend(g) {
    if (isSlide) {
        if (tX != undefined && tX.length > 1) {
            var b = parseInt(tX[0], 10);
            var f = parseInt(tX[tX.length - 1], 10);
            var d = Math.abs(b - f);
            if (tY != undefined && tY.length > 1) {
                var a = parseInt(tY[0], 10);
                var e = parseInt(tY[tY.length - 1], 10);
                var c = Math.abs(a - e);
                if (f > b) {
                    $("#func").animate({
                        "margin-left": "0px"
                    },
                    200)
                } else {
                    $("#func").animate({
                        "margin-left": "-298px"
                    },
                    200)
                }
            }
            tX = [];
            tY = []
        }
    } else {
        var h = parseInt($("#func").css("margin-left").replace("px", ""));
        if (h < -149) {
            $("#func").animate({
                "margin-left": "-298px"
            },
            200)
        } else {
            $("#func").animate({
                "margin-left": "0px"
            },
            200)
        }
        tX = [];
        tY = []
    }
}
var startPosX;
var startPosY;
var powerA;
var powerB;
var isend = false;
var cpage = 1;
function tStart(a) {
    startPosX = a.touches[0].pageX;
    startPosY = a.touches[0].pageY
}
function tMove(a) {
    var d = $("#slider").css("margin-left").replace("px", "");
    var b = Math.abs(Math.ceil(parseInt(d) / 71)) + 5;
    var j = $("#slider img");
    for (var e = 0; e < b; e++) {
        if (j.length > e && $(j[e]).attr("imgdata")) {
            $(j[e]).attr("src", $(j[e]).attr("imgdata"));
            $(j[e]).removeAttr("imgdata")
        }
    }
    if (Math.abs(a.touches[0].pageY - startPosY) < Math.abs(a.touches[0].pageX - startPosX)) {
        a.preventDefault()
    }
    var f = a.touches;
    var h = parseInt($("#slider").css("width").replace("px", ""));
    if (a.touches.length == 1) {
        if (f[0].pageX > startPosX) {
            var g = f[0].pageX - startPosX;
            var c = parseInt($("#slider").css("margin-left").replace("px", ""));
            $("#slider").css("margin-left", c + g + "px")
        } else {
            var g = f[0].pageX - startPosX;
            var c = parseInt($("#slider").css("margin-left").replace("px", ""));
            $("#slider").css("margin-left", c + g + "px")
        }
        startPosX = f[0].pageX
    }
    if (f.length > 0) {
        powerA = powerB;
        powerB = f[f.length - 1].pageX
    }
}
function tEnd(c) {
    var d = parseInt($("#slider").css("margin-left").replace("px", ""));
    var b = parseInt($("#slider").css("width").replace("px", ""));
    if (powerA && powerB && powerA > 0 && powerB > 0) {
        var a = Math.abs(powerA - powerB);
        if (a > 0) {
            $("#slider").animate({
                "margin-left": (powerA > powerB ? d - a: d + a) + "px"
            },
            200)
        }
    }
    if (d > 0) {
        setTimeout(function() {
            $("#slider").animate({
                "margin-left": "0px"
            },
            200)
        },
        200)
    }
    if (Math.abs(d) > (b - 320)) {
        if (!isend) {
            cpage += 1;
            jQuery.post("/index/getWare.json", {
                page: cpage
            },
            function(h) {
                if (h && h.crazyList && h.crazyList.length > 0) {
                    var f;
                    var g;
                    for (var e = 0; e < h.crazyList.length; e++) {
                        g = h.crazyList[e];
                        f = '<li class="new-tbl-cell"><a href="/product/' + g.wareId + '.html",$sid)"><img src="' + g.imageurl + '" width="70" height="70"><span>&yen;' + g.jdPrice + "</span></a></li>";
                        $("#slider").append(f)
                    }
                } else {
                    isend = true;
                    setTimeout(function() {
                        $("#slider").animate({
                            "margin-left": -((b - 320 + 20)) + "px"
                        },
                        200)
                    },
                    200)
                }
            },
            "json")
        } else {
            setTimeout(function() {
                $("#slider").animate({
                    "margin-left": -((b - 320 + 20)) + "px"
                },
                200)
            },
            200)
        }
    }
    powerA = 0;
    powerB = 0
};
//热卖区滑动效果结束

/*
* 全部分类页
*/
(function($) {
	// ////////////////
	var btn_up = new Image(), btn_down = new Image();
	btn_up.src = "themes/default/images/ico_12.png";
	btn_down.src = "themes/default/images/ico_11.png";
	var Menu = {
		// 初始化事件
		initEvent : function() {
			$().ready(
					function() {
						$("div.clist").click(function(e) {
							Menu.router(e);
						});
						$("#allClass").click(function(e) {
							Menu.showMenu1();
						});
						$(window).on(
								"hashchange",
								function(e) {
									var name = decodeURIComponent(location.hash.replace(/^#/, ""));
									if (name != "") {
										Menu.showMenu3(name);
									}else{
										Menu.showMenu1();
									}
								});
					});
		},
		// 事件分发路油
		router : function(_event) {
			var target = $(_event.target || _event.srcElement);
			var _tar = target.closest(".level1");

			// 显示二级菜单
			if (_tar.length > 0) {
				Menu.showMenu2(_tar);
				/*var _gp = target.closest(".crow_row");// 点击事件对应此行的祖父级节点
				var _top = _gp.offset().top;
				setTimeout(function(){
					if (_top > 100) {
						window.scroll(0, _gp.offset().top);
					} else {
						window.scroll(0, _gp.offset().top - 50);
					}					
				},15)*/
				return;
			}
		},
		// 显示一级菜单
		showMenu1 : function() {
			$("#contentsub").hide();
			$("#content").show();
		},
		// 显示二级菜单
		showMenu2 : function($curMenuDom) {
			var next = $curMenuDom.next("ul");
			if (next.css("display") == "none") {
				//$("ul.clist_sub").hide();
				//$("div.crow_arrow").each(function(i, dom) {
				//	$(dom).html(btn_down.cloneNode(true));
				//});
				next.css("opacity", "0").show().animate({
					opacity : 100
				}, 500);
				//next.show();
				$("div.crow_arrow", $curMenuDom).html(btn_up.cloneNode(true));
			} else {
				next.hide();
				$("div.crow_arrow", $curMenuDom).html(btn_down.cloneNode(true));
			}
		},
		
	}
	window.Menu = Menu;
	Menu.initEvent();// 初始化事件
})

/*
* 分类页
*/
/**
** ec模板堂  by Leah
*/
//切换浏览模式: 列表  详情  详情列表
function changeCl( cls ){
	var vl = cls.getAttribute('class') ;
	var lst = document.getElementById('J_ItemList');
	
	switch(vl){
		case "switchBtn switchBtn-list":
			$(".tiaoxin_2_id").show();
			$(".tiaoxin_1_id").hide();
			cls.setAttribute('class', 'switchBtn switchBtn-album');
			lst.setAttribute('class' , 'srp j_autoResponsive_container m-ks-autoResponsive-container m-animation album');
			document.getElementById('display').setAttribute('value','album');
			break;
		case "switchBtn switchBtn-album":
			$(".tiaoxin_1_id").show();
			$(".tiaoxin_2_id").hide();
			cls.setAttribute('class', 'switchBtn switchBtn-list');
			lst.setAttribute('class' , 'srp j_autoResponsive_container m-ks-autoResponsive-container m-animation list');
			document.getElementById('display').setAttribute('value','grid');
			break;
	}

}                  
// 右侧菜单显示与隐藏
function mtShowMenu(){
	if(parseInt($("#page").css('right'))<=0){
		$("#page").animate({right:275 , left:-275},"fast").css({"display":"block" , "height":"100%","overflow":"hidden"});
		$("#nav").animate({right:0},"fast");
	}else{
		$("#page").animate({right:0 , left : 0 },"fast").css({"display":"block" , "overflow":"hidden", "position": "none"});
		$("#nav").animate({right: -275 },"fast");
	}
}

//查看更多品牌：
function more_brands( obj ){
   if( obj.className== 'j_More avo-more avo-more-down'){
		var i = $('.filter_list li').size();
		var h = Math.round( i/2)*40;
		$(".filter_list").css('max-height', h) ;
		obj.innerHTML = '收起更多<i></i>';
		obj.className = "j_More avo-more avo-more-up";
   }
	else{
		 $(".filter_list").css('max-height','') ;
		obj.innerHTML = '查看更多<i></i>';
		obj.className = "j_More avo-more avo-more-down";
	}
}
//查看更多分类：
function more_category( obj ){
	if( obj.className== 'j_More avo-more avo-more-down'){//
		 var i = $('#av_category li').size();
		 var h = Math.round( i/2)*40;
		 $("#av_category").css('max-height', h) ;
		 obj.innerHTML = '收起更多<i></i>';
		 obj.className = "j_More avo-more avo-more-up";
	}
	else{
		 $("#av_category").css('max-height','') ;
		obj.innerHTML = '查看更多<i></i>';
		obj.className = "j_More avo-more avo-more-down";
	}
}
/**
* jquery Begin
* @returns {undefined}
*/          
$(function(){
	//点击筛选
	$('.filter_list li').click(function(){
		$(this).addClass('av-selected').siblings('li').removeClass('av-selected');
		var value = $(this).children('a').attr('data');
		if(value.indexOf('|') > 0){
			var val_array = value.split('|');
			$(this).siblings('input[name=price_min]').val(val_array[0]);
			$(this).siblings('input[name=price_max]').val(val_array[1]);
		}else{
			$(this).siblings('input').val(value);
			//属性
			var i = 0;
			var new_attr = new Array();
			$('.filter_attr').each(function() {
				if($(this).hasClass('av-selected')){
					new_attr[i] = $(this).children('a').attr('data');
					i = i+1;
				}
			});
			var new_attr_str = new_attr.join('.');
			//属性参数具体值
			$('input[name=filter_attr]').val(new_attr_str);
		}
	});
	//显示更多
	$('.av-options').click(function(){
		if ($(this).children('a').hasClass("avo-more-down")){
			var i = $('.filter_list li').size();
			var h = Math.round(i/2)*40;
			$(this).siblings('.filter_list').css('max-height', h);
			$(this).children('a').removeClass('avo-more-down').addClass('avo-more-up').html('收起更多<i></i>');
		}else{
			$(this).siblings('.filter_list').css('max-height','') ;
			$(this).children('a').removeClass('avo-more-up').addClass('avo-more-down').html('查看更多<i></i>');
		}
	})
	
	/**
	* 商品分类页跳转页面
	*/
	$('.pagenav-select').change(function(){
		 window.location.href= $(this).val();
	});
	
	/**选择配送方式**/
	
	$('#selected1').click(function(){
		   $('#shipping').slideToggle("fast");
		});
		
	$('#selected2').click(function(){
	   $('#payment').slideToggle("fast");
	});
	/**
	红包
	*/
	$('#selected4').click(function(){
		
	   $('#bonus_box').slideToggle("fast");
	
	});
	/**
    包装
	*/
	$('#selected5').click(function(){
		
	   $('#package_box').slideToggle("fast");
	
	});
	/**
    祝福贺卡
	*/
	$('#selected6').click(function(){
		
	   $('#card_box').slideToggle("fast");
	
	});
	$('.modRadio').click(function(){
		    if( $(".modRadio i").attr('class') == 'fr'){
				 $(".modRadio i").removeClass("fr");
				 $(".modRadio i").addClass("fl");
				 $(".modRadio ins").html('否');
				 $('#inviype_box').css('display','none');
				}
				else{
				 $(".modRadio i").removeClass("fl");
				 $(".modRadio i").addClass("fr");
				 $(".modRadio ins").html('是');
				 $('#inviype_box').css('display','block');
					}
		       
		});
	
})

