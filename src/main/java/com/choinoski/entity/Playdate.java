package com.choinoski.entity;

import com.choinoski.persistence.GenericDao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
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
@Entity(name = "Playdate")
@Table(name = "playdate") // case sensitive!
public class Playdate implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column( name = "playdate_nr")
    private int playdateNumber;

    @Column( name = "organizing_pack_nr")
    private int organizingPackNumber;

    @Column( name = "playdate_location")
    private String playdateLocation;

    private LocalDate date;

    private LocalTime time;

    private String status;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column( name = "private_fl", columnDefinition = "TINYINT")
    private Boolean privatePlaydate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column( name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "last_modified_date")
    private Date lastModifiedDate;

    @OneToMany(mappedBy = "playdate", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER, targetEntity = PlaydateMember.class)
    private Set<PlaydateMember> playdateMembers = new HashSet<PlaydateMember>();

    @Transient
    private GenericDao dao = null;

    public Playdate() {

    }

    public Playdate(String playdateLocation, LocalDate date, LocalTime time, String status, Boolean privatePlaydate) {
        this.playdateLocation = playdateLocation;
        this.date = date;
        this.time = time;
        this.status = status;
        this.privatePlaydate = privatePlaydate;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public Set<PlaydateMember> getMembers() {
        return playdateMembers;
    }

    /**
     * Sets members.
     *
     * @param members the members
     */
    public void setMember(Set<PackMember> members) {
        this.playdateMembers = playdateMembers;
    }

    /**
     * Add member.
     *
     * @param member the member
     */
    public void addMember(PlaydateMember member) {
        dao = new GenericDao(PlaydateMember.class);
        member.setPlaydate(this);
        int id = dao.insert(member);
        PlaydateMember newMember = (PlaydateMember) dao.getById(id);
        playdateMembers.add(newMember);
    }

    /**
     * Remove member.
     *
     * @param member the member
     */
    public boolean removeMember(PlaydateMember member) {

        boolean removed = playdateMembers.remove(member);
        member.setPlaydate(null);

        return removed;

    }

}
