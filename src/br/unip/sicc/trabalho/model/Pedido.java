/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.model;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Pedido {
    private Integer id,qtdeItens;
    private String codigo;
    private TipoPedido tipo;
    

    Pedido (){
        
    }
     Pedido (Integer id, int qtdeItens, String codigo, TipoPedido tipo){
         this(qtdeItens, codigo, tipo);
             
         this.id=id;
       
        
    }
    Pedido ( int qtdeItens, String codigo, TipoPedido tipo){
       
        this.qtdeItens=qtdeItens;
        this.codigo=codigo;
        this.tipo = tipo;
        
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQtdeItens() {
        return qtdeItens;
    }

    public void setQtdeItens(int qtdeItens) {
        this.qtdeItens = qtdeItens;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.qtdeItens;
        hash = 29 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", qtdeItens=" + qtdeItens + ", codigo=" + codigo + '}';
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }
    
    
    
}
