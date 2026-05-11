package com.tinah_tunner.skin.care.profile.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private Integer age;

    @Column(unique = true, nullable = false)
    private String email;

    private String contactDetails;

    @Enumerated(EnumType.STRING)
    private SkinType skinType;

    private String skinTone;

    @Enumerated(EnumType.STRING)
    private AcneLevel acneLevel;

    @Enumerated(EnumType.STRING)
    private SensitivityLevel sensitivityLevel;

    private String allergies;

    private String skinConditions;

    private LocalDate createdDate;

    private LocalDate lastUpdatedDate;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDate.now();
        this.lastUpdatedDate = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdatedDate = LocalDate.now();
    }
}