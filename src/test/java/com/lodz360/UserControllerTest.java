package com.lodz360;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;

import javax.servlet.http.HttpSession;

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
        User user = new User ("","",0,0,0);
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        //When
        userController.user("","",0,0,0,request);
        //Then
        verify(userRepository).addUser(any());
        verify(session).setAttribute(eq("juzek"), any());

    }



}