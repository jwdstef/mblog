/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/

define(function(require, exports, module) {

	require('ueditor.config');

    var ueditor;

    var initEditor = function (callback) {
        require.async('ueditor', function () {


            ueditor = UE.getEditor('content', {
                fastUpload: window.app.base + "/aj_um_upload.json",
                //toolbars: [
                   // ['fullscreen', 'source', "button",'undo', 'redo', 'bold']
                //],
                fastFileName: 'upfile',
                fastUrlPrefix: window.app.base,
                imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 上传图片格式显示 */

                wordCount: true,
                maximumWords: 2000,
                initialFrameWidth: '100%',
                initialFrameHeight: 300
            });

            UE.registerUI('button',function(ueditor,uiName){
                //注册按钮执行时的command命令，使用命令默认就会带有回退操作
                ueditor.registerCommand(uiName, {
                    execCommand: function() {
                        alert('execCommand:' + uiName)
                    }
                });
                //创建一个button
                var btn = new UE.ui.Button({
                    //按钮的名字
                    name: uiName,
                    //提示
                    title: uiName,
                    //添加额外样式，指定icon图标，这里默认使用一个重复的icon
                    cssRules: 'background-position: -500px 0;',
                    //点击时执行的命令
                    onclick: function() {
                        //这里可以不用执行命令,做你自己的操作也可
                        ueditor.execCommand(uiName);
                    }
                });
                //当点到编辑内容上时，按钮要做的状态反射
                ueditor.addListener('selectionchange', function() {
                    var state = ueditor.queryCommandState(uiName);
                    if (state == -1) {
                        btn.setDisabled(true);
                        btn.setChecked(false);
                    } else {
                        btn.setDisabled(false);
                        btn.setChecked(state);
                    }
                });
                //因为你是添加button,所以需要返回这个button
                return btn;
            });


            callback.call(this);
        });

    }

	exports.init = function (callback) {
        initEditor(callback);
    }
});