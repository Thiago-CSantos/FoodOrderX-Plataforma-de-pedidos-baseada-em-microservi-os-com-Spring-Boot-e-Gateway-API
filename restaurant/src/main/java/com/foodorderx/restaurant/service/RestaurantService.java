package com.foodorderx.restaurant.service;

import com.foodorderx.restaurant.dto.RequestRestaurant;
import com.foodorderx.restaurant.dto.ResponseRestaurant;
import com.foodorderx.restaurant.entity.Restaurant;
import com.foodorderx.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {

    private static final String IMAGE_FOLDER = "uploads/";

    @Autowired
    private RestaurantRepository repository;

    public List<ResponseRestaurant> listRestaurant() {
        List<Restaurant> restaurantList = repository.findAll();

        return restaurantList.stream()
                .map(r -> new ResponseRestaurant(
                        r.getId(),
                        r.getName(),
                        r.getDescription(),
                        r.getAddress(),
                        r.getImageUrl(),
                        r.isOpen()
                )).toList();
    }

    public ResponseRestaurant findById(Long id) {
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        return new ResponseRestaurant(restaurant);
    }

    public void createRestaurant(RequestRestaurant requestRestaurant, MultipartFile imageFile) {
        try {
            File uploadDir = new File(IMAGE_FOLDER);

            if (!uploadDir.exists()) {
                System.out.println(uploadDir.mkdirs());
            }

            String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_FOLDER + filename);

            Files.write(filePath, imageFile.getBytes());

            Restaurant restaurant = new Restaurant(
                    requestRestaurant.name(),
                    requestRestaurant.description(),
                    requestRestaurant.address(),
                    filePath.toString(),
                    requestRestaurant.open(),
                    null
            );
            repository.save(restaurant);

        } catch (IOException e) {
            throw new RuntimeException("Erro salvar imagem", e);
        }
    }

    public void updateRestaurant(Long id, RequestRestaurant dto, MultipartFile imageFile) {
        Restaurant r = repository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        if (dto.name() != null) r.setName(dto.name());
        if (dto.description() != null) r.setDescription(dto.description());
        if (dto.address() != null) r.setAddress(dto.address());
        r.setOpen(dto.open());

        if (imageFile != null) {
            try {
                File uploadDir = new File(IMAGE_FOLDER);

                if (!uploadDir.exists()) {
                    System.out.println(uploadDir.mkdirs());
                }

                String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path filePath = Paths.get(IMAGE_FOLDER + filename);

                Files.write(filePath, imageFile.getBytes());

                r.setImageUrl(filePath.toString());
            } catch (IOException e) {
                throw new RuntimeException("Erro salvar imagem", e);
            }
        }

        repository.save(r);
    }

    public void deleteRestaurant(Long id) {
        Restaurant r = repository.findById(id).orElseThrow(() -> new RuntimeException("Erro ao buscar"));
        repository.delete(r);
    }

}
