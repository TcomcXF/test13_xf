package com.erp.mapper;

import com.erp.bean.material.Material_consume;

public interface Material_consumeMapper {
    int updateByPrimaryKeySelective(Material_consume record);

    int updateByPrimaryKey(Material_consume record);
}