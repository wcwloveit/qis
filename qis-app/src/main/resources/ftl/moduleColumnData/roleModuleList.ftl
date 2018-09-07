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
                        <a class="btn green btn-outline btn-circle" href="javascript:save();" data-toggle="dropdown">
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
                                           data-id="${module.code!}" data-id="${module.code!}" data-id="${module.code!}"
                                           data-id="${module.code!}">
                                    ${module.name!}(${module.code!})${module.rmphId!}
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
        function save(){

        }


    </script>
</content>
</html>
