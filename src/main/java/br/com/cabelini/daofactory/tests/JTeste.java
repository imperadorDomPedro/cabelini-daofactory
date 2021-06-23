/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cabelini.daofactory.tests;

import br.com.cabelini.daofactory.entity.BusinessException;
import br.com.cabelini.daofactory.model.Contact;

/**
 *
 * @author henrique
 */
public class JTeste {
    public static void main(String args[]) throws BusinessException {
        Contact contato = new Contact();
        
        contato = contato.find(new Long(5));
        contato.setAddressEmail(0, "henrique0018@gmail.com");
        contato.save();
        
        System.out.println("Nome contato....: " + contato + ", Email: " +contato.getAddressEmail(0));
    }
}
