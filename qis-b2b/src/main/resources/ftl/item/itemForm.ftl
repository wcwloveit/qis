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
                <a href="javascript:void(0);">产品列表</a>
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
                    <i class="fa fa-gift"></i>产品<#if action?? && action == 'create'>新增</#if><#if action?? && action == 'update'>编辑</#if>
                </div>
                 <div class="actions">
                	<a href="javascript:history.back(-1)" class="btn grey">返回</a>
                </div>

            </div>
            <div class="portlet-body form">
                <form id="myForm" action="${rc.contextPath}/item/${action}" class="form-horizontal" method="POST">
                    <div class="form-body">
                        <#--<h3 class="form-section">基本信息</h3>-->
                        <input type="hidden" class="form-control"  name="id" value="${itemDetail.id?if_exists}" >
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">产品编号<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${itemDetail.modelCode?if_exists}"
                                               name="modelCode" placeholder="产品编号" id="modelCode" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">产品名称<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${itemDetail.modelName?if_exists}"
                                               name="modelName" placeholder="产品名称" id="modelName" >
                                    </div>
                                </div>
<!--
                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">颜色编号<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${itemDetail.colorCode?if_exists}"
                                               name="colorCode" placeholder="颜色编号" id="colorCode" >
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">颜色名称<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${itemDetail.colorName?if_exists}"
                                               name="colorName" placeholder="颜色名称" id="colorName" >
                                    </div>
                                </div>


 -->
                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">组织编号<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${itemDetail.orgCode?if_exists}"
                                               name="orgCode" placeholder="组织编号" id="orgCode" >
                                    </div>
                                </div>
                                <#--<div class="form-group">-->
                                    <#--<label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">车型规格<span class="required">-->
											 <#--*-->
										<#--</span></label>-->
                                    <#--<div class="col-md-10">-->
                                        <#--<input type="text" required="true" class="form-control" value="${itemDetail.modelSpecification?if_exists}"-->
                                               <#--name="modelSpecification" placeholder="车型规格" id="modelSpecification" >-->
                                    <#--</div>-->
                                <#--</div>-->

                                <div class="form-group">
                                    <label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">初始价格<span class="required">
											 *
										</span></label>
                                    <div class="col-md-10">
                                        <input type="text" required="true" class="form-control" value="${itemDetail.initialPrice?if_exists}"
                                               name="initialPrice" placeholder="初始价格" id="initialPrice" >
                                    </div>
                                </div>


                                <#--<div class="form-group">-->
                                    <#--<label class="col-md-2 control-label" style="width: 87px;text-align: left;padding-right: 0">颜色配置<span class="required">-->
											 <#--*-->
										<#--</span></label>-->
                                    <#--<div class="col-md-10">-->
                                          <#--<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal">-->
                                              <#--<i class="fa fa-plus"></i>添加颜色-->
                                          <#--</a>-->
                                        <#--<table class="table table-bordered table-striped" id="exceptionalDate"></table>-->
                                    <#--</div>-->
                                    <#--<input type="hidden" name="colorDate" id="colorDate">-->
                                <#--</div>-->


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
