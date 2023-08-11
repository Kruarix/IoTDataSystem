package com.yryun.system.controller;


import com.yryun.system.mapper.DeviceMapper;
import com.yryun.system.model.device.Device;
import com.yryun.system.service.DeviceService;
import com.yryun.system.utils.ApiResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    /*测试*/
    @Autowired
    DeviceMapper deviceMapper;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ApiResponse<List<Device>> getDeviceList(){

//        List<Device> deviceList = deviceService.getDeviceListFromYry();
//
//        log.info("设备列表"+deviceList);
        List<Device> deviceList = deviceMapper.getDeviceListAdmin();


        return ApiResponse.success(deviceList);
    }






}
