<html>
<head>
    <title>用户组</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
<#--<link href="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />-->
<#--<link href="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />-->
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${rc.contextPath}/">用户</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">人员组</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>


<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-cogs"></i>用户组列表</div>
                <div class="actions">
                    <div class="btn-group">
                        <div class="fileinput fileinput-new" data-provides="fileinput">

                            <a class="btn green btn-parent" href="${rc.contextPath}/user/create"> <#--跳转新增的URL-->
                                <i class="fa fa-plus"></i>
                                <span class="hidden-480">新增人员</span>
                            </a>


                        </div>
                    </div>
                </div>
            </div>
            <div class="portlet-body">
            <#if message>
                <div class="alert  <#if message && success='true'>alert-success<#else>alert-danger</#if>">
                    <button data-dismiss="alert" class="close">×</button>
                ${(message)!}
                </div>
            </#if>

                <div class="table-container">
                    <div class="table-actions-wrapper">
                    </div>
                    <div id="data_table_search">

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">姓名</b>
                            <input type="text" class="input-sm form-filter" name="search_User_name"
                                   id="search_User_name" placeholder="名称"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">工号</b>
                            <input type="text" class="input-sm form-filter" name="search_User_descr"
                                   id="search_User_descr" placeholder="工号"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">手机</b>
                            <input type="text" class="input-sm form-filter" name="search_User_mobile"
                                   id="search_User_mobile" placeholder="描述"/>
                        </label>


                        <label style="float:left;">
                            <span> &nbsp;&nbsp;</span>
                            <button class="btn btn-sm yellow margin-bottom filter-submit" value="搜索"
                                    onclick="search(this,grid)"><i
                                    class="fa fa-search"></i> 搜索
                            </button>
                            <button class="btn btn-sm red filter-cancel" onclick="resetSearch(this)"><i
                                    class="fa fa-times"></i> 重置
                            </button>
                        </label>
                    </div>

                    <div>
                        <table class="table table-striped table-bordered table-hover" id="user_data">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<content tag="script">
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.js"></script>
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/scripts/datatable.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/bootbox/bootbox.min.js"
            type="text/javascript"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonUtil.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/common.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonValidation.js"></script>

    <script type="text/javascript">

        $('#message').delay(3000).hide(0);
        /**
         * 数据显示
         * */
        var grid = new Datatable(); //声明 Datatable，里面的数据要塞到 <table>中
        var user_data = $("#user_data"); //user_data<table>的id
        grid.init({
            src: user_data,
            onSuccess: function (grid) {
                console.log(grid);
            },
            onError: function (grid) {
            },
            dataTable: {
                "aLengthMenu": [
                    [10, 20, 50, 100, 150, 500],
                    [10, 20, 50, 100, 150, 500]
                ],
                "iDisplayLength": 10,//页面显示数据数量
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/user/list",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}   //控制表格有多少列
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    {"sTitle": "序号", "mData": "id"},
                    {"sTitle": "ID", "mData": "no"},
                    {"sTitle": "姓名", "mData": "userName"},
                    {"sTitle": "编号", "mData": "userCode"},
                    {"sTitle": "登陆名称", "mData": "loginid"},
                    {
                        "sTitle": "状态", "mRender": function (data, type, full) {
                        if (full.userStatus == 1) {
                            return "离职";
                        } else {
                            return "正常";
                        }

                    }
                    },
                    {"sTitle": "手机号", "mData": "userMobile"},
                    {
                        "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, full) {
                        var a = '<a href="${rc.contextPath}/user/update/' + full.no + '" class="btn default btn-xs green" >编辑</a>';
                        var b = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.no + ',1\')" class="btn btn-xs red"  title="封存" >' +
                                '<i class="glyphicon glyphicon-trash"></i>封存</a>';
                        var c = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.no + ',2\')" class="btn btn-xs red"  title="封存" >' +
                                '<i class="glyphicon glyphicon-trash"></i>解封</a>';
                        if (full.orgStatus == 1) {
                            return a+b;
                        } else {
                            return b+c;
                        }
                    }
                    }


                ]
            }
        });


        /**
         * 重置查询信息
         * @param btn
         * @param excludes
         */
        function resetSearch(btn) {
            $("#data_table_search input[type='text']").val("");
            $("select.form-filter").each(function () {
                $(this).find("option").attr("selected", false);
            });
            search(btn, grid);
        }

        /**
         * 逻辑删除
         * @param id
         */
        function deleteOne(id,type) {
            var message;
            if(type==1){
                message="您确认要封存该用户？"
            }
            if(type==1){
                message="您确认要该用户进行解封吗？"
            }
            bootbox.dialog({
                message: message,
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            $.ajax({
                                url: '${rc.contextPath}/user/sealUser',
                                type: 'POST',
                                data:{"id":id,"type":type},
                                traditional: true,
                                success: function (msg) {
                                    console.log(msg);
                                    if(msg&&msg.stat){
                                        bootbox.alert('以处理完成！');

                                        location.href='${rc.contextPath}/user/index';
                                    }else{
                                        bootbox.alert(msg.msg);
                                    }
                                },

                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: "gray",
                        callback: function () {
                            $(this).hide();
                        }
                    }
                }
            });
        }





        /**
         * 批量删除
         */
        function deleteList() {
            var ids = [];
            var codes = [];
            $('#attendees_data_table span.checked >input.checkboxes:checked').each(function () {
                ids.push($(this).val());
                codes.push($(this).parents("tr").find("td").eq(1).text());
            })
            if (ids == '' || ids == null || ids.length == 0) {
                bootbox.alert('请选择需要删除的账号');
                return false;
            }
            bootbox.dialog({
                message: "您是否确认删除名称为：" + codes + "的用户组",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/userGroup/deleteAll',
                                type: 'POST',
                                data: {"ids": ids},
                                dataType: "json",
                                traditional: true,
                                success: function (msg) {
                                    Metronic.stopPageLoading();
                                    if (msg && msg.stat) {
                                        alertHint('删除成功');
                                        grid.getDataTable().fnDraw();
                                    } else {
                                        bootbox.alert('删除失败');
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: "gray",
                        callback: function () {
                            $(this).hide();
                        }
                    }
                }
            });
        }


    </script>
</content>
</html>
