/**
 * Created by Leon on 2017/2/23/023.
 */

/**
 * 功能描述：登入按钮.
 */
$(".loginBtn").on("click", function(){
	var username = $("#username").val();
	var password = $("#password").val();
	if (username && password) {
	    var data = {"username": username, "password": password};
	    $.ajax({
	    	"url"          :          $(".loginForm").attr("action"),
			"async"		   :		  true,
			"data"		   :		  data,
			"success"      :		  function(data){
				alert(data.msg);
				if(data.msg != '用户名或密码错误')
					window.location.href = $(".noryar_home").attr("href");
			}
	    });
	} else {
		alert("请输入用户名或密码");
	}
});
