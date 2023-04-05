package open.ai.agent.api.register;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geyangyang.gyy@gmail.com
 * @date  2023-04-05
 */
@Configuration
public class RegisterProxyServlet {
    @Value("${proxy.api.prefix}")
    private String apiPrefix;
    @Value("${proxy.api.targetUrl}")
    private String targetServerUrl;

    @Value("${proxy.api.EnableLog}")
    private Boolean enableLog=false;
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), apiPrefix);
        servletRegistrationBean.addInitParameter(ProxyServlet.P_TARGET_URI, targetServerUrl);
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, enableLog.toString());
        return servletRegistrationBean;
    }
}
