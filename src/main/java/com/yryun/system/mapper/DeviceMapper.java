package com.yryun.system.mapper;

import com.yryun.system.model.device.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {

    List<Device> getDeviceListAdmin();

    List<Device> getDeviceListUser();

    void updateDeviceInfo(Device device);

    void insertDeviceInfo(Device device);

    Device findDeviceByDeviceNo(String cusdeviceNo);



}
