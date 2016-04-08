package com.mskl.service.msklfile.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UploadFileDto;
import com.mskl.common.util.FileUtil;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.MsklFile;
import com.mskl.dao.msklfile.MsklFileDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.msklfile.MsklFileService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "msklFile.msklFileService")
public class MsklFileServiceImpl extends BaseServiceImpl<MsklFile, Serializable> implements MsklFileService {

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
                String filePath = FileUtil.mobileFileUpload("/upload/image/case/", uploadFileDto.getFile(), "identity.png");
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
                String filePath = FileUtil.mobileFileUpload("/upload/image/identity/", uploadFileDto.getFile(), "identity.png");
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
}
