package com.tinah_tunner.skin.care.profile.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tinah_tunner.skin.care.profile.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    // Find clients by skin type
    List<Client> findBySkinType(String skinType);
    
    // Find clients by acne level
    List<Client> findByAcneLevel(String acneLevel);
    
    // Find clients by sensitivity level
    List<Client> findBySensitivityLevel(String sensitivityLevel);
    
    // Find clients by age range
    List<Client> findByAgeBetween(Integer minAge, Integer maxAge);
    
    // Find clients by skin tone
    List<Client> findBySkinTone(String skinTone);
    
    // Check if client exists by contact details
    boolean existsByContactDetails(String contactDetails);
    
    // Find client by contact details (unique if contact details are unique)
    Optional<Client> findByContactDetails(String contactDetails);
    
    // Count clients by skin type
    long countBySkinType(String skinType);
    
    // Delete client by contact details
    void deleteByContactDetails(String contactDetails);
    
    // Custom query to find clients with high acne level
    @Query("SELECT c FROM Client c WHERE c.acneLevel = :level AND c.age >= :minAge")
    List<Client> findClientsByAcneLevelAndMinAge(@Param("level") String acneLevel, @Param("minAge") Integer minAge);
    
    // Custom query to find clients updated after a certain date
    @Query("SELECT c FROM Client c WHERE c.lastUpdatedDate >= :date")
    List<Client> findClientsUpdatedAfter(@Param("date") java.time.LocalDate date);
    
    // Custom query to search clients by name (case insensitive)
    @Query("SELECT c FROM Client c WHERE LOWER(c.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Client> searchByFullName(@Param("name") String name);
    
    // Get all clients ordered by last updated date descending
    List<Client> findAllByOrderByLastUpdatedDateDesc();
    
    // Get recent clients (last 30 days)
    @Query("SELECT c FROM Client c WHERE c.firstConsultationDate >= :date")
    List<Client> findRecentClients(@Param("date") java.time.LocalDate date);
}