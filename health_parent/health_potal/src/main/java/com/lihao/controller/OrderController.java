package com.lihao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lihao.domain.Order;
import com.lihao.service.OrderService;
import com.lihao.service.SetmealService;
import com.lihao.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
public class OrderController {

    @Reference
    private OrderService orderService;

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 提交预约        保存用户订单信息
     *
     * @param map
     * @return
     */
    @RequestMapping("/order/submit")
    public ResultEntity submit(@RequestBody Map<Object,Object> map) {
        try {
            //1. 获取用户输入的手机号
            String telephone = (String) map.get("telephone");

            //2. 从redis中获取缓存的验证码  失效时间是5分钟
            String randomCode_redis = jedisPool.getResource().get(telephone);

            //3. 获取用户输入的验证码
            String validateCode_web = (String) map.get("validateCode");

            //4. 校验手机验证码
            if (randomCode_redis == null || !randomCode_redis.equals(validateCode_web)) {
                return ResultEntity.failed("验证码不正确");
            }


            //5. 校验成功
            //5.1 保存预约人相关信息
            map.put("orderType", Order.ORDERTYPE_WEIXIN);//微信预约

            orderService.order(map);// 保存用户订单信息===================================
            return ResultEntity.successNoData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed("预约失败");
        }
    }
}
