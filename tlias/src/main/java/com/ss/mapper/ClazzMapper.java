package com.ss.mapper;

import com.ss.pojo.Clazz;
import com.ss.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    // 分页查询班级
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    // 添加班级
    void insert(Clazz clazz);

    // 根据id查询班级
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    // 修改班级
    void update(Clazz clazz);

    // 删除班级
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz")
    List<Clazz> selectAll();
}
