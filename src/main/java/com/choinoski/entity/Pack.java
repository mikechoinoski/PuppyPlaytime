package com.choinoski.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import javax.ejb.Local;
import javax.persistence.*;
/**
 * A class to represent a pack.
 *
 * @author mchoinoski
 */
@Entity(name = "Pack")
@Table(name = "pack") // case sensitive!
public class Pack {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column( name = "pack_nr")
    private int packNumber;

    @Column( name = "pack_name")
    private String packName;

    private String login;
    private String password;

    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PackMember> members = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public Pack() {
    }

    /**
     * Instantiates a new User.
     *
     * @param packName  the name of the pack
     * @param login     the login for the pack
     * @param password  the password for the pack's login
     */
    public Pack(String packName, String login , String password) {
        this.packName = packName;
        this.login  = login ;
        this.password = password;
        //this.packTokenNumber = packTokenNumber;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    public void setOrders(Set<PackMember> members) {
        this.members = members;
    }

    /**
     * Add member.
     *
     * @param member the member
     */
    public void addMember(PackMember member) {
        members.add(member);
        member.setPack(this);
    }

    /**
     * Remove member.
     *
     * @param member the member
     */
    public void removeOrder(PackMember member) {
        members.remove(member);
        member.setPack(null);
    }


}
