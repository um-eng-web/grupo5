/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author nunomonteirodias1
 */
public class Menu {
    
    private int cnt=1;
    
    //Registo de utilizador ou bookie (efetuado pelo admin)
    public void menuRegisto(BetESS e,int flag) throws ParseException{
        System.out.format("Username: ");
        Scanner reader = new Scanner(System.in);
        String n = reader.nextLine();
        while(n.equals("")){
            System.out.println("Por favor introduza um username!");
            System.out.format("Username: ");
            reader = new Scanner(System.in);
            n = reader.nextLine();
        }
        if(e.getLista_utilizadores().containsKey(n)==false){
            System.out.format("Password: ");
            String pass=reader.nextLine();
            while(pass.equals("")){
                System.out.println("Por favor introduza uma password!");
                System.out.format("Password: ");
                pass=reader.nextLine();
            }
            System.out.format("Email: ");
            String mail=reader.nextLine();
            while(mail.contains(" ")){
                System.out.println("Email inválido (espaços em branco)!");
                System.out.format("Email: ");
                mail=reader.nextLine();}
            while(mail.equals("")){
                System.out.println("Por favor introduza um email!");
                System.out.format("Email: ");
                mail=reader.nextLine();
            }
            mail.concat("@betessmail.pt");
            float valor=0f;
            if(flag==0){
                System.out.format("Valor Inicial BetESScoins: ");
                valor=reader.nextFloat();
            }
            TreeMap<String,User> aux=e.getLista_utilizadores();
            if(flag==0) aux.put(n,new Apostador(n,pass,mail,valor)); //apostador
            if(flag==1) { //bookie
                aux.put(n,new Bookie(n,pass,mail));
                e.getBookies().put(n, new Bookie(n,pass,mail));
            }
            System.out.println("Registo efetuado com sucesso!");
        }
        else System.out.println("Utilizador já existe");
        this.menuInicial(e);
        }
    
    //saldo da conta
    public void menuMeusBetESScoins(BetESS e, User usr) throws ParseException{
        System.out.println("Neste momento possui "+((Apostador) usr).getQuantia_disp()+" BetESScoins!");
        this.menuGeral(e,usr);
    }
    
    //lista de jogos
    public void menuJogo(BetESS e,User usr) throws ParseException{
        if(e.getLista_jogos().isEmpty()){
            System.out.println("Não há jogos disponíveis!");
            if(usr instanceof Admin) this.menuGeralAdmin(e,usr);
            else this.menuGeral(e,usr);
        }
        else{
            System.out.println("Lista de jogos disponíveis:");
                for(Jogo j:e.getLista_jogos()){
                        if (usr instanceof Admin) System.out.println(j.getId()+"- " + j.getEquipa1()+" vs " + j.getEquipa2()+ "("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+") Data e Hora:"+j.getData()+" Status:"+j.isEstado());
                        else{ 
                            if(j.isEstado().equals("aberto") || j.isEstado().equals("editado")) System.out.println(j.getId()+"- " + j.getEquipa1()+" vs "+j.getEquipa2()+" ("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+") Data e Hora:"+j.getData()+" Status:"+j.isEstado());
                        }
            }
            if(usr instanceof Admin) this.menuGeralAdmin(e,usr);
            else this.menuGeral(e,usr);
        }
    }
    
    //adiciona uma nova aposta por um dado apostador
    public void menuAposta(BetESS e,User usr) throws ParseException{
        ArrayList<Jogo> aux=e.getLista_jogos();
        if(aux.isEmpty()){
            System.out.println("Não há jogos disponíveis!");
            this.menuGeral(e,usr);
        }
        else{
            System.out.println("Lista de jogos disponíveis:");
                for(Jogo j:aux){
                    if(j.isEstado().equals("aberto") || j.isEstado().equals("editado")){
                    System.out.println(j.getId()+"- " + j.getEquipa1()+" vs "+j.getEquipa2()+" ("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+")");
                }
            }
        float qt_aux=0;
        System.out.format("Introduza o id do jogo: ");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int number = reader.nextInt();
        System.out.format("Quantia a apostar: ");
        float qt = reader.nextFloat(); 
        System.out.format("Introduza o resultado (1,x,2): ");
        Scanner reader2=new Scanner(System.in);
        String res = reader2.nextLine(); 
        System.out.format("Deseja mesmo apostar em "+aux.get(number-1).getEquipa1() + " vs "+aux.get(number-1).getEquipa2()+ "? [s/n] ");
        String resposta=reader2.nextLine();
            if(resposta.equals("s")){
                qt_aux=((Apostador) usr).getQuantia_disp();
                if(qt_aux>=qt){
                     ((Apostador) usr).getLista_aposta().add(new Aposta(number,e.getLista_jogos().get(number-1).getEquipa1(),e.getLista_jogos().get(number-1).getEquipa2(),qt,res,aux.get(number-1).getLista_odds().get(aux.get(number-1).getLista_odds().size()-1)));
                     ((Apostador) usr).setQuantia_disp(qt_aux-qt);
                     if(aux.get(number-1).getLista_apostadores().contains(usr.getUsername())==false) {
                         aux.get(number-1).getLista_apostadores().add(usr.getUsername());
                         aux.get(number-1).attach(((Apostador)usr).getAp());
                     }
                     System.out.println("Aposta registada com sucesso. Boa sorte!");
                }
                if(qt_aux<qt) System.out.println("Não tem BetESScoins suficientes!");
                this.menuGeral(e,usr);
            }
            if(resposta.equals("n")) this.menuJogo(e,usr);
        }
    }
    
    //Apostas do utilizador
    public void menuMinhasApostas(BetESS e, User usr) throws ParseException{
        ArrayList<Aposta> a=((Apostador) usr).getLista_aposta();
        if(a.isEmpty()){
            System.out.println("Ainda não apostou!");
        }
        else{
            System.out.println("As minhas apostas:");
            for(Aposta j:a){
                System.out.println(j.getIdJogo()+"- Jogo: "+j.getEquipa1()+" vs " +j.getEquipa2()+"     Resultado: "+j.getResultado()+"     Valor da Aposta:"+j.getQuantia()+" BetESScoins     Ganho: "+j.getQuantiaganha()+" BetESScoin(s)");
            }
        }
        this.menuGeral(e,usr);
    }
    
    //depósito de moedas
    public void menuDepBetESScoins(BetESS e,User usr) throws ParseException{
        System.out.format("Quantia que deseja depositar (BetESScoins): ");
        Scanner reader = new Scanner(System.in);
        float coins = reader.nextInt();
        ((Apostador) usr).inc(coins);
        System.out.println("Quantia depositada com sucesso!");
        this.menuGeral(e,usr);
    }
    
    //caixa de mensagens do Apostador e bookie
    public void caixaMsg(BetESS e,User usr) throws ParseException{
        if(usr instanceof Apostador){
            for(Aposta a:((Apostador)usr).getLista_aposta()){
                    System.out.println(e.getLista_jogos().get(a.getIdJogo()-1).getObservers().get(e.getLista_jogos().get(a.getIdJogo()-1).getObservers().indexOf(((Apostador)usr).getAp())).msg);
                    e.getLista_jogos().get(a.getIdJogo()-1).getObservers().get(e.getLista_jogos().get(a.getIdJogo()-1).getObservers().indexOf(((Apostador)usr).getAp())).setMsg("");
            }
            this.menuGeral(e,usr);
        }
        if(usr instanceof Bookie){
            for(Jogo a:((Bookie)usr).getJogos_interesse()){
                    System.out.println(a.getObservers().get(e.getLista_jogos().get(a.getId()-1).getObservers().indexOf(((Bookie)usr).getBk())).msg);
                    a.getObservers().get(e.getLista_jogos().get(a.getId()-1).getObservers().indexOf(((Bookie)usr).getBk())).setMsg("");
            }
            this.menuGeralBookie(e, usr);
        }
    }
    
    //menu de apostador
    public void menuGeral(BetESS e,User usr) throws ParseException{
        System.out.println("***************** Bem-vindo, "+usr.getUsername()+"! ********************");
        System.out.println("1-Lista de jogos");
        System.out.println("2-Fazer aposta");
        System.out.println("3-As minhas apostas");
        System.out.println("4-Os meus BetESScoins");
        System.out.println("5-Depositar BetESScoins");
        System.out.println("6-Ver caixa de mensagens");
        System.out.println("7-Sair");
        System.out.println("**********************************************************");
        System.out.format("Introduza a opção pretendida: ");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int number = reader.nextInt();
        switch(number){
            case 1: this.menuJogo(e,usr);
                    break;
            case 2: this.menuAposta(e,usr);
                    break;
            case 3: this.menuMinhasApostas(e,usr);
                    break;
            case 4: this.menuMeusBetESScoins(e,usr);
                    break;
            case 5: this.menuDepBetESScoins(e,usr);
                    break;
            case 6: this.caixaMsg(e,usr);
                    break;
            case 7: System.out.println("Obrigado, esperamos por si!");
                    this.menuInicial(e);
                    break;
            default:break;
        }
  }
    
    //adicionar jogo
    public void menuAdicJogo(BetESS e, User usr) throws ParseException{
        System.out.format("Introduza a primeira equipa: ");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        String game1 = reader.nextLine();
        System.out.format("Introduza a segunda equipa: ");
        reader = new Scanner(System.in);  // Reading from System.in
        String game2 = reader.nextLine();
        System.out.format("Introduza a odd vitória: ");
        float odd_v = reader.nextFloat();
        System.out.format("Introduza a odd empate: ");
        float odd_e = reader.nextFloat();
        System.out.format("Introduza a odd derrota: ");
        float odd_d = reader.nextFloat();
        Scanner reader2 = new Scanner(System.in);
        System.out.format("Introduza a data de realização do jogo (dd-MM-aaaa hh:mm:ss): ");
        String expectedPattern = "dd-MM-yyyy hh:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        Date date = formatter.parse(reader2.nextLine());
        GregorianCalendar calendar = new GregorianCalendar();
        Date data = calendar.getTime();
        while(date.before(data)){
            System.out.println("Data anterior à data atual!");
            System.out.format("Introduza a data de realização do jogo (dd-MM-aaaa hh:mm:ss): ");
            expectedPattern = "dd-MM-yyyy hh:mm:ss";
            formatter = new SimpleDateFormat(expectedPattern);
            date = formatter.parse(reader2.nextLine());
            calendar = new GregorianCalendar();
            data = calendar.getTime();
        }
        Jogo adicionar = new Jogo(cnt,game1,game2,odd_v,odd_e,odd_d,"aberto","",date,usr.getUsername());
        ArrayList<Jogo> jogos = e.getLista_jogos();
        int erro=0;
        if(jogos.isEmpty()){
            e.getLista_jogos().add(adicionar);
            ((Bookie)usr).getJogos_criados().add(adicionar);
            ((Bookie)usr).getJogos_interesse().add(adicionar);
            adicionar.attach(((Bookie)usr).getBk());
            cnt++;
            System.out.println("Jogo adicionado com sucesso!"); 
        }
        else{
            for(Jogo j:jogos){
                if(j.mesmoJogo(adicionar) && erro==0){
                    System.out.println("Este jogo já se encontra na lista de jogos!");
                    erro=1;
                }
            }
            if(erro==0){
                e.getLista_jogos().add(adicionar);
                ((Bookie)usr).getJogos_criados().add(adicionar);
                ((Bookie)usr).getJogos_interesse().add(adicionar);
                adicionar.attach(((Bookie)usr).getBk());
                cnt++;
                System.out.println("Jogo adicionado com sucesso!");
            }
        }
        this.menuGeralBookie(e,usr);
    }
    
    //fechar apostas num jogo
    public void menuFecharJogo(BetESS e,User usr) throws ParseException{
        ArrayList<Jogo> jogos=e.getLista_jogos();
        boolean entrou=false;
        if(jogos.isEmpty()){
            System.out.println("Não há jogos disponíveis!");
            this.menuGeralAdmin(e,usr);
        }
        else{
            System.out.println("Lista de jogos disponíveis:");
            for(Jogo j:jogos){
                if(j.isEstado().equals("aberto") || j.isEstado().equals("editado")){
                    entrou=true;
                System.out.println(j.getId()+"- " + j.getEquipa1()+" vs "+ j.getEquipa2()+ "("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+")");
                }
            }
            if(entrou==true){
            System.out.format("Introduza o id do jogo: ");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int id = reader.nextInt();
            jogos.get(id-1).setEstado("fechado");
            String b=jogos.get(id-1).getBookie();
            ((Bookie)e.getBookies().get(b)).getJogos_criados().get(id-1).setEstado("fechado");
            User x=e.getBookies().get(b);
            jogos.get(id-1).setState("fechado",0);
            System.out.println("Aposta encerrada com sucesso!");
            }
            this.menuGeralAdmin(e,usr);
        }
    }
    
    //alterar odds de um determinado jogo
    public void menuEditarJogo(BetESS e, User usr) throws ParseException{
        ArrayList<Jogo> jogos=((Bookie)usr).getJogos_criados();
        boolean flag=false;
        if(jogos.isEmpty()){
            System.out.println("Não há jogos disponíveis!");
        }
        else{
            System.out.println("Lista de jogos disponíveis:");
            for(Jogo j:jogos){
                if(j.isEstado().equals("aberto") || j.isEstado().equals("editado")){
                    flag=true;
                System.out.println(j.getId()+"- " + j.getEquipa1()+" vs " +j.getEquipa2() +"("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+")");
                }
            }
            if(flag==true){
            System.out.format("Introduza o id do jogo que deseja alterar: ");
            Scanner reader = new Scanner(System.in);
            int id=reader.nextInt();
            System.out.format("Introduza a odd de vitória: ");
            float odd_vitoria=reader.nextFloat();
            System.out.format("Introduza a odd de empate: ");
            float odd_empate=reader.nextFloat();
            System.out.format("Introduza a odd de derrota: ");
            float odd_derrota=reader.nextFloat();
            e.getLista_jogos().get(id-1).getLista_odds().add(new Odd(odd_vitoria,odd_empate,odd_derrota));
            jogos.get(id-1).getLista_odds().add(new Odd(odd_vitoria,odd_empate,odd_derrota));
            System.out.println("Jogo editado com sucesso!");
            jogos.get(id-1).setState("editado",0);
            }
        }
        this.menuGeralBookie(e, usr);
    }
    
    //Jogo concluido pelo admin. Resultado final já disponível
    public void menuConcluirJogo(BetESS e,User usr) throws ParseException{
        ArrayList<Jogo> jogos=e.getLista_jogos();
        if(jogos.isEmpty()){
            System.out.println("Não há jogos disponíveis!");
        }
        else{
            boolean entrou=false;
            System.out.println("Lista de jogos disponíveis:");
            for(Jogo j:jogos){
                if(j.isEstado().equals("fechado")){
                    entrou=true;
                System.out.println(j.getId()+"- " + j.getEquipa1()+" vs " +j.getEquipa2()+ "("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+")");
                }
            }
            if(entrou==true){
            System.out.format("Introduza o id do jogo: ");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int id = reader.nextInt();
            Jogo aux1=e.getLista_jogos().get(id-1);
            System.out.format("Introduza o resultado: ");
            Scanner reader2 = new Scanner(System.in);
            String res = reader2.nextLine();
            jogos.get(id-1).setResultado(res);
            ((Bookie)e.getBookies().get(jogos.get(id-1).getBookie())).getJogos_criados().get(id-1).setEstado("concluido");
            jogos.get(id-1).setEstado("concluido");
            ArrayList<String> aux=jogos.get(id-1).getLista_apostadores();
            float qt;
            float odd=0f;
            float calculo=0f;
            float balanco=0f;
            TreeMap<String,User> lista=e.getLista_utilizadores();
            User ut;
            for(String jogador:aux){ //para cada jogador
                ut=lista.get(jogador);
                for(Aposta a:((Apostador) ut).getLista_aposta()){
                    if(a.getIdJogo()==id && a.getResultado().equals(jogos.get(id-1).getResultado())){
                    qt=((Apostador) ut).getQuantia_disp();
                    a.setResultado(res);
                    if(res.equals("1")) odd=a.getOdd().getOdd_vitoria();
                    if(res.equals("x")) odd=a.getOdd().getOdd_empate();
                    if(res.equals("2")) odd=a.getOdd().getOdd_derrota();
                    calculo=a.getQuantia()*odd;
                    balanco+=calculo;
                    ((Apostador) ut).setQuantia_disp(qt+calculo);
                    ((Apostador) ut).getLista_aposta().get(id-1).setQuantiaganha(calculo);
                }
                }
            }
            System.out.println("Aposta encerrada com sucesso!");
            jogos.get(id-1).setState("concluido",calculo);
        }
        this.menuGeralAdmin(e,usr);
    }
}

    //jogo cancelado pelo administrador
    public void menuCancelarJogo(BetESS e,User usr) throws ParseException{
        ArrayList<Jogo> jogos=e.getLista_jogos();
        if(jogos.isEmpty()){
            System.out.println("Não há jogos disponíveis!");
            this.menuGeralAdmin(e,usr);
        }
        else{
            boolean entrou=false;
            System.out.println("Lista de jogos disponíveis:");
            for(Jogo j:jogos){
                if(j.isEstado().equals("aberto") || j.isEstado().equals("editado")){
                entrou=true;
                System.out.println(j.getId()+"- " + j.getEquipa1()+" vs " + j.getEquipa2()+ "("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+")");
                }
            }
            if(entrou==true){
            System.out.format("Introduza o id do jogo: ");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int id = reader.nextInt();
            jogos.get(id-1).setEstado("cancelado");
            ArrayList<String> aux=jogos.get(id-1).getLista_apostadores();
            TreeMap<String,User> lista=e.getLista_utilizadores();
            User ut;
            float quant_aposta;
            float qt;
            for(String jogador:aux){ //para cada jogador
                ut=lista.get(jogador);
                for(Aposta a:((Apostador) ut).getLista_aposta()){
                    quant_aposta=a.getQuantia();
                    qt=((Apostador) ut).getQuantia_disp();
                    ((Apostador) ut).setQuantia_disp(qt+quant_aposta);
                    ((Apostador) ut).getLista_aposta().get(id-1).setQuantiaganha(0);
                }
            }
            jogos.get(id-1).setState("cancelado",0);
            System.out.println("Aposta cancelada com sucesso!");
            }
            this.menuGeralAdmin(e,usr);
        }
    }
    
    //menu administrador
    public void menuGeralAdmin(BetESS e,User usr) throws ParseException{
        System.out.println("***************** Bem-vindo, "+usr.getUsername()+"! ********************");
        System.out.println("Bem-vindo, "+usr.getUsername()+"!");
        System.out.println("1-Lista de jogos");
        System.out.println("2-Fechar aposta");
        System.out.println("3-Concluir aposta");
        System.out.println("4-Adicionar Bookie");
        System.out.println("5-Cancelar aposta");
        System.out.println("6-Sair");
        System.out.println("**********************************************************");
        System.out.format("Introduza a opção pretendida: ");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int number = reader.nextInt();
        switch(number){
            case 1:
                this.menuJogo(e,usr);
                break;
            case 2:
                this.menuFecharJogo(e, usr);
                break;
            case 3:
                this.menuConcluirJogo(e,usr);
                break;
            case 4:
                this.menuRegisto(e,1);
                break;
            case 5:
                this.menuCancelarJogo(e,usr);
                break;
            case 6:
                System.out.println("Obrigado, esperamos por si!");
                this.menuInicial(e);
                break;
            default:
                break;
        }
    }
    
    public void menuFiltrarJogo(BetESS e, User usr){}
    
    //bookie pretende seguir um jogo
    public void menuSeguirJogo(BetESS e, User usr) throws ParseException {
        boolean hajogo=false;
        ArrayList<Jogo> jogos=e.getLista_jogos();
        ArrayList<Jogo> jogos_bookie=((Bookie) usr).getJogos_interesse();
        if(jogos.isEmpty()){
            System.out.println("Não há jogos disponíveis!");
        }
        else{
            System.out.println("Lista de jogos disponíveis:");
            for(Jogo j:jogos){
                if(jogos_bookie.isEmpty()){
                    hajogo=true;
                    System.out.println(j.getId()+"- " + j.getEquipa1()+" vs " + j.getEquipa2() +" ("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+")");
                }
                else{
                    for(Jogo jb:jogos_bookie){
                        if(j.jogoEqual(jb)==false){
                            hajogo=true;
                            System.out.println(j.getId()+"- " +  j.getEquipa1()+" vs " + j.getEquipa2() +" ("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+")");
                        }
                    }
                }   
            }
            if(hajogo==true){
                System.out.format("Introduza o id do jogo que deseja seguir: ");
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                int number = reader.nextInt();
                Jogo seg=e.getLista_jogos().get(number-1);
                seg.attach(((Bookie)usr).getBk());
                ((Bookie)usr).getJogos_interesse().add(seg);
                System.out.println("Jogo a ser seguido com sucesso!");
            }
            else{
                System.out.println("Não há jogos que possa seguir!");
            }
        this.menuGeralBookie(e, usr);
        }
    }
    
    //jogos criados pelo bookie
    public void menuMJogos(BetESS e,User usr) throws ParseException{
        if(((Bookie)usr).getJogos_criados().isEmpty()){
            System.out.println("Não há jogos disponíveis!");
        }
        else{
            System.out.println("Lista de jogos disponíveis:");
                for(Jogo j:((Bookie)usr).getJogos_criados()){
                       System.out.println(j.getId()+"- " + j.getEquipa1()+" vs " + j.getEquipa2()+ "("+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_vitoria()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_empate()+","+j.getLista_odds().get(j.getLista_odds().size()-1).getOdd_derrota()+") Data e Hora:"+j.getData()+" Status:"+j.isEstado());
                }
        }
        this.menuGeralBookie(e,usr);
    }
    
    //menu bookie
    public void menuGeralBookie(BetESS e,User usr) throws ParseException{
        System.out.println("***************** Bem-vindo, "+usr.getUsername()+"! ********************");
        System.out.println("Bem-vindo, "+usr.getUsername()+"!");
        System.out.println("1-Criar jogos");
        System.out.println("2-Editar jogo");
        System.out.println("3-Os meus jogos");
        System.out.println("4-Filtrar jogos");
        System.out.println("5-Seguir jogo");
        System.out.println("6-Ver caixa de mensagens");
        System.out.println("7-Sair");
        System.out.println("**********************************************************");
        System.out.format("Introduza a opção pretendida: ");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int number = reader.nextInt();
        switch(number){
            case 1:
                this.menuAdicJogo(e,usr);
                break;
            case 2:
                this.menuEditarJogo(e,usr);
                break;
            case 3:
                this.menuMJogos(e,usr);
                break;
            case 4:
                this.menuFiltrarJogo(e,usr);
                break;
            case 5:
                this.menuSeguirJogo(e,usr);
                break;
            case 6:
                this.caixaMsg(e, usr);
                break;
            case 7:
                System.out.println("Obrigado, esperamos por si!");
                this.menuInicial(e);
                break;
            default: break;
        }
    }
    
    //autenticação no sistema
    public void menuLogin(BetESS e) throws ParseException{
        System.out.format("Username: ");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        String n = reader.nextLine();
        System.out.format("Password: ");
        String pass=reader.nextLine();
        TreeMap<String,User> aux=e.getLista_utilizadores();
        User usr;
        if(aux.containsKey(n) && aux.get(n).getPass().equals(pass)){
            usr=aux.get(n);
            if(e.getLista_utilizadores().get(n) instanceof Apostador) this.menuGeral(e,usr);
            if(e.getLista_utilizadores().get(n) instanceof Admin) this.menuGeralAdmin(e,usr);
            if(e.getLista_utilizadores().get(n) instanceof Bookie) this.menuGeralBookie(e,usr);
        }
        else {
            System.out.println("Utilizador ou password inválidos");
            this.menuInicial(e);
        }
    }
    
    //menu inicial BetESS
    public void menuInicial(BetESS e) throws ParseException{
        System.out.println("***************** BetESS - Bem vindo! ********************");
        System.out.println("1-Login");
        System.out.println("2-Registo");
        System.out.println("3-Sair");
        System.out.println("**********************************************************");
        System.out.format("Introduza a opção pretendida: ");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int n = reader.nextInt();
        switch(n){
            case 1: this.menuLogin(e);
                    break;
            case 2: this.menuRegisto(e,0);
                    break;
            default: break;
        }
    }
}
