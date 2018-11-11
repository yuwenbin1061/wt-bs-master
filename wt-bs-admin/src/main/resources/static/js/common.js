/**
 * 护眼模式
 */
function changeTheme() {
    //这里toggleClass的意思是如果 有就删掉 没有就加上
    //按钮切换
    $('#eyesI').toggleClass('am-icon-toggle-off');
    $('#eyesI').toggleClass('am-icon-toggle-on');
    //主题切换
    $('body').toggleClass('theme-white');
    $('body').toggleClass('theme-black');
    //保存到浏览器缓存 这个暂时不需要 先注着
    saveSelectColor.Color = $('body').attr('class');
    storageSave(saveSelectColor);
}

//左边栏的隐藏和出现
function leftSidebar() {
    if ($('.left-sidebar').attr('class').indexOf('active') > 0
        && $('.tpl-content-wrapper').attr('class').indexOf('active') > 0) {
        $('.left-sidebar').addClass('active');
        $('.tpl-content-wrapper').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
        $('.tpl-content-wrapper').removeClass('active');
    }
}



