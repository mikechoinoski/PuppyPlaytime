package com.choinoski.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
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
     * @param packName the name of the pack
     * @param login    the login for the pack
     * @param password the password for the pack's login
     */
    public Pack(String packName, String login , String password) {
        this.packName = packName;
        this.login  = login ;
        this.password = password;
        //this.packTokenNumber = packTokenNumber;
    }

    /**
     * Gets pack number.
     *
     * @return the pack number
     */
    public int getPackNumber() {
        return packNumber;
    }

    /**
     * Sets pack number.
     *
     * @param packNumber the pack number
     */
    public void setPackNumber(int packNumber) {
        this.packNumber = packNumber;
    }

    /**
     * Gets pack name.
     *
     * @return the pack name
     */
    public String getPackName() {
        return packName;
    }

    /**
     * Sets pack name.
     *
     * @param packName the pack name
     */
    public void setPackName(String packName) {
        this.packName = packName;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
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

    @Override
    public String toString() {
        return "Pack{" +
                "packNumber=" + packNumber +
                ", packName='" + packName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", members=" + members +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Pack pack = (Pack) o;
        return Objects.equals( packNumber, pack.packNumber );
    }

    @Override
    public int hashCode() {
        return Objects.hash( packNumber );
    }


}
