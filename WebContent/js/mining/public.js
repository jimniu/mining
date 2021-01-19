function public_link(){
    var clipboard = new Clipboard('a.getlink');

    clipboard.on('success', function(e) {
        msgbox("链接地址复制成功！");
        console.log(e);
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
}