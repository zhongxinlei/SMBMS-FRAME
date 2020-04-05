package cn.smbms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.html")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,
						@RequestParam String password,
						HttpServletRequest request,
						HttpSession session) {
		User user = null;
		try {
			user = userService.login(userCode, password);
			if (user!=null) {
				session.setAttribute(Constants.USER_SESSION, user);
				return "redirect:/sys/main.html";
			}else {
				request.setAttribute("error", "用户名或密码不正确！");
				return "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e);
			return "500";
		}
	}
	
	@RequestMapping(value="logout.html")
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.USER_SESSION);
		return "login";
	}
	
	@RequestMapping(value="/sys/main.html")
	public String main() {
		return "frame";
	}

}
