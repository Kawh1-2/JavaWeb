package com.ss.servicer;

import com.ss.pojo.ClazzOption;
import com.ss.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    ClazzOption getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
