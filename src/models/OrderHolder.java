/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author yacin
 */
public final class OrderHolder {

    private Order order;
    private int idOrder ; 
    private final static OrderHolder INSTANCE = new OrderHolder();

    private OrderHolder() {
    }

    public static OrderHolder getInstance() {
        return INSTANCE;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
    
    

}
