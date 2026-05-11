package com.tinah_tunner.skin.care.profile.dto;

import com.tinah_tunner.skin.care.profile.model.*;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {

    private Long id;
    private String fullName;
    private Integer age;
    private String email;
    private String contactDetails;

    private SkinType skinType;
    private String skinTone;
    private AcneLevel acneLevel;
    private SensitivityLevel sensitivityLevel;

    private String allergies;
    private String skinConditions;

    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
}