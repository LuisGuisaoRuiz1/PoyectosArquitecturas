package model;

import java.util.List;

/**
 *
 * @author 57322
 */
public interface IContactRepository {
    Boolean saveContact(Contact request);
    List<Contact> findAllContact();
    Contact findByName(String name);
    Boolean updateContact(Integer id, Contact updateContact);
    Boolean deleteContact(Integer id);
}
