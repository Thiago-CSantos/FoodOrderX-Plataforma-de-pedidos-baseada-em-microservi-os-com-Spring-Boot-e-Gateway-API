package com.foodorderx.restaurant.service;

import com.foodorderx.restaurant.dto.RequestMenuItem;
import com.foodorderx.restaurant.dto.ResponseMenuItem;
import com.foodorderx.restaurant.entity.MenuItem;
import com.foodorderx.restaurant.entity.Restaurant;
import com.foodorderx.restaurant.repository.MenuItemRepository;
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
public class MenuItemService {

    private static final String IMAGE_FOLDER = "uploads/";

    @Autowired
    MenuItemRepository repository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<ResponseMenuItem> findAllRestaurantMenu(Long id) {
        List<MenuItem> list = repository.findMenuItemsByRestaurantId(id);

        List<ResponseMenuItem> responseMenuItem = list.stream()
                .map(m -> new ResponseMenuItem(
                        m.getName(),
                        m.getDescription(),
                        m.getPrice(),
                        m.getImageUrl()
                )).toList();

        System.out.println(responseMenuItem);

        return responseMenuItem;
    }

    public void createMenuItem(Long id, RequestMenuItem r_menuItem, MultipartFile imageFile) {
        try {
            File uploadDir = new File(IMAGE_FOLDER);

            if (!uploadDir.exists()) {
                System.out.println(uploadDir.mkdirs());
            }

            String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_FOLDER + filename);

            Files.write(filePath, imageFile.getBytes());

            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro em buscar Restaurant"));

            MenuItem menuItem = new MenuItem(
                    r_menuItem.name(),
                    r_menuItem.description(),
                    r_menuItem.price(),
                    filePath.toString(),
                    restaurant
            );

            repository.save(menuItem);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
