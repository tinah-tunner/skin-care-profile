
package com.tinah_tunner.skin.care.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tinah_tunner.skin.care.profile.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}