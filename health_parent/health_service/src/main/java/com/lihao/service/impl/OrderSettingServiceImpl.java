package com.lihao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lihao.domain.OrderSetting;
import com.lihao.mapper.OrderSettingMapper;
import com.lihao.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;


    /**
     * 上传excel
     *
     * @param
     */
    @Override
    public void addOrderSetting(List<OrderSetting> orderSettingList) {
        for (OrderSetting orderSetting : orderSettingList) {
            Date orderDate = orderSetting.getOrderDate();

            //1. 根据日期判断改日子是否设置了预约
            OrderSetting order = orderSettingMapper.getOrderSettingByDate(orderDate);
            if (order == null) {
                //没有设置就执行添加设置操作
                orderSettingMapper.addOrderSetting(orderSetting);
            } else {
                order.setNumber(orderSetting.getNumber());
                //如果原本该日期就设置了预约人数，那么就执行更新操作
                orderSettingMapper.updateOrderSetting(order);
            }
        }
    }

    /**
     * 查询当月相关预约信息
     *
     * @param date
     * @return
     */
    @Override
    public List<Map> getMonthOrderSetting(String date) {
        String month_first = date + "-1";//格式2020-1-1
        String month_end = date + "-31";//格式2020-1-31

//        select * from t_ordersetting where orderDate between #{month_first} and #{month_end}
//        数据库设计的 orderDate字段是  date类型
        List<OrderSetting> list = orderSettingMapper.getMonthOrderSettingByDate(month_first, month_end);

        List<Map> mapList = new ArrayList<Map>();//用于封装数据给前台

        for (OrderSetting orderSetting : list) {

//            这里要说一下前端需要的参数：    日期：int类型    改日可预约数：int       该日已预约日数：int
            int dateTody = orderSetting.getOrderDate().getDate();//日期   int类型
            Integer number = orderSetting.getNumber();//获取该日可预约日数    int类型
            Integer reservations = orderSetting.getReservations();//获取该日已预约日数  int类型

            //将数据封装成map    list里面存的是map对象
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("date", dateTody);
            hashMap.put("number", number);
            hashMap.put("reservations", reservations);
            mapList.add(hashMap);
        }
        return mapList;
    }

    /**
     * 修改当天可预约人数
     *
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        Date orderDate = orderSetting.getOrderDate();
        OrderSetting orderSettingByDate = orderSettingMapper.getOrderSettingByDate(orderDate);
        if (orderSettingByDate != null) {
            orderSetting.setId(orderSettingByDate.getId());
            orderSettingMapper.updateOrderSetting(orderSetting);
        }
    }
}
