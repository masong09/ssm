package com.suen.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.PageBean;
import com.suen.ssm.pojo.Product;
import com.suen.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

        @Autowired
        private ProductService productService;

        /**
         * 查询全部商品数据
         */
        @RequestMapping("queryProductList")
        public String queryProductList(Model model) throws Exception {
                List<Product> productList = productService.queryProductList();
                model.addAttribute("productList",productList);
                //跳转页面
                return "product-list";
        }

        /**
         * 查询全部商品数据：(传统手动分页)
         * 每次分页操作需要从前台传递到后台的参数：
         *      pageNum(当前页)，pageSize(每页显示的记录数)
         */
        @RequestMapping("queryProductListPage")
        public String queryProductListPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Model model) throws Exception {
                PageBean<Product> pageBean = productService.queryProductListPage(pageNum,pageSize);
                model.addAttribute("pageBean",pageBean);
                //跳转页面
                return "product-list-page";
        }

        /**
         * 查询全部商品数据：(PageHelper分页)
         * 每次分页操作需要从前台传递到后台的参数：
         *      pageNum(当前页)，pageSize(每页显示的记录数)
         */
        @RequestMapping("queryProductListPageHelper")
        public String queryProductListPageHelper(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Model model) throws Exception {
                PageInfo<Product> pageInfo = productService.queryProductListPageHelper(pageNum,pageSize);
                model.addAttribute("pageInfo",pageInfo);
                //跳转页面
                return "product-list-pageHelper";
        }

        /**
         * 跳转到产品添加页面
         * @return
         */
        @RequestMapping("toProductAdd")
        public String toProductAdd() {
                return "product-add";
        }

        /**
         * 新增产品信息
         * @param product
         * @return
         * @throws Exception
         */
        @RequestMapping("saveProduct")
        public String saveProduct(Product product) throws Exception {
                productService.saveProduct(product);
                //重定向到产品列表页面
                return "redirect:queryProductList.action";
        }

        /**
         * 跳转更新产品信息页面
         * @param id
         * @param model
         * @return
         * @throws Exception
         */
        @RequestMapping("toUpdateProduct")
        public String toUpdateProduct(Long id,Model model) throws Exception{
                Product product = productService.queryProductById(id);
                model.addAttribute("product",product);
                //跳转更新页面
                return "product-update";
        }

        /**
         * 更新提交动作
         * @param product
         * @return
         */
        @RequestMapping("updateProduct")
        public String updateProduct(Product product) throws Exception {
                productService.updateProduct(product);
                //跳转产品列表页面
                return "redirect:queryProductList.action";
        }

        /**
         * 删除产品信息
         * @param ids
         * @return
         */
        @RequestMapping("deleteProducts")
        public String deleteProducts(Long[] ids) throws Exception {
                productService.deleteProducts(ids);
                return "redirect:queryProductList.action";
        }
}
