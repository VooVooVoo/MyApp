/**
 * Created by michalina on 31/07/16.
 */
package com.lodz360;

import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAppWithSpark {
    public static void mainSpark(String[] args) {

        ArrayList<User> usersList = new ArrayList<>();


        Spark.staticFileLocation("/webfiles");
        Spark.get("/breakfast", (request, response) -> {
            String milk = request.queryParams("milk");
            String eggs = request.queryParams("eggs");
            String cereal = request.queryParams("cereal");

            int milkgram = Integer.parseInt(milk);
            int eggsgram = Integer.parseInt(eggs);
            int cerealgrams = Integer.parseInt(cereal);

          /*  Breakfast breakfast = new Breakfast(milkgram, eggsgram, cerealgrams);*/

            Map<String, Object> model = new HashMap();
/*
            model.put("userListAsString", );
*/
            return new ModelAndView (model,"result2.ftl");
        }, new FreeMarkerEngine());


        Spark.get("/signin", (request, response) -> {
            String name = request.queryParams("name");
            String password = request.queryParams("password");
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
            User user = new User(name, password, ageint, weightflo, heightflo);

            usersList.add(user);
          /*  System.out.println(usersList);
            System.out.println(usersList.size());*/

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


    }


}
