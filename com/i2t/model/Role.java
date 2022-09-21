package com.i2t.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * <p>
 * This class contains details of role. 
 * </p>
 *
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role_name")
    private String roleName;
    
    @ManyToMany(mappedBy = "roles")
    private List<Employee> employees = new ArrayList<>();
    public Role() {}

    /**
     * <p>
     * This method will add new trainee.
     * </p>
     *
     * @param String roleName. - This Variable has the role for employee.
     * @param int roleId. - This Variable has the role id.
     *
     */
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
        
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
}