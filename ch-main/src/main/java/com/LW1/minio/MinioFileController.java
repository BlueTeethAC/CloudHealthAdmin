package com.LW1.minio;


import com.LW1.utils.MinioUtil;
import com.ruoyi.common.config.MinioConfig;
import com.ruoyi.common.core.controller.BaseController;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/product/file")
public class MinioFileController {


    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioConfig prop;

    // 查看存储bucket是否存在
    @GetMapping("/bucketExists")
    public Boolean bucketExists(@RequestParam("bucketName") String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }

    // 创建存储bucket
    @GetMapping("/makeBucket")
    public Boolean makeBucket(String bucketName) {
        return minioUtil.makeBucket(bucketName);
    }

    // 删除存储bucket
    @GetMapping("/removeBucket")
    public Boolean removeBucket(String bucketName) {
        return minioUtil.removeBucket(bucketName);
    }

    // 获取全部bucket
    @GetMapping("/getAllBuckets")
    public List<Bucket> getAllBuckets() {
        List<Bucket> allBuckets = minioUtil.getAllBuckets();
        return allBuckets;
    }



    // 文件上传返回url
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName;
        }
        return "文件上传失败";
    }


    // 图片/视频预览
    @GetMapping("/preview")
    public String preview(@RequestParam("fileName") String fileName) {
        return minioUtil.preview(fileName);
    }

    // 文件下载
    @GetMapping("/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        minioUtil.download(fileName,res);
    }


    // 根据url地址删除文件
    @PostMapping("/delete")
    public void remove(String url) {
        String objName = url.substring(url.lastIndexOf(prop.getBucketName()+"/") + prop.getBucketName().length()+1);
        minioUtil.remove(objName);
    }

}

