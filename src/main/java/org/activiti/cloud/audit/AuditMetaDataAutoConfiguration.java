package org.activiti.cloud.audit;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ApplicationInfoManager.class)
// This code needs to live here until we find the right abstraction for registering/updating Service Metadata
public class AuditMetaDataAutoConfiguration implements BeanClassLoaderAware {

    @Value("${activiti.cloud.application.name:}")
    private String applicationName;

    @Autowired
    private ApplicationInfoManager appInfoManager;



    public AuditMetaDataAutoConfiguration() {

    }

    @PostConstruct
    public void init(){
        Map<String, String> metadata = new HashMap<>();
        metadata.put("activiti-cloud-service-type",
                     "audit");
        metadata.put("activiti-cloud-application-name",
                     applicationName);
        appInfoManager.registerAppMetadata(metadata);
    }


    public void setBeanClassLoader(ClassLoader classLoader) {
        // Do nothing for now
    }
}