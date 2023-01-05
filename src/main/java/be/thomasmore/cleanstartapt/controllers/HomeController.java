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
}
