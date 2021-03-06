package com.erp.controller.device;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;
import com.erp.bean.device.Info;
import com.erp.service.device.DeviceService;
import com.erp.utils.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: yyc
 * @Date: 2019/5/17 21:13
 */
@Controller
@RequestMapping("deviceList")
public class DeviceListController {
    @Autowired
    private DeviceService deviceService;


    @RequestMapping("list")
    public @ResponseBody
    QueryVO getDeviceInPage(int page, int rows){
        return deviceService.getDeviceInPage(page, rows);
    }

    @RequestMapping("add_judge")
    public @ResponseBody
    Map<String,String> addDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("device:add",request);
    }
    @RequestMapping("add")
    public String toAdd(){
        return "deviceList_add";
    }
    @RequestMapping("insert")
    public @ResponseBody Info insert(Device device){
        int res = deviceService.addNew(device);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else if (res==2){
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }else{
            return new Info(res,"更新失败，请稍后再来！",null);
        }
    }


    @RequestMapping("edit_judge")
    public @ResponseBody Map<String,String>  editDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("device:edit",request);
    }
    @RequestMapping("edit")
    public String toEdit(){
        return "deviceList_edit";
    }
    @RequestMapping("update")
    public @ResponseBody Info update(Device device){
        int res = deviceService.update(device);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }


    @RequestMapping("delete_judge")
    public @ResponseBody Map<String,String>  deleteDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("device:delete",request);
    }

    @RequestMapping("delete_batch")
    public @ResponseBody Info deleteByIDs(String[] ids){
        int res = deviceService.deleteByIDs(ids);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }

    @RequestMapping("search_device_by_deviceId")
    public @ResponseBody QueryVO searchDeviceByDeviceId(String searchValue,int page,int rows){
        QueryVO<Device> deviceQueryVO = deviceService.searchDeviceByDeviceId(searchValue, page, rows);
        return deviceQueryVO;
    }

    @RequestMapping("search_device_by_deviceName")
    public @ResponseBody QueryVO searchDeviceByDeviceName(String searchValue,int page,int rows){
        return deviceService.searchDeviceByDeviceName(searchValue,page,rows);
    }

    @RequestMapping("search_device_by_deviceTypeName")
    public @ResponseBody QueryVO searchDeviceByDeviceTypeName(String searchValue,int page,int rows){
        return deviceService.searchDeviceByDeviceTypeName(searchValue,page,rows);
    }
    @RequestMapping("get_data")
    public @ResponseBody
    List<Device> getData(){
        return deviceService.getAllData();
    }
    @RequestMapping("get/{id}")
    public @ResponseBody Device getByDeviceTypeId(@PathVariable("id") String id){
        return deviceService.getByDeviceId(id);
    }
    @UpdateMethod("deviceList")
    @RequestMapping("update_note")
    public @ResponseBody Info updateNote(String deviceId,String note){

        int res = deviceService.updateNoteById(deviceId,note);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }











}
