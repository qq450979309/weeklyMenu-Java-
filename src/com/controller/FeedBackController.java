package com.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.FeedBack;
import com.service.FeedBackService;

@Controller
@RequestMapping("/feedBack")
public class FeedBackController {

	@Autowired
	private FeedBackService feedBackService = null;

	public FeedBackService getFeedBackService() {
		return feedBackService;
	}

	public void setFeedBackService(FeedBackService feedBackService) {
		this.feedBackService = feedBackService;
	}
	
//	获取反馈列表
	@ResponseBody
	@RequestMapping(value ="/getFeedBackList")
	public Map<String, Object> getFeedBackList(HttpSession session, @RequestParam(value="isReplied", required=false)Integer isReplied, @RequestParam(value="order", required=false)String order){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			List<FeedBack> feedBack = feedBackService.getFeedBackList(isReplied, order, uid);
			Map<String, Object> listMap=new HashMap<String, Object>();
			listMap.put("myList", feedBack);
			map.put("msg", "获取反馈列表成功");
			map.put("relatedObject", listMap);
			map.put("success", true);
		}
		return map;
	}

//	根据fid获取反馈详情
	@ResponseBody
	@RequestMapping(value ="/getFeedBackListById")
	public Map<String, Object> getFeedBackListById(HttpSession session, @RequestParam(value="fid")int fid){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			FeedBack feedBack = feedBackService.getFeedBackListById(fid);
			map.put("msg", "获取反馈详情成功");
			map.put("relatedObject", feedBack);
			map.put("success", true);
		}
		return map;
	}
	
//	新增反馈
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> insertFeedBack(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="content")String content) {
		String uid=(String)session.getAttribute("uid_session");
		FeedBack fb = new FeedBack();
		fb.setCreateTime(new Date());
		fb.setUserId(Integer.valueOf(uid));
		fb.setContent(content);
		boolean isAdd = feedBackService.insertFeedBack(fb);
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "提交反馈成功");
			map.put("success", true);
		} else {
			map.put("msg", "提交反馈失败");
			map.put("success", false);
		}

		return map;
	}
}
