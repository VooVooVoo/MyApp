package com.lodz360;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
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
        UserController userController = new UserController(productRepository,userRepository, productFactory);
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
        Product product = mock(Product.class);
        when(productFactory.create("Milk", 1.0, 2.0, 3.0)).thenReturn(product);
        UserController userController = new UserController(productRepository, userRepository, productFactory);

        //When
        userController.product("Milk",1,2,3);

        //Then

        verify(productRepository).dodaj(product);
    }
}