var Layout=function(){
    var layoutImgPath=ctx+'/assets/admin/layout/img/';
    var handleSidebarAndContentHeight=function(){
        var content=$('.page-content');
        var sidebar=$('.page-sidebar');
        var body=$('body');
        var height;
        if(body.hasClass("page-footer-fixed")===true&&body.hasClass("page-sidebar-fixed")===false){
            var available_height=$(window).height()-$('.page-footer').outerHeight()-$('.page-header').outerHeight();
            if(content.height()<available_height){
                content.attr('style','min-height:'+available_height+'px');
            }
        }else{
            if(body.hasClass('page-sidebar-fixed')){
                height=_calculateFixedSidebarViewportHeight();
                if(body.hasClass('page-footer-fixed')===false){
                    height=height-$('.page-footer').outerHeight();
                }
            }else{
                height=sidebar.height()+20;
                var headerHeight=$('.page-header').outerHeight();
                var footerHeight=$('.page-footer').outerHeight();
                if($(window).width()>1024&&(height+headerHeight+footerHeight)<$(window).height()){
                    height=$(window).height()-headerHeight-footerHeight;
                }
            }
            content.attr('style','min-height:'+height+'px');
        }
    };
    var handleSidebarMenu=function(){
        jQuery('.page-sidebar').on('click','li > a',function(e){
            if($(this).next().hasClass('sub-menu')==false){
                if($('.btn-navbar').hasClass('collapsed')==false){
                    $('.btn-navbar').click();
                }
                return;
            }
            if($(this).next().hasClass('sub-menu always-open')){
                return;
            }
            var parent=$(this).parent().parent();
            var the=$(this);
            var menu=$('.page-sidebar-menu');
            var sub=jQuery(this).next();
            var autoScroll=menu.data("auto-scroll")?menu.data("auto-scroll"):true;
            var slideSpeed=menu.data("slide-speed")?parseInt(menu.data("slide-speed")):200;
            parent.children('li.open').children('a').children('.arrow').removeClass('open');
            parent.children('li.open').children('.sub-menu:not(.always-open)').slideUp(200);
            parent.children('li.open').removeClass('open');
            var slideOffeset=-200;
            if(sub.is(":visible")){
                jQuery('.arrow',jQuery(this)).removeClass("open");
                jQuery(this).parent().removeClass("open");
                sub.slideUp(slideSpeed,function(){
                    if(autoScroll==true&&$('body').hasClass('page-sidebar-closed')==false){
                        if($('body').hasClass('page-sidebar-fixed')){
                            menu.slimScroll({'scrollTo':(the.position()).top});
                        }else{
                            Metronic.scrollTo(the,slideOffeset);
                        }
                    }
                    handleSidebarAndContentHeight();
                });
            }else{
                jQuery('.arrow',jQuery(this)).addClass("open");
                jQuery(this).parent().addClass("open");
                sub.slideDown(slideSpeed,function(){
                    if(autoScroll==true&&$('body').hasClass('page-sidebar-closed')==false){
                        if($('body').hasClass('page-sidebar-fixed')){
                            menu.slimScroll({'scrollTo':(the.position()).top});
                        }else{
                            Metronic.scrollTo(the,slideOffeset);
                        }
                    }
                    handleSidebarAndContentHeight();
                });
            }
            e.preventDefault();
        });
        jQuery('.page-sidebar').on('click',' li > a.ajaxify',function(e){
            e.preventDefault();
            Metronic.scrollTop();
            var url=$(this).attr("href");
            var menuContainer=jQuery('.page-sidebar ul');
            var pageContent=$('.page-content');
            var pageContentBody=$('.page-content .page-content-body');
            menuContainer.children('li.active').removeClass('active');
            menuContainer.children('arrow.open').removeClass('open');
            $(this).parents('li').each(function(){
                $(this).addClass('active');
                $(this).children('a > span.arrow').addClass('open');
            });
            $(this).parents('li').addClass('active');
            Metronic.startPageLoading();
            if($(window).width()<=991&&$('.page-sidebar').hasClass("in")){
                $('.navbar-toggle').click();
            }
            var the=$(this);
            $.ajax({
                type:"GET",
                cache:false,
                url:url,
                dataType:"html",
                success:function(res){
                    if(the.parents('li.open').size()===0){
                        $('.page-sidebar-menu > li.open > a').click();
                    }
                    Metronic.stopPageLoading();
                    pageContentBody.html(res);
                    Layout.fixContentHeight();
                    Metronic.initAjax();
                },
                error:function(xhr,ajaxOptions,thrownError){
                    Metronic.stopPageLoading();
                    pageContentBody.html('<h4>Could not load the requested content.</h4>');
                }
            });
        });
        jQuery('.page-content').on('click','.ajaxify',function(e){
            e.preventDefault();
            Metronic.scrollTop();
            var url=$(this).attr("href");
            var pageContent=$('.page-content');
            var pageContentBody=$('.page-content .page-content-body');
            Metronic.startPageLoading();
            if($(window).width()<=991&&$('.page-sidebar').hasClass("in")){
                $('.navbar-toggle').click();
            }
            $.ajax({
                type:"GET",
                cache:false,
                url:url,
                dataType:"html",
                success:function(res){
                    Metronic.stopPageLoading();
                    pageContentBody.html(res);
                    Layout.fixContentHeight();
                    Metronic.initAjax();
                },
                error:function(xhr,ajaxOptions,thrownError){
                    pageContentBody.html('<h4>Could not load the requested content.</h4>');
                    Metronic.stopPageLoading();
                }
            });
        });
    };
    var _calculateFixedSidebarViewportHeight=function(){
        var sidebarHeight=$(window).height()-$('.page-header').outerHeight();
        if($('body').hasClass("page-footer-fixed")){
            sidebarHeight=sidebarHeight-$('.page-footer').outerHeight();
        }
        return sidebarHeight;
    };
    var handleFixedSidebar=function(){
        var menu=$('.page-sidebar-menu');
        if(menu.parent('.slimScrollDiv').size()===1){
            menu.slimScroll({
                destroy:true
            });
            menu.removeAttr('style');
            $('.page-sidebar').removeAttr('style').removeAttr('data-initialized');
        }
        if($('.page-sidebar-fixed').size()===0){
            handleSidebarAndContentHeight();
            return;
        }
        var viewport=Metronic.getViewPort();
        if(viewport.width>=992){
            var sidebarHeight=_calculateFixedSidebarViewportHeight();
            menu.slimScroll({
                size:'7px',
                color:'#a1b2bd',
                opacity:.3,
                position:Metronic.isRTL()?'left':'right',
                height:sidebarHeight,
                allowPageScroll:false,
                disableFadeOut:false
            });
            handleSidebarAndContentHeight();
        }
    };
    var _initFixedSidebarHoverEffect=function(){
        var body=$('body');
        if(body.hasClass('page-sidebar-fixed')){
            $('.page-sidebar-menu').on('mouseenter',function(){
                if(body.hasClass('page-sidebar-closed')){
                    $(this).removeClass('page-sidebar-menu-closed');
                }
            }).on('mouseleave',function(){
                        if(body.hasClass('page-sidebar-closed')){
                            $(this).addClass('page-sidebar-menu-closed');
                        }
                    });
        }
    };
    var handleSidebarToggler=function(){
        var viewport=Metronic.getViewPort();
        var body=$('body');
        if($.cookie&&$.cookie('sidebar_closed')==='1'&&viewport.width>=992){
            $('body').addClass('page-sidebar-closed');
            $('.page-sidebar-menu').addClass('page-sidebar-menu-closed');
        }
        $('.page-sidebar, .page-header').on('click','.sidebar-toggler',function(e){
            var sidebar=$('.page-sidebar');
            var sidebarMenu=$('.page-sidebar-menu');
            $(".sidebar-search",sidebar).removeClass("open");
            if(body.hasClass("page-sidebar-closed")){
                body.removeClass("page-sidebar-closed");
                sidebarMenu.removeClass("page-sidebar-menu-closed");
                if($.cookie){
                    $.cookie('sidebar_closed','0');
                }
            }else{
                body.addClass("page-sidebar-closed");
                sidebarMenu.addClass("page-sidebar-menu-closed");
                if(body.hasClass("page-sidebar-fixed")){
                    sidebarMenu.trigger("mouseleave");
                }
                if($.cookie){
                    $.cookie('sidebar_closed','1');
                }
            }
            $(window).trigger('resize');
        });
        _initFixedSidebarHoverEffect();
        $('.page-sidebar').on('click','.sidebar-search .remove',function(e){
            e.preventDefault();
            $('.sidebar-search').removeClass("open");
        });
        $('.page-sidebar .sidebar-search').on('keypress','input.form-control',function(e){
            if(e.which==13){
                $('.sidebar-search').submit();
                return false;
            }
        });
        $('.sidebar-search .submit').on('click',function(e){
            e.preventDefault();
            if($('body').hasClass("page-sidebar-closed")){
                if($('.sidebar-search').hasClass('open')==false){
                    if($('.page-sidebar-fixed').size()===1){
                        $('.page-sidebar .sidebar-toggler').click();
                    }
                    $('.sidebar-search').addClass("open");
                }else{
                    $('.sidebar-search').submit();
                }
            }else{
                $('.sidebar-search').submit();
            }
        });
    };
    var handleHorizontalMenu=function(){
        $('.page-header').on('click','.hor-menu a[data-toggle="tab"]',function(e){
            e.preventDefault();
            var nav=$(".hor-menu .nav");
            var active_link=nav.find('li.current');
            $('li.active',active_link).removeClass("active");
            $('.selected',active_link).remove();
            var new_link=$(this).parents('li').last();
            new_link.addClass("current");
            new_link.find("a:first").append('<span class="selected"></span>');
        });
        $('.page-header').on('click','.search-form',function(e){
            $(this).addClass("open");
            $(this).find('.form-control').focus();
            $('.page-header .search-form .form-control').on('blur',function(e){
                $(this).closest('.search-form').removeClass("open");
                $(this).unbind("blur");
            });
        });
        $('.page-header').on('keypress','.hor-menu .search-form .form-control',function(e){
            if(e.which==13){
                $(this).closest('.search-form').submit();
                return false;
            }
        });
        $('.page-header').on('mousedown','.search-form.open .submit',function(e){
            e.preventDefault();
            e.stopPropagation();
            $(this).closest('.search-form').submit();
        });
        $(document).on('click','.mega-menu-dropdown .dropdown-menu',function(e){
            e.stopPropagation();
        });
    };
    var handleTabs=function(){
        $('body').on('shown.bs.tab','a[data-toggle="tab"]',function(){
            handleSidebarAndContentHeight();
        });
    };
    var handleGoTop=function(){
        jQuery('.page-footer').on('click','.go-top',function(e){
            Metronic.scrollTo();
            e.preventDefault();
        });
    };
    var handle100HeightContent=function(){
        var reinitSlimscroll=function(target){
            if(target.find(".slimScrollDiv").size()===1){
                target.find(".full-height-content-body").slimScroll({
                    destroy:true
                });
                target.find(".full-height-content-body").removeAttr('style').removeAttr('data-initialized');
            }
        };
        var target=$('.full-height-content');
        if(target.size()===0||$(window).width()<992){
            reinitSlimscroll(target);
            return;
        }
        var height;
        var body=$('body');
        if(body.hasClass('page-footer-fixed')){
            height=$(window).height()-$('.page-header').outerHeight()-$('.page-footer').outerHeight();
        }else{
            height=$(window).height()-$('.page-header').outerHeight()-$('.page-footer').outerHeight()-$('.page-title').outerHeight(true)-$('.page-breadcrumb').outerHeight(true);
        }
        reinitSlimscroll(target);
        if(target.hasClass('portlet')){
            var portletBody=target.find('.portlet-body');
            height=height-target.find('.portlet-title').outerHeight(true)-parseInt(target.find('.portlet-body').css('padding-top'))-parseInt(target.find('.portlet-body').css('padding-bottom'))-2;
            if(target.hasClass("full-height-content-scrollable")){
                height=height-20;
                portletBody.find('.full-height-content-body').css('height',height);
                Metronic.initSlimScroll(portletBody.find('.full-height-content-body'));
            }else{
                portletBody.css('min-height',height);
            }
        }else{
            if(target.hasClass("full-height-content-scrollable")){
                height=height-20;
                target.find('.full-height-content-body').css('height',height);
                Metronic.initSlimScroll(target.find('.full-height-content-body'));
            }else{
                target.css('min-height',height);
            }
        }
    };
    var handleTheme=function(){
        var panel=$('.theme-panel');
        if($('body').hasClass('page-boxed')==false){
            $('.layout-option',panel).val("fluid");
        }
        $('.sidebar-option',panel).val("default");
        $('.page-header-option',panel).val("fixed");
        $('.page-footer-option',panel).val("default");
        if($('.sidebar-pos-option').attr("disabled")===false){
            $('.sidebar-pos-option',panel).val(Metronic.isRTL()?'right':'left');
        }
        var resetLayout=function(){
            $("body").removeClass("page-boxed").removeClass("page-footer-fixed").removeClass("page-sidebar-fixed").removeClass("page-header-fixed").removeClass("page-sidebar-reversed");
            $('.page-header > .page-header-inner').removeClass("container");
            if($('.page-container').parent(".container").size()===1){
                $('.page-container').insertAfter('body > .clearfix');
            }
            if($('.page-footer > .container').size()===1){
                $('.page-footer').html($('.page-footer > .container').html());
            }else if($('.page-footer').parent(".container").size()===1){
                $('.page-footer').insertAfter('.page-container');
            }
            $('body > .container').remove();
        };
        var lastSelectedLayout='';
        var setLayout=function(){
            var layoutOption=$('.layout-option',panel).val();
            var sidebarOption=$('.sidebar-option',panel).val();
            var headerOption=$('.page-header-option',panel).val();
            var footerOption=$('.page-footer-option',panel).val();
            var sidebarPosOption=$('.sidebar-pos-option',panel).val();
            if(sidebarOption=="fixed"&&headerOption=="default"){
                alert('Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar.');
                $('.page-header-option',panel).val("fixed");
                $('.sidebar-option',panel).val("fixed");
                sidebarOption='fixed';
                headerOption='fixed';
            }
            resetLayout();
            if(layoutOption==="boxed"){
                $("body").addClass("page-boxed");
                $('.page-header > .page-header-inner').addClass("container");
                var cont=$('body > .clearfix').after('<div class="container"></div>');
                $('.page-container').appendTo('body > .container');
                if(footerOption==='fixed'){
                    $('.page-footer').html('<div class="container">'+$('.page-footer').html()+'</div>');
                }else{
                    $('.page-footer').appendTo('body > .container');
                }
            }
            if(lastSelectedLayout!=layoutOption){
                Metronic.runResizeHandlers();
            }
            lastSelectedLayout=layoutOption;
            if(headerOption==='fixed'){
                $("body").addClass("page-header-fixed");
                $(".page-header").removeClass("navbar-static-top").addClass("navbar-fixed-top");
            }else{
                $("body").removeClass("page-header-fixed");
                $(".page-header").removeClass("navbar-fixed-top").addClass("navbar-static-top");
            }
            if($('body').hasClass('page-full-width')===false){
                if(sidebarOption==='fixed'){
                    $("body").addClass("page-sidebar-fixed");
                    $("page-sidebar-menu").addClass("page-sidebar-menu-fixed");
                    $("page-sidebar-menu").removeClass("page-sidebar-menu-default");
                    _initFixedSidebarHoverEffect();
                }else{
                    $("body").removeClass("page-sidebar-fixed");
                    $("page-sidebar-menu").addClass("page-sidebar-menu-default");
                    $("page-sidebar-menu").removeClass("page-sidebar-menu-fixed");
                    $('.page-sidebar-menu').unbind('mouseenter').unbind('mouseleave');
                }
            }
            if(footerOption==='fixed'){
                $("body").addClass("page-footer-fixed");
            }else{
                $("body").removeClass("page-footer-fixed");
            }
            if(Metronic.isRTL()){
                if(sidebarPosOption==='left'){
                    $("body").addClass("page-sidebar-reversed");
                    $('#frontend-link').tooltip('destroy').tooltip({placement:'right'});
                }else{
                    $("body").removeClass("page-sidebar-reversed");
                    $('#frontend-link').tooltip('destroy').tooltip({placement:'left'});
                }
            }else{
                if(sidebarPosOption==='right'){
                    $("body").addClass("page-sidebar-reversed");
                    $('#frontend-link').tooltip('destroy').tooltip({placement:'left'});
                }else{
                    $("body").removeClass("page-sidebar-reversed");
                    $('#frontend-link').tooltip('destroy').tooltip({placement:'right'});
                }
            }
            handleSidebarAndContentHeight();
            handleFixedSidebar();
        };
        var setColor=function(color){
            var color_=(Metronic.isRTL()?color+'-rtl':color);
            $('#style_color').attr("href",ctx+"/assets/admin/layout/css/themes/"+color_+".css");
            if(color=='light2'){
                $('.page-logo img').attr('src',ctx+'/assets/admin/layout/img/logo-invert.png');
            }else{
                $('.page-logo img').attr('src',ctx+'/assets/admin/layout/img/logo.png');
            }
            if($.cookie){
                $.cookie('style_color',color);
            }
        };
        $('.toggler',panel).click(function(){
            $('.toggler').hide();
            $('.toggler-close').show();
            $('.theme-panel > .theme-options').show();
        });
        $('.toggler-close',panel).click(function(){
            $('.toggler').show();
            $('.toggler-close').hide();
            $('.theme-panel > .theme-options').hide();
        });
        $('.theme-colors > ul > li',panel).click(function(){
            var color=$(this).attr("data-style");
            setColor(color);
            $('ul > li',panel).removeClass("current");
            $(this).addClass("current");
        });
        $('.layout-option, .page-header-option, .sidebar-option, .page-footer-option, .sidebar-pos-option',panel).change(setLayout);
        if($.cookie&&$.cookie('style_color')){
            setColor($.cookie('style_color'));
        }
    };
    return {
        init:function(){
            Metronic.addResizeHandler(handleSidebarAndContentHeight);
            Metronic.addResizeHandler(handleFixedSidebar);
            Metronic.addResizeHandler(handle100HeightContent);
            handleFixedSidebar();
            handleSidebarMenu();
            handleHorizontalMenu();
            handleSidebarToggler();
            handle100HeightContent();
            handleGoTop();
            handleTabs();
            handleTheme();
        },
        fixContentHeight:function(){
            handleSidebarAndContentHeight();
        },
        getLayoutImgPath:function(){
            return layoutImgPath;
        }
    };
}();