package com.example.vestiback.controller;
import com.example.vestiback.dto.*;
import com.example.vestiback.model.Item;
import com.example.vestiback.model.User;
import com.example.vestiback.service.Exception.Error;
import com.example.vestiback.service.OutfitService;
import com.example.vestiback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vesti/request")
public class UserController {
    private final UserService userService;
    private final OutfitService outfitService;

    public UserController(UserService userService, OutfitService outfitService){
        this.userService = userService;
        this.outfitService = outfitService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public List<UserShortDTO> findAll() throws Error { //Get all user.
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{userId}") //Find a user by his id.
    public UserFullDTO getUserById(@PathVariable String userId) throws Error {
        return userService.getUserFUllById(userId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/user/{userId}") //Find short information about a user.
    public UserShortDTO getUserShortById(@PathVariable String userId) throws Error {
        return userService.getUserShortById(userId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{userId}/wardrobes")
    public List<WardrobeDTO> getWardrobes(@PathVariable String userId) throws Error {
        return userService.getWardrobes(userId);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{userId}/{wardrobeName}")
    public WardrobeDTO getDressingById(@PathVariable String userId, @PathVariable String wardrobeName) throws Error {
        return userService.getWardrobeByName(userId, wardrobeName);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{userId}/events")
    public List<EventDTO> getEvent(@PathVariable String userId) throws Error {
        return userService.getEvent(userId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/{userId}/wardrobe/{type}") //Find all user tops.
    public List<Item> getByType(@PathVariable String userId, @PathVariable String type) throws Error {
        return outfitService.findItemsByUserIdAndType(userId, type);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{userId}/{eventName}/outfit") //Find outfit.
    public List<Item> getOutfit(@PathVariable String userId, @PathVariable String eventName) throws Error {
        return outfitService.getOutfit(userId,eventName);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/{userId}")
    public User update(@RequestBody User user, @PathVariable String userId) throws Error {
        return userService.update(user,userId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/{userId}/{eventName}/outfit")
    public User updateOutfit(@PathVariable String userId,@PathVariable String eventName) throws Error {
        return outfitService.createRandomOutfit(userId, eventName);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteUserAll(){
        userService.deleteAll();
        return ResponseEntity.ok("You've deleted all users");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable String userId) throws Error {
        userService.deleteById(userId);
        return ResponseEntity.ok("You've deleted user" + "{userid}");
    }
}