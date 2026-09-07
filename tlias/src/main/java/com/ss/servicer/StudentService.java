package com.ss.servicer;

import com.ss.pojo.PageResult;
import com.ss.pojo.Student;
import com.ss.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void add(Student student);

    Student get(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void updateViolation(Integer id, Integer score);
}
