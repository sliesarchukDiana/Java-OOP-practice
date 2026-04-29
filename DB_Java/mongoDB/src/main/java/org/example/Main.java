package org.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    static void main() {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("mango_lab");
            MongoCollection<Document> materialsCollection = database.getCollection("materials");
            MongoCollection<Document> sectionsCollection = database.getCollection("sections");
            MongoCollection<Document> usersCollection = database.getCollection("users");
            System.out.println("Connected to DB!");

// db.materials.find({ cost: { $gt: 200 } }).pretty()
            System.out.println("\n 1. Materials with cost > 200");
            for (Document doc : materialsCollection.find(Filters.gt("cost", 200))) {
                System.out.println(doc.toJson());
            }

// db.users.find({ $or: [ {first_name: "Eric"}, {first_name: "Riley"} ] })
            System.out.println("\n 2. Users named Eric or Riley ");
            Bson nameFilter = Filters.or(
                    Filters.eq("first_name", "Eric"),
                    Filters.eq("first_name", "Riley")
            );
            for (Document doc : usersCollection.find(nameFilter)) {
                System.out.println(doc.toJson());
            }

// db.materials.find({}, {title: 1, cost: 1, _id: 0}).sort({cost: -1}).limit(3)
            System.out.println("\n 3. Тоp-3 most expensive materials ");
            Bson projection = Projections.fields(
                    Projections.include("title", "cost"),
                    Projections.excludeId()
            );

            for (Document doc : materialsCollection.find()
                    .projection(projection)
                    .sort(Sorts.descending("cost"))
                    .limit(3)) {
                System.out.println(doc.toJson());
            }

// db.materials.find({ keywords: "Algorithms" })
            System.out.println("\n 4. Materials with keyword 'Algorithms' ");
            for (Document doc : materialsCollection.find(Filters.eq("keywords", "Algorithms"))) {
                System.out.println(doc.toJson());
            }

// db.materials.insert({ _id: 999, title: "Test Material for Deletion", cost: 0 })
            System.out.println("\n 5. Insert test material");
            Document newMaterial = new Document("_id", 999)
                    .append("title", "Test Material for Deletion")
                    .append("cost", 0);

            materialsCollection.insertOne(newMaterial);
            System.out.println("Element was inserted");

// db.materials.deleteOne({ _id: 999 })
            System.out.println("\n 6. Delete test material ");
            materialsCollection.deleteOne(Filters.eq("_id", 999));
            System.out.println("Element was deleted!");


            System.out.println("\nТоp-3 sections by overall courses price");
            materialsCollection.aggregate(Arrays.asList(
                    Aggregates.lookup("sections", "section_id", "_id", "section_info"),
                    Aggregates.unwind("$section_info"),
                    Aggregates.match(Filters.gte("cost", 100)),
                    Aggregates.group("$section_info.name",
                            Accumulators.sum("total_cost", "$cost"),
                            Accumulators.push("courses", "$title")),
                    Aggregates.sort(Sorts.descending("total_cost")),
                    Aggregates.limit(3)
            )).forEach(doc -> System.out.println(doc.toJson()));


            System.out.println("\nStatistics by keywords");
            materialsCollection.aggregate(Arrays.asList(
                    Aggregates.unwind("$keywords"),
                    Aggregates.match(Filters.gt("cost", 50)),
                    Aggregates.group("$keywords",
                            Accumulators.avg("avg_cost", "$cost"),
                            Accumulators.sum("course_count", 1)),
                    Aggregates.sort(Sorts.orderBy(Sorts.descending("course_count"), Sorts.descending("avg_cost"))),
                    Aggregates.limit(4)
            )).forEach(doc -> System.out.println(doc.toJson()));


            System.out.println("\nStatistics subscription format");
            usersCollection.aggregate(Arrays.asList(
                    Aggregates.match(Filters.and(
                            Filters.exists("subscriptions", true),
                            Filters.not(Filters.size("subscriptions", 0))
                    )),
                    Aggregates.unwind("$subscriptions"),
                    Aggregates.group("$subscriptions.format_type",
                            Accumulators.sum("subs_count", 1),
                            Accumulators.addToSet("users_list", "$first_name")),
                    Aggregates.match(Filters.gte("subs_count", 2)),
                    Aggregates.sort(Sorts.descending("subs_count"))
            )).forEach(doc -> System.out.println(doc.toJson()));


            System.out.println("\nТоp-3 authors by amount of their materials");
            materialsCollection.aggregate(Arrays.asList(
                    Aggregates.unwind("$author_ids"),
                    Aggregates.match(Filters.gt("cost", 0)),
                    Aggregates.group("$author_ids",
                            Accumulators.sum("materials_count", 1),
                            Accumulators.max("max_cost", "$cost")),
                    Aggregates.sort(Sorts.orderBy(Sorts.descending("materials_count"), Sorts.descending("max_cost"))),
                    Aggregates.limit(3)
            )).forEach(doc -> System.out.println(doc.toJson()));

            
            System.out.println("\nRevenue and Minimal Price by sections ");
            materialsCollection.aggregate(Arrays.asList(
                    Aggregates.match(Filters.gte("cost", 100)),
                    Aggregates.group("$section_id",
                            Accumulators.min("min_cost", "$cost"),
                            Accumulators.sum("total_cost", "$cost")),
                    Aggregates.match(Filters.lt("total_cost", 1000)),
                    Aggregates.sort(Sorts.ascending("min_cost")),
                    Aggregates.project(Projections.fields(
                            Projections.excludeId(),
                            Projections.computed("Section_ID", "$_id"),
                            Projections.computed("Minimal_Price", "$min_cost"),
                            Projections.computed("Revenue", "$total_cost")
                    ))
            )).forEach(doc -> System.out.println(doc.toJson()));

        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}