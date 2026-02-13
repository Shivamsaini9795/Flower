package com.example.flower.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.flower.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {
}