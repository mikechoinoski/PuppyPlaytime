package com.choinoski.entity;

import com.choinoski.util.TimestampAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * The type PackMember.
 */

//@Data
@Getter
@Setter
@Entity(name = "Role")
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column( name = "roleID")
    private int roldId;

    @CreationTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    private Date createDate;

    @Column( name = "role_name")
    private String    roleName;

    @UpdateTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    private Date updateDate;

    @Column( name = "pack_name")
    private String    login;

    @ManyToOne
    @JoinColumn(name = "pack_nr",
            foreignKey = @ForeignKey(name = "pack_foreign_key"))
    @JsonIgnore
    private Pack pack;

    //@Column( name = "pack_nr")
    //private int packNumber;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "login", referencedColumnName = "login")
    //private Pack pack;

    public Role() {

    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(String roleName, Pack pack) {
        this.roleName = roleName;
        this.login = pack.getPackName();
        this.pack = pack;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roldId=" + roldId +
                ", createDate=" + createDate +
                ", roleName='" + roleName + '\'' +
                ", updateDate=" + updateDate +
                ", login='" + login + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roldId == role.roldId &&
                Objects.equals(createDate, role.createDate) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(updateDate, role.updateDate) &&
                Objects.equals(login, role.login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roldId, createDate, roleName, updateDate, login);
    }

}


