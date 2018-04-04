package org.activiti.cloud.audit;

import java.util.HashMap;
import java.util.Map;

import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ApplicationInfoManager.class)
public class AuditMetaDataAutoConfiguration implements BeanClassLoaderAware {

    // This code needs to live here until we find the right abstraction for registering/updating Service Metadata
    @Autowired
    public AuditMetaDataAutoConfiguration(ApplicationInfoManager appInfoManager) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("activiti-cloud-service-type",
                     "audit");
        appInfoManager.registerAppMetadata(metadata);
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        // Do nothing for now
    }
}