package com.yryun.system.scheduleTask;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.yryun.system.utils.Constants;
import com.yryun.system.model.device.Device;
import com.yryun.system.mapper.DeviceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling   //开启定时任务
@EnableAsync    //开启定时任务多线程
@Slf4j
public class YryDeviceService {

    /* Token */
    @Autowired
    YryTokenService yryTokenService;

    /* Mapper */
    @Autowired
    DeviceMapper deviceMapper;

    /* 请求接口 */
    RestTemplate restTemplate = new RestTemplate();

    @Async
    @Scheduled(fixedDelay = 10000)
    public void getDeviceListFromYry() {

        // 设置请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Access-Token", yryTokenService.getToken());
        // 设置请求体信息
        JSONObject requestBody = new JSONObject();

        // 创建请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(requestBody,headers);

        String url = "https://openapi.mp.usr.cn/usrCloud/V6/cusdevice/getCusdevices";

        JSONObject response = restTemplate.postForEntity(url, requestEntity, JSONObject.class).getBody();

        if(response != null){
            int status = response.getIntValue("status");
            if (status == Constants.CustomStatus.CURRENT_SESSION_TIMEOUT) {
                // 当状态码为11，重新请求Token
                yryTokenService.requestYryToken();

                //JSONObject updatedResponse = restTemplate.postForEntity(url, requestEntity, JSONObject.class).getBody();

            }else if(status == Constants.CustomStatus.OK){
                JSONArray cusdeviceResponseDTO = response.getJSONObject("data").getJSONArray("cusdeviceResponseDTO");

                for (int i = 0; i < cusdeviceResponseDTO.size(); i++) {
                    JSONObject jsonObject = cusdeviceResponseDTO.getJSONObject(i);
                    Device device = jsonObject.toJavaObject(Device.class);
                    checkAndInsertOrUpdate(device);
                }

            }

        }else {
            log.warn("请求设备列表为空");
        }

    }


    public void checkAndInsertOrUpdate(Device device) {
        Device existingDevice = findDeviceByDeviceNo(device.getCusdeviceNo());
        if (existingDevice != null) {
            // 在数据库中存在记录，调用更新函数
            updateDevice(existingDevice);
        } else {
            // 在数据库中不存在记录，调用插入函数
            insertDevice(device);
        }
    }

    public Device findDeviceByDeviceNo(String cusdeviceNo) {
        // 在数据库中查找并返回对应的设备记录
        return deviceMapper.findDeviceByDeviceNo(cusdeviceNo);
    }

    public void updateDevice(Device device) {
        // 更新数据库中的设备记录
        deviceMapper.updateDeviceInfo(device);
    }

    public void insertDevice(Device device) {
        // 插入新的设备记录到数据库
        deviceMapper.insertDeviceInfo(device);
        //Long id = device.getId();
    }

}
