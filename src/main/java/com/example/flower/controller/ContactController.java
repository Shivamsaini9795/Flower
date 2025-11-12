package com.example.flower.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.flower.model.Contact;
import com.example.flower.service.ContactService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contact")
    public Contact submitContact(@RequestBody Contact contact) {
        return contactService.save(contact);
    }

    @GetMapping("/admin/contacts")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}
