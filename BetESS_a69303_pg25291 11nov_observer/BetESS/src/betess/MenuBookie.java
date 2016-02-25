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
public class MenuBookie {

    private Scanner in;
    private Bookie bookie;
    private BetESS bd;
    private boolean flag = true;

    public MenuBookie(Bookie bo, BetESS bd) {
        in = new Scanner(System.in).useLocale(Locale.US);
        this.bookie = bo;
        this.bd = bd;
    }

    public void start() {
        while (flag) {
            System.out.println("************************************************");
            System.out.println("*                    BOOKIE                    *");
            System.out.println("************************************************");
            System.out.println("Escolha a opção");
            System.out.println("1-Registar nova Aposta");
            System.out.println("2-Editar Odd de Aposta");
            System.out.println("3-Registar Interesse ");
            System.out.println("4-Listar Apostas Diponiveis para Registar Interesse");
            System.out.println("5-Listar Resultado Final das Apostas com Interesse");
            System.out.println("6-Listar Notificações de Odds de Apostas Alteradas");
            System.out.println("7-Limpar Notificações");
            System.out.println("8-Sair");
            String opt = in.next();
            switch (opt) {
                case ("1"):
                    System.out.println("************************************************");
                    registarAposta();
                    break;
                case ("2"):
                    System.out.println("************************************************");
                    editarAposta();
                    break;
                case ("3"):
                    System.out.println("************************************************");
                    registarInteresse();
                    break;
                case ("4"):
                    System.out.println("************************************************");
                    listarApostas();
                    break;
                case ("5"):
                    System.out.println("************************************************");
                    listarFinalApostas();
                    break;
                case ("6"):
                    System.out.println("************************************************");
                    listarNotificacoesOdd();
                    break;
                case ("7"):
                    System.out.println("************************************************");
                    limparNofificacoes();
                    break;
                case ("8"):
                    System.out.println("************************************************");
                    flag = false;
                    break;
            }
        }

    }

    private void registarAposta() {
        System.out.println("Descrição do Evento");
        in.next();
        String des = in.nextLine();
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

        Evento a = new Evento(Main.getContadorEvento(), des, dateEvent, oddA, oddB, empate, eqA, eqB);
        Main.addContadorEvento();
        this.bookie.addEvento(a.getId());
        bd.addEvento(a.getId(), a);
        a.addObserver(bookie);

        //this.bookie.addEvento(a.getId(), a);
    }

    private void editarAposta() {
        System.out.println("Insira Id da aposta a alterar");
        int id = in.nextInt();

        if (this.bookie.getEventos().contains(id)) {
            System.out.println("Odd para a equipa 1");
            double oddA = in.nextDouble();
            System.out.println("Odd para a equipa 2");
            double oddB = in.nextDouble();
            System.out.println("Odd para o empate");
            double empate = in.nextDouble();
            Evento e = this.bd.getEventos().get(id);
            e.setOdd(oddA, oddB, empate);
            e.notifyObserverOdd();
            System.out.println("Registado");
        } else {
            System.out.println("Não tem permissões para alterar");
        }
        //this.bd.avisaBookiesOdd(this.bookie.getEmail(), e.getId());

    }

    private void registarInteresse() {
        System.out.println("Insira Id da aposta que deseja registar interesse");
        int id = in.nextInt();
        if (this.bd.getEventos().containsKey(id)) {
            Evento e = this.bd.getEventos().get(id);
            if(e.getEstado()){
            this.bd.getEventos().get(id).addObserver(bookie);

            System.out.println("REGISTADO");
            }
            else
                System.out.println("Aposta fechada");
        }

    }

    private void listarApostas() {
        for (int d : this.bd.getEventos().keySet()) {
            Evento e = this.bd.getEventos().get(d);
            if (e.getEstado()) {
                if (!this.bookie.getEventos().isEmpty()) {
                    if (!this.bookie.getEventos().contains(e.getId())) {
                        System.out.println(e.toString());
                    }
                } //&&( (this.bookie.getEventos().isEmpty()) || (this.bookie.getEventos().contains(e.getId())) )
                else {
                    System.out.println(e.toString());
                }
            }
        }
    }

    private void limparNofificacoes() {
        this.bookie.limpaNoficacoesOdd();
    }

    private void listarNotificacoesOdd() {
        if (bookie.getNotificacoesOdd().isEmpty()) {
            System.out.println("Não existem alterações");
        } else {
            System.out.println("Novas odds: ");
            for (int idEvento : bookie.getNotificacoesOdd()) {
                System.out.println("EVENTO: " + this.bd.getEventos().get(idEvento).toString());
            }
        }
    }
    /*
     private void listarFinalApostas() {
     for (int id : bookie.getEventos().keySet()) {
     if (bookie.getEventos().get(id).concluido()) {
     Evento e = bookie.getEventos().get(id);
     System.out.println(e.toString());
     System.out.println("");
     String resultado;
     switch (e.getResultado()) {
     case (0):
     resultado = "Empate";
     break;
     case (1):
     resultado = e.getEquipaA();
     break;
     default:
     resultado = e.getEquipaB();
     break;
     }
     System.out.println("RESULTADO: " + resultado);
     }
     }
     }
     */

    private void listarFinalApostas() {
        System.out.println(this.bookie.getResultadosEventos());
    }

}
