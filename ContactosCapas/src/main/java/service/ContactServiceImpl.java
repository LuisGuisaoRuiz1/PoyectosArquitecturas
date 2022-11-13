package service;

import java.util.List;
import model.Contact;
import dao.IContactRepository;

/**
 *
 * @author 57322
 */
public class ContactServiceImpl implements IContactService {

    /*Atributos*/
    private IContactRepository contactRepository;

    /*Constructor*/
    public ContactServiceImpl(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Boolean saveContact(Contact request) {
        return this.contactRepository.saveContact(request);
    }

    @Override
    public List<Contact> findAllContact() {
        return this.contactRepository.findAllContact();
    }

    @Override
    public Contact findByName(String name) {
        return this.contactRepository.findByName(name);
    }

    @Override
    public Boolean updateContact(Integer id, Contact updateContact) {
        return this.contactRepository.updateContact(id, updateContact);
    }

    @Override
    public Boolean deleteContact(Integer id) {
        return this.contactRepository.deleteContact(id);
    }
}
