package com.suen.ssm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suen.ssm.mapper.OrdersMapper;
import com.suen.ssm.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

        @Autowired
        private OrdersMapper ordersMapper;

        @Override
        public PageInfo<Orders> queryOrdersList(Integer pageNum, Integer pageSize) throws Exception {
                // 向pageHelper传参
                PageHelper.startPage(pageNum,pageSize);
                // 紧跟PageHelper.startPage后的第一个查询（查询全部数据）
                List<Orders> ordersList = ordersMapper.queryOrdersList();
                // 封装pageInfo对象
                PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
                return pageInfo;
        }

        @Override
        public Orders queryOrderById(Long id) throws Exception {
                return ordersMapper.queryOrderById(id);
        }

        @Override
        public void saveOrder(Orders order) throws Exception {
                ordersMapper.saveOrder(order);
        }

        @Override
        public void updateOrders(Orders order) throws Exception {
                ordersMapper.updateOrders(order);
        }

        @Override
        public void deleteOrders(Long[] ids) throws Exception {
                ordersMapper.deleteOrders(ids);
        }
}
