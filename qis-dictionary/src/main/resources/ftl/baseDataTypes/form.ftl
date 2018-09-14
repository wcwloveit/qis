<html>
<head>
    <title>数据字典管理</title>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/select2/css/select2.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/summernote.css">
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"/>
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"/>
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"/>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.css"/>

    <style type="text/css">
        a.delete {
            color: #ffffff;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${rc.contextPath}/">数据字典管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">数据字典详情</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet light portlet-fit portlet-form bordered" id="form_wizard_1">
            <div class="portlet-title">

                <div class="caption">
                    数据字典<#if action?? && action == 'create'>
                    新增</#if><#if action?? && action == 'update'>编辑</#if>
                </div>
                <div class="actions">
                    <a href="javascript:history.back(-1)" class="btn grey">返回</a>
                </div>
            </div>


            <div class="portlet-body">

                <form id="myForm" action="${rc.contextPath}/dictionary/${action?if_exists}"
                      method="POST" class="form-horizontal">
                    <input id="id" name="id" value="${baseDataTypes.id?if_exists}" style="display: none">
                    <div class="form-body">
                        <h3 class="form-section">名称</h3>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">数据字典名称<span class="required">*</span></label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="name" id="name" value="${baseDataTypes.name?if_exists}"/>
                            </div>
                        </div>

                        <h3 class="form-section">值</h3>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">数据字典值<span class="required">*</span></label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="typeValue" id="typeValue" value="${baseDataTypes.typeValue?if_exists}"/>
                            </div>
                        </div>

                        <h3 class="form-section">描述</h3>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">描述<span class="required">*</span></label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="descr" id="descr" value="${baseDataTypes.descr?if_exists}"/>
                            </div>
                        </div>
                        <#if baseDataTypes.id??>
                            <h3 class="form-section">子数据字典</h3>
                            <div class="form-group">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <div id="resourceTree"></div>
                            </div>
                        </div>
                        </#if>


                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn green">提交</button>
                                <button type="button" class="btn default"
                                        onclick="javascript:window.location.href='${rc.contextPath}/dictionary/baseDataTypes/index';">
                                    取消
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<content tag="script">
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/scripts/datatable.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/bootbox/bootbox.min.js"
            type="text/javascript"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonUtil.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/common.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonValidation.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/plupload/js/plupload.full.min.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/select2/js/select2.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/lib/jquery.form.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/jquery.validate.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/summernote.min.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/lang/summernote-zh-CN.js"
            type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/fancybox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
    <script type="text/javascript">
        var tree=$("#resourceTree").jstree({
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
                            'url': "${rc.contextPath}/dictionary/baseData/list/${baseDataTypes.id?if_exists}",
                },
            },
            "types": {
                "default": {
                    "valid_children": ["default", "file"]
                }
            },
            "plugins": ["types", "wholerow"]
        });
        jQuery(function ($) {

            var form = $('#myForm'), error = $('.alert-danger', form);

            form.validate({
                errorElement: 'span',
                errorClass: 'help-block help-block-error',
                focusInvalid: true,
                rules: {
                    name:{
                        required: true
                    },
                    typeValue:{
                        required: true
                    },
                    desc:{
                        required: true
                    }
                },
                messages: {
                    name:{
                        required: "不能为空"
                    },
                    typeValue:{
                        required: "不能为空"
                    },
                    desc:{
                        required: "不能为空"
                    }
                },
                invalidHandler: function (event, validator) {
                    error.show();
                    Metronic.scrollTo(error, -200);
                },
                highlight: function (element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                unhighlight: function (element) {
                    $(element).closest('.form-group').removeClass('has-error');
                },
                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                },
                submitHandler: function (form) {

                    form.submit();
                }
            })
        });

    </script>

</content>
</html>
