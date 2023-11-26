package com.amazonaws.s3883080;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

public class UserManager {
    private User user;
    private static UserManager state;

    public UserManager() {
        UserManager.state = this;
    }

    public void setUser (User user) {
        this.user=user;
    }

    public void logoutUser() {
        this.user = null;
    }
    public User getUser() {
        return this.user;
    }

    public static UserManager getState() {
        return UserManager.state;
    }

    public void setState(UserManager state) {
        this.state = state;
    }


    public boolean LoginUser(String email, String password) {
        boolean loginSuccess = false;

        Table table = getDynamoDBTable("user");

        Item item = table.getItem("email", email);

        if (item!=null) {
            String storedPassword = item.getString("password");

            if (storedPassword.equals(password)) {
                loginSuccess = true;
            }
        }

        return loginSuccess;
    }

    public User returnUser (String email, String password) {
        User user = null;

        Table table = getDynamoDBTable("user");

        Item item = table.getItem("email", email);

        String storedPassword = item.getString("password");
        String usersName = item.getString("username");
        String hometown = item.getString("hometown");

        if (storedPassword.equals(password)) {
            user = new User(usersName, password, email, hometown);
        }

        return user;
    }

    public Table getDynamoDBTable(String tableName) {
        AmazonCredentials amazonCredentials = new AmazonCredentials();
        Properties properties;
        properties = System.getProperties();
        properties.setProperty("aws.accessKeyId", amazonCredentials.getAccessKey());
        properties.setProperty("aws.secretKey", amazonCredentials.getSecretAccessKey());
        properties.setProperty("aws.sessionToken", amazonCredentials.getSessionToken());
        SystemPropertiesCredentialsProvider systemPropertiesCredentialsProvider = new SystemPropertiesCredentialsProvider();
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(systemPropertiesCredentialsProvider)
                .withRegion(Regions.US_EAST_1);
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable(tableName);

        return table;
    }

    public boolean DoesUserExist(String targetEmail) {
        boolean userExists = false;
        Table table = getDynamoDBTable("user");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#email", "email");

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#email, password, username")
                .withFilterExpression("#email = :email").withNameMap(nameMap)
                .withValueMap(new ValueMap().withString(":email", targetEmail));

        try {
            ItemCollection<ScanOutcome> itemCollection = table.scan(scanSpec);
            Iterator<Item> iterator = itemCollection.iterator();

            if (iterator.hasNext()) {
                userExists = true;
            }
        } catch (Exception e) {
            System.err.println("An error has occurred");
            System.err.println(e.getMessage());
        }

        return userExists;
    }

    public void AddUser(String email, String username, String password, String hometown) {
        Table table = getDynamoDBTable("user");
        Item item = new Item()
                .withPrimaryKey("email", email)
                .withString("password", password)
                .withString("username", username)
                .withString("hometown", hometown);
        PutItemOutcome putItemOutcome = table.putItem(item);
    }


}
