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
                <a href="${rc.contextPath}/">产品管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">产品列表</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-cogs"></i>产品列表</div>
                <div class="actions">
                    <div class="btn-group">
                        <div class="fileinput fileinput-new" data-provides="fileinput">
                            <a class="btn green btn-parent" href="${rc.contextPath}/item/create">
                                <i class="fa fa-plus"></i>
                                <span class="hidden-480">新增产品</span>
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
                <div id="tip"></div>
                <div class="table-container">
                    <div class="table-actions-wrapper">
                    </div>
                    <div id="data_table_search">
                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">产品编号</b>
                            <input type="text" class="input-sm form-filter" name="search_modelCode"
                                   id="search_modelCode" placeholder="车型编号"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">产品名称</b>
                            <input type="text" class="input-sm form-filter" name="search_modelName"
                                   id="search_modelName" placeholder="车型名称"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">组织编号</b>
                            <input type="text" class="input-sm form-filter" name="search_orgCode"
                                   id="search_orgCode" placeholder="组织编号"/>
                        </label>

                    <#--<label style="float:left;margin-right:5px;">-->
                            <#--<b class="form-control input-inline" style="border: 0px; text-align: left;">车型规格</b>-->
                            <#--<input type="text" class="input-sm form-filter" name="search_modelSpecification"-->
                                   <#--id="search_modelSpecification" placeholder="车型规格"/>-->
                        <#--</label>-->
<!--


                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">颜色编号</b>
                            <input type="text" class="input-sm form-filter" name="search_colorCode"
                                   id="search_colorCode" placeholder="颜色编号"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">颜色名称</b>
                            <input type="text" class="input-sm form-filter" name="search_colorName"
                                   id="search_colorName" placeholder="颜色名称"/>
                        </label>
-->
                        <label style="float:left;margin-right:5px;">
                            <div class="form-control input-inline"
                                 style="float: left;padding-top: 5px;border: 0px; text-align: left;"
                            ">创建时间</div>
                    <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                         style="width: 123px;float: left;">
                        <input id="search_startCreatedOn" name="search_startCreatedOn" style=" width: 90px; padding: 2px; "
                               type="text" class="form-filter input-sm" placeholder="开始日期" readonly>
                        <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                class="fa fa-calendar"></i></button></span>
                    </div>
                    <div style="float:left;">~</div>
                    <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                         style="width: 123px;float: left;">
                        <input id="search_endCreatedOn" name="search_endCreatedOn" style=" width:90px; padding: 2px;"
                               type="text" class="form-filter input-sm" placeholder="结束日期" readonly>
                        <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                class="fa fa-calendar"></i></button></span>
                    </div>
                    </label>

                    <label style="float:left;margin-right:5px;">
                        <div class="form-control input-inline"
                             style="float: left;padding-top: 5px;border: 0px; text-align: left;"
                        ">更新时间</div>
                <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                     style="width: 123px;float: left;">
                    <input id="search_startUpdatedOn" name="search_startUpdatedOn" style=" width: 90px; padding: 2px; "
                           type="text" class="form-filter input-sm" placeholder="开始日期" readonly>
                    <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                            class="fa fa-calendar"></i></button></span>
                </div>
                <div style="float:left;">~</div>
                <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                     style="width: 123px;float: left;">
                    <input id="search_endUpdatedOn" name="search_endUpdatedOn" style=" width:90px; padding: 2px;"
                           type="text" class="form-filter input-sm" placeholder="结束日期" readonly>
                    <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                            class="fa fa-calendar"></i></button></span>
                </div>
                </label>


                <label style="float:left;margin-right:5px;">
                    <div class="form-control input-inline"
                         style="float: left;padding-top: 5px;border: 0px; text-align: left;"
                    ">初始价格</div>
            <div class="input-group" style="width: 90px;float: left;">
                <input id="search_startPrice" name="search_startPrice" style=" width: 90px; padding: 2px; "
                       type="text" class="form-filter input-sm" placeholder="From">
            </div>
            <div style="float:left;">~</div>
            <div class="input-group" style="width: 90px;float: left;">
                <input id="search_endPrice" name="search_endPrice" style=" width: 90px; padding: 2px; "
                       type="text" class="form-filter input-sm" placeholder="To">
            </div>

            </label>


            <label style="float:left;">
                <span> &nbsp;&nbsp;</span>
                <button class="btn btn-sm yellow margin-bottom filter-submit" value="搜索" onclick="search(this,grid)"><i
                        class="fa fa-search"></i> 搜索
                </button>
                <button class="btn btn-sm red filter-cancel" onclick="resetSearch(this)"><i class="fa fa-times"></i> 重置
                </button>
            </label>
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
                "sAjaxSource": "${rc.contextPath}/item/list",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]}
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    {"sTitle": "产品编号", "mData": "modelCode"},
                    {"sTitle": "产品名称", "mData": "modelName"},
                //    {"sTitle": "颜色编号", "mData": "colorCode"},
                //    {"sTitle": "颜色名称", "mData": "colorName"},
                      {"sTitle": "组织编号", "mData": "orgCode"},
//                    {"sTitle": "车型规格", "mData": "modelSpecification"},
                    {"sTitle": "初始价格", "mData": "initialPrice"},
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
                        "sTitle": "修改时间", "mData": "updatedTime", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                               return new Date(data).Format("yyyy-MM-dd hh:mm");
                            } else {
                               return "";
                            }
                        }
                    },
                    {
                        "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {
                        var a = '<a href="${rc.contextPath}/item/update/' + row.id
                                + '" class="btn btn-xs blue"  title="编辑" >' +
                                '<i class="glyphicon glyphicon-pencil"></i>编辑</a>';

                        var b = '<a href="${rc.contextPath}/itemPic/index-2-' + row.id
                                + '" class="btn btn-xs green"  title="产品图片" >' +
                                '<i class="glyphicon glyphicon-picture"></i>产品图片</a>';
                        var c = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.id
                                + '\')" class="btn btn-xs red"  title="删除" >' +
                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';

                        var d = '<a href="${rc.contextPath}/itemMode/index-' + row.id
                                + '" class="btn btn-xs blue"  title="配置信息" >' +
                                '<i class="glyphicon glyphicon-plus"></i>配置信息</a>';

                        var e = '<a href="${rc.contextPath}/item/update/' + row.id
                                + '" class="btn btn-xs grey"  title="编辑" >' +
                                '<i class="glyphicon glyphicon-pencil"></i>编辑</a>';

                        var f = '<a href="${rc.contextPath}/itemPic/index-2-' + row.id
                                + '" class="btn btn-xs grey"  title="产品图片" >' +
                                '<i class="glyphicon glyphicon-picture"></i>产品图片</a>';

                        var h = '<a href="${rc.contextPath}/itemMode/index-' + row.id
                                + '" class="btn btn-xs grey"  title="配置信息" >' +
                                '<i class="glyphicon glyphicon-plus"></i>配置信息</a>';

                        var g = '<a href="javascript:void(0);" onclick="removeOne(\'' + row.id
                                + '\')" class="btn btn-xs grey"  title="删除" >' +
                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';
                        if(row.isSynchro==1){
                            return "已删除";
                        }else{
                            return a+b+d+c;
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
                                url: '${rc.contextPath}/item/deleteOne-' + id,
                                type: 'POST',
                                traditional: true,
                                success: function (data) {
                                    console.log(data);
                                    Metronic.stopPageLoading();
                                    if(data.code == '200'){
                                        $('#tip').show();
                                        $('#tip').html('<div class="alert alert-success"><button data-dismiss="alert" class="close">×</button>'+data.msg+'</div>');
                                        window.setTimeout(function(){
                                            $('#tip').hide();
                                        }, 3000);
                                    }else{
                                        $('#tip').show();
                                        $('#tip').html('<div class="alert alert-danger"><button data-dismiss="alert" class="close">×</button>'+data.msg+'</div>');
                                        window.setTimeout(function(){
                                            $('#tip').hide();
                                        }, 3000);
                                    }
                                    grid.getDataTable().fnDraw();
                                },
                                error:function(error){
                                    $('#tip').show();
                                    $('#tip').html('<div class="alert alert-danger"><button data-dismiss="alert" class="close">×</button>删除失败</div>');
                                    window.setTimeout(function(){
                                        $('#tip').hide();
                                    }, 3000);
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
         * 物理删除
         */
        function removeOne(id) {
            bootbox.dialog({
                message: "您是否确认删除?",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function() {
                            Metronic.startPageLoading();
                            $.ajax({
                                url:'${rc.contextPath}/item/removeOne',
                                type:'POST',
                                data:{"id":id},
                                dataType:"json",
                                traditional:true,
                                success:function(){
                                    Metronic.stopPageLoading();
                                    grid.getDataTable().fnDraw();
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: "gray",
                        callback: function() {
                            $(this).hide();
                        }
                    }
                }
            });
        }


    </script>
</content>
</html>
