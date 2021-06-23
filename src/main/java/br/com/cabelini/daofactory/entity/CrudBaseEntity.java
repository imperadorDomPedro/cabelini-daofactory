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

import java.io.Serializable;

/**
 * Interface das entidades com suporte as operações de persistência CRUD
 * genéricas:
 * <dl>
 * <dt>CREATE
 * <dd>Persistir uma entidade nova.
 * <dt>RETRIEVE
 * <dd>Recuperar uma entidade persistente, previamente criada, do repositório.
 * <dt>UPDATE
 * <dd>Editar os dados de uma entidade persistente, previamente criada, do
 * repositório.
 * <dt>DELETE
 * <dd>Remover uma entidade persistente, previamente criada, do
 * repositório.</dd>
 * </dl>
 *
 * Usada, principalmente, no {@link JpaBaseDAO} para disponibilizar um método
 * {@link #getId()}
 *
 * @param <T> A classe da entidade a ser mapeada pelo DAO.
 * @param <K> A classe do identificador da entidade a ser mapeada pelo DAO.
 *
 * @author AC de Souza
 * @since JDK1.5
 * @version 1.2 $ 2007-2015
 */
public interface CrudBaseEntity<T, K> extends Serializable {

    /**
     * Recuperar o identificador da entidade. A chave primária mapeada do banco
     * de dados.
     *
     * @return oid (object identifier) da entidade.
     */
    public K getId();
    
    /**
     * Gravar a entidade no repositório. Caso a entidade já exista, alterar os
     * dados gravado.
     *
     * @throws BusinessException Para violação de uma regra de negócio que
     * proíba a inclusão ou alteração da entidade com os dados atuais.
     */
    public void save() throws BusinessException;

    /**
     * Apagar a instância da entidade
     *
     * @throws BusinessException Para violação de uma regra de negócio que
     * proíba a exclusão da entidade.
     */
    public void remove() throws BusinessException;

}
