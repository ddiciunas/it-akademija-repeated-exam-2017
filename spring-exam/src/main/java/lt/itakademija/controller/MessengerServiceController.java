package lt.itakademija.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.model.Id;
import lt.itakademija.model.command.CreateContact;
import lt.itakademija.model.command.CreateMessage;
import lt.itakademija.model.command.UpdateContact;
import lt.itakademija.model.query.Contact;
import lt.itakademija.model.query.Message;
import lt.itakademija.repository.MessengerRepository;

/**
 * Created by mariusg on 2017.03.19.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/spring-exam/webapi/messenger/")
public class MessengerServiceController {
    @Autowired
    private final MessengerRepository repository;

    @Autowired
    public MessengerServiceController(MessengerRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    public Id createContact(@RequestBody CreateContact createContact) {
        return repository.createContact(createContact);
    }

    @GetMapping(value = "/contacts")
    public List<Contact> getContacts() {
        return repository.getContacts();
    }

    @GetMapping(value = "/contacts/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Contact getContact(@PathVariable("contactId") Long contactId) {
        return repository.getContact(contactId);
    }

    @PutMapping(value = "/contacts/{contactId}")
    public void updateContact(@PathVariable("contactId") Long contactId, @RequestBody UpdateContact updateContact) {
        repository.updateContact(contactId, updateContact);
    }

    @DeleteMapping(value = "/contacts/{contactId}")
    public void deleteContact(@PathVariable("contactId") Long contactId) {
        repository.deleteContact(contactId);
    }

    @PostMapping(value = "/messages/{contactId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Id createMessage(@PathVariable("contactId") Long contactId, @RequestBody CreateMessage createMessage) {
        return repository.createMessage(contactId, createMessage);
    }

    @GetMapping(value = "/messages/{contactId}")
    public List<Message> getMessages(@PathVariable("contactId") Long contactId) {
        return repository.getMessages(contactId);
    }
}
