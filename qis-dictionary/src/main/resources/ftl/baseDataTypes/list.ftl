<html>
<head>
    <title>数据字典</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>


<div class="actions">
    <div class="btn-group">
        <div class="fileinput fileinput-new" data-provides="fileinput">
            <@shiro.hasPermission name="dictionary-dictionary-create">
                <a class="btn"
                   href="${rc.contextPath}/dictionary/create"> <#--跳转新增的URL-->
                    <i class="fa fa-plus"></i>
                    <span class="hidden-480">新增</span>
                </a>
            </@shiro.hasPermission>

            <@shiro.hasPermission name="dictionary-dictionary-deleteAll">
                <a href="javascript:void(0)" class="btn">
                    <i class="fa fa-trash-o"></i>
                    <span class="hidden-480" onclick="deleteList();">删除</span>
                </a>
            </@shiro.hasPermission>

            <@shiro.hasPermission name="dictionary-dictionary-export">
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

<div class="table-container">
    <div class="table-search" id="data_table_search">

        <form role="form">
            <div class="row">
                <div class="col-md-2">
                    <input type="text" class="form-control form-filter" name="search_name" id="search_name"
                           placeholder="名称">
                </div>
                <div class="col-md-2">
                    <input type="text" class="form-control form-filter" name="search_type_value" id="search_type_value"
                           placeholder="值">
                </div>
                <div class="col-md-2">
                    <input type="text" class="form-control form-filter" name="search_desc" id="search_desc"
                           placeholder="描述">
                </div>
                <div class="col-md-4">
                    <div class="input-group date-picker input-daterange">
                        <input type="text" class="form-control border-none form-filter" name="from" placeholder="开始日期">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="form-control border-none form-filter" name="to" placeholder="结束日期">
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

        <@shiro.hasPermission name="dictionary-dictionary-list">
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
                "sAjaxSource": "${rc.contextPath}/dictionary/list",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7]}
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><''<'paddinglr10'pli><'paddinglr10'>r>>",//dataTable翻页,只保留表格底部翻页样式
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
                        "sTitle": "名称", "mData": "name", "mRender": function (data, type, row) {
                        var a = <@shiro.hasPermission name="dictionary-dictionary-edit">
                                 '<a href="${rc.contextPath}/dictionary/update/' + row.id + '" class="btn"  title="编辑" >' +
                                    '<i class="fa fa-edit"></i></a>';
                            </@shiro.hasPermission>
                            var b = '<a href="${rc.contextPath}/dictionary/baseData/index/' + row.id + '" class="btn"  title="编辑" >' +
                                    '<i class="fa fa-balance-scale"></i></a>';
                            var c = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.id + '\')" class="btn"  title="删除" >' +
                                    '<i class="fa fa-trash-o"></i></a>';

                            if (row.isDeleted == 1) {
                                return data + '<span class="pull-right table-action" >' + a + '</span>';
                            } else {
                                return data + '<span class="pull-right table-action" >' + a + b + c + '</span>';
                            }

                        }
                    },
                    {
                        "sTitle": "值", "mData": "typeValue", "mRender": function (data, type, row) {
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
                        "sTitle": "状态", "mData": "isDeleted", "mRender": function (data, type, row) {
                            if (data == '0') {
                                return "存在";
                            } else if (data == '1') {
                                return "已删除";
                            }
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
                        "sTitle": "修改时间", "mData": "modifiedOn", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                                return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                            } else {
                                return "";
                            }
                        }
                    }
                        <#--}},-->
                    <#--{ "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {-->
                        <#--var a = '<@shiro.hasPermission name="dictionary-dictionary-edit"> <a href="${rc.contextPath}/dictionary/update/' + row.id + '" class="btn btn-xs blue"  title="编辑" >' +-->
                                    <#--'<i class="glyphicon glyphicon-pencil"></i>编辑</a></@shiro.hasPermission>';-->
                            <#--var b = '<a href="${rc.contextPath}/dictionary/baseData/index/' + row.id + '" class="btn btn-xs green"  title="编辑" >' +-->
                                    <#--'<i class="fa fa-balance-scale"></i>树</a>';-->
                            <#--var c = '<@shiro.hasPermission name="dictionary-dictionary-delete"><a href="javascript:void(0);" onclick="deleteOne(\'' + row.id + '\')" class="btn btn-xs red"  title="删除" >' +-->
                                    <#--'<i class="glyphicon glyphicon-trash"></i>删除</a></@shiro.hasPermission>';-->
                            <#--if(row.isDeleted==1){-->
                                <#--return a;-->
                            <#--}else{-->
                                <#--return a+b+c;-->
                            <#--}-->
                        }}
                ]
            }
        });

        $($attendees_data_table).on("mouseenter", "tr", function () {
            $(this).find('.table-action').css("visibility", "visible");
        })

        $($attendees_data_table).on("mouseleave", "tr", function () {
            $(this).find('.table-action').css("visibility", "hidden");
        })

        </@shiro.hasPermission>

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
                                url: '${rc.contextPath}/dictionary/deleteOne/' + id,
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
                bootbox.alert('请选择需要删除的类型');
                return false;
            }
            bootbox.dialog({
                message: "您是否确认删除类型编号为：" + ids + "的类型",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/dictionary/delete-all',
                                type: 'POST',
                                data: {"ids": ids},
                                dataType: "json",
                                traditional: true,
                                success: function (msg) {
                                url:'${rc.contextPath}/dictionary/deleteAll',
                                type:'POST',
                                data:{"ids":ids},
                                dataType:"json",
                                traditional:true,
                                success:function(msg){
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
