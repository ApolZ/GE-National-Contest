package application.controller;

import application.dao.*;
import application.dto.*;
import application.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import application.service.ExportExcel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static application.service.ExportExcel.export;

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
    @Autowired
    ToolboxDao toolboxDao;
    @Autowired
    OnsiteDao onsiteDao;
    @Autowired
    ParameterDao parameterDao;

    private String sysTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private String sysDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    /*Get orders for different position*/
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
        if ("ToolKeeper".equals(position)) {
            List<Order> orders = (List<Order>) orderDao.toolKeeperQuery(staffID);
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
        System.out.println("Confirming Reservation");
        Tool tool = toolDao.findByToolID(inputID);
        tool.setStatus("OnUsing");
        toolDao.save(tool);
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
        Parameter parameter = parameterDao.findOne(1);
        Integer threshold = Integer.parseInt(parameter.getThreshold());
        String tools = order.getToolsReservationID();
        String[] toolArray = tools.split("\\|");
        Integer totalAmount = 0;
        for (String str : toolArray) {
            Tool tool = toolDao.findByToolID(str);
            totalAmount += Integer.parseInt(tool.getPrice());
        }
        System.out.println("Total amount of this tools is: "+totalAmount);
        if (totalAmount >= threshold ) {
            order.setValue("High");
            orderDao.save(order);
            return "{\"value\":\"High\"}";
        }
        order.setValue("Low");
        order.setTaskBeginTime(sysTime());
        orderDao.save(order);
        return "{\"value\":\"Low\"}";
    }

    /*LowValue, write whether to express */
    @RequestMapping(value = "/expressInfo", method = RequestMethod.GET)
    public ResultDto expressInfo (HttpServletRequest request, String result) {
        System.out.println("Executing expressInfo");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        order.setWhetherExpress(result);
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/toolsInCurrentOrder", method = RequestMethod.GET)
    public ToolDto toolsInCurrentOrder (HttpServletRequest request) {
        System.out.println("Checking tools in current order");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        String tools = order.getToolsReservationID();
        String[] toolArray = tools.split("\\|");
        List<Tool> toolList = new ArrayList<Tool>();
        for (String str : toolArray) {
            Tool tool = toolDao.findByToolID(str);
            if ("OnUsing".equals(tool.getStatus())) {
                toolList.add(tool);
            }
        }
        ToolDto toolDto = new ToolDto();
        toolDto.setResult("True");
        toolDto.setTools(toolList);
        return toolDto;
    }

    @RequestMapping(value = "/engineerReturnTools", method = RequestMethod.GET)
    public ResultDto engineerReturnTools (String returnStatus, String toolID) {
        System.out.println("Executing engineerReturnTools");
        Tool tool = toolDao.findByToolID(toolID);
        if ("Lost".equals(returnStatus) || "Broken".equals(returnStatus)) {
            tool.setBrokenOrLostDate(sysDate());
        }
        tool.setStatus(returnStatus);
        toolDao.save(tool);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/engineerFinish", method = RequestMethod.GET)
    public ResultDto engineerFinish (HttpServletRequest request) {
        System.out.println("Executing engineerFinish");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        order.setTaskEndTime(sysTime());
        order.setTaskStatus("True");
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/clientConfirmFinish", method = RequestMethod.GET)
    public ResultDto clientConfirmFinish (HttpServletRequest request) {
        System.out.println("Executing clientConfirmFinish");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        order.setClientConfirmTime(sysTime());
        order.setClientStatus("True");
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/managerConfirmFinish", method = RequestMethod.GET)
    public ResultDto managerConfirmFinish (HttpServletRequest request) {
        System.out.println("Executing managerConfirmFinish");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        order.setManagerConfirmTime(sysTime());
        order.setManagerStatus("True");
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/keeperConfirmAccept", method = RequestMethod.GET)
    public ResultDto keeperConfirmAccept (HttpServletRequest request) {
        System.out.println("Executing keeperConfirmAccept");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        String staffID = (String) session.getAttribute("StaffID");
        Order order = orderDao.findByOrderID(orderID);
        order.setKeeperConfirmTime(sysTime());
        order.setToolkeeperID(staffID);
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/scanBox", method = RequestMethod.GET)
    public Toolbox scanBox (String toolboxID, HttpServletRequest request) {
        System.out.println("Scanning toolbox");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        if (order.getTaskBeginTime()==null) {
            order.setTaskBeginTime(sysTime());
            orderDao.save(order);
        }
        session.setAttribute("toolboxID",toolboxID);
        Toolbox toolbox = toolboxDao.findByToolboxID(toolboxID);
        return toolbox;
    }

    @RequestMapping(value = "/onsiteAddress", method = RequestMethod.GET)
    public ResultDto onsiteAddress (String address, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("address", address);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/scanStaffOrTool", method = RequestMethod.GET)
    public ScanDto scanStaffOrTool (String inputID, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ScanDto scanDto = new ScanDto();

        Staff staff = staffDao.findByStaffID(inputID);
        if (staff != null) {
            System.out.println("Got an engineer. Writing scanEngineer into session");
            session.setAttribute("scanEngineer", inputID);
            scanDto.setResult("True");
            scanDto.setName(staff.getName());
            scanDto.setPicture(staff.getPicture());
            scanDto.setPosition(staff.getPosition());
            return scanDto;
        }

        Tool tool = toolDao.findByToolID(inputID);
        if (tool != null) {
            System.out.println("Got a tool. Writing scanTools into session");
            String scanTools = (String) session.getAttribute("scanTools");
            if (scanTools == null) {
                session.setAttribute("scanTools", inputID);
            } else {
                scanTools += "|";
                scanTools += inputID;
                session.setAttribute("scanTools", scanTools);
            }
            scanDto.setResult("True");
            scanDto.setPicture(tool.getPicture());
            scanDto.setName(tool.getName());
            return scanDto;
        }
            scanDto.setResult("False");
            return scanDto;
    }

    @RequestMapping(value = "/confirmBorrowTools", method = RequestMethod.GET)
    public ResultDto confirmBorrowTools (HttpServletRequest request) {
        HttpSession session = request.getSession();
        String scanEngineer = (String) session.getAttribute("scanEngineer");
        String staffID = (String) session.getAttribute("StaffID");
        String orderID = (String) session.getAttribute("orderID");
        String toolboxID = (String) session.getAttribute("toolboxID");
        String address = (String) session.getAttribute("address");

        String scanTools = (String) session.getAttribute("scanTools");
        try {
            String[] tools = scanTools.split("\\|");
            for (String str: tools) {
                Onsite onsite = new Onsite();
                onsite.setEngineerID(scanEngineer);
                onsite.setToolkeeperID(staffID);
                onsite.setOrderID(orderID);
                onsite.setToolboxID(toolboxID);
                onsite.setAddress(address);
                onsite.setLendingTime(sysTime());
                onsite.setToolID(str);
                onsiteDao.save(onsite);
            }
            return new ResultDto("True");
        } catch (Exception e) {
            return new ResultDto("False");
        }
    }

    @RequestMapping(value = "/scanReturnedTools", method = RequestMethod.GET)
    public ToolDto scanReturnedTools (String inputID) {
        System.out.println("Scanning returned toolID or staffID");
        ToolDto toolDto = new ToolDto();

        Tool tool = toolDao.findByToolID(inputID);
        if (tool != null) {
            System.out.println("Got a tool");
            List<Tool> toolList = new ArrayList<Tool>();
            toolList.add(tool);
            toolDto.setResult("True");
            toolDto.setTools(toolList);
            return toolDto;
        }

        Staff staff = staffDao.findByStaffID(inputID);
        if (staff != null) {
            System.out.println("Got an engineer");
            List<Tool> toolList = new ArrayList<Tool>();
            List<Onsite> onsiteList = onsiteDao.findByEngineerID(inputID);
            for (Onsite onsite: onsiteList) {
                if (onsite.getReturnTime()==null) {
                    Tool eachTool = toolDao.findByToolID(onsite.getToolID());
                    toolList.add(eachTool);
                }
            }
            toolDto.setResult("True");
            toolDto.setTools(toolList);
            return toolDto;
        }

        toolDto.setResult("False");
        return toolDto;
    }

    @RequestMapping(value = "/toolsReturn", method = RequestMethod.GET)
    public ResultDto toolsReturn (String returnStatus, String toolID, String remarks) {
        Onsite onsite = onsiteDao.unreturnedRowByToolID(toolID);
        if (! "Return".equals(returnStatus)) {
            remarks = remarks.replace('+',' ');
            onsite.setRemarks(remarks);
        }
        onsite.setReturnStatus(returnStatus);
        onsite.setReturnTime(sysTime());
        onsiteDao.save(onsite);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/keeperFinish", method = RequestMethod.GET)
    public ResultDto keeperFinish (String result, HttpServletRequest request) {
        System.out.println("Executing keeperFinish");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        Order order = orderDao.findByOrderID(orderID);
        order.setTaskEndTime(sysTime());
        order.setTaskStatus(result);
        orderDao.save(order);
        return new ResultDto("True");
    }

    @RequestMapping(value = "/onsiteInfoToExcel", method = RequestMethod.GET)
    public void exportExcel (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Exporting excel file");
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        List<Onsite> onsites = onsiteDao.findByOrderID(orderID);
        try {
            response.reset();
            response.setContentType("msexcel");
            response.setHeader("Content-disposition","attachment; filename=test.xls");
            OutputStream out = response.getOutputStream();
            export(onsites, out);
            out.close();
        } catch (Exception e) {}
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultDto logout (HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResultDto("True");
    }

    @RequestMapping(value = "/setNewThreshold", method = RequestMethod.GET)
    public ResultDto setNewThreshold (String threshold) {
        try {
            Parameter parameter = parameterDao.findOne(1);
            parameter.setThreshold(threshold);
            parameterDao.save(parameter);
        } catch (Exception e) {
            return new ResultDto("False");
        }
        return new ResultDto("True");
    }

    /*search distinct kind of tools*/
    @RequestMapping(value = "/searchToolsForRegister", method = RequestMethod.GET)
    public ToolDto searchToolsForRegister (String key) {
        System.out.println("Executing searchToolsForRegister");
        List<Tool> tools = new ArrayList<Tool>();
        tools = toolDao.keyQueryWithSingleName(key);
        ToolDto toolDto = new ToolDto();
        toolDto.setResult("True");
        toolDto.setTools(tools);
        return toolDto;
    }

    @RequestMapping(value = "/loadAddingToolInfo", method = RequestMethod.GET)
    public Tool loadAddingToolInfo (String inputID) {
        Tool tool = toolDao.findByToolID(inputID);
        tool.setPurchaseDate(sysTime());
        return tool;
    }

    /*Creating several tools at one time*/
    @RequestMapping(value = "/createNewTools", method = RequestMethod.GET)
    public ResultDto createNewTools (String name, String purchaseDate, String serviceLife, String description, String toolcenterID, String minQuant, String price, String number) {
        System.out.println("Creating "+number +" "+ name);
        try {
            List<Tool> toolList = toolDao.findByName(name);
            Long toolID = 0L;
            for (Tool tool: toolList) {
                if (Long.parseLong(tool.getToolID())>toolID) {
                    toolID = Long.parseLong(tool.getToolID());
                }
            }
            for (int i=0;i<Integer.parseInt(number);i++) {
                Tool tool = new Tool();
                toolID += 1;
                tool.setToolID(toolID.toString());
                tool.setName(name);
                tool.setPurchaseDate(purchaseDate);
                tool.setServiceLife(serviceLife);
                tool.setPicture(toolList.get(0).getPicture());
                tool.setStatus("Good");
                tool.setDescription(description);
                tool.setToolcenterID(toolcenterID);
                tool.setMinQuant(minQuant);
                tool.setPrice(price);
                toolDao.save(tool);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDto("False");
        }
        return new ResultDto("True");
    }

    @RequestMapping(value = "/toolsOverview", method = RequestMethod.GET)
    public ToolStorageDto toolsOverview () {
        ToolStorageDto toolStorageDto = new ToolStorageDto();
        toolStorageDto.setTotalNumber(toolDao.countTotalNumber().toString());
        //goodNumber is the sum of Good and OnUsing
        Integer goodNumber = 0;
        goodNumber += toolDao.countExactStatus("Good");
        goodNumber += toolDao.countExactStatus("OnUsing");
        toolStorageDto.setGoodNumber(goodNumber.toString());
        toolStorageDto.setBrokenNumber(toolDao.countExactStatus("Broken").toString());
        toolStorageDto.setLostNumber(toolDao.countExactStatus("Lost").toString());
        return toolStorageDto;
    }

    /*Storage details of a kind of tools*/
    @RequestMapping(value = "/showDetailedToolStorage", method = RequestMethod.POST)
    public ToolStorageDto showDetailedToolStorage (String toolID) {
        Tool tool = toolDao.findByToolID(toolID);
        String toolName = tool.getName();
        ToolStorageDto toolStorageDto = new ToolStorageDto();
        toolStorageDto.setPicture(tool.getPicture());
        toolStorageDto.setDescription(tool.getDescription());
        toolStorageDto.setPrice(tool.getPrice());
        toolStorageDto.setTotalNumber(toolDao.countTotalNumber(toolName).toString());
        //goodNumber is the sum of Good and OnUsing
        Integer goodNumber = 0;
        goodNumber += toolDao.countExactStatus("Good",toolName);
        goodNumber += toolDao.countExactStatus("OnUsing",toolName);
        toolStorageDto.setGoodNumber(goodNumber.toString());
        toolStorageDto.setBrokenNumber(toolDao.countExactStatus("Broken",toolName).toString());
        toolStorageDto.setLostNumber(toolDao.countExactStatus("Lost",toolName).toString());
        return toolStorageDto;
    }

    /*check the loss statistic of given year */
    @RequestMapping(value = "/chenYearLoss", method = RequestMethod.GET)
    public YearStatisticsDto yearStatistics (String year) {
        YearStatisticsDto yearStatisticsDto = new YearStatisticsDto();
        List<Tool> brokenList =toolDao.findbrokenToolForGivenYear(year);
        List<Tool> lostList = toolDao.findLostToolForGivenYear(year);
        Integer brokenValue = 0;
        Integer brokenAmount =brokenList.size();
        Integer lostValue = 0;
        Integer lostAmount = lostList.size();
        for (Tool tool : brokenList) {
            brokenValue += Integer.parseInt( tool.getPrice());
        }
        for (Tool tool : lostList) {
            lostValue += Integer.parseInt( tool.getPrice());
        }
        Integer lossAmount = brokenAmount + lostAmount;
        Integer lossValue = brokenValue + lostValue;
        yearStatisticsDto.setBrokenPieces(brokenAmount.toString());
        yearStatisticsDto.setBrokenValue(brokenValue.toString());
        yearStatisticsDto.setLostPieces(lostAmount.toString());
        yearStatisticsDto.setLostValue(lostValue.toString());
        yearStatisticsDto.setLossPieces(lossAmount.toString());
        yearStatisticsDto.setLossValue(lossValue.toString());
        return yearStatisticsDto;
    }

    @RequestMapping(value = "/currentOrderOnsiteInfo", method = RequestMethod.GET)
    public OnsiteInfoDto currentOrderOnsiteInfo (HttpServletRequest request) {
        HttpSession session = request.getSession();
        String orderID = (String) session.getAttribute("orderID");
        List<Onsite> onsiteList = onsiteDao.findByOrderID(orderID);
        Onsite eg = onsiteList.get(0);
        OnsiteInfoDto onsiteInfoDto = new OnsiteInfoDto();
        onsiteInfoDto.setOrderID(orderID);
        onsiteInfoDto.setAddress(eg.getAddress());
        onsiteInfoDto.setToolboxID(eg.getToolboxID());
        onsiteInfoDto.setToolkeeperID(eg.getToolkeeperID());
        onsiteInfoDto.setList(onsiteList);
        onsiteInfoDto.setResult("True");
        return onsiteInfoDto;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Integer test () {
        List<Tool> toolList = toolDao.findbrokenToolForGivenYear("2016");
        return toolList.size();
    }
}
