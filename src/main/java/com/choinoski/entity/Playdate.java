package com.choinoski.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a pack.
 *
 * @author mchoinoski
 */
@Entity(name = "Playdate")
@Table(name = "playdate") // case sensitive!
public class Playdate implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column( name = "playdate_nr")
    private int playdateNumber;



}
