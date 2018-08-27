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
                <a href="#">数据字典</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="${rc.contextPath}/ftl/dictionary/list">数据字典</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="${rc.contextPath}/baseDataTypes/index">数据字典列表</a>
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
                <div class="caption"><i class="fa fa-sitemap"></i>数据字典树</div>
            </div>
            <div class="portlet-body" id="dicTree"></div>
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
                        <form class="form-horizontal" action="${rc.contextPath}/baseData/save" method="POST"
                              id="dicForm">
                            <input type="hidden" name="id"/>
                            <input type="hidden" name="pid" value="0"/>
                            <input type="hidden" name="path"/>
                            <#--<input type="hidden" name="bid" value="${id?if_exists}"/>-->

                            <div class="form-body">
                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button>
                                    请检查后再提交
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3 nowrap">名称 <span
                                                    class="required">*</span></label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="name"
                                                           placeholder="请录入数据名称" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">值 <span
                                                    class="required">*</span></label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="dataValue"
                                                           placeholder="请录入数据值" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">编码 <span
                                                    class="required">*</span></label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="code"
                                                           placeholder="请录入数据编码" readonly="true"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3 nowrap">描述 <span
                                                    class="required">*</span></label>
                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="descr" readonly="true" placeholder="请输入数据描述"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">数据类型</label>
                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                <#--<input type="text" class="form-control" name="parentBaseDataId"-->
                                                <#--placeholder="请录入上级数据字典编号" readonly="true"/>-->
                                                    <select id="baseDataTypeId" name="baseDataTypeId" class="form-control" placeholder="请录入上级数据字典编号"
                                                            readonly="true">

                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">上级</label>

                                            <div class="col-md-9">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <#--<input type="text" class="form-control" name="parentBaseDataId"-->
                                                           <#--placeholder="请录入上级数据字典编号" readonly="true"/>-->
                                                    <select id="parentBaseDataId" name="parentBaseDataId" class="form-control"
                                                            disabled="disabled">

                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-md-6">

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
<script src="${rc.contextPath}/assets/global/plugins/jsTree/dist/jsTree.js" type="text/javascript"></script>
    <#--<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>-->
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
                    'url': "${rc.contextPath}/baseData/list/${id?if_exists}",
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
                    url: '${rc.contextPath}/baseData/read-category/' + dic_id,
                    type: 'GET',
                    beforeSend: function () {
                        $(".input-icon > i").removeClass("fa-check fa-warning").attr("data-original-title", "");
                        $(".form-group").removeClass("has-error has-success");
                        $(".form-body .alert-danger").css("display", "none");
                    },
                    success: function (msg) {
                        var data = msg.baseData;
                        $('input[name=id]').val(data.id);
                        $('input[name=pid]').val(data.parentBaseDataId);
                        $('input[name=name]').val(data.name);
                        $('input[name=dataValue]').val(data.dataValue);
                        $('input[name=descr]').val(data.descr);
                        $('input[name=code]').val(data.code);
                        $("select[name=isDeleted] option[value='" + data.isDelete + "']").prop("selected", true);
                        $("select[name=isDeleted] option[value='" + data.isDelete + "']").attr("selected", "selected");
                        var value = "";
                        if (data.parentBaseDataId != '') {
                            for (var i = 0; i < msg.category.length; i++) {
                                var obj = msg.category[i];
                                value = value + "<option value=" + obj.id + ">" + obj.name + "</option>";
                            }
                            $('.btn-children').attr('disabled', "disabled");

                        } else {

                            $('.btn-children').enable();
                            $('.btn-parent').enable();
                        }
                        var types="";
                        for (var i = 0; i < msg.types.length; i++) {
                            var type = msg.types[i];
                            types = types + "<option value=" + type.id + ">" + type.name + "</option>"
                        }
                        document.getElementById("parentBaseDataId").innerHTML = value;
                        document.getElementById("baseDataTypeId").innerHTML = types;
                        $("select[name=isDeleted] option[value='" + data.isDeleted + "']").attr("selected", "selected");
                        $("select[name=parentBaseDataId] option[value='" + data.parentBaseDataId + "']").attr("selected", "selected");
                        $("select[name=baseDataTypeId] option[value='" + data.baseDataTypeId + "']").attr("selected", "selected");
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
                name: {
                    minlength: 2,
                    maxlength: 30,
                    required: true,
                    remote: {
                        type: "GET",
                        contentType: "application/json;charset=UTF-8",
                        url: "${rc.contextPath}/baseData/checkExist",//请求地址
                        //传递的参数,不写默认是当前校验的值
                        data: {
                            //多参数传递,每个值需要用function返回,
                            "name": function () {
                                return $(" input[ name='name' ] ").val();
                            },
                            "pid": function () {
                                var parentBaseDataId = $("select[name=parentBaseDataId] option:selected").val();
                                if (!parentBaseDataId)
                                    parentBaseDataId = 0;
                                return parentBaseDataId;
                            },
                            "status": function () {
                                return status;
                            },
                            "id": function () {
                                var id = $("input[name=id]").val();
                                if (!id)
                                    id = 0
                                return id;
                            },
                            "bid": function () {
                                var baseDataTypeId = $("select[name=baseDataTypeId] option:selected").val();
                                if (!baseDataTypeId)
                                    baseDataTypeId = 0;
                                return baseDataTypeId;

                            }
                        }
                    }
                },
                dataValue: {
                    required: true
                },
                code: {
                    required: true
                },
                descr: {
                    required: true
                }
            },
            messages: {
                name: {
                    remote: "当前层级下存在同名项",
                    required: "名称不能为空"
                },
                dataValue: {
                    required: "值不能为空"
                },
                code: {
                    required: "编码不能为空"
                },
                descr: {
                    required: "描述不能为空"
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
                $('select[name=baseDataTypeId]').enable();
                $('select[name=parentBaseDataId]').enable();
                error.hide();
                form.submit();
            }
        });

        $('.btn-parent').click(function () {
            form.resetForm();
            $('input[name=id]').val("");
            $('input[name=name]').val("");
            $('input[name=dataValue]').val("");
            $('input[name=code]').val("");
            $('input[name=descr]').val("");
            $('select[name=baseDataTypeId]').val("");
            $('select[name=parentBaseDataId]').val("");
            $.ajax({
                url: '${rc.contextPath}/baseData/typeList',
                type: 'POST',
                success: function (data) {
                    var value = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        value = value + "<option value=" + obj.id + ">" + obj.name + "</option>"
                    }
                    document.getElementById("baseDataTypeId").innerHTML = value;
                    $("select[name=baseDataTypeId] option[value='" + ${id?if_exists} + "']").prop("selected", true).attr("selected", "selected");
                }
            });

            $('#dicForm :input').each(function (a) {
                $(this).enable();
                $(this).attr("readonly", false);
            });
            $('select[name=parentBaseDataId]').attr('disabled', "disabled");
            $('.btn-edit').attr('disabled', "disabled");
            $('.btn-delete').attr('disabled', "disabled");

            status = "create";
        });
        $('.btn-children').click(function () {
            form.resetForm();
            $.ajax({
                url: '${rc.contextPath}/baseData/read-category/'+dic_id,
                type: 'GET',
                success: function (msg) {
                    var data=msg.baseData;
                    var value = "";
                    for (var i = 0; i < msg.category.length; i++) {
                        var obj = msg.category[i];
                        value = value + "<option value=" + obj.id + ">" + obj.name + "</option>";
                    }
                    var types="";
                    for (var i = 0; i < msg.types.length; i++) {
                        var type = msg.types[i];
                        types = types + "<option value=" + type.id + ">" + type.name + "</option>"
                    }
                    document.getElementById("parentBaseDataId").innerHTML = value;
                    document.getElementById("baseDataTypeId").innerHTML = types;
                    $("select[name=parentBaseDataId] option[value='" + data.id + "']").attr("selected", "selected");
                    $("select[name=baseDataTypeId] option[value='" + data.baseDataTypeId + "']").attr("selected", "selected");
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
            $('select[name=baseDataTypeId]').attr('disabled', "disabled");
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
            $('select[name=baseDataTypeId]').attr('disabled', "disabled");
            $('select[name=parentBaseDataId]').attr('disabled', "disabled");
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
                                url: '${rc.contextPath}/baseData/delete/' + dic_id,
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
