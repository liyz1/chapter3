<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#userList").jqGrid({
            url: "${pageContext.request.contextPath}/userController/selectAllUserFromUser",
            datatype: "json",
            colNames: ["id", "客户公司名称", "客户姓名", "盐值", "密码", "产品名称", "客户地址", "客户电话"],
            colModel: [
                {name: "id"},
                {name: "company"},
                {name: "username"},
                {name: "salt"},
                {name: "password"},
                {name: "nickname"},
                {name: "address"},
                {name: "phone"}
            ],
            styleUI: "Bootstrap",
            pager: "#albumPager",
            rowNum: 2,
            rowList: [2, 5, 10],
            autowidth: true,
            viewrecords: true,
            height: '60%',
            multiselect: true,
            rownumbers: true,
        })
    })
    function showModal() {
        location.href = "${pageContext.request.contextPath}/userController/selectAllUserFromUser"
    }

</script>
<h3>用户管理</h3>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">用户列表</a></li>
        <li><a href="javascript:void(0)" onclick="showModal()"><b>生成报表</b></a></li>
    </ul>
</div>
<table id="userList"></table>

