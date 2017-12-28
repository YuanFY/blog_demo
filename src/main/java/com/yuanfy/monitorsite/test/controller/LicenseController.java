package com.yuanfy.monitorsite.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @RequestMapping("/test/license/downloadWciFile")
    public void downloadWCIFile(LicenseEntity entity){
        //加密许可信息
        
    }
    
    @RequestMapping("/test/license/checkDownloadWciFile")
    public AjaxResult checkDownloadWciFile(LicenseEntity entity){
        //加密许可信息
        
    }
}
