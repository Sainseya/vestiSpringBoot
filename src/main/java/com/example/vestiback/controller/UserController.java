package com.example.vestiback.controller;
import com.example.vestiback.dto.*;
import com.example.vestiback.model.Item;
import com.example.vestiback.model.User;
import com.example.vestiback.service.Exception.Error;
import com.example.vestiback.service.OutfitService;
import com.example.vestiback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vesti")
public class UserController {
    private final UserService userService;
    private final OutfitService outfitService;

    private UserController(UserService userService, OutfitService outfitService){
        this.userService = userService;
        this.outfitService = outfitService;
    }


    @GetMapping("")
    public List<User> findAll() throws Error { //Get all user.
        return userService.findAll();
    }
    @GetMapping("/{id}") //Find a user by his id.
    public UserFullDTO getUserById(@PathVariable String id) throws Error {
        return userService.getUserFUllById(id);
    }

    @GetMapping("/user/{userId}") //Find short information about a user.
    public UserShortDTO getUserShortById(@PathVariable String userId) throws Error {
        return userService.getUserShortById(userId);
    }

    @GetMapping("{userId}/wardrobes")
    public List<WardrobeDTO> getWardrobes(@PathVariable String userId) throws Error {
        return userService.getWardrobes(userId);
    }

    @GetMapping("{userId}/wardrobe/{wardrobeName}")
    public WardrobeDTO getDressingById(@PathVariable String userId, @PathVariable String wardrobeName) throws Error {
        return userService.getWardrobeByName(userId, wardrobeName);
    }

    @GetMapping("/{userId}/events")
    public List<EventDTO> getEvent(@PathVariable String userId) throws Error {
        return userService.getEvent(userId);
    }
    @GetMapping("{userId}/wardrobe/{wardrobeName}/tops") //Find all user tops.
    public List<Item> getTops(@PathVariable String userId, @PathVariable String wardrobeName) throws Error {
        return outfitService.getTops(userId, wardrobeName);
    }

    @GetMapping("{userId}/wardrobe/{wardrobeName}/bottoms") //Find all users bottoms.
    public List<Item> getBottoms(@PathVariable String userId,@PathVariable String wardrobeName) throws Error {
        return outfitService.getBottoms(userId, wardrobeName);
    }

//    @GetMapping("{userId}/outfit") //Find outfit.
//    public List<Object> getOutfit(@PathVariable String userId) throws Error {
//        return outfitService.getOutfit(userId);
//    }

    @PostMapping("")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable String id) throws Error {
        return userService.update(user,id);
    }

    @PutMapping("/{userId}/{eventId}")
    public User updateOutfit(@PathVariable String userId,@PathVariable String eventId) throws Error {
        return outfitService.createRandomOutfit(userId, eventId);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteUserAll(){
        userService.deleteAll();
        return ResponseEntity.ok("You delete all user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) throws Error {
        userService.deleteById(id);
        return ResponseEntity.ok("You delete " +"{id}"+ "user");
    }



}