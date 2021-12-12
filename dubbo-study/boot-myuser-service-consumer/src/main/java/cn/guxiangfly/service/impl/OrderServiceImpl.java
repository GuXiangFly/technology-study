package cn.guxiangfly.service.impl;

import cn.guxiangfly.bean.UserAddress;
import cn.guxiangfly.service.OrderService;
import cn.guxiangfly.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id："+userId);
        //1.查询用户的收货地址
        List<UserAddress> addresses = userService.getUserAddressList(userId);
        for (UserAddress address : addresses) {
            System.out.println(address.getUserAddress());
        }
        return addresses;
    }
}