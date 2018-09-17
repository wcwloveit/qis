<html>
<head>
    <title>众筹项目</title>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
    <style>
        /*body{*/
           /*background-color: #8a8a8a;    */
        /*}*/
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${rc.contextPath}/">产品管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">图片列表</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-cogs"></i>${(row)!}</div>
                <div class="actions">
                    <div class="btn-group">
                        <div class="fileinput fileinput-new">
                            <a href="javascript:void(0)" class="btn green">
                                <i class="fa fa-upload"></i>
                                <span class="hidden-480"  onclick="createImg();">新增图片</span>
                            </a>
                        <#if type==1>
                            <a href="javascript:void(0)" class="btn green">
                                <i class="fa fa-plus"></i>
                                <span class="hidden-480"  onclick="chooseImg();">选择图片</span>
                            </a>
                        </#if>
                            <a href="javascript:history.back(-1)" class="btn grey">返回</a>
                        </div>

                    </div>
                </div>
            </div>
            <div class="portlet-body">
            <#if message>
                <div class="alert  <#if message && success='true'>alert-success<#else>alert-danger</#if>">
                    <button data-dismiss="alert" class="close">×</button>
                ${(message)!}
                </div>
            </#if>
                <div id="tip"></div>
                <div class="table-container">
                    <div class="table-actions-wrapper">
                    </div>
                    <div id="data_table_search">
                    </div>
        </div>

        <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
        </table>
    </div>
</div>

</div>
</div>
</div>




<div class="modal fade" id="createModals"  tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">新增图片信息</h4>
            </div>
            <div class="modal-body">
                <form action="" id="files" name="files" method="post" enctype="multipart/form-data">
                    <input id="file" name="files" type="file" value="导入图片" /><br/>
                    <span class="btn default btn-xs red">请勿重复提交,请上传.png .gif .jpg .jpeg格式的图片</span><br/>
                    <br/>
                    <input class="btn default btn-xs green" type="submit" value="上  传  图  片" onClick="upImg();">

                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="chooseModals"  tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择产品图片</h4>
            </div>
            <div class="modal-body" >
                <iframe src="${rc.contextPath}/itemPic/chooseProduct-${(id)!}" id="chooseIframe" width="100%" height="100%"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="chooseBtn();">关闭</button>
                <#--<button type="button" class="btn btn-primary" onclick="choosePicImg();">选择</button>-->
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<input type="hidden" id="activityId" value="${(id)!}">
</body>
<content tag="script">
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/data-tables/DT_bootstrap.js"></script>
    <script type="text/javascript"
            src="${rc.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/scripts/datatable.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/plugins/bootbox/bootbox.min.js"
            type="text/javascript"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonUtil.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/common.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/assets/global/common/commonValidation.js"></script>
    <script src="${rc.contextPath}/assets/global/plugins/plupload/js/plupload.full.min.js"
            type="text/javascript"></script>
    <script src="${rc.contextPath}/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        $('#message').delay(3000).hide(0);
        /**
         * 数据显示
         * */
        var grid = new Datatable();
        var $attendees_data_table = $("#attendees_data_table");
        grid.init({
            src: $attendees_data_table,
            onSuccess: function (grid) {
                console.log(grid);
            },
            onError: function (grid) {
            },
            dataTable: {
                "aLengthMenu": [
                    [10, 20, 50, 100, 150, 500],
                    [10, 20, 50, 100, 150, 500]
                ],
                "iDisplayLength": 10,
                "bServerSide": true,
                "sAjaxSource": "${rc.contextPath}/itemPic/list-${(type)!}-${(id)!}",
                "aaSorting": [
                    [0, "desc"]
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5,6,7,8,9]}
                ],//设置不排序得列
                "sDom": "<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
                "aoColumns": [
                    {"sTitle": "图片",  "mData": "picSmall", "mRender": function (data, type, row) {
                            return "<img src='${rc.contextPath}"+row.picSmall+"' width='100px' height='80px'/>";
                    }},
                    {"sTitle": "路径", "mData": "picUrl"},
                    {"sTitle": "大小", "mData": "picSize", "mRender": function (data, type, row) {
                        if(data==null||data==""){
                            return "0kb";
                        }else{
                            return  (data/1024).toFixed(2) + "kb";
                        }
                    }
                    },
                    {"sTitle": "格式", "mData": "picLayout"},
                    {"sTitle": "默认", "mData": "picSort","mRender": function (data, type, row) {
                        if (data==1) {
                            return "是";
                        }else{
                            return "否";
                        }
                    }},
                    {"sTitle": "类型", "mData": "pageCode", "mRender": function (data, type, row) {
                        if (data==1) {
                            return "活动";
                        }else if(data==2) {
                            return "产品";
                        }else if(data==3) {
                            return "配置";
                        }else if(data==4) {
                            return "颜色";
                        }
                    }},
                    {"sTitle": "类型名称", "mData": "itemId", "mRender": function (data, type, row) {
                       return "${rowName}";
                    }},
                    {
                        "sTitle": "创建时间", "mData": "createdTime", "mRender": function (data, type, row) {
                            if (data != null && "" != data) {
                                return new Date(data).Format("yyyy-MM-dd hh:mm");
                            } else {
                                return "";
                            }
                        }
                    },
                    {"sTitle": "创建人", "mData": "createdOn"},
                    {
                        "sTitle": "操作", "sDefaultContent": "", "mRender": function (data, type, full) {

                        var a = '<a href="javascript:void(0);" onclick="defaultOne(\'' + full.id
                                + '\')" class="btn btn-xs blue"  title="设为默认" >' +
                                '<i class="glyphicon glyphicon-cog"></i>设为默认</a>';
                        var b = '<a href="javascript:void(0);" onclick="cancelOne(\'' + full.id
                                + '\')" class="btn btn-xs red"  title="取消默认" >' +
                                '<i class="glyphicon glyphicon-cog"></i>取消默认</a>';

                        var c = '<a href="javascript:void(0);" onclick="deleteOne(\'' + full.id
                                + '\')" class="btn btn-xs red"  title="删除" >' +
                                '<i class="glyphicon glyphicon-trash"></i>删除</a>';

                        if(full.isSynchro==1){//
                            return "已删除";
                        }else{
                            if(full.picSort==1){//默认
                                return b+c;
                            }else{
                                return a+c;
                            }

                        }
                    }
                    }
                ]
            }
        });

        /**
         * 关闭提示信息
         * */
        function alertClose() {
            $(".alert").alert('close');
        }

        /**
         * 提示信息显示
         * */
        jQuery(function ($) {
            var message = "${message?if_exists}", stat = "${success?if_exists}";
            if (null == message || '' == message) {
                $(".alert").alert('close');
            } else {
                if ('true' == stat) {
                    $('#message').removeClass('alert-danger');
                    $('#message').addClass('alert-success');
                    setInterval(alertClose, 3 * 1000);
                } else {
                    $('#message').addClass('alert-danger');
                    $('#message').removeClass('alert-success');
                }
            }
        });

        /**
         * 日期组件
         * */
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            autoclose: true
        })

        /**
         *  选择框选中事件
         * */
        jQuery('#attendees_data_table').on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('span').toggleClass("checked");
        });

        /**
         * 重置查询信息
         * @param btn
         * @param excludes
         */
        function resetSearch(btn) {
            $("#data_table_search input[type='text']").val("");
            $("select.form-filter").each(function () {
                $(this).find("option").attr("selected", false);
            });
            search(btn, grid);
        }

        /**
         * 逻辑删除
         * @param id
         */
        function deleteOne(id) {
            bootbox.dialog({
                message: "您是否确认删除?",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/itemPic/deleteOne-' + id,
                                type: 'POST',
                                traditional: true,
                                success: function (data) {
                                    console.log(data);
                                    Metronic.stopPageLoading();
                                    grid.getDataTable().fnDraw();
                                },
                                error:function(error){

                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: "gray",
                        callback: function () {
                            $(this).hide();
                        }
                    }
                }
            });
        }



        /**
         * 设为默认
         * @param id
         */
        function defaultOne(id) {
            bootbox.dialog({
                message: "您是否确认设置?",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/itemPic/defaultOne-' + id,
                                type: 'POST',
                                traditional: true,
                                success: function (data) {
                                    Metronic.stopPageLoading();
                                    grid.getDataTable().fnDraw();
                                },
                                error:function(error){

                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: "gray",
                        callback: function () {
                            $(this).hide();
                        }
                    }
                }
            });
        }

        /**
         * 取消默认
         * @param id
         */
        function cancelOne(id) {
            bootbox.dialog({
                message: "您是否确认取消默认?",
                buttons: {
                    main: {
                        label: "确定",
                        className: "green",
                        callback: function () {
                            Metronic.startPageLoading();
                            $.ajax({
                                url: '${rc.contextPath}/itemPic/cancelOne-' + id,
                                type: 'POST',
                                traditional: true,
                                success: function (data) {
                                    Metronic.stopPageLoading();
                                    grid.getDataTable().fnDraw();
                                },
                                error:function(error){

                                }
                            });
                        }
                    },
                    cancel: {
                        label: "取消",
                        className: "gray",
                        callback: function () {
                            $(this).hide();
                        }
                    }
                }
            });
        }

        /**
         * 转新增图片
         */
        function createImg() {
            $('#createModals').modal('show');
        }

        /**
         *转选择图片
         */
        function chooseImg() {
            //传活动id给下个页面
            $('#chooseModals').modal('show');
        }

        /**
         * 新增图片
         * */
        function upImg() {
            document.files.action = "${rc.contextPath}/itemPic/upImg-${(type)!}-${(id)!}";
            var submitUrl = document.getElementById("files").attributes["action"].value;
            $.ajax({
                data:$('#files').serialize(),
                type:"POST",
                url:submitUrl,
                dataType:"json",
                cache:false,
                contentType:false,
                processData:false,
                success:function(data){
                }
            })
        }


//        /**
//         *选择图片
//         */
//        function choosePicImg() {
//          var list = document.getElementById("chooseIframe").contentWindow.getCheck();
//        }


        function chooseBtn(){
            grid.getDataTable().fnDraw();
        }
    </script>
</content>
</html>
