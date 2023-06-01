package com.example.vestiback.controller;
import com.example.vestiback.dto.*;
import com.example.vestiback.model.User;
import com.example.vestiback.service.OutfitService;
import com.example.vestiback.service.UserService;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vesti")
public class UserController {
    private final UserService userService;
    private final OutfitService outfitService;

    private UserController(UserService userService, ObjectMapper mapper, OutfitService outfitService){
        this.userService = userService;
        this.outfitService = outfitService;
    }


    @GetMapping("")
    public List<User> findAll(){ //Get all user.
        return userService.findAll();
    }
    @GetMapping("/{id}") //Find a user by his id.
    public UserFullDTO getUserById(@PathVariable String id){
        return userService.getUserFUllById(id);
    }

    @GetMapping("/user/{userId}") //Find a short inforamtion about a user.
    public UserShortDTO getUserShortById(@PathVariable String userId){
        return userService.getUserShortById(userId);
    }

    @GetMapping("{userId}/wardrobe/{wardrobeId}")
    public wardrobeDTO getDressingById(@PathVariable String userId, @PathVariable String wardrobeId){
        return userService.getWardrobeById(userId, wardrobeId);
    }

    @GetMapping("/{userId}/event/{eventId}")
    public EventDTO getEventById(@PathVariable String userId, @PathVariable String eventId){
        return userService.getEventById(userId,eventId);
    }

/*    @GetMapping("/{userId}/outfit/{outfitId}")
    public OutfitDTO getOutfitById(@PathVariable String userId, @PathVariable String outfitId){
        return userService.getOutfitById(userId,outfitId);
    }*/

    @PostMapping("")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable String id){
        return userService.update(user,id);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteUserAll(){
        userService.deleteAll();
        return ResponseEntity.ok("You delete all user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id){
        userService.deleteById(id);
        return ResponseEntity.ok("You delete " +"{id}"+ "user");
    }

    @GetMapping("{userId}/wardrobe/{wardrobeId}/top") //Find a user by his id.
    public TopDTO getTop(@PathVariable String userId,@PathVariable String wardrobeId){
        return outfitService.getTop(userId, wardrobeId);
    }

}