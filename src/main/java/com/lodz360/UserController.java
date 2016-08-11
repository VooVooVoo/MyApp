package com.lodz360;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michalina on 10/08/16.
 */
@Controller
public class UserController {

    List<User> usersList = new ArrayList<>();
    private ProductRepository productRepository;

    @Autowired
    public UserController(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        model.addAttribute("products", productRepository.getAllProducts());
        return "breakfast";
    }


    @RequestMapping("/breakfast")
    public String breakfast(HttpServletRequest request, Model model) {

        double countProteinInProductYouAte = 0;
        double countFatInProductYouAte = 0;
        double countCarbohydratesInProductYouAte = 0;

        for (Product product: productRepository.getAllProducts()) {
            String parameterName = "amountOf" + product.getName();
            String parameterValue = request.getParameter(parameterName);
            if (parameterValue != null && !parameterValue.equals("")) {
                double amountOfProduct = Double.parseDouble(parameterValue);
                countProteinInProductYouAte += (product.getProtain() * amountOfProduct);
                countFatInProductYouAte += (product.getFat() *  amountOfProduct);
                countCarbohydratesInProductYouAte += (product.getCarbohydrates() * amountOfProduct);
            }

        }

        // TODO correct spelling in attribute names
        model.addAttribute("countProteinInProductYueAte", countProteinInProductYouAte);
        model.addAttribute("countFatInProductYueAte", countFatInProductYouAte);
        model.addAttribute("countCarbohydratesInProductYueAte", countCarbohydratesInProductYouAte);
        return "result2";
    }

    private Product getProductByName(String productName) throws NoSuchProductException {
        return productRepository.getProductByName(productName);
    }

}

