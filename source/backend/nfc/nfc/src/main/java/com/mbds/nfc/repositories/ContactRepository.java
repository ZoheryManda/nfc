package com.mbds.nfc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbds.nfc.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
