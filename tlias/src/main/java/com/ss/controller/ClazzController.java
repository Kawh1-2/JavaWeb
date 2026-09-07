package com.ss.controller;

import com.ss.anno.Log;
import com.ss.pojo.Clazz;
import com.ss.pojo.ClazzQueryParam;
import com.ss.pojo.PageResult;
import com.ss.pojo.Result;
import com.ss.servicer.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("班级条件分页查询：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    // 添加班级
    @Log
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    // 根据id查询班级
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询班级：{}", id);
        Clazz clazz = clazzService.getClazz(id);
        return Result.success(clazz);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级：{}", id);
        clazzService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAllClazz() {
        log.info("查询所有班级：");
        List<Clazz> List = clazzService.getAllClazz();
        return Result.success(List);

    }
}
