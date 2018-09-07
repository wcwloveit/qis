package com.xinri.vo.moduleInfo;

public class RoleModuleInfoColVo {
    private Long moduleColumnId;//module_info_column 模块权限id
    private Integer mcIsEffective;//module_info_column 是否勾选
    private Long colunmnId;//permission_id 权限id
    private Long moduleId;//module_info_id 模块id
    private Long roleId;//角色id
    private Integer rIsEffective;//role_module_info_column_heads //是否启用该权限
    private String name;//column 权限名称
    private String code;//column 权限编码
    private Long rmchId;//role_module_info_column_heads id

    public Long getModuleColumnId() {
        return moduleColumnId;
    }

    public void setModuleColumnId(Long moduleColumnId) {
        this.moduleColumnId = moduleColumnId;
    }

    public Integer getMcIsEffective() {
        return mcIsEffective;
    }

    public void setMcIsEffective(Integer mcIsEffective) {
        this.mcIsEffective = mcIsEffective;
    }

    public Long getColunmnId() {
        return colunmnId;
    }

    public void setColunmnId(Long colunmnId) {
        this.colunmnId = colunmnId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getrIsEffective() {
        return rIsEffective;
    }

    public void setrIsEffective(Integer rIsEffective) {
        this.rIsEffective = rIsEffective;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRmchId() {
        return rmchId;
    }

    public void setRmchId(Long rmchId) {
        this.rmchId = rmchId;
    }
}
