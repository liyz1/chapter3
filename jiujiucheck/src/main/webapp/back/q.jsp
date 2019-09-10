<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#articleList").jqGrid({
            url: "${pageContext.request.contextPath}/article/paging",
            editurl: "${pageContext.request.contextPath}/article/edit",
            datatype: "json",
            colNames: ["id", "标题", "作者", "创建时间", "内容", "上师id", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "author", editable: true},
                {name: "createDate"},
                {name: "content", hidden: true},
                {name: "guruId", hidden: true},
                {
                    name: "", edittype: "file",
                    formatter: function (cellValue, option, value) {
                        return "<a href='#' onclick=\"lookModal('" + value.id + "')\">查看详情</a>";
                    }
                }
            ],
            styleUI: "Bootstrap",
            pager: "#articlePager",
            rowNum: 2,
            rowList: [2, 5, 10],
            autowidth: true,
            viewrecords: true,
            height: '60%',
            multiselect: true,
            rownumbers: true
        }).jqGrid("navGrid", "#articlePager",
            {search: false, add: false, edit: false, deltext: "删除"},
            {
                //修改
            },
            {
                //添加
            },
            {
                //删除

            }
        )
    })


    function showModal() {
        $("#myModal").modal("show");

        /*将jquary对象转化为js对象并清空*/
        $("#addArticleFrom")[0].reset();
        /*清空文本域*/
        KindEditor.html("#editor", "");

        $("#modal_footer").html(
            "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"addModal()\">保存</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>")
        KindEditor.create('#editor', {
            uploadJson: "${pageContext.request.contextPath}/kindeditor/uploadImg",
            filePostName: "img",//默认是imgFile
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAll",
            allowFileManager: true,
            resizeType: 1,
            /*失去焦点时将值同步到文本框*/
            afterBlur: function () {
                this.sync()
            }
        });
    }

    function addModal() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/edit",
            datatype: "json",
            type: "post",
            data: $("#addArticleFrom").serialize(),
            success: function () {
                $("#articleList").trigger("reloadGrid");
            }
        })
    }

    function lookModal(id) {
        var value = $("#articleList").jqGrid("getRowData", id);
        $("#myModal").modal("show");
        $("#title").val(value.title);
        $("#author").val(value.author);
        $("#modal_footer").html(
            "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"saveModal('" + value.id + "')\">保存</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>")

        KindEditor.create('#editor', {
            uploadJson: "${pageContext.request.contextPath}/kindeditor/uploadImg",
            filePostName: "img",//默认是imgFile
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAll",
            allowFileManager: true,
            resizeType: 1,
            afterBlur: function () {
                this.sync()
            }
        });
        $("#editor").val(value.content);
        KindEditor.html("#editor", value.content);
    }

    function saveModal(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/update?id=" + id,
            datatype: "json",
            type: "post",
            data: $("#addArticleFrom").serialize(),
            success: function () {
                $("#articleList").trigger("reloadGrid");
            }
        })
    }

</script>
<h3>文章管理</h3>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">文章列表</a></li>
        <li><a href="javascript:void(0)" onclick="showModal()"><b>添加文章</b></a></li>
    </ul>
</div>
<table id="articleList"></table>
<div id="articlePager" style="height: 50px"></div>
<div id="articleMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <strong>Warning!</strong>
</div>

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!--用来关闭模态框的属性:data-dismiss="modal"-->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">编辑用户信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <form action="" class="form-horizontal" id="addArticleFrom">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <input type="text" name="author" id="author" placeholder="请输入作者" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea id="editor" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                    <input id="addInsertImg" name="insertImg" hidden>
                </form>
            </div>
            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer">
            </div>
        </div>
    </div>
</div>
