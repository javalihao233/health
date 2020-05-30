package com.lihao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lihao.service.MemberService;
import com.lihao.service.SetmealService;
import com.lihao.util.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 5. 报表模块
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private MemberService memberService;

    @Reference
    private SetmealService setmealService;

    /**
     * 会员折线图
     *
     * @return
     */
    @RequestMapping("/getMemberReport")
    public ResultEntity getMemberReport() {
        try {
            //************************************
            //1. 获取第前12个月当月的月份
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -12);
            System.out.println(calendar.getTime());

            //2. 获取前第12个月——今天所处的月份  一个一个遍历出来并将其存放在集合listMonth里
            List<String> listMonth = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                calendar.add(Calendar.MONTH, 1);  //每次遍历，月份+1  遍历12次 就得出12个月份了
                listMonth.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
            }
            //************************************


            //************************************
            //1. 根据月份集合查询各月的会员数量
            List<Integer> listCount = memberService.getMemberByMonth(listMonth);
            //************************************



            //4. 封装数据返回前台
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("months", listMonth);
            hashMap.put("memberCount", listCount);
            return ResultEntity.successWithData(hashMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 因为上面用到了  Calendar
     * @param args
     */
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance);// ava.util.GregorianCalendar[time=1590832776146,areFieldsSe
        System.out.println(instance.getTime());//Sat May 30 17:56:29 CST 2020      getTime返回一个表示此 Calendar 时间值（从历元至现在的毫秒偏移量）的 Date 对象

        //add(int field, int amount)  根据日历的规则，为给定的日历字段添加或减去指定的时间量
        instance.add(Calendar.MONTH, -12); // 当前时间减去12个月
        System.out.println(instance.getTime());//Thu May 30 17:59:36 CST 2019

        //get(int field)  返回给定日历字段的值
        int i = instance.get(Calendar.MONTH);//4    注意从0开始
        System.out.println(i);

        //set(int field, int value)   将给定的日历字段设置为给定值。
        instance.set(Calendar.MONTH,10);
        System.out.println(instance.get(Calendar.MONTH));//10
    }

//    /**
//     * 套餐占比饼状图
//     *
//     * @return
//     */
//    @RequestMapping("/getSetmealReport")
//    public ResultEntity getSetmealReport() {
//        HashMap<String, Object> hashMap = new HashMap<>();
//
//        //1. 获取所有套餐名称
//        List<String> list = setmealService.getAllSetmealName();
//
//        //2. 获取套餐名称及数量
//        List<Map<String, Object>> mapList = new ArrayList<>();
//        Map<String, Object> setmealMap = setmealService.getSetmealAndCount();
//
//        //3. 封装前端需要的数据
//        hashMap.put("setmealNames", list);
//        hashMap.put("setmealCount", mapList);
//
//
//        return ResultEntity.successWithData(hashMap);
//    }
}
