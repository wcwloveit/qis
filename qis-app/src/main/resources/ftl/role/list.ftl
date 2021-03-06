<html>
<head>
    <title>角色管理</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>


<div class="actions">
    <div class="btn-group">
        <div class="fileinput fileinput-new" data-provides="fileinput">
           <@shiro.hasPermission name="role-list-create">
                            <a class="btn" href="${rc.contextPath}/role/create"> <#--跳转新增的URL-->
                                <i class="fa fa-plus"></i>
                                <span class="hidden-480">新增</span>
                            </a>
           </@shiro.hasPermission>
<@shiro.hasPermission name="role-list-deleteAll">

                            <a href="javascript:void(0)" class="btn">
                                <i class="fa fa-trash-o"></i>
                                <span class="hidden-480" onclick="deleteList();">批量删除</span>
                            </a>
</@shiro.hasPermission>
<@shiro.hasPermission name="role-list-export">

                            <a class="btn" href="javascript:exportData();">
                                <i class="fa fa-download"></i>
                                <span class="hidden-480">导出</span>
                            </a>
</@shiro.hasPermission>

        </div>
    </div>
</div>

<!-- BEGIN PAGE BREADCRUMB -->
<ul class="page-breadcrumb breadcrumb">
    <li>
        <a href="#">
            <i class="fa fa-chevron-left"></i>
        </a>
    </li>
    <li>
        <a href="#">
            <i class="fa fa-chevron-right"></i>
        </a>
    </li>
    <li>
        <a href="#">
            <i class="fa fa-refresh"></i>
        </a>
    </li>
    <li class="vertical-line"></li>
    <li>
        <a href="${rc.contextPath}/">角色管理</a>
        <i class="fa fa-angle-right"></i>
    </li>
    <li>
        <span class="active">角色</span>
    </li>
</ul>
<!-- END PAGE BREADCRUMB -->

<#if message>
   <div class="note note-danger">
       <p>
           ${(message)!}
       </p>
   </div>
</#if>
<input type="hidden" id="roleId">
<div class="table-container">
    <div class="table-search" id="data_table_search">

        <form role="form">
            <div class="row">
                <div class="col-md-2">
                    <input type="text" class="form-control form-filter" name="search_name" id="search_name"
                           placeholder="角色名称">
                </div>
                <div class="col-md-2">
                    <input type="text" class="form-control form-filter" name="search_role_class_id" id="search_role_class_id"
                           placeholder="角色类型">
                </div>
                <div class="col-md-2">
                    <input type="text" class="form-control form-filter" name="search_desc" id="search_desc"
                           placeholder="描述">
                </div>
                <div class="col-md-4">
                    <div class="input-group date-picker input-daterange">
                        <input type="text" class="form-control border-none form-filter" name="search_startCreatedOn" placeholder="开始日期">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="form-control border-none form-filter" name="search_endCreatedOn" placeholder="结束日期">
                    </div>
                </div>
                <div class="col-md-2">
                    <button class="btn btn-primary filter-submit" value="搜索"
                            onclick="search(this,grid)">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                    <button class="btn btn-default filter-cancel" onclick="resetSearch(this)">
                        <i class="fa fa-remove"></i> 重置
                    </button>
                </div>
            </div>
        </form>

    </div>

    <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
    </table>
    <div id="group_list_div" class="modal fade" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog" style="width:800px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">用户组列表</h4>
                </div>
                <div class="modal-body">
                    <div class="row" id="tableDatas">
                        <div class="col-md-12">
                            <div class="portlet">
                                <div class="portlet-body">
                                    <table class="table table-striped table-bordered table-hover"
                                           id="group_list_table">
                                        <thead>
                                        <tr role="row" class="heading">
                                            <th width="25%">用户组名称</th>
                                            <th width="25%">用户组编号</th>
                                            <th width="25%">用户组描述</th>
                                            <th width="25%">操作</th>
                                        </tr>
                                        <tr role="row" class="filter">

                                            <!-- 登录名 -->
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_name"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_code"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_descr"></td>
                                            <td>
                                                <button class="btn btn-sm yellow filter-submit margin-bottom"><i
                                                        class="fa fa-search"></i> 搜索
                                                </button>
                                                <button class="btn btn-sm red filter-cancel"><i
                                                        class="fa fa-times"></i> 重置
                                                </button>
                                            </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="group_list_div2" class="modal fade" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog" style="width:800px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">用户组列表</h4>
                </div>
                <div class="modal-body">
                    <div class="row" id="tableDatas">
                        <div class="col-md-12">
                            <div class="portlet">
                                <div class="portlet-body">
                                    <table class="table table-striped table-bordered table-hover"
                                           id="group_list_table2">
                                        <thead>
                                        <tr role="row" class="heading">
                                            <th width="25%">用户组名称</th>
                                            <th width="25%">用户组编号</th>
                                            <th width="25%">用户组描述</th>
                                            <th width="25%">操作</th>
                                        </tr>
                                        <tr role="row" class="filter">
                                            <!-- 登录名 -->
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_name"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_code"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_descr"></td>
                                            <td>
                                                <button class="btn btn-sm yellow filter-submit margin-bottom"><i
                                                        class="fa fa-search"></i> 搜索
                                                </button>
                                                <button class="btn btn-sm red filter-cancel"><i
                                                        class="fa fa-times"></i> 重置
                                                </button>
                                            </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
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
    <script src="${rc.contextPath}/assets/global/plugins/plupload/js/plupload.full.min.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
        });
        //$('#message').delay(3000).hide(0);
        /**
         * 数据显示
         * */
        var grid = new Datatable();
        var $attendees_data_table = $("#attendees_data_table");

        <@shiro.hasPermission name="role-list-list">
        grid.init({
            src: $attendees_data_table,
            onError: function (grid) {
            },
            dataTable: {
                "aLengthMenu": [
                    [10, 20, 50, 100, 150, 500],
                    [10, 20, 50, 100, 150, 500]
                ],
                "iDisplayLength": 10,
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/role/list",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7]}
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    {
                        "sWidth": "1%",
                        "sTitle": '<input type="checkbox" class= "checkAllBox" onclick="checkAllBox(this)" title="全选" class="group-checkable" />',
                        "sDefaultContent": "",
                        "mRender": function (data, type, full) {
                            return '<div class="checker"  ><span class=""><input type="checkbox" class="checkboxes" name="checkBox" value="' + full.id + '"></span></div>';
                        }
                    },
                    {
                        "sTitle": "角色名称", "mData": "name", "mRender": function (data, type, row) {
                            return data;
                        }
                    },
                    {
                        "sTitle": "角色类型", "mData": "roleClassesName", "mRender": function (data, type, row) {
                            return data;
                        }
                    },
                    {
                        "sTitle": "描述", "mData": "descr", "mRender": function (data, type, row) {
                            return data;
                        }
                    },
                    {
                        "sTitle": "唯一标识符", "mData": "guidId", "mRender": function (data, type, row) {
                            return data;
                        }
                    },
                    {
                        "sTitle": "创建时间", "mData": "createdOn", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                                return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                            } else {
                                return "";
                            }
                        }
                    },
                    {
                        "sTitle": "状态", "mData": "isEffective", "mRender": function (data, type, row) {
                            if (data != null && "0" == data) {
                                return "失效";
                            }else if(data != null && "1" == data)  {
                                return "生效";
                            }
                        }
                    },
                    {
                        "sTitle": "修改时间", "mData": "modifiedOn", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                                return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                            } else {
                                return "";
                            }
                        }
                    },
                    {
                        "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {
                            var a = '<a href="${rc.contextPath}/role/update/' + row.id + '" class="btn btn-xs blue"  title="编辑" >' +
                                    '<i class="glyphicon glyphicon-pencil"></i>编辑</a>';
                            var b = '<a href="${rc.contextPath}/role/module/' + row.id + '" class="btn btn-xs green"  title="模块管理" >' +
                                    '<i class="fa fa-map"></i>模块管理</a>';
                            var c = '<a class="btn btn-xs yellow" href="javascript:void(0);" onclick="seegroup(\'' + row.id + '\')" title ="查看用户组"><i class="fa fa-search"></i>查看用户组</a>';
                            var d = '<a class="btn btn-xs yellow" href="javascript:void(0);" onclick="togroup(\'' + row.id + '\')" title ="分配用户组"><i class="fa fa-search"></i>分配用户组</a>';
                            var e = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.id + '\')" class="btn btn-xs red"  title="删除" >' +
                                    '<i class="glyphicon glyphicon-trash"></i>删除</a>';

                            if (row.isDeleted == 1) {
                                return "已删除";
                            } else {
                                return a + b + c + d + e;
                            }
                        }
                    }
                ]
            }
        });
        </@shiro.hasPermission>

        var groupGrid = new Datatable();
        var $group_list_table = $("#group_list_table");
        groupGrid.init({
            src: $group_list_table,
            onSuccess: function (groupGrid) {
                console.log(groupGrid);
            },
            onError: function (groupGrid) {
            },
            dataTable: {
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/role/query-group-list",
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3]}
                ],//设置不排序得列
                "aoColumns": [
                    {"sTitle": "用户组名称", "mData": "name"},
                    {"sTitle": "用户组编号", "mData": "code"},
                    {"sTitle": "用户组描述", "mData": "descr"},
                    {
                        "sTitle": "操作", "mData": "id", "sDefaultContent": "", "mRender": function (data, type, row) {
                            return '<a class="delete btn green btn-xs black" href="javascript:leave(' + data + ');"><i class="fa fa-level-down"></i>离开</a>';
                        }
                    }
                ]
            }
        });

        var groupGrid2 = new Datatable();
        var $groupList_data_table2 = $("#group_list_table2");
        groupGrid2.init({
            src: $groupList_data_table2,
            onSuccess: function (groupGrid2) {
                console.log(groupGrid2);
            },
            onError: function (groupGrid2) {
            },
            dataTable: {
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/role/query-group-notinrole",
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3]}
                ],//设置不排序得列
                "aoColumns": [
                    {"sTitle": "用户组名称", "mData": "name"},
                    {"sTitle": "用户组编号", "mData": "code"},
                    {"sTitle": "用户组描述", "mData": "descr"},
                    {
                        "sTitle": "操作", "mData": "id", "sDefaultContent": "", "mRender": function (data, type, row) {
                            return '<a class="delete btn green btn-xs black" href="javascript:join(' + data + ');"><i class="fa fa-level-up"></i>加入</a>';
                        }
                    }
                ]
            }
        });

        function seegroup(id) {
            groupGrid.setAjaxParam("roleId", id);
            $("#roleId").val(id);
            groupGrid.getDataTable().fnDraw();
            $('#group_list_div').modal('show');
        }

        function togroup(id) {
            groupGrid2.setAjaxParam("roleId", id);
            $("#roleId").val(id);
            groupGrid2.getDataTable().fnDraw();
            $('#group_list_div2').modal('show');
        }

        function join(id) {
            bootbox.dialog({
                message: "确认此用户组加入此角色",
                buttons: {
                    success: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            $.ajax({
                                url: '${rc.contextPath}/role/join',
                                type: 'POST',
                                traditional: true,
                                data: {"roleId": $("#roleId").val(), "groupId": id},
                                success: function () {
                                    groupGrid2.getDataTable().fnDraw();
                                    groupGrid2.getDataTable().fnDraw();
                                }
                            });
                        }
                    },
                    main: {
                        label: "取消",
                        className: "gray",
                        callback: function () {
                            $(this).hide();
                        }
                    }
                }
            });
        }

        function leave(id) {
            bootbox.dialog({
                message: "确认此用户组离开此角色",
                buttons: {
                    success: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            $.ajax({
                                url: '${rc.contextPath}/role/leave',
                                type: 'POST',
                                traditional: true,
                                data: {"roleId": $("#roleId").val(), "groupId": id},
                                success: function () {
                                    groupGrid.getDataTable().fnDraw();
                                    groupGrid.getDataTable().fnDraw();
                                }
                            });
                        }
                    },
                    main: {
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
         * 关闭提示信息
         * */
        function alertClose() {
            $(".alert").alert('close');
        }

        /**
         * 提示信息显示
         * */
        jQuery(function ($) {
            var message = "${message?if_exists}", stat = "${success?if_exists}";
            if (null == message || '' == message) {
                $(".alert").alert('close');
            } else {
                if ('true' == stat) {
                    $('#message').removeClass('alert-danger');
                    $('#message').addClass('alert-success');
                    setInterval(alertClose, 3 * 1000);
                } else {
                    $('#message').addClass('alert-danger');
                    $('#message').removeClass('alert-success');
                }
            }
        });

        /**
         * 日期组件
         * */
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            autoclose: true
        });

        /**
         *  选择框选中事件
         * */
        jQuery('#attendees_data_table').on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('span').toggleClass("checked");
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
        function deleteOne(id) {
            bootbox.dialog({
                message: "您是否确认删除?",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/role/deleteOne/' + id,
                                type: 'POST',
                                traditional: true,
                                success: function (data) {
                                    Metronic.stopPageLoading();
                                    grid.getDataTable().fnDraw();
                                    bootbox.alert("操作成功");
                                },
                                error: function (error) {

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

        /**
         * 批量删除
         */
        function deleteList() {
            var ids = [];
            $('#attendees_data_table span.checked >input.checkboxes:checked').each(function () {
                ids.push($(this).val());
            })
            if (ids == '' || ids == null || ids.length == 0) {
                bootbox.alert('请选择需要删除的角色');
                return false;
            }
            bootbox.dialog({
                message: "您是否确认删除角色编号为：" + ids + "的角色",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/role/deleteAll',
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
