package cn.lys.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.lys.entity.User;
import cn.lys.entity.UsersEquipment;
import cn.lys.repository.UserRepository;
import cn.lys.repository.UsersEquipmentRepository;
import cn.lys.utils.CodeUtil;

@Controller
public class CodeLoginController {
	
	@Value("${loginUri}")
	private String loginUri;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private UsersEquipmentRepository usersEquipmentRepository;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "loginPage";
	}
	
	@RequestMapping("/makeLoginCode")
	@ResponseBody
	public String makeLoginCode(HttpServletRequest request) {
		//生成随机数ticket，用于存储每次生成登录验证码信息
		String ticket = UUID.randomUUID().toString();
		//将ticket存储到redis  key为ticket  value此时为空串
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(ticket,"",1,TimeUnit.MINUTES);
		//拼接存储二维码生成位置
		String rootdir = this.getClass().getResource("/").getPath().substring(1);
		String sendpath = "codes/"+ ticket + ".png";
		String codeImgPath = rootdir +"static/"+ sendpath;
		System.out.println(codeImgPath);
		//设置二维码存储信息
		Map<String, String> map = new HashMap<String,String>();
		map.put("ticket",ticket);
		map.put("action",loginUri);
		String content = JSON.toJSONString(map);
		System.out.println(content);
		CodeUtil.code(content, codeImgPath);
		
		request.setAttribute("ticket",ticket);
		return sendpath;
	}
	
	@RequestMapping("/login/{ticket}/{equipmentId}")
	@ResponseBody
	public String login(@PathVariable String ticket,
			@PathVariable String equipmentId) {
		UsersEquipment usersEquipment = usersEquipmentRepository.findByEquipmentId(equipmentId);
		if(usersEquipment == null || StringUtils.isEmpty(usersEquipment.getUserId())) {
			return "error";
		}
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		User user = userRepository.findOne(usersEquipment.getUserId());
		String userJson = JSON.toJSONString(user);
		opsForValue.set(ticket,userJson);
		return userJson;
	}
	
	
	@RequestMapping("/readItHasTicketVal/{ticket}")
	public String readItHasTicketVal(@PathVariable String ticket,
			HttpServletRequest request){
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		String userJson = opsForValue.get(ticket);
		if(StringUtils.isEmpty(userJson)) {
			return "redirect:/loginPage";
		}
		request.getSession(true).setAttribute("user",JSON.parseObject(userJson,User.class));
		return "index";
	}
	
	
	
}
