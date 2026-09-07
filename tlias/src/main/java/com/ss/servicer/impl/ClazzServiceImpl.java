package com.ss.servicer.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.exception.BusinessException;
import com.ss.mapper.ClazzMapper;
import com.ss.mapper.StudentMapper;
import com.ss.pojo.Clazz;
import com.ss.pojo.ClazzQueryParam;
import com.ss.pojo.PageResult;
import com.ss.servicer.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // List<Clazz>一个班级对象列表，列表中每个元素对应数据库里的一行
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        Page<Clazz> p = (Page<Clazz>) clazzList;

        LocalDate now = LocalDate.now();
        for (Clazz clazz : clazzList) {
            if (now.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else if (now.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getClazz(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    //TODO: 删除班级时，如果该班级下关联有学生，不允许删除，
    // 并提示错误信息："对不起, 该班级下有学生, 不能直接删除"。
    // (提示：可以通过自定义异常 + 全局异常处理器实现)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        Integer count = studentMapper.countByClazzId(id);
        if (count != null && count > 0) {
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> getAllClazz() {
        return clazzMapper.selectAll();
    }
}
