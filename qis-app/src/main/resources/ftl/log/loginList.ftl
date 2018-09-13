<html>
<head>
    <title>日志管理</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${rc.contextPath}/">登录日志管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">登录日志</a>
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
            <input type="hidden"  id="roleId">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">登录日志列表</span>
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <div class="fileinput fileinput-new" data-provides="fileinput">

                        <@shiro.hasPermission name="log-logins-export">
                            <a class="btn green btn-outline btn-circle" href="javascript:exportData();">
                                <i class="fa fa-download"></i>
                                <span class="hidden-480">导出</span>
                            </a>
                        </@shiro.hasPermission>

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
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">登录账号</b>
                            <input type="text" class="input-sm form-filter" name="search_userName"
                                   id="search_userName" placeholder="登录名"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">姓名</b>
                            <input type="text" class="input-sm form-filter" name="search_name"
                                   id="search_name" placeholder="姓名"/>
                        </label>
                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">IP地址</b>
                            <input type="text" class="input-sm form-filter" name="search_ipAddress"
                                   id="search_ipAddress" placeholder="IP地址"/>
                        </label>
                        <#--<label style="float:left;margin-right:5px;">-->
                            <#--<b class="form-control input-inline" style="border: 0px; text-align: left;">用户ID</b>-->
                            <#--<input type="text" class="input-sm form-filter" name="search_userId"-->
                                   <#--id="search_userId" placeholder="用户ID"/>-->
                        <#--</label>-->


                        <label style="float:left;margin-right:5px;">
                            <div class="form-control input-inline"
                                 style="float: left;padding-top: 5px;border: 0px; text-align: left;"
                            ">创建时间</div>
                    <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                         style="width: 123px;float: left;">
                        <input id="search_RoleClass_startCreatedOn" name="search_RoleClass_startCreatedOn" style=" width: 90px; padding: 2px; "
                               type="text" class="form-filter input-sm" placeholder="开始日期" readonly>
                        <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                class="fa fa-calendar"></i></button></span>
                    </div>
                    <div style="float:left;">~</div>
                    <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                         style="width: 123px;float: left;">
                        <input id="search_RoleClass_endCreatedOn" name="search_RoleClass_endCreatedOn" style=" width:90px; padding: 2px;"
                               type="text" class="form-filter input-sm" placeholder="结束日期" readonly>
                        <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                class="fa fa-calendar"></i></button></span>
                    </div>
                    </label>

                        <label style="float:left;">
                            <span> &nbsp;&nbsp;</span>
                            <button class="btn btn-sm yellow btn-outline btn-circle" value="搜索"
                                    onclick="search(this,grid)"><i class="fa fa-search"></i> 搜索
                            </button>
                            <button class="btn btn-sm red  btn-outline btn-circle" onclick="resetSearch(this)"><i
                                    class="fa fa-times"></i> 重置
                            </button>
                        </label>
                    </div>

            <#--空的表格，init初始化加载数据-->
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

    <script type="text/javascript">

        $('#message').delay(3000).hide(0);
        /**
         * 数据显示
         * */
        var grid = new Datatable(); //声明 Datatable，里面的数据要塞到 <table>中
        var $attendees_data_table = $("#attendees_data_table"); //attendees_data_table是<table>的id
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
                "iDisplayLength": 10,//页面显示数据数量
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/log/login/list",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}   //控制表格有多少列
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
//                    { "sWidth":"1%","sTitle":'<input type="checkbox" class= "checkAllBox" onclick="checkAllBox(this)" title="全选" class="group-checkable" />',"sDefaultContent":"","mRender":function(data,type,full){
//                        return '<div class="checker"  ><span class=""><input type="checkbox" class="checkboxes" name="checkBox" value="'+full.id+'"></span></div>';
//                    }},
                    {"sTitle": "登录账号", "mData": "userNo"},
                    {"sTitle": "姓名", "mData": "name"},
                    {"sTitle": "IP地址", "mData": "ipAddress"},
//                    {"sTitle": "用户ID", "mData": "userId"},
//                   {"sTitle": "登录类别ID", "mData": "dataTypeId"},
                    {"sTitle": "登录类别", "mData": "dataTypeId","mRender": function (data, type, row) {
                        if (data == "35" ) {
                            return "登入";
                        } else if (data == "36" ){
                            return "登出";
                        }
                    }},
//                    {"sTitle": "用户类别ID", "mData": "isEffective"},
                    {"sTitle": "用户类别", "mData": "isEffective","mRender": function (data, type, row) {
                        if (data == "1" ) {
                            return "系统用户";
                        } else if (data == "2" ){
                            return "普通用户";
                        }
                    }},
                    { "sTitle": "创建时间", "mData": "createdTime", "mRender": function (data, type, row) {
                        if (data != null && "" != data) {
                            return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                        } else {
                            return "";
                        }
                    }}
//                    { "sTitle": "生效结束时间", "mData": "effectiveDateEndVo", "mRender": function (data, type, row) {
//                        if (data != null && "" != data) {
//                            return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
//                        } else {
//                            return "";
//                        }
//                    }},
                    <#--{-->
                        <#--&lt;#&ndash;"sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {&ndash;&gt;-->
                        <#--&lt;#&ndash;var a = '<a href="${rc.contextPath}/role/roleClass/update/' + row.id&ndash;&gt;-->
                                <#--&lt;#&ndash;+ '" class="btn btn-xs blue"  title="编辑" >' +&ndash;&gt;-->
                                <#--&lt;#&ndash;'<i class="glyphicon glyphicon-pencil"></i>编辑</a>';&ndash;&gt;-->

                    <#--&lt;#&ndash;var b = '<a href="${rc.contextPath}/zc/itemPic/index-2-' + row.id&ndash;&gt;-->
                    <#--&lt;#&ndash;+ '" class="btn btn-xs green"  title="产品图片" >' +&ndash;&gt;-->
                    <#--&lt;#&ndash;'<i class="glyphicon glyphicon-picture"></i>产品图片</a>';&ndash;&gt;-->

                        <#--//  逻辑删除-->
<#--//                        var c = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.id-->
<#--//                                + '\')" class="btn btn-xs red"  title="删除" >' +-->
<#--//                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';-->

                    <#--&lt;#&ndash;var d = '<a href="${rc.contextPath}/zc/itemMode/index-' + row.id&ndash;&gt;-->
                    <#--&lt;#&ndash;+ '" class="btn btn-xs blue"  title="配置信息" >' +&ndash;&gt;-->
                    <#--&lt;#&ndash;'<i class="glyphicon glyphicon-plus"></i>配置信息</a>';&ndash;&gt;-->

                    <#--&lt;#&ndash;var e = '<a href="${rc.contextPath}/zc/update/update/' + row.id&ndash;&gt;-->
                    <#--&lt;#&ndash;+ '" class="btn btn-xs grey"  title="编辑" >' +&ndash;&gt;-->
                    <#--&lt;#&ndash;'<i class="glyphicon glyphicon-pencil"></i>编辑</a>';&ndash;&gt;-->

                    <#--&lt;#&ndash;var f = '<a href="${rc.contextPath}/zc/itemPic/index-2-' + row.id&ndash;&gt;-->
                    <#--&lt;#&ndash;+ '" class="btn btn-xs grey"  title="产品图片" >' +&ndash;&gt;-->
                    <#--&lt;#&ndash;'<i class="glyphicon glyphicon-picture"></i>产品图片</a>';&ndash;&gt;-->

                    <#--&lt;#&ndash;var h = '<a href="${rc.contextPath}/zc/itemMode/index-' + row.id&ndash;&gt;-->
                    <#--&lt;#&ndash;+ '" class="btn btn-xs grey"  title="配置信息" >' +&ndash;&gt;-->
                    <#--&lt;#&ndash;'<i class="glyphicon glyphicon-plus"></i>配置信息</a>';&ndash;&gt;-->

                        <#--//物理删除-->
<#--//                        var g = '<a href="javascript:void(0);" onclick="removeOne(\'' + row.id-->
<#--//                                + '\')" class="btn btn-xs grey"  title="删除" >' +-->
<#--//                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';-->

<#--//                        if(row.isSynchro==1){    //isSynchro是自己写的字段-->
<#--//                            return "已删除";-->
<#--//                        }else{-->
<#--//                            return a+c; //修改和逻辑删除-->
<#--//                            // return a+g; //修改和物理删除-->
<#--////                            return a+b+d+c;-->
<#--//                        }-->
                    <#--}-->
                  //  }
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
         * 导出excel
         */
        function exportData(){
            var search_ipAddress=$('#search_ipAddress').val(),
                    search_userId=$('#search_userId').val(),
                    search_userName=$('#search_userName').val(),
                    search_name=$('#search_name').val();
            location.href='${rc.contextPath}/log/login/exportExcel?search_ipAddress='+search_ipAddress+'&search_userId='+search_userId+'&search_userName='+search_userName+'&search_name='+search_name;
        }

    </script>
</content>
</html>
