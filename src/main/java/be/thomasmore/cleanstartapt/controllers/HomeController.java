package be.thomasmore.cleanstartapt.controllers;

import be.thomasmore.cleanstartapt.model.Pub;
import be.thomasmore.cleanstartapt.repositories.PubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
public class HomeController {
    private final int mySpecialNumber = 567;
    private final String[] pubNames = {"Het Anker","'t Schuurke","De Zwaan","De ton"};
    private final Pub [] pubs = {
            new Pub(1,"Bobbejaanland", "http://www.bobbejaanland.be", true, false, false, true),
            new Pub(2,"Plopsa", "http://www.plopsa.be", false, true, true, true),
            new Pub(3,"Walibi", "http://www.walibi.be", false, false, true, true),
            new Pub(4,"Legoland", "https://www.legoland.de/en/", false, false, true, true),
            new Pub(5,"Phantasialand", "https://www.phantasialand.de/en/", false, true, true, true),
            new Pub(6,"Efteling", "https://www.efteling.nl", false, true, true, true)
    };

    @Autowired
    private PubRepository pubRepository;

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
//        model.addAttribute("pubNames", pubNames);
//        return "publist";

         Iterable<Pub> pubs = pubRepository.findAll();
         model.addAttribute("pubs", pubs);
         return "publist";

    }

    @GetMapping({"/pubdetailsbyid", "/pubdetailsbyid/{id}"})
    public String pubDetailsById(Model model, @PathVariable(required = false) Integer id){
        model.addAttribute("pub", pubRepository.findById(id).get());
        return "pubdetails";
    }

}
