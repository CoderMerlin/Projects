
$j(function(){
    var $ = $j;
    var Search = function(){
        this.init();
    };
    $.extend(Search.prototype,{
        init:function(){
            this.bindEvent();
        },
        bindEvent:function(){
            var $this = this;
            $('#searchNav').find('.pull-left').on('click',function(e){
                $(this).addClass('active').siblings().removeClass('active');
                $('#searchType').val($(this).data('type'));
            });
            $('#quickSearch').on('mouseenter',function(e){
                var offset = $(this).offset();
                $this.showAdPanel(offset);
            }).on('mouseleave',function(e){
                $this.hideAdPanel();
            });
            $('#adPanel').on('mouseenter',function(e){
                $this.showAdPanel();
            }).on('mouseleave',function(e){
                $this.hideAdPanel();
            });
            $('.radio').on('click',function(e){
                $(this).addClass('active').siblings().removeClass('active');
                $(this).parents('.td2').children('input[type=hidden]').val($(this).data('type'));
            });
            $('.checkbox').on('click',function(e){
                var el = $(this);
                el.hasClass('active')?el.removeClass('active').find('input[type=checkbox]').prop('checked',false):el.addClass('active').find('input[type=checkbox]').prop('checked',true);
            });
            $('.select').on('click',function(e){
                var sel = $(this).attr('id');
                var select = $('#sel4'+sel), offset = $(this).offset();
                if(select.length){
                    select.css({top:offset.top,left:offset.left}).show();
                    if($('#sel4searchRange').length){
                        new IScroll('#sel4searchRange',{hScroll:0,vScroll:1,eventPassthrough:0,scrollX:0,scrollY:1,preventDefault:0,scrollbars:1,hideScrollbar:0,mouseWheel:1});
                    }
                }
            });
            $('.selectUl').find('li').on('click',function(e){
                e.stopPropagation();
                var text = $(this).text(), val = $(this).data('val');
                var select = $(this).parents('.selectUl').attr('id').slice(4);
                $('#'+select).find('p').text($.trim(text.replace(/&nbsp;/g,'')));
                $('#'+select).find('input[type=hidden]').val(val);
                $(this).parents('.selectUl').hide();
            });
            $('.selectUl').on('mouseleave',function(e){
                $(this).hide();
            });
            $('#scform_submit').parents('form').on('submit',function(e){
                if($.trim($('input[name=srchtxt]').val())==''){
                    e.preventDefault();
                    e.stopPropagation();
                    return;
                }
            });
        },
        adTimer:null,
        showAdPanel:function(offset){
            var $this = this;
            if($this.adTimer){
                clearTimeout($this.adTimer);
            }
            if(offset){
                $('#adPanel').css({left:offset.left,top:offset.top+25}).fadeIn(200);
            }else{
                $('#adPanel').fadeIn(200);
            }
        },
        hideAdPanel:function(){
            var $this = this;
            if($this.adTimer){
                clearTimeout($this.adTimer);
            }
            $this.adTimer = setTimeout(function(){
                $('#adPanel').fadeOut(200);
            },200);
        }
    });
    new Search();
});