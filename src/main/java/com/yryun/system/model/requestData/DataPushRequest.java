package com.yryun.system.model.requestData;

import lombok.Data;

import java.util.List;

@Data
public class DataPushRequest {

    // 数据点类型
    private String type;
    // 数据
    private DataArray data;

    @Data
    public static class DataArray{
        // 设备编号
        private String cusdeviceNo;
        // 数据
        private List<DataPointInfo> dataPoints;
    }




}
