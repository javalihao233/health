package com.lihao.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lihao.domain.CheckGroup;
import com.lihao.domain.CheckItem;
import com.lihao.domain.Setmeal;
import com.lihao.mapper.CheckGroupMapper;
import com.lihao.mapper.CheckitemMapper;
import com.lihao.mapper.SetmealMapper;
import com.lihao.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Autowired
    private CheckitemMapper checkitemMapper;

    /**
     * 分页查询套餐信息
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
    @Override
    public PageInfo getAllSetmeal(int page, int size, String name) {
        String newName = name.replace(" ", "");
        PageHelper.startPage(page, size);
        List<Setmeal> list = setmealMapper.getAllSetmeal(newName);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 删除套餐
     *
     * @param id
     */
    @Override
    public void delSetmeal(int id) {
        //1. 删除套餐与检查组中间表
        setmealMapper.delMiddle(id);
        //2. 删除套餐表
        setmealMapper.delSetmealTable(id);

        //3. 套餐与订单时一对多关系   还得删除订单表信息
        //   怀疑实际工作中，应该会限定像这类数据不能删除   所以我这里就不继续吧这个功能完善了
    }

    /**
     * 查询所有检查组信息
     *
     * @return
     */
    @Override
    public List<CheckGroup> getAllCheckGroup() {
        return setmealMapper.getAllCheckGroup();
    }

    /**
     * 添加套餐信息
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void addSetmeal(Setmeal setmeal, int[] checkgroupIds) {
        //1. 添加套餐基本信息
        setmealMapper.addSetmealBase(setmeal);
        //1.1 插入数据后得到它自增的id
        Integer id = setmeal.getId();
        System.out.println(id);

        //2. 添加套餐对应的检查组信息—————————中间表
        //当新建一个套餐信息，对应的需要添加起对应的检查组信息
        // ————————当一个方法需要多有参数的时候   传参方式：3种  采用@Param
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (int checkgroupId : checkgroupIds) {
                setmealMapper.addMiddle(id, checkgroupId);
            }
        }

        if (setmeal.getImg() != null) {
            jedisPool.getResource().sadd("imgUp_all", setmeal.getImg());//img：套餐对应图片存储路径      // 还要存一次图片地址   为了和用户上传文件时做对比 解决用户上传文件不提交的操作
        }
    }

    /**
     * 根据id查询套餐信息
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal getSetmealById(int id) {
        return setmealMapper.getSetmealById(id);
    }

    /**
     * 根据套餐id查询检查组id集合
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> getGroupIdsById(int id) {
        return setmealMapper.getGroupIdsById(id);
    }

    /**
     * 根据id修改套餐信息
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void editSetmeal(Setmeal setmeal, int[] checkgroupIds) {
        //1. 修改套餐基本信息
        setmealMapper.editSetmealById(setmeal);

        //2. 修改中间表信息
        //2.1 根据套餐id删除中间表相关信息
        Integer id = setmeal.getId();
        setmealMapper.delMiddle(id);
        //2.2 插入中间表相关信息
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (int checkgroupId : checkgroupIds) {
                setmealMapper.addMiddle(id, checkgroupId);
            }
        }
    }

    /**
     * 查询所有套餐信息——————用于前台
     *
     * @return
     */
    @Override
    public List<Setmeal> getAllSetmealForPotal() {
        return setmealMapper.getAllSetmealForPotal();
    }

    /**
     * 获取所有套餐的名称
     *
     * @return
     */
    @Override
    public List<String> getAllSetmealName() {
        return setmealMapper.getAllSetmealName();
    }

    /**
     * 获取所有套餐名称及相应的预约人数
     *
     * @return
     */
    @Override
    public Map<String, Object> getSetmealAndCount() {
        //1. 获取所有套餐名称
        List<String> setmealName = setmealMapper.getAllSetmealName();

        //2. 获取所有套餐的预约数量
//        setmealMapper.getCountByName();

        return null;

    }

    /**
     * 根据套餐id 查询            套餐信息      检查组信息        检查项信息
     *
     * @param id 套餐id
     * @return
     */
    @Override
    public Setmeal findById(int id) {

        //1. 根据id查询套餐信息
        Setmeal setmealById = setmealMapper.getSetmealById(id);

        //2. 根据套餐id  查询  检查组id信息[中间表]
        List<Integer> groupIdsById = setmealMapper.getGroupIdsById(id);

        List<CheckGroup> checkGroupList = new ArrayList();
        for (Integer integer : groupIdsById) {
            //根据id查询出来的检查组信息
            CheckGroup checkGroupById = checkGroupMapper.getCheckGroupById(integer);
            checkGroupList.add(checkGroupById);
        }
        setmealById.setCheckGroups(checkGroupList);


        for (CheckGroup checkGroup : checkGroupList) {
            //3. 根据检查组id  查询  出检查项id[中间表]
            List<Integer> checkitemBygId = checkGroupMapper.getCheckitemBygId(checkGroup.getId());

            List<CheckItem> checkItemList = new ArrayList();
            for (Integer integer : checkitemBygId) {
                CheckItem checkitemById = checkitemMapper.getCheckitemById(integer);
                checkItemList.add(checkitemById);
            }
            checkGroup.setCheckItems(checkItemList);
        }

        return setmealById;
    }
}
