package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalMeasuret;
import com.erp.mapper.quality.FinalMeasuretMapper;
import com.erp.service.quality.FMeasureService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:ZCH
 * @Date:2019/5/18 10:49
 */
@Service
public class FMeasureServiceImpl implements FMeasureService {

    @Autowired
    FinalMeasuretMapper finalMeasuretMapper;

    @Override
    public QueryVO selectPageMeasure(int page, int rows) {
        int total = finalMeasuretMapper.countAllFinalMeasure();
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalMeasuretMapper.selectAllFMeasureCheckLeft();
        return new QueryVO(total, measurets);
    }

    @Override
    public boolean updateMeasureByfMeasureCheckId(FinalMeasuret finalMeasuret) {
        int update = finalMeasuretMapper.updateByPrimaryKey(finalMeasuret);
        return update == 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deleteMeasureByfMeasureCheckIds(String[] ids) {
        for (String id : ids) {
            int key = finalMeasuretMapper.deleteByPrimaryKey(id);
            if (key != 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public QueryVO searchFMeasureCheckByFMeasureCheckId(String searchValue, int page, int rows) {
        int total = finalMeasuretMapper.countAllFinalMeasureBySomething(searchValue, null);
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalMeasuretMapper.searchFMeasureCheckByFMeasureCheckIdLeft(searchValue);
        return new QueryVO(total, measurets);
    }

    @Override
    public QueryVO searchfMeasureCheckByOrderId(String searchValue, int page, int rows) {
        int total = finalMeasuretMapper.countAllFinalMeasureBySomething(null, searchValue);
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalMeasuretMapper.searchfMeasureCheckByOrderIdLeft(searchValue);
        return new QueryVO(total, measurets);
    }

    @Override
    public boolean insert(FinalMeasuret finalMeasuret) {
        int insert = finalMeasuretMapper.insert(finalMeasuret);
        return insert == 1;
    }


}