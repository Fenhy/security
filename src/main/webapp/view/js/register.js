/**
 * Created by Leon on 2017/2/23/023.
 */

$(".registerBtn").on("click", function(){
	var username = $("#username").val();
	var password = $("#password").val();
	var nickname = $("#nickname").val();
	if (username && password && nickname) {
	    var data = {"username": username, "password": password, "nickname":nickname};
	    $.ajax({
	    	"url"          :          $(".registerForm").attr("action"),
			"async"		   :		  true,
			"data"		   :		  data,
			"success"      :		  function(data){
				alert(data.msg);
				if(data.msg != '用户名或密码错误')
					window.location.href = $(".noryar_home").attr("href");
			}
	    });
	} else {
		alert("请输入用户名，密码和昵称");
	}
});