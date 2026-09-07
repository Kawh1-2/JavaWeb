package com.ss.servicer;

import com.ss.pojo.Clazz;
import com.ss.pojo.ClazzQueryParam;
import com.ss.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void add(Clazz clazz);

    Clazz getClazz(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> getAllClazz();
}
