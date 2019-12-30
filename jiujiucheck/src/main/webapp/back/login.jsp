<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- jquery -->
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../font/css/font-awesome.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    <script src="../css/bootstrap.min.css"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>

    <script src="../back/assets/js/jquery-2.2.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>
    <script src="../boot/js/jquery.validate.min.js"></script>
    <script src="../boot/js/jquery.validate.min.js"></script>
    <!--表单验证的js插件-->
    <script src="../boot/js/jquery.validate.min.js"></script>


    <script>

        <!--背景图片的脚本 -->
        jQuery(document).ready(function () {

            /*
                Fullscreen background
            */
            $.backstretch("img/loginBackground.jpg");

            /*
                Form validation
            */
            $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function () {
                $(this).removeClass('input-error');
            });

            $('.login-form').on('submit', function (e) {

                $(this).find('input[type="text"], input[type="password"], textarea').each(function () {
                    if ($(this).val() == "") {
                        e.preventDefault();
                        $(this).addClass('input-error');
                    } else {
                        $(this).removeClass('input-error');
                    }
                });

            });


        });

        $(function () {

            /*验证码点击刷新*/
            $("#captchaImage").click(function () {
                //请求后拼接时间，表明是不同的请求，拼接验证码
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/verificationCode/getVerificationCode?time=" + new Date().getTime())
            })

            /*中文重写提示*/
            $.extend($.validator.messages, {
                required: "<span style='color: red'>此字段不能为空</span>",
            })

            /*表单验证的js*/
            $("#loginButtonId").click(function () {

                //验证表单输入的值是否为空。如果不为空，则发送Ajax请求
                var status = $("#loginForm").valid();
                if(status){
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/adminLogin",
                        data: $("#loginForm").serialize(),
                        type: "post",
                        datatype: "json",
                        success: function (data) {
                            if(data.msg=="ok"){
                                location.href = "${pageContext.request.contextPath}/back/backHome.jsp"
                            }else{
                                $("#msgDiv").html(data.msg)
                            }
                        }
                    })
                }

            })
        })

    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>帅帅气气</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your username and password to log on:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="" method="post" class="login-form" id="loginForm">
                            <span id="msgDiv" style="color: red"></span>
                            <div class="form-group">
                                <span id="av"></span>
                                <label class="sr-only" for="form-username">Username</label>
                                <input required type="text" name="username" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input required type="password" name="password" placeholder="请输入密码..."
                                       class="form-password form-control" id="form-password">
                            </div>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 48px" class="captchaImage"
                                     src="${pageContext.request.contextPath}/verificationCode/getVerificationCode">
                                <input required
                                       style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       type="test" name="userVerificationCode" id="form-code">
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;"
                                   id="loginButtonId" value="登录">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>