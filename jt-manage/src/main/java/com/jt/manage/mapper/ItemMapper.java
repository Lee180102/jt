package com.jt.manage.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface ItemMapper extends SysMapper<Item> {
    List<Item> findItemByPage(@Param("start") int start,@Param("rows") int rows);

    int findCount();

    String findItemCatNameById(Long itemId);

    void updateStatus(@Param("ids") Long[] ids, @Param("status") int status);
}
