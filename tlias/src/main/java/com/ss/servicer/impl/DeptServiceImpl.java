package com.ss.servicer.impl;

import com.ss.exception.BusinessException;
import com.ss.mapper.DeptMapper;
import com.ss.mapper.EmpMapper;
import com.ss.pojo.Dept;
import com.ss.pojo.Result;
import com.ss.servicer.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        Integer count = empMapper.countByDeptId(id);
        if (count > 0 && count != null) {
            throw new BusinessException("该部门下有员工, 不能直接删除");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
