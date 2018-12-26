package com.suen.ssm.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.PageBean;
import com.suen.ssm.pojo.Product;

public interface ProductService {
        List<Product> queryProductList() throws Exception;

        PageBean<Product> queryProductListPage(Integer pageNum, Integer pageSize) throws Exception;

        PageInfo<Product> queryProductListPageHelper(Integer pageNum, Integer pageSize) throws Exception;

        void saveProduct(Product product) throws Exception;

        Product queryProductById(Long id) throws Exception;

        void updateProduct(Product product) throws Exception;

        void deleteProducts(Long[] ids) throws Exception;
}
