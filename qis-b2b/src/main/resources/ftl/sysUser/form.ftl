<html>
<head>
    <title>系统用户管理</title>
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
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
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
                <a href="${rc.contextPath}/">系统用户管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">系统用户详情</a>
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
                    系统用户<#if action?? && action == 'create'>
                    新增</#if><#if action?? && action == 'update'>编辑</#if>
                </div>
                <div class="actions">
                    <a href="javascript:history.back(-1)" class="btn grey">返回</a>
                </div>
            </div>


            <div class="portlet-body">

                <form id="myForm" action="${rc.contextPath}/sysUser/${action?if_exists}"
                      method="POST" class="form-horizontal">
                    <input id="id" name="id" value="${sysUser.id?if_exists}" style="display: none">
                    <div class="form-body">
                        <h3 class="form-section">账号信息</h3>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">账号<span class="required">*</span></label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="account" id="account" value="${sysUser.account?if_exists}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">密码<span class="required">*</span></label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="password" id="password" value="${sysUser.password?if_exists}"/>
                            </div>
                        </div>

                        <h3 class="form-section">个人信息</h3>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">名字</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="name" id="name" value="${sysUser.name?if_exists}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">生日</label>
                            <div class="col-md-4">
                                <div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
                                    <input type="text" name="birthday" id="birthday"  class="form-control" value="${birth?if_exists}" readonly name="datepicker">
                                    <span class="input-group-btn">
                                                            <button class="btn default" type="button">
                                                                <i class="fa fa-calendar"></i>
                                                            </button>
                                                        </span>
                                </div>
                                <span class="help-block"> 选择出生日期 </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">性别
                            </label>
                            <div class="col-md-4">
                                <div class="radio-list" data-error-container="#form_2_membership_error">
                                    <label>
                                        <input type="radio" name="sex" value="1" ${ (sysUser.sex == '1') ?string('checked','')}/> 男 </label>
                                    <label>
                                        <input type="radio" name="sex" value="2" ${ (sysUser.sex == '2') ?string('checked','')}/> 女 </label>
                                </div>
                                <div id="form_2_membership_error"> </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">邮箱</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="email" id="email" value="${sysUser.email?if_exists}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">电话</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="phone" id="phone" value="${sysUser.phone?if_exists}"/>
                            </div>
                        </div>

                        <h3 class="form-section">身份信息</h3>
                        <div class="form-group">
                            <label class="control-label col-md-3">角色
                                <span class="required"> * </span>
                            </label>
                            <div class="col-md-4">
                                <select class="form-control" name="roleid">
                                    <option value="">请选择一个角色</option>
                                    <#list roles as role>
                                    <option value="${role.id}" <#if role.id==sysUser.roleid>selected="selected"</#if>>${role.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3" for="inputWarning">部门</label>
                            <div class="col-md-4">
                                <select class="form-control" name="deptid">
                                    <option value="">请选择一个部门</option>
                                    <#list departments as department>
                                    <option value="${department.id}" <#if department.id==sysUser.deptid>selected="selected"</#if>>${department.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn green">提交</button>
                                <button type="button" class="btn default"
                                        onclick="javascript:window.location.href='${rc.contextPath}/sysUser/index';">
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
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
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
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            autoclose: true
        });
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
                    'url': "${rc.contextPath}/sysUser/list/${sysUser.id?if_exists}",
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
                    code:{
                        required: true
                    }
                },
                messages: {
                    name:{
                        required: "不能为空"
                    },
                    code:{
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
