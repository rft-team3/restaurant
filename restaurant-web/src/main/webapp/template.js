$(document).ready(function(){
    var elementPosition = $('#navigation').offset();

    $(window).scroll(function(){
        if($(window).scrollTop() > elementPosition.top){
            $('#navigation').css('position','fixed').css('top','0');
            $('#navFix').css('padding-bottom','60px');
        } else {
            $('#navigation').css('position','static');
            $('#navFix').css('padding-bottom','0');
        }
    });
});