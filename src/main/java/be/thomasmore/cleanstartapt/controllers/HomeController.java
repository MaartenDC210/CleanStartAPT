package be.thomasmore.cleanstartapt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {
    private final int mySpecialNumber = 567;

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
        model.addAttribute("dateOfToday", dtf.format(dateToday));
        model.addAttribute("datePlus30", dtf.format(datePlus30Days));
        return "pay";
    }
    @GetMapping({"/venuedetails","/venuedetails/{venueName}"})
    public String venueDetails(Model model, @PathVariable(required = false) String venueName){
        model.addAttribute("venueName", (venueName != null) ? venueName : "No venue chosen");
        return "venueDetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model){
        return "venuelist";
    }
}
