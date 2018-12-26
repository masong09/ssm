package com.suen.ssm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suen.ssm.mapper.ProductMapper;
import com.suen.ssm.pojo.PageBean;
import com.suen.ssm.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

        @Autowired
        private ProductMapper productMapper;

        @Override
        public List<Product> queryProductList() throws Exception {
                return productMapper.queryProductList();
        }

        /**
         * 传统手动分页逻辑处理
         * @param pageNum
         * @param pageSize
         * @return
         * @throws Exception
         */
        @Override
        public PageBean<Product> queryProductListPage(Integer pageNum, Integer pageSize) throws Exception {
                PageBean<Product> pageBean = new PageBean<>();
                // 当前页
                pageBean.setPageNum(pageNum);
                // 每页显示的条数
                pageBean.setPageSize(pageSize);
                // 总条数（调用dao层查询product表的总记录数）
                long totalCount = productMapper.queryProductCount();
                pageBean.setTotalCount(totalCount);
                // 总页数(总条数/每页的条数，结果向上取整)
                int totalPage = (int)Math.ceil (1.0*totalCount/pageSize);
                pageBean.setTotalPage(totalPage);
                // 当前页的数据(oracle分页)
                List<Product> productList = productMapper.queryProductListPage(pageNum,pageSize);
                pageBean.setPageList(productList);
                return pageBean;
        }

        /**
         * 使用PageHelper分页
         * @param pageNum
         * @param pageSize
         * @return
         * @throws Exception
         */
        @Override
        public PageInfo<Product> queryProductListPageHelper(Integer pageNum, Integer pageSize) throws Exception {
                // 向pageHelper传参
                PageHelper.startPage(pageNum,pageSize);
                // 紧跟PageHelper.startPage后的第一个查询（查询全部数据）
                List<Product> productList = productMapper.queryProductList();
                // 封装pageInfo对象
                PageInfo<Product> pageInfo = new PageInfo<>(productList);
                return pageInfo;
        }

        @Override
        public Product queryProductById(Long id) throws Exception {
                return productMapper.queryProductById(id);
        }

        @Override
        public void saveProduct(Product product) throws Exception {
                productMapper.saveProduct(product);
        }

        @Override
        public void updateProduct(Product product) throws Exception {
                productMapper.updateProduct(product);
        }

        @Override
        public void deleteProducts(Long[] ids) throws Exception {
                productMapper.deleteProducts(ids);
        }
}
