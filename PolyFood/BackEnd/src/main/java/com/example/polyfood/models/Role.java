package com.example.polyfood.models;

import com.example.polyfood.models.Roles.ERole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int decentralizationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private ERole roleName;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updateAt;

    public Role()
    {

    }

    public Role(ERole roleName)
    {
        this.roleName = roleName;
    }
    public int getDecentralizationId() {
        return decentralizationId;
    }

    public void setDecentralizationId(int decentralizationId) {
        this.decentralizationId = decentralizationId;
    }

    public ERole getRoleName() {
        return roleName;
    }

    public void setRoleName(ERole roleName) {
        this.roleName = roleName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

}
