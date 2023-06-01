package com.example.vestiback.service;

import com.example.vestiback.dto.TopDTO;
import com.example.vestiback.model.User;
import com.example.vestiback.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutfitService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;



    public OutfitService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public TopDTO getTop(String userId, String wardrobeId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User.Wardrobe> wardrobes = user.getWardrobes();

        for (User.Wardrobe wardrobe: wardrobes) {
            List<User.Wardrobe.Top> tops = wardrobe.getTops();

            for (User.Wardrobe.Top top: tops) {
                if (wardrobe.getId().equals(wardrobeId)){
                    return modelMapper.map(top, TopDTO.class);
                }
            }
        }
        throw new RuntimeException("Tops not found");
    }


}
