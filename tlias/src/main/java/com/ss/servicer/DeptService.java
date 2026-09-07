package com.ss.servicer;

import com.ss.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {


    List<Dept> findAll();

    void deleteById(Integer id);

    void insert(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
