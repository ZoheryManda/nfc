package com.mbds.nfc.repositories;

import com.mbds.nfc.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
