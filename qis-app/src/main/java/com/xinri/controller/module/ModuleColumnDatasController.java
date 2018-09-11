package com.xinri.controller.module;

import com.app.api.DataTable;
import com.app.util.StatusMsgUtils;
import com.qis.common.mapper.JsonMapper;
import com.qis.common.web.BaseController;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataLines;
import com.xinri.service.moduleInfo.*;
import com.xinri.service.moduleInfo.impl.ModuleInfoesServiceImpl;
import com.xinri.vo.moduleInfo.RoleModuleInFoColumnDataLineVo;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 模块 数据列
 * 创建人 汪震 20180907
 */
@Controller
@RequestMapping(value = "module/moduleColumnDatas")
public class ModuleColumnDatasController extends BaseController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IColumnDatasService ColumnDatasService;

    @Autowired
    private IModuleInfoColumnDatasService moduleInfoColumnDatasService;

    @Autowired
    private IRoleModuleInfoColumnDataHeadsService roleModuleInfoColumnDataHeadsService;

    @Autowired
    private IRoleModuleInfoColumnDataLinesService roleModuleInfoColumnDataLinesService;

    @ResponseBody
    @RequestMapping(value = "lines-{roleId}-{infoId}", method = RequestMethod.POST)
    public DataTable<RoleModuleInFoColumnDataLineVo> getModuleList(DataTable<RoleModuleInFoColumnDataLineVo> dt,
                                                                   @PathVariable(value = "roleId") Long roleId,
                                                                   @PathVariable(value = "infoId") Long infoId) {
        return roleModuleInfoColumnDataLinesService.getModulesForRole(dt, roleId,infoId);
    }

    /**
     * 首页
     * @return
     * 创建人 汪震 20180907
     */
    @RequiresPermissions("module-columns-index")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        ModelAndView mv = new ModelAndView("/module/col");
        ColumnDatas columnData = new ColumnDatas();
        columnData.setIsDeleted(0);
        mv.addObject("columnDatas", ColumnDatasService.findList(columnData));//返回所有的权限信息供页面选择
        return mv;
    }

    /**
     * 根据模块id获取当前模块已分配的所有数据列
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "getCols/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getCols(@PathVariable Long id) {
        Map map = new HashMap();
        ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
        moduleInfoColumnData.setModuleInfoId(id);
        moduleInfoColumnData.setIsEffective(1);
        map.put("module", moduleInfoesService.get(id));
        map.put("myCols", moduleInfoColumnDatasService.findList(moduleInfoColumnData));
        return map;
    }

    /**
     * 保存
     * @param id
     * @param cols
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(Long id, String[] cols) {
        ModuleInfoColumnDatas moduleInfoColumnDatas = new ModuleInfoColumnDatas();
        moduleInfoColumnDatas.setModuleInfoId(id);
        moduleInfoColumnDatas.setIsEffective(0);
        moduleInfoColumnDatasService.relate(moduleInfoColumnDatas);

        Long[] ids = (Long[]) ConvertUtils.convert(cols,Long.class);
        if(ids!=null&&ids.length>0){
        roleModuleInfoColumnDataHeadsService.deleteByDiff(Arrays.asList(ids),id);
        }
        if (cols != null) {
            for (String col : cols) {
                moduleInfoColumnDatas.setModuleInfoId(id);
                moduleInfoColumnDatas.setColumnDataId(Long.valueOf(col));
                moduleInfoColumnDatas.setIsEffective(1);
                moduleInfoColumnDatasService.relate(moduleInfoColumnDatas);
            }
        }
        ModelAndView mv = new ModelAndView("redirect:/module/moduleColumnDatas/index/");
        return mv;
    }

    /**创建者 汪震  创建时间 2018.9.10
     * 逻辑删除 角色数据列line
     * @param id
     * @return
     */
    @RequestMapping(value="roleDelete/{id}")
    public String roleDelete(@PathVariable("id") Long id, HttpServletResponse response){
        roleModuleInfoColumnDataLinesService.deleteById(id);
        return responseJsonData("200", "离开成功", null,response);
    }

    /**创建者 汪震  创建时间 2018.9.10
     * 保存数据列line
     * @param body
     * @param response
     * @return
     */
    @RequestMapping(value="roleSave")
    public String delete(@RequestBody String body, HttpServletResponse response){
        logger.info("保存角色模块数据列line开始");
        String code = StatusMsgUtils.SUCCEEDEDCODE_200;
        String msg = StatusMsgUtils.ResponseCode.getName(code);
        try {
            RoleModuleInfoColumnDataLines vo = (RoleModuleInfoColumnDataLines) JsonMapper.fromJsonString(body,RoleModuleInfoColumnDataLines.class);
            RoleModuleInfoColumnDataLines lines = new RoleModuleInfoColumnDataLines();
            lines = roleModuleInfoColumnDataLinesService.get(vo);
            if(lines==null){
                vo.setIsEffective(1);
                roleModuleInfoColumnDataLinesService.saveOrUpdate(vo);
            }else{
                if(lines.getIsDeleted()==0){
                    code = StatusMsgUtils.ERRORCODE_8002;
                    msg = StatusMsgUtils.ResponseCode.getName(code);
                }else{
                    lines.setIsDeleted(0);
                    lines.setIsNewRecord(false);
                    roleModuleInfoColumnDataLinesService.saveOrUpdate(lines);
                }
            }
        } catch (Exception e) {
            logger.error("保存角色模块数据列line报错：", e);
            code = StatusMsgUtils.ERRORCODE_400;
            msg = StatusMsgUtils.ResponseCode.getName(code);
        }
        logger.info("保存角色模块数据列line完成");
        return responseJsonData(code, msg, null,response);
    }
}
