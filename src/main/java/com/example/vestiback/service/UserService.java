package com.example.vestiback.service;

import com.example.vestiback.dto.wardrobeDTO;
import com.example.vestiback.dto.EventDTO;
import com.example.vestiback.dto.ItemDTO;
import com.example.vestiback.dto.UserFullDTO;
import com.example.vestiback.model.User;
import com.example.vestiback.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User update(User user, String id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser == null) {
            throw new RuntimeException("User not found");
        } else {
            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setPseudo(user.getPseudo());
            existingUser.setEmail(user.getEmail());
            existingUser.setGender(user.getGender());
            existingUser.setAccountType(user.getAccountType());
            existingUser.setWardrobes(user.getWardrobes());
            existingUser.setEvents(user.getEvents());
            return userRepository.save(existingUser);
        }}


    /**DTO Configuration**/

    public UserFullDTO getUserById(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserFullDTO.class);
    }

    public ItemDTO getItemById(String userId, String itemId ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User.Wardrobe> wardrobes = user.getWardrobes();

        // Parcourir tous les wardrobe de l'utilisateur pour trouver l'Item correspondant
        for (User.Wardrobe wardrobe : wardrobes) {
            List<User.Wardrobe.Item> items = wardrobe.getItems();

            for (User.Wardrobe.Item item : items) {
                if (item.getId().equals(itemId)) {
                    return modelMapper.map(item, ItemDTO.class);
                }
            }
        }
        throw new RuntimeException("Item not found");
    }

    public wardrobeDTO getDressingById(String userId, String wardrobeId){

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User.Wardrobe> wardrobes = user.getWardrobes();

        for ( User.Wardrobe wardrobe: wardrobes) {
            if (wardrobe.getId().equals(wardrobeId)){
                return modelMapper.map(wardrobe, wardrobeDTO.class);
            }
        }
        throw new RuntimeException("Wardrobe not found");
    }

    public EventDTO getEventById(String userId, String eventId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User.Event> events = user.getEvents();

        for (User.Event event: events) {
            if (event.getId().equals(eventId)){
                return modelMapper.map(event, EventDTO.class);
            }
        }
        throw new RuntimeException("Event not found");
    }}
