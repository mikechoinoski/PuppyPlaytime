package com.choinoski.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.choinoski.persistence.GenericDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * A class to represent a pack.
 *
 * @author mchoinoski
 */
@Getter
@Setter
@Entity(name = "Pack")
@Table(name = "pack")
public class Pack implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column( name = "pack_nr")
    private int packNumber;

    @Column( name = "pack_name")
    private String packName;

    @Column( name = "first_name")
    private String firstName;

    @Column( name = "last_name")
    private String lastName;

    private String address;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PackMember> members = new HashSet<PackMember>();

    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL,
                  orphanRemoval = true, fetch = FetchType.EAGER, targetEntity = Role.class)
    private Set<Role> roles = new HashSet<Role>();

    @Transient
    private GenericDao dao = null;

    /**
     * Instantiates a new Pack.
     */
    public Pack() {

    }

    /**
     * Instantiates a new Pack.
     *
     * @param packName     the pack name
     * @param firstName    the first name
     * @param lastName     the last name
     * @param address      the address
     * @param emailAddress the email address
     * @param phoneNumber  the phone number
     * @param password     the password
     */
    public Pack(String packName, String firstName, String lastName, String address, String emailAddress,
                String phoneNumber, String password) {
        this.packName = packName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public Set<PackMember> getMembers() {
        return members;
    }

    /**
     * Sets members.
     *
     * @param members the members
     */
    public void setMember(Set<PackMember> members) {
        this.members = members;
    }

    /**
     * Add a member.
     *
     * @param member the member to add
     */
    public void addMember(PackMember member) {
        dao = new GenericDao(PackMember.class);
        member.setPack(this);
        int id = dao.insert(member);
        PackMember newMember = (PackMember) dao.getById(id);
        members.add(newMember);
    }

    /**
     * Remove a member.
     *
     * @param member the member to remove
     * @return the boolean
     */
    public boolean removeMember(PackMember member) {

        boolean removed = members.remove(member);
        member.setPack(null);

        return removed;

    }

    /**
     * Returns how many members are in the pack
     *
     * @return the number of members in a pack
     */
    public int getPackSize() {

        return members.size();

    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Add a role.
     *
     * @param roleText the role
     */
    public void addRole(String roleText) {
        Role newRole = new Role(roleText);
        dao = new GenericDao(Role.class);
        newRole.setLogin(this.getPackName());
        newRole.setPack(this);
        int id = dao.insert(newRole);
        Role roleWithId = (Role) dao.getById(id);
        roles.add(roleWithId);
    }

    /**
     * Remove a role.
     *
     * @param role the role
     * @return the boolean
     */
    public boolean removeRole(Role role) {

        boolean removed = roles.remove(role);
        role.setPack(null);

        return removed;

    }
}
