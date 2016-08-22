package com.lodz360;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
        UserController userController = new UserController(productRepository,userRepository);
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
        UserController userController = new UserController(productRepository, userRepository);
        Product product = new Product("name",1,2,3);
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productRepository.getAllProducts()).thenReturn(products);


        //When
        userController.product("name",1,2,3);
        //Then
        verify(productRepository).dodaj(any());

    }
}