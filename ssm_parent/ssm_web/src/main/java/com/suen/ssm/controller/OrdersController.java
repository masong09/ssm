package com.suen.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.Orders;
import com.suen.ssm.pojo.Product;
import com.suen.ssm.service.OrdersService;
import com.suen.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

        @Autowired
        private OrdersService ordersService;
        @Autowired
        private ProductService productService;

        /**
         * 查询全部订单信息+PageHelper分页
         * @param model
         * @return
         * @throws Exception
         */
        @RequestMapping("queryOrdersList")
        public String queryOrdersList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Model model) throws Exception{
                PageInfo<Orders> pageInfo = ordersService.queryOrdersList(pageNum,pageSize);
                model.addAttribute("pageInfo",pageInfo);
                return "order-list";
        }

        /**
         * 跳转到订单编辑页面
         * @param id
         * @param model
         * @return
         * @throws Exception
         */
        @RequestMapping("toUpdateOrders")
        public String toUpdateOrders(Long id,Model model) throws Exception {
                //查询全部商品数据（前台显示下拉列表）
                List<Product> productList = productService.queryProductList();
                model.addAttribute("productList",productList);
                Orders order = ordersService.queryOrderById(id);
                model.addAttribute("order",order);
                return "order-update";
        }

        /**
         * 更新提交动作
         * @param order
         * @return
         */
        @RequestMapping("updateOrders")
        public String updateOrders(Orders order) throws Exception {
                ordersService.updateOrders(order);
                //跳转产品列表页面
                return "redirect:queryOrdersList.action";
        }



        //跳转到订单添加页面
        @RequestMapping("toOrderAdd")
        public String toOrderAdd(Model model) throws Exception {
                List<Product> productList = productService.queryProductList();
                model.addAttribute("productList",productList);
                return "order-add";
        }

        /**
         * 新建订单
         * @param order
         * @return
         * @throws Exception
         */
        @RequestMapping("saveOrder")
        public String saveOrder(Orders order) throws Exception{
                ordersService.saveOrder(order);
                return "redirect:queryOrdersList.action";
        }

        /**
         * 删除订单
         * @param ids
         * @return
         */
        @RequestMapping("deleteOrders")
        public String deleteOrders(Long[] ids) throws Exception {
                ordersService.deleteOrders(ids);
                return "redirect:queryOrdersList.action";
        }
}
