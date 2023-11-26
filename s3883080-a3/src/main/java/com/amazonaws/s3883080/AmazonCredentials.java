package com.amazonaws.s3883080;

public class AmazonCredentials {
    private String accessKey;
    private String secretAccessKey;
    private String sessionToken;

    public AmazonCredentials() {
        this.accessKey = "ASIAZIRAM6QCHG47EVG4";
        this.secretAccessKey = "3p92b+Xc5HvVBCr1zJTiKQYyTLvwhl0RZtVuJG4R";
        this.sessionToken = "FwoGZXIvYXdzEEMaDDM/TEkPzk+HYwZGdCK+ATwses2FoFfkwKkn3UO8KcfSKtqluqLUIZcvVHmnhZE8d50n82UOd2az5tQAWlx/F8PcySx5qMDVQQkv9bo7oJ8tejSn99IsuVFZ7EuRIpchdC8dE6xv9Vnm5Af3T6CuqJ3EBFSa8sgwIvXwkKO2dQ7GNiiPFaWU5eTZLL223u+YxEXrHWno329WQGQFrHz4ana8IPmTzh3XpCC61JFcpIGONC7yyDyIeKASubDbDnc19JUM+woanvdAra03Hsso6p+MqwYyLdRS0l2AWpzMV1kLQPq9pHm55eVXeAn0dE+ZIomAHLLTIlsYhEwCljGGOpOPOQ==";
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
