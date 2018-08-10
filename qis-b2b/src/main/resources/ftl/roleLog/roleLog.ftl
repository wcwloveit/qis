<html>
<head>
    <title>报表查看日志</title>

</head>
<body>


<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${rc.contextPath}/">报表查看日志</a>
                <i class="fa fa-angle-right"></i>
            </li>

        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-cogs"></i>查看报表日志</div>

            </div>
            <div class="portlet-body">
            <#if message>
                <div class="alert  <#if message && success='true'>alert-success<#else>alert-danger</#if>">
                    <button data-dismiss="alert" class="close">×</button>
                ${(message)!}
                </div>
            </#if>
                <div class="table-container">
                    <div class="table-actions-wrapper">
                    </div>
                    <div id="data_table_search">
                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">OA账号</b>
                            <input type="text" class="input-sm form-filter" name="search_code"
                                   id="search_ZC_activityTitle" placeholder="OA账号"/>
                        </label>
                        <label style="float:left;margin-right:5px;">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">姓名</b>
                            <input type="text" class="input-sm form-filter" name="search_name"
                                   id="search_ZC_modelName" placeholder="姓名"/>
                        </label>

                        <label style="float:left;">
                            <span> &nbsp;&nbsp;</span>
                            <button class="btn btn-sm yellow margin-bottom filter-submit" value="搜索"
                                    onclick="search(this,grid)"><i class="fa fa-search"></i> 搜索
                            </button>
                            <button class="btn btn-sm red filter-cancel" onclick="resetSearch(this)"><i
                                    class="fa fa-times"></i> 重置
                            </button>
                        </label>
                        <label style="float:left;margin-right:5px;visibility: hidden;width: 100%">
                            <b class="form-control input-inline" style="border: 0px; text-align: left;">活动状态</b>
                            <input type="text" class="input-sm form-filter" name="search_ZC_activityStatus"
                                   id="search_ZC_activityStatus" placeholder="活动状态"/>
                        </label>
                    </div>
                    <div>
                        <table class="table table-striped table-bordered table-hover" id="attendees_data_table">
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
