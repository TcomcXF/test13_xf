package com.erp.service.material.impl;

import com.erp.bean.QueryVO;

import com.erp.bean.material.Material;

import com.erp.mapper.material.MaterialMapper;
import com.erp.service.material.MaterialService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 12:11
 */

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public QueryVO<Material> getMaterialList(Integer page, Integer rows) {

        List<Material> list1 = materialMapper.queryAllMaterial();



        PageHelper.startPage(page,rows);


        List<Material> list = materialMapper.queryAllMaterial();

        return new QueryVO<>(list1.size(),list);
    }

    @Override
    public QueryVO<Material> queryMaterialById(String searchValue,Integer page, Integer rows) {

        searchValue = "%"+searchValue+"%";


        List<Material> materialList1= materialMapper.getMaterialById(searchValue);

        PageHelper.startPage(page,rows);

        List<Material> materialList= materialMapper.getMaterialById(searchValue);

        return new QueryVO<Material>(materialList1.size(),materialList);
    }

    @Override
    public QueryVO<Material> queryMaterialByType(String searchValue,Integer page, Integer rows) {
        searchValue = "%"+searchValue+"%";

        List<Material> materialList1 = materialMapper.getMaterialByType(searchValue);

        PageHelper.startPage(page,rows);

        List<Material> materialList = materialMapper.getMaterialByType(searchValue);

        return new QueryVO<Material>(materialList1.size(),materialList);
    }

    @Override
    public Material queryMaterial(String id) {
        Material material = materialMapper.queryMaterialById(id);
        return material;
    }

    @Override
    public boolean insertMaterial(Material material) {


        int insert = materialMapper.insertMaterial(material);

        return insert == 1?true:false;

    }

    @Override
    public List<Material> queryAllMaterial() {
        List<Material> materialList = materialMapper.queryAllMaterial();
        return materialList;
    }

    @Override
    public boolean update_note(Material material) {
        int update = materialMapper.update_note(material);
        return update==1?true:false;
    }


    @Override
    public boolean delete_batch(String ids) {
        int isDele = materialMapper.delete_batch(ids);
        return isDele==1?true:false;
    }

    @Override
    public boolean update_all(Material material) {
        int update = materialMapper.updateByPrimaryKeySelective(material);


        return update ==1?true:false;
    }
}
