package cn.lys.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.lys.entity.User;
import cn.lys.entity.UsersEquipment;
import cn.lys.repository.UsersEquipmentRepository;
import cn.lys.utils.CodeUtil;

@Controller
public class CodeBindController {
	
	@Value("${bindUri}")
	private String bindUri;
	
	@Autowired()
	private UsersEquipmentRepository usersEquipmentRepository;
	
	@RequestMapping("/bindPage")
	public String bindPage() {
		return "bindPage";
	}
	
	@RequestMapping("/makeBindCode")
	@ResponseBody
	public String makeBindCode(HttpServletRequest request) {
		
		//模拟用户登录后存储的session
		request.getSession(true).setAttribute("user",new User("10086","张三"));
		
		User user = (User) request.getSession(false).getAttribute("user");
		String rootPath = this.getClass().getResource("/").getPath().substring(1);
		String realPath = "codes/"+UUID.randomUUID().toString()+".png";
		String codePath = rootPath + "static/" + realPath;
		//二维码所含信息
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId",user.getId());
		map.put("action",bindUri);
		String content = JSON.toJSONString(map);
		CodeUtil.code(content, codePath);
		return realPath;
	}
	
	@RequestMapping(value="/bind/{equipmentId}/{userId}")
	@ResponseBody
	public String bind(@PathVariable String equipmentId,
			@PathVariable String userId) {
		UsersEquipment usersEquipment = new UsersEquipment();
		usersEquipment.setId(UUID.randomUUID().toString());
		usersEquipment.setUserId(userId);
		usersEquipment.setEquipmentId(equipmentId);
		usersEquipmentRepository.save(usersEquipment);
		return JSON.toJSONString(usersEquipment);
	}
	
}
