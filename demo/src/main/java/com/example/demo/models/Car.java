package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table
public class Car {

    @Id
    private Long id;
    @NotNull
    private Integer year;
    @NotNull @NotBlank
    private String color;
    @NotNull
    private Integer mileage;

    private String owner;
    @NotNull
    private Long modelId;

    public Car(@NotNull Integer year, @NotNull @NotBlank String color, @NotNull Integer mileage, String owner, @NotNull Long modelId) {
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.owner = owner;
        this.modelId = modelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", owner='" + owner + '\'' +
                ", modelId=" + modelId +
                '}';
    }
}