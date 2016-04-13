package com.mskl.service.msklfile;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UploadFileDto;
import com.mskl.dao.model.MsklFile;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface MsklFileService extends BaseService<MsklFile,Serializable>{
    /**
     * 上传实名认证
     * @param uploadFileDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> uploadIdentityFile(UploadFileDto uploadFileDto, String token);

    /**
     * 上传病例
     * @param uploadFileDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> uploadCaseFile(UploadFileDto uploadFileDto, String token);

    /**
     * 上传文件到服务器
     * @param path
     * @param file
     * @param fileName
     * @return
     * @throws Exception
     */
    String saveFileToServer(String path, String file, String fileName) throws Exception;
}
