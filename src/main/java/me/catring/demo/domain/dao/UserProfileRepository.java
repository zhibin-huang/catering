package me.catring.demo.domain.dao;

import me.catring.demo.domain.document.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    void deleteByUserId(int userId);

    UserProfile findByUserId(int userId);

    UserProfile findByUsername(String username);
}