<html>
<head>
    <title>登录信息</title>
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
<#--<link href="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />-->
<#--<link href="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />-->
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
          rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL PLUGINS -->
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="note note-danger">
            <p> 错误或者成功信息 </p>
        </div>
        <!-- Begin: life time stats -->
        <div class="portlet light portlet-fit portlet-datatable bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">登录日志</span>
                </div>
                <div class="actions">
                    <div class="btn-group btn-group-devided" data-toggle="buttons">
                        <label class="btn btn-transparent grey-salsa btn-outline btn-circle btn-sm active">
                            <input type="radio" name="options" class="toggle" id="option1">Actions</label>
                        <label class="btn btn-transparent grey-salsa btn-outline btn-circle btn-sm">
                            <input type="radio" name="options" class="toggle" id="option2">Settings</label>
                    </div>
                    <div class="btn-group">
                        <a class="btn red btn-outline btn-circle" href="javascript:;" data-toggle="dropdown">
                            <i class="fa fa-share"></i>
                            <span class="hidden-xs"> Tools </span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="javascript:;"> Export to Excel </a>
                            </li>
                            <li>
                                <a href="javascript:;"> Export to CSV </a>
                            </li>
                            <li>
                                <a href="javascript:;"> Export to XML </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:;"> Print Invoices </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-container">
                    <div class="table-actions-wrapper">
                        <span> </span>
                        <select class="table-group-action-input form-control input-inline input-small input-sm">
                            <option value="">Select...</option>
                            <option value="Cancel">Cancel</option>
                            <option value="Cancel">Hold</option>
                            <option value="Cancel">On Hold</option>
                            <option value="Close">Close</option>
                        </select>
                        <button class="btn btn-sm green table-group-action-submit">
                            <i class="fa fa-check"></i> Submit
                        </button>
                    </div>
                    <#--<table class="table table-striped table-bordered table-hover table-checkable"-->
                           <#--id="attendees_data_table">-->

                    <#--</table>-->

                    <table class="table table-striped table-hover table-bordered" id="showOrgList">
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- End: life time stats -->
    </div>
</div>


<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-body" style="max-height:940px;overflow-y:auto;overflow-x:hidden">

            </div>
        </div>
    </div>
</div>
</body>
<content tag="script">

    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.js"></script>
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="${rc.contextPath}/assets/global/scripts/datatable.js" type="text/javascript"></script>
<#--<script src="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>-->
<#--<script src="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>-->
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
            type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <script type="text/javascript">


        <#--var orgInfo = new Datatable();-->
        <#--orgInfo.init({-->
            <#--src: $('#showOrgList'),-->
            <#--dataTable: {-->
                <#--onSuccess: function (orgInfo) {-->
                    <#--console.log(orgInfo);-->
                <#--},-->
                <#--onError: function (orgInfo) {-->
                <#--},-->
                <#--"iDeferLoading": 0,-->
                <#--"iDisplayLength": 10,-->
                <#--"bServerSide": true,-->
                <#--"sAjaxSource": "${rc.contextPath}/organization/org-list",-->
                <#--"aaSorting": [-->
                    <#--[0, "desc"]-->
                <#--],-->
                <#--"aoColumnDefs": [{'bSortable': false, 'aTargets': [1]}],-->
                <#--"sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式-->
                <#--"aoColumns": [-->
                    <#--{"sTitle": "序号", "mData": "id"},-->
                    <#--{"sTitle": "简称", "mData": "orgName"},-->
                    <#--{"sTitle": "全称", "mData": "orgDescr"},-->
                    <#--{"sTitle": "编号", "mData": "orgCode"},-->
                    <#--{"sTitle": "状态", "mData": "orgStatus"}-->

                <#--]-->
            <#--}-->
        <#--});-->


        /**
         * 数据显示
         * */
        var grid = new Datatable();
        var $attendees_data_table = $("#showOrgList");
        grid.init({
            src: $attendees_data_table,
            onSuccess: function (grid) {
                console.log(grid);
            },
            onError: function (grid) {
            },
            dataTable: {
                "iDisplayLength": 10,
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/organization/org-list/"+"128o",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2]}
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    {"sTitle": "序号", "mData": "id"},
                    {"sTitle": "简称", "mData": "orgName"},
                    {"sTitle": "全称", "mData": "orgDescr"},
                    {"sTitle": "编号", "mData": "orgCode"},
                    {"sTitle": "状态", "mData": "orgStatus"}

                ]
            }
        });


    </script>
</content>
</html>
