package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.EmaillistDao;
import com.javaex.vo.EmaillistVo;

@Controller //이 컨트롤러만을 뒤져라.
public class EmaillistController {
	
	@Autowired //Controller와 dao를 자동연결해줘.
	private EmaillistDao emaillistDao; // new할 필요 없도록 코딩
	//이제 controller에서 dao를 뒤져서, 찾음.(@repository 되어있는 dao만을 찾음)
	
	//localhost:8088/emaillist3/form
	@RequestMapping(value="/form")
	public String form() {
		return "form"; //뷰 리졸버 세팅에 의해, 주소값을 줄일 수 있음.
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(@ModelAttribute EmaillistVo emaillistVo ) {
		System.out.println(emaillistVo.toString());
		//emaillistDao의 new는 Autowired에 의해 생략
		//emaillistVo의 new는 @ModelAttribute에 의해 생략
		emaillistDao.insert(emaillistVo); 		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET) //이렇게 요청받으면 주소에 값이 공개됨.
	public String list(Model model) {
		List<EmaillistVo> list=emaillistDao.getList();
		model.addAttribute("elist",list);
		return "list";
	}
	
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public String add(@ModelAttribute EmaillistVo emaillistVo, Model model) {
//		System.out.println(emaillistVo.toString());
//		//emaillistDao의 new는 Autowired에 의해 생략
//		//emaillistVo의 new는 @ModelAttribute에 의해 생략
//		emaillistDao.insert(emaillistVo); 	
//		
//		List<EmaillistVo> list=emaillistDao.getList();
//		model.addAttribute("elist",list);
//		return "list";
//	}
//	add기능과 list기능을 합쳐 놓은 것인데, 이렇게 해도 돌아가지만, 따로 만드는 것이 에러 디버깅시 더 좋음.
	
}
