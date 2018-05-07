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
 * A class to represent a playdate.
 *
 * @author mchoinoski
 */
@Getter
@Setter
@Entity(name = "Playdate")
@Table(name = "playdate")
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

    /**
     * Instantiates a new Playdate.
     */
    public Playdate() {

    }

    /**
     * Instantiates a new Playdate.
     *
     * @param organizingPackNumber the organizing pack number
     * @param playdateLocation     the playdate location
     * @param date                 the date
     * @param time                 the time
     * @param status               the status
     * @param privatePlaydate      the private playdate
     */
    public Playdate(int organizingPackNumber, String playdateLocation, LocalDate date, LocalTime time,
                    String status, Boolean privatePlaydate) {
        this.organizingPackNumber = organizingPackNumber;
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
     * Add a member.
     *
     * @param member the member to add
     */
    public void addMember(PlaydateMember member) {
        dao = new GenericDao(PlaydateMember.class);
        member.setPlaydate(this);
        int id = dao.insert(member);
        PlaydateMember newMember = (PlaydateMember) dao.getById(id);
        playdateMembers.add(newMember);
    }

    /**
     * Remove a member.
     *
     * @param member the member to remove
     * @return if the removal of a member is successful
     */
    public boolean removeMember(PlaydateMember member) {

        boolean removed = playdateMembers.remove(member);
        member.setPlaydate(null);

        return removed;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playdate playdate = (Playdate) o;
        return organizingPackNumber == playdate.organizingPackNumber &&
                Objects.equals(playdateLocation, playdate.playdateLocation) &&
                Objects.equals(date, playdate.date) &&
                Objects.equals(time, playdate.time) &&
                Objects.equals(status, playdate.status) &&
                Objects.equals(privatePlaydate, playdate.privatePlaydate);
    }

}
