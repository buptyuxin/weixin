package com.yanmo.weixin;

import com.google.gson.Gson;
import com.yanmo.weixin.log.WxLog;
import com.yanmo.weixin.service.MsgProcessService;
import com.yanmo.weixin.service.impl.AccessTokenServiceImpl;
import com.yanmo.weixin.service.impl.HttpClientServiceImpl;
import com.yanmo.weixin.utils.EnvUtils;
import com.yanmo.weixin.utils.SecurityUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class DispatcherServlet extends GenericServlet {

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    private static final String POST_CONTENT_TYPE_MULTIPART = "multipart/form-data";
    private static final String POST_CONTENT_TYPE_APPLICATION = "application/x-www-form-urlencoded";
    private static final String POST_CONTENT_TYPE_JSON = "application/json";
    private static final String POST_CONTENT_TYPE_TEXT = "text/xml";

    private static final String SPRING_BEANS_XML = "spring-beans.xml";

    private AbstractApplicationContext ctx;
    private MsgProcessService msgProcessService;

    public MsgProcessService getMsgProcessService() {
        return msgProcessService;
    }

    public void setMsgProcessService(MsgProcessService msgProcessService) {
        this.msgProcessService = msgProcessService;
    }

    public AbstractApplicationContext getCtx() {
        return ctx;
    }

    public void setCtx(AbstractApplicationContext context) {
        this.ctx = context;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(SPRING_BEANS_XML);
        setCtx(context);
//        Timer timer = new Timer();
//        timer.schedule(new AccessTokenTask(), 0, ACCESS_TOKEN_TASK_PERIOD);
        // 开个定时任务每一个小时更新一次access token
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate((Runnable) ctx.getBean("accessTokenRunnable"), 0, EnvUtils.ACCESS_TOKEN_TASK_PERIOD, TimeUnit.HOURS);

        // 注入消息处理服务
        this.msgProcessService = (MsgProcessService) ctx.getBean("msgProcessService");
    }

    @Override
    public void destroy() {
        getCtx().close();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) res;
        } catch (ClassCastException e) {
            WxLog.log("不是http请求");
            e.printStackTrace();
            return;
        }

        if (METHOD_GET.equals(request.getMethod())) {
            doGet(request, response);
        } else if (METHOD_POST.equals(request.getMethod())) {
            doPost(request, response);
        } else {
            // 无法处理的请求
            WxLog.log(request.toString());
        }
    }

    private void doGet(HttpServletRequest req, HttpServletResponse res) {
        if (!SecurityUtils.wxCheck(req)) {
            return;
        }
        String echostr = req.getParameter("echostr");
        try {
            res.getWriter().write(echostr);
            res.getWriter().close();
        } catch (IOException e) {
            WxLog.log("无法写html");
            e.printStackTrace();
        }
    }

    private void doPost(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        if (!SecurityUtils.wxCheck(req)) {
            return;
        }
        if (POST_CONTENT_TYPE_TEXT.equals(req.getContentType())) {
            String xml = parse(req);
            String reply = msgProcessService.processXmlMsg(xml);
            try {
                res.getWriter().write(reply);
                res.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (POST_CONTENT_TYPE_JSON.equals(req.getContentType())) {
            String json = parse(req);
            processJson(json);
        } else {
            // 暂不处理
            return;
        }
    }

    private String parse(HttpServletRequest req) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            while ((line=req.getReader().readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void processJson(String json) {
        Gson gson = new Gson();

    }

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("parser-beans.xml");
        if (((AccessTokenServiceImpl)context.getBean("accessTokenService")).getHttpClientService() == null) {
            System.out.println("not inject");
        }
        context.getAutowireCapableBeanFactory().createBean(HttpClientServiceImpl.class, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
        if (((AccessTokenServiceImpl)context.getBean("accessTokenService")).getHttpClientService() == null) {
            System.out.println("still not inject");
        }
        System.out.println("asdfa");
    }
}
