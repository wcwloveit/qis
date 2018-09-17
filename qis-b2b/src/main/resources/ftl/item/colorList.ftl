<html>
<head>
    <title>众筹项目</title>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${rc.contextPath}/">颜色管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">颜色列表</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-cogs"></i>产品：${(product.modelName)!} 配置：${(mode.bname)!} 颜色列表</div>
                <div class="actions">
                    <div class="btn-group">
                        <div class="fileinput fileinput-new" data-provides="fileinput">
                            <a class="btn green btn-parent" href="${rc.contextPath}/itemColor/create-${(product.id)!}-${(mode.id)!}">
                                <i class="fa fa-plus"></i>
                                <span class="hidden-480">新增颜色</span>
                            </a>
                            <a href="javascript:history.back(-1)" class="btn grey">返回</a>
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
                <div id="tip"></div>
                <div class="table-container">
                    <div class="table-actions-wrapper">
                    </div>
                    <div id="data_table_search">
                    </div>
        </div>

        <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
        </table>
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
    <script src="${rc.contextPath}/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        $('#message').delay(3000).hide(0);
        /**
         * 数据显示
         * */
        var grid = new Datatable();
        var $attendees_data_table = $("#attendees_data_table");
        grid.init({
            src: $attendees_data_table,
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
                "iDisplayLength": 10,
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/itemColor/list-${(mode.id)!}",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5,6,7,8]}
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    {"sTitle": "产品名称",  "sDefaultContent": "", "mRender": function (data, type, row) {
                            return "${(product.modelName)!}";
                    }},
                    {"sTitle": "配置别名", "sDefaultContent": "", "mRender": function (data, type, row) {
                        return "${(mode.bname)!}";
                    }},
                    {"sTitle": "颜色编号", "mData": "colorCode"},
                    {"sTitle": "颜色名称", "mData": "colorName"},
                    {"sTitle": "价格", "mData": "price"},
                    {"sTitle": "组织编码", "mData": "orgCode"},
                    {
                        "sTitle": "创建时间", "mData": "createdTime", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                                return new Date(data).Format("yyyy-MM-dd hh:mm");
                            } else {
                                return "";
                            }
                        }
                    },
                    {"sTitle": "创建人", "mData": "createdBy"},
                    {
                        "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, full) {
                        var a = '<a href="${rc.contextPath}/itemColor/update-${(product.id)!}-${(mode.id)!}/' + full.id
                                + '" class="btn btn-xs blue"  title="编辑" >' +
                                '<i class="glyphicon glyphicon-pencil"></i>编辑</a>';

                        var b ='<a href="${rc.contextPath}/itemPic/index-4-' + full.id
                                + '" class="btn btn-xs green"  title="颜色图片" >' +
                                '<i class="glyphicon glyphicon-picture"></i>颜色图片</a>';
                        var c = '<a href="javascript:void(0);" onclick="deleteOne(\'' + full.id
                                + '\')" class="btn btn-xs red"  title="删除" >' +
                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';

                        var e = '<a href="${rc.contextPath}/itemColor/update-${(product.id)!}-${(mode.id)!}/' + full.id
                                + '" class="btn btn-xs grey"  title="编辑" >' +
                                '<i class="glyphicon glyphicon-pencil"></i>编辑</a>';

                        var f ='<a href="${rc.contextPath}/itemPic/index-4-' + full.id
                                + '" class="btn btn-xs grey"  title="颜色图片" >' +
                                '<i class="glyphicon glyphicon-picture"></i>颜色图片</a>';
                        var g = '<a href="javascript:void(0);" onclick="deleteOne(\'' + full.id
                                + '\')" class="btn btn-xs grey"  title="删除" >' +
                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';

                        if(full.isSynchro==1){
                            return "已删除";
                        }else{
                            return a+b+c;
                        }
                    }
                    }
                ]
            }
        });

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
        })

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
                                url: '${rc.contextPath}/itemColor/deleteOne-' + id,
                                type: 'POST',
                                traditional: true,
                                success: function (data) {
                                    console.log(data);
                                    Metronic.stopPageLoading();
                                    grid.getDataTable().fnDraw();
                                },
                                error:function(error){

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
