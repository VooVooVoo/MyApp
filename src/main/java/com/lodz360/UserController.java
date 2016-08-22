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

    @RequestMapping("/meals")
    public String meals() {return "meals";}


    @RequestMapping("/dodawanie")
    public String form4() {return "addnew";}

    @RequestMapping("/addnew")
    public String product (@RequestParam(value = "name")String name,
                           @RequestParam(value = "protein")Integer protein,
                           @RequestParam(value = "fat") Integer fat,
                           @RequestParam(value = "carbohydrates") Integer carbohydrates){

        Product product = new Product(name, protein,fat,carbohydrates);
        productRepository.dodaj(product);
        return "redirect:/sniadanie";
    }

    @RequestMapping("/logowanie")
    public String form2(@RequestParam(value = "error",required = false) String error,
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
            session.setAttribute("juzek",user);
        } catch (NoSuchUsertException e){
            String errorMessage = encode("Sign up, no user like that!");
            return "redirect:/logowanie?error="+errorMessage;
        }

          return "redirect:/sniadanie";
      }

    @RequestMapping("/")
    public String form3() {
        return "main";
    }

    @RequestMapping("/rejestracja")
    public String form() {
        return "signin";
    }


    @RequestMapping("/signin")
    public String user(@RequestParam(value = "name") String name,
                       @RequestParam(value = "password")String password,
                       @RequestParam(value = "age") Integer age,
                       @RequestParam(value = "weight") Integer weight,
                       @RequestParam(value = "height") Integer height,
                       HttpServletRequest request) {

        HttpSession session = request.getSession();



        User user = new User(name, password, age, weight, height);

        session.setAttribute("juzek", user);

        userRepository.addUser(user);
        return "redirect:/bmi";
    }



        @RequestMapping("/bmi")
        public String form(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User juzek = (User) session.getAttribute("juzek");
        if(juzek == null) {
        return "redirect:/logowanie";
        }

        model.addAttribute("user", juzek);
        try {
            model.addAttribute("checkbmi", juzek.checkbmi());
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

        HttpSession session = request.getSession();
        User juzek = (User) session.getAttribute("juzek");
        if(juzek == null) {
            return "redirect:/";
        }

        model.addAttribute("products", productRepository.getAllProducts());
        model.addAttribute("user", juzek);
        return "breakfast";
    }


    @RequestMapping("/breakfast")
    public String breakfast(HttpServletRequest request, Model model) {


        double countProteinInProductYouAte = 0;
        double countFatInProductYouAte = 0;
        double countCarbohydratesInProductYouAte = 0;



        for (Product product: productRepository.getAllProducts()) {
            String parameterName = "amountOf" + product.getName();
            String parameterValue = request.getParameter(parameterName);  //??
            if (parameterValue != null && !parameterValue.equals("")) {
                double amountOfProduct = Double.parseDouble(parameterValue);
                countProteinInProductYouAte += (product.getProtain() * amountOfProduct);
                countFatInProductYouAte += (product.getFat() *  amountOfProduct);
                countCarbohydratesInProductYouAte += (product.getCarbohydrates() * amountOfProduct);
            }

        }
        HttpSession session = request.getSession();
        User juzek = (User) session.getAttribute("juzek");
        if(juzek == null) {
            return "redirect:/";
        }

        model.addAttribute("countProteinInProductYouAte", countProteinInProductYouAte);
        model.addAttribute("countFatInProductYouAte", countFatInProductYouAte);
        model.addAttribute("countCarbohydratesInProductYouAte", countCarbohydratesInProductYouAte);
        model.addAttribute("user",juzek);
        return "result2";
    }

    private String encode(String text){
     try{
         return URLEncoder.encode(text, "UTF-8");
     }catch(UnsupportedEncodingException e){
         throw new RuntimeException(e);
        }
    }

}

