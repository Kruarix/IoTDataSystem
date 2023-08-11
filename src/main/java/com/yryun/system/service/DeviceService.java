package com.yryun.system.service;

import com.yryun.system.model.device.Device;

import java.util.List;

public interface DeviceService {

    List<Device> getDeviceListAdmin();

    List<Device> getDeviceListUser();




}
