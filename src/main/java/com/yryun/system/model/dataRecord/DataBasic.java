package com.yryun.system.model.dataRecord;

import lombok.Data;

@Data
public class DataBasic {

    private Long id;

    private Long deviceId;

    private Integer recordTime;

    private String flow1;

    private String flow2;

    private String speed;

    private String power;


}
