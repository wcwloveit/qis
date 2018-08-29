<html>
<head>
    <title>模块管理</title>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/select2/css/select2.css" type="text/css"/>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
          type="text/css"/>
    <style type="text/css">
        .vakata-context {
            z-index: 999 !important;
        }
        .pagination { display: inline-block; padding-left: 0; margin: 20px 0; border-radius: 4px; }
        .pagination li { display: inline; }
        .pagination li a { position: relative; float: left; padding: 6px 12px; margin-left: -1px; line-height: 1.428571429; text-decoration: none; background-color: #fff; border: 1px solid #ddd; }
        .pagination li:first-child a { margin-left: 0; border-bottom-left-radius: 4px; border-top-left-radius: 4px; }
        .pagination li:last-child a { border-top-right-radius: 4px; border-bottom-right-radius: 4px; }
        .pagination li a:hover, .pagination li a:focus { background-color: #eee; }
        .pagination .active a, .pagination .active a:hover, .pagination .active a:focus { z-index: 2; color: #fff; cursor: default; background-color: #428bca; border-color: #428bca; }
        .pagination .disabled a, .pagination .disabled a:hover, .pagination .disabled a:focus { color: #999; cursor: not-allowed; background-color: #fff; border-color: #ddd; }
        .pagination-lg li a { padding: 10px 16px; font-size: 18px; }
        .pagination-sm li a, .pagination-sm li span { padding: 5px 10px; font-size: 12px; }
    </style>
</head>
<body>
<!--导航菜单栏-->
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="#">系统设置</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="${rc.contextPath}/ftl/moduletionary/list">模块</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="${rc.contextPath}/moduleTypes/index">模块列表</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">${name?if_exists}</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<!--数据列表-->
<div class="row">
    <div class="col-md-4">
        <div class="portlet light portlet-fit bordered">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-sitemap"></i>模块树</div>
            </div>
            <div class="portlet-body" id="moduleTree"></div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="row">
            <div class="col-md-12">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-square-o"></i>权限列表
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <form class="form-horizontal" action="${rc.contextPath}/modulePermissions/save" method="POST"
                              id="moduleForm">
                            <input type="hidden" name="id"/>
                            <div class="form-body">
                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button>
                                    请检查后再提交
                                </div>
                                <div class="row ">
                                    <div class="col-md-10">
                                        <#--style="display: none"-->
                                        <div class="form-group permissions" style="display: none">
                                            <label class="control-label col-md-2">权限</label>
                                            <div class="col-md-10">
                                            <#list permissions as permission>
                                                <input type="checkbox" value="${permission.id}" name="pers">${permission.name}
                                            </#list>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="row">
                                        <div class="col-md-6">
                                        </div>
                                        <div class="col-md-6">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button type="submit" class="btn green" >保存</button>
                                                <button type="button" class="btn default btn-cancel"
                                                        disabled="disabled">取消
                                                </button>
                                            </div>
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

</body>
<content tag="script">
    <script src="${rc.contextPath}/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/scripts/datatable.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/lib/jquery.form.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/additional-methods.min.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/localization/messages_zh.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jstree/dist/jstree.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/drags/bootstrap-paginator.js" type="text/javascript"></script>
    <script type="text/javascript">
        var status = "";
        var form = $('#moduleForm'), module_id = 0;
        $("#moduleTree").jstree({
            "core": {
                "animation": 0,
                "themes": {
                    theme: "classic",
                    "dots": true,
                    "icons": true
                },
                "check_callback": true,
                "data": {
                    'dataType': 'json',
                    'type': "post",
                    'url': "${rc.contextPath}/module/list",
                },
            },
            "types": {
                "default": {
                    "valid_children": ["default", "file"]
                }
            },
            "plugins": ["types", "wholerow"]
        }).on("select_node.jstree", function (node, selectd) {
            module_id = selectd.node.id;
            if (module_id) {
                $.ajax({
                    url: '${rc.contextPath}/modulePermissions/getPers/' + module_id,
                    type: 'GET',
                    success: function (msg) {
                        var pers=msg.myPers;
                        var module=msg.module;
                        $("input[name=id]").val(module.id);
                        if (module.parentModuleId != 0) {
                            $(".permissions").attr("style","display:block;");
                        } else {
                            $(".permissions").attr("style","display:none;");
                        }
                        $("input[name=pers]").removeProp("checked");
                        $("input[name=pers]").parents('span').removeClass("checked");
                        if(pers){
                            for(var i=0;i<pers.length;i++){
                                var per=pers[i];
                                $("input[name=pers][value='" +per.permissionId+"']").prop("checked", true).parents('span').toggleClass("checked");
                            }
                        }

                    }
                });
            }
        });

    </script>
</content>
</html>
