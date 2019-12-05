<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#orderList").jqGrid({
            //资源获取地址
            url:"${pageContext.request.contextPath}/OrderController/paging",
            //操作地址
            editurl:"${pageContext.request.contextPath}/OrderController/edit",
            //数据类型
            datatype:"json",
            //列的标题，也就是表格的字段名
            colNames:["id","产品名称","纸张类型","产品数量","订单创建时间","单价","预计开工时间",
                "预计完工时间","订单状态","打包方式","业务员","所属用户id","备注"],
            //列的模型,和数据库字段严格对应
            colModel:[
                {name:"id",search:false},
                {name:"orderName",editable: true},
                {name:"paperType",editable: true,search:false},
                {name:"amount",editable: true,search:false},
                {name:"createDate",search:false},
                {name:"price",search:false,editable: true},
                {name:"prepareBeginDate",editable: true,search:false},
                {name:"prepareCompleteDate",editable: true,search:false},
                {name:"status",editable: true,search:false},
                {name:"packageMode",editable: true,search:false,edittype:'select',editoptions: {value:{1:'装箱',2:'装袋'}}},
                {name:"salesman",editable: true,search:false},
                {name:"userId",editable: true,search:false},
                {name:"remake",editable: true,search:false}
            ],
            styleUI:"Bootstrap",    //采用Bootstrap风格
            pager:"#userPager",     //分页。需要给一个div
            rowNum:10,              //每页展示多少条
            rowList:[10,20,30],     //下拉框。自己选择每页展示多少条
            autowidth:true,         //自适应宽度
            viewrecords:true,       //显示总记录条数
            height:'60%',           //高度
            multiselect:true,       //多选按钮，批量删除使用
            rownumbers:true,        //显示行号
        }).jqGrid("navGrid","#userPager",
            {
                addtext:"添加",
                edittext:"修改",
                deltext:"删除",
                refreshtext:"刷新",
                search:true},
            {
                //修改
            },
            {
                //添加
            },
            {
                //删除
            }
        );
    })
    function showModal() {
        location.href="${pageContext.request.contextPath}/user/print"
    }
</script>
<h3>订单管理</h3>
<div>
    <!--导航选项卡-->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">订单列表</a></li>
        <li><a href="javascript:void(0)" onclick="showModal()"><b>生成报表</b></a></li>
    </ul>
</div>
<table id="orderList"></table>
<div id="userPager" style="height: 40px"></div>


