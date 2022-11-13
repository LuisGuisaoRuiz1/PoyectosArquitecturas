package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.*;
import view.FrmContact;
import dao.IContactRepository;
import service.IContactService;

/**
 *
 * @author 57322
 */
public class ContactController implements ActionListener {

    /*Atributos*/
    private Contact model;
    private IContactService contactService;
    private FrmContact frm;

    /*Constructor*/
    public ContactController(Contact model, IContactService contactService, FrmContact frm) {
        this.model = model;
        this.contactService = contactService;
        this.frm = frm;
        this.frm.btnSave.addActionListener(this);
        this.frm.btnListar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnEditar.addActionListener(this);
        /*Init Table*/
        DefaultTableModel tableModel = (DefaultTableModel) this.frm.jTable1.getModel();
        tableModel.setRowCount(0);
        frm.jTable1.setModel(tableModel);
    }

    public void iniciar() {
        frm.setTitle("CONTACT"); // establecer titulo del formulario
        frm.setLocationRelativeTo(null); // posicion del formulario centrado
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnSave) {
            if (frm.txtNumber.getText().trim().isEmpty() || frm.txtName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE LLENAR EL FORMULARIO COMPLETO");
            } else {
                this.model.setName(this.frm.txtName.getText());
                this.model.setNumber(Long.valueOf(this.frm.txtNumber.getText()));
                GroupEnumeration group = GroupEnumeration.valueOf(this.frm.comboGroup.getSelectedItem().toString());
                this.model.setGroup(group);
                if (this.contactService.saveContact(this.model)) {
                    JOptionPane.showMessageDialog(null, "Contacto guardado correctamente!");
                    updateTable();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar el contacto!");
                    limpiar();
                }
            }
        }
        if (e.getSource() == frm.btnListar) {
            List<Contact> contacts = this.contactService.findAllContact();
            DefaultTableModel tableModel = (DefaultTableModel) this.frm.jTable1.getModel();
            tableModel.setRowCount(0);
            for (int i = 0; i < contacts.size(); i++) {
                String[] data = new String[4];
                data[0] = contacts.get(i).getIdContact().toString();
                data[1] = contacts.get(i).getName();
                data[2] = contacts.get(i).getNumber().toString();
                data[3] = contacts.get(i).getGroup().toString();
                tableModel.addRow(data);
            }
            this.frm.jTable1.removeAll();
            this.frm.jTable1.setModel(tableModel);
        }
        if (e.getSource() == frm.btnEditar) {
            int indiceEliminar = frm.jTable1.getSelectedRow();
            if (indiceEliminar == -1) {
                JOptionPane.showMessageDialog(null, "SELECCIONE LA FILA QUE DESEA EDITAR");
            } else {
                if (frm.txtNumber.getText().trim().isEmpty() || frm.txtName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR EL FORMULARIO CON LA NUEVA INFORMACIÓN");
                } else {
                    Integer idEliminar = Integer.valueOf(frm.jTable1.getModel().getValueAt(indiceEliminar, 0).toString());
                    this.model.setName(this.frm.txtName.getText());
                    this.model.setNumber(Long.valueOf(this.frm.txtNumber.getText()));
                    GroupEnumeration group = GroupEnumeration.valueOf(this.frm.comboGroup.getSelectedItem().toString());
                    this.model.setGroup(group);
                    if (this.contactService.updateContact(idEliminar, this.model)) {
                        JOptionPane.showMessageDialog(null, "Contacto actualizado correctamente!");
                        updateTable();
                        limpiar();

                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el contacto!");
                        updateTable();
                        limpiar();
                    }
                }
            }
        }
        if (e.getSource() == frm.btnEliminar) {
            int indiceEliminar = frm.jTable1.getSelectedRow();
            if (indiceEliminar == -1) {
                JOptionPane.showMessageDialog(null, "SELECCIONE LA FILA QUE DESEA ELIMINAR");
            } else {
                Integer idEliminar = Integer.valueOf(frm.jTable1.getModel().getValueAt(indiceEliminar, 0).toString());
                if (this.contactService.deleteContact(idEliminar)) {
                    JOptionPane.showMessageDialog(null, "Contacto eliminado correctamente!");
                    updateTable();

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el contacto!");
                    updateTable();
                }
            }

        }
        if (e.getSource() == frm.jTable1) {
            System.out.println("Hola");
        }
    }

    public void updateTable() {
        List<Contact> contacts = this.contactService.findAllContact();
        DefaultTableModel tableModel = (DefaultTableModel) this.frm.jTable1.getModel();
        tableModel.setRowCount(0);
        for (int i = 0; i < contacts.size(); i++) {
            String[] data = new String[4];
            data[0] = contacts.get(i).getIdContact().toString();
            data[1] = contacts.get(i).getName();
            data[2] = contacts.get(i).getNumber().toString();
            data[3] = contacts.get(i).getGroup().toString();
            tableModel.addRow(data);
        }
        this.frm.jTable1.removeAll();
        this.frm.jTable1.setModel(tableModel);
    }

    public void limpiar() {
        frm.txtName.setText(null);
        frm.txtNumber.setText(null);
    }

}
