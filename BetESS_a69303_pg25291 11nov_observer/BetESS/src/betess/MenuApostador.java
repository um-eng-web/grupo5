/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author ana almeida
 */
public class MenuApostador {

    public Scanner in = new Scanner(System.in).useLocale(Locale.US);
    Apostador user;
    private BetESS bd;
    private boolean flag;

    public MenuApostador(Apostador u, BetESS bd) {
        this.user = u;
        this.bd = bd;
        flag = true;

    }


    public void start() {
        while (flag) {
            System.out.println("************************************************");
            System.out.println("           Bem Vindo " + user.getNome());
            System.out.println("************************************************");
            System.out.println("Escolha a opção:");
            System.out.println("1-Listar Apostas");
            System.out.println("2-Apostar");
            System.out.println("3-Ganho em apostas fechadas");
            System.out.println("4-Valor disponivel para apostar");
            System.out.println("5-Listar Notificações");
            System.out.println("6-Limpar Notificações");
            System.out.println("7-Sair");
            String opt = in.next();

            switch (opt) {
                case ("1"):
                    System.out.println("************************************************");
                    menuListarApostas();
                    break;
                case ("2"):
                    System.out.println("************************************************");
                    menuApostar();
                    break;
                case ("3"):
                    System.out.println("************************************************");
                    menuGanhoApostas();
                    break;
                case ("4"):
                    System.out.println("************************************************");
                    System.out.println("Valor: " + this.user.getValor());
                    break;
                case ("5"):
                    System.out.println("************************************************");
                    listarNotificacoesOdd();
                    break;
                case ("6"):
                    System.out.println("************************************************");
                    limparNofificacoes();
                    break;
                case ("7"):
                    System.out.println("************************************************");
                    this.bd.updateUser(user);
                    flag = false;
                    break;

            }
        }

    }

    private void menuListarApostas() {
        for (int d : this.bd.getEventos().keySet()) {
            Evento e = this.bd.getEventos().get(d);
            if (e.getEstado()) {
                System.out.println(e.toString());
            }
        }
    }

    private void menuApostar() {

        System.out.println("Id da aposta");
        int idEvento = in.nextInt();
        if(this.bd.getEventos().get(idEvento).getEstado()){
        System.out.println("Qual o resultado a apostar:");
        System.out.println("0-Empate");
        System.out.println("1-Equipa 1");
        System.out.println("2-Equipa 2");
        int aposta = in.nextInt();
        System.out.println("Valor a apostar");
        double valor = in.nextDouble();
        if (this.user.getValor() < valor) {
            System.out.println("Não tem valor disponivel para essa aposta");
        } else {

            double v = this.user.getValor();
            this.user.setValor(v - valor);
            Evento e = this.bd.getEventos().get(idEvento);
            e.addObserver(user);

            //Aposta(int id, String eqA, String eqB, double oddA, double oddB, double oddEmp)
            Odd o = e.getOdd();
            Aposta a = new Aposta(this.user.getContadorAposta(), idEvento, aposta, valor, o.getOddA(), o.getOddB(), o.getOddEmpate());

            //System.out.println("APOSTA   A ################ "+ a.getOdd().getOddA());
            //a.setOdd(e.getOdd());
            //a.setValor(valor);
            //((Apostador)this.bd.getUsers().get(user.getEmail())).addAposta(idEvento, a);
            this.user.addAposta(a.getId(), a);
            System.out.println("Registado");
        }
        }
        else
            System.out.println("Aposta fechada");

    }

    private void menuGanhoApostas() {

        if (this.user.getListaApostas().isEmpty()) {
            System.out.println("Não existem apostas");
            System.out.println("");
        } else {
            for (int id : this.user.getListaApostas().keySet()) {

                if (this.user.getListaApostas().get(id).fechada()) {
                    Aposta a = user.getListaApostas().get(id);
                    //System.out.println("Aposta :" + a.getId());
                    System.out.println(this.bd.getEventos().get(id).toString());
                    System.out.println("Ganho : " + a.getGanho());
                }

            }
        }

    }

    private void limparNofificacoes() {
        this.user.limpaNoficacoesOdd();
    }

    private void listarNotificacoesOdd() {
        if (user.getNotificacoesOdd().isEmpty()) {
            System.out.println("Não existem alterações");
        } else {
            System.out.println("Novas odds: ");
            for (int idEvento : user.getNotificacoesOdd()) {
                System.out.println("EVENTO: " + this.bd.getEventos().get(idEvento).toString());
            }
        }
    }

}
