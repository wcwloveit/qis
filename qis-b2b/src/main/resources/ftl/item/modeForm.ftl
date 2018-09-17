<html>
<head>
    <title>产品<#if action?? && action == 'create'>新增</#if><#if action?? && action == 'update'>编辑</#if></title>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/select2/select2.css"/>
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
                <a href="javascript:void(0);">产品管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="javascript:void(0);">配置列表</a>
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
                    <i class="fa fa-gift"></i>产品：${(product.modelName)!}  配置<#if action?? && action == 'create'>新增</#if><#if action?? && action == 'update'>编辑</#if>
                </div>
                 <div class="actions">
                	<a href="javascript:history.back(-1)" class="btn grey">返回</a>
                </div>

            </div>
            <div class="portlet-body form">
                <form id="myForm" action="${rc.contextPath}/itemMode/${action}-${(product.id)?if_exists}" class="form-horizontal" method="POST">
                    <div class="form-body">
                        <#--<h3 class="form-section">基本信息</h3>-->
                        <input type="hidden" class="form-control"  name="id" value="${mode.id?if_exists}" >
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">配置编码<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${mode.modeNo?if_exists}"
                                               name="modeNo" placeholder="配置编码" id="modeNo" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">配置名称<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${mode.modeName?if_exists}"
                                               name="modeName" placeholder="配置名称" id="modeName" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">配置别名<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${mode.bname?if_exists}"
                                               name="bname" placeholder="配置别名" id="bname" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">组织编号<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${mode.orgCode?if_exists}"
                                               name="orgCode" placeholder="组织编号" id="orgCode" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">价格<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${mode.price?if_exists}"
                                               name="price" placeholder="价格" id="price" >
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
    <script src="${rc.contextPath}/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/lang/summernote-zh-CN.js" type="text/javascript"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonUtil.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript">
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
