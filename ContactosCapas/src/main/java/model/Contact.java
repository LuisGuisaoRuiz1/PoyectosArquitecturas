
package model;

/**
 *
 * @author 57322
 */
public class Contact {
    
    /*Atributos*/
    private Integer idContact;
    private String name;
    private Long number;
    private GroupEnumeration group;
    
    /*Constructores*/

    /**
     *
     * @param name
     * @param number
     * @param group
     */
    public Contact(String name, Long number, GroupEnumeration group) {
        this.name = name;
        this.number = number;
        this.group = group;
    }
    public Contact(){}
    
    /*Getters and Setters*/

    public Integer getIdContact() {
        return idContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public GroupEnumeration getGroup() {
        return group;
    }

    public void setGroup(GroupEnumeration group) {
        this.group = group;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }
    
    
    
    
}
