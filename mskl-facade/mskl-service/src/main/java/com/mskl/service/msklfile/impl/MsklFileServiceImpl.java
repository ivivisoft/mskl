package com.mskl.service.msklfile.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UploadFileDto;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.MsklFile;
import com.mskl.dao.msklfile.MsklFileDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.msklfile.MsklFileService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.UUID;

@Service(value = "msklFile.msklFileService")
public class MsklFileServiceImpl extends BaseServiceImpl<MsklFile, Serializable> implements MsklFileService {

    @Value("${file.upload.dir}")
    private String fileSavePath;

    private Log logger = LogFactory.getLog(MsklFileServiceImpl.class);

    private MsklFileDao msklFileDao;

    @Resource(name = "msklFile.msklFileDao")
    public void setMsklFileDao(MsklFileDao msklFileDao) {
        this.msklFileDao = msklFileDao;
        super.setBaseDaoImpl(msklFileDao);
    }


    public RestServiceResult<Boolean> uploadIdentityFile(UploadFileDto uploadFileDto, String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("上传实名认证服务", false);
        Long userId = TokenUtil.getUserIdFromToken(token);
        MsklFile msklFile = new MsklFile();
        msklFile.setMobile(uploadFileDto.getMobile());
        msklFile.setUserId(userId);
        msklFile.setType(1);
        if (!updateIdentityImg(uploadFileDto, result, msklFile)) {
            return result;
        }
        try {
            saveObject(msklFile);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        } catch (Exception e) {
            result.setMessage("上传身份证图片失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
    }

    public RestServiceResult<Boolean> uploadCaseFile(UploadFileDto uploadFileDto, String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("上传病例服务", false);
        Long userId = TokenUtil.getUserIdFromToken(token);

        MsklFile msklFile = new MsklFile();

        msklFile.setMobile(uploadFileDto.getMobile());
        msklFile.setUserId(userId);
        msklFile.setType(1);
        if (!updateCaseImg(uploadFileDto, result, msklFile)) {
            return result;
        }
        try {
            saveObject(msklFile);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        } catch (Exception e) {
            result.setMessage("上传病历图片到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
    }

    private boolean updateCaseImg(UploadFileDto uploadFileDto, RestServiceResult<Boolean> result, MsklFile msklFile) {
        if (StringUtils.isBlank(uploadFileDto.getFile())) {
            try {
                String filePath = saveFileToServer("/upload/image/case/", uploadFileDto.getFile(), "identity.png");
                msklFile.setFilePath(filePath);
                return true;
            } catch (Exception e) {
                result.setMessage("更新图像失败!");
                if (logger.isErrorEnabled()) {
                    logger.error(result.toString());
                }
                return false;
            }
        }
        return true;
    }

    private boolean updateIdentityImg(UploadFileDto uploadFileDto, RestServiceResult<Boolean> result, MsklFile msklFile) {
        if (StringUtils.isBlank(uploadFileDto.getFile())) {
            try {
                String filePath = saveFileToServer("/upload/image/identity/", uploadFileDto.getFile(), "identity.png");
                msklFile.setFilePath(filePath);
                return true;
            } catch (Exception e) {
                result.setMessage("更新图像失败!");
                if (logger.isErrorEnabled()) {
                    logger.error(result.toString());
                }
                return false;
            }
        }
        return true;
    }



    public  String saveFileToServer(String path, String file, String fileName) throws Exception {
        FileOutputStream fos = null;
        String filePath = fileSavePath+path;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                //检查目录是否存在，不存在即新建
                dir.mkdirs();
            }
            int dotIndex = StringUtils.lastIndexOf(fileName, ".");
            String fixFileName = dotIndex > 0 ? StringUtils.trimToEmpty(StringUtils.substring(fileName, dotIndex)) : "";
            String newFileName = UUID.randomUUID() + fixFileName;
            File newFile = new File(filePath + newFileName);
            // 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密
            byte[] photoImg = new BASE64Decoder().decodeBuffer(file);
            for (int i = 0; i < photoImg.length; ++i) {
                if (photoImg[i] < 0) {
                    // 调整异常数据
                    photoImg[i] += 256;
                }
            }
            fos = new FileOutputStream(newFile);
            fos.write(photoImg);
            fos.flush();
            return path + newFileName;
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }

}
