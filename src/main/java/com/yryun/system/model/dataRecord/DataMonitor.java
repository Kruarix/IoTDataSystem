package com.yryun.system.model.dataRecord;

import lombok.Data;

@Data
public class DataMonitor {

    private Long id;

    private Long deviceId;

    private Integer recordTime;

    private String bloodPressure;

    private String bloodOxygen;

    private String heartRate;

}
