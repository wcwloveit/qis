package com.xinri.vo.moduleInfo;

public class RoleModuleInFoPerVo {
    private Long modulePermissionId;//module_info_permissions 模块权限id
    private Integer mpIsEffective;//module_info_permissions 是否勾选
    private Long permissionId;//permission_id 权限id
    private Long moduleId;//module_info_id 模块id
    private Long roleId;//角色id
    private Integer rIsEffective;//role_module_info_permission_heads //是否启用该权限
    private String name;//permissions 权限名称
    private String code;//permissions 权限编码

    public Long getModulePermissionId() {
        return modulePermissionId;
    }

    public void setModulePermissionId(Long modulePermissionId) {
        this.modulePermissionId = modulePermissionId;
    }

    public Integer getMpIsEffective() {
        return mpIsEffective;
    }

    public void setMpIsEffective(Integer mpIsEffective) {
        this.mpIsEffective = mpIsEffective;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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
}
