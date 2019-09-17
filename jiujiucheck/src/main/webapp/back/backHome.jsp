<%@page pageEncoding="UTF-8" contentType="text/html;utf-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>帅帅气气</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../boot/css/bootstrap.css">
    <%-- jqgrid相关css --%>
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../boot/js/bootstrap.js"></script>
    <%-- jqgrid  js --%>
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>

    <%-- ajax文件上传--%>
    <script src="../boot/js/ajaxfileupload.js"></script>

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <h1>持名法洲管理系统</h1>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li><h5>欢迎：${sessionScope.login.username}</h5></li>
                <li><h5><a href="${pageContext.request.contextPath}/admin/exit">退出登录<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a></h5></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="row">
    <div class="col-md-2">
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('user.jsp')">用户列表</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" >
                            上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse">
                    <div class="panel-body">
                        查看上师
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapsethree" >
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapsethree" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('article.jsp')">查看文章</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapsefour" >
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapsefour" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('album.jsp')">查看专辑</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapsesix" >
                            轮播图
                        </a>
                    </h4>
                </div>
                <div id="collapsesix" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('banner.jsp')">轮播图管理</a></li>
                    </div>
                </div>
            </div>



            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapsesix" >
                            管理员管理
                        </a>
                    </h4>
                </div>
                <div id="collapseserven" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('banner.jsp')">轮播图管理</a></li>
                    </div>
                </div>
            </div>



        </div>

    </div>
    <div class="col-md-10">
        <div class="jumbotron">
            <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎来到持名法洲后台管理系统</h3>
        </div>
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="${pageContext.request.contextPath}/images/22.png" width="1366" height="450" alt="...">
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/images/22.png" width="1366" height="450" alt="...">
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/images/22.png" width="1366" height="450" alt="...">
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>

        </div>

    </div>
</div>

<nav class="navbar navbar-default navbar-fixed-bottom">
    <div>
        <h6 class="text-center">@持明法洲</h6>
    </div>
</nav>
</body>
</html>