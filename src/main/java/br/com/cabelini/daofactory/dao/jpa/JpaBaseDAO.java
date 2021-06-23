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
package br.com.cabelini.daofactory.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.Level;

import br.com.cabelini.daofactory.constants.DAOConstants;
import br.com.cabelini.daofactory.dao.CrudDAO;
import br.com.cabelini.daofactory.dao.exception.DAOException;
import br.com.cabelini.daofactory.entity.CrudBaseEntity;

/**
 * Implementação base para um DAO que disponibiliza as operações de persistência
 * em bancos de dados relacionais, usando a JPA; Graças as implementações feitas
 * nesta classe os DAOs das entidades só precisarão implementar métodos de busca
 * com filtros específicos.
 *
 * @author AC de Souza
 * @param <T> Entidade que estende as operações base de uma entidade.
 * @param <K> Objeto que mapeia a chave primária da entidade.
 *
 * @since JDK1.5
 * @version 1.2 $ 2007-2015
 *
 */
public abstract class JpaBaseDAO<T extends CrudBaseEntity<T, K>, K> implements
        CrudDAO<T, K> {

    /**
     * JPA - Java Persistence API (Usado para executar as operações com o banco
     * de dados).
     */
    protected final EntityManager manager;

    /**
     * Este construtor serve para receber o {@link EntityManager} criado pela
     * fábrica concreta de DAOs.
     *
     * @param manager {@link EntityManager} criado pela fábrica concreta para o
     * repositório, isto é, a implementação da fábrica de DAOs usando a JPA.
     */
    public JpaBaseDAO(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Usado nos métodos de busca para a impossibilidade de recuperar a classe
     * de um parâmetro Generics.
     *
     * @return A classe Class da entidade que o DAO mapeia.
     */
    public abstract Class<T> getEntityClass();

    /**
     * Utiliza o método {@link CrudBaseEntity#getId()} para verificar se é para
     * criar uma entidade nova ou atualizar uma existente.
     *
     * Antes eu usava, somente, o <code>merge</code>. Que realizava um
     * <code>select</code> para colocar a entidade no estado
     * <code>"managed"</code>, no {@link EntityManager}. Com a abordagem da
     * verificação se o ID está nulo eu elimino esse <code>select</code> nos
     * casos de inclusão. E é por isso que eu faço as entidades implementarem a
     * interface {@link CrudBaseEntity}.
     *
     * @param obj Objeto a ser inserido no banco de dados.
     *
     * @see br.com.cabelini.dao.CrudDAO#save(java.lang.Object)
     * @throws DAOException Para traduzir qualquer {@link RuntimeException} que
     * tenha ocorrido durante a operação.
     */
    @Override
    public void save(T obj) throws DAOException {
        try {
            if (obj.getId() == null) {
                manager.persist(obj);
                DAOConstants.LOGGER.log(Level.INFO, "JpaBaseDAO:persist(obj)");
            } else {
                manager.merge(obj);
                DAOConstants.LOGGER.log(Level.INFO, "JpaBaseDAO:merge(obj)");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getCause());
            throw new DAOException(e);
        }
    }

    /**
     * Implementação para remoção de uma entidade mapeada pelo seu ID.
     *
     * @param id Chave primária deve ser fornecida a este método.
     *
     * @see com.cabelini.daofactory.dao.CrudDAO#remove(java.lang.Object)
     * @throws DAOException Para traduzir qualquer {@link RuntimeException} que
     * tenha ocorrido durante a operação.
     */
    @Override
    public void remove(K id) throws DAOException {
        try {
            manager.remove(getReference(id));
            DAOConstants.LOGGER.log(Level.INFO, "JpaBaseDAO:remove(id)");
        } catch (RuntimeException e) {
            throw new DAOException(e);
        }
    }
    
    @Override
    public void remove(List<T> list) throws DAOException {
        for (T obj : list) {
            this.remove(obj.getId());
        }
    }
    
    /**
     * Usado pelo método {@link #remove(K)}. Serve para recuperar uma referencia
     * a entidade, associada ao ID informado, e colocá-la com no estado de
     * <code>"managed"</code>, no {@link EntityManager}. A vantagem sobre o
     * <code>find</code> é que o método
     * {@link EntityManager} {@link #getReference(Object)} não percorre as
     * associações.
     *
     * @param id Identificador da entidade, a qual se deseja uma instância no
     * {@link EntityManager}.
     * @return A entidade associada ao ID informado.
     */
    protected T getReference(K id) {
        return manager.getReference(getEntityClass(), id);
    }

    /**
     * Retorna uma lista de objetos do tipo T. Faz uso do método
     * {@link EntityManager}{@link #find()}.
     *
     * @see br.com.commons.model.dao.CrudDAO#find()
     * @throws DAOException Para traduzir qualquer {@link RuntimeException} que
     * tenha ocorrido durante a operação.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> find() throws DAOException {
        try {
            return manager.createQuery(
                    "SELECT e FROM " + getEntityClass().getName().substring(18)
                    + " e").getResultList();
        } catch (RuntimeException e) {
            DAOException t = new DAOException(e.getCause() + " persistentes.\n"
                    + "Erro ao tentar listar todos as entidades "
                    + getEntityClass().getName().substring(18));
            DAOConstants.LOGGER.info("O repositório deve ser informado!", t);
            throw t;
        }
    }

    /**
     * Retorna um objeto do tipo T a partir do seu ID.
     *
     * @param id Identificador da entidade, a qual se deseja uma instância no
     * {@link EntityManager}. Aqui se faz uma chamada ao método {@link EntityManager}
     *            {@link #find(java.lang.Object)}.
     * @see br.com.commons.model.dao.CrudDAO#find(K)
     * @throws DAOException Para traduzir qualquer {@link RuntimeException} que
     * tenha ocorrido durante a operação.
     */
    @Override
    public T find(K id) throws DAOException {
        try {
            return manager.find(getEntityClass(), id);
        } catch (RuntimeException e) {
            throw new DAOException("Erro ao tentar recuperar a entidade "
                    + getEntityClass().getName() + " com o identificador: "
                    + id + ".", e);
        }
    }
}
