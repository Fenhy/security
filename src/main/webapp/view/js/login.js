/**
 * Created by Leon on 2017/2/23/023.
 */

/**
 * 功能描述：登入按钮.
 */
$(".loginBtn").on("click", function () {
    var data = {};
    var username = $("#username").val();
    var password = $("#password").val();
    data.username = username;
    data.password = password;
    if ($("#jizhumima").is(":checked")) {
        var jizhumima = $("#jizhumima").val();
        data.jizhumima = jizhumima;
    }
    if (username && password) {
        $.ajax({
            "url": $(".loginForm").attr("action"),
            "async": true,
            "data": data,
            "type": "POST",
            "success": function (result) {
                alert(result.msg);
                if(result.msg != '用户名或密码错误')
                    window.location.href = $(".noryar_home").attr("href");
            }
        });
    } else {
        alert("请输入用户名或密码");
    }
});
