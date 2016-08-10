package com.lodz360;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by michalina on 10/08/16.
 */
@Controller
public class UserController {

    Product milk = new Product("Milk",3.4,3,0);
    Product egg = new Product("Egg",13, 11, 0);
    ArrayList<User> usersList = new ArrayList<>();

    Product cereal = new Product("Cereal", 8, 0.4, 84);
    @RequestMapping("/")
    public String form() {
        return "signin";
    }



    @RequestMapping("/signin")
    public String user(@RequestParam(value = "name") String name,
                          @RequestParam(value = "age") Integer age,
                          @RequestParam(value = "weight") Integer weight,
                           @RequestParam(value = "height") Integer height, Model model) {


        User user = new User(name, age, weight, height);
        usersList.add(user);

        model.addAttribute("user", user);
        try{
        model.addAttribute("checkbmi", user.checkbmi());
        } catch (BMIToLowException toLow) {
            model.addAttribute("checkbmi", "Wpisałeś głupoty, albo jesteś tak chudy, że już nic Ci nie pomoże");
        } catch (BMIToHighException toHigh) {
            model.addAttribute("checkbmi", "Za duże BMI");
        } catch (BMIException e) {
            e.printStackTrace();
        }
        return "result";
    }
    @RequestMapping("/main")
    public String form1() {
        return "breakfast";
    }



    @RequestMapping("/breakfast")
    public String breakfast(@RequestParam(value = "amountOfMilk"/*,required = false, defaultValue = "0"*/) Double amountOfMilk,
                       @RequestParam(value = "amountOfEgg",required = false, defaultValue = "0") Double amountOfEgg,
                       @RequestParam(value = "amountOfCereal",required = false, defaultValue = "0") Double amountOfCereal, Model model) {

        double countProteinInProductYueAte = (milk.getProtain() * amountOfMilk) + (egg.getProtain() * amountOfEgg) + (cereal.getProtain() * amountOfCereal);
        double countFatInProductYueAte = (milk.getFat() * amountOfMilk) + (egg.getFat() * amountOfEgg) + (cereal.getFat() * amountOfCereal);
        double countCarbohydratesInProductYueAte = (milk.getCarbohydrates() * amountOfMilk) + (egg.getCarbohydrates() * amountOfEgg) + (cereal.getCarbohydrates() * amountOfCereal);

        model.addAttribute("countProteinInProductYueAte", countProteinInProductYueAte);
        model.addAttribute("countFatInProductYueAte", countFatInProductYueAte);
        model.addAttribute("countCarbohydratesInProductYueAte", countCarbohydratesInProductYueAte);


        return "result2";



    }

}

