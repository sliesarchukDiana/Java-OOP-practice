package org.example;
import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            System.out.println("Connecting to Redis: " + jedis.ping());
            System.out.println("\nCRUD Person");
            String personKey = "person:1";

            jedis.hset(personKey, "first_name", "Gordon");
            jedis.hset(personKey, "last_name", "Freeman");
            jedis.hset(personKey, "email", "gordonthefreeman@gmail.com");
            jedis.hset(personKey, "bio", "Wow, another amazing description");
            System.out.println("Created " + personKey);

            System.out.println("Info: " + jedis.hgetAll(personKey));

            jedis.hset(personKey, "email", "new.email@gmail.com");
            System.out.println("New email - " + jedis.hget(personKey, "email"));

            jedis.del(personKey);
            System.out.println("exists? " + jedis.exists(personKey));


            System.out.println("\nMaterial");
            String materialKey = "material:101";

            jedis.hset(materialKey, "title", "Introduction to Artificial Intelligence");
            jedis.hset(materialKey, "annotation", "A comprehensive guide to modern AI concepts.");
            jedis.hset(materialKey, "cost", "150.50");
            jedis.hset(materialKey, "id_section", "5");
            jedis.hset(materialKey, "author_ids", "1");
            jedis.hset(materialKey, "keyword_ids", "10,12");
            System.out.println("Created" + materialKey);

            System.out.println("Info: " + jedis.hgetAll(materialKey));

            jedis.hset(materialKey, "cost", "199.99");
            System.out.println("New cost - " + jedis.hget(materialKey, "cost"));

            jedis.del(materialKey);
            System.out.println("exists? " + jedis.exists(materialKey));


            System.out.println("\nCatalogue_section");
            String sectionKey = "section:5";

            jedis.hset(sectionKey, "name", "Programming");
            jedis.hset(sectionKey, "parent_id", "0");
            System.out.println("Created " + sectionKey);

            System.out.println("Info: " + jedis.hgetAll(sectionKey));

            jedis.hset(sectionKey, "name", "Web-development");
            System.out.println("New name - " + jedis.hget(sectionKey, "name"));

            jedis.del(sectionKey);
            System.out.println("exists? " + jedis.exists(sectionKey));

        } catch (Exception e) {
            System.err.println("Something went wrong... " + e.getMessage());
        }
    }
}