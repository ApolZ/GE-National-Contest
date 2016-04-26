package application.controller;

import application.dto.StaffDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by AZ on 2016/4/26.
 */

@RestController
public class DefualtController {

    @RequestMapping(value = "/verify_password", method = RequestMethod.POST)
    public String verifyPassword(String username, String password, HttpServletRequest request) {
        System.out.println("Executing verifyPassword for username= "+username);
        HttpSession session = request.getSession();
        //db operation, if true:
        session.setAttribute("StaffID",username);
        session.setAttribute("Login",1);
        return "{\"Result\":\"True\",\"Position\":\"Client\"}";
        //if false return "{\"Result\":\"False\"};
    }

    @RequestMapping(value = "/whoAmI", method = RequestMethod.GET)
    public StaffDto whoAmI (HttpServletRequest request) {
        System.out.println("Executing whoAmI");
        HttpSession session = request.getSession();
        String staffId = (String) session.getAttribute("StaffID");
        //用staffId查以下信息并填入StaffDto实例
        StaffDto staffDto = new StaffDto();
        staffDto.setName(staffId);//其实不是
        staffDto.setPhoneNumber("13166383666");
        staffDto.setPicture("/static/images/staff/r1.jpg");
        staffDto.setRemarks("Good guy");
        return staffDto;
    }

}
