package com.pyming.demo.infrastructure.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 8:59
 */
public class UpUtil {

    /**
     * 获取文件大小
     */
    public static long getFileSize(MultipartFile multipartFile) {
        return multipartFile.getSize();
    }

    /**
     * 获取文件类型
     */
    public static String getFileType(MultipartFile multipartFile) {
        if (multipartFile.getContentType() != null) {
            int begin = multipartFile.getOriginalFilename().lastIndexOf(".");
            int last = multipartFile.getOriginalFilename().length();
            return multipartFile.getOriginalFilename().substring(begin + 1, last);
        }

        String fileName = multipartFile.getOriginalFilename();
        int begin = fileName.lastIndexOf(".");
        int last = fileName.length();
        return fileName.substring(begin + 1, last);
    }

    /**
     * 上传文件
     */
    public static String upLoad(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String time = formatter.format(date);
        String uuidFileName = time + "_" + RandomUtil.uuId() + "@" + fileName;
        File dest = new File(uuidFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return uuidFileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
