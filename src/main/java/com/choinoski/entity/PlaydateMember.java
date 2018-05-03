package com.choinoski.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a pack.
 *
 * @author mchoinoski
 */
@Getter
@Setter
@Entity(name = "PlaydateMember")
@Table(name = "playdate_member") // case sensitive!
public class PlaydateMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column( name = "playdate_member_nr")
    private int packdateMemberNumber;

    @Column( name = "pack_member_nr")
    private int packMemberNumber;

    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column( name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "last_modified_date")
    private Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "playdate_nr",
            foreignKey = @ForeignKey(name = "playdate_member_playdate_playdate_nr_fk"))
    private Playdate playdate;

    public PlaydateMember() {
    }

    public PlaydateMember(int packMemberNumber, String status) {
        this.packMemberNumber = packMemberNumber;
        this.status = status;
    }
}