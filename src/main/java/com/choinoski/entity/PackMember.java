package com.choinoski.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * The type PackMember.
 */
@Entity(name = "PackMember")
@Table(name = "pack_member")
public class PackMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column( name = "pack_member_nr")
    private int packMemberNumber;

    //@Column( name = "pack_token_nr2")
    //private int       packTokenNumber;

    private String        name;
    private String        size;
    private String        breed;
    private char          sex;

    @Column( name = "date_of_birth")
    private LocalDate     dateOfBirth;

    @Column( name = "create_date")
    private LocalDateTime createDate;

    @Column( name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "pack_token_nr2",
            foreignKey = @ForeignKey(name = "pack_foreign_key"))
    private Pack pack;

    /**
     * Instantiates a new Order.
     */
    public PackMember() {
    }

    public PackMember(String name, String size, String breed, char sex, LocalDate dateOfBirth, LocalDateTime createDate,
                      LocalDateTime lastModifiedDate, Pack pack) {
        //this.packTokenNumber = pack.getPackTokenNumber();
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.pack = pack;
    }

    public int getPackMemberNumber() {
        return packMemberNumber;
    }

    public void setPackMemberNumber(int packMemberNumber) {
        this.packMemberNumber = packMemberNumber;
    }

    //public int getPackTokenNumber() {
    //    return packTokenNumber;
    //}

    //public void setPackTokenNumber(int packTokenNumber) {
    //    this.packTokenNumber = packTokenNumber;
    //}

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

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {

        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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

}

