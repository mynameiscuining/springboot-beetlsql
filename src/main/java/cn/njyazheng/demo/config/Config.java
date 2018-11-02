package cn.njyazheng.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class Config implements WebMvcConfigurer {

    /**
     * 数据源配置
     * @param
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(){
        HikariDataSource dataSource=new HikariDataSource();
        return dataSource;
    }

    /**
     * 代理配置
     * @param environment
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(Environment environment){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), environment.getProperty("proxy.access_url"));
        //这个setName必须要设置，并且多个的时候，名字需要不一样
        servletRegistrationBean.setName("proxy");
        servletRegistrationBean.addInitParameter("targetUri", environment.getProperty("proxy.target_url"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, environment.getProperty("proxy.logging_enabled"));
        return servletRegistrationBean;
    }
}
