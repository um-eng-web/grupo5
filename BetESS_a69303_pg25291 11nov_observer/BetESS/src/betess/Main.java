package betess;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author Bruno Pereira
 */
public class Main {

    public static final int EQUIPA_A = 1;
    public static final int EMPATE = 0;
    public static final int EQUIPA_B = 2;

    private static Scanner input = new Scanner(System.in);
    private static int contadorIdAposta = 0;
    private static int contadorIdEvento = 0;
    private static BetESS bd;



    private static void menuRegistarApostador() {
        String opt;

        System.out.println("************************************************");
        System.out.println("*               Registar Apostador             *");
        System.out.println("************************************************");

        System.out.println("Insira um email: ");
        String email = input.next();
        if(bd.getUsers().containsKey(email)){
            System.out.println("Escolha outro email");
        }
        else{
        System.out.println("Insira o seu nome: ");
        String nome = input.next();
        System.out.println("Insira uma palavra passe");
        String pass = input.next();
        System.out.println("Insira a quantia para as apostas");
        double valor = input.nextDouble();

        Apostador user = new Apostador(email, pass, nome, valor);
        // bd.addUser(user);
        //listaUtilizadores.put(email, user);
        bd.addUser(user);
        MenuApostador menu = new MenuApostador((Apostador) bd.getUsers().get(user.getEmail()), bd);
        // menu.addUser();

        menu.start();
        }
    }
/*
    private static void menuRegistarUtilizador() {
        String opt;

        System.out.println("************************************************");
        System.out.println("*              Registar Utilizador             *");
        System.out.println("************************************************");
        System.out.println("1-Bookie");
        System.out.println("2-Apostador");
        opt = input.next();
        if (!(opt.equals("1") || opt.equals("2"))) {
            menuPrincipal();
        }
        switch (opt) {
            case ("1"):
                menuRegistarBookie();
                break;
            case ("2"):
                menuRegistarApostador();
                break;
        }

    }
    */

    private static void menuAdmin() {
        System.out.println("Insira o seu email: ");
        String email = input.next();
        System.out.println("Insira a sua palavra passe");
        String pass = input.next();

        if (bd.existUser(email)) {
            User u = bd.getUsers().get(email);
            if (u instanceof Admin) {
                if (u.getPassword().equals(pass)) {
                    Admin a = (Admin) u;
                    MenuAdmin m = new MenuAdmin(a, bd);
                    m.start();
                } else {
                    System.out.println("Palavra passe errada");
                    menuAdmin();
                }
            } else {
                System.out.println("Não tem autorização");
                menuPrincipal();
            }

        } else {
            System.out.println("Administrador inexistente");
            menuPrincipal();
        }

    }

    private static void menuUser() {
        System.out.println("Insira o seu email: ");
        String email = input.next();
        System.out.println("Insira a sua palavra passe");
        String pass = input.next();

        if (bd.getUsers().containsKey(email)) {
            User u = bd.getUsers().get(email);

            if (u.getPassword().equals(pass)) {
                if (u instanceof Apostador) {
                    MenuApostador menuUser = new MenuApostador((Apostador) u, bd);
                    menuUser.start();
                } else {
                    System.out.println("Não tem premissões");
                }
            } else {
                System.out.println("Palavra passe errada");
                menuUser();
            }

        } else {
            System.out.println("Utilizador inexistente");
            menuPrincipal();
        }

    }

    public static void menuPrincipal() {
        String opt;

        while (true) {
            System.out.println("************************************************");
            System.out.println("*                    BetESS                    *");
            System.out.println("************************************************");

            System.out.println("Escolha uma das seguintes opções");
            System.out.println("1-Registar");
            System.out.println("2-Entrar");
            System.out.println("3-Sair");
            opt = input.next();
            if (!(opt.equals("1") || opt.equals("2") || opt.equals("3"))) {
                menuPrincipal();
            }

            switch (opt) {
                case ("1"):
                    System.out.println("************************************************");
                    menuRegistarApostador();
                    break;
                case ("2"):
                    System.out.println("************************************************");
                    System.out.println("1-Admin");
                    System.out.println("2-Utilizador Regular");
                    System.out.println("3-Bookie");
                    opt = input.next();
                    if (opt.equals("1")) {
                        menuAdmin();
                    } else if (opt.equals("2")) {
                        menuUser();
                    } else if (opt.equals("3")) {
                        menuBookie();
                    }
                    break;
                case ("3"):
                    System.out.println("************************************************");
                    System.exit(0);
            }

        }
    }

    private static void menuBookie() {
        System.out.println("Insira o seu email: ");
        String email = input.next();
        System.out.println("Insira a sua palavra passe");
        String pass = input.next();

        if (bd.getUsers().containsKey(email)) {
            User u = bd.getUsers().get(email);

            if (u.getPassword().equals(pass)) {
                if (u instanceof Bookie) {
                    MenuBookie menuBookie = new MenuBookie((Bookie) u, bd);
                    menuBookie.start();
                } else {
                    System.out.println("Não tem premissões");
                }
            } else {
                System.out.println("Palavra passe errada");
                menuUser();
            }

        } else {
            System.out.println("Utilizador inexistente");
            menuPrincipal();
        }
    }

    /*
     public static HashMap<Integer, Aposta> getApostas(){
     return listaApostas;
     }
     */
    public static void addContadorEvento() {
        contadorIdEvento++;
    }

    public static int getContadorEvento() {
        return contadorIdEvento;

    }
    /*
     public static void addAposta(Aposta a){
     listaApostas.put(a.getId(), a);
     }
     */
    /*
     public static void avisaUsers(Aposta a) {
     for(String email : listaUtilizadores.keySet()){
     Apostador u = listaUtilizadores.get(email);
     if(!(u instanceof Admin)){
     u.setResultadoAposta(a.getId(),a.getResultadoAposta());
           
     }
            
     }
     }
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Admin a = new Admin("a", "123", "bruno");
        Apostador b = new Apostador("ze", "123", "ze", 20);
        Bookie bo = new Bookie("ze1", "123", "ze1");
        Evento evento = new Evento(0, "dese", LocalDateTime.now(), 2.1, 1.1, 3.1, "a", "b");
        bo.addEvento(0);
        //bo.addEvento(0, evento);
        contadorIdEvento++;

        bd = new BetESS();
        bd.addUser(a);
        bd.addUser(b);
        bd.addUser(bo);
        bd.addEvento(0, evento);

        //public Evento(int id, String des, LocalDateTime d, double oddA, double oddB, double oddEm, String eA, String eB) {
        menuPrincipal();
    }

}
