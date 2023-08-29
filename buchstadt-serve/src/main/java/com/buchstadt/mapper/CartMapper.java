package com.buchstadt.mapper;

import com.buchstadt.pojo.Cart;
import com.buchstadt.params.PayForData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {

    int insert(Integer id, Integer num, Integer uid);

    List<Cart> query(Map<String, Object> map);

    int delete(Integer id);

    int empty(Integer userId);

    int insertOrder(PayForData data);

    int insertOrderBuchs(List<PayForData.Item> list);
}
