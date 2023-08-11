package com.yryun.system.model.requestData;

import lombok.Data;

@Data
public class DataPointInfo {

    // 数据点Id
    private int dataPointId;
    // 从机名称
    private String slaveName;
    // 变量名称
    private String variableName;
    // 数值
    private String value;
    // 错误码
    private int err;
    // 时间戳(秒)
    private Integer time;

}
