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
    private Boolean hasOutDoor;
    private Boolean hasGoodBeer;
    private Boolean teenagerFriendly;
    private int capacity;

    public Pub() {

    }

    public Pub(int id, String PubName, String linkMoreInfo, Boolean hasFreeParking, Boolean hasOutDoor, Boolean hasGoodBeer, Boolean teenagerFriendly, int capacity) {
        this.id = id;
        this.PubName = PubName;
        this.linkMoreInfo = linkMoreInfo;
        this.hasFreeParking = hasFreeParking;
        this.hasOutDoor = hasOutDoor;
        this.hasGoodBeer = hasGoodBeer;
        this.teenagerFriendly = teenagerFriendly;
        this.capacity = capacity;
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

    public Boolean gethasOutDoor() {
        return hasOutDoor;
    }

    public void sethasOutDoor(Boolean hasOutDoor) {
        this.hasOutDoor = hasOutDoor;
    }

    public Boolean getHasGoodBeer() {
        return hasGoodBeer;
    }

    public void setHasGoodBeer(Boolean hasGoodBeer) {
        this.hasGoodBeer = hasGoodBeer;
    }

    public Boolean getTeenagerFriendly() {
        return teenagerFriendly;
    }

    public void setTeenagerFriendly(Boolean teenagerFriendly) {
        this.teenagerFriendly = teenagerFriendly;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
}
