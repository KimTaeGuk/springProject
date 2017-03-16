package tq.spring.project;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tq.spring.dao.userDao;
import tq.spring.dao.userEmailSender;
import tq.spring.dao.impl.boardDaoImpl;
import tq.spring.dao.impl.commentDaoImpl;
import tq.spring.dao.impl.replyDaoImpl;
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
	private replyDaoImpl replyDaoImpl;
	
	@RequestMapping(value = {"/", "/home", "/welcome"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
					
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String userId=(String)session.getAttribute("userId");
		
		if(authentication.getName().equals("anonymousUser")){
				System.out.println("익명");
		}	else {
				session.setAttribute("userId",authentication.getName());
				System.out.println((String)session.getAttribute("userId"));
		}
		
		//이미지 찾는 것입니다.
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		String userImg=userDaoImpl.userImgView(userId);
		
		model.addAttribute("userImg",userImg);
		System.out.println(userImg);
		
		return "home";
	}
	
	@RequestMapping(value="userModify", method=RequestMethod.GET)
	public String userModifyView(
			@RequestParam Map<String, Object> paramMap, 
			HttpSession session,
			Model model){
		
		String userId=(String)session.getAttribute("userId");
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		
		model.addAttribute("userDto", userDaoImpl.userModifyView(userId));
		
		return "signIn/userModify";
	}
	
	//회원 정보 수정 컨트롤러입니다.
	@RequestMapping(value="userModify", method=RequestMethod.POST)
	public String userModifyProc(){
		
		return "home";
	}
	
	//회원 이미지 수정 컨트롤러입니다.
	@RequestMapping(value="userImgModify", method=RequestMethod.GET)
	public String userImgModifyView(HttpSession session, Model model){

		String userId=(String)session.getAttribute("userId");
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		
		model.addAttribute("userDto", userDaoImpl.userModifyView(userId));
		return "signIn/userImgModify";
	}
	
	@RequestMapping(value="userImgModify", method=RequestMethod.POST)
	public @ResponseBody String userImgModifyProc(
			HttpSession session,
			@Param("userImg") String userImg,
			@RequestParam("file") MultipartFile file){
		
		//파일 경로입니다.
		String rootPath="C:\\Users\\itwill\\Desktop\\springProject\\src\\main\\webapp\\resources\\images\\userImg\\";

		//먼저 기존 이미지를 삭제해줍니다.
		
		System.out.println(userImg);
		File oldFile=new File(rootPath+userImg);
		if(oldFile.isFile()) {oldFile.delete();}
		
		Calendar calendar=Calendar.getInstance();
		Date date=calendar.getTime();
		String today=(new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		
		String userId=(String)session.getAttribute("userId");
		
		File dir=new File(rootPath+File.separator);

		if(!dir.exists()) dir.mkdirs();
		
		if(!file.isEmpty()){
			try{
				byte[] bytes=file.getBytes();
				
				String fileName=today+userId+file.getOriginalFilename();
				
				File serverFile=new File(dir.getAbsolutePath()+File.separator+fileName);
				BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
					
				//이미지 이름 DB에 넣어주기
				userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
				userDaoImpl.userImgUpload(userId, fileName);
				
				return "You successfully upload file";
			}catch(Exception e){
				return "You failed to upload "+file.getOriginalFilename()+ " => "+ e.getMessage();
			}
		}	else {
			
			//파일이 존재하지 않을 때 입니다.
			userDaoImpl.userImgUpload(userId, null);
			
			return "You failed to Upload, because the file was empty";
		}
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
			@Param("userEmailId") String userEmailId,
			@Param("userName") String userName,
			@Param("userEmailAddress") String userEmailAddress,
			HttpServletRequest request,
			Model model){

		//비밀번호 암호화 입니다.
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String hashedPassword=passwordEncoder.encode(userPassword);	
		
		String userEmail=userEmailId+"@"+userEmailAddress;
		String userBirth=request.getParameter("register_year")+"/"+request.getParameter("register_month")+"/"+request.getParameter("register_day");
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		userDaoImpl.userInsert(userId, hashedPassword, userEmail, userName, userBirth);
		
		return "home";
	}
	
	//회원 이미지 설정 이동입니다.
	@RequestMapping(value="/UserImgUpload", method=RequestMethod.GET)
	public String UserImgUploadView(){
		return "signIn/UserImgUpload";
	}
	
	//회원가입 이미지 처리입니다.
	@RequestMapping(value="/UserImgUpload", method=RequestMethod.POST)
	public @ResponseBody String UserImgUploadProc(
			HttpSession session,
			@RequestParam("file") MultipartFile file){

		Calendar calendar=Calendar.getInstance();
		Date date=calendar.getTime();
		String today=(new SimpleDateFormat("yyyyMMddHHmmss").format(date));

		String userId=(String)session.getAttribute("userId");
		
		//파일 경로입니다.
		String rootPath="C:\\Users\\itwill\\Desktop\\springProject\\src\\main\\webapp\\resources\\images\\userImg\\";
		File dir=new File(rootPath+File.separator);

		if(!dir.exists()) dir.mkdirs();
		
			
		if(!file.isEmpty()){
			try{
				byte[] bytes=file.getBytes();
				
				String fileName=today+userId+file.getOriginalFilename();
				
				File serverFile=new File(dir.getAbsolutePath()+File.separator+fileName);
				BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
					
				//이미지 이름 DB에 넣어주기
				userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
				userDaoImpl.userImgUpload(userId, fileName);
				
				return "You successfully upload file";
			}catch(Exception e){
				return "You failed to upload "+file.getOriginalFilename()+ " => "+ e.getMessage();
			}
		}	else {
			
			//파일이 존재하지 않을 때 입니다.
			userDaoImpl.userImgUpload(userId, null);
			
			return "You failed to Upload, because the file was empty";
		}
	}
	
	//아이디 확인 페이지입니다.
	@RequestMapping(value="/checkId", method=RequestMethod.GET)
	public String checkId(@RequestParam Map<String, Object> paramMap,Model model){

		String userId=(String)paramMap.get("userId");
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		model.addAttribute("userCheckId",userDaoImpl.userCheckId(userId));
		
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
		replyDaoImpl=sqlSession.getMapper(replyDaoImpl.class);
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		
		boardDaoImpl.boardDelete(boardNum);
		boardDaoImpl.boardDelUpdateNum(boardNum);	//글 삭제 시 번호 내려 주기
		
		commentDaoImpl.boardDel(boardNum);
		commentDaoImpl.boardNumUp(boardNum);
	
		replyDaoImpl.boardDel(boardNum);
		replyDaoImpl.boardDelNumUp(boardNum);
		
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
		replyDaoImpl=sqlSession.getMapper(replyDaoImpl.class);
		
		commentDaoImpl.commentDelete(boardNum, commentNum);
		commentDaoImpl.commentNumUp(boardNum, commentNum);
		
		replyDaoImpl.commentDel(boardNum, commentNum);
		replyDaoImpl.commentDelNumUp(boardNum, commentNum);
		
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

	@RequestMapping(value="replyList", method=RequestMethod.GET)
	public String replyList(@RequestParam Map<String, Object> paramMap, Model model){
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		
		replyDaoImpl=sqlSession.getMapper(replyDaoImpl.class);
		
		model.addAttribute("boardNum",boardNum);
		model.addAttribute("commentNum",commentNum);
		model.addAttribute("replyList",replyDaoImpl.replyList(boardNum, commentNum));
		
		return "board/reply/replyView";
	}
	
	@RequestMapping(value="replyInsert", method=RequestMethod.POST)
	public String replyInsert(@RequestParam Map<String, Object> paramMap, Model model){
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		String replyId=(String)paramMap.get("replyId");
		String replyContent=(String)paramMap.get("replyContent");
		
		replyDaoImpl=sqlSession.getMapper(replyDaoImpl.class);
		Integer replyNum=replyDaoImpl.replyMax(boardNum, commentNum)+1;
		replyDaoImpl.replyInsert(boardNum, commentNum, replyNum, replyId, replyContent);

		return "redirect:boardView?boardNum="+(String)paramMap.get("boardNum");	
	}
	
	@RequestMapping(value="replyDelete", method=RequestMethod.GET)
	public String replyDelete(@RequestParam Map<String, Object> paramMap, Model model){
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		Integer replyNum=Integer.parseInt((String)paramMap.get("replyNum"));
		
		replyDaoImpl=sqlSession.getMapper(replyDaoImpl.class);
		replyDaoImpl.replyDelete(boardNum, commentNum, replyNum);
		replyDaoImpl.replyNumUp(boardNum, commentNum, replyNum);
		
		return "redirect:boardView?boardNum="+(String)paramMap.get("boardNum");	
	}
	
	@RequestMapping(value="replyModify", method=RequestMethod.GET)
	public String replyModifyView(@RequestParam Map<String, Object> paramMap, Model model){
		
		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		Integer replyNum=Integer.parseInt((String)paramMap.get("replyNum"));
		
		replyDaoImpl=sqlSession.getMapper(replyDaoImpl.class);
		
		model.addAttribute("replyModify",replyDaoImpl.replyModifyView(boardNum, commentNum, replyNum));
		
		return "board/reply/replyModify";
	}
	
	@RequestMapping(value="replyModify", method=RequestMethod.POST)
	public String replyModifyProc(@RequestParam Map<String, Object> paramMap){

		Integer boardNum=Integer.parseInt((String)paramMap.get("boardNum"));
		Integer commentNum=Integer.parseInt((String)paramMap.get("commentNum"));
		Integer replyNum=Integer.parseInt((String)paramMap.get("replyNum"));		
		String replyContent=(String)paramMap.get("replyContent");
		
		replyDaoImpl=sqlSession.getMapper(replyDaoImpl.class);		
		replyDaoImpl.replyModifyProc(boardNum, commentNum, replyNum, replyContent);
		
		return "redirect:boardView?boardNum="+(String)paramMap.get("boardNum");	
	}
	
	@RequestMapping(value="boardSearch", method=RequestMethod.GET)
	public String boardSearch(@Param("keyword") String keyword, Model model){
		
		boardDaoImpl=sqlSession.getMapper(boardDaoImpl.class);
		
		model.addAttribute("boardSearch", boardDaoImpl.boardSearch(keyword));
		
		return "board/boardSearch";
	}
	
	@RequestMapping(value="searchId", method=RequestMethod.GET)
	public String searchIdView(){
		return "signIn/searchId";
	}
	
	@RequestMapping(value="searchId", method=RequestMethod.POST)
	public String searchIdProc(HttpServletRequest request, Model model){
		
		String userName=request.getParameter("userName");
		String userBirth=request.getParameter("register_year")+"/"+request.getParameter("register_month")+"/"+request.getParameter("register_day");
		
		System.out.println(userName);
		System.out.println(userBirth);
		
		userDaoImpl=sqlSession.getMapper(userDaoImpl.class);
		model.addAttribute("searchId", userDaoImpl.userSearchId(userName, userBirth));
		
		System.out.println(userDaoImpl.userSearchId(userName, userBirth));
		
		return "signIn/searchConfirmId";
	}
}