package com.jt.manage.controller;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult fileUpload(MultipartFile uploadFile){
        return fileService.fileUpload(uploadFile);
    }
}
