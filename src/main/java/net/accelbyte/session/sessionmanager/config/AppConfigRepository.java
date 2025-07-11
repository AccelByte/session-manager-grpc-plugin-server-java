/*
 * Copyright (c) 2025 AccelByte Inc. All Rights Reserved
 * This is licensed software from AccelByte Inc, for limitations
 * and restrictions contact your company contract manager.
 */
package net.accelbyte.session.sessionmanager.config;

import net.accelbyte.sdk.core.repository.DefaultConfigRepository;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AppConfigRepository extends DefaultConfigRepository {

    private String baseUrl = "";
    private String clientId = "";
    private String clientSecret = "";
    private String appName = "";
    private String traceIdVersion = "";
    private String namespace = "";
    private boolean enableTraceId = false;
    private boolean enableUserAgentInfo = false;
    private String resourceName = "";    

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getClientSecret() { return clientSecret; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }

    public String getAppName() { return appName; }
    public void setAppName(String appName) { this.appName = appName; }

    public String getTraceIdVersion() { return traceIdVersion; }
    public void setTraceIdVersion(String traceIdVersion) { this.traceIdVersion = traceIdVersion; }

    public String getNamespace() { return namespace; }
    public void setNamespace(String namespace) { this.namespace = namespace; }

    public boolean isEnableTraceId() { return enableTraceId; }
    public void setEnableTraceId(boolean enableTraceId) { this.enableTraceId = enableTraceId; }

    public boolean isEnableUserAgentInfo() { return enableUserAgentInfo; }
    public void setEnableUserAgentInfo(boolean enableUserAgentInfo) { this.enableUserAgentInfo = enableUserAgentInfo; }

    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }    

    public void readEnvironmentVariables() {
        baseUrl = getEnv("AB_BASE_URL", baseUrl);
        clientId = getEnv("AB_CLIENT_ID", clientId);
        clientSecret = getEnv("AB_CLIENT_SECRET", clientSecret);
        namespace = getEnv("AB_NAMESPACE", namespace);
        resourceName = getEnv("APP_RESOURCE_NAME", "SESSIONDSMGRPCSERVICE");        
    }

    private String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value != null && !value.trim().isEmpty()) ? value.trim() : defaultValue;
    }
}
