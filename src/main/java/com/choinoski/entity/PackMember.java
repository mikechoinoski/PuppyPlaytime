package com.choinoski.entity;

import com.choinoski.persistence.GenericDao;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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
    private int           weight;
    private String        breed;
    private char          sex;

    @Column( name = "date_of_birth")
    private LocalDate     dateOfBirth;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean intact;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column( name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "last_modified_date")
    private Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "pack_nr",
            foreignKey = @ForeignKey(name = "pack_foreign_key"))
    private Pack pack;

    /**
     * Instantiates a new Pack Member.
     */
    public PackMember() {
    }

    public PackMember(String name, int weight, String breed, char sex, LocalDate dateOfBirth, boolean intact) {

        String size;

        this.name = name;
        this.weight = weight;
        this.breed = breed;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.intact = intact;

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public String getSize() {

        String size =  null;

        if (weight < 10) {
            size = "XS";
        } else if (weight < 25) {
            size = "S";
        } else if (weight < 60) {
            size = "M";
        } else if (weight < 110) {
            size = "L";
        } else {
            size = "XL";
        }

        return size;
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

    public boolean isIntact() {
        return intact;
    }

    public void setIntact(boolean intact) {
        this.intact = intact;
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
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets last modified date.
     *
     * @return the last modified date
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets last modified date.
     *
     * @param lastModifiedDate the last modified date
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
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
                ", size='" + getSize() + '\'' +
                ", breed='" + breed + '\'' +
                ", sex=" + sex +
                ", age=" + getAge() +
                ", intact=" + intact +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
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
        return  Objects.equals( packMemberNumber, packMember.packMemberNumber ) &&
                Objects.equals( name, packMember.name ) &&
                Objects.equals( getSize(), packMember.getSize() ) &&
                Objects.equals( breed, packMember.breed ) &&
                Objects.equals( sex, packMember.sex ) &&
                Objects.equals( intact, packMember.intact ) &&
                Objects.equals( getAge(), packMember.getAge() );
    }

    @Override
    public int hashCode() {

        return Objects.hash( packMemberNumber, name, breed, sex, intact, dateOfBirth,
                             createDate, lastModifiedDate);

    }

}

