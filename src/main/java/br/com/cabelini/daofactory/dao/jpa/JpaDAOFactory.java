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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.cabelini.daofactory.dao.factory.DAOFactory;

/**
 * Classe que estende DAOFactory que implementa seus métodos abstratos para
 * prover a funcionalidade de JPA com suporte a DAO.
 *
 * AC de Souza definiu algumas convenções:
 *
 * <ul>
 * <li>O nome da interface DAO será o nome da entidade com o sufixo DAO. Por
 * exemplo, o DAO para a entidade Cliente será ClienteDAO; O pacote, onde ficam
 * as interfaces e a fábrica abstrata será assim:
 * {@code pacote.do.sistema.dao};</li>
 * <li>O nome da implementação da interface DAO será o nome da interface DAO com
 * o prefixo do repositório. Por exemplo, a implementação usando JPA será:
 * {@code JpaClienteDAO};</li>
 * <li>O pacote, onde ficam a implementação e a fábrica concreta, será um
 * sub-pacote, do pacote onde estão as interfaces e a fábrica abstrata, como em:
 * {@code pacote.do.sistema.dao.jpa};</li>
 * </ul>
 * Essas convenções me permitiram modificar a fábrica abstrata para retornar a
 * implementação de uma interface DAO a partir da interface DAO.
 *
 * @author AC de Souza
 * @since JDK1.5
 * @version 1.2 $ 2007-2015
 */
public class JpaDAOFactory extends DAOFactory {

    /**
     * Gerenciador de Entidades. Abstrai a comunicação, administração e
     * complexidade do banco de dados.
     */
    private EntityManager manager = null;

    /**
     * Fábrica de EntityManager.
     */
    private static final EntityManagerFactory FACTORY = createEntityManagerFactory("cabelini-daofactory");

    /**
     * Gerencia as transações. Abstrai o controle sobre os diversos tipos de
     * transações.
     */
    private EntityTransaction tx;

    /**
     * Construtor de {@code JpaDAOFactory}. Associa ao EntityManager (cria)
     * conforme o banco de dados usado na aplicação.
     */
    public JpaDAOFactory() {
        if (manager == null || !manager.isOpen()) {
            manager = FACTORY.createEntityManager();
        }
    }

    /**
     * Método para criação de uma fábrica de EntityManagers. Recebe o nome para
     * o <code>entity</code>.
     *
     * @param name Descrição (nome) para o <code>entity</code>.
     *
     * @return retorna a fábrica de EntityManager.
     */
    public static EntityManagerFactory createEntityManagerFactory(String name) {
        if (name == null || name.equals("")) {
            name = "cabelini-daofactory";
        }

        return Persistence.createEntityManagerFactory(name);
    }

    /**
     * Iniciar uma transação na ação de persistência.
     */
    @Override
    public void txBegin() {
        if (tx == null) {
            tx = manager.getTransaction();
            tx.begin();
        }
    }

    /**
     * Finaliza uma transação na ação de persistência.
     */
    @Override
    public void txCommit() {
        if (tx != null && tx.isActive()) {
            tx.commit();
        }
    }

    /**
     * Desfaz uma transação na ação de persistência.
     */
    @Override
    public void txRollback() {
        if (tx != null) {
            tx.rollback();
        }
    }

    /**
     * Retorna o prefixo usado por essa classe (JPA). Se fosse outro tipo de
     * implementação, a classe teria sua própria implementação.
     *
     * @return O prefixo de implementação do pacote.
     */
    @Override
    protected String getPrefixoImpl() {
        return "jpa";
    }

    /**
     * Obtém os argumentos do repositório.
     *
     * @return Uma matriz com os argumentos.
     */
    @Override
    protected Object[] getRepositoryArgs() {
        return new Object[]{manager};
    }

    /**
     * Retorna os tipos presentes no repositório.
     *
     * @return O tipo de classe que gerencia o repositório.
     */
    @Override
    @SuppressWarnings("rawtypes")
    protected Class[] getRepositoryTypes() {
        return new Class[]{EntityManager.class};
    }

    /**
     * Encerra o gerenciador de persistência.
     */
    @Override
    public void shutdown() {
        manager.clear();
        manager.close();
    }
}
