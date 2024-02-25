package com.ia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ia.model.PillsCounterVO;
import com.ia.model.WebResponseJsonBo;
import com.ia.service.IAService;

@RestController
@RequestMapping(value = "/api")
public class IAController {

	@Autowired
	IAService iaService;
	
	@GetMapping(value = "/loadUploadImg")
	public ModelAndView loadUploadImg(HttpServletRequest req,HttpServletResponse res)
	{
		return new ModelAndView("/loadUploadImg");
	}
	
	@PostMapping(value = "/uploadImg")
	public ResponseEntity<WebResponseJsonBo>  saveUploadImg(HttpServletRequest req,HttpServletResponse res,@ModelAttribute("imageForm") PillsCounterVO imageForm)
	{
		WebResponseJsonBo returnBo=new WebResponseJsonBo();
		PillsCounterVO retuCounterVO= iaService.saveUploadImg(imageForm);
		returnBo.setRetResponse(retuCounterVO);
		return ResponseEntity.ok(returnBo);
	}
	
	@GetMapping(value = "/uploadImgAug")
	public ResponseEntity<WebResponseJsonBo> uploadImgAug(HttpServletRequest req,HttpServletResponse res,@ModelAttribute("imageForm") PillsCounterVO imageForm)
	{
		WebResponseJsonBo returnBo=new WebResponseJsonBo();
		iaService.uploadImgAug();
		return ResponseEntity.ok(returnBo);
	}
	
	@GetMapping(value = "/getUploadImg")
	public ResponseEntity<WebResponseJsonBo> yourEndpoint() {
        // Your controller logic
		WebResponseJsonBo returnBo=new WebResponseJsonBo();
		List<PillsCounterVO> listImgPill= iaService.getUploadImg();
		returnBo.setRetResponse(listImgPill);
		return ResponseEntity.ok(returnBo);
    }
}
