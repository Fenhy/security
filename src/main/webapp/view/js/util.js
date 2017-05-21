/**
 * Created by Leon on 2017/2/23/023.
 */

/**
 * 功能描述：js获取项目根路径，如： http://localhost:8083/uimcardprj
 * @returns
 */
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //return(localhostPaht+projectName);
    return projectName;
}

/**
 * 功能描述：ajax文件上传.
 * @param type 表单提交方式(get,post等).
 * @param url 上传路径
 * @param async 是否异步(true=异步，否则false)
 * @param formData 待上传的表单文件，json形式.
 * @param completeFn 上传成功调用的方法.
 * @returns
 */
function ajaxUpload(type, url, async, formData, completeFn){
	var xhr = new XMLHttpRequest();
	// 上传过程处理
    var onProgressHandler = function(evt) {
/*    	if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            $("#uploadCnt").html(percentComplete.toString() + '%');
        } else {
        	$("#uploadCnt").html('无法计算上传进度。');
        }*/
    }
    // 上传成功响应
    var uploadComplete;
    if(!completeFn){
    	uploadComplete = function(evt) {
            // 服务断接收完文件返回的结果
            var result = eval("(" + evt.target.responseText + ")");
            // do something
        }
    }else{
    	uploadComplete = completeFn;
    }
    // 上传失败
    var uploadFailed = function(evt) {
         alert("上传失败");
    }
    // 取消上传
    var uploadCanceled = function(evt) {
        alert("您取消了本次上传");
    }
    // 监听事件
    xhr.upload.addEventListener("progress", onProgressHandler, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    // 发送文件和表单自定义参数
    xhr.open(type, url, async);
    xhr.send(formData);
}

//#############################################日期格式化区######################################
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//#############################################Ajax方法调用区####################################
function AjaxHelper() {
    this.ajax = function(url, type, dataType, data, callback) {
        $.ajax({
            url: url,
            type: type,
            dataType: dataType,
            data: data,
            success: callback,
            error: function(xhr, errorType, error) {
                alert('Ajax request error, errorType: ' + errorType +  ', error: ' + error)
            }
        })
    }
}
AjaxHelper.prototype.get = function(url, data, callback) {
    this.ajax(url, 'GET', 'json', data, callback)
}
AjaxHelper.prototype.post = function(url, data, callback) {
    this.ajax(url, 'POST', 'json', data, callback)
}

AjaxHelper.prototype.put = function(url, data, callback) {
    this.ajax(url, 'PUT', 'json', data, callback)
}

AjaxHelper.prototype.delete = function(url, data, callback) {
    this.ajax(url, 'DELETE', 'json', data, callback)
}

AjaxHelper.prototype.jsonp = function(url, data, callback) {
    this.ajax(url, 'GET', 'jsonp', data, callback)
}

AjaxHelper.prototype.constructor = AjaxHelper