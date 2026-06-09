package com.farmily.groupbuy.model;

import java.util.*;

import com.farmily.product.ProductOptionVO;

public interface GroupBuyDAO_interface {
          public void insert(GroupBuyVO groupBuyVO);
          public void update(GroupBuyVO groupBuyVO);
          public void delete(Integer groupBuyId);
          public GroupBuyVO findByPrimaryKey(Integer groupBuyId);
          public List<GroupBuyVO> getAll();
          List<ProductOptionVO> getProductOptions();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
