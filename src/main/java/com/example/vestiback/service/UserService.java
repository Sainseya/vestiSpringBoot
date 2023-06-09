package com.example.vestiback.service;

import com.example.vestiback.dto.*;
import com.example.vestiback.model.Event;
import com.example.vestiback.model.User;
import com.example.vestiback.model.Wardrobe;
import com.example.vestiback.repository.UserRepository;
import com.example.vestiback.service.Exception.Error;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //Find all users
    public List<User> findAll()throws Error {

        if (userRepository.findAll().isEmpty()){
            throw new Error("The table is empty");
        }
        else {
            return userRepository.findAll();
        }
    }


    //Delete all users
    public void deleteAll(){
        userRepository.deleteAll();
    }


    //Delete user by Id
    public void deleteById(String id)throws Error{
         userRepository.findById(id).orElseThrow(() -> new Error("User not found"));
         userRepository.deleteById(id);
    }


    //Save all user
    public User save(User user){
        return userRepository.save(user);
    }


    //Update all user
    public User update(User user, String id)throws Error {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new Error("User not found"));
            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setPseudo(user.getPseudo());
            existingUser.setEmail(user.getEmail());
            existingUser.setGender(user.getGender());
            existingUser.setAccountType(user.getAccountType());
            existingUser.setWardrobes(user.getWardrobes());
            existingUser.setEvents(user.getEvents());
            return userRepository.save(existingUser);
        }


    /**DTO Configuration**/

    //Get full information about a user
    public UserFullDTO getUserFUllById(String id)throws Error{
        User user = userRepository.findById(id).orElseThrow(() -> new Error("User not found"));
        return modelMapper.map(user, UserFullDTO.class);
    }


    //Get short information about a user
    public UserShortDTO getUserShortById(String id)throws Error{
        User user = userRepository.findById(id).orElseThrow(() -> new Error("User not found"));
        return modelMapper.map(user, UserShortDTO.class);
    }

    public User getUserById(String id)throws Error{
        User user = userRepository.findById(id).orElseThrow(() -> new Error("User not found"));
        return modelMapper.map(user, User.class);
    }


    //Get the list of the user wardrobes
    public List<WardrobeDTO> getWardrobes(String userId)throws Error{
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Wardrobe> wardrobes  = user.getWardrobes();
        return  wardrobes.stream()
                .map(e -> modelMapper.map(e,WardrobeDTO.class))
                .collect(Collectors.toList());
    }


    //Get all elements contain in user wardrobe by his name
    public WardrobeDTO getWardrobeByName(String userId, String wardrobeId)throws Error{
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Wardrobe> wardrobes = user.getWardrobes();
        for ( Wardrobe wardrobe: wardrobes) {
            if (wardrobe.getName().equals(wardrobeId)){
                return modelMapper.map(wardrobe, WardrobeDTO.class);
            }
        }
        throw new Error("Wardrobe not found");
    }


    //Get the list of the user event
    public List<EventDTO> getEvent(String userId)throws Error{
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Event> events = user.getEvents();
        return  events.stream()
                .map(e -> modelMapper.map(e,EventDTO.class))
                .collect(Collectors.toList());
    }
}