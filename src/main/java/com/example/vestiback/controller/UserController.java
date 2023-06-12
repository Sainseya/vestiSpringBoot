package com.example.vestiback.controller;
import com.example.vestiback.dto.*;
import com.example.vestiback.model.Event;
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
    @GetMapping("/{userId}") //Find a user by his id.
    public UserFullDTO getUserById(@PathVariable String userId) throws Error {
        return userService.getUserFUllById(userId);
    }

    @GetMapping("/user/{userId}") //Find short information about a user.
    public UserShortDTO getUserShortById(@PathVariable String userId) throws Error {
        return userService.getUserShortById(userId);
    }

    @GetMapping("{userId}/wardrobes")
    public List<WardrobeDTO> getWardrobes(@PathVariable String userId) throws Error {
        return userService.getWardrobes(userId);
    }

    @GetMapping("{userId}/{wardrobeName}")
    public WardrobeDTO getWardrobeByName(@PathVariable String userId, @PathVariable String wardrobeName) throws Error {
        return userService.getWardrobeByName(userId, wardrobeName);
    }

    @GetMapping("/{userId}/events")
    public List<EventDTO> getEvent(@PathVariable String userId) throws Error {
        return userService.getEvents(userId);
    }

    @GetMapping("{userId}/wardrobe/{type}") //Find all user tops.
    public List<Item> getByType(@PathVariable String userId, @PathVariable String type) throws Error {
        return outfitService.findItemsByUserIdAndType(userId, type);
    }

    @GetMapping("{userId}/{eventName}/outfit") //Find outfit.
    public List<Item> getOutfit(@PathVariable String userId, @PathVariable String eventName) throws Error {
        return outfitService.getOutfit(userId,eventName);
    }

    @GetMapping("{userId}/outfitHistory") //Find outfitHistory.
    public List<Event> getOutfitHistory(@PathVariable String userId) throws Error {
        return userService.getOutfitHistory(userId);
    }


    @PostMapping("")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{userId}")
    public User update(@RequestBody User user, @PathVariable String userId) throws Error {
        return userService.update(user,userId);
    }

    @PutMapping("/{userId}/{eventName}/outfit")
    public User updateOutfit(@PathVariable String userId,@PathVariable String eventName) throws Error {
        return outfitService.createRandomOutfit(userId, eventName);
    }

    @PutMapping("/{userId}/{wardrobeName}/newItem")
    public User updateWardrobe(@PathVariable String userId,@PathVariable String wardrobeName,@RequestBody Item item) throws Error {
        return userService.putItemInWardrobe(userId, wardrobeName, item);
    }


    @DeleteMapping("/all")
    public ResponseEntity<String> deleteUserAll(){
        userService.deleteAll();
        return ResponseEntity.ok("You've deleted all users");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable String userId) throws Error {
        userService.deleteById(userId);
        return ResponseEntity.ok("You've deleted user" + "{userid}");
    }



}