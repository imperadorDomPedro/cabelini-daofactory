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
package br.com.cabelini.daofactory.dao;

import br.com.cabelini.daofactory.dao.exception.DAOException;
import java.util.List;

/**
 * Interface para facilitar a implementação dos métodos CRUD nos DAOs. Ao herdar
 * os métodos desta interface, os DAOs das entidade têm quatro métodos a menos
 * para declarar.
 *
 * @param <T> A classe da entidade a ser mapeada pelo DAO.
 * @param <K> A classe do identificador da entidade a ser mapeada pelo DAO.
 *
 * @author AC de Souza
 * @since JDK1.5
 * @version 1.2 $ 2007-2015
 */
public interface CrudDAO<T, K> {

    /**
     * Persistir uma entidade nova ou edita os dados de uma entidade
     * persistente, previamente criada, do repositório:
     *
     * <ul>
     * <li>A decisão é tomada, se cria ou edita, baseado no preenchimento do
     * identificador do objeto, caso esteja preenchido é feito uma edição, caso
     * não esteja, é criado um novo;
     * </li>
     * </ul>
     *
     * @param entidade Tabela mapeada a ser persistida no banco de dados usado.
     *
     * @throws DAOException Qualquer erro que ocorrer na gravação dos dados.
     */
    public void save(T entidade) throws DAOException;

    /**
     * Remover uma entidade persistente, previamente criada, do repositório.
     *
     * @param oid Identificador da entidade.
     */
    public void remove(K oid);
    
    /**
     * Remover uma lista de entidades persistente, previamente criada, do 
     * repositório.
     *
     * @param list Lista de entidades a ser removida.
     */
    public void remove(List<T> list) throws DAOException;
    
    /**
     * Recuperar todas as entidades persistentes no repositório.
     *
     * @return Todas as entidades persistentes no repositório.
     */
    public List<T> find();

    /**
     * Recuperar a entidade persistente no repositório que está associada ao ID
     * passado como par&acirc;metro.
     *
     * @param key Identificador associado a entidade persistente no repositório
     * que se deseja recuperar.
     *
     * @return Entidade persistente no repositório que está associada ao ID
     * passado como par&acirc;metro.
     */
    public T find(K key);
}
