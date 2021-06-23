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
package br.com.cabelini.daofactory.entity;

import java.util.List;

/**
 * Interface que define os métodos de CRUD (C - Create, R - Read, U - Update, D
 * - Delete) para os pojos.
 *
 * <b>Pojos</b> são também conhecidos como javabeans, isto é, são as classes que
 * possuem um construtor sem argumentos e métodos <code>get</code> e
 * <code>set</code> e são normalmente usados para representar as classes de
 * entidade presentes no sistema.
 *
 * @param <T> Classe de entidade coringa. Este parâmetro define a classe de
 * entidade. Essa classe estende a classe genérica
 * <code>{@link br.com.cabelini.daofactory.model.Objeto}</code>.
 * @param <K> Define a classe usada para armazenar o ID. Possíveis classes:
 * <code>Integer</code>, <code>Long</code>, <code>String</code> e etc.
 *
 * @author Henrique Moreira - Pedro XVIII
 * @since JDK1.5
 * @version 1.2 $ 2011-2015
 */
public interface CrudEntity<T, K> {

    /**
     * Inclui uma entidade no banco de dados.
     *
     * @see
     * br.com.cabelini.daofactory.model.Objeto#save(br.com.cabelini.daofactory.model.Objeto)
     * @param entity Entidade (mapeada por uma tabela) a ser cadastrada.
     * @throws BusinessException para violação de uma regra de negócio que
     * proíba a inclusão ou alteração da entidade com os dados atuais.
     */
    public void save(T entity) throws BusinessException;
    
    /**
     * Atualiza uma entidade no banco de dados.
     *
     * @param entity entidade (mapeada por uma tabela) a ser atualizada.
     * @throws BusinessException para violação de uma regra de negócio que
     * proíba a inclusão ou alteração da entidade com os dados atuais.
     */
    public void update(T entity) throws BusinessException;

    /**
     * Remove uma entidade no banco de dados a partir de seu código (ID).
     *
     * @param key ID (primary key) da entidade a ser removida.
     * @throws BusinessException para violação de uma regra de negócio que
     * proíba a exclusão da entidade com os dados atuais.
     */
    public void remove(K key) throws BusinessException;

    /**
     * Remove a lista de entidades passadas ao método.
     *
     * @param list lista de entidades a ser removida.
     * @throws BusinessException para violação de uma regra de negócio que
     * proíba a exclusão da entidade com os dados atuais.
     */
    public void remove(List<T> list) throws BusinessException;

    /**
     * Busca uma entidade no banco de dados a partir de seu código (ID).
     *
     * @param key ID (primary key) da entidade a ser removida.
     * @return Retorna a entidade que possui o ID informado.
     * @throws BusinessException para violação de uma regra de negócio que
     * proíba recuperação da entidade com os dados atuais.
     */
    public T find(K key) throws BusinessException;

    /**
     * Recupera todas as entidades cadastradas no banco de dados.
     *
     * @param limit Restrição de quantidade de registros a ser recuperada.
     * @return Lista de <code>E</code> limitados por <code>limit</code>.
     * @throws BusinessException para violação de uma regra de negócio que
     * proíba a listagem da entidade com os dados atuais.
     */
    public List<T> list(int limit) throws BusinessException;

}
