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
                    <span class="caption-subject font-dark sbold uppercase">角色(${role.name})模块(${info.name})--权限管理  </span>
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
                            <#list moList as module>
                                <label>
                                    <input type="checkbox" class="icheck" <#if module.rIsEffective && module.rIsEffective=1>checked</#if>
                                           data-mId="${module.modulePermissionId!}" data-rmphId="${module.rmphId!}">
                                    ${module.name!}(${module.code!})
                                </label>
                            </#list>
                            </div>
                        </div>


                        <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
                        </table>
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
    <script src="${rc.contextPath}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $('.alert').delay(3000).hide(0);


        //保存角色模块权限
        function saveModule(){
            Metronic.startPageLoading();
            var module = {};
            module.roleId = ${role.id!};

            var mList = new Array();
            $(".icheck").each(function(i) {
                var obj = {};
                var rmphId = $(this).attr("data-rmphId");
                var mid = $(this).attr("data-mId");
                if(rmphId!=null&&rmphId.length>0&&rmphId!="") obj.rmphId =  Number(rmphId);
                obj.modulePermissionId = Number(mid);

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
                url:  "${rc.contextPath}/role/modulePermissionsSave",
                data: JSON.stringify(module),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function(resp) {
                    Metronic.stopPageLoading();
                    bootbox.alert(resp.msg);
                    window.location.reload();
                }
            });

        }


    </script>
</content>
</html>
