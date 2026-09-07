package com.ss.servicer.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.utils.JwtUtils;
import com.ss.mapper.EmpExprMapper;
import com.ss.mapper.EmpMapper;
import com.ss.pojo.*;
import com.ss.servicer.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    //原始分页查询
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //调用mapper接口，查询总记录数
        Long total = empMapper.count();

        //调用mapper接口，查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list();
        //封装结果 pageResult
        return new PageResult<>(total, rows);
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);

        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})      // 事务管理，默认出现RuntimeException才会回滚
    @Override
    public void save(Emp emp) {
        //1. 调用mapper接口保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //保存工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {   //判断工作经历集合是否为空
            // 遍历集合，为exprList集合中每个EmpExpr中的empId赋值
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //删除员工
        empMapper.deleteByIds(ids);
        //删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    //根据Id查询员工信息
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1. 调用mapper接口更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 调用mapper接口更新员工工作经历信息
        //2.1 删除旧的工作经历信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 批量保存新的工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> getAllEmps() {
        return empMapper.selectAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        // 通过用户名和密码查询员工
        Emp e = empMapper.selectByUAndP(emp);
        // 如果查询结果不为空，说明登录成功，返回员工信息
        if (e != null) {
            log.info("登录成功，员工信息：{}", e);
            Map<String, Object> cliams = new HashMap<>();
            cliams.put("id", e.getId());
            cliams.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(cliams);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }
        // 如果查询结果为空，说明登录失败，返回null
        return null;
    }

}
