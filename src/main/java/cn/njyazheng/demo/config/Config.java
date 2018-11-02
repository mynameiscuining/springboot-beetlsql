package cn.njyazheng.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class Config implements WebMvcConfigurer {

    /**
     * 数据源配置
     *
     * @param
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        return dataSource;
    }

    /**
     * 代理配置
     *
     * @param environment
     * @return
     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean(Environment environment) {
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), environment.getProperty("proxy.access_url"));
//        //这个setName必须要设置，并且多个的时候，名字需要不一样
//        servletRegistrationBean.setName("proxy");
//        servletRegistrationBean.addInitParameter("targetUri", environment.getProperty("proxy.target_url"));
//        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, environment.getProperty("proxy.logging_enabled"));
//        return servletRegistrationBean;
//    }

    /**
     * 配置一个TomcatEmbeddedServletContainerFactory bean
     *
     * @return
     */
    @Bean
    public ServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");//机密的
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
        factory.addAdditionalTomcatConnectors(initiateHttpConnector());
        return factory;
    }

    /**
     * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
     * 但是不能同时在application.properties中同时配置两个connector，
     * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
     *
     * @return Connector
     */
    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80); // http端口
        connector.setSecure(false);
        connector.setRedirectPort(8443); // application.properties中配置的https端口
        return connector;
    }

}
