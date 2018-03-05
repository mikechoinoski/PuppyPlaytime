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
@Entity(name = "PackLeader")
@Table(name = "pack_leader")
public class PackLeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "pack_leader_nr")
    private int packLeaderNumber;

    @Column(name = "pack_name")
    private String packName;
    private String address;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private char phoneNumber;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "pack_nr",
            foreignKey = @ForeignKey(name = "pack_leader_to_pack_fk"))

    private Pack pack;

    public int getPackLeaderNumber() {
        return packLeaderNumber;
    }

    public void setPackLeaderNumber(int packLeaderNumber) {
        this.packLeaderNumber = packLeaderNumber;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
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

    public char getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(char phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "PackLeader{" +
                "packLeaderNumber=" + packLeaderNumber +
                ", packName='" + packName + '\'' +
                ", address='" + address + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackLeader that = (PackLeader) o;
        return packLeaderNumber == that.packLeaderNumber &&
                phoneNumber == that.phoneNumber &&
                Objects.equals(packName, that.packName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(emailAddress, that.emailAddress) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(packLeaderNumber, packName, address, emailAddress,
                            phoneNumber, createDate, lastModifiedDate);
    }

}