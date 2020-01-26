# Activiti Cloud Audit (JPA)

[![Join Us in Gitter](https://badges.gitter.im/Activiti/Activiti7.svg)](https://gitter.im/Activiti/Activiti7?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status Travis](https://travis-ci.com/Activiti/activiti-cloud-audit.svg?branch=master)](https://travis-ci.com/Activiti/activiti-cloud-audit)
[![Coverage Status](http://img.shields.io/codecov/c/github/Activiti/activiti-cloud-audit/master.svg?maxAge=86400)](https://codecov.io/gh/Activiti/activiti-cloud-audit)
[![ASL 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/Activiti/activiti-cloud-audit/blob/master/LICENSE.txt)
[![CLA](https://cla-assistant.io/readme/badge/Activiti/activiti-cloud-audit)](https://cla-assistant.io/Activiti/activiti-cloud-audit)
[![Docker Build Status](https://img.shields.io/docker/build/activiti/activiti-cloud-audit.svg)](https://hub.docker.com/r/activiti/activiti-cloud-audit/)
[![security status](https://www.meterian.com/badge/gh/Activiti/activiti-cloud-audit/security)](https://www.meterian.com/report/gh/Activiti/activiti-cloud-audit)
[![stability status](https://www.meterian.com/badge/gh/Activiti/activiti-cloud-audit/stability)](https://www.meterian.com/report/gh/Activiti/activiti-cloud-audit)

Activiti Cloud Audit Service JPA Implementation. This service uses a Relational Database to store events emitted by Runtime Bundles as they come in.

As all our services, this module was build using the [activiti-cloud-starter-audit](https://github.com/activiti/activiti-cloud-audit-service) module, that you can use to create your own version of this service as with any other Spring Boot Starter.  

For more information about his module and the starters you can take a look at our [Activiti & Activiti Cloud GitBook](https://activiti.gitbooks.io/activiti-7-developers-guide/content/components/activiti-cloud-app/AuditService.html)

[Docker Image](https://hub.docker.com/r/activiti/activiti-cloud-audit/)

## Building & Running this Service
You can build this service from source using Git & Maven or you can just run our Docker Image. 

### Spring Boot: 
> git clone https://github.com/Activiti/activiti-cloud-audit.git
> cd activiti-cloud-audit/
> mvn clean install spring-boot:run

### Docker: 
> docker run -p 8181:8181 -d --name activiti-cloud-audit activiti/activiti-cloud-audit:latest

## Environment Variables
```
server.port=${ACT_AUDIT_PORT:8181}
spring.application.name=${ACT_AUDIT_APP_NAME:audit}
spring.cloud.stream.bindings.auditConsumer.destination=${ACT_AUDIT_CONSUMER_DEST:engineEvents}
spring.cloud.stream.bindings.auditConsumer.group=${ACT_AUDIT_CONSUMER_GROUP:audit}
spring.cloud.stream.bindings.auditConsumer.contentType=${ACT_AUDIT_CONSUMER_CONTENT_TYPE:application/json}
spring.jackson.serialization.fail-on-unwrapped-type-identifiers=${ACT_AUDIT_JACKSON_FAIL_ON_UNWRAPPED_IDS:false}
keycloak.auth-server-url=${ACT_KEYCLOAK_URL:http://activiti-cloud-sso-idm:8180/auth}
keycloak.realm=${ACT_KEYCLOAK_REALM:springboot}
keycloak.resource=${ACT_KEYCLOAK_RESOURCE:activiti}
keycloak.ssl-required=${ACT_KEYCLOAK_SSL_REQUIRED:none}
keycloak.public-client=${ACT_KEYCLOAK_CLIENT:true}
keycloak.security-constraints[0].authRoles[0]=${ACT_KEYCLOAK_ROLES:user}
keycloak.security-constraints[0].securityCollections[0].patterns[0]=${ACT_KEYCLOAK_PATTERNS:/v1/*}
keycloak.principal-attribute=${ACT_KEYCLOAK_PRINCIPAL_ATTRIBUTE:preferred-username}
activiti.keycloak.admin-client-app=${ACT_KEYCLOAK_CLIENT_APP:admin-cli}
activiti.keycloak.client-user=${ACT_KEYCLOAK_CLIENT_USER:client}
activiti.keycloak.client-password=${ACT_KEYCLOAK_CLIENT_PASSWORD:client}
spring.rabbitmq.host=${ACT_RABBITMQ_HOST:rabbitmq}
eureka.client.serviceUrl.defaultZone=${ACT_EUREKA_URL:http://activiti-cloud-registry:8761/eureka/}
eureka.instance.hostname=${ACT_AUDIT_HOST:activiti-cloud-audit}
eureka.client.enabled=${ACT_AUDIT_EUREKA_CLIENT_ENABLED:true}
```
