package com.example.vestiback.service;

import com.example.vestiback.model.*;
import com.example.vestiback.repository.UserRepository;
import com.example.vestiback.service.Exception.Error;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OutfitService {
    private final UserRepository userRepository;

    private final UserService userService;

    public OutfitService(UserRepository userRepository,
                         UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Item> getTops(String userId, String wardrobeName) throws Error {
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Wardrobe> wardrobes = user.getWardrobes();

        for (Wardrobe wardrobe : wardrobes) {
            if (wardrobe.getName().equals(wardrobeName)) {
                List<Item> tops = wardrobe.getItems();
                return tops.stream().filter(e -> e.getType().equals("top"))
                        .collect(Collectors.toList());
            }
        }
        throw new Error("Tops not found");
    }

    public List<Item> getBottoms(String userId, String wardrobeName) throws Error{
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Wardrobe> wardrobes = user.getWardrobes();

        for (Wardrobe wardrobe : wardrobes) {
            if (wardrobe.getName().equals(wardrobeName)) {
                List<Item> bottoms = wardrobe.getItems();
                return bottoms.stream().filter(e -> e.getType().equals("bottom"))
                        .collect(Collectors.toList());
            }
        }
        throw new Error("Bottoms not found");
    }

    public List<Item> findItemsByUserIdAndType(String userId, String itemType) throws Error {
        List<User> users = userRepository.findUsersByUserIdAndItemType(userId, itemType);
        List<Item> items = new ArrayList<>();
        for (User user : users) {
            for (Wardrobe wardrobe : user.getWardrobes()) {
                for (Item item : wardrobe.getItems()) {
                    if (item.getType().equals(itemType)) {
                        items.add(item);
                    }
                }
            }
        }
        if (items.isEmpty()) {
            throw new Error("Vous n'avez pas de dressing ou de d'articles dans votre dressing.");
        } else {
            return items;
        }
    }

    public User createRandomOutfit(String userId, String eventId) throws Error {
        List<Item> tops = findItemsByUserIdAndType(userId, "top");
        List<Item> bottoms = findItemsByUserIdAndType(userId, "bottom");
        Item randomTop = tops.get(new Random().nextInt(tops.size()));
        Item randomBottom = bottoms.get(new Random().nextInt(bottoms.size()));
        List<Item> randomOutfit = new ArrayList<Item>();
        randomOutfit.add(randomTop);
        randomOutfit.add(randomBottom);
        return updateEventItems(userId,eventId,randomOutfit);
    }

    public User updateEventItems(String userId, String eventId, List<Item> updatedItems) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = user.getEvents().stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setOutfit(updatedItems);
        return userRepository.save(user);
    }


    public List<Item> getOutfit(String userId, String eventId) throws Error {
        User user = userService.getUserById(userId);
        List<Item> outfit = new ArrayList<>();
        for (Event event : user.getEvents()) {
            if (event.getEventId().equals(eventId)){
                outfit.addAll(event.getOutfit());
            }
        }if (outfit.isEmpty()) {
            throw new Error("Vous n'avez pas de tenue enregistrée.");
        } else {
            return outfit;
        }
    }
}
