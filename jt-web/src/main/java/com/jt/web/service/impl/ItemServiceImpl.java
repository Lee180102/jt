package com.jt.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;
import com.jt.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpClientService httpClient;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Item findItemById(Long itemId) {

        String url = "http://manage.jt.com/web/item/findItemById";

        Map<String, String> params = new HashMap<>();
        params.put("itemId", itemId.toString());

        Item item = null;

        try {
            String resultJson = httpClient.doPost(url, params);
            item = OBJECT_MAPPER.readValue(resultJson, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return item;
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {
        String url = "http://manage.jt.com/web/item/findItemDescById";

        Map<String, String> params = new HashMap<>();
        params.put("itemId", itemId.toString());

        ItemDesc itemDesc = null;

        try {
            String resultJson = httpClient.doPost(url, params);

            itemDesc = OBJECT_MAPPER.readValue(resultJson, ItemDesc.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }


        return itemDesc;
    }
}
