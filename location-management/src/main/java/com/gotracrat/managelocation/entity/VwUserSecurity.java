package com.gotracrat.managelocation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author anudeep
 * @since 2021-08-05
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vwUserSecurity", schema = "dbo")
public class VwUserSecurity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsersecurityKey compositePrimaryKey ;
    @Column(name="UserName")
    private String userName;
    @Column(name="CompanyName")
    private String companyName;
    @Column(name="LocationName")
    private String locationName;
    @Column(name="LocationPath")
    private String locationPath;
    @Column(name="RoleID")
    private String roleId;
    @Column(name="RoleName")
    private String roleName;
    @Column(name="LoweredRoleName")
    private String loweredRoleName;
    @Column(name="Rank")
    private Integer rank;
}
