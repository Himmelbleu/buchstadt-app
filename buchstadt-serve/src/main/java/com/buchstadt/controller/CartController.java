package com.buchstadt.controller;

import com.buchstadt.annotaion.GlobalUrl;
import com.buchstadt.pojo.Cart;
import com.buchstadt.params.PayForData;
import com.buchstadt.service.CartService;
import com.buchstadt.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@GlobalUrl("/cart")
public class CartController {

    @Resource
    private CartService service;

    /**
     * 把书籍加入到购物车
     *
     * @param id  书籍 ID
     * @param num 书籍数量
     * @param uid 用户 ID
     */
    @PostMapping("/insert")
    public R<Object> insert(@RequestParam Integer id, @RequestParam Integer num, @RequestHeader("Uid") Integer uid) {
        return service.insert(id, num, uid);
    }

    /**
     * 查询用户加入购物车的书籍
     *
     * @param uid 用户 ID
     */
    @GetMapping("/query")
    public R<List<Cart>> query(@RequestHeader("Uid") Integer uid) {
        return service.query(Map.of("userId", uid));
    }

    /**
     * 删除购物车中的书籍
     *
     * @param id 删除书籍的 ID
     */
    @PostMapping("/delete")
    public R<Object> delete(@RequestParam Integer id) {
        return service.delete(id);
    }

    /**
     * 将购物车中的书本以及收货地址插入到数据库中
     *
     * @param data 购物车书本集合以及收货地址实体类
     * @param uid  用户 ID
     */
    @PostMapping("/pay")
    public R<Object> pay(@RequestBody PayForData data, @RequestHeader("Uid") Integer uid) {
        return service.pay(data, uid);
    }

}