package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

public interface ItemService {
    EasyUIResult findItemByPage(int page, int rows);

    String findItemCatNameById(Long itemId);

    void saveItem(Item item,String desc);

    void updateItem(Item item,String desc);


    void deleteItem(Long... ids);

    void updateItem(Long[] ids, int status);

    ItemDesc findItemDescById(Long itemId);

}
