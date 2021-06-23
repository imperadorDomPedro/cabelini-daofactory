/**
 * GNU GPL CC - Creative Commons - Legal Code
 *                                             Gurupi, 18 de fevereiro de 2007.
 *
 * Copyright 2007 (c) AC de Souza. All rights reserved. Licença Pública Geral do
 * GNU (GPL) [General Public License]
 *
 * This is  an  unofficial  translation  of the GNU  General Public License into
 * Portuguese. It was not published  by the  Free  Software Foundation, and does
 * not legally state the distribution terms for software that uses the GNU GPL--
 * only the original English text of the GNU GPL does  that.  However,  we  hope
 * that this translation will help Portuguese speakers  understand the  GNU  GPL
 * better.
 *
 * Esta  é  uma  tradução  não-oficial  da  GNU  General  Public License para o
 * Português. Ela não é publicada pela Free Software  Foundation e não  traz os
 * termos de distribuição legal do software que usa a GNU GPL  --  estes termos
 * estão contidos apenas no texto da  GNU  GPL  original  em inglês. No entanto,
 * esperamos  que esta  tradução  ajudará  no melhor  entendimento  da  GNU GPL
 * em Português.
 *
 * Informações:
 * <a href="http://creativecommons.org/licenses/GPL/2.0/legalcode.pt">
 * CREATIVE COMMONS GPL legalcode</a>
 *
 * COMO O PROGRAMA É LICENCIADO SEM CUSTO, NÃO HÁ NENHUMA GARANTIA PARA O
 * PROGRAMA, NO LIMITE PERMITIDO PELA LEI APLICÁVEL. EXCETO QUANDO DE OUTRA
 * FORMA ESTABELECIDO POR ESCRITO, OS TITULARES DOS DIREITOS AUTORAIS E/OU
 * OUTRAS PARTES, FORNECEM O PROGRAMA &quot;NO ESTADO EM QUE SE ENCONTRA;, SEM
 * NENHUMA GARANTIA DE QUALQUER TIPO, TANTO EXPRESSA COMO IMPLÍCITA, INCLUINDO,
 * DENTRE OUTRAS, AS GARANTIAS IMPLÍCITAS DE COMERCIABILIDADE E ADEQUAÇÃO A UMA
 * FINALIDADE ESPECÍFICA. O RISCO INTEGRAL QUANTO À QUALIDADE E DESEMPENHO DO
 * PROGRAMA É ASSUMIDO POR VOCÊ. CASO O PROGRAMA CONTENHA DEFEITOS, VOCÊ ARCARÁ
 * COM OS CUSTOS DE TODOS OS SERVIÇOS, REPAROS OU CORREÇÕES NECESSÁRIAS. EM
 * NENHUMA CIRCUNSTÂNCIA, A MENOS QUE EXIGIDO PELA LEI APLICÁVEL OU ACORDADO POR
 * ESCRITO, QUALQUER TITULAR DE DIREITOS AUTORAIS OU QUALQUER OUTRA PARTE QUE
 * POSSA MODIFICAR E/OU REDISTRIBUIR O PROGRAMA, CONFORME PERMITIDO ACIMA, SERÁ
 * RESPONSÁVEL PARA COM VOCÊ POR DANOS, INCLUINDO ENTRE OUTROS, QUAISQUER DANOS
 * GERAIS, ESPECIAIS, FORTUITOS OU EMERGENTES, ADVINDOS DO USO OU
 * IMPOSSIBILIDADE DE USO DO PROGRAMA (INCLUINDO, ENTRE OUTROS, PERDAS DE DADOS
 * OU DADOS SENDO GERADOS DE FORMA IMPRECISA, PERDAS SOFRIDAS POR VOCÊ OU
 * TERCEIROS OU A IMPOSSIBILIDADE DO PROGRAMA DE OPERAR COM QUAISQUER OUTROS
 * PROGRAMAS), MESMO QUE ESSE TITULAR, OU OUTRA PARTE, TENHA SIDO ALERTADA SOBRE
 * A POSSIBILIDADE DE OCORRÊNCIA DESSES DANOS.
 *
 */
package br.com.cabelini.daofactory.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.cabelini.daofactory.dao.EmailDAO;
import br.com.cabelini.daofactory.dao.exception.DAOException;
import br.com.cabelini.daofactory.dao.factory.DAOFactory;
import br.com.cabelini.daofactory.entity.BusinessException;
import br.com.cabelini.daofactory.entity.CrudBaseEntity;
import br.com.cabelini.daofactory.entity.CrudEntity;

/**
 * Classe de entidade que mapeia a tabela <code>tbcontact</code> no banco de
 * dados.<br><br>
 *
 * <b>Atributos:</b>
 * <ul>
 * <li><code>id</code> - ID chave primária</li>
 * <li><code>name</code> - Endereço eletrônico do email.</li>
 * <li><code>idStatus</code> - Status herdado de {@link Objeto}</li> 
 * </ul>
 *
 * @author Henrique Moreira - Pedro XVIII
 * @since JDK1.5 | JDK1.8 
 * @version 1.1 $ 2015-2021
 *
 */
@Entity
@Table(name = "tbemail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e"),
    @NamedQuery(name = "Email.findById", query = "SELECT e FROM Email e WHERE e.id = :id"),
    @NamedQuery(name = "Email.findByName", query = "SELECT e FROM Email e WHERE e.name = :name"),
    @NamedQuery(name = "Email.findByIdStatus", query = "SELECT e FROM Email e WHERE e.idStatus = :idStatus")})
public class Email extends Objeto implements CrudBaseEntity<Email, Long>, CrudEntity<Email, Long> {

	/**
     * Serial version UID desta classe.
     */
    @Transient
    private static final long serialVersionUID = 1L;
    
	/**
     * Contact ao qual este email tem dono.
     */
    @JoinColumn(name = "idContact", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Contact contact;

    /**
     * Construtor sem argumentos de <code>Email</code>.
     */
    public Email() {
        super();
    }

    /**
     * Construtor com argumentos que cria uma instância de <code>Email</code> a
     * partir do valor dado.
     *
     * @see java.lang.Long
     * @param oid ID fornecido para este objeto.
     */
    public Email(Long oid) {
        super(oid);
    }

    /**
     * <strong>Construtor com argumentos</strong> Instância um objeto a partir
     * de seu id e nome. Os valores para os demais atributos são preenchidos
     * automaticamente.
     *
     * @param oid Valor que define o id deste objeto.
     * @param aName Valor que define o nome deste objeto.
     */
    protected Email(Long oid, String aName) {
        super(oid, aName);
    }

    /**
     * <strong>Construtor com argumentos</strong> Instância um objeto a partir
     * de seu id e nome. Os valores para os demais atributos são preenchidos
     * automaticamente.
     *
     * @param oid Valor que define o id deste objeto.
     * @param aName Valor que define o nome deste objeto.
     * @param aStatus Indica se está ativo ou não no sistema.
     */
    protected Email(Long oid, String aName, String aStatus) {
        super(oid, aName, aStatus);
    }

    /**
     * <strong>Construtor com argumentos</strong> Instância um objeto a partir
     * de seu id e nome. Os valores para os demais atributos são preenchidos
     * automaticamente.
     *
     * @param oid Valor que define o id deste objeto.
     * @param aName Valor que define o nome deste objeto.
     * @param aStatus Indica se está ativo ou não no sistema.
     * @param aContact Indica o contato do email.
     */
    protected Email(Long oid, String aName, String aStatus, Contact aContact) {
        this(oid, aName, aStatus);
        this.setContact(aContact);
    }

    /**
     * Retorna o contato que está vinculado ao grupo.
     *
     * @return O contato deste grupo.
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     * Altera o contato deste grupo.
     *
     * @param value Novo valor para o contato.
     */
    public void setContact(Contact value) {
        this.contact = value;
    }

    @Override
    public void save() throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            EmailDAO dao = factory.getDao(EmailDAO.class);

            dao.save(this);

            factory.txCommit();
        } catch (DAOException e) {
            factory.txRollback();
            throw new BusinessException(e);
        } finally {
            factory.shutdown();
        }
    }

    @Override
    public void save(Email entity) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            EmailDAO dao = factory.getDao(EmailDAO.class);

            if (this.equals(entity)) {
                dao.save(entity);
            }

            factory.txCommit();
        } catch (DAOException e) {
            factory.txRollback();
            throw new BusinessException(e);
        } finally {
            factory.shutdown();
        }
    }

    @Override
    public void update(Email entity) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            EmailDAO dao = factory.getDao(EmailDAO.class);

            if (this.equals(entity)) {
                dao.save(entity);
            }

            factory.txCommit();
        } catch (DAOException e) {
            factory.txRollback();
            throw new BusinessException(e);
        } finally {
            factory.shutdown();
        }
    }

    @Override
    public void remove(Long id) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            EmailDAO dao = factory.getDao(EmailDAO.class);

            dao.remove(id);

            factory.txCommit();
        } catch (DAOException e) {
            factory.txRollback();
            throw new BusinessException(e);
        } finally {
            factory.shutdown();
        }
    }

    @Override
    public void remove() throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            EmailDAO dao = factory.getDao(EmailDAO.class);

            dao.remove(this.getId());

            factory.txCommit();
        } catch (DAOException e) {
            factory.txRollback();
            throw new BusinessException(e);
        } finally {
            factory.shutdown();
        }
    }

    @Override
    public void remove(List<Email> list) throws BusinessException {
        for (Email obj : list) {
            this.remove(obj.getId());
        }
    }

    @Override
    public List<Email> list(int limite) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        EmailDAO dao = factory.getDao(EmailDAO.class);

        return dao.find();
    }

    @Override
    public Email find(Long key) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        EmailDAO dao = factory.getDao(EmailDAO.class);

        return dao.find(key);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        return !((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id)));
    }
}
