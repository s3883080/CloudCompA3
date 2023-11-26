package com.amazonaws.s3883080;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class DatabaseManager {
    private static DatabaseManager state;

    public DatabaseManager() { DatabaseManager.state=this; }

    public static DatabaseManager getState(){ return DatabaseManager.state;}

    public ArrayList<Instrument> FetchMyInstruments(String targetEmail) {

        Table table = getDynamoDBTable("instruments");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#seller", "seller");
        nameMap.put("#sold", "sold");

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("instrument, brand, model, description, cost, loctn, ono, sold, #seller")
                .withFilterExpression("#seller = :seller and #sold = :sold").withNameMap(nameMap)
                .withValueMap(new ValueMap().withString(":seller", targetEmail)
                        .withBoolean(":sold", false));

        ArrayList<Instrument> getMyInstruments = new ArrayList<Instrument>();

        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                Item item = iter.next();
                String seller = item.getString("seller");
                String type = item.getString("instrument");
                String model = item.getString("model");
                String brand = item.getString("brand");
                String locat = item.getString("loctn");
                String description = item.getString("description");
                float cost = item.getFloat("cost");
                boolean ono = item.getBoolean("ono");
                boolean sold = item.getBoolean("sold");
//                Instrument instrument = new Instrument(seller, location, type, brand, model, description, cost, ono, sold);
                Instrument instrument = new Instrument(seller, locat, type, brand, model, description, cost, ono, sold);
                getMyInstruments.add(instrument);
            }
        }

        catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }
        return getMyInstruments;


    }

    public void setState(DatabaseManager state) { this.state=state;}

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


    public ArrayList<Instrument> FetchInstruments (String instrumentTarget) {

        Table table = getDynamoDBTable("instruments");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#instrument", "instrument");
        nameMap.put("#sold", "sold");

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("key_value, #instrument, seller, loctn, brand, model, description, cost, ono, #sold")
                .withFilterExpression("#instrument = :instrument and #sold = :sold").withNameMap(nameMap)
                .withValueMap(new ValueMap().withString(":instrument", instrumentTarget)
                        .withBoolean(":sold",false));

        ArrayList<Instrument> instrumentsForSale = new ArrayList<Instrument>();

        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                Item item = iter.next();
                Instrument instrument = itemToInstrumentObject(item);
                instrumentsForSale.add(instrument);            }
        }

        catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }
        return instrumentsForSale;
    }

    public static Instrument itemToInstrumentObject(Item item) {
        String seller = item.getString("seller");
        String type = item.getString("instrument");
        String model = item.getString("model");
        String brand = item.getString("brand");
        String locat = item.getString("loctn");
        String description = item.getString("description");
        float cost = item.getFloat("cost");
        boolean ono = item.getBoolean("ono");
        boolean sold = item.getBoolean("sold");
        Instrument instrument = new Instrument(seller, locat, type, brand, model,description,cost,ono,sold);
        return instrument;
    }

    public Instrument getInstrument(String instrumentKey) {
        Instrument instrument = null;

        Table table = getDynamoDBTable("instruments");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#itemKey", "itemKey");

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#itemKey, instrument, seller, loctn, brand, model, description, cost, ono, sold")
                .withFilterExpression("#itemKey = :itemKey").withNameMap(nameMap)
                .withValueMap(new ValueMap().withString(":itemKey", instrumentKey));

        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            if (iter.hasNext()) {
                Item item = iter.next();
                instrument = itemToInstrumentObject(item);
            }
        }

        catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }
        return instrument;
    }

    public boolean UpdateInstrumentToSold (User user, String instrumentKey, String type) {
        boolean instrumentUpdated = false;

        Table table = getDynamoDBTable("instruments");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#sold", "sold");

        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                    .withPrimaryKey("itemKey", instrumentKey, "instrument", type)
                    .withUpdateExpression("set #sold = :sold")
                .withNameMap(nameMap)
                    .withValueMap(new ValueMap().withBoolean(":sold", true));
        try {
            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            instrumentUpdated = true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return instrumentUpdated;
    }

    public boolean AddInstrument(Instrument instrument, String itemKey) {

        boolean worked = false;

        Table table = getDynamoDBTable("instruments");

        Item item = new Item()
                .withPrimaryKey("itemKey", itemKey)
                .withString("instrument", instrument.getInstrumentType())
                .withString("seller", instrument.getSeller())
                .withString("loctn", instrument.getLocation())
                .withString("brand", instrument.getInstrumentBrand())
                .withString("model", instrument.getInstrumentModel())
                .withString("description", instrument.getDescription())
                .withFloat("cost", instrument.getCost())
                .withBoolean("ono", instrument.isOno())
                .withBoolean("sold", instrument.isSold());

        PutItemOutcome putItemOutcome = table.putItem(item);
        worked = true;

        return worked;
    }

    public boolean AddReceipt(Receipt receipt) {
        boolean worked = false;

        String itemKey = receipt.getSeller() + "," + receipt.getBuyer() + "," + receipt.getItem();

        Table table = getDynamoDBTable("receipts");

        Item item = new Item()
                .withPrimaryKey("primKey", itemKey)
                .withString("buyer", receipt.getBuyer())
                .withString("seller", receipt.getSeller())
                .withString("item", receipt.getItem())
                .withString("brand", receipt.getBrand())
                .withString("model", receipt.getModel())
                .withString("type", receipt.getType())
                .withFloat("cost", receipt.getCost())
                .withFloat("gst", receipt.getGst());

        PutItemOutcome putItemOutcome = table.putItem(item);
        worked = true;

        return worked;
    }

}
