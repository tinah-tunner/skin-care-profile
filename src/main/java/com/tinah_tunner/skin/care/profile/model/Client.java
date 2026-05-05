package com.tinah_tunner.skin.care.profile.model;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👤 Personal Info
    private String fullName;
    private Integer age;
    private String contactDetails;

    // 🧴 Skin Profile
    @Enumerated(EnumType.STRING)
    private SkinType skinType;

    private String skinTone;

    @Enumerated(EnumType.STRING)
    private AcneLevel acneLevel;

    @Enumerated(EnumType.STRING)
    private SensitivityLevel sensitivityLevel;

    // ⚠️ Health Info
    @Column(length = 1000)
    private String allergies;

    @Column(length = 1000)
    private String skinConditions;

    // 📅 Tracking
    @CreatedDate
    private LocalDate firstConsultationDate;
    @LastModifiedDate
    private LocalDate lastUpdatedDate;
     @CreatedBy
    private String createdBy;   // who created the record

    @LastModifiedBy
    private String updatedBy;   // who last updated the record

    // Constructors
    public Client() {}

    public Client(String createdBy) {
        this.createdBy = createdBy;
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }

    public String getSkinTone() {
        return skinTone;
    }

    public void setSkinTone(String skinTone) {
        this.skinTone = skinTone;
    }

    public AcneLevel getAcneLevel() {
        return acneLevel;
    }

    public void setAcneLevel(AcneLevel acneLevel) {
        this.acneLevel = acneLevel;
    }

    public SensitivityLevel getSensitivityLevel() {
        return sensitivityLevel;
    }

    public void setSensitivityLevel(SensitivityLevel sensitivityLevel) {
        this.sensitivityLevel = sensitivityLevel;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getSkinConditions() {
        return skinConditions;
    }

    public void setSkinConditions(String skinConditions) {
        this.skinConditions = skinConditions;
    }

    public LocalDate getFirstConsultationDate() {
        return firstConsultationDate;
    }

    public void setFirstConsultationDate(LocalDate firstConsultationDate) {
        this.firstConsultationDate = firstConsultationDate;
    }

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
