package com.lodz360;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



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
    @RequestMapping("/logowanie")
    public String form2() {
        return "login";
    }

    @RequestMapping("/login")
    public String user(@RequestParam(value = "name") String name,
                       @RequestParam(value = "password") String password,
                       HttpServletRequest request,
                       Model model) {

        String userName=request.getParameter("name");
        String userPassword = request.getParameter("password");

        User user  = userRepository.getUserByNameAndPassword(userName, userPassword);


          if (user != null ){
              HttpSession session = request.getSession();
              session.setAttribute("juzer", user);
          } else {  return "redirect:/login";

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
                       HttpServletRequest request,
                       Model model) {

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
        return "redirect:/login";
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
    public String breakfast(HttpServletRequest request, Model model) {  //co tu się dzieje?

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

    /*private Product getProductByName(String productName) throws NoSuchProductException {
        return productRepository.getProductByName(productName);
    }*/

}

