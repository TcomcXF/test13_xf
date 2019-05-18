package com.erp.controller.user;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.user.SysUser;
import com.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 21:55
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @RequestMapping("ajaxLogin")
    public @ResponseBody Info login(SysUser user, HttpServletRequest request){
        boolean login = userService.login(user);
        if (!login){
            //TODO 'password_error authentication_error
            return new Info(0,"account_error",null);
        }else{
            //TODO 所有人都是超级管理员，以后修改
            HttpSession session = request.getSession();
            List<String> sysPermissionList = (List<String>) session.getAttribute("sysPermissionList");
            if (sysPermissionList==null){
                sysPermissionList=new ArrayList<String>();
            }
            sysPermissionList.add("device:add");
            sysPermissionList.add("device:edit");
            sysPermissionList.add("device:delete");
            sysPermissionList.add("deviceType:add");
            sysPermissionList.add("deviceType:edit");
            sysPermissionList.add("deviceType:delete");

            //这里就按这样写
            sysPermissionList.add("technology:add");
            sysPermissionList.add("technology:edit");
            sysPermissionList.add("technology:delete");
            //部门
            sysPermissionList.add("department:add");
            sysPermissionList.add("department:edit");
            sysPermissionList.add("department:delete");
            //人员
            sysPermissionList.add("employee:add");
            sysPermissionList.add("employee:edit");
            sysPermissionList.add("employee:delete");
            //管理
            sysPermissionList.add("user:add");
            sysPermissionList.add("user:edit");
            sysPermissionList.add("user:delete");


            //schedule
            sysPermissionList.add("custom:add");
            sysPermissionList.add("custom:edit");
            sysPermissionList.add("custom:delete");
            sysPermissionList.add("work:add");
            sysPermissionList.add("work:edit");
            sysPermissionList.add("work:delete");

            sysPermissionList.add("material:add");
            sysPermissionList.add("material:edit");
            sysPermissionList.add("material:delete");

            sysPermissionList.add("materialReceive:add");
            sysPermissionList.add("materialReceive:edit");
            sysPermissionList.add("materialReceive:delete");

            sysPermissionList.add("materialConsume:add");
            sysPermissionList.add("materialConsume:edit");
            sysPermissionList.add("materialConsume:delete");


            sysPermissionList.add("unqualify:add");
            sysPermissionList.add("unqualify:edit");
            sysPermissionList.add("unqualify:delete");

            sysPermissionList.add("pMeasureCheck:add");
            sysPermissionList.add("pMeasureCheck:edit");
            sysPermissionList.add("pMeasureCheck:delete");

            session.setAttribute("sysPermissionList",sysPermissionList);
            return new Info(1,"success",null);

        }
    }

    @RequestMapping("user/find")
    public String userpage(){
        return "user_list";
    }

    @RequestMapping("user/list")
    public @ResponseBody QueryVO userList(int page,int rows){
        return userService.getUserInPage(page,rows);
    }
}
