package com.lodz360;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
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
        //Product product = new Product("name",1,2,3);

        //When
        userController.product("Milk",1,2,3);

        //Then
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).dodaj(captor.capture());
        Product actualArgument = captor.getValue();

        assertEquals("Milk", actualArgument.getName());
        assertThat(actualArgument.getName()).isEqualTo("Milk");
        Assert.assertThat(actualArgument, equalTo("Milk"));
        assertTrue("Milk".equals(actualArgument.getName()));

        assertThat(actualArgument).isEqualToComparingFieldByField(new Product("Milk",1,2,3));




    }
}