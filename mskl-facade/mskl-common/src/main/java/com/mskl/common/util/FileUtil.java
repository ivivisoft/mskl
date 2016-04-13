package com.mskl.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public final class FileUtil {
    private static Log log = LogFactory.getLog(FileUtil.class);

    /**
     * 文件上传
     *
     * @param path     目录
     * @param srcFile  源始文件
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    public static String fileUpload(String path, File srcFile, String fileName) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("文件上传目录：" + path);
        }
        FileOutputStream fos = null;
        FileInputStream fis = null;
        int dotIndex = StringUtils.lastIndexOf(fileName, ".");
        String fixFileName = dotIndex > 0 ? StringUtils.trimToEmpty(StringUtils.substring(fileName, dotIndex)) : "";

        String newFileName = UUID.randomUUID() + fixFileName;
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                //检查目录是否存在，不存在即新建
                dir.mkdirs();
            }
            File newFile = new File(path + newFileName);
            fos = new FileOutputStream(newFile);
            fis = new FileInputStream(srcFile);

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(fis);
        }
        return newFileName;
    }

    /**
     * 文件上传
     *
     * @param path     目录
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    public static String fileUpload(String path, InputStream fis, String fileName) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("文件上传目录：" + path);
        }
        FileOutputStream fos = null;
        int dotIndex = StringUtils.lastIndexOf(fileName, ".");
        String fixFileName = dotIndex > 0 ? StringUtils.trimToEmpty(StringUtils.substring(fileName, dotIndex)) : "";

        String newFileName = UUID.randomUUID() + fixFileName;
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                //检查目录是否存在，不存在即新建
                dir.mkdirs();
            }
            File newFile = new File(path + newFileName);
            fos = new FileOutputStream(newFile);

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(fis);
        }
        return newFileName;
    }

}