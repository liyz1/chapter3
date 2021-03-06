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
            colNames:["id","产品名称","纸张类型","产品数量","订单创建时间","单价(元)","预计开工时间",
                "预计完工时间","订单状态","打包方式","业务员","设计师","业务助理","所属客户","备注"],
            //列的模型,和数据库字段严格对应
            colModel:[
                {name:"id",search:false,align:"center",hidden:true},
                {name:"orderName",editable: true,align:"center"},
                {name:"paperType",editable: true,search:false,width:140,align:"center"},
                {name:"amount",editable: true,search:false,width:100,align:"center"},
                {name:"createDate",search:false,width:250,align:"center"},
                {name:"price",search:false,editable: true,width:100,align:"center"},
                {name:'prepareBeginDate',search:false,width:250,editable:true,edittype:'text',editrules:{required:true},
                    editoptions: {
                        dataInit:function(e){$(e).click(function () {
                            WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});
                        })
                        }
                    },align:"center"
                },

                {name:'prepareCompleteDate',search:false,width:250,editable:true,edittype:'text',editrules:{required:true},
                    editoptions: {
                        dataInit:function(e){$(e).click(function () {
                            WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});
                        })
                        }
                    },align:"center"
                },

                {name:"status",editable: true,search:false,width:140,align:"center",edittype:'select',
                    formatter:function(value,options,rowData){
                        if(value == '1'){return '设计';}
                        if(value == '2'){return '印刷';}
                        if(value == '3'){return '覆膜';}
                        if(value == '4'){return '烫金';}
                        if(value == '5'){return '过油';}
                        if(value == '6'){return '压纹';}
                        if(value == '7'){return '模切';}
                        if(value == '8'){return '粘盒';}
                        if(value == '9'){return '打包';}
                        if(value == '10'){return '发货';}
                        if(value == '11'){return '完成';}
                    },
                      editoptions: {value:{1:'设计',2:'印刷',3:'覆膜',4:'烫金',5:'过油',6:'压纹',
                      7:'模切',8:'粘盒',9:'打包',10:'发货',11:'完成'}}
                },
                {name:"packageMode",editable: true,search:false,width:140,edittype:'select',
                      formatter:function(value,options,rowData){
                        if(value == '1'){return '装箱';}
                        if(value == '2'){return '装袋';}
                      },
                      editoptions: {value:{1:'装箱',2:'装袋'}},align:"center"
                },
                {name:"salesman",editable: true,align:"center",search:false,width:140,edittype:'select',editoptions:{dataUrl:"${pageContext.request.contextPath}/StaffController/selectAllSalesmanFromStaff"}},
                {name:"designer",editable: true,align:"center",search:false,width:140,edittype:'select',editoptions:{dataUrl:"${pageContext.request.contextPath}/StaffController/selectAllDesignerFromStaff"}},
                {name:"businessAssistant",editable: true,align:"center",search:false,width:140,edittype:'select',editoptions:{dataUrl:"${pageContext.request.contextPath}/StaffController/selectAllBusinessAssistantFromStaff"}},
                {name:"company",editable: true,align:"center",search:false,width:250,edittype:'select',editoptions:{dataUrl:"${pageContext.request.contextPath}/userController/selectCompanyFromUser"}},
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
        location.href="${pageContext.request.contextPath}/OrderController/orderPrint";
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


