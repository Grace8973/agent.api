package open.ai.agent.api.annotation;

import open.ai.agent.api.register.RegisterProxyServlet;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author geyangyang.gyy@gmail.com
 * @date  2023-04-05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RegisterProxyServlet.class)
public @interface EnableProxy {
}
