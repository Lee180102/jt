package com.jt.manage.service.impl;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyUIResult findItemByPage(int page, int rows) {

        //int total = itemMapper.findCount();
        //通用Mapper
        int total = itemMapper.selectCount(null);
        int start = (page - 1) * rows;
        List<Item> itemList = itemMapper.findItemByPage(start, rows);
        return new EasyUIResult(total, itemList);
    }

    @Override
    public String findItemCatNameById(Long itemId) {
        return itemMapper.findItemCatNameById(itemId);

    }

    @Override
    public void saveItem(Item item,String desc) {
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemMapper.insert(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        itemDescMapper.insert(itemDesc);
    }

    @Override
    public void updateItem(Item item,String desc) {
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
    }

    @Override
    public void deleteItem(Long... ids) {
        itemMapper.deleteByIDS(ids);
        itemDescMapper.deleteByIDS(ids);
    }

    @Override
    public void updateItem(Long[] ids, int status) {
        itemMapper.updateStatus(ids,status);
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {
        return itemDescMapper.selectByPrimaryKey(itemId);
    }

}
