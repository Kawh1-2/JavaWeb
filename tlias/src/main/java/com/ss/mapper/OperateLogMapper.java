package com.ss.mapper;

import com.ss.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    void insert(OperateLog log);

    //分页查询日志信息，关联员工表查询操作人姓名，按操作时间倒序
    @Select("select ol.*, e.name operateEmpName from operate_log ol left join emp e " +
            "on ol.operate_emp_id = e.id order by ol.operate_time desc")
    List<OperateLog> list();
}
