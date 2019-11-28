package com.jt.manage.controller;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult findItemByPage(int page, int rows) {
        return itemService.findItemByPage(page, rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(Item item,String desc){
        try {
            itemService.saveItem(item,desc);
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"商品新增失败！");
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(Item item,String desc){
        try {
            itemService.updateItem(item,desc);
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"修改商品数据失败！");
    }


    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteItem(Long[] ids){
        try {
            itemService.deleteItem(ids);
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"删除商品失败！");
    }

    @RequestMapping("/instock")
    @ResponseBody
    public SysResult instockItem(Long[] ids){
        try {
            int status = 2;
            itemService.updateItem(ids,status);
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"商品下架失败！");
    }


    @RequestMapping("/reshelf")
    @ResponseBody
    public SysResult reshelfItem(Long[] ids){
        try {
            int status = 1;
            itemService.updateItem(ids,status);
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"商品上架失败！");
    }

    @RequestMapping("/query/item/desc/{itemId}")
    @ResponseBody
    public SysResult findItemDescById(@PathVariable Long itemId){
        try {
          ItemDesc itemDesc =  itemService.findItemDescById(itemId);
            return SysResult.oK(itemDesc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"商品详情查询失败！");
    }


    @RequestMapping(value = "/cat/queryItemName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String findItemCatNameById(Long itemId){
        return itemService.findItemCatNameById(itemId);
    }

}
