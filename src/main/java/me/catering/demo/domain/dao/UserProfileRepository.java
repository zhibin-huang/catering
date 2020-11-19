package me.catering.demo.domain.dao;

import me.catering.demo.domain.document.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    void deleteByUserId(int userId);

    UserProfile findByUserId(int userId);

    UserProfile findByUsername(String username);
}