package com.suen.ssm.mapper;

import com.suen.ssm.pojo.Orders;

import java.util.List;

public interface OrdersMapper {
        List<Orders> queryOrdersList() throws Exception;

        Orders queryOrderById(Long id) throws Exception;

        void saveOrder(Orders order) throws Exception;

        void deleteOrders(Long[] ids) throws Exception;

        void updateOrders(Orders order) throws Exception;
}

