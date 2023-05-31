package com.example.vestiback.controller;
import com.example.vestiback.dto.*;
import com.example.vestiback.model.User;
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
    private final ObjectMapper mapper;

    private UserController(UserService userService, ObjectMapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }
    @GetMapping("")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserFullDTO getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/{userId}/{itemId}")
    public ItemDTO getItemById(@PathVariable String userId, @PathVariable String itemId){
        return userService.getItemById(userId,itemId);
    }

    @GetMapping("{userId}/{wardrobeId}")
    public wardrobeDTO getDressingById(@PathVariable String userId, @PathVariable String wardrobeId){
        return userService.getDressingById(userId, wardrobeId);
    }

    @GetMapping("/{userId}/{eventId}")
    public EventDTO getEventById(@PathVariable String userId, @PathVariable String eventId){
        return userService.getEventById(userId,eventId);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable String id){
        return userService.update(user,id);
    }

    @PostMapping("")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/all")
    public void deleteUserAll(){
        userService.deleteAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id){
        userService.deleteById(id);
        return ResponseEntity.ok("No Content");
    }
}