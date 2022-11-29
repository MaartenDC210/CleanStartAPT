package be.thomasmore.cleanstartapt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Pub {
    @Id
    private int id;
    private String PubName;
    private String linkMoreInfo;
    private Boolean hasFreeParking;
    private Boolean toddlerFriendly;
    private Boolean hasGoodBeer;
    private Boolean teenagerFriendly;

    public Pub() {

    }

    public Pub(int id, String PubName, String linkMoreInfo, Boolean hasFreeParking, Boolean toddlerFriendly, Boolean hasGoodBeer, Boolean teenagerFriendly) {
        this.id = id;
        this.PubName = PubName;
        this.linkMoreInfo = linkMoreInfo;
        this.hasFreeParking = hasFreeParking;
        this.toddlerFriendly = toddlerFriendly;
        this.hasGoodBeer = hasGoodBeer;
        this.teenagerFriendly = teenagerFriendly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPubName() {
        return PubName;
    }

    public void setPubName(String PubName) {
        this.PubName = PubName;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public Boolean getHasFreeParking() {
        return hasFreeParking;
    }

    public void setHasFreeParking(Boolean hasFreeParking) {
        this.hasFreeParking = hasFreeParking;
    }

    public Boolean getToddlerFriendly() {
        return toddlerFriendly;
    }

    public void setToddlerFriendly(Boolean toddlerFriendly) {
        this.toddlerFriendly = toddlerFriendly;
    }

    public Boolean getHasGoodFood() {
        return hasGoodBeer;
    }

    public void setHasGoodFood(Boolean hasGoodFood) {
        this.hasGoodBeer = hasGoodFood;
    }

    public Boolean getTeenagerFriendly() {
        return teenagerFriendly;
    }

    public void setTeenagerFriendly(Boolean teenagerFriendly) {
        this.teenagerFriendly = teenagerFriendly;
    }
}
