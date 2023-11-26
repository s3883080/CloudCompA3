package com.amazonaws.s3883080;

public class AmazonCredentials {
    private String accessKey;
    private String secretAccessKey;
    private String sessionToken;

    public AmazonCredentials() {
        this.accessKey = "ASIAZIRAM6QCNDX6VQ5S";
        this.secretAccessKey = "8kcEFIXNuvI6o8pxxKl5lOxNlT04uzAiXRfYj5nf";
        this.sessionToken = "FwoGZXIvYXdzEDEaDIfYjcny9aWRpKNJVyK+AYc9kkskdfl/cbFC9vcJ5xyzOvgyCgLPJLm56TvbJFDqBTwnt/dw+Xa0ATMbLLzN3qHDHtKYai9vRdppZj0/Ve+cUjCSUNC3B2PtdNyMEexTCo69wEBYU6XKhmXBlD9UEWh3aSDQLiY9G7RIbiLy9mvbwq03/V3/xrsBqjIb3pqUbLVmhGEfZUwICA7Xii/amvNGHf49dJPjg+gWyF+ffSWBFBNYuXcrbpIA03euLhGxEr2YeLAjSdKo2ndmmxsosJ6IqwYyLQoWoSMWwlHms95IJpE8R5yio44d0J7T+ShwUr2tquPpIHdbugcRjRlSpvBMfA==";
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
