package be.thomasmore.cleanstartapt.controllers;

import be.thomasmore.cleanstartapt.model.Pub;
import be.thomasmore.cleanstartapt.repositories.ArtistRepository;
import be.thomasmore.cleanstartapt.repositories.PubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Controller
public class HomeController {
    private final int mySpecialNumber = 567;
    private final String[] pubNames = {"Het Anker","'t Schuurke","De Zwaan","De ton"};
    private final Pub [] pubs = {
            new Pub(1,"Bobbejaanland", "http://www.bobbejaanland.be", true, false, false, true,20),
            new Pub(2,"Plopsa", "http://www.plopsa.be", false, true, true, true, 30),
            new Pub(3,"Walibi", "http://www.walibi.be", false, false, true, true,120),
            new Pub(4,"Legoland", "https://www.legoland.de/en/", false, false, true, true,80),
            new Pub(5,"Phantasialand", "https://www.phantasialand.de/en/", false, true, true, true, 90),
            new Pub(6,"Efteling", "https://www.efteling.nl", false, true, true, true, 66)
    };

    @Autowired
    private PubRepository pubRepository;
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "about";
    }
    @GetMapping("/pay")
    public String pay(Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime dateToday = LocalDateTime.now();
        LocalDateTime datePlus30Days = dateToday.plusDays(30);
        Boolean weekend = false;
        if(dateToday.getDayOfWeek() == DayOfWeek.SATURDAY || dateToday.getDayOfWeek() == DayOfWeek.SUNDAY )
        {
            weekend = true;
        }

        model.addAttribute("dateOfToday", dtf.format(dateToday));
        model.addAttribute("datePlus30", dtf.format(datePlus30Days));
        model.addAttribute("weekend", weekend);
        return "pay";
    }
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
         Iterable<Pub> pubs = pubRepository.findAll();
         model.addAttribute("pubs", pubs);
        model.addAttribute("allPubs", "yes");

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

    @GetMapping("/artistlist")
    public String getArtistList(Model model){
        model.addAttribute("artistList", artistRepository.findAll());
        return "artistList";
    }

    @GetMapping("/artistdetails/{id}")
    public String getArtistDetails(Model model, @PathVariable(required = false) Integer id){
        model.addAttribute("artist", artistRepository.findById(id).get());
        return "artistdetails";
    }

}
