package com.ss.servicer.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.mapper.StudentMapper;
import com.ss.pojo.PageResult;
import com.ss.pojo.Student;
import com.ss.pojo.StudentQueryParam;
import com.ss.servicer.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    // 分页查询学员
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> studentList = studentMapper.list(studentQueryParam);

        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    // 添加学员
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student get(Integer id) {
        return studentMapper.selectById(id);
    }

    // 修改学员
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    // 删除学员
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    // 学员违纪处理
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateViolation(Integer id, Integer score) {
        studentMapper.updateViolationCount(id, score);
        studentMapper.updateViolationScore(id, score);
    }
}
