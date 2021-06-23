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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import br.com.cabelini.daofactory.constants.DAOConstants;

/**
 * <strong>Classe Objeto</strong> Classe genérica e abstrata que reúne atributos
 * comuns a todos os objetos da aplicação. São eles:
 * <ul>
 * <li>id - Código de identificação deste objeto;</li>
 * <li>name - Nome ou descrição que caracterize o objeto e;</li>
 * <li>idStatus - Diz se este se encontra ativo ou inativo</li>
 * </ul>
 * <br>
 * Esta classe está implementada segundo o padrão de projeto <a
 * href="http://weblogs.java.net/blog/2006/05/31/unknown-javabean">JavaBeans.
 * </a>A partir deste POJO(Plain Old Java Object) ou classe de negócio, que
 * possui um construtor sem argumentos e métodos <b>getters</b> e <b>setters</b>
 * que manipulam seus atributos que podem ser considerados, neste caso, como
 * propriedades. Um POJO é também um que não possui dependência externa.
 *
 * Em relação à persistência dos dados, foi usada a biblioteca de classes JDBC.
 * Para explorar o recuso de binding, foi utilizado neste projeto as classes do
 * pacote <strong>java.beans.</strong>
 *
 * @author Henrique Moreira - Pedro XVIII
 * @since JDK1.5| JDK1.8 
 * @version 1.2 $ 2012-2015 | 2015-2021
 *
 */
@MappedSuperclass
public abstract class Objeto implements Serializable {

    /**
     * Serial version UID desta classe.
     */
    @Transient
    protected static final long serialVersionUID = 1L;
    
    /**
     * Implementação do padrão singleton.
     */
    @Transient
    private static Objeto instance = null;
    /**
     * Suporte ao beans binding (ligação) entre a GUI e um atributo de classe.
     */
    @Transient
    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Persiste o ID (primary key) presente na tabela.
     */
    @Id
    @Column(name = DAOConstants.CONSTANTS_ID, nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    protected Long id;

    /**
     * Persiste o nome presente na tabela.
     */
    @Column(name = DAOConstants.CONSTANTS_NAME, nullable = false, insertable = true, updatable = true)
    @Basic(optional = false)
    protected String name;

    /**
     * Persiste o status presente na tabela.
     */
    @Column(name = DAOConstants.CONSTANTS_STATUS, nullable = true, insertable = true, updatable = true)
    @Basic(optional = true)
    protected String idStatus;
    
    public static Objeto getInstance() {
        return (instance == null) ? new Objeto() {
			private static final long serialVersionUID = 1L;
		} : instance;
    }

    /**
     * Construtor sem argumentos de <code>Objeto</code>.
     */
    protected Objeto() {
        this.id = new Long(1);
        pcs.firePropertyChange(DAOConstants.CONSTANTS_ID, new Long(1), this.id);
        this.name = "Digite meu nome";
        pcs.firePropertyChange(DAOConstants.CONSTANTS_NAME, "Meu nome", this.name);
        this.idStatus = "Undefined";
        pcs.firePropertyChange(DAOConstants.CONSTANTS_STATUS, "Undefined", this.idStatus);
    }

    /**
     * Construtor com argumentos que cria uma instância de <code>Objeto</code> a
     * partir do valor dado.
     *
     * @see java.lang.Long
     * @param oid ID fornecido para este objeto.
     */
    protected Objeto(Long oid) {
        this.id = (oid == null ? new Long(1) : oid);
        pcs.firePropertyChange(DAOConstants.CONSTANTS_ID, oid, this.id);
    }

    /**
     * <strong>Construtor com argumentos</strong> Instância um objeto a partir
     * de seu id e nome. Os valores para os demais atributos são preenchidos
     * automaticamente.
     *
     * @param oid Valor que define o id deste objeto.
     * @param aName Valor que define o nome deste objeto.
     */
    protected Objeto(Long oid, String aName) {
        this(oid);
        this.name = (aName == null ? "Digite meu nome" : aName);
        pcs.firePropertyChange(DAOConstants.CONSTANTS_NAME, aName, this.name);
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
    protected Objeto(Long oid, String aName, String aStatus) {
        this(oid, aName);
        this.idStatus = (aName == null ? "Meu nome" : aStatus);
        pcs.firePropertyChange(DAOConstants.CONSTANTS_STATUS, aStatus, this.idStatus);
    }

    /**
     * Retorna o ID deste objeto.
     *
     * @return O código de identificação deste objeto na aplicação.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Altera o código deste objeto.
     *
     * @param value Nova identificação para este objeto.
     */
    public void setId(Long value) {
        Long old = this.getId();
        this.id = (value == null ? Long.getLong("1") : value);
        pcs.firePropertyChange(DAOConstants.CONSTANTS_ID, old, this.id);
    }

    /**
     * Retorna o nome deste objeto.
     *
     * @return Uma descrição que caracteriza o nome para o objeto.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Altera o nome deste objeto.
     *
     * @param value Renomeia o objeto com o novo valor.
     */
    public void setName(String value) {
        String old = this.getName();
        this.name = (value == null ? "Meu nome" : value);
        pcs.firePropertyChange(DAOConstants.CONSTANTS_NAME, old, this.name);
    }

    /**
     * Retorna o estado deste objeto.
     *
     * @return Ativo ou inativo, caso contrário.
     */
    public String getState() {
        return this.idStatus;
    }

    /**
     * Altera o estado deste objeto. Os possíveis valores são: Ativo ou Inativo.
     *
     * @param value Novo estado para este objeto.
     */
    public void setState(String value) {
        String old = this.idStatus;
        this.idStatus = (value == null ? "Undefined" : value);
        pcs.firePropertyChange(DAOConstants.CONSTANTS_STATUS, old, this.idStatus);
    }

    /**
     * Retorna o suporte a mudança de propriedades, ou seja, o objeto que
     * controla a mudança de propriedades.
     *
     * @return O suporte a alteração de atributos interligados com a GUI à para
     * suas classes filhas. JSR 295 (Beans Binding).
     */
    protected PropertyChangeSupport getChangeSupport() {
        return this.pcs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Objeto)) {
            return false;
        }

        Objeto other = (Objeto) object;
        return !((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id)));
    }

    /**
     * Permite adicionar um ouvinte para observar mudanças no valor dos
     * atributos do objeto, mais conhecido como beans binding (JSR 295).
     *
     * Este mecanismo implementa o padrão <b>Observer</b>, isto é, esta classe é
     * registrada como ouvinte e fica observando a mudança de valor de seus
     * atributos, atualizando esses valores onde forem usados; por exemplo em um
     * <code>JTexField</code>, se sua propriedade <code>text</code> for
     * alterada, o valor linkado será atualizado também.
     *
     * @param listener ouvinte (observer) para mudanças de estado do objeto.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Permite remover um ouvinte para observar mudanças no valor dos atributos
     * do objeto, mais conhecido como beans binding (JSR 295).
     *
     * Este mecanismo implementa o padrão <b>Observer</b>, isto é, esta classe é
     * desvinculada como ouvinte e não mais observa a mudança de valor de seus
     * atributos.
     *
     * @param listener Ouvinte (observer) a ser desvinculado.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Retorna o nome deste objeto, quando usado em listas, comboboxes e outros.
     *
     * @return este objeto convertido em <code>String</code>
     */
    @Override
    public String toString() {
        return name;
    }
}
