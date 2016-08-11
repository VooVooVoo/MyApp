package com.lodz360;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michalina on 10/08/16.
 */
@Controller
public class UserController {

    List<User> usersList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    public UserController() {
        productList.add(new Product("Milk", 3.4, 3, 0));
        productList.add(new Product("Egg", 13, 11, 0));
        productList.add(new Product("Cereal", 8, 0.4, 84));
        productList.add(new Product("Butter", 0.9, 81, 0.1));
    }

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
        try {
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
    public String form1(Model model) {
        model.addAttribute("products", productList);
        return "breakfast";
    }


    @RequestMapping("/breakfast")
    public String breakfast(@RequestParam(value = "amountOfProduct", required = false) List<Double> amountsOfProducts,
                            @RequestParam(value = "productName", required = false) List<String> productsName,
                            Model model) {

        double countProteinInProductYouAte = 0;
        double countFatInProductYouAte = 0;
        double countCarbohydratesInProductYouAte = 0;

        for (int i = 0; i < productsName.size(); i++) {
            Product product = getProductByName(productsName.get(i));
            countProteinInProductYouAte += (product.getProtain() * amountsOfProducts.get(i));
            countFatInProductYouAte += (product.getFat() *  amountsOfProducts.get(i));
            countCarbohydratesInProductYouAte += (product.getCarbohydrates() * amountsOfProducts.get(i));
        }

        // TODO correct spelling in attribute names
        model.addAttribute("countProteinInProductYueAte", countProteinInProductYouAte);
        model.addAttribute("countFatInProductYueAte", countFatInProductYouAte);
        model.addAttribute("countCarbohydratesInProductYueAte", countCarbohydratesInProductYouAte);


        return "result2";


    }

    private Product getProductByName(String productName) throws NoSuchProductException {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        throw new NoSuchProductException();
    }
/*
    public String getProducts(HttpServletRequest request) {

        for (Product product : productList) {
            String parameterName = "amountOf" + product.getName();
            String amountAsString = request.getParameter(parameterName);
        }

        return "aaaaa";
    }*/

}

