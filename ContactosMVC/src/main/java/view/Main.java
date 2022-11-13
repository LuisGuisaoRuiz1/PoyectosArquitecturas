package view;

import controller.ContactController;
import model.Contact;
import model.ContactRepositoryImpl;
import model.IContactRepository;

/**
 *
 * @author 57322
 */
public class Main {

    public static void main(String args[]) {
        Contact model = new Contact();
        IContactRepository contactRepository = new ContactRepositoryImpl();
        FrmContact frm = new FrmContact();
        ContactController contactController = new ContactController(model, contactRepository, frm);
        contactController.iniciar();
        frm.setVisible(true);

    }

}
