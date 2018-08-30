package com.flyingDog.controller;

import com.flyingDog.model.User;
import com.flyingDog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	UserService userService;
	@RequestMapping("/index")
	public String index(HashMap<String, Object> map) {
        //获取配置文件中的信息vv
        //传到template模板中（会把模板中能找到的所有的ftl文件中的名称都替换掉）
        map.put("hello", "欢迎进入HTML页面");
        //返回页面hello.ftl
        return "main/index";
	}
	@RequestMapping("/test")
	@ResponseBody
	public String  index() {
		//redis
		//mybatis
		User user = userService.selectByPrimaryKey(1);
        System.out.println(user.toString());
		return "success";
	}
}
