package com.foodorderx.restaurant.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "open")
    private boolean open;
    @OneToMany
    private List<MenuItem> menuItemList;

    public Restaurant() {
    }

    public Restaurant(String name, String description, String address, String imageUrl, boolean open, List<MenuItem> menuItemList) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.imageUrl = imageUrl;
        this.open = open;
        this.menuItemList = menuItemList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
