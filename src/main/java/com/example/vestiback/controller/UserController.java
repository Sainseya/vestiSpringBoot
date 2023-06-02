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
    public WardrobeDTO getDressingById(@PathVariable String userId, @PathVariable String wardrobeId){
        return userService.getWardrobeById(userId, wardrobeId);
    }

    @GetMapping("/{userId}/event")
    public List<EventDTO> getEventById(@PathVariable String userId){
        return userService.getEventById(userId);
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

    @GetMapping("{userId}/wardrobe/{wardrobeId}/tops") //Find all user tops.
    public List<TopDTO> getTops(@PathVariable String userId,@PathVariable String wardrobeId){
        return outfitService.getTops(userId, wardrobeId);
    }
    @GetMapping("{userId}/wardrobe/{wardrobeId}/bottoms") //Find all users bottoms.
    public List<BottomDTO> getBottoms(@PathVariable String userId,@PathVariable String wardrobeId){
        return outfitService.getBottoms(userId, wardrobeId);
    }

    @GetMapping("{userId}/outfit") //Find outfit.
    public List<Object> getOutfit(@PathVariable String userId){
        return outfitService.getOutfit(userId);
    }

}