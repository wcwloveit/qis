<html>
<head>
    <title>众筹项目</title>
    <link href="${rc.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="${rc.contextPath}/assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
</head>
<body>
<div class="row">
    <div class="col-md-12">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <th>图片</th>
                        <th>图片格式</th>
                        <th>图片大小</th>
                        <th>创建人</th>
                        <th>操作</th>
                        </thead>
                        <tbody>
                    <#list picList?sort_by("picSort") as pic>
                        <tr>
                            <td><img src="${rc.contextPath}${pic.picSmall}" width="100px" height="80px"/></td>
                            <td>${pic.picLayout}</td>
                            <td><#if pic.picSize>${pic.picSize/1024}<#else>0</#if>kb</td>
                            <td>${pic.createdBy}</td>
                            <td>
                                <button onclick="setImg(${pic.id});" class="btn btn-primary" type="button">设为活动图片</button>
                            </td>
                        </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>
    </div>
</div>
</body>
<content tag="script">
    <script src="${rc.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function setImg(id){
            var actId = $('#activityId',parent.document).val();
            $.ajax({
                url: '${rc.contextPath}/itemPic/choosePic-' + actId + "-"+ id,
                type: 'POST',
                traditional: true,
                success: function () {
                    alert("设置成功！");
//                    $('#chooseModals',parent.document).modal('hidden');
                },
                error:function(error){
                }
            });
        }
    </script>
</content>
</html>
