package com.ss.servicer;

import com.ss.pojo.*;

import java.util.List;

public interface EmpService {
    // 分页查询员工信息
    PageResult<Emp> page(EmpQueryParam empQueryParam);
    // 保存员工信息
    void save(Emp emp);
    // 删除员工信息
    void delete(List<Integer> ids);
    // 根据Id查询员工信息
    Emp getInfo(Integer id);
    // 修改员工信息
    void update(Emp emp);
    // 查询所有员工信息
    List<Emp> getAllEmps();
    // 登录
    LoginInfo login(Emp emp);
}
