package com.ss.controller;

import com.ss.anno.Log;
import com.ss.pojo.*;
import com.ss.servicer.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询所有学生");
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学生：{}", student);
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("根据id查询学生：{}", id);
        Student student = studentService.get(id);
        return Result.success(student);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学生：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("修改学生违规积分：{}", id);
        studentService.updateViolation(id, score);
        return Result.success();
    }
}
