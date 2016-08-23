package com.lodz360;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.mockito.Mockito.*;

/**
 * Created by michalina on 22/08/16.
 */

public class UserControllerTest {

    @Test

    public void shouldAddUser(){
        //Given
        UserRepository userRepository = mock(UserRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductFactory productFactory = mock(ProductFactory.class);
        SessionHelper sessionHelper = mock(SessionHelper.class);
        UserController userController = new UserController(productRepository,userRepository, productFactory,sessionHelper);
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        //When
        userController.user("","",0,0,0,request);
        //Then
        verify(userRepository).addUser(any());
        verify(session).setAttribute(eq("juzek"), any());

    }
    @Test
    public void shouldAddProduct(){
        //Given
        UserRepository userRepository = mock(UserRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductFactory productFactory = mock(ProductFactory.class);
        SessionHelper sessionHelper = mock(SessionHelper.class);
        Product product = mock(Product.class);
        User user = mock(User.class);
        when(productFactory.create("Milk", 1.0, 2.0, 3.0)).thenReturn(product);
        UserController userController = new UserController(productRepository, userRepository, productFactory,sessionHelper);

        //When
        userController.product("Milk",1,2,3);
        //Then

        verify(productRepository).dodaj(product);
    }
    @Test
    public void shouldCountAmountOfProduct(){
        //Given
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductFactory productFactory = mock(ProductFactory.class);
        UserRepository userController = mock(UserRepository.class);
        SessionHelper sessionHelper = mock(SessionHelper.class);
        SessionHelper sessionHelper1 = mock(SessionHelper.class);
        UserController userController1 = new UserController(productRepository,userController,productFactory,sessionHelper);



        Model model = mock(Model.class);
        List<Product> products = new ArrayList<>();
        Product product = new Product("Milk",1.,1.,1.);
        products.add(product);

        when(productRepository.getAllProducts()).thenReturn(products);

        User user = new User("","",0,0,0);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("amountOfMilk")).thenReturn("1");
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("juzek")).thenReturn(user);

        when(sessionHelper.isUserLoggedIn(request)).thenReturn(true);


        //When
        String view = userController1.breakfast(request, model);


        //Then
        assertThat(view).isEqualTo("result2");
        verify(model).addAttribute("countProteinInProductYouAte",0.01);
        verify(model).addAttribute("countFatInProductYouAte", 0.01);
        verify(model).addAttribute("countCarbohydratesInProductYouAte",0.01);

    }
    @Test
   public void should(){
       //Given
       ProductRepository productRepository = mock(ProductRepository.class);
       ProductFactory productFactory = mock(ProductFactory.class);
       UserRepository userController = mock(UserRepository.class);
       SessionHelper sessionHelper = mock(SessionHelper.class);
       SessionHelper sessionHelper1 = mock(SessionHelper.class);
       UserController userController1 = new UserController(productRepository,userController,productFactory,sessionHelper);
        HttpServletRequest request = mock(HttpServletRequest.class);
       Model model = mock(Model.class);
       HttpSession session = mock(HttpSession.class);
       User user = new User("","",0,0,0);
       when(request.getSession()).thenReturn(session);
       when(session.getAttribute("juzek")).thenReturn(user);
       //When
       userController1.form1(model, request);
       //Then
       verify(model).addAttribute("user", user);
   }

}
