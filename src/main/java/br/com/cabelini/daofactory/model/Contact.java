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

import br.com.cabelini.daofactory.dao.ContactDAO;
import br.com.cabelini.daofactory.dao.exception.DAOException;
import br.com.cabelini.daofactory.dao.factory.DAOFactory;
import br.com.cabelini.daofactory.entity.BusinessException;
import br.com.cabelini.daofactory.entity.CrudBaseEntity;
import br.com.cabelini.daofactory.entity.CrudEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Classe de entidade que mapeia a tabela <code>tbcontact</code> no banco de
 * dados.<br><br>
 *
 * <b>Atributos:</b>
 * <ul>
 * <li><code>id</code> - Primeira coluna da chave primária</li>
 * <li><code>name</code> - Segunda coluna da chave primária</li>
 * <li><code>idStatus</code> - Status herdado de {@link Objeto}</li>
 * <li><code>lastname</code> - Terceira coluna da chave primária</li>
 * <li><code>nickname</code> - Apelido para este contato</li>
 * <li><code>relationship</code> - Estado civil deste contato</li>
 * <li><code>website</code> - Site desse contato, se o possuir</li>
 * <li><code>organization</code> - Nome da empresa que este prestra serviço</li>
 * </ul>
 *
 * @author Henrique Moreira - Pedro XVIII
 * @since JDK1.5 | JDK1.8 
 * @version 1.1 $ 2015-2021
 *
 */
@Entity
@Table(name = "tbcontact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
    @NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name"),
    @NamedQuery(name = "Contact.findByLastname", query = "SELECT c FROM Contact c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Contact.findByNickname", query = "SELECT c FROM Contact c WHERE c.nickname = :nickname"),
    @NamedQuery(name = "Contact.findByRelationship", query = "SELECT c FROM Contact c WHERE c.relationship = :relationship"),
    @NamedQuery(name = "Contact.findByWebsite", query = "SELECT c FROM Contact c WHERE c.website = :website"),
    @NamedQuery(name = "Contact.findByOrganization", query = "SELECT c FROM Contact c WHERE c.organization = :organization")})
public class Contact extends Objeto implements CrudBaseEntity<Contact, Long>, CrudEntity<Contact, Long> {

	/**
     * Serial version UID desta classe.
     */
    @Transient
    private static final long serialVersionUID = 1L;

	/**
     * Um contato tem um ou mais telefones.
     */
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Phone> phoneList = new ArrayList<>();

    /**
     * Um contato tem um ou mais endereços de email.
     */
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Email> emailList = new ArrayList<>();

    /**
     * Um contato está em um ou mais grupos.
     */
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Group> groupList = new ArrayList<>();

    /**
     * Último nome desse contato.
     */
    @Column(name = "lastname", nullable = true, insertable = true,
            updatable = true)
    private String lastname;

    /**
     * Apelido desse contato.
     */
    @Column(name = "nickname", nullable = true, insertable = true,
            updatable = true)
    private String nickname;

    /**
     * Estado civil desse contato.
     */
    @Column(name = "relationship", nullable = true, insertable = true,
            updatable = true)
    private String relationship;

    /**
     * Site (sítio) desse contato, caso possua.
     */
    @Column(name = "website", nullable = true, insertable = true,
            updatable = true)
    private String website;

    /**
     * Organização onde esse contato tem vínculo empregatício.
     */
    @Column(name = "organization", nullable = true, insertable = true,
            updatable = true)
    private String organization;

    /**
     * Construtor sem argumentos dessa classe.
     */
    public Contact() {
        super();
    }

    /**
     * Construtor que inicializa <code>Contato</code> pela chave primária
     * definida.
     *
     * @param oid Chave primária para este objeto.
     */
    public Contact(Long oid) {
        super(oid);
    }

    /**
     * Construtor que define <code>Contato</code> com seu ID, nome e sobrenome.
     *
     * @param oid Coluna ID que faz parte da chave primária.
     * @param aName Nome deste objeto.
     * @param aIdStatus Status deste objeto.
     */
    public Contact(Long oid, String aName, String aIdStatus) {
        super(oid, aName, aIdStatus);
    }

    /**
     * Construtor que define <code>Contato</code> com seu ID, nome e sobrenome.
     *
     * @param id Coluna ID que faz parte da chave primária.
     * @param aName Nome deste objeto.
     * @param aIdStatus Status deste objeto.
     */
    public Contact(Long id, String aName, String aIdStatus, String aLastname) {
        super(id, aName, aIdStatus);
        this.setLastName(aLastname);
    }

    /**
     * Construtor que define <code>Contato</code> com seu ID, nome, sobrenome e
     * apelido (nickname).
     *
     * @param oid Coluna ID que faz parte da chave primária.
     * @param aName Nome deste objeto.
     * @param aIdStatus Status deste objeto.
     * @param aLastName Último nome do contato.
     * @param aNickname Apelido para este contato.
     */
    public Contact(Long oid, String aName, String aIdStatus, String aLastName,
            String aNickname) {
        this(oid, aName, aIdStatus, aLastName);
        this.setNickname(aNickname);
    }

    /**
     * Construtor que define <code>Contato</code> com seu ID, nome, sobrenome,
     * apelido (nickname) e estado civil.
     *
     * @param oid Coluna ID que faz parte da chave primária.
     * @param aName Nome deste objeto.
     * @param aIdStatus Status deste objeto.
     * @param aLastName Último nome do contato.
     * @param aNickname Apelido para este contato.
     * @param aRelationship Estado civil (relacionamento) deste contato.
     */
    public Contact(Long oid, String aName, String aIdStatus, String aLastName,
            String aNickname, String aRelationship) {
        this(oid, aName, aIdStatus, aLastName, aNickname);
        this.setRelationship(aRelationship);
    }

    /**
     * Construtor que define <code>Contato</code> com seu ID, nome, sobrenome,
     * apelido (nickname) e estado civil.
     *
     * @param oid Coluna ID que faz parte da chave primária.
     * @param aName Nome deste objeto.
     * @param aIdStatus Status deste objeto.
     * @param aLastName Último nome do contato.
     * @param aNickname Apelido para este contato.
     * @param aRelationship Estado civil (relacionamento) deste contato.
     * @param aWebsite Website que o contato possa ter.
     */
    public Contact(Long oid, String aName, String aIdStatus, String aLastName,
            String aNickname, String aRelationship, String aWebsite) {
        this(oid, aName, aIdStatus, aLastName, aNickname, aRelationship);
        this.setWebsite(aWebsite);
    }

    /**
     * Construtor que define <code>Contato</code> com seu ID, nome, sobrenome,
     * apelido (nickname) e estado civil.
     *
     * @param oid Coluna ID que faz parte da chave primária.
     * @param aName Nome deste objeto.
     * @param aIdStatus Status deste objeto.
     * @param aLastName Último nome do contato.
     * @param aNickname Apelido para este contato.
     * @param aRelationship Estado civil (relacionamento) deste contato.
     * @param aWebsite Website que o contato possa ter.
     * @param aOrganization Nome da empresa que este contato presta serviço.
     */
    public Contact(Long oid, String aName, String aIdStatus, String aLastName,
            String aNickname, String aRelationship, String aWebsite,
            String aOrganization) {
        this(oid, aName, aIdStatus, aLastName, aNickname, aRelationship, aWebsite);
        this.setOrganization(aOrganization);
    }

    /**
     * Retorna a lista de emails deste contato.
     *
     * @return A lista de emails cadastrada deste contato.
     */
    @XmlTransient
    public List<Email> getEmailsList() {
        return this.emailList;
    }

    /**
     * Altera a lista de emails deste contato.
     *
     * @param value Nova lista de emails deste contato.
     */
    public void setEmailsList(List<Email> value) {
        this.emailList = value;
    }

    /**
     * Retorna o objeto do tipo <code>Email</code> deste contato.
     *
     * @param index Índice do email na lista de emails do contato.
     *
     * @return O objeto que mapeia o relacionamento entre contato e email (1
     * para n)
     */
    public Email getEmail(int index) {
        return this.emailList.get(index);
    }

    /**
     * Altera o objeto do tipo <code>Email</code> deste contato.
     * 
     * @param index Índice do email na lista de emails do contato.
     * @param value Novo objeto <code>Email</code> para este objeto.
     */
    public void setEmail(int index, Email value) {
        this.emailList.add(index, value);
    }
    
    public void addEmail(Email value) {
        value.setContact(this);
        this.emailList.add(value);
    }

    /**
     * Retorna o ID do objeto <code>Email</code>.
     *
     * @param index Índice do email na lista de emails do contato.
     *
     * @return O código de identificação do email na aplicação.
     */
    public Long getIdEmail(int index) {
        return this.emailList.get(index).getId();
    }

    /**
     * Altera o ID do objeto do tipo <code>Email</code> deste contato.
     *
     * @param index Índice do email na lista de emails do contato.
     *
     * @param value Novo objeto <code>Email</code> para este objeto.
     */
    public void setIdEmail(int index, Long value) {
        this.emailList.get(index).setId(value);
    }

    /**
     * Retorna o endereço de email de <code>Contact</code>.
     *
     * @param index Índice do email na lista de emails do contato.
     *
     * @return O endereço de email (address) do contato.
     */
    public String getAddressEmail(int index) {
        return this.emailList.get(index).getName();
    }

    /**
     * Altera o endereço eletrônico do objeto <code>Email</code>.
     *
     * @param index Índice do email na lista de emails do contato.
     * @param value Novo valor para o endereço eletrônico.
     */
    public void setAddressEmail(int index, String value) {
        this.emailList.get(index).setName(value);
    }

    /**
     * Retorna o status do email de <code>Contact</code>.
     *
     * @param index Índice do email na lista de emails do contato.
     *
     * @return O status do email do contato.
     */
    public String getStatusEmail(int index) {
        return this.emailList.get(index).getState();
    }

    /**
     * Altera o status do objeto <code>Email</code>.
     *
     * @param index Índice do email na lista de emails do contato.
     * @param value Novo valor para o status do email.
     */
    public void setStatusEmail(int index, String value) {
        this.emailList.get(index).setState(value);
    }

    /**
     * Remove um email da lista de emails do contato.
     *
     * @param obj Objeto a ser removido da lista.
     *
     * @return <code>TRUE</code> se removeu e <code>FALSE</code> caso contrário.
     */
    public boolean removeEmail(Email obj) {
        return this.emailList.remove(obj);
    }

    /**
     * Remove uma lista de emails do contato.
     *
     * @param list Lista a ser removida da lista de emails.
     *
     * @return <code>TRUE</code> se removeu e <code>FALSE</code> caso contrário.
     */
    public boolean removeAllEmails(List<Email> list) {
        return this.emailList.removeAll(list);
    }

    /**
     * Retorna a lista de grupos deste contato.
     *
     * @return A lista de grupos cadastrada deste contato.
     */
    @XmlTransient
    public List<Group> getGroupList() {
        return this.groupList;
    }

    /**
     * Altera a lista de grupos deste contato.
     *
     * @param list Nova lista de grupos.
     *
     */
    public void setGroupList(List<Group> list) {
        this.groupList = list;
    }

    /**
     * Retorna o ID do objeto <code>Group</code>.
     *
     * @param index Índice do grupo na lista de grupos do contato.
     *
     * @return O código de identificação do grupo na aplicação.
     */
    public Long getIdGroup(int index) {
        return this.groupList.get(index).getId();
    }

    /**
     * Altera o ID do objeto do tipo <code>Group</code> deste contato.
     *
     * @param index Índice do grupo na lista de grupos do contato.
     *
     * @param value Novo id para o <code>Group</code> deste objeto.
     */
    public void setIdGroup(int index, Long value) {
        this.groupList.get(index).setId(value);
    }

    /**
     * Retorna o nome do grupo do <code>Contact</code>.
     *
     * @param index Índice do grupo na lista de grupos do contato.
     *
     * @return O nome do grupo (descrição) do contato.
     */
    public String getNameGroup(int index) {
        return this.groupList.get(index).getName();
    }

    /**
     * Altera o nome do grupo do <code>Contact</code>.
     *
     * @param index Índice do grupo na lista de grupos do contato.
     * @param value Novo valor para o nome do grupo.
     */
    public void setNameGroup(int index, String value) {
        this.groupList.get(index).setName(value);
    }

    /**
     * Retorna o status do grupo do <code>Contact</code>.
     *
     * @param index Índice do grupo na lista de grupos do contato.
     *
     * @return O status do grupo do contato.
     */
    public String getStatusGroup(int index) {
        return this.groupList.get(index).getState();
    }

    /**
     * Altera o status do objeto <code>Group</code>.
     *
     * @param index Índice do grupo na lista de grupos do contato.
     * @param value Novo valor para o status do grupo.
     */
    public void setStatusGroup(int index, String value) {
        this.groupList.get(index).setState(value);
    }

    /**
     * Remove um grupo da lista de grupos do contato.
     *
     * @param obj Objeto a ser removido da lista.
     *
     * @return <code>TRUE</code> se removeu e <code>FALSE</code> caso contrário.
     */
    public boolean removeGroup(Group obj) {
        return this.groupList.remove(obj);
    }

    /**
     * Remove uma lista de grupos do contato.
     *
     * @param list Lista a ser removida da lista de grupos.
     *
     * @return <code>TRUE</code> se removeu e <code>FALSE</code> caso contrário.
     */
    public boolean removeAllGroups(List<Group> list) {
        return this.groupList.removeAll(list);
    }

    /**
     * Retorna a lista de telefones deste contato.
     *
     * @return A lista de telefones cadastrada deste contato.
     */
    @XmlTransient
    public List<Phone> getPhoneList() {
        return this.phoneList;
    }

    /**
     * Altera a lista de telefones deste contato.
     *
     * @param list Nova lista de telefones.
     *
     */
    public void setPhoneList(List<Phone> list) {
        this.phoneList = list;
    }

    /**
     * Retorna o ID do objeto <code>Phone</code>.
     *
     * @param index Índice do telefone na lista de telefones do contato.
     *
     * @return O código de identificação do telefone na aplicação.
     */
    public Long getIdPhone(int index) {
        return this.phoneList.get(index).getId();
    }

    /**
     * Altera o ID do objeto do tipo <code>Phone</code> deste contato.
     *
     * @param index Índice do telefone na lista de telefones do contato.
     *
     * @param value Novo id para o <code>Group</code> deste objeto.
     */
    public void setIdPhone(int index, Long value) {
        this.phoneList.get(index).setId(value);
    }

    /**
     * Retorna o nome do telefone do <code>Contact</code>.
     *
     * @param index Índice do telefone na lista de telefones do contato.
     *
     * @return O nome do telefone (descrição) do contato.
     */
    public String getNamePhone(int index) {
        return this.phoneList.get(index).getName();
    }

    /**
     * Altera o nome do grupo do <code>Contact</code>.
     *
     * @param index Índice do telefone na lista de telefone do contato.
     * @param value Novo valor para o nome do grupo.
     */
    public void setNamePhone(int index, String value) {
        this.phoneList.get(index).setName(value);
    }

    /**
     * Retorna o status do telefone do <code>Contact</code>.
     *
     * @param index Índice do telefone na lista de telefones do contato.
     *
     * @return O status do telefone do contato.
     */
    public String getStatusPhone(int index) {
        return this.phoneList.get(index).getState();
    }

    /**
     * Altera o status do objeto <code>Group</code>.
     *
     * @param index Índice do telefone na lista de telefones do contato.
     * @param value Novo valor para o status do telefone.
     */
    public void setStatusPhone(int index, String value) {
        this.phoneList.get(index).setState(value);
    }

    /**
     * Remove um telefone da lista de telefones do contato.
     *
     * @param obj Objeto a ser removido da lista.
     *
     * @return <code>TRUE</code> se removeu e <code>FALSE</code> caso contrário.
     */
    public boolean removePhone(Phone obj) {
        return this.phoneList.remove(obj);
    }

    /**
     * Remove uma lista de telefones do contato.
     *
     * @param list Lista a ser removida da lista de telefones.
     *
     * @return <code>TRUE</code> se removeu e <code>FALSE</code> caso contrário.
     */
    public boolean removeAllPhone(List<Phone> list) {
        return this.phoneList.removeAll(list);
    }

    /**
     * Retorna o último nome deste contato.
     *
     * @return Retorna o último nome (lastname) deste contato.
     */
    public String getLastName() {
        return this.lastname;
    }

    /**
     * Altera o último nome deste contato.
     *
     * @param value Novo sobrenome deste contato.
     */
    public void setLastName(String value) {
        String old = this.lastname;
        this.lastname = (value == null ? "Undefined" : value);
        pcs.firePropertyChange("lastname", old, this.lastname);
    }

    /**
     * Retorna o apelido deste contato.
     *
     * @return Retorna o nickname (apelido) deste contato.
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Altera o apelido deste contato.
     *
     * @param value Novo nickname (apelido) deste contato.
     */
    public void setNickname(String value) {
        String old = this.nickname;
        this.nickname = (value == null ? "Undefined" : value);
        pcs.firePropertyChange("nickname", old, this.nickname);
    }

    /**
     * Retorna o estado civil deste contato.
     *
     * @return Retorna o estado civil deste contato.
     */
    public String getRelationship() {
        return this.relationship;
    }

    /**
     * Altera o estado civil deste contato.
     *
     * @param value Novo estado civil deste contato.
     */
    public void setRelationship(String value) {
        String old = this.relationship;
        this.relationship = (value == null ? "Undefined" : value);
        pcs.firePropertyChange("relationship", old, this.relationship);
    }

    /**
     * Retorna o website deste contato.
     *
     * @return Retorna o website (sítio) deste contato.
     */
    public String getWebsite() {
        return this.website;
    }

    /**
     * Altera o website deste contato.
     *
     * @param value Novo website deste contato.
     */
    public void setWebsite(String value) {
        String old = this.website;
        this.website = (value == null ? "Undefined" : value);
        pcs.firePropertyChange("website", old, this.website);
    }

    /**
     * Retorna o nome da organização deste contato.
     *
     * @return Retorna o nome da organização deste contato.
     */
    public String getOrganization() {
        return this.organization;
    }

    /**
     * Altera o nome da organização deste contato.
     *
     * @param value Novo nome da organização deste contato.
     */
    public void setOrganization(String value) {
        String old = this.organization;
        this.organization = (value == null ? "Undefined" : value);
        pcs.firePropertyChange("organization", old, this.organization);
    }

    @Override
    public void save() throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            ContactDAO dao = factory.getDao(ContactDAO.class);

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
    public void save(Contact entity) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            ContactDAO dao = factory.getDao(ContactDAO.class);

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
    public void update(Contact entity) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            ContactDAO dao = factory.getDao(ContactDAO.class);

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
            ContactDAO dao = factory.getDao(ContactDAO.class);

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
            ContactDAO dao = factory.getDao(ContactDAO.class);

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
    public void remove(List<Contact> list) throws BusinessException {
        for (Contact obj : list) {
            this.remove(obj.getId());
        }
    }

    @Override
    public List<Contact> list(int limite) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        ContactDAO dao = factory.getDao(ContactDAO.class);

        return dao.find();
    }

    @Override
    public Contact find(Long key) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        ContactDAO dao = factory.getDao(ContactDAO.class);

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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        return !((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id)));
    }
}
