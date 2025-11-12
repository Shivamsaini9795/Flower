package com.example.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flower.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
