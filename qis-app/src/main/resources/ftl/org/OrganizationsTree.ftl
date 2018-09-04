<html>
<head>
    <title>组织树</title>
    <link href="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"
          rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
          rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
          type="text/css"/>


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
                <a href="${rc.contextPath}/dictionary/list">组织设置</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">组织树列表</a>
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
                <div class="caption"><i class="fa fa-sitemap"></i>组织树</div>
            </div>
            <div class="portlet-body" id="orgTree"></div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="col-md-12">
            <div class="portlet box green-haze">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-square-o"></i>组织信息表单
                    </div>

                    <div class="actions" id="qisOrgInfo">
                        <div class="btn-group">
                            <button type="button" class="btn greeen btn-new-org">
                                <span class="hidden-480 span-org">新增同级分部</span>
                            </button>
                            <button type="button" class="btn blue btn-edit">
                                <span class="hidden-480">编辑</span>
                            </button>
                            <button id="editOrg" type="button" class="btn yellow btn-seal" onclick="sealOrg();">
                                <span class="hidden-480">封存</span>
                            </button>
                        </div>
                    </div>

                    <div class="actions" id="qisParentOrg" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn green btn-org-parent">
                                <span class="hidden-480">新增同级分部</span>
                            </button>
                            <button type="button" class="btn blue btn-org-child">
                                <span class="hidden-480">新增下级分部</span>
                            </button>
                            <button type="button" class="btn red btn-delete">
                                <span class="hidden-480">批量删除</span>
                            </button>
                        </div>
                    </div>

                    <div class="actions" id="qisParentDept" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn green btn-dept-children">
                                <span class="hidden-480">新增同级部门</span>
                            </button>
                            <button type="button" class="btn blue btn-dept-children">
                                <span class="hidden-480">新增下级部门</span>
                            </button>
                            <button type="button" class="btn red btn-delete">
                                <span class="hidden-480">批量删除</span>
                            </button>
                        </div>
                    </div>

                    <div class="actions" id="qisNewUser" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn green btn-children">
                                <span class="hidden-480">新建人员</span>
                            </button>
                        </div>
                    </div>

                </div>

                <div class="portlet-body form">
                    <ul class="nav nav-tabs" role="tablist" id="tabs">
                        <li class="active menuli" id="limsg"><a href="#tab0" role="tab" data-toggle="tab"
                                                                onclick="getInfoInter();">详细信息</a>
                        </li>
                        <li class="menuli" id="childOrg"><a href="#tab1" role="tab" data-toggle="tab"
                                                            onclick="getOrgInter();">下级分部</a>
                        </li>
                        <li class="menuli" id="childDept"><a href="#tab2" role="tab" data-toggle="tab"
                                                             onclick="getDeptInter();">下级部门</a></li>
                        <li class="menuli" style="display: none" id="liUsers"><a href="#tab3" role="tab"
                                                                                 data-toggle="tab"
                                                                                 onclick="getUsersInter();">人力资源</a>
                        </li>
                    </ul>


                    <form class="form-horizontal" action="${rc.contextPath}/organization/save" method="POST"
                          id="orgForm">
                        <input type="hidden" name="id"/>
                        <input type="hidden" name="supId" value="0"/>
                        <input type="hidden" name="type" value="0"/>

                        <div class="alert alert-danger display-hide">
                            <button class="close" data-close="alert"></button>
                            请检查后再提交
                        </div>

                        <div class="table-container">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr role="row" class="heading">
                                    <th colspan="2">基本信息</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td width="30%"><label class="control-label">简称<span
                                            class="required">*</span></label>
                                    </td>
                                    <td width="70%"><input type="text" name="name" id="name"
                                                           style="width: 400px;" class="form-control"
                                                           placeholder="请录入组织简称" readonly="true"></td>
                                </tr>
                                <tr>
                                    <td width="30%"><label class="control-label">全称<span
                                            class="required">*</span></label>
                                    </td>
                                    <td width="70%"><input type="text" name="descr" id="descr"
                                                           style="width: 400px;" class="form-control"
                                                           placeholder="请录入组织全称"
                                                           readonly="true"></td>
                                </tr>
                                <tr>
                                    <td width="30%"><label class="control-label">所属分部<span
                                            class="required">*</span></label>
                                    </td>
                                    <td width="70%"><input type="text" name="parentOrg" id="parentOrg"
                                                           class="form-control"
                                                           style="width: 400px;" readonly="true"></td>
                                </tr>
                                <tr style="display:none" id="parentDepttr">
                                    <td width="30%"><label class="control-label">上级部门<span
                                            class="required">*</span></label>
                                    </td>
                                    <td width="70%"><input type="text" name="parentDept" id="parentDept"
                                                           class="form-control"
                                                           style="width: 400px;" readonly="true"></td>
                                </tr>
                                <tr>
                                    <td width="30%"><label class="control-label">OA编号</label>
                                    </td>
                                    <td width="70%"><input type="text" name="oaNo" id="oaNo"
                                                           style="width: 400px;" class="form-control"
                                                           readonly="true"></td>
                                </tr>
                                <tr>
                                    <td width="30%"><label class="control-label">U9编号</label>
                                    </td>
                                    <td width="70%"><input type="text" name="u9No" id="u9No"
                                                           style="width: 400px;" class="form-control"
                                                           readonly="true"></td>
                                </tr>
                                <tr>
                                    <td width="30%"><label class="control-label">QIS编号</label>
                                    </td>
                                    <td width="70%"><input type="text" name="code" id="code"
                                                           style="width: 400px;" class="form-control"
                                                           readonly="true"></td>
                                </tr>
                                </tbody>
                            </table>

                            <table class="table table-striped table-bordered table-hover table-save" style="display: none">
                                <tbody>

                                <tr>
                                    <td colspan="4" align="center">
                                        <button type="submit" class="btn green" disabled="disabled">保存</button>
                                        <button type="button" class="btn default btn-cancel"
                                                disabled="disabled">取消
                                        </button>
                                    </td>
                                    <input type="hidden" name="dates" id="dates">
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </form>

                    <div class="row" style="display: none;" id="getOrgInfo">
                        <div class="col-md-12">
                            <div class="portlet box green-haze">
                                <div class="portlet-body" style="max-height:940px;overflow-y:auto;overflow-x:hidden">
                                    <table class="table table-striped table-hover table-bordered" id="showOrgList">
                                        <thead>
                                        <tr role="row" class="heading">
                                            <th>序号</th>

                                            <th>简称</th>
                                            <th>全称</th>
                                            <th>编号</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <tr role="row" class="filter">
                                            <td></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_name"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_descr"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_LIKE_code"></td>
                                            <td>
                                                <select name="search_LIKE_type"
                                                        class="form-control  form-filter input-sm">
                                                    <option selected="selected" value>请选择</option>
                                                    <option value="ENABLE">正常</option>
                                                    <option value="DISABLE">封存</option>
                                                </select>
                                            </td>
                                            <td style="white-space: nowrap">
                                                <button class="btn btn-sm yellow filter-submit margin-bottom"><i
                                                        class="fa fa-search"></i>搜索
                                                </button>
                                                <button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>重置
                                                </button>
                                            </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="display: none;" id="getUserInfo">
                        <div class="col-md-12">
                            <div class="portlet box green-haze">
                                <div class="portlet-body" style="max-height:940px;overflow-y:auto;overflow-x:hidden">
                                    <table class="table table-striped table-hover table-bordered" id="showUserTable">
                                        <thead>
                                        <tr role="row" class="heading">
                                            <th>序号</th>
                                            <th>姓名</th>
                                            <th>编号</th>
                                            <th>状态</th>
                                            <th>登陆名称</th>
                                            <th>手机号</th>
                                            <th>操作</th>
                                        </tr>
                                        <tr role="row" class="filter">
                                            <td></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_User_name"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_User_workcode"></td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_User_code"></td>
                                            <td>
                                                <select name="search_User_status"
                                                        class="form-control  form-filter input-sm">
                                                    <option selected="selected" value>请选择</option>
                                                    <option value="ENABLE">正常</option>
                                                    <option value="DISABLE">封存</option>
                                                </select>
                                            </td>
                                            <td><input type="text" class="form-control form-filter input-sm"
                                                       name="search_User_mobile"></td>
                                            <td style="white-space: nowrap">
                                                <button class="btn btn-sm yellow filter-submit margin-bottom"><i
                                                        class="fa fa-search"></i>搜索
                                                </button>
                                                <button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>重置
                                                </button>
                                            </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="show_createOrg" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
                            <h4 class="modal-title orgTitle">新增分部</h4>
                        </div>
                        <div class="portlet-body form">
                            <form class="form-horizontal" action="${rc.contextPath}/organization/save" method="POST"
                                  id="createOrgForm">
                                <input type="hidden" name="id"/>
                                <input type="hidden" name="supId" value="0"/>
                                <input type="hidden" name="type" value="0"/>
                                <input type="hidden" name="keepOrg">
                                <input type="hidden" name="keepParentOrg">

                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button>
                                    请检查后再提交
                                </div>

                                <div class="table-container">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr role="row" class="heading">
                                            <th colspan="2">基本信息</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td width="30%"><label class="control-label">简称<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="name"
                                                                   style="width: 400px;"
                                                                   placeholder="请录入组织简称"></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">全称<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="descr"
                                                                   style="width: 400px;"
                                                                   placeholder="请录入组织全称"></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">所属分部<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="parentOrg"
                                                                   style="width: 400px;"></td>
                                        </tr>
                                        <tr style="display:none" class="parentDepttr">
                                            <td width="30%"><label class="control-label">上级部门<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="parentDept"

                                                                   style="width: 400px;"></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">OA编号</label>
                                            </td>
                                            <td width="70%"><input type="text" name="oaNo"
                                                                   style="width: 400px;"
                                                                   ></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">U9编号</label>
                                            </td>
                                            <td width="70%"><input type="text" name="u9No"
                                                                   style="width: 400px;"></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">QIS编号</label>
                                            </td>
                                            <td width="70%"><input type="text" name="code"
                                                                   style="width: 400px;"></td>
                                        </tr>
                                        </tbody>
                                    </table>

                                    <table class="table table-striped table-bordered table-hover table-save">
                                        <tbody>

                                        <tr>
                                            <td colspan="4" align="center">
                                                <button type="submit" class="btn green">保存</button>
                                            </td>
                                            <input type="hidden" name="dates" id="dates">
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="show_createUsers" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
                            <h4 class="modal-title usersTitle">新增人员</h4>
                        </div>
                        <div class="portlet-body form">
                            <form class="form-horizontal" action="${rc.contextPath}/organization/save" method="POST"
                                  id="createOrgForm">
                                <input type="hidden" name="id"/>

                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button>
                                    请检查后再提交
                                </div>

                                <div class="table-container">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr role="row" class="heading">
                                            <th colspan="2">人员信息</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td width="30%"><label class="control-label">编号<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="code"
                                                                   style="width: 400px;"></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">姓名<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="name"
                                                                   style="width: 400px;"
                                                                   placeholder="请录入组织全称"></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">部门<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="parentOrg"
                                                                   style="width: 400px;"></td>
                                        </tr>
                                        <tr style="display:none" class="parentDepttr">
                                            <td width="30%"><label class="control-label">移动电话<span
                                                    class="required">*</span></label>
                                            </td>
                                            <td width="70%"><input type="text" name="parentDept"

                                                                   style="width: 400px;"></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label class="control-label">密码</label>
                                            </td>
                                            <td width="70%"><input type="text" name="oaNo"
                                                                   style="width: 400px;"
                                            ></td>
                                        </tr>
                                        </tbody>
                                    </table>

                                    <table class="table table-striped table-bordered table-hover table-save">
                                        <tbody>

                                        <tr>
                                            <td colspan="4" align="center">
                                                <button type="submit" class="btn green">保存</button>
                                            </td>
                                            <input type="hidden" name="dates" id="dates">
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>

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
    <script src="${rc.contextPath}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/lib/jquery.form.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
            type="text/javascript">
    </script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/additional-methods.min.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-validation/js/localization/messages_zh.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/jstree/dist/jstree.js" type="text/javascript"></script>

    <script type="text/javascript">
        var oid = 0, text, type;
        $("#orgTree").jstree({
            "core": {
                "animation": 0,
                "themes": {
                    theme: "classic",
                    "dots": true,
                    "icons": true
                },
                "check_callback": true,
                "data": {
                    "type": "POST",
                    "url": "${rc.contextPath}/organization/list",
                    "data": function (node) {
                        if (node.text) {
                            return {"supId": node.id};
                        }
                        return {};
                    }
                }
            },
            "types": {
                "default": {
                    "valid_children": ["default", "file"]
                }
            },
            "plugins": ["types", "wholerow"]
        }).on("select_node.jstree", function (node, selectd) {
            oid = selectd.node.id;
            text = selectd.node.text;
            $(".menuli").removeClass("active");
            $('#limsg').addClass("active");
            getInfoInter();
            if (oid.indexOf("o") > 0) {
                $('.span-org').html('新增同级分部');
                $('#liUsers').hide();
                $('#childOrg').show();
                $('.parentDepttr').hide();

            } else if (oid.indexOf("d") > 0) {
                $('.span-org').html('新增同级部门');
                $('#liUsers').show();
                $('#childOrg').hide();
                $('.parentDepttr').show();
            }
            console.log(oid + "," + text);
            if (oid) {
                fillMsg(oid);
            }
        });

        function fillMsg(oid){
            $.ajax({
                url: '${rc.contextPath}/organization/read/' + oid,
                type: 'GET',
                success: function (data) {
                    $('input[name=supId]').val(oid);
                    $('input[name=name]').val(data.name);
                    $('input[name=descr]').val(data.descr);
                    $('input[name=parentOrg]').val(data.parentOrgName);
                    $('input[name=parentDept]').val(data.parentDeptName);
                    $('input[name=oaNo]').val(data.oaNo);
                    $('input[name=u9No]').val(data.u9No);
                    $('input[name=code]').val(data.code);
                    $('input[name=keepOrg]').val(data.name);
                    $('input[name=keepParentOrg]').val(data.parentOrgName);
                }
            });
        }

        /**
         * 封存
         * @param orderNumber
         */
        function sealOrg(){
            var oid = $('input[name=supId]').val();
            var name=$('input[name=name]').val();
            commonAccept({"id":oid},'确定要封存'+name+'吗？请确认该组织下无人员，否则无法封存！','封存组织','${rc.contextPath}/organization/sealOrg',1);
        }

        /**
         * 判断是否封存成功
         * @param orderNumber
         */
        function commonAccept(data,message,title,url,tip){
            bootbox.dialog({
                message:message,
                title:title,
                buttons:{
                    main:{
                        label:"取消",
                        className:"gray",
                        callback:function(){
                            $(this).hide();
                        }
                    },
                    success:{
                        label:"确定",
                        className:"green",
                        callback:function(){
                            $.ajax({
                                url:url,
                                type:'POST',
                                data:data,
                                dataType:"json",
                                traditional:true,
                                success:function(msg){
                                    if(msg&&msg.stat){
                                        bootbox.alert('已封存');

                                        location.href='${rc.contextPath}/organization/index';
                                    }else{
                                        bootbox.alert(msg.msg);
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }



        /**
         * 清空所有框的action
         * */
        function makeEmpty() {
            $('#qisOrgInfo').hide();
            $('#qisParentOrg').hide();
            $('#qisParentDept').hide();
            $('#qisNewUser').hide();
            $('#orgForm').hide();
            $('#getOrgInfo').hide();
            $('#getUserInfo').hide();

        }

        $('.btn-edit').click(function(){
            $('#orgForm :input').each(function(a){
                $(this).enable();
                $(this).attr("readonly",false);
            });
            $('.table-save').show();
            $('#qisOrgInfo').hide();
            $('input[name=type]').val('sib');
        });

        //新增同级分部
        $('.btn-org-parent').click(function(){
            var keepname=$('input[name=keepParentOrg]').val();

            cleanInfo();
            $('input[name=id]').val(1);
            $('input[name=parentOrg]').val(keepname);
            $('#show_createOrg').modal('show');
            $('input[name=type]').val('sib');
        });

        //新增下级分部
        $('.btn-org-child').click(function(){
            var keepname=$('input[name=keepOrg]').val();
            cleanInfo();

            $('input[name=id]').val(1);
            $('input[name=parentOrg]').val(keepname);
            $('#show_createOrg').modal('show');
            $('input[name=type]').val('child');
        });



        $('.btn-new-org').click(function(){
            cleanInfo();
            $('input[name=id]').val(1);
            $('#orgForm :input').each(function(a){
                $(this).enable();
                $(this).attr("readonly",false);
            });

            $('.table-save').show();
            $('#qisOrgInfo').hide();
            $('input[name=type]').val('sib');
        });

        function cleanInfo(){
            $('input[name=code]').val('');
            $('input[name=name]').val('');
            $('input[name=descr]').val('');
            $('input[name=oaNo]').val('');
            $('input[name=u9No]').val('');
        }



        function getInfoInter() {
            makeEmpty();
            $('#qisOrgInfo').show();
            $('#orgForm').show();
            var oid=$('input[name=supId]').val();
            if(oid){
                fillMsg(oid);
            }
        }

        function sendOrgInfo(url, table) {
            var oid = $('input[name=supId]').val();
            var grid = new Datatable();
            var oTable = $('#' + table).dataTable();
            oTable.fnClearTable();
            oTable.fnDestroy();

            var showOrgList = $("#" + table);
            grid.init({
                        src: showOrgList,
                        onSuccess: function (grid) {
                            console.log(grid);
                        },
                        onError: function (grid) {
                        },
                        dataTable: {
                            "iDisplayLength": 10,
                            "bServerSide": true,
                            "sAjaxSource": "${rc.contextPath}/" + url + "/" + oid,
                            "aaSorting": [
                                [0, "desc"]
                            ],
                            "aoColumnDefs": [
                                {"bSortable": false, "aTargets": [0, 1, 2]}
                            ],//设置不排序得列
                            "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                            "aoColumns": [
                                {"sTitle": "序号", "mData": "id"},
                                {"sTitle": "简称", "mData": "orgName"},
                                {"sTitle": "全称", "mData": "orgDescr"},
                                {"sTitle": "编号", "mData": "orgCode"},
                                {
                                    "sTitle": "状态", "mRender": function (data, type, full) {
                                    if (full.orgStatus == 1) {
                                        return "封存";
                                    } else {
                                        return "正常";
                                    }

                                }
                                },
                                {
                                    "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, full) {
                                    var a = '<a href="${rc.contextPath}/order/order-info-detail/' + full.no + '" class="btn default btn-xs green" >编辑</a>';
                                    var b = '<a href="${rc.contextPath}/order/order-info-detail/' + full.no + '" class="btn red btn-xs green" >解封</a>';
                                    if (full.orgStatus == 1) {
                                        return b;
                                    } else {
                                        return a;
                                    }

                                }
                                }

                            ]
                        }
                    }
            )
            ;


        }

        //组织
        function getOrgInter() {
            makeEmpty();
            $('#qisParentOrg').show();
            $('#getOrgInfo').show();
            sendOrgInfo("organization/org-list", "showOrgList");
        }

        //部门
        function getDeptInter() {
            makeEmpty();
            $('#qisParentDept').show();
            $('#getOrgInfo').show();
            sendOrgInfo("department/dept-list", "showOrgList");
        }


        //人员
        function getUsersInter() {
            makeEmpty();
            $('#qisNewUser').show();
            $('#getUserInfo').show();
            var oid = $('input[name=supId]').val();
            var oTable = $('#showUserTable').dataTable();
            oTable.fnClearTable();
            oTable.fnDestroy();

            var grid = new Datatable();

            var showOrgList = $("#showUserTable");
            grid.init({
                        src: showOrgList,
                        onSuccess: function (grid) {
                            console.log(grid);
                        },
                        onError: function (grid) {
                        },
                        dataTable: {
                            "iDisplayLength": 10,
                            "bServerSide": true,
                            "sAjaxSource": "${rc.contextPath}/user/user-list/" + oid,
                            "aaSorting": [
                                [0, "desc"]
                            ],
                            "aoColumnDefs": [
                                {"bSortable": false, "aTargets": [0, 1, 2]}
                            ],//设置不排序得列
                            "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                            "aoColumns": [
                                {"sTitle": "序号", "mData": "id"},
                                {"sTitle": "姓名", "mData": "userName"},
                                {"sTitle": "编号", "mData": "userCode"},
                                {"sTitle": "登陆名称", "mData": "loginid"},
                                {
                                    "sTitle": "状态", "mRender": function (data, type, full) {
                                    if (full.userStatus == 1) {
                                        return "离职";
                                    } else {
                                        return "正常";
                                    }

                                }
                                },
                                {"sTitle": "手机号", "mData": "userMobile"},
                                {
                                    "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, full) {
                                    var a = '<a href="${rc.contextPath}/order/order-info-detail/' + full.no + '" class="btn default btn-xs green" >编辑</a>';
                                    var b = '<a href="${rc.contextPath}/order/order-info-detail/' + full.no + '" class="btn red btn-xs green" >解封</a>';
                                    if (full.orgStatus == 1) {
                                        return b;
                                    } else {
                                        return a;
                                    }
                                }
                                }

                            ]
                        }
                    }
            )
            ;


        }

        /**
         * 重置查询信息
         * @param btn
         * @param excludes
         */
        function resetSearch(btn) {
            $("#order_detail_table_search input[type='text']").val("");
            $("select.form-filter").each(function () {
                $(this).find("option").attr("selected", false);
            });
            search(btn, grid);
        }


    </script>
</content>
</html>
