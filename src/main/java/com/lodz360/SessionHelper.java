package com.lodz360;

import org.omg.CORBA.Object;
import org.springframework.asm.Attribute;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
/**
 * Created by michalina on 23/08/16.
 */
public class SessionHelper {
    public boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User juzek = (User) session.getAttribute("juzek");
        if (juzek == null) {
            return false;
        } else {
            return true;
        }
    }
    public User returnUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User juzek = (User) session.getAttribute("juzek");
        if (juzek != null) {
            return juzek;
        }
throw new NoSuchUsertException();
    }
}
