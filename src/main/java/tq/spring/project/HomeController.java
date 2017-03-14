package tq.spring.project;

import java.util.Locale;
import java.util.Map;

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
import tq.spring.dao.userEmailSender;
import tq.spring.dao.impl.boardDaoImpl;
import tq.spring.dao.impl.commentDaoImpl;
import tq.spring.dao.impl.userDaoImpl;
import tq.spring.dto.userDto;
import tq.spring.dto.userEmailDto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Autowired
	private userEmailSender emailSender;
	
	@Autowired
	private userEmailDto email;

	private userDaoImpl userDaoImpl;
	private boardDaoImpl boardDaoImpl;
	private commentDaoImpl commentDaoImpl;
	
	@RequestMapping(value = {"/", "/home", "/welcome"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
					
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication.getName().equals("anonymousUser")){
				System.out.println("익명");
		}	else {
				session.setAttribute("userId",authentication.getName());
				System.out.println((String)session.getAttribute("userId"));
		}
		
		
		return "home";
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////회원가입///////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
	//회원가입 페이지입니다.
	@RequestMapping("/signUp")
	public String signUp(Model model){

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

		//비밀번호 암호화 입니다.
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String hashedPassword=passwordEncoder.encode(userPassword);	
		
		String userBirth=request.getParameter("register_year")+"/"+request.getParameter("register_month")+"/"+request.getParameter("register_day");
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		userDaoImpl.userInsert(userId, hashedPassword, userEmail, userName, userBirth);
		
		return "home";
	}
	
	//아이디 확인 페이지입니다.
	@RequestMapping(value="/checkId", method=RequestMethod.GET)
	public String checkId(HttpServletRequest request,Model model){

		return "signUp/checkId";
	}
	
	//로그인 페이지 입니다.
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){

		return "signIn/login";
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////메일 보내기///////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	
	//메일 보내기 페이지입니다.
	@RequestMapping("/mailView")
	public String mailView(){

		return "mailView";
	}
	
	//메일 보내는 기능입니다.
	@RequestMapping(value="/mailSend", method=RequestMethod.POST)
	public String mailSend(@RequestParam Map<String, Object> paramMap, Model model) throws Exception{
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		
		userDao userDao=new userDao();
		String userId=(String)paramMap.get("userId");
		String userName=(String)paramMap.get("userName");
		String userEmail=userDaoImpl.userEmailSearch(userId, userName);	//받을 이메일 번호는 DB에서 끌어오기
		String userPassword=userDao.userNewPassword();		//새로운 패스워드 생성입니다.
				
		email.setSubject(userId+"님의 새로운 비밀번호입니다.");
		email.setContent("새로운 비밀번호는" + userPassword + "입니다");
		email.setReceiver(userEmail);

		//비밀번호 암호화 입니다.
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String hashedPassword=passwordEncoder.encode(userPassword);	
		
		userDaoImpl.userpasswordUpdate(userId, userName, hashedPassword);		//비밀번호 업데이트	

		emailSender.SendMail(email);		

		return "home";
	}
	
	/////////////////////////////////////////////////////////////////////
	//////////////////////////////////게시판 리스트//////////////////////
	///////////////////////////////////////////////////////////////////
	
	@RequestMapping("/boardList")
	public String boardList(Model model){

		boardDaoImpl=sqlSession.getMapper(boardDaoImpl.class);

		model.addAttribute("boardList", boardDaoImpl.boardList());
		
		return "board/boardList";
	}
	
	//글쓰기 이동 페이지입니다.
	@RequestMapping("/boardWrite")
	public String boardWrite(){	
		return "board/boardWrite";
	}
	
	//글쓰기 처리 메소드입니다.
	@RequestMapping(value="/boardWriteProc", method=RequestMethod.POST)
	public String boardWriteProc(@RequestParam Map<String, Object> paramMap, Model model){

		boardDaoImpl=sqlSession.getMapper(boardDaoImpl.class);
		
		String boardId=(String)paramMap.get("boardId");
		String boardSubject=(String)paramMap.get("boardSubject");
		String boardContent=(String)paramMap.get("boardContent");
		Integer boardNum=boardDaoImpl.boardMax()+1;

		boardDaoImpl.boardInsert(boardNum, boardId, boardSubject, boardContent);
		
		return "redirect:boardList";
	}
	
	@RequestMapping(value="/boardView", method=RequestMethod.GET)
	public String boardView(@RequestParam Map<String, Object> paramMap, Model model){
		
		boardDaoImpl=sqlSession.getMapper(boardDaoImpl.class);
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		
		
		boardDaoImpl.boardCount(boardNum);
		model.addAttribute("boardView",boardDaoImpl.boardView(boardNum));
		
		return "board/boardView";
	}
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(@RequestParam Map<String, Object> paramMap, Model model){
		
		boardDaoImpl=sqlSession.getMapper(boardDaoImpl.class);
		commentDaoImpl=sqlSession.getMapper(commentDaoImpl.class);
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		
		boardDaoImpl.boardDelete(boardNum);
		boardDaoImpl.boardDelUpdateNum(boardNum);	//글 삭제 시 번호 내려 주기
		
		commentDaoImpl.boardDel(boardNum);
		commentDaoImpl.boardNumUp(boardNum);
	
		return "redirect:boardList";
	}

	@RequestMapping(value="/boardModify", method=RequestMethod.GET)
	public String boardModifyView(
			@RequestParam Map<String, Object> paramMap,Model model){
			
		model.addAttribute("boardNum", paramMap.get("boardNum"));
		
		return "board/boardModify";
		
	}
	
	@RequestMapping(value="/boardModify", method=RequestMethod.POST)
	public String boardModifyProc(@RequestParam Map<String,Object> paramMap){
				
		boardDaoImpl=sqlSession.getMapper(boardDaoImpl.class);
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		String boardSubject=(String)paramMap.get("boardSubject");
		String boardContent=(String)paramMap.get("boardContent");
		
		boardDaoImpl.boardModify(boardNum, boardSubject, boardContent);
		
		return "redirect:boardList";
	}
	
	@RequestMapping(value ="/commentList", method=RequestMethod.GET)
	public String commentList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception{
		System.out.println("controllerCommentList()");
	
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));

		commentDaoImpl=sqlSession.getMapper(commentDaoImpl.class);		
		model.addAttribute("commentList", commentDaoImpl.commentList(boardNum));
		
		return "board/comment/commentView";
	}
	
	@RequestMapping(value="commentDelete", method=RequestMethod.GET)
	public String commentDelete(@RequestParam Map<String, Object> paramMap){
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		
		commentDaoImpl=sqlSession.getMapper(commentDaoImpl.class);
		commentDaoImpl.commentDelete(boardNum, commentNum);
		commentDaoImpl.commentNumUp(boardNum, commentNum);
		
		return "redirect:boardView?boardNum="+(String)paramMap.get("boardNum");
	}
	
	@RequestMapping(value="commentInsert", method=RequestMethod.POST)
	public String commentInsert(@RequestParam Map<String, Object> paramMap){
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		String commentId=(String)paramMap.get("commentId");
		String commentContent=(String)paramMap.get("commentContent");
		
		commentDaoImpl=sqlSession.getMapper(commentDaoImpl.class);
		
		Integer commentNum=commentDaoImpl.commentMax(boardNum)+1;
			
		commentDaoImpl.commentInsert(boardNum, commentNum, commentId, commentContent);
		
		return "redirect:boardList";
	}
	
	@RequestMapping(value="commentModify", method=RequestMethod.GET)
	public String commentModifyView(@RequestParam Map<String, Object> paramMap, Model model){

		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		
		commentDaoImpl=sqlSession.getMapper(commentDaoImpl.class);
		
		model.addAttribute("comment", commentDaoImpl.commentModifyView(boardNum, commentNum));
		
		return "board/comment/commentModify";
	}
	
	@RequestMapping(value="commentModify", method=RequestMethod.POST)
	public String commentModifyProc(@RequestParam Map<String, Object> paramMap){
		
		commentDaoImpl=sqlSession.getMapper(commentDaoImpl.class);
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		String commentContent=(String)paramMap.get("commentContent");
		
		commentDaoImpl.commentModify(boardNum, commentNum, commentContent);
		
		return "redirect:boardView?boardNum="+(String)paramMap.get("boardNum");	
	}
	
}

