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
    @RequestMapping("/")
    public String form() {
        return "signin";
    }
     ArrayList<User> usersList = new ArrayList<>();

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
}
/*

 Spark.get("/signin", (request, response) -> {
        String name = request.queryParams("name");
        String age = request.queryParams("age");
        String weight = request.queryParams("weight");
        String height = request.queryParams("height");

        int ageint;
        try {
            ageint = Integer.parseInt(age);
        } catch (NumberFormatException ex) {
            ageint = -1;
            response.redirect("/signin.html?error=Podales+zly+wiek");
        }

        float weightflo = Float.parseFloat(weight);
        float heightflo = Float.parseFloat(height);
        User user = new User(name, ageint, weightflo, heightflo);

        usersList.add(user);
          */
/*  System.out.println(usersList);
            System.out.println(usersList.size());*//*


        Map<String, Object> model = new HashMap();
        model.put("user", user);
        try {
            model.put("checkbmi", user.checkbmi());
        } catch (BMIToLowException toLow) {
            model.put("checkbmi", "Wpisałeś głupoty, albo jesteś tak chudy, że już nic Ci nie pomoże");
        } catch (BMIToHighException toHigh) {
            model.put("checkbmi", "Za duże BMI");
        } catch (BMIException e) {
            e.printStackTrace();
        }
        model.put("userListAsString", usersList.toString());
        return new ModelAndView(model, "result.ftl");
    }, new FreeMarkerEngine());
*/

