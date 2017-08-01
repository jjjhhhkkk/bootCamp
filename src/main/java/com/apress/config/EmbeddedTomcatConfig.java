package com.apress.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EmbeddedTomcatConfig {

    @Value("${server.additionalPorts}")
    private String defaultPort;

    @Value("${server.port}")
    private int sslPort;

    @Bean
    @Profile({ "local", "localMac" })
    public EmbeddedServletContainerFactory servletContainer() {
        
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/views/work/accountStaff/*");
                collection.addPattern("/logout");
                collection.addPattern("/connect/**");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(additionalConnector());
        return tomcat;
    }
    
    private Connector[] additionalConnector() {
        if (this.defaultPort == null || this.defaultPort.length() == 0) {
            return null;
        }
        String[] ports = this.defaultPort.split(",");
        List<Connector> result = new ArrayList<>();
        for (String port : ports) {
            Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
            connector.setScheme("http");
            connector.setPort(Integer.valueOf(port));
            connector.setSecure(false);
            connector.setRedirectPort(sslPort);
            result.add(connector);
        }
        return result.toArray(new Connector[] {});
    }

}