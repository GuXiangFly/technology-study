package cn.guxiangfly.controller;

import cn.guxiangfly.bean.UserAddress;
import cn.guxiangfly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping("/initOrder")
    public List<UserAddress> initOrder(String userId){
        /**
         * 初始化订单时，调用远程服务获取地址列表
         */
        return orderService.initOrder(userId);
    }
}