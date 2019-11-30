package com.jt.manage.controller;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileUploadService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    @Autowired
    private FileUploadService fileService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult fileUpload(MultipartFile uploadFile){
        return fileService.fileUpload(uploadFile);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("获取Spring工厂对象：" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("获取当前累在Spring中的ID："+ name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("获取整个Spring容器：" + applicationContext);
    }
}
