package com.ss.controller;


import com.ss.utils.AliyunOSSOperator;
import com.ss.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    /**
     * 本地磁盘存储
     */
    /*@PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数：{},{},{}", name, age, file);
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 新文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID() + extension;

        // 保存文件
        file.transferTo(new File("D:/AAimages/" + newFilename));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}", file);
        //文件交给OSS存储
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS，url：{}", url);

        return Result.success(url);
    }
}
