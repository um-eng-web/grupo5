/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.util.ArrayList;

/**
 *
 * @author nunomonteirodias1
 */
public interface Subject {

   public String getState();

   public void setState(String state,float valor);

   public void attach(Observer observer);

   public void notifyAllObservers(int code,float valor,String resultado);	
}
