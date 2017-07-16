$("a#logout").on("click", function () {
    $.ajax({
        url: "/login/logout.do",
        data: {"doLogout": "true"},
        "success": function (data) {
            if (data.status == "success") {
                alert("退出成功");
                window.location.href = "/viewc/license/index.do";
            } else {
                alert("退出失败");
            }
        }
    });
});