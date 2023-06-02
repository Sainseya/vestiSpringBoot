package com.example.vestiback.service;

import com.example.vestiback.dto.BottomDTO;
import com.example.vestiback.dto.OutfitDTO;
import com.example.vestiback.dto.TopDTO;
import com.example.vestiback.model.User;
import com.example.vestiback.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutfitService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;



    public OutfitService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<TopDTO> getTops(String userId, String wardrobeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User.Wardrobe> wardrobes = user.getWardrobes();

        for (User.Wardrobe wardrobe : wardrobes) {
            if (wardrobe.getId().equals(wardrobeId)) {
                List<User.Wardrobe.Top> tops = wardrobe.getTops();
                return tops.stream()
                        .map(e -> modelMapper.map(e, TopDTO.class))
                        .collect(Collectors.toList());
            }
        }
        throw new RuntimeException("Tops not found");
    }

    /**Cette foction recupère tous les élements contenus dans le bottoms*/
    public List<BottomDTO> getBottoms(String userId, String wardrobeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User.Wardrobe> wardrobes = user.getWardrobes();

        for (User.Wardrobe wardrobe : wardrobes) {
            if (wardrobe.getId().equals(wardrobeId)) {
                List<User.Wardrobe.Bottom> bottoms = wardrobe.getBottoms();
                return bottoms.stream()
                        .map(e -> modelMapper.map(e, BottomDTO.class))
                        .collect(Collectors.toList());
            }
        }
        throw new RuntimeException("Bottoms not found");
    }

    /**
     * @param userId
     * @return
     */
    public List<Object> getOutfit(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User.Wardrobe> wardrobes = user.getWardrobes();
        List<Object> outfits = new ArrayList<>();

        for (User.Wardrobe wardrobe: wardrobes) {
            List<User.Wardrobe.Bottom> bottoms = wardrobe.getBottoms();
            List<User.Wardrobe.Top> tops = wardrobe.getTops();

            outfits.add(bottoms.stream()
                    .map(e -> modelMapper.map(e, OutfitDTO.class))
                    .collect(Collectors.toList()));
            outfits.add(tops.stream()
                    .map(e -> modelMapper.map(e, OutfitDTO.class))
                    .collect(Collectors.toList()));
            return outfits;
        }
        throw new RuntimeException("Outfit not not found");
    }



}
