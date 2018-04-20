package com.choinoski.entity;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    private String        weight;
    private String        size;
    private String        breed;
    private char          sex;

    @Column( name = "date_of_birth")
    private LocalDate     dateOfBirth;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean intact;

    @Column( name = "create_date")
    @Temporal( value = TemporalType.TIMESTAMP )
    @org.hibernate.annotations.Generated(value= GenerationTime.ALWAYS)
    private LocalDateTime createDate;

    @Column( name = "last_modified_date")
    @Temporal( value = TemporalType.TIMESTAMP )
    @org.hibernate.annotations.Generated(value= GenerationTime.ALWAYS)
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "pack_nr",
            foreignKey = @ForeignKey(name = "pack_foreign_key"))
    private Pack pack;

    /**
     * Instantiates a new Pack Member.
     */
    public PackMember() {
    }

    public PackMember(String name, String weight, String size, String breed, char sex,
                      LocalDate dateOfBirth, boolean intact) {
        this.name = name;
        this.weight = weight;
        this.size = size;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
                Objects.equals( size, packMember.size ) &&
                Objects.equals( breed, packMember.breed ) &&
                Objects.equals( sex, packMember.sex ) &&
                Objects.equals( intact, packMember.intact ) &&
                Objects.equals( dateOfBirth, packMember.dateOfBirth );
    }

    @Override
    public int hashCode() {

        return Objects.hash( packMemberNumber, name, size, breed, sex, intact, dateOfBirth,
                             createDate, lastModifiedDate);

    }

}

