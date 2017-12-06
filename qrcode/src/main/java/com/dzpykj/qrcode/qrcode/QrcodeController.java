package com.dzpykj.qrcode.qrcode;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzpykj.common.utils.QrcodeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/qrcode")
public class QrcodeController {
	
//	@RequestMapping(value="/generate",produces="application/json;charset=UTF-8")
	@RequestMapping(value="/generate",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object generateQrode(HttpServletRequest req) throws JsonProcessingException{
		System.out.println("<<<<<>>>>>");
		StringBuilder sb = new StringBuilder();
		String telephone = req.getParameter("telephone");//手机号
		String username = req.getParameter("username");//姓名
		String email = req.getParameter("email");//邮箱
		String address = req.getParameter("address");//家庭住址
		String organization = req.getParameter("organization");
		String title = req.getParameter("job");
		String bday = req.getParameter("bday");
		String note = req.getParameter("note");
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		if(telephone != null && !"".equals(telephone)){
			sb.append("TEL:"+telephone+"\n");
		} else {
			return "Telephone number is needed";
		}
		if(username != null && !"".equals(username)){
			sb.append("N:"+username+"\n");
		} else {
			return "Name is needed";
		}
		if(email != null && !"".equals(email)){
			sb.append("EMAIL:"+email+"\n");
		}
		if(address != null && !"".equals(address)){
			sb.append("ADR:"+address+"\n");
		}
		if(organization != null && !"".equals(organization)){
			sb.append("ORG:"+organization+"\n");
		}
		if(title != null && !"".equals(title)){
			sb.append("TITLE:"+title+"\n");
		}
		if(bday != null && !"".equals(bday)){
			sb.append("BDAY:"+bday+"\n");
		}
		if(note != null && !"".equals(note)){
			sb.append("NOTE:"+note+"\n");
		}
		sb.append("END:VCARD");
		
		String base = req.getServletContext().getRealPath("/");
		System.out.println(base);
//		String imgPath = base+"qrcodeImgs\\"+telephone+".png";
		
		String imgPath = "D:\\Eclipse\\LearningSpace\\qrcode\\src\\main\\webapp\\qrcodeImgs\\"+telephone+".png";
		System.out.println(imgPath);
		try {
			QrcodeUtil.encoderQRCode(sb.toString(), imgPath, "png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//当@RequestMapping(value="/generate",produces="application/json;charset=UTF-8")时,用此段代码
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		return mapper.writeValueAsString(telephone+".png");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return telephone+".png";
	}
}
