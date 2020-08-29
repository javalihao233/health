package com.lihao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lihao.domain.CheckItem;
import com.lihao.service.CheckitemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName：ForeachTest
 * @Description：用于测试mybatis传集合参数
 * @author：lihao
 * @date：2020/8/29 15:03
 * @version：1.0
 */
@RestController
@RequestMapping("/test")
public class ForeachTest {

    @Reference
    private CheckitemService checkitemService;

    /**
    * @author lihao
    * @description
    * @date 2020/8/29 15:08
    * @Param []
    * @return java.util.List<com.lihao.domain.CheckItem>
    **/
    @RequestMapping("/foreach")
    public List<CheckItem> foreach(){
        List<Integer> list = new ArrayList<>();
        list.add(28);
        list.add(29);
        list.add(30);
        return checkitemService.foreach(list);
    }


}
