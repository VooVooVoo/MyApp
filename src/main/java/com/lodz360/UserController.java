package com.lodz360;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.lodz360.NutritionalValue.ZERO;


/**
 * Created by michalina on 10/08/16.
 */
@Controller
public class UserController {


    private ProductRepository productRepository;
    private UserRepository userRepository;
    private ProductFactory productFactory;
    private SessionHelper sessionHelper;

    @Autowired
    public UserController(ProductRepository productRepository, UserRepository userRepository, ProductFactory productFactory, SessionHelper sessionHelper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productFactory = productFactory;
        this.sessionHelper = sessionHelper;
    }

    @RequestMapping("/meals")
    public String meals() {
        return "meals";
    }


    @RequestMapping("/dodawanie")
    public String form4() {
        return "addnew";
    }

    @RequestMapping("/addnew")
    public String product(@RequestParam(value = "name") String name,
                          @RequestParam(value = "protein") Double protein,
                          @RequestParam(value = "fat") Double fat,
                          @RequestParam(value = "carbohydrates") Double carbohydrates, HttpServletRequest request) {

        if (sessionHelper.isUserLoggedIn(request) == false) {
            return "redirect:/";
        }

        Product product = productFactory.create(name, protein, fat, carbohydrates);
        productRepository.dodaj(product);
        return "redirect:/sniadanie";
    }

    @RequestMapping("/logowanie")
    public String form2(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password,
                        HttpServletRequest request) {


        try {
            User user = userRepository.getUserByNameAndPassword(name, password);
            HttpSession session = request.getSession();
            session.setAttribute("juzek", user);
        } catch (NoSuchUsertException e) {
            String errorMessage = encode("Login and password are incorrect!");
            return "redirect:/logowanie?error=" + errorMessage;
        }

        return "redirect:/sniadanie";
    }

    @RequestMapping("/")
    public String form3() {
        return "main";
    }

    @RequestMapping("/rejestracja")
    public String form() {
        return "signup";
    }


    @RequestMapping("/signup")
    public String user(@RequestParam(value = "name") String name,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "age") Integer age,
                       @RequestParam(value = "weight") Integer weight,
                       @RequestParam(value = "height") Integer height,
                       HttpServletRequest request) {
        User user = new User(name, password, age, weight, height);
        HttpSession session = request.getSession();
        session.setAttribute("juzek", user);
        try {
            userRepository.addUser(user);
        } catch (NoUniqueUserName e) {
            String errorMessage = encode("Login has already been used!");
            return "redirect:/rejestracja?error=" + errorMessage;
        }

        return "redirect:/bmi";
    }


    @RequestMapping("/bmi")
    public String form(Model model, HttpServletRequest request) {


        if (sessionHelper.isUserLoggedIn(request) == false) {
            return "redirect:/";
        }

        model.addAttribute("user", sessionHelper.returnUser(request));
        try {
            model.addAttribute("checkbmi", sessionHelper.returnUser(request).checkbmi());
        } catch (BMIToLowException toLow) {
            model.addAttribute("checkbmi", "Wpisałeś głupoty, albo jesteś tak chudy, że już nic Ci nie pomoże");
        } catch (BMIToHighException toHigh) {
            model.addAttribute("checkbmi", "Za duże BMI");
        } catch (BMIException e) {
            e.printStackTrace();
        }
        model.addAttribute("allUsers", userRepository.getAllUsers());
        return "result";
    }

    @RequestMapping("/sniadanie")
    public String form1(Model model, HttpServletRequest request) {

        if (sessionHelper.isUserLoggedIn(request) == false) {
            return "redirect:/";
        }


        model.addAttribute("products", productRepository.getAllProducts());
        model.addAttribute("user", sessionHelper.returnUser(request));
        return "breakfast";
    }


    @RequestMapping("/breakfast")
    public String breakfast(HttpServletRequest request, Model model) {

        NutritionalValue whatYouAte = ZERO;

        for (Product product : productRepository.getAllProducts()) {
            String parameterName = "amountOf" + product.getName();
            String parameterValue = request.getParameter(parameterName);
            if (parameterValue != null && !parameterValue.equals("")) {
                double amountOfProduct = Double.parseDouble(parameterValue);
                whatYouAte.addMeal(product, amountOfProduct);
            }

        }

        if (sessionHelper.isUserLoggedIn(request) == false) {
            return "redirect:/";
        }

        model.addAttribute("countProteinInProductYouAte", whatYouAte.getProteinsInGrams());
        model.addAttribute("countFatInProductYouAte", whatYouAte.getFatInGrams());
        model.addAttribute("countCarbohydratesInProductYouAte", whatYouAte.getCarbsInGrams());
        model.addAttribute("user", sessionHelper.returnUser(request));
        return "result2";
    }

    private String encode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}

