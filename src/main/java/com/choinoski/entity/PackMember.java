package com.choinoski.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type PackMember.
 */

@Getter
@Setter
@Entity(name = "PackMember")
@Table(name = "pack_member")
public class PackMember implements Serializable {

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

    @Column( name = "picture_filename")
    private String pictureFilename;

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

    @OneToMany(mappedBy = "packMember", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PlaydateMember> playdateMembers = new HashSet<PlaydateMember>();

    /**
     * Instantiates a new Pack Member.
     */
    public PackMember() {
    }

    public PackMember(PackMember memberToCopy) {

        this.packMemberNumber = memberToCopy.getPackMemberNumber();
        this.name             = memberToCopy.getName();
        this.weight           = memberToCopy.getWeight();
        this.breed            = memberToCopy.getBreed();
        this.sex              = memberToCopy.getSex();
        this.dateOfBirth      = memberToCopy.getDateOfBirth();
        this.intact           = memberToCopy.isIntact();
        this.pictureFilename  = memberToCopy.getPictureFilename();
        this.createDate       = memberToCopy.getCreateDate();
        this.lastModifiedDate = memberToCopy.getLastModifiedDate();
        this.pack             = memberToCopy.getPack();

    }

    public PackMember(String name, int weight, String breed, char sex, LocalDate dateOfBirth, boolean intact,
                      String pictureFilename) {

        setName(name);
        setWeight(weight);
        setBreed(breed);
        setSex(sex);
        setDateOfBirth(dateOfBirth);
        setIntact(intact);
        setPictureFilename(pictureFilename);

    }

    public PackMember(String name, String weight, String breed, char sex, LocalDate dateOfBirth, boolean intact,
                      String pictureFilename) {

        setName(name);
        setWeight(weight);
        setBreed(breed);
        setSex(sex);
        setDateOfBirth(dateOfBirth);
        setIntact(intact);
        setPictureFilename(pictureFilename);

    }

    public void setWeight(String weight) {
        this.weight = Integer.parseInt(weight);
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
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }

    @Override
    public String toString() {
        return "PackMember{" +
                "packMemberNumber=" + packMemberNumber +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", breed='" + breed + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                ", intact=" + intact +
                ", pictureFilename='" + pictureFilename + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    public void copyDemographicData(PackMember memberToCopy) {

        this.name             = memberToCopy.getName();
        this.weight           = memberToCopy.getWeight();
        this.breed            = memberToCopy.getBreed();
        this.sex              = memberToCopy.getSex();
        this.dateOfBirth      = memberToCopy.getDateOfBirth();
        this.intact           = memberToCopy.isIntact();
        this.pictureFilename  = memberToCopy.getPictureFilename();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackMember that = (PackMember) o;
        return packMemberNumber == that.packMemberNumber &&
                weight == that.weight &&
                sex == that.sex &&
                intact == that.intact &&
                Objects.equals(name, that.name) &&
                Objects.equals(breed, that.breed) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(pictureFilename, that.pictureFilename) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash("PackMember", packMemberNumber);
    }

}

