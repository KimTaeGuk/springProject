package tq.spring.project;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tq.spring.dao.userDao;
import tq.spring.dao.impl.userDaoImpl;
import tq.spring.dto.userDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	private userDaoImpl userDaoImpl;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
				
		return "home";
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome(Locale locale, Model model){
		
		System.out.println("controllerWelcome()");
		
		return "signIn/welcome";
	}
	
	//회원가입 페이지입니다.
	@RequestMapping("/signUp")
	public String signUp(Model model){
		
		System.out.println("controllerSingUp()");
		
		return "signUp/signUp";
	}
	
	//회원가입 처리 기능입니다.
	@RequestMapping(value="/signUpProc", method=RequestMethod.POST)
	public String signUpProc(
			@Param("userId") String userId,
			@Param("userPassword") String userPassword,
			@Param("userEmail") String userEmail,
			@Param("userName") String userName,
			HttpServletRequest request,
			Model model){
		
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String hashedPassword=passwordEncoder.encode(userPassword);
		
		System.out.println("controllerSignUpProc()");
		
		String userBirth=request.getParameter("register_year")+"/"+request.getParameter("register_month")+"/"+request.getParameter("register_day");
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		userDaoImpl.userInsert(userId, hashedPassword, userEmail, userName, userBirth);
		
		return "home";
	}
	
	//아이디 확인 페이지입니다.
	@RequestMapping(value="/checkId", method=RequestMethod.GET)
	public String checkId(HttpServletRequest request,Model model){
		System.out.println("controllerCheckId()");	
		
		return "signUp/checkId";
	}
	
	//로그인 처리 페이지 입니다.
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		
		return "signIn/login";
	}
	
	//로그인 페이지입니다.
	@RequestMapping(value="/loginProc", method=RequestMethod.POST)
	public String loginProc(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout",required=false) String logout){
		
		logger.info("controllerLogin()");
		
		ModelAndView model=new ModelAndView();
		
		if(error != null){
			model.addObject("error","Invaild username and password");
		}
		if(logout != null){
			model.addObject("msg","You've been logged out successfully");
		}
		
		return "login";
	}
	
	
}

