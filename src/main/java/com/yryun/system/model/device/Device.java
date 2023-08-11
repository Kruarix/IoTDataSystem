package com.yryun.system.model.device;

import lombok.Data;

@Data
public class Device {

    private Long id;

    private String cusdeviceNo;

    private String cusdeviceName;

    private String address;

    private String position;

    private Integer onlineOffline;

    private Integer scadaAlarm;

    private Integer cusdeviceConfigStatus;

}
