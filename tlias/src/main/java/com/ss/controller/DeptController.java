package com.ss.controller;

import com.ss.anno.Log;
import com.ss.pojo.Dept;
import com.ss.pojo.Result;
import com.ss.servicer.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  //lombok提供日志记录功能
@RequestMapping("/depts")
@RestController//@Controller + @ResponseBody
public class DeptController {

//    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result lists() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete(Integer id) {
        log.info("根据id删除部门: {}", id);   // {}为占位符
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门: " + dept);
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询部门: " + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("根据id更新部门: " + dept);
        deptService.update(dept);
        return Result.success();
    }
}
