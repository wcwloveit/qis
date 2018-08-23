<html>
<head>
    <title>数据字典管理</title>
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
                <a href="${rc.contextPath}/dictionary/list">数据字典</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">数据字典列表</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<!--数据列表-->
<div class="row">
    <div class="col-md-4">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-sitemap"></i>数据字典树</div>
            </div>
            <div class="portlet-body" id="dicTree"></div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="row">
            <div class="col-md-12">
                <div class="portlet box green-haze">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-square-o"></i>数据信息表单
                        </div>
                        <div class="actions">
                            <div class="btn-group">
                                <a class="btn gray btn-refresh">
                                    <i class="glyphicon glyphicon-refresh"></i>
                                    <span class="hidden-480">刷新</span>
                                </a>
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
                        <form class="form-horizontal" action="${rc.contextPath}/dictionary/save" method="POST"
                              id="dicForm">
                            <input type="hidden" name="id"/>
                            <input type="hidden" name="dicPid" value="0"/>
                            <input type="hidden" name="path"/>

                            <div class="form-body">
                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button>
                                    请检查后再提交
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-2 nowrap">名称<span
                                                    class="required">*</span></label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="dicValue"
                                                           placeholder="请录入数据名称" readonly="true"/>
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
                                                    <input type="text" class="form-control" name="dicKey"
                                                           placeholder="请录入数据编码" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-2">类型</label>

                                            <div class="col-md-10">
                                                <select id="type" name="type" class="form-control" disabled="disabled"
                                                        onchange="typeChange()">
                                                    <option value="CATEGORY">类</option>
                                                    <option value="ITEM">项</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">状态</label>

                                            <div class="col-md-9">
                                                <select name="isDeleted" class="form-control" disabled="disabled">
                                                    <option value="0">有效</option>
                                                    <option value="1">失效</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-2">排序</label>

                                            <div class="col-md-10">
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
                                            <label class="control-label col-md-3">所属类型</label>

                                            <div class="col-md-9">
                                                <select id="levelId" name="levelId" class="form-control"
                                                        disabled="disabled" onchange="pidChanged()">

                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-2 nowrap">描述</label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="desc"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">字典类型<span
                                                    class="required">*</span></label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="dictionaryType"
                                                           placeholder="请录入字典类型" readonly="true"/>
                                                </div>
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
        var form = $('#dicForm'), dic_id = 0, text, level_id = 0;
        $("#dicTree").jstree({
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
                    'url': "${rc.contextPath}/dictionary/list",
                },
            },
            "types": {
                "default": {
                    "valid_children": ["default", "file"]
                }
            },
            "plugins": ["types", "wholerow"]
        }).on("select_node.jstree", function (node, selectd) {
            dic_id = selectd.node.id;
            text = selectd.node.text;
            if (dic_id) {
                $.ajax({
                    url: '${rc.contextPath}/dictionary/read-category/' + dic_id,
                    type: 'GET',
                    beforeSend: function () {
                        $(".input-icon > i").removeClass("fa-check fa-warning").attr("data-original-title", "");
                        $(".form-group").removeClass("has-error has-success");
                        $(".form-body .alert-danger").css("display", "none");
                    },
                    success: function (msg) {
                        var data = msg.dictionary;
                        $('input[name=id]').val(data.id);
                        $('input[name=dicPid]').val(data.dicPid);
                        $('input[name=dicKey]').val(data.dicKey);
                        $('input[name=dicValue]').val(data.dicValue);
                        $('input[name=sort]').val(data.sort);
                        $('input[name=desc]').val(data.desc);
                        $('input[name=dictionaryType]').val(data.dictionaryType);
                        $('input[name=path]').val(data.path);
                        $("select[name=isDeleted] option[value='" + data.isDelete + "']").prop("selected", true);
                        $("select[name=isDeleted] option[value='" + data.isDelete + "']").attr("selected", "selected");
                        $("select[name=type] option[value='" + data.type + "']").prop("selected", true);
                        $("select[name=type] option[value='" + data.type + "']").attr("selected", "selected");
                        var value = "";
                        if (data.type == 'ITEM') {
                            for (var i = 0; i < msg.category.length; i++) {
                                var obj = msg.category[i];
                                value = value + "<option value=" + obj.id + ">" + obj.dicValue + "</option>"
                            }
                            $('.btn-children').attr('disabled', "disabled");

                        } else {
                            $('.btn-children').enable();
                            $('.btn-parent').enable();
                        }
                        document.getElementById("levelId").innerHTML = value;
                        level_id = data.dicValue;
                        $("select[name=invalid] option[value='" + data.isDeleted + "']").attr("selected", "selected");
                        $("select[name=levelId] option[value='" + data.dicPid + "']").attr("selected", "selected");
                        $('#dicForm :input').each(function (a) {
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
                dicValue: {
                    minlength: 2,
                    maxlength: 30,
                    required: true,
                    remote: {
                        type: "GET",
                        contentType: "application/json;charset=UTF-8",
                        url: "${rc.contextPath}/dictionary/checkExist",//请求地址
                        //传递的参数,不写默认是当前校验的值
                        data: {
                            //多参数传递,每个值需要用function返回,
                            "dicValue": function () {
                                return $(" input[ name='dicValue' ] ").val();
                            },
                            "dicPid": function () {
                                var level = $("select[name=levelId] option:selected").val();
                                if (!level)
                                    level = 0
                                return level;
                            },
                            "status": function () {
                                return status;
                            },
                            "id": function () {
                                var id = $("input[name=id]").val();
                                if (!id)
                                    id = 0
                                return id;
                            }
                        }
                    }
                },
                dicKey: {
                    required: true
                },
                dictionaryType: {
                    required: true
                },
                active: {
                    required: true
                }
            },
            messages: {
                dicValue: {
                    remote: "当前层级下存在同名项"
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
                error.hide();
                form.submit();
            }
        });
        $('.btn-refresh').click(function () {
            Metronic.startPageLoading();
            $.ajax({
                url: '${rc.contextPath}/dictionary/refresh',
                type: 'GET',
                traditional: true,
                success: function (data) {
                    Metronic.stopPageLoading();
                    //bootbox.alert("刷新数据字典成功");
                    bootbox.alert({buttons: {ok: {label: '确认'}}, message: "刷新数据字典成功"});
                }
            });
        });
        $('.btn-parent').click(function () {
            form.resetForm();
            $('input[name=id]').val("");
            $('input[name=dicPid]').val(0);
            $("select[name=type] option[value='CATEGORY']").prop("selected", true).attr("selected", "selected");
            $('#dicForm :input').each(function (a) {
                $(this).enable();
                $(this).attr("readonly", false);
            });
            $('.btn-edit').attr('disabled', "disabled");
            $('.btn-delete').attr('disabled', "disabled");
            $('select[name=levelId]').val('');
            $('select[name=levelId]').attr('disabled', "disabled");
            status = "create";
        });
        $('.btn-children').click(function () {
            form.resetForm();
            $.ajax({
                url: '${rc.contextPath}/dictionary/categoryList',
                type: 'POST',
                success: function (data) {
                    var value = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        value = value + "<option value=" + obj.id + ">" + obj.dicValue + "</option>"
                    }
                    document.getElementById("levelId").innerHTML = value;
                    $("select[name=levelId] option[value='" + dic_id + "']").prop("selected", true).attr("selected", "selected");
                }
            });
            $('input[name=id]').val("");
            $('input[name=dicPid]').val(dic_id);
            // typeChange();
            $("select[name=type] option[value='ITEM']").prop("selected", true).attr("selected", "selected");

            $('#dicForm :input').each(function (a) {
                $(this).enable();
                $(this).attr("readonly", false);
            });
            $('.btn-edit').attr('disabled', "disabled");
            $('.btn-delete').attr('disabled', "disabled");
            status = "create";
        });
        $('.btn-edit').click(function () {
            $('#dicForm :input').each(function (a) {
                $(this).enable();
                $(this).attr("readonly", false);
                status = "update";
            });
        });
        $('.btn-cancel').click(function () {
            form.resetForm();
            $('#dicForm :input').each(function (a) {
                $(this).attr('disabled', "disabled");
                $(this).attr("readonly", "true");
            });
            $('div.form-group ').removeClass('has-error');
            $('div.alert-danger').css('display', 'none');
        });
        $('.btn-delete').click(function () {
            bootbox.dialog({
                message: "确认要删除此数据字典吗？",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/dictionary/delete/' + dic_id,
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

        function typeChange() {
            if ($('select[name=type]').val() == 'ITEM') {
                $('#dicForm :input').each(function (a) {
                    $(this).enable();
                    $(this).attr("readonly", false);
                });
                $.ajax({
                    url: '${rc.contextPath}/dictionary/categoryList',
                    type: 'POST',
                    success: function (data) {
                        var value = "";
                        for (var i = 0; i < data.length; i++) {
                            var obj = data[i];
                            value = value + "<option value=" + obj.id + ">" + obj.dicValue + "</option>"
                        }
                        document.getElementById("levelId").innerHTML = value;
                    }
                });
            } else {
                $('select[name=levelId]').val('');
                $('select[name=levelId]').attr('disabled', "disabled");
            }
        }

        function pidChanged() {
            $('input[name=dicPid]').val($("select[name=levelId] option:selected").val());
            $('input[name=dicValue]').val('');
        }

    </script>
</content>
</html>
