package com.lihao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.lihao.domain.CheckItem;
import com.lihao.service.CheckitemService;
import com.lihao.util.ResultEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 1. 检查项管理
 */
@RestController
public class CheckitemController {

    @Reference
    private CheckitemService checkitemService;

    /**
     * 分页条件查询
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
    @RequestMapping("/checkitem/getAllCheckitem")
    public ResultEntity getAllCheckitem(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "") String name) {
        try {
            PageInfo pageInfo = checkitemService.getAllCheckitem(page, size, name);
            return ResultEntity.successWithData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 添加检查项
     *
     * @param checkItem
     * @return
     */
    @RequestMapping("/checkitem/doAdd")
    public ResultEntity doAdd(@RequestBody CheckItem checkItem) {//前段是post请求，数据是json
        try {
                checkitemService.doAdd(checkItem);
            return ResultEntity.successNoData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 根据id删除检查项
     *
     * @param id
     * @return
     */
    @RequestMapping("/checkitem/doDel")
    public ResultEntity doDel(int id) {//          "/checkitem/doDel.do?id=" + row.id
        try {
            checkitemService.doDel(id);
            return ResultEntity.successNoData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 根据id查找检查项
     *
     * @param id
     * @return
     */
    @RequestMapping("/checkitem/getCheckitemById")
    public ResultEntity getCheckitemById(int id) { //   "/checkitem/getCheckitemById.do?id=" + row.id   这里发的是一个get请求
        try {
            //需不需要验证id呢，  如果不传id   id默认值是0    会查询id为0的数据
            // SELECT * FROM t_checkitem WHERE id =0    查出来的数据为null
            CheckItem checkItem = checkitemService.getCheckitemById(id);
            return ResultEntity.successWithData(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 编辑检查项
     *
     * @param checkItem
     * @return
     */
    @RequestMapping("/checkitem/doEdit")
    public ResultEntity doEdit(@RequestBody CheckItem checkItem) {
        try {
            checkitemService.doEdit(checkItem);
            return ResultEntity.successNoData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 获取所有检查项信息
     *
     * @return
     */
    @RequestMapping("/checkitem/getAllCheckItem")//getAllCheckItem   I是大写
    public ResultEntity getAllCheckItem() {
        try {
            List<CheckItem> list = checkitemService.getAllCheckItem();
            return ResultEntity.successWithData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
}
