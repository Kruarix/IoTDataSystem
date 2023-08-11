package com.yryun.system.service.impl;

import com.yryun.system.model.device.Device;
import com.yryun.system.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {


    @Override
    public List<Device> getDeviceListAdmin() {
        return null;
    }

    @Override
    public List<Device> getDeviceListUser() {
        return null;
    }
}
