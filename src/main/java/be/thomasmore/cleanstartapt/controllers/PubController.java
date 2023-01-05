package be.thomasmore.cleanstartapt.controllers;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import be.thomasmore.cleanstartapt.model.Pub;
import be.thomasmore.cleanstartapt.repositories.PubRepository;

public class PubController {
    private Logger logger = LoggerFactory.getLogger(PubController.class);

    @Autowired
    private PubRepository pubRepository;
    private final String[] pubNames = {"Het Anker","'t Schuurke","De Zwaan","De ton"};
    private final Pub [] pubs = {
            new Pub(1,"Bobbejaanland", "http://www.bobbejaanland.be", true, false, false, true,20),
            new Pub(2,"Plopsa", "http://www.plopsa.be", false, true, true, true, 30),
            new Pub(3,"Walibi", "http://www.walibi.be", false, false, true, true,120),
            new Pub(4,"Legoland", "https://www.legoland.de/en/", false, false, true, true,80),
            new Pub(5,"Phantasialand", "https://www.phantasialand.de/en/", false, true, true, true, 90),
            new Pub(6,"Efteling", "https://www.efteling.nl", false, true, true, true, 66)
    };

    @GetMapping({"/pubdetails","/pubdetails/{pubName}"})
    public String pubDetails(Model model, @PathVariable(required = false) String pubName){
        model.addAttribute("pubName", pubName );
        return "pubDetails";
    }
    @GetMapping({"/pubdetailsbyindex","/pubdetailsbyindex/","/pubdetailsbyindex/{pubindex}"})
    public String pubDetailsByIndex(Model model, @PathVariable(required = false) String pubindex){
        Pub pub = null;

        if(pubindex !=null && Integer.parseInt(pubindex)%1 == 0 && Integer.parseInt(pubindex)>= 0 && Integer.parseInt(pubindex)< pubs.length )
        {
            //get pub object
            pub = pubs[Integer.parseInt(pubindex)];
        }

        int prevIndex = Integer.parseInt(pubindex)-1;
        if(prevIndex<0){
            prevIndex = pubs.length - 1;
        }

        int nextIndex = Integer.parseInt(pubindex)+1;
        if(nextIndex >pubs.length-1)
        {
            nextIndex = 0;
        }

        model.addAttribute("pub",pub);
        model.addAttribute("prevIndex", prevIndex);
        model.addAttribute("nextIndex", nextIndex);
        return "pubdetailsbyindex";
    }

    @GetMapping("/publist")
    public String pubList(Model model){
        logger.info("pubList");
        Iterable<Pub> pubs = pubRepository.findAll();
        long nrOfpubs = pubRepository.count();
        model.addAttribute("pubs", pubs);
        model.addAttribute("nrOfpubs", nrOfpubs);
        model.addAttribute("showFilters", false);
        return "publist";
    }

    @GetMapping( "/publist/hasgoodbeer/{hasGoodBeerValue}")
    public String pubListHasGoodBeer (Model model, @PathVariable Optional<String> hasGoodBeerValue){
        boolean hasGoodBeer = hasGoodBeerValue.get().equalsIgnoreCase("yes") ? true : false;
        boolean allPubs = false;
        Iterable<Pub> pubs;

        if(!hasGoodBeerValue.isPresent()){
            pubs = pubRepository.findAll();
            allPubs = true;
        }
        else{
            pubs = pubRepository.findByHasGoodBeer(hasGoodBeer);
        }

        model.addAttribute("pubs", pubs);
        model.addAttribute("allPubs", allPubs ? "yes" : "no");
        model.addAttribute("hasGoodBeer", hasGoodBeer);
        return "publist";
    }

    @GetMapping( "/publist/hasfreeparking/{hasFreeParking}")
    public String pubListHasFreeParking (Model model, @PathVariable Optional<String> hasFreeParkingValue){
        boolean hasFreeParking = hasFreeParkingValue.get().equalsIgnoreCase("yes") ? true : false;
        boolean allPubs = false;
        Iterable<Pub> pubs;

        if(!hasFreeParkingValue.isPresent()){
            pubs = pubRepository.findAll();
            allPubs = true;
        }
        else{
            pubs = pubRepository.findByHasFreeParking(hasFreeParking);
        }

        model.addAttribute("pubs", pubs);
        model.addAttribute("allPubs", allPubs ? "yes" : "no");
        model.addAttribute("hasFreeParking", hasFreeParking);
        return "publist";
    }

    @GetMapping("/publist/capacity/{filter}")
    public String pubListCapacity(Model model,
                                @PathVariable String filter) {
        Iterable<Pub> pubs;
        switch (filter) {
            case "S":
                pubs = pubRepository.findByCapacityBetween(0, 50);
                break;
            case "M":
                pubs = pubRepository.findByCapacityBetween(50, 100);
                break;
            case "L":
                pubs = pubRepository.findByCapacityGreaterThan(100);
                break;
            default:
                pubs = pubRepository.findAll();
                filter = null;
                break;
        }
        model.addAttribute("pubs", pubs);
        model.addAttribute("capacity", filter);
        return "publist";
    }


    @GetMapping({"/pubdetailsbyid", "/pubdetailsbyid/{id}"})
    public String pubDetailsById(Model model, @PathVariable(required = false) Integer id){
        model.addAttribute("pub", pubRepository.findById(id).get());

        Pub pub = pubRepository.findById(id).get();;

        Iterable<Pub> pubsAll = pubRepository.findAll();
        int minId = pub.getId();
        int maxId = pub.getId();

        for ( Pub p :pubsAll) {
            minId = p.getId() < minId ? p.getId() : minId;
            maxId = p.getId() > maxId ? p.getId() : maxId;
        }

        int prevIndex = id - 1;
        if(prevIndex< minId){
            prevIndex = maxId;
        }

        int nextIndex =id + 1;
        if(nextIndex > maxId)
        {
            nextIndex = minId;
        }

        model.addAttribute("pub",pub);
        model.addAttribute("prevIndex", prevIndex);
        model.addAttribute("nextIndex", nextIndex);

        return "pubdetails";
    }

    @GetMapping({"/publist/filter"})
    public String pubListWithFilter(Model model,
                                      @RequestParam(required = false) Integer minCapacity,
                                      @RequestParam(required = false) Integer maxCapacity,
                                      @RequestParam(required = false) Integer maxDistance,
                                      @RequestParam(required = false) String filterFood,
                                      @RequestParam(required = false) String filterIndoor,
                                      @RequestParam(required = false) String filterOutdoor) {
        logger.info(String.format("pubListWithFilter -- min=%d, max=%d, distance=%d, filterFood=%s, filterIndoor=%s, , filterOutdoor=%s",
                minCapacity, maxCapacity, maxDistance, filterFood, filterIndoor, filterIndoor));

        List<Pub> pubs = pubRepository.findByFilter(minCapacity, maxCapacity, maxDistance,
                filterStringToBoolean(filterFood), filterStringToBoolean(filterIndoor), filterStringToBoolean(filterOutdoor));

        model.addAttribute("pubs", pubs);
        model.addAttribute("nrOfpubs", pubs.size());
        model.addAttribute("showFilters", true);
        model.addAttribute("minCapacity", minCapacity);
        model.addAttribute("maxCapacity", maxCapacity);
        model.addAttribute("maxDistance", maxDistance);
        model.addAttribute("filterFood", filterFood);
        model.addAttribute("filterIndoor", filterIndoor);
        model.addAttribute("filterOutdoor", filterOutdoor);

        return "publist";
    }
    private Boolean filterStringToBoolean(String filterString) {
        return (filterString == null || filterString.equals("all")) ? null : filterString.equals("yes");
    }

}
