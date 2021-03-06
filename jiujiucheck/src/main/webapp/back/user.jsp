<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#userList").jqGrid({
            //资源获取地址
            url:"${pageContext.request.contextPath}/userController/paging",
            //操作地址
            editurl:"${pageContext.request.contextPath}/userController/edit",
            //数据类型
            datatype:"json",
            //列的标题，也就是表格的字段名
            colNames:["id","公司名称","用户名","密码","客户名称","注册时间","状态","发货地址","联系电话","备注"],
            //列的模型,和数据库字段严格对应
            colModel:[
                {name:"id",search:false,align:"center",hidden:true},
                {name:"company",align:"center",editable: true},
                {name:"username",align:"center",editable: true,search:false},
                {name:"password",align:"center",editable: true,search:false,hidden:true},
                {name:"nickname",align:"center",editable: true},
                {name:"registerTime",align:"center",editable: false,search:false},
                {name:"status",align:"center",editable: true,search:false,edittype:'select',
                    formatter:function(value,options,rowData){
                        if(value == '1'){return '激活';}
                        if(value == '2'){return '冻结';}
                    },
                    editoptions: {value:{1:'激活',2:'冻结'}}},
                {name:"address",editable: true,align:"center",search:false},
                {name:"phone",align:"center",editable: true,search:false},
                {name:"remark",editable: true,align:"center",search:false}
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
<h3>用户管理</h3>
<div>
    <!--导航选项卡-->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户列表</a></li>

    </ul>
</div>
<table id="userList"></table>
<div id="userPager" style="height: 40px"></div>


