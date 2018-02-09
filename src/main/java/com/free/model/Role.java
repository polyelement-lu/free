package com.free.model;

import javax.persistence.*;

public class Role {
    @Id
    private Integer id;

    /**
     * 角色
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 用户角色
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色
     *
     * @return role_code - 角色
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色
     *
     * @param roleCode 角色
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取用户角色
     *
     * @return role_name - 用户角色
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置用户角色
     *
     * @param roleName 用户角色
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}