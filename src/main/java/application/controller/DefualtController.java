package application.controller;

import application.dao.OrderDao;
import application.dao.StaffDao;
import application.dao.ToolDao;
import application.dto.*;
import application.entity.Order;
import application.entity.Staff;
import application.entity.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by AZ on 2016/4/26.
 */

@RestController
public class DefualtController {

    @Autowired
    StaffDao staffDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    ToolDao toolDao;

    private String sysTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    @RequestMapping(value = "/verify_password", method = RequestMethod.POST)
    public LoginDto verifyPassword (String username, String password, HttpServletRequest request) {
        System.out.println("Executing verifyPassword for username= "+username);
        LoginDto loginDto = new LoginDto("False","");
        try {
            Staff staff = staffDao.findByStaffID(username);
            if (password.equals(staff.getPassword())) {
                loginDto.setResult("True");
                loginDto.setPosition(staff.getPosition());
                HttpSession session = request.getSession();
                session.setAttribute("StaffID",username);
                session.setAttribute("Position",staff.getPosition());
                session.setAttribute("Login",1);
            }
        } catch (Exception e) {
            System.out.println("Wrong username or password :"+e.toString());
        }
        return loginDto;
    }

    @RequestMapping(value = "/whoAmI", method = RequestMethod.GET)
    public Staff whoAmI (HttpServletRequest request) {
        System.out.println("Executing whoAmI");
        HttpSession session = request.getSession();
        String staffId = (String) session.getAttribute("StaffID");
        Staff staff = new Staff();
        staff.setResult("False");
        try {
            staff= staffDao.findByStaffID(staffId);
            staff.setResult("True");
        } catch (Exception e) {
            System.out.println("Fail to get session: "+e.toString());
        }
        return staff;
    }

    @RequestMapping(value = "/clientReservation", method = RequestMethod.GET)
    public ResultDto clientReservation (String location, String date, String title, String description, HttpServletRequest request) {
        System.out.println("Executing clientReservation");
        HttpSession session = request.getSession();
        String staffID = (String) session.getAttribute("StaffID");
        //生成orderID
        Random random = new Random();
        int randomNum = random.nextInt(100);
        String orderID = staffID + randomNum;
        Order order = new Order();
        order.setOrderID(orderID);
        order.setClientID(staffID);
        order.setLocation(location);
        order.setDescription(description);
        order.setTitle(title);
        order.setOnsiteTime(date);
        order.setReservationTime(sysTime());
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/orderInformation", method = RequestMethod.GET)
    public OrderInformationDto orderInformation (String orderID, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String staffID = (String) session.getAttribute("StaffID");
        String position = (String) session.getAttribute("Position");
        System.out.println("Searching orderInformation for a "+position+" whose staffID = "+staffID);
        OrderInformationDto orderInformationDto = new OrderInformationDto();
        if ("Client".equals(position)) {
            List<Order> orders = orderDao.findByClientID(staffID);
            orderInformationDto.setOrders(orders);
            orderInformationDto.setResult("True");
        }
        if ("Manager".equals(position)) {
            List<Order> orders = (List<Order>) orderDao.findAll();
            orderInformationDto.setOrders(orders);
            orderInformationDto.setResult("True");
        }
        if ("Engineer".equals(position)) {
            List<Order> orders = (List<Order>) orderDao.engineerQuery(staffID);
            orderInformationDto.setOrders(orders);
            orderInformationDto.setResult("True");
        }
        return orderInformationDto;
    }

    @RequestMapping(value = "/chooseOrderID", method = RequestMethod.GET)
    public ResultDto chooseOrderID (String orderID, HttpServletRequest request) {
        System.out.println("Executing chooseOrderID");
        HttpSession session = request.getSession();
        session.setAttribute("orderID",orderID);
        return new ResultDto("True");
    }

    /*从session取orderID返回该Order*/
    @RequestMapping(value = "/currentOrder", method = RequestMethod.GET)
    public Order currentOrder (HttpServletRequest request) {
        System.out.println("Executing currentOrder");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        order.setResult("True");
        return order;
    }

    /*经理Accept*/
    @RequestMapping(value = "/managerConfirmAccept", method = RequestMethod.GET)
    public ResultDto managerConfirmAccept (HttpServletRequest request) {
        System.out.println("Executing managerConfirmAccept");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        String staffID = (String) session.getAttribute("StaffID");
        Order order = orderDao.findByOrderID(orderID);
        order.setManagerID(staffID);
        order.setRequestAcceptTime(sysTime());
        orderDao.save(order);
        return new ResultDto("True");
    }

    /*Engineer confirm*/
    @RequestMapping(value = "/engineerConfirmAccept", method = RequestMethod.GET)
    public ResultDto engineerConfirmAccept (HttpServletRequest request) {
        System.out.println("Executing engineerConfirmAccept");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        String staffID = (String) session.getAttribute("StaffID");
        Order order = orderDao.findByOrderID(orderID);
        order.setEngineerConfirmTime(sysTime());
        order.setDutyEngineerID(staffID);
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/searchTools", method = RequestMethod.GET)
    public ToolDto searchTools (String key) {
        System.out.println("Executing searchTools");
        List<Tool> tools = new ArrayList<Tool>();
        tools = toolDao.keyQuery(key);
        ToolDto toolDto = new ToolDto();
        toolDto.setResult("True");
        toolDto.setTools(tools);
        return toolDto;
    }

    /*根据toolID返回Tool*/
    @RequestMapping(value = "/accurateDescription", method = RequestMethod.GET)
    public Tool accurateDescription (String inputID) {
        System.out.println("Finding a tool information");
        Tool tool = toolDao.findByToolID(inputID);
        return tool;
    }

    /*将该tool加入当前订单*/
    @RequestMapping(value = "/confirmReservingTools", method = RequestMethod.GET)
    public ResultDto confirmReservingTools (String inputID, HttpServletRequest request) {
        System.out.println("Confirming Resvation");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        String currentTools = order.getToolsReservationID();
        if (currentTools == null) {
            currentTools = inputID;
        } else {
            currentTools += "|";
            currentTools += inputID;
        }
        order.setToolsReservationID(currentTools);
        orderDao.save(order);
        return new ResultDto("True");
    }

    /*结束借工具，写入当前时间和归还日期*/
    @RequestMapping(value = "/dateForReservation", method = RequestMethod.GET)
    public ResultDto dateForReservation (HttpServletRequest request, String date) {
        System.out.println("Executing dateForReservation");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        order.setPredictedReturnTime(date);
        order.setToolsReservationTime(sysTime());
        orderDao.save(order);
        return new ResultDto("True");
    }

    /*search engineer*/
    @RequestMapping(value = "/otherEngineerSearch", method = RequestMethod.GET)
    public SearchEngineerDto otherEngineerSearch (String name) {
        System.out.println("Searching other engineers");
        SearchEngineerDto searchEngineerDto = new SearchEngineerDto();
        List<Staff> engineers = staffDao.searchForEngineers(name);
        searchEngineerDto.setResult("True");
        searchEngineerDto.setEngineers(engineers);
        return searchEngineerDto;
    }

    @RequestMapping(value = "/otherEngineerConfirm", method = RequestMethod.GET)
    public ResultDto otherEngineerConfirm (String engineerID,HttpServletRequest request) {
        System.out.println("Confirming enrollment");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        String currentEngineers = order.getOtherEngineer();
        if (currentEngineers == null) {
            currentEngineers = engineerID;
        } else {
            currentEngineers += "|";
            currentEngineers += engineerID;
        }
        order.setOtherEngineer(currentEngineers);
        orderDao.save(order);
        return new ResultDto("True");
    }

    /*结束加人，计算工具价值总和，返回value="High" or "Low"*/
    @RequestMapping(value = "/finishEnrollEngineer", method = RequestMethod.GET)
    public String finishEnrollEngineer (HttpServletRequest request) {
        System.out.println("Executing finishEnrollEngineer");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        String tools = order.getToolsReservationID();
        String[] toolArray = tools.split("\\|");
        Integer totalAmount = 0;
        for (String str : toolArray) {
            totalAmount += Integer.parseInt(str);
        }
        if (totalAmount >= 100 ) {
            order.setValue("High");
            orderDao.save(order);
            return "{\"value\":\"High\"}";
        }
        order.setValue("Low");
        orderDao.save(order);
        return "{\"value\":\"Low\"}";
    }
}
