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
package br.com.cabelini.daofactory.dao.factory;

import br.com.cabelini.daofactory.constants.DAOConstants;
import br.com.cabelini.daofactory.dao.CrudDAO;
import br.com.cabelini.daofactory.dao.exception.DAOException;
import br.com.cabelini.daofactory.dao.jpa.JpaDAOFactory;
import java.lang.reflect.InvocationTargetException;

/**
 * Esta é a implementação da fábrica de DAOs usando a estratégia de
 * AbstractFactory apresentada no site da Oracle em:
 * <a href="http://www.oracle.com/technetwork/java/dataaccessobject-138824.html">
 * Catálogo de padrão J2EE</a>. Porém, esta versão foi modificada para que não
 * seja necessário a criação de um método para cada DAO criado pela fábrica.
 *
 * @author AC de Souza
 * @since JDK1.5 | JDK1.8
 * @version 1.3 $ 2007-2021
 */
public abstract class DAOFactory {

    public static final int JPA = 1;
    private static final int DEFAULT_REPOSITORY = JPA;

    /**
     * Instanciar a implementação padrão <b>Singleton</b>, definida pela
     * constante DEFAULT_REPOSITORY, da {@link DAOFactory}.
     *
     * @return A implementação padrão da {@link DAOFactory}.
     */
    public static DAOFactory getInstance() {
        return getInstance(DEFAULT_REPOSITORY);
    }

    /**
     * Método que recebe um inteiro que define o tipo de persistência existente
     * e até o momento, essa biblioteca só possui implementado a JPA.
     *
     * Valores para <code>repository</code>:
     * <ul>
     * <li>Valor 1 (um) para JPA;</li>
     * <li>Valor 2 (dois) para JDBC (Não implementado ainda);</li>
     * </ul>
     *
     * @param repository
     * @return
     * @throws DAOException
     */
    public static DAOFactory getInstance(int repository) throws DAOException {
        DAOConstants.LOGGER.info("Implementação para o repositório: " + repository);
        
        switch (repository) {
            case JPA:
                DAOConstants.LOGGER.info("Implementação de JPA selecionada.");
                return new JpaDAOFactory();
            default:
                DAOException t = new DAOException("Sem implementação!");
                DAOConstants.LOGGER.info("O repositório deve ser informado!", t);
                throw t;
        }
    }

    /**
     * Iniciar uma transação.
     *
     * @throws DAOException Problemas de comunicação com o repositório de dados.
     */
    public abstract void txBegin() throws DAOException;

    /**
     * Confirmar as alterações feitas na transação atual.
     *
     * @throws DAOException Problemas de comunicação com o repositório de dados.
     */
    public abstract void txCommit() throws DAOException;

    /**
     * Desfazer as alterações feitas na transação atual.
     *
     * @throws DAOException Problemas de comunicação com o repositório de dados.
     */
    public abstract void txRollback() throws DAOException;

    /**
     * Terminar a transação liberando os recursos necessários para as operações.
     *
     * @throws DAOException Problemas de comunicação com o repositório de dados.
     */
    public abstract void shutdown() throws DAOException;

    /**
     * Informar o prefixo usado na implementação do repositório; será usado para
     * definir o pacote e o nome da classe que implementa a interface DAO.
     *
     * @return Prefixo da implementação
     */
    protected abstract String getPrefixoImpl();

    /**
     * Informar a lista de classes usadas no construtor dos DAOs.
     *
     * @return Tipos de parâmetros do construtor
     */
    @SuppressWarnings("rawtypes")
    protected abstract Class[] getRepositoryTypes();

    /**
     * Informar os parâmetros a serem passados no construtor dos DAOs.
     *
     * @return Parâmetros do construtor
     */
    protected abstract Object[] getRepositoryArgs();

    /**
     * Aqui é onde se encontra a mágica da implementação do padrão DAO, onde
     * através do recurso de Generics, esse método irá retornar o DAO específico
     * para cada uma das classes; segundo AC de Souza, o desafio era fazer isso
     * sem perder a checagem feita pelo compilador se a classe DAO que eu estou
     * retornando implementa a interface DAO que eu estou querendo usar: nesse
     * intuito ele começou a definir algumas convenções:
     *
     * <ul>
     * <li>O nome da interface DAO será o nome da entidade com o sufixo DAO. Por
     * exemplo, o DAO para a entidade <code>Cliente</code> será
     * <code>ClienteDAO</code>;</li>
     * <li>O pacote, onde ficam as interfaces e a fábrica abstrata será assim:
     * <code>pacote.do.sistema.dao;</code></li>
     * <li>O nome da implementação da interface DAO será o nome da interface DAO
     * com o prefixo do repositório. Por exemplo, a implementação usando JPA
     * será: <code>JpaClienteDAO;</code></li>
     * <li>O pacote, onde ficam a implementação e a fábrica concreta, será um
     * sub-pacote, do pacote onde estão as interfaces e a fábrica abstrata, como
     * em: <code>pacote.do.sistema.dao.jpa;</code></li>
     * </ul>
     * Essas convenções me permitiram modificar a fábrica abstrata para retornar
     * a implementação de uma interface DAO a partir da interface DAO.
     *
     * Mas ainda preciso saber o prefixo das implementações. Para resolver isso
     * criei um método, na fábrica abstrata, que retorna uma
     * <code>String</code>. Esta <code>String</code> é o prefixo da
     * implementação, que a fábrica concreta, irá informar.
     *
     * Na fábrica abstrata, isto é, a classe <code>DAOFactory</code>:
     *
     * <code>protected abstract String getPrefixoImpl();</code>
     *
     * E a implementação na fábrica concreta usando a JPA, isto
     * é,<code>JpaDAOFactory</code>:
     *
     * <code>@Override
     * protected String getPrefixoImpl() {
     *    return "jpa";
     * }
     * </code>
     *
     * O autor tem uma classe base para cada um dos DAOs, que implementam as
     * operações básicas de inclusão, alteração, exclusão e busca da entidade, e
     * tem um construtor que recebe o <code>EntityManager</code> a ser usado
     * nestas operações. Por conta deste construtor eu preciso dos métodos
     * <code>getRepositoryTypes()</code> e o <code>getRepositoryArgs()</code>
     * que irão retornar o objeto responsável pelo acesso ao banco de dados
     * (<code>EntityManager</code> no caso do JPA, <code>Session</code> no caso
     * de Hibernate e <code>Connection</code> no caso de JDBC “cru”).
     *
     * Com essas alterações simples eu posso recuperar a implementação do DAO
     * sem precisar criar um método para cada DAO em cada implementação da
     * fábrica abstrata.
     *
     * E para usar a nova implementação, o código fica assim:
     * <code>DAOFactory factory = DAOFactory.getInstance();</code>
     * <code>EntidadeDAO dao = factory.getDao(EntidadeDAO.class);</code>
     *
     * @param <T> Entidade que define qual o tipo de classe será persistida.
     * @param daoInterface Classe que define a implementação do padrão de
     * projeto DAO.
     * @return Retorna o DAO especifico a partir da interface especificada em
     * <code>daoInterface</code>.
     * @throws DAOException
     */
    @SuppressWarnings("unchecked")
    public <T extends CrudDAO<?, ?>> T getDao(Class<T> daoInterface) throws DAOException {
        T dao = null;

        String daoPackage = daoInterface.getPackage().getName() + "." + getPrefixoImpl();
        String daoInterfaceName = daoInterface.getSimpleName();
        String daoClassSimpleName = getPrefixoImpl().substring(0, 1).toUpperCase().
                concat(getPrefixoImpl().substring(1)) + daoInterfaceName;
        String daoClassName = daoPackage + "." + daoClassSimpleName;
        DAOConstants.LOGGER.debug("Implementação solicitada para o DAO: " + daoClassName);

        try {
            Class<?> daoClass = Class.forName(daoClassName);
            dao = (T) daoClass.getConstructor(getRepositoryTypes()).newInstance(getRepositoryArgs());
        } catch (IllegalArgumentException | SecurityException | 
                 InstantiationException | IllegalAccessException | 
                 InvocationTargetException | NoSuchMethodException | 
                 ClassNotFoundException e) {
            DAOException t = new DAOException(e);
            DAOConstants.LOGGER.info("Tipo de classe não encontrada!", t);
            throw t;
        }

        return dao;
    }

}
