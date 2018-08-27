<html>
<head>
    <title>员工管理|新建员工</title>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-summernote/summernote.css" rel="stylesheet" type="text/css">
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <a href="#">系统设置</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li><a href="${rc.contextPath}">员工管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="portlet box green-haze">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-plus-square"></i><#if action?? && action == 'create'>新增</#if><#if action?? && action == 'update'>编辑</#if>员工
            </div>
        </div>
        <div class="portlet-body form">
        <form class="form-horizontal form-bordered" role="form" id="employeeForm" action="${rc.contextPath}/system/employee/${action}" method="post" enctype="multipart/form-data">
        <input name="id" type="hidden" value="${e.id}"/>
        <div class="form-body">
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            请检查后再提交
        </div>
        <div class="row">
        	<div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">姓名<span class="required">*</span></label>

                    <div class="col-md-9">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input name="userName" type="text" class="form-control" placeholder="请输入用户名" value="${u.lastname}">
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">登录名<span class="required">*</span></label>

                    <div class="col-md-9">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-key"></i></span>
                            <input check-type='required' name="loginName" type="text" class="form-control"
                                   placeholder="请输入登录名" value="${u.username}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">工号<span class="required">*</span></label>

                    <div class="col-md-9">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                            <input name="email" type="text" class="grayTips form-control"
                                   placeholder="工号" value="${u.workcode}">
                        </div>
                    </div>
                </div>
            </div>
            <#if action?? && action == 'create'>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">登录密码<span class="required">*</span></label>

                    <div class="col-md-9">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input check-type='required' name="plainPassword" type="password" class="form-control" placeholder="请输入登录密码" >
                        </div>
                    </div>
                </div>
            </div>
            </#if>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">状态<span class="required">*</span></label>

                    <div class="col-md-9">
                        <select name="status" class="form-control">
                            <option value="ENABLE">在职</option>
                            <option value="DISABLE">离职</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">手机</label>

                    <div class="col-md-9">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
                            <input type="text" class="form-control" isMobile="true" name="phone" minlength="11" value="${u.mobile}">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">组织<span class="required">&nbsp;</span></label>
                    <input type="text" name="orgName" style="width: 400px;"  class="form-control" readonly="true" value="${u.depName}">
                    <input type="text" name="orgId" style="width: 400px;"  type="hidden">

                    <div class="col-md-9" style="heigiht:300px">
                        <input type="hidden" name="empOrg">
                        <div id="orgTree"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">角色</label>

                    <div class="col-md-9">
                        <select name="roles" id="roles" multiple="multiple" class="select" style="width: 100%;">
                        
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-actions fluid">
            <div class="col-md-offset-5 ">
                <button type="submit" class="btn green">保存</button>
                <button type="button" class="btn default return" onclick="javascript:window.location.href='${rc.contextPath}/system/employee';">取消</button>
            </div>
        </div>
        </div>
        </form>
        </div>
    </div>
</div>
</body>
<content tag="script">
    <script src="${rc.contextPath}/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jstree/dist/jstree.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/lib/jquery.form.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-validate.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonUtil.js"></script>
    <script type="text/javascript">
        var oid,text;

        $("#orgTree").jstree({
            "core":{
                "animation":0,
                "themes":{
                    theme:"classic",
                    "dots":true,
                    "icons":true
                },
                "check_callback":true,
                "data": {
                    "type": "POST",
                    "url": "${rc.contextPath}/user/list",
                    "data": function (node) {
                        if (node.text) {
                            return {"supId": node.id};
                        }
                        return {};
                    }
                }
            },
            "types":{
                "default":{
                    "valid_children":["default","file"]
                }
            },
            "plugins":["types","wholerow"],
        }).on('select_node.jstree',function(node,selectd){
            oid = selectd.node.id;
            text = selectd.node.text;
            $('input[name=orgId]').val(oid);
            $('input[name=orgName]').val(text);
        });


        var form=$('#employeeForm');
        var error=$('.alert-danger',form);
        form.validate({
            errorElement:'span',
            errorClass:'help-block help-block-error',
            focusInvalid:true,
            messages:{
                loginName:{remote:"登录名已经存在"}
            },
            rules:{
                loginName:{
                    minlength:2,
                    maxlength:30,
                    required:true,
                    remote:'${rc.contextPath}/system/employee/check/${u.id}'
                },
                userName:{
                    required:true
                },
                email:{
                	required:true,
                    email:true
                },
                status:{
                    required:true
                },
                plainPassword:{
                	required:true,
                    maxlength:16
                }
            },
            invalidHandler:function(event,validator){
                error.show();
                Metronic.scrollTo(error,-200);
            },
            highlight:function(element){
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight:function(element){
                $(element).closest('.form-group').removeClass('has-error');
            },
            success:function(label){
                label.closest('.form-group').removeClass('has-error');
            },
            submitHandler:function(form){
                var empOrg=[];
                var empPos=[];
                $("#orgTable tbody tr").each(function(){
                    var json={};
                    var nRow=orgTable.fnGetData(this);
                    if(nRow){
                        json.id=nRow.id;
                        json.major=$(this).find("input.checkboxes.major").parents("span").hasClass("checked");
                        json.charge=$(this).find("input.checkboxes.charge").parents("span").hasClass("checked");
                        empOrg.push(json);
                    }
                });
                $("#posTable tbody tr").each(function(){
                    var json={};
                    var nRow=posTable.fnGetData(this);
                    if(nRow){
                        json.id=nRow.id;
                        json.major=$(this).find("input.checkboxes").parents("span").hasClass("checked");
                        empPos.push(json);
                    }
                });
                $('input[name="empOrg"]').val(JSON.stringify(empOrg));
                $('input[name="empPos"]').val(JSON.stringify(empPos));
                error.hide();
                form.submit();
            }
        });

        
        function inputTipText(){      
			$("input[class*=grayTips]").each(function(){ //所有样式名中含有grayTips的input     
				var oldVal=$(this).val();   //默认的提示性文本     
			   	$(this).css({"color":"#808080"}).focus(function(){   //灰色     
			    	if($(this).val()!=oldVal){
			    		$(this).css({"color":"#000"})
			    	}else{
			    		$(this).val("").css({"color":"#808080"})
			    	}     
			   	}).blur(function(){     
			    	if($(this).val()==""){
			    		$(this).val(oldVal).css({"color":"#808080"})
			    	}     
			   	}).keydown(function(){
			   		$(this).css({"color":"#000"})
			   	})     
			})     
		}     
			    
		<#if action =='create'>
		$(function(){     
			inputTipText();  //直接调用就OK了     
		})
		</#if>
    </script>
</content>
</html>