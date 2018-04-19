package com.choinoski.entity;

import com.choinoski.util.TimestampAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type PackMember.
 */
@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column( name = "roleID")
    private int roldId;

    @Convert(converter = TimestampAttributeConverter.class)
    private LocalDateTime createDate;

    @Column( name = "role_name")
    private String    roleName;

    @Convert(converter = TimestampAttributeConverter.class)
    private LocalDateTime updateDate;

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

    public Role(LocalDateTime createDate, String roleName, LocalDateTime updateDate, String login, Pack pack) {
        this.createDate = createDate;
        this.roleName = roleName;
        this.updateDate = updateDate;
        this.pack = pack;
    }

    public int getRoldId() {
        return roldId;
    }

    public void setRoldId(int roldId) {
        this.roldId = roldId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
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


