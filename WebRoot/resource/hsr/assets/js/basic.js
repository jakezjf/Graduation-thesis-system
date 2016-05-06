function SetWinHeight(obj) {
    var win = obj;
    if (document.getElementById) {
        if (win && !window.opera) {
            if (win.contentDocument && win.contentDocument.body.offsetHeight)
                win.height = win.contentDocument.body.offsetHeight;
            else if (win.Document && win.Document.body.scrollHeight)
                win.height = win.Document.body.scrollHeight;
        }
    }
}
if (document.getElementById("newPriceLinks")) {
    var sfEls = document.getElementById("newPriceLinks").getElementsByTagName("li");
    for (var i = 0; i < sfEls.length; i++) {
        sfEls[i].onmouseover = function() {
            this.className += (this.className.length > 0 ? " " : "") + "sfhover";
        }
        sfEls[i].onmouseout = function() {
            this.className = this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
        }
    }
}
if (document.getElementById("login_tcc")) {
    var sfEls = document.getElementById("login_tcc").getElementsByTagName("li");
    for (var i = 0; i < sfEls.length; i++) {
        sfEls[i].onmouseover = function() {
            this.className += (this.className.length > 0 ? " " : "") + "sfhover";
        }
        sfEls[i].onmouseout = function() {
            this.className = this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
        }
    }
}
if (document.getElementById("login_tpynav")) {
    var sfEls = document.getElementById("login_tpynav").getElementsByTagName("li");
    for (var i = 0; i < sfEls.length; i++) {
        sfEls[i].onmouseover = function() {
            this.className += (this.className.length > 0 ? " " : "") + "sfhover";
        }
        sfEls[i].onmouseout = function() {
            this.className = this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
        }
    }
}
$(function () {
    //跳转
    $('.tit a').click(function () {
        //$(this).parent().next('div').children('di:first').find('a').click();
        location.href =($(this).parent().next('div').children('dl:first').find('a').get(0));
    });
    navh();
    $(window).resize(function () {
        navh();
    });
    function navh() {
        wh = $(window).height() - 166;
        nh = $('.nav_menu_box');
        mh = $('.core_main').height() + 55;
        if (wh > mh) {
            nh.height(wh);
        } else {
            nh.height(mh);
        }
    }
});
