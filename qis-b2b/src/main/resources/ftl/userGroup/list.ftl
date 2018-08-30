<html>
<head>
    <title>用户组</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
<#--<link href="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />-->
<#--<link href="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />-->
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        li{
            list-style: none;
            float:left;
        }
        #tabCon2{
            clear: both;
        }
        #tabCon2_1{
            display: none;
        }
        #tabCon1{
            clear: both;
        }
        #tabCon1_1{
            display: none;
        }
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
        .button2 {background-color: #008CBA;}
    </style>
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
                <a href="#">用户组</a>
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

                            <a class="btn green btn-parent" href="${rc.contextPath}/userGroup/create"> <#--跳转新增的URL-->
                                <i class="fa fa-plus"></i>
                                <span class="hidden-480">新增</span>
                            </a>

                            <#--<a href="javascript:void(0)" class="btn red">-->
                                <#--<i class="fa fa-trash-o"></i>-->
                                <#--<span class="hidden-480"  onclick="deleteList();">批量删除</span>-->
                            <#--</a>-->

                            <#--<a class="btn green" href="javascript:exportData();">-->
                                <#--<i class="fa fa-download"></i>-->
                                <#--<span class="hidden-480">导出</span>-->
                            <#--</a>-->

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

                <#--隐藏的空格，用于存选中用户组数据-->
               <input type="hidden"  id="roleId">

                <div class="table-container">
                    <div class="table-actions-wrapper">
                    </div>
                    <div id="data_table_search">

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">名称</b>
                            <input type="text" class="input-sm form-filter" name="search_userGroup_name"
                                   id="search_userGroup_name" placeholder="名称"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">编号</b>
                            <input type="text" class="input-sm form-filter" name="search_userGroup_code"
                                   id="search_userGroup_code" placeholder="编号"/>
                        </label>

                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">描述</b>
                            <input type="text" class="input-sm form-filter" name="search_userGroup_descr"
                                   id="search_userGroup_descr" placeholder="描述"/>
                        </label>

                        <#--<label style="float:left;margin-right:5px;">-->
                            <#--<b class="form-control input-inline" style="border: 0px; text-align: left;">生效开始时间</b>-->
                            <#--<input type="text" class="input-sm form-filter" name="search_BaseData_effectiveDateStart"-->
                                   <#--id="search_BaseData_effectiveDateStart" placeholder="生效开始时间"/>-->
                        <#--</label>-->

                        <#--<label style="float:left;margin-right:5px;">-->
                            <#--<b class="form-control input-inline" style="border: 0px; text-align: left;">生效结束时间</b>-->
                            <#--<input type="text" class="input-sm form-filter" name="search_BaseData_effectiveDateEnd"-->
                                   <#--id="search_BaseData_effectiveDateEnd" placeholder="生效结束时间"/>-->
                        <#--</label>-->
                        <label style="float:left;margin-right:5px;">
                            <div class="form-control input-inline"
                                 style="float: left;padding-top: 5px;border: 0px; text-align: left;"
                            ">创建时间</div>
                    <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                         style="width: 123px;float: left;">
                        <input id="search_userGroup_startCreatedOn" name="search_userGroup_startCreatedOn" style=" width: 90px; padding: 2px; "
                               type="text" class="form-filter input-sm" placeholder="开始日期" readonly>
                        <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                class="fa fa-calendar"></i></button></span>
                    </div>
                    <div style="float:left;">~</div>
                    <div class="input-group date date-picker" data-date-format="yyyy-mm-dd"
                         style="width: 123px;float: left;">
                        <input id="search_userGroup_endCreatedOn" name="search_userGroup_endCreatedOn" style=" width:90px; padding: 2px;"
                               type="text" class="form-filter input-sm" placeholder="结束日期" readonly>
                        <span class="input-group-btn"><button class="btn btn-sm default" type="button"><i
                                class="fa fa-calendar"></i></button></span>
                    </div>
                    </label>


                <#--<label style="float:left;margin-right:5px;">-->
                <#--<b class="form-control input-inline" style="border: 0px; text-align: left;">车型规格</b>-->
                <#--<input type="text" class="input-sm form-filter" name="search_ZC_modelSpecification"-->
                <#--id="search_ZC_modelSpecification" placeholder="车型规格"/>-->
                <#--</label>-->
                    <!--


                                            <label style="float:left;margin-right:5px;">
                                                <b class="form-control input-inline" style="border: 0px; text-align: left;">颜色编号</b>
                                                <input type="text" class="input-sm form-filter" name="search_ZC_colorCode"
                                                       id="search_ZC_colorCode" placeholder="颜色编号"/>
                                            </label>

                                            <label style="float:left;margin-right:5px;">
                                                <b class="form-control input-inline" style="border: 0px; text-align: left;">颜色名称</b>
                                                <input type="text" class="input-sm form-filter" name="search_ZC_colorName"
                                                       id="search_ZC_colorName" placeholder="颜色名称"/>
                                            </label>
                    -->

                <#--<label style="float:left;margin-right:5px;">-->
                <#--<div class="form-control input-inline"-->
                <#--style="float: left;padding-top: 5px;border: 0px; text-align: left;"-->
                <#--">创建时间</div>-->
                <#--<div class="input-group date date-picker" data-date-format="yyyy-mm-dd"-->
                <#--style="width: 123px;float: left;">-->
                <#--<input id="search_ZC_startCreatedOn" name="search_ZC_startCreatedOn" style=" width: 90px; padding: 2px; "-->
                <#--type="text" class="form-filter input-sm" placeholder="开始日期" readonly>-->
                <#--<span class="input-group-btn"><button class="btn btn-sm default" type="button"><i-->
                <#--class="fa fa-calendar"></i></button></span>-->
                <#--</div>-->
                <#--<div style="float:left;">~</div>-->
                <#--<div class="input-group date date-picker" data-date-format="yyyy-mm-dd"-->
                <#--style="width: 123px;float: left;">-->
                <#--<input id="search_ZC_endCreatedOn" name="search_ZC_endCreatedOn" style=" width:90px; padding: 2px;"-->
                <#--type="text" class="form-filter input-sm" placeholder="结束日期" readonly>-->
                <#--<span class="input-group-btn"><button class="btn btn-sm default" type="button"><i-->
                <#--class="fa fa-calendar"></i></button></span>-->
                <#--</div>-->
                <#--</label>-->

                <#--<label style="float:left;margin-right:5px;">-->
                <#--<div class="form-control input-inline"-->
                <#--style="float: left;padding-top: 5px;border: 0px; text-align: left;"-->
                <#--">更新时间</div>-->
                <#--<div class="input-group date date-picker" data-date-format="yyyy-mm-dd"-->
                <#--style="width: 123px;float: left;">-->
                <#--<input id="search_ZC_startUpdatedOn" name="search_ZC_startUpdatedOn" style=" width: 90px; padding: 2px; "-->
                <#--type="text" class="form-filter input-sm" placeholder="开始日期" readonly>-->
                <#--<span class="input-group-btn"><button class="btn btn-sm default" type="button"><i-->
                <#--class="fa fa-calendar"></i></button></span>-->
                <#--</div>-->
                <#--<div style="float:left;">~</div>-->
                <#--<div class="input-group date date-picker" data-date-format="yyyy-mm-dd"-->
                <#--style="width: 123px;float: left;">-->
                <#--<input id="search_ZC_endUpdatedOn" name="search_ZC_endUpdatedOn" style=" width:90px; padding: 2px;"-->
                <#--type="text" class="form-filter input-sm" placeholder="结束日期" readonly>-->
                <#--<span class="input-group-btn"><button class="btn btn-sm default" type="button"><i-->
                <#--class="fa fa-calendar"></i></button></span>-->
                <#--</div>-->
                <#--</label>-->


                <#--<label style="float:left;margin-right:5px;">-->
                <#--<div class="form-control input-inline"-->
                <#--style="float: left;padding-top: 5px;border: 0px; text-align: left;"-->
                <#--">初始价格</div>-->
                <#--<div class="input-group" style="width: 90px;float: left;">-->
                <#--<input id="search_ZC_startPrice" name="search_ZC_startPrice" style=" width: 90px; padding: 2px; "-->
                <#--type="text" class="form-filter input-sm" placeholder="From">-->
                <#--</div>-->
                <#--<div style="float:left;">~</div>-->
                <#--<div class="input-group" style="width: 90px;float: left;">-->
                <#--<input id="search_ZC_endPrice" name="search_ZC_endPrice" style=" width: 90px; padding: 2px; "-->
                <#--type="text" class="form-filter input-sm" placeholder="To">-->
                <#--</div>-->

                <#--</label>-->


                    <label style="float:left;">
                        <span> &nbsp;&nbsp;</span>
                        <button class="btn btn-sm yellow margin-bottom filter-submit" value="搜索" onclick="search(this,grid)"><i
                                class="fa fa-search"></i> 搜索
                        </button>
                        <button class="btn btn-sm red filter-cancel" onclick="resetSearch(this)"><i class="fa fa-times"></i> 重置
                        </button>
                    </label>
                </div>

            <#--空的表格，init初始化加载数据-->
                <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
                </table>
            </div>
        </div>

    <#--查看-->
        <div id="user_list_div" class="modal fade" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog" style="width:800px;">
                <div class="modal-content">

                <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <#--<h4 class="modal-title">员工列表</h4>-->
                </div>

                <#--TAB切换-->
                    <div id="tanContainer2">
                        <div id="tab2">

                            <ul>
                                <li onclick="changeTab2(0)"><button class="button">人员列表</button></li>
                                <li onclick="changeTab2(1)"><button class="button button2">部门列表</button></li>
                            </ul>

                        </div>
                        <div id="tabCon2">
                            <div id="tabCon2_0">
                            <#--对应人员列表的页面-->
                                <div class="modal-body">
                                    <div class="row" id="tableDatas">
                                        <div class="col-md-12">
                                            <div class="portlet">
                                                <div class="portlet-body">
                                                    <table class="table table-striped table-bordered table-hover" id="user_list_table">
                                                        <thead>
                                                        <tr role="row" class="heading">
                                                            <th width="15%">登录名</th>
                                                            <th width="15%">姓名</th>
                                                            <th width="15%">编号</th>
                                                            <th width="15%">手机</th>
                                                            <th width="15%">操作</th>
                                                        </tr>
                                                        <tr role="row" class="filter">

                                                            <!-- 登录名 -->
                                                            <td><input type="text" class="form-control form-filter input-sm" name="search_userName"></td>
                                                            <td><input type="text" class="form-control form-filter input-sm" name="search_name"></td>
                                                            <td><input type="text" class="form-control form-filter input-sm" name="search_userNo"></td>
                                                            <td><input type="text" class="form-control form-filter input-sm" name="search_mobilePhone"></td>
                                                            <td>
                                                                <button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 搜索
                                                                </button>
                                                                <button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> 重置</button>
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

                            <div id="tabCon2_1">
                            <#--对应部门列表的页面-->
                                部门
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    <#--分配-->
        <div id="user_list_div2" class="modal fade" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog" style="width:800px;">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <#--<h4 class="modal-title">员工列表</h4>-->
             <#--TAB切换-->
                 <div id="tanContainer1">
                     <div id="tab1">
                         <ul>
                             <li onclick="changeTab1(0)"><button class="button">人员列表</button></li>
                             <li onclick="changeTab1(1)"><button class="button button2">部门列表</button></li>
                         </ul>
                     </div>
                     <div id="tabCon1">
                       <div id="tabCon1_0">
                       <#--对应人员列表的页面-->
                           <div class="modal-body">
                               <div class="row">
                                   <div class="col-md-12">
                                       <div class="portlet">
                                           <div class="portlet-body">
                                               <table class="table table-striped table-bordered table-hover" id="user_list_table2">
                                                   <thead>
                                                   <tr role="row" class="heading">
                                                       <th width="15%">登录名</th>
                                                       <th width="15%">姓名</th>
                                                       <th width="15%">编号</th>
                                                       <th width="15%">手机</th>
                                                       <th width="15%">操作</th>
                                                   </tr>
                                                   <tr role="row" class="filter">
                                                       <!-- 登录名 -->
                                                       <td><input type="text" class="form-control form-filter input-sm" name="search_userName"></td>
                                                       <td><input type="text" class="form-control form-filter input-sm" name="search_name"></td>
                                                       <td><input type="text" class="form-control form-filter input-sm" name="search_userNo"></td>
                                                       <td><input type="text" class="form-control form-filter input-sm" name="search_mobilePhone"></td>
                                                       <td>
                                                           <button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 搜索
                                                           </button>
                                                           <button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> 重置</button>
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

                         <div id="tabCon1_1">
                         <#--对应部门列表的页面-->
                             部门
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
                "sAjaxSource": "${rc.contextPath}/userGroup/list",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}   //控制表格有多少列
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
//                    多选框
//                    { "sWidth":"1%","sTitle":'<input type="checkbox" class= "checkAllBox" onclick="checkAllBox(this)" title="全选" class="group-checkable" />',"sDefaultContent":"","mRender":function(data,type,full){
//                        return '<div class="checker"  ><span class=""><input type="checkbox" class="checkboxes" name="checkBox" value="'+full.id+'"></span></div>';
//                    }},
                    {"sTitle": "名称", "mData": "name"},
                    {"sTitle": "编号", "mData": "code"},
                    {"sTitle": "描述", "mData": "descr"},
                    { "sTitle": "创建时间", "mData": "createdTime", "mRender": function (data, type, row) {
                        if (data != null && "" != data) {
                            return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                        } else {
                            return "";
                        }
                    }},
//                    { "sTitle": "生效结束时间", "mData": "effectiveDateEndVo", "mRender": function (data, type, row) {
//                        if (data != null && "" != data) {
//                            return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
//                        } else {
//                            return "";
//                        }
//                    }},
                    {
                        "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {
                        var a = '<a href="${rc.contextPath}/userGroup/update/' + row.id
                                + '" class="btn btn-xs blue"  title="编辑" >' +
                                '<i class="glyphicon glyphicon-pencil"></i>编辑</a>';

                        //  逻辑删除
                        var c = '<a href="javascript:void(0);" onclick="deleteOne(\'' + row.id
                                + '\')" class="btn btn-xs red"  title="删除" >' +
                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';

                        var b='<a class="btn btn-xs green" href="javascript:void(0);" onclick="seeUser(\''+row.id+'\')" title ="查看"><i class="fa fa-search"></i>查看</a>';
                        var d='<a class="btn btn-xs green" href="javascript:void(0);" onclick="toUser(\''+row.id+'\')" title ="分配"><i class="glyphicon glyphicon-cog"></i>分配</a>';


                        if(row.isSynchro==1){    //isSynchro是自己写的字段
                            return "已删除";
                        }else{
                            //  return a+c; //修改和逻辑删除
                            //return a+c+b;
                            return a+c+b+d;
                        }
                    }
                    }
                ]
            }
        });

        //查看用户函数
        function seeUser(id){
            userGrid.setAjaxParam("roleId",id);
            $("#roleId").val(id);
            userGrid.getDataTable().fnDraw();
            $('#user_list_div').modal('show');
        }

        var userGrid=new Datatable();
        var $userList_data_table=$("#user_list_table");
        userGrid.init({
            src:$userList_data_table,
            onSuccess:function(userGrid){
                console.log(userGrid);
            },
            onError:function(userGrid){
            },
            dataTable:{
                "bServerSide":true,
                "sAjaxSource":"${rc.contextPath}/userGroup/query-user-list",
                "aoColumnDefs":[
                    { "bSortable":false,"aTargets":[ 0,1,2,3] }
                ],//设置不排序得列
                "aoColumns":[
                    { "sTitle":"登录名","mData":"userName"},
                    { "sTitle":"姓名","mData":"name"},
                    { "sTitle":"编号","mData":"userNo"},
                    { "sTitle":"手机","mData":"mobilePhone"},
//                    { "sTitle":"邮箱","mData":"email","mRender":function(data){
//                        return "<a href='mailto:"+data+"'>"+data+"</a>";
//                    }},
                    { "sTitle":"操作","mData":"","sDefaultContent":"","mRender":function(data,type,row){
                        return'<a class="delete btn green btn-xs black" href="javascript:leave('+row.id+');"><i class="fa fa-level-down"></i>离开</a>';
                    }}
                ]
            }
        });



        //分配用户信息
        function toUser(id){
            userGrid2.setAjaxParam("roleId",id);
            $("#roleId").val(id);
            userGrid2.getDataTable().fnDraw();
            $('#user_list_div2').modal('show');
        }

        var userGrid2=new Datatable();
        var $userList_data_table2=$("#user_list_table2");
        userGrid2.init({
            src:$userList_data_table2,
            onSuccess:function(userGrid2){
                console.log(userGrid2);
            },
            onError:function(userGrid2){
            },
            dataTable:{
                "bServerSide":true,
                "sAjaxSource":"${rc.contextPath}/userGroup/query-user-notinrole",
                "aoColumnDefs":[
                    { "bSortable":false,"aTargets":[ 0,1,2,3] }
                ],//设置不排序得列
                "aoColumns":[
                   { "sTitle":"登录名","mData":"userName"},
                    { "sTitle":"姓名","mData":"name"},
                    { "sTitle":"编号","mData":"userNo"},
                    { "sTitle":"手机","mData":"mobilePhone"},
//                    { "sTitle":"邮箱","mData":"email","mRender":function(data){
//                        return "<a href='mailto:"+data+"'>"+data+"</a>";
//                    }},
                    { "sTitle":"操作","mData":"","sDefaultContent":"","mRender":function(data,type,row){
                        return'<a class="delete btn green btn-xs black" href="javascript:join('+row.id+');"><i class="fa fa-level-up"></i>加入</a>';
                    }}
                ]
            }
        });

        function join(id){
            bootbox.dialog({
                message: "确认此人员加入此用户组",
                buttons: {
                    success: {
                        label: "确定",
                        className: "green",
                        callback: function() {
                            $.ajax({
                                url:'${rc.contextPath}/userGroup/join',
                                type:'POST',
                                traditional:true,
                                data:{"roleId":$("#roleId").val(),"empId":id},
                                success:function(){
                                    userGrid2.getDataTable().fnDraw();
                                    //成功以后刷新页面
                                }
                            });
                        }
                    },
                    main: {
                        label: "取消",
                        className: "gray",
                        callback: function() {
                            $(this).hide();
                        }
                    }
                }
            });
        }

        function leave(id){
            bootbox.dialog({
                message: "确认此员工离开此角色",
                buttons: {
                    success: {
                        label: "确定",
                        className: "green",
                        callback: function() {
                            $.ajax({
                                url:'${rc.contextPath}/userGroup/leave',
                                type:'POST',
                                traditional:true,
                                data:{"roleId":$("#roleId").val(),"empId":id},
                                success:function(){
                                    userGrid.getDataTable().fnDraw();
                                }
                            });
                        }
                    },
                    main: {
                        label: "取消",
                        className: "gray",
                        callback: function() {
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

//        /**
//         * 切换
//         * */
      function changeTab2(tabCon2_num){
        for(i=0;i<=1;i++) {
            document.getElementById("tabCon2_"+i).style.display="none"; //将所有的层都隐藏
        }
        document.getElementById("tabCon2_"+tabCon2_num).style.display="block";//显示当前层
        }

        function changeTab1(tabCon1_num){
            for(i=0;i<=1;i++) {
                document.getElementById("tabCon1_"+i).style.display="none"; //将所有的层都隐藏
            }
            document.getElementById("tabCon1_"+tabCon1_num).style.display="block";//显示当前层
        }

    </script>
</content>
</html>