package com.tw.controller.sys;

import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tw.util.EmailSender;

@Controller
public class IndexController {

	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "/index";
	}
	@RequestMapping("/init")
	public String init(HttpServletRequest request) {
		Properties prop = new Properties();
		prop.put("runtime.log", request.getServletContext().getRealPath("/WEB-INF/vel/log/velocity.log"));
		prop.put("file.resource.loader.path", request.getServletContext().getRealPath("/WEB-INF/vel/vm"));
		prop.put("input.encoding", "UTF-8");
		prop.put("output.encoding", "UTF-8");
		Velocity.init(prop);
		return "/index";
	}
	@RequestMapping("/testv")
	public void testv() {
		VelocityContext context = new VelocityContext();
		Template template = Velocity.getTemplate("notice.vm");
		context.put("U", "qqq");
		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		sw.flush();
		String mailContent = sw.toString();
//		EmailSender.send("weir2009@sogou.com", "test", mailContent);
		EmailSender.send("634623907@qq.com", "test", mailContent);
//		EmailSender.send("983554383@qq.com", "test", mailContent);
//		EmailSender.send("yiran.tang@unionman.com.cn", "test", mailContent);
	}
}
