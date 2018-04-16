package org.activiti.cloud.audit;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.activiti.cloud.services.metadata.MetadataService;

@Configuration
@ConditionalOnClass(ApplicationInfoManager.class)
// This code needs to live here until we find the right abstraction for registering/updating Service Metadata
public class AuditMetaDataAutoConfiguration {

    @Autowired
    private ApplicationInfoManager appInfoManager;

    @Autowired
    private MetadataService metadataService;

    public AuditMetaDataAutoConfiguration() {

    }

    @PostConstruct
    public void init() {
        appInfoManager.registerAppMetadata(metadataService.getMetadata());
    }
}