package com.ss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {

    private List<Object> jobList;    //职位列表
    private List<Object> dataList;   //数据列表

}
