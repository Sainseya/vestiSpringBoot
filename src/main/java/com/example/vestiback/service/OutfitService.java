package com.example.vestiback.service;

import com.example.vestiback.dto.OutfitDTO;
import com.example.vestiback.model.*;
import com.example.vestiback.repository.UserRepository;
import com.example.vestiback.service.Exception.Error;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutfitService {
    private final UserRepository userRepository;

    public OutfitService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Item> getTops(String userId, String wardrobeName) throws Error {
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Wardrobe> wardrobes = user.getWardrobes();

        for (Wardrobe wardrobe : wardrobes) {
            if (wardrobe.getName().equals(wardrobeName)) {
                List<Item> tops = wardrobe.getItems();
                return tops.stream().filter(e -> e.getType().equals("top"))
                        .collect(Collectors.toList());
            }
        }
        throw new Error("Tops not found");
    }

    public List<Item> getBottoms(String userId, String wardrobeName) throws Error{
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Wardrobe> wardrobes = user.getWardrobes();

        for (Wardrobe wardrobe : wardrobes) {
            if (wardrobe.getName().equals(wardrobeName)) {
                List<Item> bottoms = wardrobe.getItems();
                return bottoms.stream().filter(e -> e.getType().equals("bottom"))
                        .collect(Collectors.toList());
            }
        }
        throw new Error("Bottoms not found");
    }

    /*
      <p>
      * La méthode getOutfit prend en paramètre userId, l'ID de l'utilisateur, et retourne une liste d'objets représentant les tenues de l'utilisateur.
      *
      * La méthode commence par récupérer l'utilisateur correspondant à l'ID fourni en utilisant le UserRepository.
      Si l'utilisateur n'est pas trouvé, une exception Error est levée avec le message "User not found".
      *
      * Ensuite, la méthode récupère la liste des garde-robes (wardrobes) de l'utilisateur.
      *
      * Un nouvel ArrayList appelé outfits est créé pour stocker les tenues.
      *
      * La méthode parcourt chaque garde-robe dans la liste des garde-robes de l'utilisateur en utilisant une boucle for-each.
      *
      * À l'intérieur de la boucle, les bas (bottoms) et les hauts (tops) de chaque garde-robe sont récupérés à l'aide des méthodes getBottoms() et getTops() respectivement.
      *
      * Pour chaque liste de bas et de hauts, un Stream est créé à l'aide de la méthode stream().
      *
      * Ensuite, la méthode map() est utilisée pour mapper chaque élément de la liste d'objets d'origine à un objet OutfitDTO à l'aide de modelMapper.map().
      Cela permet de convertir les objets de type User.Wardrobe.Bottom et User.Wardrobe.Top en objets de type OutfitDTO.
      *
      * La méthode toList() est appelée pour convertir le Stream en une liste d'objets OutfitDTO.
      *
      * Enfin, la méthode findAny() est utilisée pour obtenir un élément de la liste résultante.
      Cet élément est ajouté à la liste des tenues (outfits) à l'aide de la méthode add().
      *
      * Une fois que toutes les garde-robes ont été traitées, la méthode retourne la liste des tenues (outfits).
      *
      * Si aucune tenue n'est trouvée (c'est-à-dire si la liste des garde-robes est vide), une exception
      <p/>

      @param userId : String
     * @return une liste des tenues de l'utilisateurs généré aléatoirement.
     * @author Nseya Malumba
     */
    public List<Object> getOutfit(String userId) throws Error{
        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        List<Wardrobe> wardrobes = user.getWardrobes();
        List<Object> outfits = new ArrayList<>();

        for (Wardrobe wardrobe: wardrobes) {
            List<Item> items = wardrobe.getItems();

            //Mélange les objects contenus les dressings
            Collections.shuffle(items);

            if (!items.isEmpty()){

                outfits.add(items.stream().filter(e -> e.getType().equals("top")).findFirst().orElse(null));

                outfits.add(items.stream().filter(e -> e.getType().equals("bottom")).findFirst().orElse(null));

                outfits.add(items.stream().filter(e -> e.getType().equals("shoes")).findFirst().orElse(null));

                //Save user outfit.
                userRepository.save(outfit);

                return outfits;
            }
        }
        throw new Error("Outfit not found");
    }
}
