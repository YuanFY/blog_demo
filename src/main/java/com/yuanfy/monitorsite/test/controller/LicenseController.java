package com.yuanfy.monitorsite.test.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanfy.monitorsite.common.util.DownloadUtils;
import com.yuanfy.monitorsite.common.util.file.ResPathUtils;
import com.yuanfy.monitorsite.common.util.license.LicenseManager;
import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.test.entity.LicenseEntity;

/**
 * @Description: license控制器类
 * @author yuanfy
 * @date 2017年12月28日 上午11:34:43 
 * @version 1.0
 */
@Controller
public class LicenseController {
    
    private static Logger log = LoggerFactory.getLogger(LicenseController.class);
    
    @RequestMapping("/test/license/downloadWciFile")
    public void downloadWCIFile(LicenseEntity entity, HttpServletResponse response){
        try {
            //加密许可信息
            String encryptInfo = LicenseManager.encryptWciInfo(entity);
            DownloadUtils.setFileDownloadHeader(response, ResPathUtils.WCI_FILE_NAME, null);
            OutputStream out = response.getOutputStream();
            out.write(encryptInfo.getBytes());
            out.flush();
            out.close();
        }
        catch (FileNotFoundException e) {
            //已经在检测方法里捕获了。
            log.error(e.getMessage(), e);
        }
        catch (IOException e) {
            log.error("文件下载io流异常");
        }
    }
    
    @RequestMapping("/test/license/checkDownloadWciFile")
    @ResponseBody
    public AjaxResult checkDownloadWciFile(LicenseEntity entity){
        AjaxResult result = new AjaxResult(1);
        try {
            //加密许可信息
            if (LicenseManager.encryptWciInfo(entity) == null) {
                result.setError(0);
                result.setMsg("检测下载wci文件失败，请重试");
            }
        }
        catch (FileNotFoundException e) {
            result.setError(0);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
