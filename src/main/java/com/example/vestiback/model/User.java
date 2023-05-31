package com.example.vestiback.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Document("user")
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    private String pseudo;
    private String email;
    private String gender;
    private String accountType;
    private List<Wardrobe> wardrobes;
    private List<Event> events;
    @Getter
    @Setter
    public static class Wardrobe {
        @Id
        private String id;
        private String name;
        private List<Item> items;

        @Getter
        @Setter
        public static class Item {
            @Id
            private String id;
            private String name;
            private String label;
            private String season;
            private String type;
            private String color;
            private String size;
            private String fit;
            }
        }
    @Getter
    @Setter
    public static class Event{
        @Id
        private String id;
        private Date dateStart;
        private Date dateEnd;
        private String description;
        private List<Wardrobe.Item> outfit;
    }
}