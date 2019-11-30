package com.jt.manage.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private JedisCluster jedisCluster;
    // private Jedis jedis;


   private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<EasyUITree> findItemCatList(Long parentId) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);

        List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
        List<EasyUITree> treeList = new ArrayList<>();

        itemCatList.forEach(itemCatTemp -> {
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(itemCatTemp.getId());
            easyUITree.setText(itemCatTemp.getName());
            easyUITree.setState(itemCatTemp.getParent() ? "close" : "open");
            treeList.add(easyUITree);
        });

        return treeList;
    }

    @Override
    public List<EasyUITree> findItemCatCache(Long parentId) {

        String key = "ITEM_CAT" + parentId;
        String result = jedisCluster.get(key);

        List treeList = null;
        try {
            if (StringUtils.isEmpty(result)){
               treeList =  findItemCatList(parentId);
                String treeListJson = objectMapper.writeValueAsString(treeList);
                jedisCluster.set(key,treeListJson);
                System.out.println("查询数据库");
            }else {
                treeList = objectMapper.readValue(result, List.class);
                System.out.println("查询缓存");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return treeList;
    }
}
