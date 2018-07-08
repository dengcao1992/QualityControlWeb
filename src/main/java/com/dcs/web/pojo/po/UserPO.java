package com.dcs.web.pojo.po;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "users")
public class UserPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(unique = true, nullable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    //中心名称
    @Column(nullable = false)
    private String organizationName;
    private String adminLimit = "default";

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAdminLimit() {
        return adminLimit;
    }

    public void setAdminLimit(String adminLimit) {
        this.adminLimit = adminLimit;
    }
}
