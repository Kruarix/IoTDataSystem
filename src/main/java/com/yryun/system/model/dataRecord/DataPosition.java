package com.yryun.system.model.dataRecord;

import lombok.Data;

@Data
public class DataPosition {

    private Long id;

    private Long deviceId;

    private Integer recordTime;

    private String longitude;

    private String latitude;

}
