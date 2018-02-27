package com.choinoski.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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
    @JoinColumn(name = "pack_nr",
            foreignKey = @ForeignKey(name = "pack_foreign_key"))
    private Pack pack;

    /**
     * Instantiates a new Order.
     */
    public PackMember() {
    }

    /**
     * Instantiates a new Pack member.
     *
     * @param name             the name
     * @param size             the size
     * @param breed            the breed
     * @param sex              the sex
     * @param dateOfBirth      the date of birth
     * @param createDate       the create date
     * @param lastModifiedDate the last modified date
     * @param pack             the pack
     */
    public PackMember(String name, String size, String breed, char sex, LocalDate dateOfBirth, LocalDateTime createDate,
                      LocalDateTime lastModifiedDate, Pack pack) {

        this.name = name;
        this.size = size;
        this.breed = breed;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.pack = pack;
    }

    /**
     * Gets pack member number.
     *
     * @return the pack member number
     */
    public int getPackMemberNumber() {
        return packMemberNumber;
    }

    /**
     * Sets pack member number.
     *
     * @param packMemberNumber the pack member number
     */
    public void setPackMemberNumber(int packMemberNumber) {
        this.packMemberNumber = packMemberNumber;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets breed.
     *
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Sets breed.
     *
     * @param breed the breed
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * Gets sex.
     *
     * @return the sex
     */
    public char getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     */
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

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets last modified date.
     *
     * @return the last modified date
     */
    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets last modified date.
     *
     * @param lastModifiedDate the last modified date
     */
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Gets pack.
     *
     * @return the pack
     */
    public Pack getPack() {
        return pack;
    }

    /**
     * Sets pack.
     *
     * @param pack the pack
     */
    public void setPack(Pack pack) {
        this.pack = pack;
    }

    @Override
    public String toString() {
        return "PackMember{" +
                "packMemberNumber=" + packMemberNumber +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", breed='" + breed + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", pack=" + pack +
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
        PackMember packMember = (PackMember) o;
        return Objects.equals( packMemberNumber, packMember.packMemberNumber );
    }

    @Override
    public int hashCode() {
        return Objects.hash( packMemberNumber );
    }

}

