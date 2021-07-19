package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table
public class Model {

    @Id
    private Long id;
    @NotNull @NotBlank
    private String name;
    @NotNull
    private Long brandId;
    @NotNull @NotBlank @Size(max=255)
    private String imagePath;

    public Model(@NotNull @NotBlank String name, @NotNull Long brandId, @NotNull @NotBlank String imagePath) {
        this.name = name;
        this.brandId = brandId;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brandId=" + brandId +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}