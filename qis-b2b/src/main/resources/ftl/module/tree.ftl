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
                            <i class="fa fa-square-o"></i>数据信息表单
                        </div>
                        <div class="actions">
                            <div class="btn-group">
                                <a class="btn green btn-parent">
                                    <i class="fa fa-plus"></i>
                                    <span class="hidden-480">新增父节点</span>
                                </a>
                                <button type="button" class="btn green btn-children" disabled="disabled">
                                    <i class="fa fa-plus"></i>
                                    <span class="hidden-480">新增子节点</span>
                                </button>
                                <button type="button" class="btn blue btn-edit" disabled="disabled">
                                    <i class="fa fa-edit"></i>
                                    <span class="hidden-480">编辑此节点</span>
                                </button>
                                <button type="button" class="btn red btn-delete" disabled="disabled">
                                    <i class="fa fa-trash-o"></i>
                                    <span class="hidden-480">删除此节点</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <form class="form-horizontal" action="${rc.contextPath}/module/save" method="POST"
                              id="moduleForm">
                            <input type="hidden" name="id"/>
                            <input type="hidden" name="pid" value="0"/>
                            <div class="form-body">
                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button>
                                    请检查后再提交
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3 nowrap">模块名称<span
                                                    class="required">*</span></label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="name"
                                                           placeholder="请录入模块名称" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">编码<span
                                                    class="required">*</span></label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="code"
                                                           placeholder="请录入编码" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label  class="control-label col-md-3">链接类型</label>
                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <select type="hidden" id="menuType" name="menuType" class="form-control" disabled="disabled" placeholder="请选择是菜单还是选项">
                                                        <option value="0">无</option>
                                                        <option value="1">内部</option>
                                                        <option value="2">外部</option>

                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">链接地址</label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="linkUrl"
                                                           placeholder="请录入链接地址" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>



                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">父模块</label>
                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <select id="parentModuleId" name="parentModuleId" class="form-control" disabled="disabled" placeholder="请录入上级模块编号">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">描述</label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="descr"
                                                           placeholder="请录入描述" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label  class="control-label col-md-3">类型</label>
                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <select id="isMenu" name="isMenu" class="form-control" disabled="disabled" placeholder="请选择是菜单还是选项">
                                                        <option value="1">菜单</option>
                                                        <option value="0">选项</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">是否公开</label>
                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <select id="isOpen" name="isOpen" class="form-control" disabled="disabled" placeholder="请选择是否公开">
                                                        <option value="1">公开</option>
                                                        <option value="0">不公开</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row ">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">排序</label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="sort"
                                                           placeholder="请录入排序号" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group permissions"  style="display: none">
                                            <label class="control-label col-md-2">权限</label>
                                            <div class="col-md-10">
                                                <#list permissions as permission>
                                                    <input type="checkbox" value="${permission.id}" id="pers" name="pers">${permission.name}
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
                                                <button type="submit" class="btn green" disabled="disabled">保存</button>
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
<#--<script src="${rc.contextPath}/assets/global/plugins/JsTree/dist/JsTree.js" type="text/javascript"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
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
                    url: '${rc.contextPath}/module/get-infos/' + module_id,
                    type: 'GET',
                    beforeSend: function () {
                        $(".input-icon > i").removeClass("fa-check fa-warning").attr("data-original-title", "");
                        $(".form-group").removeClass("has-error has-success");
                        $(".form-body .alert-danger").css("display", "none");
                    },
                    success: function (msg) {
                        var data = msg.module;
                        $('input[name=id]').val(data.id);
                        $('input[name=name]').val(data.name);
                        $('input[name=code]').val(data.code);
                        $('input[name=linkUrl]').val(data.linkUrl);
                        $('input[name=descr]').val(data.descr);
                        $('input[name=sort]').val(data.sort);
                        $("select[name=isMenu] option[value='" + data.isMenu + "']").prop("selected", true);
                        $("select[name=isOpen] option[value='" + data.isOpen + "']").prop("selected", true);
                        $("select[name=menuType] option[value='" + data.menuType + "']").prop("selected", true);
                        var value = "";
                        if (data.parentModuleId != 0) {
                            $(".permissions").attr("style","display:block;");
                            for (var i = 0; i < msg.parents.length; i++) {
                                var obj = msg.parents[i];
                                value = value + "<option value=" + obj.id + ">" + obj.name + "</option>";
                            }

                            $('.btn-children').attr('disabled', "disabled");
                        } else {
                            $(".permissions").attr("style","display:none;");
                            $('.btn-children').enable();
                            $('.btn-parent').enable();
                        }

                        $("input[name=pers]").parents('span').removeClass("checked");
                        if(msg.myPers){
                            for(var i=0;i<msg.myPers.length;i++){
                                var myPer=msg.myPers[i];
                                var qdwd=myPer.permissionId;
                                $("input[name=pers][value='" +myPer.permissionId+"']").parents('span').toggleClass("checked");
                                $("input[name=pers][value='" +myPer.permissionId+"']").prop("checked", true);
                            }
                        }
                        document.getElementById("parentModuleId").innerHTML = value;
                        $("select[name=parentModuleId] option[value='" + data.parentModuleId + "']").prop("selected", true);
                        $('#moduleForm :input').each(function (a) {
                            $(this).attr('disabled', "disabled");
                            $(this).attr("readonly", "true");
                        });
                        $('.btn-edit').enable();
                        $('.btn-delete').enable();
                    }
                });
            }
        });
        var error = $('.alert-danger', form);
        form.validate({
            errorElement: 'span',
            errorClass: 'help-block help-block-error',
            focusInvalid: false,
            rules: {
                name: {
                    minlength: 2,
                    maxlength: 30,
                    required: true,
                    remote: {
                        type: "GET",
                        contentType: "application/json;charset=UTF-8",
                        url: "${rc.contextPath}/module/checkExist",//请求地址
                        //传递的参数,不写默认是当前校验的值
                        data: {
                            //多参数传递,每个值需要用function返回,
                            "name": function () {
                                return $(" input[ name='name' ] ").val();
                            },
                            "pid": function () {
                                var parentModuleId = $("select[name=parentModuleId] option:selected").val();
                                if (!parentModuleId)
                                    parentModuleId = 0;
                                return parentModuleId;
                            },
                            "status": function () {
                                return status;
                            },
                            "id": function () {
                                var id = $("input[name=id]").val();
                                if (!id)
                                    id = 0;
                                return id;
                            }
                        }
                    }
                },
                code:{
                    required: true,
                    remote: {
                        type: "GET",
                        contentType: "application/json;charset=UTF-8",
                        url: "${rc.contextPath}/module/checkCode",//请求地址
                        //传递的参数,不写默认是当前校验的值
                        data: {
                            //多参数传递,每个值需要用function返回,
                            "code": function () {
                                return $(" input[ name='code' ] ").val();
                            },
                             "status": function () {
                                return status;
                             },
                            "id": function () {
                                var id = $("input[name=id]").val();
                                if (!id)
                                    id = 0;
                                return id;
                            }
                        }
                    }
                }

            },
            messages: {
                name: {
                    remote: "当前层级下存在同名项",
                    required: "名称不能为空"
                },
                code: {
                    remote: "存在相同编码",
                    required: "编码不能为空"
                }
            },
            invalidHandler: function (event, validator) {
                error.show();
                Metronic.scrollTo(error, -200);
            },
            errorPlacement: function (e, element) {
                var icon = $(element).parent('.input-icon').children('i');
                icon.removeClass('fa-check').addClass("fa-warning");
                icon.attr("data-original-title", e.text()).tooltip({'container': 'body'});
            },
            highlight: function (element) {
                $(element).closest('.form-group').removeClass("has-success").addClass('has-error');
            },
            unhighlight: function (element) {
            },
            success: function (label, element) {
                var icon = $(element).parent('.input-icon').children('i');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
                icon.removeClass("fa-warning").addClass("fa-check");
            },
            submitHandler: function (form) {
                $('select[name=parentModuleId]').enable();
                $("select[name=isMenu]").enable();
                error.hide();
                form.submit();
            }
        });

        $('.btn-parent').click(function () {
            form.resetForm();
            $('input[name=id]').val("");
            $('select[name=parentModuleId]').val("");
            $(".permissions").attr("style","display:none;");
            $('#moduleForm :input').each(function (a) {
                $(this).enable();
                $(this).attr("readonly", false);
            });
            $("select[name=isMenu] option[value=1]").prop("selected", true);
            $("select[name=isMenu]").attr('disabled', "disabled");
            $('select[name=parentModuleId]').attr('disabled', "disabled");
            $('.btn-edit').attr('disabled', "disabled");
            $('.btn-delete').attr('disabled', "disabled");
            status = "create";
        });
        $('.btn-children').click(function () {
            form.resetForm();
            $(".permissions").attr("style","display:block;");
            $('input[name=id]').val("");
            $.ajax({
                url: '${rc.contextPath}/module/get-infos/'+module_id,
                type: 'GET',
                success: function (msg) {
                    var data=msg.module;
                    var value = "";
                    for (var i = 0; i < msg.parents.length; i++) {
                        var obj = msg.parents[i];
                        value = value + "<option value=" + obj.id + ">" + obj.name + "</option>";
                    }
                    document.getElementById("parentModuleId").innerHTML = value;
                    $("select[name=parentModuleId] option[value='" + data.id + "']").attr("selected", "selected");
                }
            });
            $('#moduleForm :input').each(function (a) {
                $(this).enable();
                $(this).attr("readonly", false);
            });
            $("select[name=isMenu] option[value=0]").prop("selected", true);
            $("select[name=isMenu]").attr('disabled', "disabled");
            $('select[name=moduleTypeId]').attr('disabled', "disabled");
            $('.btn-edit').attr('disabled', "disabled");
            $('.btn-delete').attr('disabled', "disabled");
            status = "create";
        });
        $('.btn-edit').click(function () {
            $('#moduleForm :input').each(function (a) {
                $(this).enable();
                $(this).attr("readonly", false);
                status = "update";
            });
            $('select[name=parentModuleId]').attr('disabled', "disabled");
            $("select[name=isMenu]").attr('disabled', "disabled");
        });
        $('.btn-cancel').click(function () {
            form.resetForm();
            $('#moduleForm :input').each(function (a) {
                $(this).attr('disabled', "disabled");
                $(this).attr("readonly", "true");
            });
            $('div.form-group ').removeClass('has-error');
            $('div.alert-danger').css('display', 'none');
        });
        $('.btn-delete').click(function () {
            bootbox.dialog({
                message: "确认要删除此模块吗？",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/module/delete/' + module_id,
                                type: 'DELETE',
                                success: function (data) {
                                    Metronic.stopPageLoading();
                                    if (data.success) {
                                        location.reload();
                                    } else {
                                        bootbox.alert(data.message);
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消", className: "gray", callback: function () {
                            $(this).hide();
                        }
                    }
                }
            });
        });

    </script>
</content>
</html>
