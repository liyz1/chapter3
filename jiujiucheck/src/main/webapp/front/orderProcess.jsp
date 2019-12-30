<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>进度</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <style type="text/css">
        .demo{
            padding: 2em 0;
            background: linear-gradient(to right, #2c3b4e, #4a688a, #2c3b4e);
        }
        .progress{
            height: 25px;
            background: #262626;
            padding: 5px;
            overflow: visible;
            border-radius: 20px;
            border-top: 1px solid #000;
            border-bottom: 1px solid #7992a8;
            margin-top: 50px;
        }
        .progress .progress-bar{
            border-radius: 20px;
            position: relative;
            animation: animate-positive 2s;
        }
        .progress .progress-value{
            display: block;
            padding: 3px 7px;
            font-size: 13px;
            color: #fff;
            border-radius: 4px;
            background: #191919;
            border: 1px solid #000;
            position: absolute;
            top: -40px;
            right: -10px;
        }
        .progress .progress-value:after{
            content: "";
            border-top: 10px solid #191919;
            border-left: 10px solid transparent;
            border-right: 10px solid transparent;
            position: absolute;
            bottom: -6px;
            left: 26%;
        }
        .progress-bar.active{
            animation: reverse progress-bar-stripes 0.40s linear infinite, animate-positive 2s;
        }
        @-webkit-keyframes animate-positive{
            0% { width: 0; }
        }
        @keyframes animate-positive{
            0% { width: 0; }
        }
    </style>
</head>
<body>
<div class="demo">
    <div class="container" id = "container">

    </div>
</div>


<script src="../back/assets/js/jquery-2.2.1.min.js" type="text/javascript"></script>
<script>
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/OrderController/selectOrderByUserId",
            data: $("#loginForm").serialize(),
            type: "post",
            datatype: "json",
            success: function (data) {

                console.log(data)

                /*动态创建如下所示的页面

                <div style="color:white" class=" col-md-6" id="orderLeft">
					产品名称：潜金散<br>
					纸张类型：350白卡<br>
					产品数量：10000<br>
					预计开工时间：2019-09-09 12：30：30
				</div>

				<div style="color:white" class=" col-md-6" id="orderRight">
					打包方式：装箱<br>
					业务员：杜兆运<br>
					备注：参样<br>
					预计完工时间：2019-09-09 12：30：30
				</div>

		        <div class="row">
			        <div class=" col-md-12">
				        <br></br>
				        <div class="progress" class=" col-md-12">
					        <div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 25%;">
						        <div class="progress-value">85%</div>
					        </div>
				        </div>
			        </div>
		        </div>

                */
                if(data.length==0){
                    alert("暂无订单信息！")
                }else{
                    for (var i=0;i<data.length;i++){
                        //orderLeft
                        var orderLeft = $("<div>"); //创建div2标签
                        orderLeft.attr('class','col-md-6'); //设置标签属性
                        orderLeft.attr('style','color:white');

                        orderLeft.html("产品名称："+data[i].orderName+"<br>纸张类型："+data[i].paperType+"<br>产品数量："+data[i].amount+"<br>预计开工时间："+data[i].prepareBeginDate);

                        //orderRight
                        var orderRight = $("<div>"); //创建div2标签
                        orderRight.attr('class','col-md-6'); //设置标签属性
                        orderRight.attr('style','color:white');
                        orderRight.html("打包方式："+data[i].packageMode+"<br>业务员："+data[i].salesman+"<br>备注："+data[i].remark+"<br>预计完工时间："+data[i].prepareCompleteDate);

                        //进度条
                        var div1 = $("<div>"); //创建div标签
                        div1.attr('class','progress-value'); //设置标签属性
                        var processName = data[i].status;
                        if(processName == '1'){processName = '设计';}
                        if(processName == '2'){processName = '印刷';}
                        if(processName == '3'){processName = '覆膜';}
                        if(processName == '4'){processName = '烫金';}
                        if(processName == '5'){processName = '过油';}
                        if(processName == '6'){processName = '压纹';}
                        if(processName == '7'){processName = '模切';}
                        if(processName == '8'){processName = '粘盒';}
                        if(processName == '9'){processName = '打包';}
                        if(processName == '10'){processName = '发货';}
                        div1.html(processName);

                        var div2 = $("<div>"); //创建div2标签
                        div2.attr('id','div2'); //设置标签属性
                        div2.attr('class','progress-bar progress-bar-success progress-bar-striped active');
                        var processNumber = (data[i].status*10).toFixed(1);
                        processNumber += "%";
                        div2.attr('style','width:'+processNumber+';');

                        var td = $("<td>");
                        var div3 = $("<div>"); //创建div3标签
                        div3.attr('id','div3');
                        div3.attr('class','progress');

                        var div4 = $("<div>"); //创建div4标签
                        div4.attr('id','div4');
                        div4.attr('class','col-md-12');

                        var progress = $("<div>"); //创建div4标签
                        progress.attr('id','progress');
                        progress.attr('class','row');

                        div2.append(div1);
                        div3.append(div2);
                        div4.append(td);
                        div4.append(div3);
                        progress.append(div4);
                        $("#container").append(orderLeft);
                        $("#container").append(orderRight);
                        $("#container").append(progress);
                    }
                }
            }
        })
    })
</script>

</body>
</html>