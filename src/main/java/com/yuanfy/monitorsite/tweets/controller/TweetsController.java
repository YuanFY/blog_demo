package com.yuanfy.monitorsite.tweets.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.yuanfy.monitorsite.common.util.DateUtils;
import com.yuanfy.monitorsite.framework.SessionUtil;
import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.framework.dto.TableResult;
import com.yuanfy.monitorsite.system.entity.UserEntity;
import com.yuanfy.monitorsite.tweets.entity.TweetsEntity;
import com.yuanfy.monitorsite.tweets.service.TweetsService;

/**
 * @description TODO
 * @author YuanFY 
 * @date 2017年2月11日 下午9:50:47
 * @version 1.0
 */
@Controller
public class TweetsController{
	final static Logger log = Logger.getLogger(TweetsController.class);
	
	@Autowired
	private TweetsService tweetsService;
	
	@RequestMapping(value = "/tweets/index")
	public String index(HttpServletRequest request,Model model) {
		model.addAttribute("listSize", tweetsService.getCount());
		return "/tweets/index";
	}
	
	@RequestMapping(value = "/tweets/save")
	@ResponseBody
	public AjaxResult save(TweetsEntity entity, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(0);
		try{
			//1、 先获取用户信息
			UserEntity user = SessionUtil.getUser(request);
			if (user == null) {
				result.setError(-1);
				result.setMsg("请先登录");
				return result;
			}
			//2 发布动弹
			entity.setUserId(user.getId());
			entity.setTweetsTime(System.currentTimeMillis());
			tweetsService.insert(entity);
			result.setError(1);
		} catch (Exception e) {
			log.error("发表动弹内容失败：", e);
			result.setMsg("发布失败，请联系管理员！");
		}
		return result;
	}
	
	@RequestMapping(value = "/tweets/list")
	@ResponseBody
	public TableResult<TweetsEntity> list(TweetsEntity entity, int page,int limit, HttpServletRequest request) {
		TableResult<TweetsEntity> result = new TableResult<TweetsEntity>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", (page-1) * limit);
		params.put("limit", limit);
		result.setData(tweetsService.findAll(params));
		return result;
	}
	
	@RequestMapping(value="/tweets/uploadImg")
    @ResponseBody
    public AjaxResult upload(HttpServletRequest request){
		AjaxResult result = new AjaxResult(0);
        //1.根据servletContext获取多文件上传解析组件
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (!multipartResolver.isMultipart(request)) {
        	result.setMsg("不是上传文件表单，请检查表单属性");
            return result;
        }
        //2.将请求对象转换为多文件请求对象。
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        //3、根据多文件请求对象获取文件存放Map
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        Iterator<Entry<String, MultipartFile>> iterator = fileMap.entrySet().iterator();
        
        //获取存放图片的路径
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        
        //4、迭代文件Map,获取具体的MultipartFile
        while (iterator.hasNext()) {
            try {
            	Entry<String, MultipartFile> entry = iterator.next();
                MultipartFile multipartFile = entry.getValue();
            	String fileName =  multipartFile.getOriginalFilename();
            	if (!(fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".gif"))) {
            		result.setError(0);
            		result.setMsg("请选择后缀名为.png或.jpg或.gif类型的图片");
            		return result;
            	}
            	String tmpFileName = rootPath + "tweetsImg/" + DateUtils.dateFormatToTimestamp() + fileName.substring(fileName.lastIndexOf("."));
            	//上传文件
                multipartFile.transferTo(new File(tmpFileName));
            }catch (Exception e) {
            	result.setMsg("上传文件失败，请重试");
                return result;
            }
        }
        result.setError(1);
        return result;
    }
	public static void main(String[] args) {
		String relativelyPath=System.getProperty("user.dir"); 
		System.out.println(relativelyPath);
		System.out.println("t.gif".substring("t.gif".lastIndexOf(".")));
	}
}
