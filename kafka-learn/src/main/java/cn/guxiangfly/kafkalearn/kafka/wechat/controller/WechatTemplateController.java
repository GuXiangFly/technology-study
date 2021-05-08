package cn.guxiangfly.kafkalearn.kafka.wechat.controller;

import cn.guxiangfly.kafkalearn.kafka.wechat.common.BaseResponseVO;
import cn.guxiangfly.kafkalearn.kafka.wechat.conf.WechatTemplateProperties;
import cn.guxiangfly.kafkalearn.kafka.wechat.service.WechatTemplateService;
import cn.guxiangfly.kafkalearn.kafka.wechat.utils.FileUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1")
public class WechatTemplateController {

    @Autowired
    private WechatTemplateProperties properties;

    @Autowired
    private WechatTemplateService wechatTemplateService;

    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public BaseResponseVO getTemplate(){

        WechatTemplateProperties.WechatTemplate wechatTemplate = wechatTemplateService.getWechatTemplate();

        Map<String, Object> result = new HashMap<>();
        result.put("templateId",wechatTemplate.getTemplateId());
        result.put("template", FileUtils.readFile2JsonArray(wechatTemplate.getTemplateFilePath()));

        return BaseResponseVO.success(result);
    }

    @RequestMapping(value = "/template/result", method = RequestMethod.GET)
    public BaseResponseVO templateStatistics(
            @RequestParam(value = "templateId", required = false)String templateId){

        JSONObject statistics = wechatTemplateService.templateStatistics(templateId);

        return BaseResponseVO.success(statistics);
    }

    @RequestMapping(value = "/template/report", method = RequestMethod.POST)
    public BaseResponseVO dataReported(
            @RequestBody String reportData){

        wechatTemplateService.templateReported(JSON.parseObject(reportData));

        return BaseResponseVO.success();
    }

}
