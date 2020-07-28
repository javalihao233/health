package com.lihao.service;

import java.util.Map;

public interface OrderService {
    void order(Map map) throws Exception;

    Integer getCountBySetmealId(Integer id);
}
