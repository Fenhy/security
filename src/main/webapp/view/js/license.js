/**
 * Created by Leon on 2017/2/23/023.
 */

// *******************************************************生成证书
/**
 * 功能描述：上传证书xml按钮.
 */
$(".uploadBtn").on("click", function(){
	clearFile("uploadInput", "fileName");
	$("#uploadInput").click();
});

/**
 * 功能描述：生产证书按钮.
 */
$(".createLicenseBtn").on("click",function(){
	if (errorFileName($("#uploadFileName").val(), ["xls", "xlsx"])) {
		alert("请上传文件");
		return;
	}
    var url = $("form.msgfileForm").attr("action");
	var fd = new FormData();
	fd.append("msgfile",$("#uploadInput")[0].files[0]);
	var completeFn = function(evt) {
        // 服务断接收完文件返回的结果
        var result = eval("(" + evt.target.responseText + ")");
        alert(result.msg);
        alert("即将开始下载文件...");
        var files = result.files;
        for (var index in files){
        	window.location.href = getRootPath()+"/license/getLicFile.do?path="+files[index];
        }
    }
    ajaxUpload("POST", url, true, fd, completeFn);
});

/**
 * 功能描述：文件被改变时操作，如果使用$().on("change")方式会出现问题，因此使用dom的onchange属性进行绑定.
 * @param obj 【input type='file' /】的this对象.
 * @param fileInputId 显示文件名的id.
 * @returns
 */
function uploadInputChange(obj, fileInputId){
    var file = obj.files[0];
    $("#"+fileInputId).val(file.name);
}

/**
 * 功能描述：校验文件是否合法.
 * @param fileName 待校验文件名.
 * @param Exts 需要校验的扩展名.
 * @returns
 */
function checkExcel(fileName, Exts){
    var fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
    for( var ext in Exts)
	    if(fileExt == ext)
	        return true;
    return false;
}

/**
 * 功能描述：清空上传文件对象.
 * @param fileSelect 【input type='file' /】对象id.
 * @param fileNameSelect 显示文件名的input对象id.
 * @returns
 */
function clearFile(fileSelect, fileNameSelect){
    $("#"+fileNameSelect).val("");
    // for IE, Opera, Safari, Chrome
    var file = $("#"+fileSelect)[0];
    if (file.outerHTML) {
        file.outerHTML = file.outerHTML;
    } else { // FF(包括3.5)
        file.value = "";
    }
}

/**
 * 功能描述：检查文件名是否错误.
 * @param fileName 文件名.
 * @param exts [] 待校验文件扩展名.如果为null，则直接返回false;
 * @returns boolean true:错误文件, false:正确文件.
 */
function errorFileName(fileName, exts){
	if (fileName) { // 文件名不为空
		if (!exts) { // 扩展名为空
			return false;
		} else if (exts instanceof Array) { // 扩展名是数组
			var lastI = fileName.lastIndexOf(".");
			if (lastI != fileName.length -1) { // 文件有扩展名
				var waitChk = fileName.substring(lastI + 1);
				for (var i in exts) {
					if (waitChk == exts[i]) {
						return false;
					}
				}
			}
		}
	}
	return true;
}

// *********************************************************验证证书
/**
 * 功能描述：上传证书lic按钮.
 */
$(".uploadLicenseBtn").on("click", function(){
	clearFile("licenseInput", "licenseName");
	$("#licenseInput").click();
});

/**
 * 功能描述：验证证书.
 */
$(".verificationBtn").on("click",function(){
	if (errorFileName($("#licenseName").val(), ["lic"])) {
		alert("请上传文件");
		return;
	}
    var url = $("form.verificationForm").attr("action");
	var fd = new FormData();
	fd.append("licfile",$("#licenseInput")[0].files[0]);
	var completeFn = function(evt) {
        // 服务断接收完文件返回的结果
        var result = eval("(" + evt.target.responseText + ")");
        alert(result.msg);
    }
    ajaxUpload("POST", url, true, fd, completeFn);
});
