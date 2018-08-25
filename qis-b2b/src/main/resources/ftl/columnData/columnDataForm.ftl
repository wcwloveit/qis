<html>
<head>
<#--判断action 等于create是新增   等于update是编辑修改-->
    <title>角色新增<#if action?? && action == 'create'>新增</#if><#if action?? && action == 'update'>编辑</#if></title>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet"/>
    <#--<link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/select2/select2.css"/>-->
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/summernote.css">
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.css"/>
    <style>
        @media (min-width: 992px){
            .col-md-10 {
                width: 81.333333%;
            }
        }
    </style>
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="javascript:void(0);">数据</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="javascript:void(0);">角色类型</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                <#--判断action 等于create是新增   等于update是编辑修改-->
                    <i class="fa fa-gift"></i>产品<#if action?? && action == 'create'>新增</#if><#if action?? && action == 'update'>编辑</#if>
            </div>
                <div class="actions">
                    <a href="javascript:history.back(-1)" class="btn grey">返回</a>
                </div>

            </div>
            <div class="portlet-body form">
            <#--寻找是哪个Controller-->
                <form id="myForm" action="${rc.contextPath}/column/${action}" class="form-horizontal" method="POST">
                    <div class="form-body">
                    <#--<h3 class="form-section">基本信息</h3>-->
                        <input type="hidden" class="form-control"  name="id" value="${ColumnDatas.id?if_exists}" >
                        <div class="row">
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">
                                        名称<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${ColumnDatas.name?if_exists}" <#--Product-->
                                               name="name" placeholder="名称" id="name" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">
                                        编号<span class="required">
                                        *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text"  class="form-control" value="${ColumnDatas.code?if_exists}"
                                               name="code" placeholder="编号" id="code" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">描述<span class="descr">

									</span></label>
                                    <div class="col-md-10">
                                        <input type="text"  class="form-control" value="${ColumnDatas.descr?if_exists}"
                                               name="descr" placeholder="描述" id="descr" >
                                    </div>
                                </div>

                            </div>

                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">提交</button>
                            <button type="reset" class="btn red">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



</body>
<content tag="script">
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <#--<script src="${rc.contextPath}/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>-->
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/lang/summernote-zh-CN.js" type="text/javascript"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonUtil.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/jquery.validate.js"></script> <#-- jquery.validate.js 验证框架的地址，在mxm-web 里面 -->
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript">

        var form = $('#myForm'), error = $('.alert-danger', form);
        form.validate({
            errorElement: 'span',
            errorClass: 'help-block help-block-error',
            focusInvalid: true,
            messages: {
                name:{    //对应手机验证的 name id
                    // phone:"ddddd"  // 验证信息内容，如果没有填写就提示框架自带的提示信息
                },
                code:{
//                    remote:"未找到满足条件的料号"
                }
            },
            rules: {
                name:{
                    required: true  //表示是必填项
                },
                code:{
                    required: true  //表示是必填项
                },


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
                console.info("dadkad");
                form.submit();
            }
        });
        /**
         * 提示信息显示
         * */
        jQuery(function ($) {

        <#if action?? && action == 'update'>

        </#if>
        });



    </script>
</content>
</html>
