package com.erp.mapper;

import com.erp.bean.material.Material_receive;

public interface Material_receiveMapper {
    int updateByPrimaryKeySelective(Material_receive record);

    int updateByPrimaryKey(Material_receive record);
}