<html>
<head>
    <title>活动管理</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="row">
    <div class="col-md-12">
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
                    <span class="caption-subject font-dark sbold uppercase">角色拥有的模块列表</span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-container">
                    <div>
                        <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div id="user_list_per" class="modal fade" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog" style="width:800px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">权限列表</h4>
                </div>
                <div class="modal-body">
                    <div class="row" id="tableDatas">
                        <div class="col-md-12">
                            <div class="portlet">
                                <div class="portlet-body">
                                    <div class="form-group permissions">
                                        <form class="form-horizontal" action="${rc.contextPath}/role/role/permissionsSave" method="POST"
                                              id="permissionsForm">
                                            <input id="roleId" name="roleId" value="${role.id?if_exists}" style="display: none">
                                            <input type="text"  id="moduleId" name="moduleId"  value="0" style="display: none">
                                              <div id="permissions" class="col-md-10">
                                              </div>
                                              <div class="form-actions">
                                                  <div class="row">
                                                      <div class="col-md-offset-3 col-md-9">
                                                          <button type="submit" class="btn green">提交</button>
                                                      </div>
                                                  </div>
                                              </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="user_list_col" class="modal fade" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog" style="width:800px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">数据列</h4>
                </div>
                <div class="modal-body">
                    <div class="row" id="tableDatas">
                        <div class="col-md-12">
                            <div class="portlet">
                                <div class="portlet-body">
                                    <div class="form-group columnDatas">
                                        <form class="form-horizontal" action="${rc.contextPath}/role/role/columnDatasSave" method="POST"
                                              id="columnDatasForm">
                                            <input id="roleId" name="roleId" value="${role.id?if_exists}" style="display: none">
                                            <input type="text"  id="moduleId" name="moduleId"  value="0" style="display: none">
                                            <div id="columnDatas" class="col-md-10">
                                            </div>
                                            <div class="form-actions">
                                                <div class="row">
                                                    <div class="col-md-offset-3 col-md-9">
                                                        <button type="submit" class="btn green">提交</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
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
                "sAjaxSource": "${rc.contextPath}/role/role/moduleList/${role.id?if_exists}",
                "aaSorting": [
                    [ 0, "desc" ]
                ],
                "aoColumnDefs": [
                    { "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, ] }
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    { "sTitle": "模块名称", "mData": "name", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "模块编码", "mData": "code", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "链接地址", "mData": "linkUrl", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "链接类型", "mData": "menuType", "mRender": function (data, type, row) {
                            if (data == '0') {
                                return "无";
                            }else if (data == '1') {
                                return "内部";
                            }else if (data == '2') {
                                return "外部";
                            }
                        }},
                    { "sTitle": "描述", "mData": "descr", "mRender": function (data, type, row) {
                            return data;
                        }},
                    { "sTitle": "模块类型", "mData": "isMenu", "mRender": function (data, type, row) {
                            if (data == '1') {
                                return "菜单";
                            }else if (data == '0') {
                                return "选项";
                            }
                        }},
                    { "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, row) {
                            var a = '<a href="javascript:void(0);" onclick="assign('+row.id+')"  class="btn btn-xs blue"  title="权限管理" >' +
                                    '<i class="glyphicon glyphicon-pencil"></i>权限管理</a>';
                            var b = '<a href="javascript:void(0);" onclick="assignCol('+row.id+')"  class="btn btn-xs blue"  title="权限管理" >' +
                                    '<i class="glyphicon glyphicon-pencil"></i>数据列管理</a>';
                            return a+b;
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

        function assign(moduleId){
            $('input[name=moduleId]').val(moduleId);
            $.ajax({
                url: '${rc.contextPath}/module/module/getPermissions?moduleId=' + moduleId+'&roleId=${role.id?if_exists}',
                type: 'GET',
                async:false,
                success: function (msg) {
                    var permissions=msg.permissions;
                    var perIds=msg.perIds;
                    var value="";
                    for (var i = 0; i < permissions.length; i++) {
                        var obj = permissions[i];
                        value = value + "<input type='checkbox' value='"+obj.id+"'} id='ids' name='ids'>"+obj.name+" ";
                    }
                    document.getElementById("permissions").innerHTML = value;
                    for (var i = 0; i < perIds.length; i++) {
                        $("input[name=ids][value='" +perIds[i]+"']").parents('span').toggleClass("checked");
                        $("input[name=ids][value='" +perIds[i]+"']").prop("checked", true);
                    }

                }
            });
            $('#user_list_per').modal('show');
        }

        function assignCol(moduleId){
            $('input[name=moduleId]').val(moduleId);
            $.ajax({
                url: '${rc.contextPath}/module/module/getColumnDatas?moduleId=' + moduleId+'&roleId=${role.id?if_exists}',
                type: 'GET',
                async:false,
                success: function (msg) {
                    var columnDatas=msg.columnDatas;
                    var colIds=msg.colIds;
                    var value="";
                    for (var i = 0; i < columnDatas.length; i++) {
                        var obj = columnDatas[i];
                        value = value + "<input type='checkbox' value='"+obj.id+"'} id='colIds' name='colIds'>"+obj.name+" ";
                    }
                    document.getElementById("columnDatas").innerHTML = value;
                    for (var i = 0; i < colIds.length; i++) {
                        $("input[name=colIds][value='" +colIds[i]+"']").parents('span').toggleClass("checked");
                        $("input[name=colIds][value='" +colIds[i]+"']").prop("checked", true);
                    }

                }
            });
            $('#user_list_col').modal('show');
        }





    </script>
</content>
</html>
