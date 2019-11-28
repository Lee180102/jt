package com.jt.manage.service.impl;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.jvmstat.perfdata.monitor.protocol.file.FileMonitoredVm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${image.localPath}")
    private String localPath ;
    @Value("${image.urlPath}")
    private String urlPath ;
    @Override
    public PicUploadResult fileUpload(MultipartFile uploadFile) {

        PicUploadResult uploadResult = new PicUploadResult();

        String filename = uploadFile.getOriginalFilename();
        filename = filename.toLowerCase();
        if (!filename.matches("^.*\\.(jpg|png|gif)$")){
            uploadResult.setError(1);
            return uploadResult;
        }


        try {
            BufferedImage read = ImageIO.read(uploadFile.getInputStream());
            int height = read.getHeight();
            int width = read.getWidth();

            if (height ==0 || width ==0){
                uploadResult.setError(1);
                return uploadResult;
            }

            String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

           String dirPath =  localPath + "/" +datePath;

            File dirFile = new File(dirPath);
            if (!dirFile.exists()){
                dirFile.mkdirs();
            }

            String uuid = UUID.randomUUID().toString().replace("-", "");
            int randomNumber = new Random().nextInt(1000);

            String imageFileType = filename.substring(filename.lastIndexOf("."));

            String imageFileName = uuid + randomNumber + imageFileType;

            String imageLocalPath = dirPath + "/" + imageFileName;

            uploadFile.transferTo(new File(imageLocalPath));

            uploadResult.setHeight(height+"");
            uploadResult.setWidth(width+"");

            String imageUrlPath = urlPath + datePath + "/" + imageFileName;

            uploadResult.setUrl(imageUrlPath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  uploadResult;
    }
}
