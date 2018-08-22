<html>
<head>
    <title>权限管理</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${rc.contextPath}/">权限管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">权限</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
           <#if message>
               <div class="note note-danger">
                   <p>
                       ${(message)!}
                   </p>
               </div>
           </#if>
        <div class="portlet light portlet-fit portlet-datatable bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">权限列表</span>
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <div class="fileinput fileinput-new" data-provides="fileinput">
                            <a class="btn green btn-parent" href="${rc.contextPath}/permissions/create"> <#--跳转新增的URL-->
                                <i class="fa fa-plus"></i>
                                <span class="hidden-480">新增</span>
                            </a>

                            <a href="javascript:void(0)" class="btn red">
                                <i class="fa fa-trash-o"></i>
                                <span class="hidden-480"  onclick="deleteList();">批量删除</span>
                            </a>

                            <a class="btn green" href="javascript:exportData();">
                                <i class="fa fa-download"></i>
                                <span class="hidden-480">导出</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="portlet-body">

                <div class="table-container">
                    <div class="table-actions-wrapper">
                    </div>
                    <div id="data_table_search">
                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">名称</b>
                            <input type="text" class="input-sm form-filter" name="search_name"
                                   id="search_name" placeholder="名称"/>
                        </label>
                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">编码</b>
                            <input type="text" class="input-sm form-filter" name="search_code"
                                   id="search_type_value" placeholder="编码"/>
                        </label>
                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">描述</b>
                            <input type="text" class="input-sm form-filter" name="search_descr"
                                   id="search_descr" placeholder="描述"/>
                        </label>
                        <label style="float:left;margin-right:5px;">
                            <div class="form-control input-inline"
                                 style="float: left;padding-top: 5px;border: 0px; text-align: left;">创建时间
                            </div>
                            <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                                 style="width: 123px;float: left;">
                                <input id="search_startCreatedOn" name="search_startCreatedOn"
                                       style=" width: 90px; padding: 2px; "
                                       type="text" class="form-filter input-sm" placeholder="" readonly>
                                <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                        class="fa fa-calendar"></i></button></span>
                            </div>
                            <div style="float:left;">~</div>

                            <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                                 style="width: 123px;float: left;">
                                <input id="search_endCreatedOn" name="search_endCreatedOn"
                                       style=" width:90px; padding: 2px;"
                                       type="text" class="form-filter input-sm" placeholder="" readonly>
                                <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                        class="fa fa-calendar"></i></button></span>
                            </div>
                        </label>

                        <label style="float:left;">
                            <span> &nbsp;&nbsp;</span>
                            <button class="btn btn-sm yellow margin-bottom filter-submit" value="搜索"
                                    onclick="search(this,grid)"><i class="fa fa-search"></i> 搜索
                            </button>
                            <button class="btn btn-sm red filter-cancel" onclick="resetSearch(this)"><i
                                    class="fa fa-times"></i> 重置
                            </button>
                        </label>

                    </div>
                    <div>
                        <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
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
                "sAjaxSource": "${rc.contextPath}/permissions/list",
                "aaSorting": [
                    [ 0, "desc" ]
                ],
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7,8] }
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    { "sWidth":"1%","sTitle":'<input type="checkbox" class= "checkAllBox" onclick="checkAllBox(this)" title="全选" class="group-checkable" />',"sDefaultContent":"","mRender":function(data,type,full){
                            return '<div class="checker"  ><span class=""><input type="checkbox" class="checkboxes" name="checkBox" value="'+full.id+'"></span></div>';
                        }},
                    { "sTitle": "名称", "mData": "name", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "编码", "mData": "code", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "描述", "mData": "descr", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "唯一标识符", "mData": "guidId", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "状态", "mData": "isDeleted", "mRender": function (data, type, row) {
                            if (data == '0') {
                                return "存在";
                            }else if (data == '1') {
                                return "已删除";
                            }
                        }},
                    { "sTitle": "创建时间", "mData": "createdOn", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                                return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                            } else {
                                return "";
                            }
                        }},
                    { "sTitle": "修改时间",  "mData": "modifiedOn", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                                return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                            } else {
                                return "";
                            }
                        }},
                    { "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {
                            var a = '<a href="${rc.contextPath}/permissions/update/' + row.id + '" class="btn btn-xs blue"  title="编辑" >' +
                                    '<i class="glyphicon glyphicon-pencil"></i>编辑</a>';

                            var b = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.id + '\')" class="btn btn-xs red"  title="删除" >' +
                                    '<i class="glyphicon glyphicon-trash"></i>删除</a>';

                            if(row.isDeleted==1){
                                return "已删除";
                            }else{
                                return a+b;
                            }
                        }}
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
                                url: '${rc.contextPath}/permissions/deleteOne-' + id,
                                type: 'POST',
                                traditional: true,
                                success: function (data) {
                                    Metronic.stopPageLoading();
                                    grid.getDataTable().fnDraw();
                                    bootbox.alert("操作成功");
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

        /**
         * 批量删除
         */
        function deleteList() {
            var ids=[];
            $('#attendees_data_table span.checked >input.checkboxes:checked').each(function(){
                ids.push($(this).val());
            })
            if(ids==''||ids==null||ids.length==0){
                bootbox.alert('请选择需要删除的权限');
                return false;
            }
            bootbox.dialog({
                message: "您是否确认删除权限编号为："+ids+"的权限",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function() {
                            Metronic.startPageLoading();
                            $.ajax({
                                url:'${rc.contextPath}/permissions/delete-all',
                                type:'POST',
                                data:{"ids":ids},
                                dataType:"json",
                                traditional:true,
                                success:function(msg){
                                    Metronic.stopPageLoading();
                                    if(msg&&msg.stat){
                                        alertHint('删除成功');
                                        grid.getDataTable().fnDraw();
                                    }else{
                                        bootbox.alert('删除失败');
                                    }
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
