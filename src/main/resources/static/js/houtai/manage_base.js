
//检测窗口的尺寸是否合适 ,用于适配小浏览器尺寸
function checkWindow(){
    console.log("文档的宽是"+document.body.offsetWidth);
    console.log("文档的高是"+document.body.offsetHeight);
    console.log("窗口的宽是"+$(window).width());
    console.log("窗口的高是"+$(window).height());

    if(h>$(window).height())
    {
        h=(document.body.offsetHeight-50);
    }
    if(w>$(window).width()-50)
    {
        w=(document.body.offsetWidth-60);
    }
}
