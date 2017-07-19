package cn.com.daocaore.wechat.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daocaore.wechat.common.util.CoreService;
import cn.com.daocaore.wechat.common.util.SignUtil;

/***
 ** @category 对微信提供的接口（微信用来验证开发者服务器）
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月18日下午6:00:48
 **/
public class WechatCoreServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
     * 确认请求来自微信服务器
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		System.out.println("*******开始接收来自微信消息：*******");
		// 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        System.out.println("signature:"+signature+"\ntimestamp:"+timestamp+"\nnonce:"+nonce+"\nechostr:"+echostr);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        boolean success=SignUtil.checkSignature(signature, timestamp, nonce);
        if (success) {
            out.print(echostr);
        }
        System.out.println("success:"+success);
        out.close();
        out = null;
	}
	/**
     * 处理微信服务器发来的消息
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("*******开始处理来自微信消息：*******");
//	    // 消息的接收、处理、响应
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respXml = CoreService.processRequest(request);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respXml);
        System.out.println("response:"+respXml);
        out.close();
	}
}
