package com.ss.controller;

import com.ss.anno.Log;
import com.ss.pojo.*;
import com.ss.servicer.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {  //分页查找
        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("保存员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //根据Id查询员工信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据Id查询员工信息:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAllEmps() {
        log.info("查询所有员工");
        List<Emp> list = empService.getAllEmps();
        return Result.success(list);
    }
}
