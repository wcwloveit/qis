<html>
<head>
    <title>活动管理</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<ul class="page-breadcrumb breadcrumb">
    <li>
        <i class="fa fa-home"></i>
        <a href="${rc.contextPath}/">角色管理</a>
        <i class="fa fa-angle-right"></i>
    </li>
    <li>
        <a href="#">模块管理</a>
        <i class="fa fa-angle-right"></i>
    </li>
</ul>
        <div class="portlet light portlet-fit portlet-datatable bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">角色(${role.name})模块(${info.name})--数据列管理  </span>
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <a class="btn green btn-outline btn-circle" href="javascript:void(0);"
                           onclick="javascript:history.back(-1);" data-toggle="dropdown">
                            <i class="fa fa-mail-reply"></i>
                            <span class="hidden-xs"> 返回 </span>
                        </a>
                    </div>
                    <div class="btn-group">
                        <a class="btn green btn-outline btn-circle" href="javascript:void(0);"
                           onclick="saveModule()" data-toggle="dropdown">
                            <i class="fa fa-save"></i>
                            <span class="hidden-xs"> 保存设置 </span>
                        </a>
                    </div>

                </div>
            </div>
            <div class="portlet-body">
            <#if message>
                <div class="alert alert-block  fade in <#if message && success='true'>alert-success<#else>alert-danger</#if>">
                    <button type="button" class="close" data-dismiss="alert"></button>
                ${(message)!}
                </div>
            </#if>
                <div class="table-container">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="icheck-inline">
                            <#list coList as column>
                                <label>
                                    <input type="checkbox" class="icheck" <#if column.rIsEffective && column.rIsEffective=1>checked</#if>
                                           data-mId="${column.moduleColumnId!}" data-rmchId="${column.rmchId!}">
                                    ${column.name!}(${column.code!})
                                </label>
                            </#list>
                            </div>
                        </div>


                    </div>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab_1" data-toggle="tab"> 已加入汇总列表 </a>
                        </li>
                        <li>
                            <a href="#tab_2" data-toggle="tab"> 待加入人员列表 </a>
                        </li>
                        <li>
                            <a href="#tab_3" data-toggle="tab"> 待加入部门列表 </a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id="tab_1">
                            <table class="table table-striped table-bordered table-hover" id="data_table_1">
                            </table>
                        </div>
                        <div class="tab-pane fade" id="tab_2">
                            <table class="table table-striped table-bordered table-hover" id="data_table_2">
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
                        <div class="tab-pane fade" id="tab_3">
                            </table>
                            <table class="table table-striped table-bordered table-hover" id="data_table_3">
                                <thead>
                                <tr role="row" class="heading">
                                    <th width="15%">部门名称</th>
                                    <th width="15%">编号</th>
                                    <th width="15%">描述</th>
                                    <th width="15%">操作</th>
                                </tr>
                                <tr role="row" class="filter">
                                    <!-- 登录名 -->
                                    <td><input type="text" class="form-control form-filter input-sm" name="search_name"></td>
                                    <td><input type="text" class="form-control form-filter input-sm" name="search_code"></td>
                                    <td><input type="text" class="form-control form-filter input-sm" name="search_descr"></td>
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

<div class="modal fade" id="joinByUser"  tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">人员权限控制</h4>
            </div>
            <div class="modal-body">
                <form method="POST" class="form-horizontal">
                    <div class="form-body">
                        <input type="hidden" id="userId">
                        <div class="form-group">
                            <label class="col-md-3 control-label">员工名称</label>
                            <div class="col-md-6">
                                <input type="text" class="input-sm form-filter" id="userName" readonly/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">员工编号</label>
                            <div class="col-md-6">
                                <input type="text" class="input-sm form-filter" id="userNo" readonly/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">选择数据列<span class="required">*</span></label>
                            <div class="col-md-6">
                                <select id="userSelect" class="form-control" >
                                <#list coList as column>
                                    <#if column.rIsEffective && column.rIsEffective=1>
                                        <option value="${column.rmchId!}">${column.name!}(${column.code!})</option>
                                    </#if>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn green ladda-button" data-style="expand-right" onclick="join(0);" id = 'sign_org_save'><span class="ladda-label"></span>确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="joinByUserGroup"  tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">部门权限控制</h4>
            </div>
            <div class="modal-body">
                <form method="POST" class="form-horizontal">
                    <div class="form-body">
                        <input type="hidden" id="userGroupId">
                        <div class="form-group">
                            <label class="col-md-3 control-label">部门名称</label>
                            <div class="col-md-6">
                                <input type="text" class="input-sm form-filter" id="userGroupName" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">选择数据列<span class="required">*</span></label>
                            <div class="col-md-6">
                                <select id="userGroupSelect" class="form-control" >
                            <#list coList as column>
                                <#if column.rIsEffective && column.rIsEffective=1>
                                    <option value="${column.rmchId!}">${column.name!}(${column.code!})</option>
                                </#if>
                            </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn green ladda-button" data-style="expand-right" onclick="join(1);"><span class="ladda-label"></span>加入</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
    <script src="${rc.contextPath}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $('.alert').delay(3000).hide(0);

        var grid1 = new Datatable();
        grid1.init({
            src: $("#data_table_1"),
            onError: function (grid1) {
            },
            dataTable: {
                "aLengthMenu": [
                    [10, 20, 50, 100, 150, 500],
                    [10, 20, 50, 100, 150, 500]
                ],
                "iDisplayLength": 10,
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/module/moduleColumnDatas/lines-${role.id}-${info.id}",
                "aaSorting": [
                    [ 0, "desc" ]
                ],
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5] }
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    { "sTitle": "权限名称", "mData": "tname"},
                    { "sTitle": "权限编码", "mData": "tcode"},
                    { "sTitle": "人员类型", "mData": "roleType", "mRender": function (data, type, row) {
                            if(data==1)return "部门";
                            if(data==0)return "员工";
                        }},
                    { "sTitle": "人员/部门", "mData": "udname"},
                    { "sTitle": "工号", "mData": "udno", "mRender": function (data, type, row) {
                            if(row.roleType==1)return "/";
                            if(row.roleType==0)return data;
                        }},
                    { "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {
                            //方法待定
                            var b = '<a href="${rc.contextPath}/role/getModuleColumnData-' + row.id + '-' + ${role.id?if_exists} +
                                    '"  class="btn btn-xs blue"  title="离开" >' +'离开</a>';
                            return b;
                        }}
                ]
            }
        });

        var grid2 = new Datatable();
        grid2.init({
            src: $("#data_table_2"),
            onError: function (grid2) {
            },
            dataTable: {
                "aLengthMenu": [
                    [10, 20, 50, 100, 150, 500],
                    [10, 20, 50, 100, 150, 500]
                ],
                "iDisplayLength": 10,
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/user/userGroup/query-user-notinrole",
                "aaSorting": [
                    [ 0, "desc" ]
                ],
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0, 1, 2, 3 ] }
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    { "sTitle":"登录名","mData":"userName"},
                    { "sTitle":"姓名","mData":"name"},
                    { "sTitle":"编号","mData":"userNo"},
                    { "sTitle":"手机","mData":"mobilePhone"},
//                    { "sTitle":"邮箱","mData":"email","mRender":function(data){
//                        return "<a href='mailto:"+data+"'>"+data+"</a>";
//                    }},
                    { "sTitle":"操作","mData":"","sDefaultContent":"","mRender":function(data,type,row){
                            return'<a class="delete btn green btn-xs black" href="javascript:joinByUser('+row.id+',\''+row.userNo+'\',\''+row.name+'\');"><i class="fa fa-level-up"></i>加入</a>';
                        }}
                ]
            }
        });

        var grid3 = new Datatable();
        grid3.init({
            src: $("#data_table_3"),
            onError: function (grid3) {
            },
            dataTable: {
                "aLengthMenu": [
                    [10, 20, 50, 100, 150, 500],
                    [10, 20, 50, 100, 150, 500]
                ],
                "iDisplayLength": 10,
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/user/userGroup/query-departments-notinrole",
                "aaSorting": [
                    [ 0, "desc" ]
                ],
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0, 1, 2, 3 ] }
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [

//                    { "sTitle":"登录名","mData":"userName"},
                    { "sTitle":"部门名称","mData":"name"},
                    { "sTitle":"编号","mData":"code"},
                    { "sTitle":"描述","mData":"descr"},
//                    { "sTitle":"邮箱","mData":"email","mRender":function(data){
//                        return "<a href='mailto:"+data+"'>"+data+"</a>";
//                    }},
                    { "sTitle":"操作","mData":"","sDefaultContent":"","mRender":function(data,type,row){
                            return'<a class="delete btn green btn-xs black" href="javascript:joinByUserGroup('+row.id+',\''+ row.name+'\');"><i class="fa fa-level-up"></i>加入</a>';
                        }}
                ]
            }
        });

        //保存角色模块数据列
        function saveModule(){
            Metronic.startPageLoading();
            var module = {};
            module.roleId = ${role.id!};
            module.moduleId=${info.id}
            var mList = new Array();
            $(".icheck").each(function(i) {
                var obj = {};
                var rmchId = $(this).attr("data-rmchId");
                var mid = $(this).attr("data-mId");
                if(rmchId!=null&&rmchId.length>0&&rmchId!="") obj.rmchId =  Number(rmchId);
                obj.moduleColumnId = Number(mid);

                if(this.checked==true){
                    obj.rIsEffective=1;
                }else{
                    obj.rIsEffective=0;
                }
                mList.push(obj);
            });
            module.mList = mList;
            $.ajax({
                type: 'POST',
                url:  "${rc.contextPath}/role/moduleColumnsSave",
                data: JSON.stringify(module),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function(resp) {
                    Metronic.stopPageLoading();
                    bootbox.alert(resp.message);
                    window.location.reload();
                }
            });

        }

        function joinByUser(id,userNo,name){
            $('#userName').val(name);
            $('#userNo').val(userNo);
            $('#userId').val(id);
            $('#joinByUser').modal('show');
        }
        function joinByUserGroup(id,name){
            $('#userGroupName').val(name);
            $('#userGroupId').val(id);
            $('#joinByUserGroup').modal('show');
        }

        function leave(id){
            bootbox.dialog({
                message: "确认要离开权限人员吗？",
                buttons: {
                    success: {
                        label: "确定",
                        className: "green ",
                        callback: function() {
                            Metronic.startPageLoading();
                            $.ajax({
                                url:'${rc.contextPath}/module/moduleColumnDatas/roleDelete/'+id,
                                type:'POST',
                                dataType:"json",
                                traditional:true,
                                success:function(data){
                                    Metronic.stopPageLoading();
                                    if(data.code=="200"){
                                        grid1.getDataTable().fnDraw();
                                    }
                                }
                            });
                        }
                    },
                    main: {
                        label: "取消",
                        className: "gray ",
                        callback: function() {
                            $(this).hide();
                        }
                    }
                }
            });
        }

        function join(type){
            Metronic.startPageLoading();
            var obj={};
            obj.roleType=type;
            if(type==0){//员工
                obj.roleModuleInfoColumnDataHeadId= $('#userSelect').val();
                obj.userIdOrDeptId= $('#userId').val();
            }else if(type==1){//部门
                obj.roleModuleInfoColumnDataHeadId= $('#userGroupSelect').val();
                obj.userIdOrDeptId= $('#userGroupId').val();
            }

            $.ajax({
                url:'${rc.contextPath}/module/moduleColumnDatas/roleSave',
                type:'POST',
                dataType:"json",
                data: JSON.stringify(obj),
                contentType: "application/json; charset=utf-8",
                traditional:true,
                success:function(data){
                    Metronic.stopPageLoading();
                    if(data.code=="200"){
                        if(type==0){//员工
                            $('#joinByUser').modal('hide');
                        }else if(type==1){//部门
                            $('#joinByUserGroup').modal('hide');
                        }
                        grid1.getDataTable().fnDraw();
                        bootbox.alert(data.msg);
                    }else{
                        bootbox.alert(data.msg);
                    }
                }
            });
        }


    </script>
</content>
</html>
