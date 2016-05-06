/* Credit: http://www.templatemo.com */

jQuery(function($) {

    /* Everything is loaded.
    ---------------------------- */
    $(window).load( function() {

        /* Load Flex slider
        -------------------------------------------*/
        // The slider being synced must be initialized first
        $('#carousel').flexslider({
            animation: "slide",
            controlNav: false,
            animationLoop: false,
            slideshow: false,
            itemWidth: 170,
            itemMargin: 10,
            asNavFor: '#slider'
        });

        $('#slider').flexslider({
            animation: "slide",
            controlNav: false,
            animationLoop: false,
            slideshow: false,
            sync: "#carousel",
            start: function() {

                $('.js-content').hide();
                
                /* Remove preloader
                -----------------------------------------------*/
                $('#status').fadeOut(); // will first fade out the loading animation
                $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.

                /* Get hash in URL and show page content
                ---------------------------------------------------------*/
                var hash = window.location.hash.substring(1);
                
                if(hash == "") {
                    hash = "page-1";
                }

                var defaultImgSrc = $("img#" + hash + "-img").attr('src');
                $.backstretch(defaultImgSrc, {speed: 500});                              // show background image

                $(".js-nav-item[data-nav-item-id='" + hash + "']").addClass("active");   // highlight nav item
                $(".js-content[data-page-id='" + hash + "']").show();                    // show page content
                $(".js-footer").fadeIn();                                                // show footer
                
            }
        });       

    });
    
    $(document).ready(function(){

        /* Handle site title click, hide nav, page content, remove # from URL
        ----------------------------------------------------------------------*/
        $(".js-site-title").click(function(){
            $('.js-nav').fadeToggle();
            $('.js-content-wrapper').fadeToggle();
            history.pushState("", document.title, window.location.pathname);   
            $('.js-footer').toggleClass("sticky");
        });
        
        /* Handle Nav item click
        -----------------------------------------*/
        $(".js-nav-item").click(function(){

            // Change nav item active link

            $(".js-nav-item").removeClass("active");    // Remove active class of all nav items
            $(this).addClass("active");                 // Add active class to current item

            
            // Change page background image

            var currentItemNo = $(this).attr('data-nav-item-id');
            var currentPage = $(this).children("a").attr('href');
            var currentImgSrc = $("img" + currentPage + "-img").attr('src');

            $.backstretch(currentImgSrc, {speed: 500});

            
            // Change page content

            $('.js-content').hide();
            $('.js-footer').hide();
            
            $(".js-content[data-page-id='" + currentItemNo + "']").slideDown('slow', function(){
                $(".js-footer").fadeIn();
            });                      
            
        }); 

    });
    
});
