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
    private Boolean hasOutdoor;
    private Boolean hasGoodBeer;
    private Boolean teenagerFriendly;
    private int capacity;
    private String city;
    private double distanceFromPublicTransportInKm;

    public Pub() {

    }

    public Pub(int id, String PubName, String linkMoreInfo, Boolean hasFreeParking, Boolean hasOutdoor, Boolean hasGoodBeer, Boolean teenagerFriendly, int capacity, String city, int distanceFromPublicTransportInKm) {
        this.id = id;
        this.PubName = PubName;
        this.linkMoreInfo = linkMoreInfo;
        this.hasFreeParking = hasFreeParking;
        this.hasOutdoor = hasOutdoor;
        this.hasGoodBeer = hasGoodBeer;
        this.teenagerFriendly = teenagerFriendly;
        this.capacity = capacity;
        this.city = city;
        this.distanceFromPublicTransportInKm = distanceFromPublicTransportInKm;
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

    public Boolean getHasOutdoor() {
        return hasOutdoor;
    }

    public void setHasOutdoor(Boolean hasOutdoor) {
        this.hasOutdoor = hasOutdoor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDistanceFromPublicTransportInKm() {
        return distanceFromPublicTransportInKm;
    }

    public void setDistanceFromPublicTransportInKm(double distanceFromPublicTransportInKm) {
        this.distanceFromPublicTransportInKm = distanceFromPublicTransportInKm;
    }
}
