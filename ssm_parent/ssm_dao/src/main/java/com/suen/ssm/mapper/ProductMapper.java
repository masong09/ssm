package com.suen.ssm.mapper;

import com.suen.ssm.pojo.PageBean;
import com.suen.ssm.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

        List<Product> queryProductList() throws Exception;

        void saveProduct(Product product) throws Exception;

        Product queryProductById(Long id) throws Exception;

        void updateProduct(Product product) throws Exception;

        void deleteProducts(Long[] ids) throws Exception;

        long queryProductCount() throws Exception;

        /**
         *
         * @Param("pageNum") Integer pageNum ：往Mybatis中传参，ProductMapper.xml
         * @Param("pageSize") Integer pageSize
         * @return
         * @throws Exception
         */
        List<Product> queryProductListPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize) throws Exception;
}
