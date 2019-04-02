import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class DBTest {

    public static void main(String[] args) {
        System.out.println("Hello");

        //****************************************************************************
        //atlas setup
        //MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        //MongoClient mongoClient = new MongoClient(connectionString);
        //****************************************************************************

        MongoClient mongoClient = new MongoClient();

        MongoDatabase database = mongoClient.getDatabase("School");
        MongoCollection<Document> collection = database.getCollection("Staff");

        //creates new Staff member document & adds to db
        Document doc = new Document("Name", "Rodger Murphy")
                .append("Position", "Advisor")
                .append("Office", "PRI136");

        collection.insertOne(doc);

        //reads newly added document
        doc = collection.find(eq("Name", "Rodger Murphy")).first();
        System.out.println(doc.toJson());

        //changes Rodger Murphy's Office to PRI137 fro PRI136
        collection.updateOne(eq("Name", "Rodger Murphy"), new Document("$set", new Document("Office", "PRI137")));

        //reads document with updated information
        doc = collection.find(eq("Name", "Rodger Murphy")).first();
        System.out.println(doc.toJson());

        //deletes Rodger Murphy's Staff document
        collection.deleteOne(eq("Name", "Rodger Murphy"));
    }//end main
}//end class