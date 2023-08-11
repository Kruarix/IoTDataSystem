package com.yryun.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.yryun.system.model.dataRecord.DataPosition;
import com.yryun.system.model.requestData.DataPointInfo;
import com.yryun.system.model.dataRecord.DataBasic;
import com.yryun.system.model.dataRecord.DataECMO;
import com.yryun.system.model.dataRecord.DataMonitor;
import com.yryun.system.model.requestData.DataPushRequest;
import com.yryun.system.service.UtilsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UtilsServiceImpl implements UtilsService {



    /* 解析数据 */
    @Override
    public void getDataPoint(DataPushRequest request) {

        log.info("接收推送数据: " + JSON.toJSONString(request));

        // 获取设备编号
        String cusdeviceNo = request.getData().getCusdeviceNo();
        // 获取数据点列表
        List<DataPointInfo> dataPoints = request.getData().getDataPoints();

        // 获取时间戳
        Integer time = request.getData().getDataPoints().get(0).getTime();


        //声明Monitor记录表
        DataMonitor dataMonitor = new DataMonitor();
        //声明ECMO记录表
        DataECMO dataECMO = new DataECMO();


        //根据编号搜索设备，确定设备存在

        //将数据传入数据记录表
        for(DataPointInfo dataPointInfo : dataPoints){
            // 获取采集模板
            String slaveName = dataPointInfo.getSlaveName();
            switch (slaveName) {
                case "流量采集模板" -> {
                    initBasicData(dataPointInfo, cusdeviceNo);

                }
                case "定位信息模板" -> {
                    initPositionData(dataPointInfo, cusdeviceNo);
                }
            }

            dataPointInfo.getVariableName();
        }


        //若不存在则发出警告WARN，尝试重新获取设备列表，更新状态。

    }



    //工具方法
    //流量采集模板方法
    public void initBasicData(DataPointInfo dataPointInfo, String cusdeviceNo){
        //声明基础记录表
        DataBasic dataBasic = new DataBasic();
        String variableName = dataPointInfo.getVariableName();
        String value = dataPointInfo.getValue();
        switch (variableName) {
            case "流量I" -> {
                dataBasic.setDeviceId(Long.valueOf(cusdeviceNo));

            }
            case "流量II" -> {

            }
            case "转速" -> {

            }
            case "电量" -> {

            }
        }


    }

    //定位信息模板方法
    public void initPositionData(DataPointInfo dataPointInfo, String cusdeviceNo){
        //声明定位记录表
        DataPosition dataPosition = new DataPosition();



    }

    //Monitor信息模板方法
    public void initMonitorData(DataPointInfo dataPointInfo){
        //声明定位记录表
        DataMonitor dataMonitor = new DataMonitor();



    }

    //ECMO信息模板方法
    public void initECMOData(DataPointInfo dataPointInfo){
        //声明定位记录表
        DataECMO dataECMO = new DataECMO();



    }


}
