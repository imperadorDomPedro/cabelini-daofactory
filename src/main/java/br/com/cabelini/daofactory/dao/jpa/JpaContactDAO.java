/**
 *
 */
package br.com.cabelini.daofactory.dao.jpa;

import br.com.cabelini.daofactory.dao.ContactDAO;
import br.com.cabelini.daofactory.model.Contact;

import javax.persistence.EntityManager;

/**
 * Implementação, com a tecnologia JPA, da interface DAO responsável pelo
 * mapeamento da entidade {@link Contact}.
 *
 * @author Henrique Moreira - Pedro XVIII
 * @since JDK1.5
 * @version 1.0 $ 2015
 */
public class JpaContactDAO extends JpaBaseDAO<Contact, Long> implements ContactDAO {

    /**
     * Construtor recebendo o {@link EntityManager} criado pela fábrica concreta
     * e repassando ao {@link JpaBaseDAO}.
     *
     * @param manager
     */
    public JpaContactDAO(EntityManager manager) {
        super(manager);
    }

    /**
     * @return @see br.com.commons.model.dao.jpa.JpaBaseDAO#getEntityClass()
     */
    @Override
    public Class<Contact> getEntityClass() {
        return Contact.class;
    }
}
