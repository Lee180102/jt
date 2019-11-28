package com.jt.manage.service;

import com.jt.manage.vo.EasyUITree;

import java.util.List;

public interface ItemCatService {
    List<EasyUITree> findItemCatList(Long parentId);

    List findItemCatCache(Long parentId);
}
