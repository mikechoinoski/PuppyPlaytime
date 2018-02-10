package com.choinoski.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.hibernate.annotations.GenericGenerator;
import javax.ejb.Local;
import javax.persistence.*;
/**
 * A class to represent a user.
 *
 * @author pwaite
 */
@Entity(name = "Pack")
@Table(name = "pack") // case sensitive!
public class Pack {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column( name = "pack_token_nr")
    private int packTokenNumber;

    @Column( name = "pack_name")
    private String packName;

    private String login;
    private String password;

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

    public int getPackTokenNumber() {
        return packTokenNumber;
    }

    public void setPackTokenNumber(int packTokenNumber) {
        this.packTokenNumber = packTokenNumber;
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

    //@Override
    //public String toString() {
    //    return "User{" +
    //            "firstName='" + firstName + '\'' +
    //            ", lastName='" + lastName + '\'' +
    //            ", userName='" + userName + '\'' +
    //            '}';
    //}


}