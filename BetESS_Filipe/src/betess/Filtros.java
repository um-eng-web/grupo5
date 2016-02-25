/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.util.ArrayList;

/**
 *
 * @author FilipePinto
 */
public interface Filtros {
    
    ArrayList<Jogo> todosJogos();
    
    ArrayList<Jogo> bookieJogos(User usr);
    
    ArrayList<Jogo> equipaJogos(String equipa);
    
    ArrayList<Jogo> jogosBookiequipa(User usr, String equipa);
    
}
