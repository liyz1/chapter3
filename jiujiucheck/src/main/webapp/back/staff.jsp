<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#staffList").jqGrid({
            //资源获取地址
            url:"${pageContext.request.contextPath}/StaffController/paging",
            //操作地址
            editurl:"${pageContext.request.contextPath}/StaffController/edit",
            //数据类型
            datatype:"json",
            //列的标题，也就是表格的字段名
            colNames:["id","姓名","电话","用户名","密码","盐值","状态","职位类型","备注"],
            //列的模型,和数据库字段严格对应
            colModel:[
                {name:"id",search:false,align:"center",hidden:true},
                {name:"name",editable: true,align:"center"},
                {name:"phone",editable: true,search:false,width:140,align:"center"},
                {name:"username",editable: true,search:false,width:100,align:"center"},
                {name:"password",editable: true,search:false,width:200,align:"center",hidden:true},
                {name:"salt",search:false,editable: false,width:100,align:"center",hidden:true},
                {name:"status",editable: true,search:false,align:"center",edittype:'select',
                    formatter:function(value,options,rowData){
                        if(value == '1'){return '激活';}
                        if(value == '2'){return '冻结';}
                    },
                    editoptions: {value:{1:'激活',2:'冻结'}},},
                {name:"position",editable: true,search:false,edittype:'select',
                    formatter:function(value,options,rowData){
                        if(value == '1'){return '业务员';}
                        if(value == '2'){return '设计师';}
                        if(value == '3'){return '业务助理';}
                        if(value == '4'){return '其它';}
                    },
                    editoptions: {value:{1:'业务员',2:'设计师',3:'业务助理',4:'其它'}},align:"center"},
                {name:"remake",editable: true,search:false,fixed: true,align:"center"}
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
<h3>员工管理</h3>
<div>
    <!--导航选项卡-->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">员工列表</a></li>
        <li><a href="javascript:void(0)" onclick="showModal()"><b>生成报表</b></a></li>
    </ul>
</div>
<table id="staffList"></table>
<div id="userPager" style="height: 40px"></div>


