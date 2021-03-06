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

        .pagination {
            display: inline-block;
            padding-left: 0;
            margin: 20px 0;
            border-radius: 4px;
        }

        .pagination li {
            display: inline;
        }

        .pagination li a {
            position: relative;
            float: left;
            padding: 6px 12px;
            margin-left: -1px;
            line-height: 1.428571429;
            text-decoration: none;
            background-color: #fff;
            border: 1px solid #ddd;
        }

        .pagination li:first-child a {
            margin-left: 0;
            border-bottom-left-radius: 4px;
            border-top-left-radius: 4px;
        }

        .pagination li:last-child a {
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px;
        }

        .pagination li a:hover, .pagination li a:focus {
            background-color: #eee;
        }

        .pagination .active a, .pagination .active a:hover, .pagination .active a:focus {
            z-index: 2;
            color: #fff;
            cursor: default;
            background-color: #428bca;
            border-color: #428bca;
        }

        .pagination .disabled a, .pagination .disabled a:hover, .pagination .disabled a:focus {
            color: #999;
            cursor: not-allowed;
            background-color: #fff;
            border-color: #ddd;
        }

        .pagination-lg li a {
            padding: 10px 16px;
            font-size: 18px;
        }

        .pagination-sm li a, .pagination-sm li span {
            padding: 5px 10px;
            font-size: 12px;
        }
    </style>
</head>
<body>

<div class="actions">
    <div class="btn-group">
        <@shiro.hasPermission name="module-list-create">
            <a class="btn btn-parent">
                <i class="fa fa-plus"></i>
                <span class="hidden-480">新增父节点</span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="module-list-create">
            <a type="button" class="btn btn-children" disabled="disabled">
                <i class="fa fa-plus"></i>
                <span class="hidden-480">新增子节点</span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="module-list-edit">
            <a type="button" class="btn btn-edit" disabled="disabled">
                <i class="fa fa-edit"></i>
                <span class="hidden-480">编辑此节点</span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="module-list-delete">
            <a type="button" class="btn btn-delete" disabled="disabled">
                <i class="fa fa-trash-o"></i>
                <span class="hidden-480">删除此节点</span>
            </a>
        </@shiro.hasPermission>
    </div>
</div>

<!--导航菜单栏-->
<ul class="page-breadcrumb breadcrumb">
    <li>
        <a href="#">
            <i class="fa fa-chevron-left"></i>
        </a>
    </li>
    <li>
        <a href="#">
            <i class="fa fa-chevron-right"></i>
        </a>
    </li>
    <li>
        <a href="#">
            <i class="fa fa-refresh"></i>
        </a>
    </li>
    <li class="vertical-line"></li>
    <li>
        <a href="#">系统设置</a>
        <i class="fa fa-angle-right"></i>
    </li>
    <li>
        <a href="${rc.contextPath}/ftl/moduletionary/list">模块</a>
        <i class="fa fa-angle-right"></i>
    </li>
    <li>
        <span class="active">模块列表</span>
    </li>
</ul>

<!--数据列表-->
<div class="QIS-data-list">
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
                                                <label class="control-label col-md-3">链接类型</label>
                                                <div class="col-md-9">
                                                    <div class="input-icon right">
                                                        <i class="fa"></i>
                                                        <select type="hidden" id="menuType" name="menuType"
                                                                class="form-control" disabled="disabled"
                                                                placeholder="请选择是菜单还是选项">
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
                                                        <select id="parentModuleId" name="parentModuleId"
                                                                class="form-control" disabled="disabled"
                                                                placeholder="请录入上级模块编号">
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
                                                <label class="control-label col-md-3">类型</label>
                                                <div class="col-md-9">
                                                    <div class="input-icon right">
                                                        <i class="fa"></i>
                                                        <select id="isMenu" name="isMenu" class="form-control"
                                                                disabled="disabled" placeholder="请选择是菜单还是选项">
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
                                                        <select id="isOpen" name="isOpen" class="form-control"
                                                                disabled="disabled" placeholder="请选择是否公开">
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
                                            <div class="form-group">
                                                <label class="control-label col-md-3">图标</label>
                                                <div class="col-md-7">
                                                    <div class="input-icon right">

                                                        <i class="fa"></i>
                                                        <input type="text" id="icon" class="form-control" name="icon"
                                                               placeholder="请选择图标图标" readonly="true"/>
                                                    </div>

                                                </div>
                                                <span class="input-group-btn">
                                                    <button id="icon_add" class="btn btn-primary"
                                                            onclick="showIconModul()"
                                                            type="button"> 选择</button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions fluid">
                                        <div class="pull-right">
                                            <button type="submit" class="btn btn-primary" disabled="disabled">保存
                                            </button>
                                            <button type="button" class="btn default btn-cancel"
                                                    disabled="disabled">取消
                                            </button>
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

<div id="icon_add_div" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog" style="width:900px;">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom:none;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="row" id="roletableDatas">
                    <div class="col-md-12">
                        <div class="portlet">
                            <div class="portlet-title">
                                <div class="caption"><i class="fa fa-cogs"></i>图标列表</div>
                                <div class="actions">
                                    <div class="btn-group">
                                        <select class="form-control" id="_dlgCheckIcon" onchange="checkIcon()">
                                            <option value="new">new</option>
                                            <option value="web-application">web-application</option>
                                            <option value="hand">hand</option>
                                            <option value="transportation">transportation</option>
                                            <option value="gender">gender</option>
                                            <option value="file-type">file-type</option>
                                            <option value="spinner">form-control</option>
                                            <option value="payment">payment</option>
                                            <option value="chart">chart</option>
                                            <option value="currency">currency</option>
                                            <option value="text-editor">text-editor</option>
                                            <option value="directional">directional</option>
                                            <option value="video-player">video-player</option>
                                            <option value="brand">brand</option>
                                            <option value="medical">medical</option>
                                            <option value="simplelineicons-demo">simplelineicons-demo</option>
                                            <option value="glyphicon">glyphicon</option>
                                        <#--<option value="Basic Icons">Basic Icons</option>-->
                                        <#--<option value="Circle Icons">Circle Icons</option>-->
                                        <#--<option value="Solid Icons">Solid Icons</option>-->
                                        <#--<option value="Large Size">Large Size</option>-->
                                        <#--<option value="Small Size">Small Size</option>-->

                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <!-- 必须加上row样式,不然高度为1px,不能显示边框 -->
                                <div id="tab_1_2" class="tab-pane glyphicons-demo active">
                                    <ul id="iconTable" class="bs-glyphicons bs-glyphicons-list">
                                    </ul>
                                </div>
                                <div class="row">
                                    <table id="iconPageBar" class="col-md-8"
                                           style="text-align: center; width: 100%;">
                                        <tr>
                                            <td class="form-inline">
                                                <div class="pagination" id="iconPager"
                                                     style="font-size: 18px; text-align: center; vertical-align: middle;"></div>
                                                <span style="margin-top: 0px; size: 12px; color: #8a8a8a">跳转到</span>
                                                <input type="text" id="toMPage"
                                                       style="font-size: 18px; width: 50px; height: 30px;"
                                                       class="input-inline page_input"
                                                       onkeyup="if(isNaN(value))execCommand('undo');if(event.keyCode==32)execCommand('undo');"
                                                       onafterpaste="if(isNaN(value))execCommand('undo'));if(event.keyCode==32)execCommand('undo');">
                                                <button style="width: 40px; height: 30px;" id="gotoMPage"
                                                        class="btn">
                                                    GO
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="col-md-2"></div>
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
                        $('input[name=icon]').val(data.icon);
                        $("select[name=isMenu] option[value='" + data.isMenu + "']").prop("selected", true);
                        $("select[name=isOpen] option[value='" + data.isOpen + "']").prop("selected", true);
                        $("select[name=menuType] option[value='" + data.menuType + "']").prop("selected", true);
                        var value = "";
                        if (data.parentModuleId != 0) {
                            for (var i = 0; i < msg.parents.length; i++) {
                                var obj = msg.parents[i];
                                value = value + "<option value=" + obj.id + ">" + obj.name + "</option>";
                            }
                            $('.btn-children').attr('disabled', "disabled");
                        } else {
                            $('.btn-children').enable();
                            $('.btn-parent').enable();
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
                code: {
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
            $('input[name=id]').val("");
            $.ajax({
                url: '${rc.contextPath}/module/get-infos/' + module_id,
                type: 'GET',
                success: function (msg) {
                    var data = msg.module;
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

        /**获取所有的功能****/
        var currentPage = 1; //第几页
        var pageCount = 32; //每页显示多少条记录数据
        var totalPages = 0;
        //分页查询
        var queryByPage = function () {
            Metronic.startPageLoading();
            $.ajax({
                dataType: "json",
                cache: true,
                type: "GET",
                url: "${rc.contextPath}/assets/global/newIcon.json",
                traditional: true,
                success: function (data) {
                    window.Metronic.stopPageLoading();
                    var checkIcon = $("#_dlgCheckIcon").val();
                    data = data[checkIcon];
                    //删除所有子项
                    $("#iconTable").empty();
                    var total = 0, str = '';
                    $.each(data, function (i, n) {
                        str += '<li onclick="addIconImage(\'' + n + '\');"><span class="' + n + '" style="font-size:24px;margin:5px auto 10px;display:block"></span><span>' + n + ' </span></li>';
                        total++;
                    });
                    if (data == null || data == undefined || total == 0) {
                        return;
                    }
                    if (total <= pageCount) {
                        $("#iconPageBar").css({visibility: "hidden"});
                    } else {
                        $("#iconPageBar").css({visibility: "visible"});
                        str = '';
                        var start = (currentPage - 1) * pageCount;
                        var k = 0;
                        $.each(data, function (i, n) {
                            if (i >= start) {
                                str += '<li onclick="addIconImage(\'' + n + '\');"><span class="' + n + '" style="font-size:24px;margin:5px auto 10px;display:block"></span><span>' + n + ' </span></li>';
                                k++;
                            }
                            if (pageCount == k) {
                                return false;
                            }
                        });
                    }
                    $("#iconTable").append(str);
                    //总页数
                    if (total % pageCount != 0) {
                        totalPages = parseInt(total / pageCount) + 1;
                    } else {
                        totalPages = total / pageCount;
                    }
                    var options = {
                        currentPage: currentPage,
                        totalPages: totalPages,
                        onPageClicked: function (event, originalEvent, type, page) {
                            currentPage = page;
                            queryByPage(currentPage, pageCount);
                        }
                    }
                    $('#iconPager').bootstrapPaginator(options);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
//                    bootbox.alert("网络异常,数据不能成功返回");
                }
            });
        }
        //初始化列表
        queryByPage(currentPage, pageCount);
        //翻页
        $("#gotoMPage").bind("click", function () {
            if ($("#toMPage").val() == null || "" == $("#toMPage").val()) {
                bootbox.alert("请输入跳转页码");
                return;
            }
            var thisPage = parseInt($("#toMPage").val());
            if (!(thisPage > 0 && thisPage <= totalPages)) {
                bootbox.alert("请输入正确跳转页码");
                return;
            }
            $('#iconPager').bootstrapPaginator("show", thisPage);
            currentPage = thisPage;
            queryByPage(currentPage, pageCount);
        });

        function showIconModul() {
            $('#icon_add_div').modal('show');
        }

        function checkIcon() {
            this.currentPage = 1; //第几页
            this.pageCount = 32; //每页显示多少条记录数据
            this.totalPages = 0;
            $('#toMPage').val('');
            queryByPage(1, 32);
        }

        function addIconImage(data) {
            $('#icon').val(data);
            $('#icon_add_div').modal('hide');
        }

    </script>
</content>
</html>
