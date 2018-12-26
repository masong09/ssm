package com.suen.ssm.service;


import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.Orders;

public interface OrdersService {

        PageInfo<Orders> queryOrdersList(Integer pageNum, Integer pageSize) throws Exception;

        Orders queryOrderById(Long id) throws Exception;

        void saveOrder(Orders order) throws Exception;

        void updateOrders(Orders order) throws Exception;

        void deleteOrders(Long[] ids) throws Exception;
}
