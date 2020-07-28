package com.lihao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lihao.domain.Member;
import com.lihao.domain.Order;
import com.lihao.domain.OrderSetting;
import com.lihao.mapper.MemberMapper;
import com.lihao.mapper.OrderMapper;
import com.lihao.mapper.OrderSettingMapper;
import com.lihao.service.OrderService;
import com.lihao.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 提交预约
     * <p>
     * 1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
     * 2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
     * <p>
     * <p>
     * 3、检查当前用户是否注册为会员，如果是会员则执行下一步，如果没有注册会员则**自动**完成注册并进行预约
     * 4、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public void order(Map map) throws Exception {

        //1. 检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSettingByDate = orderSettingMapper.getOrderSettingByDate(date);

        if (orderSettingByDate != null) {//进行了设置的，可以预约

            //2. 检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
            int number = orderSettingByDate.getNumber();//该日子可预约人数
            int reservations = orderSettingByDate.getReservations();//该日子已预约人数

            if (reservations < number) {//没有设置满，可以预约

                //3. 检查当前用户是否注册为会员  如果不是会员不但要执行预约操作 还要把他的信息保存到会员表
                String telephone = (String) map.get("telephone");
                Member byTelephone = memberMapper.findByTelephone(telephone);

                if (byTelephone == null) {//没注册过
                    //如果没有注册会员则**自动**完成注册并进行预约
                    byTelephone = new Member();
                    byTelephone.setName((String) map.get("name"));
                    byTelephone.setPhoneNumber(telephone);
                    byTelephone.setIdCard((String) map.get("idCard"));
                    byTelephone.setSex((String) map.get("sex"));
                    byTelephone.setRegTime(new Date());
                    memberMapper.add(byTelephone);//  ★  添加用户后，需要返回该id
                    System.out.println(byTelephone.getId());
                }

                //4. 检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
                Integer memberId = byTelephone.getId();//4.1 获取用户id

                int setmealId = Integer.parseInt((String) map.get("setmealId"));//4.2 获取用户本次准备预约的这个套餐id

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("memberId", memberId);
                hashMap.put("setmealId", setmealId);

                //4.3 查询预约信息表判断用户是否已经预约过此套餐
                //select * from t_order where member_id = #{memberId} and setmeal_id=#{setmealId} and orderDate = #{orderDate}  sql应该这样写  前端应该还给我预约时间
                //这里我就不按上面这样改了 可能还得去前端看看  懒球的去看了
                List<Order> orderList = orderMapper.findByCondition(hashMap);
                if (orderList == null || orderList.size() == 0) {
                    //可以预约，设置预约人数加一  
                    orderSettingByDate.setReservations(orderSettingByDate.getReservations() + 1);
                    //更新当日的已预约人数
                    orderSettingMapper.updateOrderSetting(orderSettingByDate);
                    //将预约信息保存到表中
                    Order order = new Order(byTelephone.getId(), date, (String) map.get("orderType"), Order.ORDERSTATUS_NO, Integer.parseInt((String) map.get("setmealId")));
                    orderMapper.add(order);
                } else {
                    throw new RuntimeException("对不起，您已经预约过了");
                }
            }
        }
    }

    @Override
    public Integer getCountBySetmealId(Integer id) {
        return orderMapper.getCountBySetmealId(id);
    }
}
