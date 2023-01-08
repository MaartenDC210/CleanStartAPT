package be.thomasmore.cleanstartapt.model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Entity
public class Party {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party_generator")
    @SequenceGenerator(name = "party_generator", sequenceName = "party_seq", allocationSize = 1)
    @Id
    private Integer id;
    @NotBlank
    private String name;
    private Integer pricePresaleInEur;
    private Integer priceInEur;
    private String extraInfo;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date date;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private Date doors;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pub pub;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Artist> artists;

    @ManyToMany(mappedBy = "parties", fetch = FetchType.LAZY)
    private Collection<Animal> animals;

    public Party() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPricePresaleInEur() {
        return pricePresaleInEur;
    }

    public void setPricePresaleInEur(Integer pricePresaleInEur) {
        this.pricePresaleInEur = pricePresaleInEur;
    }

    public Integer getPriceInEur() {
        return priceInEur;
    }

    public void setPriceInEur(Integer priceInEur) {
        this.priceInEur = priceInEur;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDoors() {
        return doors;
    }

    public void setDoors(Date doors) {
        this.doors = doors;
    }

    public Pub getPub() {
        return pub;
    }

    public void setPub(Pub pub) {
        this.pub = pub;
    }

    public Collection<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Collection<Artist> artists) {
        this.artists = artists;
    }

    public Collection<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Collection<Animal> animals) {
        this.animals = animals;
    }
}


