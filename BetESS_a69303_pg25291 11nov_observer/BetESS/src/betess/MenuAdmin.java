package betess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Bruno Pereira
 */
public class MenuAdmin {

    private Scanner in;
    private Admin adim;
    private BetESS bd;
    private boolean flag = true;

    public MenuAdmin(Admin adm, BetESS bd) {
        in = new Scanner(System.in).useLocale(Locale.US);
        this.adim = adm;
        this.bd = bd;
    }

    public void start() {
        while (flag) {
            System.out.println("************************************************");
            System.out.println("*                    ADMIN                     *");
            System.out.println("************************************************");
            System.out.println("Escolha a opção");
            System.out.println("1-Registar nova Aposta");
            System.out.println("2-Listar Apostas Registadas");
            System.out.println("3-Fechar Aposta");
            System.out.println("4-Concluir Aposta");
            System.out.println("5-Registar Bookie");
            System.out.println("6-Sair");
            String opt = in.next();
            switch (opt) {
                case ("1"):
                    System.out.println("************************************************");
                    registarAposta();
                    break;
                case ("2"):
                    System.out.println("************************************************");
                    menuListarApostas();
                    break;
                case ("3"):
                    System.out.println("************************************************");
                    fecharAposta();
                    break;
                case ("4"):
                    System.out.println("************************************************");
                    conluirAposta();
                    break;
                case ("5"):
                    System.out.println("************************************************");
                    menuRegistarBookie();
                    break;
                case ("6"):
                    System.out.println("************************************************");
                    flag = false;
                    break;
            }
        }

    }

    private void menuRegistarBookie() {
        String opt;

        System.out.println("************************************************");
        System.out.println("*               Registar Bookie                *");
        System.out.println("************************************************");

        System.out.println("Insira um email: ");
        String email = in.next();
        if (bd.getUsers().containsKey(email)) {
            System.out.println("Escolha outro email");
        } else {
            System.out.println("Insira o seu nome: ");
            String nome = in.next();
            System.out.println("Insira uma palavra passe");
            String pass = in.next();

            Bookie user = new Bookie(email, pass, nome);
            // bd.addUser(user);
            //listaUtilizadores.put(email, user);
            bd.addUser(user);
            //MenuBookie menu = new MenuBookie((Bookie) bd.getUsers().get(user.getEmail()), bd); // Vai buscar o utilizador por email ao BetESS
            // menu.addUser();

            //menu.start();
            System.out.println("Bookie Registado");
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

    private void registarAposta() {
        System.out.println("Descrição do Evento");
        String des = in.next();
        System.out.println("Data do evento (yyyy-MM-dd)");
        String str = in.next();
        LocalDate date = LocalDate.parse(str);
        System.out.println("Hora do evento (HH:mm)");
        String str2 = in.next();
        LocalTime time = LocalTime.parse(str2);
        LocalDateTime dateEvent = LocalDateTime.of(date, time);

        System.out.println("Nome da equipa 1");
        String eqA = in.next();
        System.out.println("Nome da equipa 2");
        String eqB = in.next();
        System.out.println("Odd para a equipa 1");
        double oddA = in.nextDouble();
        System.out.println("Odd para a equipa 2");
        double oddB = in.nextDouble();
        System.out.println("Odd para o empate");
        double empate = in.nextDouble();
        //    public Evento(int id, String des, LocalDateTime d, double oddA, double oddB, double oddEm, String eA, String eB) {

        Evento a = new Evento(Main.getContadorEvento(), des, dateEvent, oddA, oddB, empate, eqA, eqB);
        Main.addContadorEvento();
        bd.addEvento(a.getId(), a);

    }

    //EDITAR A APOSTA
    private void editarAposta() {

    }

    private void conluirAposta() {
        System.out.println("Introduza o id da aposta a fechar");
        int id = in.nextInt();
        System.out.println("Introduza o resutado");
        System.out.println("0-Empate");
        System.out.println("1-Equipa 1");
        System.out.println("2-Equipa 2");
        int resultado = in.nextInt();
        Evento e = bd.getEventos().get(id);
        //e.setEstadoOff();
        e.setResultado(resultado);
        e.setConcluido();

        e.notifyObserver(resultado);
        // bd.avisaUsers(id, resultado);

        //Aposta a = bd.getApostas().get(id);
        //a.setResultado(resultado);
        //a.setEstadoOff();
        //Main.avisaUsers(a);
    }

    private void fecharAposta() {
        System.out.println("Introduza o id da aposta a fechar");
        int id = in.nextInt();

        Evento e = bd.getEventos().get(id);
        e.setEstadoOff();
        //bd.avisaUsers(id, resultado);
        //Aposta a = bd.getApostas().get(id);
        //a.setResultado(resultado);
        //a.setEstadoOff();
        //Main.avisaUsers(a);

    }

}
