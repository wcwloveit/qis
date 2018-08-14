<html>
<head>
    <title>登录信息</title>
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <#--<link href="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />-->
    <#--<link href="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />-->
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
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
                                <li class="divider"> </li>
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
                                <i class="fa fa-check"></i> Submit</button>
                        </div>
                        <table class="table table-striped table-bordered table-hover table-checkable" id="attendees_data_table">
                            <#--<thead>-->
                            <#--<tr role="row" class="heading">-->
                                <#--<th width="2%">-->
                                    <#--<input type="checkbox" class="group-checkable"> </th>-->
                                <#--<th width="5%"> Record&nbsp;# </th>-->
                                <#--<th width="15%"> Date </th>-->
                                <#--<th width="200"> Customer </th>-->
                                <#--<th width="10%"> Ship&nbsp;To </th>-->
                                <#--<th width="10%"> Price </th>-->
                                <#--<th width="10%"> Amount </th>-->
                                <#--<th width="10%"> Status </th>-->
                                <#--<th width="10%"> Actions </th>-->
                            <#--</tr>-->
                            <#--<tr role="row" class="filter">-->
                                <#--<td> </td>-->
                                <#--<td>-->
                                    <#--<input type="text" class="form-control form-filter input-sm" name="order_id"> </td>-->
                                <#--<td>-->
                                    <#--<div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">-->
                                        <#--<input type="text" class="form-control form-filter input-sm" readonly name="order_date_from" placeholder="From">-->
                                        <#--<span class="input-group-btn">-->
                                                                <#--<button class="btn btn-sm default" type="button">-->
                                                                    <#--<i class="fa fa-calendar"></i>-->
                                                                <#--</button>-->
                                                            <#--</span>-->
                                    <#--</div>-->
                                    <#--<div class="input-group date date-picker" data-date-format="dd/mm/yyyy">-->
                                        <#--<input type="text" class="form-control form-filter input-sm" readonly name="order_date_to" placeholder="To">-->
                                        <#--<span class="input-group-btn">-->
                                                                <#--<button class="btn btn-sm default" type="button">-->
                                                                    <#--<i class="fa fa-calendar"></i>-->
                                                                <#--</button>-->
                                                            <#--</span>-->
                                    <#--</div>-->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<input type="text" class="form-control form-filter input-sm" name="order_customer_name"> </td>-->
                                <#--<td>-->
                                    <#--<input type="text" class="form-control form-filter input-sm" name="order_ship_to"> </td>-->
                                <#--<td>-->
                                    <#--<div class="margin-bottom-5">-->
                                        <#--<input type="text" class="form-control form-filter input-sm" name="order_price_from" placeholder="From" /> </div>-->
                                    <#--<input type="text" class="form-control form-filter input-sm" name="order_price_to" placeholder="To" /> </td>-->
                                <#--<td>-->
                                    <#--<div class="margin-bottom-5">-->
                                        <#--<input type="text" class="form-control form-filter input-sm margin-bottom-5 clearfix" name="order_quantity_from" placeholder="From" /> </div>-->
                                    <#--<input type="text" class="form-control form-filter input-sm" name="order_quantity_to" placeholder="To" /> </td>-->
                                <#--<td>-->
                                    <#--<select name="order_status" class="form-control form-filter input-sm">-->
                                        <#--<option value="">Select...</option>-->
                                        <#--<option value="pending">Pending</option>-->
                                        <#--<option value="closed">Closed</option>-->
                                        <#--<option value="hold">On Hold</option>-->
                                        <#--<option value="fraud">Fraud</option>-->
                                    <#--</select>-->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<div class="margin-bottom-5">-->
                                        <#--<button class="btn btn-sm green btn-outline filter-submit margin-bottom">-->
                                            <#--<i class="fa fa-search"></i> Search</button>-->
                                    <#--</div>-->
                                    <#--<button class="btn btn-sm red btn-outline filter-cancel">-->
                                        <#--<i class="fa fa-times"></i> Reset</button>-->
                                <#--</td>-->
                            <#--</tr>-->
                            <#--</thead>-->
                            <#--<tbody> </tbody>-->
                        </table>
                    </div>
                </div>
            </div>
            <!-- End: life time stats -->
        </div>
    </div>
</body>
<content tag="script">

    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.js"></script>
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="${rc.contextPath}/assets/global/scripts/datatable.js" type="text/javascript"></script>
    <#--<script src="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>-->
    <#--<script src="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>-->
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
   <script type="text/javascript">

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
                "sAjaxSource": "${rc.contextPath}/log/List",
                "aaSorting": [
                    [ 0, "desc" ]
                ],
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0, 1, 2] }
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    { "sTitle": "标题9", "mData": "ipAddress"},
                    { "sTitle": "标题8", "mData": "userId"},
                    { "sTitle": "标题7", "mData": "descr"},
                    { "sTitle": "标题6", "mData": "guidId"},
                    { "sTitle": "标题5", "mData": "isEffective"},
//                    { "sTitle": "标题4", "mData": "effectiveDateStart","mRender": function (data, type, row) {
//                        if (data != null && "" != data) {
//                            return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
//                        } else {
//                            return "";
//                        }
//                    }},
//                    { "sTitle": "标题3", "mData": "effectiveDateEnd","mRender": function (data, type, row) {
//                        if (data != null && "" != data) {
//                            return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
//                        } else {
//                            return "";
//                        }
//                    }},
                    { "sTitle": "标题2", "mData": "descFlexField1"},
                    { "sTitle": "标题1", "mData": "dataTypeId"}
                ]
            }
        });



    </script>
</content>
</html>
