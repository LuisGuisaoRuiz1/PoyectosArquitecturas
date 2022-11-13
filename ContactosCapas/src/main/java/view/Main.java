package view;

import controller.ContactController;
import model.Contact;
import dao.ContactRepositoryImpl;
import dao.IContactRepository;
import service.ContactServiceImpl;
import service.IContactService;

/**
 *
 * @author 57322
 */
public class Main {

    public static void main(String args[]) {
        Contact model = new Contact();
        IContactService contactService = new ContactServiceImpl(new ContactRepositoryImpl());
        FrmContact frm = new FrmContact();
        ContactController contactController = new ContactController(model, contactService, frm);
        contactController.iniciar();
        frm.setVisible(true);

    }

}
