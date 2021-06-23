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

import br.com.cabelini.daofactory.dao.GroupDAO;
import br.com.cabelini.daofactory.dao.exception.DAOException;
import br.com.cabelini.daofactory.dao.factory.DAOFactory;
import br.com.cabelini.daofactory.entity.BusinessException;
import br.com.cabelini.daofactory.entity.CrudBaseEntity;
import br.com.cabelini.daofactory.entity.CrudEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe de entidade que mapeia a tabela <code>tbcontact</code> no banco de
 * dados.<br><br>
 *
 * <b>Atributos:</b>
 * <ul>
 * <li><code>id</code> - Chave primária da tabela mapeada</li>
 * <li><code>name</code> - Nome ou descrição do grupo</li>
 * <li><code>idStatus</code> - Status herdado de {@link Objeto}</li>
 * <li><code>contact</code> - Chave estrangeira que relaciona
 * {@link Contact}</li>
 * </ul>
 *
 * @author Henrique Moreira - Pedro XVIII
 * @since JDK1.5 | JDK1.8 
 * @version 1.1 $ 2015-2021
 *
 */
@Entity
@Table(name = "tbgroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group.findAll", query = "SELECT g FROM Group g"),
    @NamedQuery(name = "Group.findById", query = "SELECT g FROM Group g WHERE g.id = :id"),
    @NamedQuery(name = "Group.findByName", query = "SELECT g FROM Group g WHERE g.name = :name"),
    @NamedQuery(name = "Group.findByIdStatus", query = "SELECT g FROM Group g WHERE g.idStatus = :idStatus")})
public class Group extends Objeto implements CrudBaseEntity<Group, Long>, CrudEntity<Group, Long> {

	/**
     * Serial version UID desta classe.
     */
    @Transient
    private static final long serialVersionUID = 1L;
	
	/**
     * Contact ao qual este grupo tem como membro.
     */
    @JoinColumn(name = "idContact", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Contact contact;

    /**
     * Construtor sem argumentos de <code>Group</code>.
     */
    public Group() {
        super();
    }

    /**
     * Construtor com argumentos que cria uma instância de <code>Group</code> a
     * partir do valor dado.
     *
     * @see java.lang.Long
     * @param oid ID fornecido para este objeto.
     */
    public Group(Long oid) {
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
    public Group(Long oid, String aName) {
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
    public Group(Long oid, String aName, String aStatus) {
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
     * @param aContact Indica o contato do grupo.
     */
    public Group(Long oid, String aName, String aStatus, Contact aContact) {
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
            GroupDAO dao = factory.getDao(GroupDAO.class);

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
    public void save(Group entity) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            GroupDAO dao = factory.getDao(GroupDAO.class);

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
    public void update(Group entity) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        try {
            factory.txBegin();
            GroupDAO dao = factory.getDao(GroupDAO.class);

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
            GroupDAO dao = factory.getDao(GroupDAO.class);

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
            GroupDAO dao = factory.getDao(GroupDAO.class);

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
    public void remove(List<Group> list) throws BusinessException {
        for (Group obj : list) {
            this.remove(obj.getId());
        }
    }

    @Override
    public List<Group> list(int limite) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        GroupDAO dao = factory.getDao(GroupDAO.class);

        return dao.find();
    }

    @Override
    public Group find(Long key) throws BusinessException {
        DAOFactory factory = DAOFactory.getInstance();
        GroupDAO dao = factory.getDao(GroupDAO.class);

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
        if (!(object instanceof Group)) {
            return false;
        }
        Group other = (Group) object;
        return !((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id)));
    }  
}
