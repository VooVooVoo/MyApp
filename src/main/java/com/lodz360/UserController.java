package com.lodz360;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by michalina on 10/08/16.
 */
@Controller
public class UserController {


    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public UserController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

/*
    @RequestMapping("/")
    public String form3() {
        return "check";
    }

    @RequestMapping("/check")
    public String form4(Model model) {
        userRepository.addUser(User user);
        model.addAttribute("allUsers", userRepository.addUser(user));

        return null;
    }
*/


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
        /*userRepository.addUser(user);*/



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
        model.addAttribute("allUsers", userRepository.addUser(user));
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

        model.addAttribute("countProteinInProductYouAte", countProteinInProductYouAte);
        model.addAttribute("countFatInProductYouAte", countFatInProductYouAte);
        model.addAttribute("countCarbohydratesInProductYouAte", countCarbohydratesInProductYouAte);
        return "result2";
    }

    /*private Product getProductByName(String productName) throws NoSuchProductException {
        return productRepository.getProductByName(productName);
    }*/

}

