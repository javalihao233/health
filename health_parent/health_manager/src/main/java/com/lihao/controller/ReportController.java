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
            HashMap<String, Object> hashMap = new HashMap<>();

            //1. 获取前12个月的月份
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -12);
            System.out.println(calendar.getTime());

            //2. 获取前12个月到今天的月份  并将其存放在集合listMonth里
            List<String> listMonth = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                calendar.add(Calendar.MONTH, 1);
                listMonth.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
            }

            //3. 根据月份集合查询各月的会员数量
            List<Integer> listCount = memberService.getMemberByMonth(listMonth);

            //4. 封装数据返回前台
            hashMap.put("months", listMonth);
            hashMap.put("memberCount", listCount);
            return ResultEntity.successWithData(hashMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
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
