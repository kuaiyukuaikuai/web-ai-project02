package com.kuaiyukuaikuai.controller;

import com.kuaiyukuaikuai.pojo.Result;
import com.kuaiyukuaikuai.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
/*    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接受参数:{},{},{}", name, age, file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //截取原文件后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新的文件名
        String newFileName = UUID.randomUUID().toString() + extension;
        //保存文件
        file.transferTo(new File("D:/images/" + newFileName));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}", file);
        if (!file.isEmpty()) {
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
            // 上传文件
            String url = aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);
            return Result.success(url);
        }
        return Result.error("上传失败");
    }


}
