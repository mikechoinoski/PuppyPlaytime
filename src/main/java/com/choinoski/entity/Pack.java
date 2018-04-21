package com.choinoski.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.choinoski.persistence.GenericDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * A class to represent a pack.
 *
 * @author mchoinoski
 */
@Entity(name = "Pack")
@Table(name = "pack") // case sensitive!
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
    @JsonIgnore
    private Set<PackMember> members = new HashSet<PackMember>();

    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL,
                  orphanRemoval = true, fetch = FetchType.EAGER, targetEntity = Role.class)
    private Set<Role> roles = new HashSet<Role>();

    @Transient
    private GenericDao dao = null;

    public Pack() {

    }

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

    public int getPackNumber() {
        return packNumber;
    }

    public void setPackNumber(int packNumber) {
        this.packNumber = packNumber;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
     * Add member.
     *
     * @param member the member
     */
    public void addMember(PackMember member) {
        dao = new GenericDao(PackMember.class);
        member.setPack(this);
        int id = dao.insert(member);
        PackMember newMember = (PackMember) dao.getById(id);
        members.add(newMember);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Remove member.
     *
     * @param member the member
     */
    public void removeMember(PackMember member) {
        members.remove(member);
        member.setPack(null);
    }

}
