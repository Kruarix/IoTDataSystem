package com.yryun.system.controller;

import com.yryun.system.model.requestData.DataPushRequest;
import com.yryun.system.service.UtilsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/usr")
public class UtilsController {

    @Resource
    private UtilsService utilsService;


//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public JSONObject hello(){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("o", "k");
//        return jsonObject;
//    }

    @RequestMapping(value = "/obtains",method = RequestMethod.GET)
    public String getVerify(@RequestParam("verify") String verify){
        return verify;
    }

    @RequestMapping(value = "/obtains",method = RequestMethod.POST)
    public void getDataPoint(@RequestBody DataPushRequest request){

        utilsService.getDataPoint(request);
    }







}
