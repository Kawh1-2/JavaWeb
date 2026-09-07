package com.ss.mapper;

import com.ss.pojo.ClazzOption;
import com.ss.pojo.Student;
import com.ss.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);

    void insert(Student student);

    Student selectById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void updateViolationCount(Integer id, Integer score);
    void updateViolationScore(Integer id, Integer score);

    List<Map<String, Object>> countStu();

    List<Map<String, Object>> countStuDegreeData();

    @Select("select count(*) from student where clazz_id = #{clazzId}")
    Integer countByClazzId(Integer clazzId);
}
