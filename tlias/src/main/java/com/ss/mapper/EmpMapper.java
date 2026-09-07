package com.ss.mapper;

import com.ss.pojo.Emp;
import com.ss.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //----------------原始分页查询方式--------------------
    /*@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    Long count();

    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id" +
            " order by e.update_time desc limit #{start}, #{pageSize}")
    List<Emp> list(Integer start, Integer pageSize);*/
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, " +
            "entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, " +
            "#{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    // 根据Id批量删除员工基本信息
    void deleteByIds(List<Integer> ids);

    // 根据Id查询员工信息及工作经历信息
    Emp getById(Integer id);

    // 根据Id更新员工信息
    void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();

    @Select("select id, username, name, password, entry_date, gender, image, job, salary, dept_id, create_time, update_time from emp")
    List<Emp> selectAll();

    @Select("select count(*) from emp where dept_id = #{id}")
    Integer countByDeptId(Integer id);

    // 根据用户名和密码查询员工信息
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUAndP(Emp emp);
}
