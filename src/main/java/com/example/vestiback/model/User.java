package com.example.vestiback.model;
import lombok.Data;
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
        private List<Top> tops;
        private List<Bottom> bottoms;

            @Data
            public static class Top{
                @Id
                private String id;
                private String name;
                private String label;
                private String season;
                private String type;
                private String color;
                private String size;
                private boolean favorite;
                private String fit;

                }
                @Data
                public static class Bottom{
                    @Id
                    private String id;
                    private String name;
                    private String label;
                    private String season;
                    private String type;
                    private String color;
                    private String size;
                    private boolean favorite;
                    private String fit;
                }
            }

    @Data
    public static class Event {
        @Id
        private String id;
        private Date dateStart;
        private Date dateEnd;
        private String description;
        private List<Outfit> outfits;

        @Data
        public static class Outfit {
            @Id
            private String id;
            private String name;
            private String label;
            private String season;
            private String type;
            private String color;
            private String size;
            private boolean favorite;
            private String fit;
        }
    }
}