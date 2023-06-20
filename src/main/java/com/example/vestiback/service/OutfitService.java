package com.example.vestiback.service;

import com.example.vestiback.model.*;
import com.example.vestiback.repository.UserRepository;
import com.example.vestiback.service.Exception.Error;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OutfitService {
    private final UserRepository userRepository;

    private final UserService userService;

    public OutfitService(UserRepository userRepository,
                         UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Item> findItemsByUserIdAndType(String userId, String itemType) throws Error {
        User user = userRepository.findUsersByUserIdAndItemType(userId, itemType);
        List<Item> items = new ArrayList<>();
        for (Wardrobe wardrobe : user.getWardrobes()) {
                for (Item item : wardrobe.getItems()) {
                    if (item.getType().equals(itemType)) {
                        items.add(item);
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
        userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Item> tops = findItemsByUserIdAndType(userId, "haut");
        List<Item> bottoms = findItemsByUserIdAndType(userId, "bas");
        List<Item> shoes = findItemsByUserIdAndType(userId, "chaussures");
        List<Item> accessories = findItemsByUserIdAndType(userId, "accessoire");
        Item randomTop = tops.get(new Random().nextInt(tops.size()));
        Item randomBottom = bottoms.get(new Random().nextInt(bottoms.size()));
        Item randomShoes = shoes.get(new Random().nextInt(shoes.size()));
        Item randomAccessory = accessories.get(new Random().nextInt(accessories.size()));
        List<Item> randomOutfit = new ArrayList<Item>();
        randomOutfit.add(randomTop);
        randomOutfit.add(randomBottom);
        randomOutfit.add(randomShoes);
        randomOutfit.add(randomAccessory);
        return updateEventItems(userId,eventId,randomOutfit);
    }

    public User updateEventItems(String userId, String eventName, List<Item> randomOutfit) throws Error{
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        Event event = user.getEvents().stream()
                .filter(e -> e.getName().equals(eventName))
                .findFirst()
                .orElseThrow(() -> new Error("Event not found"));
        event.setOutfit(randomOutfit);
        userService.putOutfitInOutfitHistory(user,event);

        return userRepository.save(user);
    }


    public List<Item> getOutfit(String userId, String eventName) throws Error {
        User user = userService.getUserById(userId);
        List<Item> outfit = new ArrayList<>();
        for (Event event : user.getEvents()) {
            if (event.getName().equals(eventName)){
                outfit.addAll(event.getOutfit());
            }
        }if (outfit.isEmpty()) {
            throw new Error("Vous n'avez pas de tenue enregistrée.");
        } else {
            return outfit;
        }
    }
}
