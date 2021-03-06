package com.choinoski.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A class to represent a pack.
 *
 * @author mchoinoski
 */
@Getter
@Setter
@Entity(name = "PlaydateMember")
@Table(name = "playdate_member")
public class PlaydateMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column( name = "playdate_member_nr")
    private int playdateMemberNumber;

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

    @ManyToOne
    @JoinColumn(name = "pack_member_nr",
            foreignKey = @ForeignKey(name = "playdate_member_pack_member_pack_member_nr_fk"))
    private PackMember packMember;

    /**
     * Instantiates a new Playdate member.
     */
    public PlaydateMember() {
    }

    /**
     * Instantiates a new Playdate member.
     *
     * @param status     the status
     * @param packMember the pack member
     * @param playdate   the playdate
     */
    public PlaydateMember(String status, PackMember packMember, Playdate playdate) {
        this.status = status;
        this.packMember = packMember;
        this.playdate = playdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaydateMember that = (PlaydateMember) o;
        return playdateMemberNumber == that.playdateMemberNumber &&
                Objects.equals(status, that.status) &&
                Objects.equals(playdate, that.playdate) &&
                Objects.equals(packMember, that.packMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash("PlayDateMember=" + playdateMemberNumber);
    }
}