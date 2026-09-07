package com.ss.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private Integer page = 1;//当前页码
    private Integer pageSize = 10;//每页大小
    private String name;//姓名
    private Integer degree;//学历
    private Integer clazzId;//班级ID

}
