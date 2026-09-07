package com.ss.servicer.impl;

import com.ss.mapper.EmpMapper;
import com.ss.mapper.StudentMapper;
import com.ss.pojo.ClazzOption;
import com.ss.pojo.JobOption;
import com.ss.servicer.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();   //map: pos=教研主管，num=1

        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.countStu();

        List<Object> calzzList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new ClazzOption(calzzList, dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStuDegreeData();
    }
}
