package com.choinoski.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The type Order.
 */
@Entity(name = "PackMember")
@Table(name = "pack_member")
public class PackMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column( name = "pack_token_nr")
    private int packMemberNumber;

    @Column( name = "pack_token_nr")
    private int       packTokenNumber;

    private String        name;
    private String        size;
    private String        breed;
    private char          sex;
    private LocalDate     dateOfBirth;
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    private Pack pack;

    /**
     * Instantiates a new Order.
     */
    public PackMember() {
    }

    public PackMember(int packTokenNumber, String name, String size, String breed, char sex, LocalDate dateOfBirth,
                      LocalDateTime lastModifiedDate, Pack pack) {
        this.packTokenNumber = packTokenNumber;
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.lastModifiedDate = lastModifiedDate;
        this.pack = pack;
    }

    public int getPackMemberNumber() {
        return packMemberNumber;
    }

    public void setPackMemberNumber(int packMemberNumber) {
        this.packMemberNumber = packMemberNumber;
    }

    public int getPackTokenNumber() {
        return packTokenNumber;
    }

    public void setPackTokenNumber(int packTokenNumber) {
        this.packTokenNumber = packTokenNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    @Override
    public String toString() {
        return "PackMember{" +
                "packMemberNumber=" + packMemberNumber +
                ", packTokenNumber=" + packTokenNumber +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", breed='" + breed + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                ", lastModifiedDate=" + lastModifiedDate +
                ", pack=" + pack +
                '}';
    }

}

