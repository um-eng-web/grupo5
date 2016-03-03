require './Bookie'
require './Evento'
require './Main'

class MenuBookie

  def initialize(bookie)
    @bookie=bookie
    @flag = true
  end

  def self.start
    p "************************************************"
    p "*                    BOOKIE                    *"
    p "************************************************"
    p "Escolha a opção"
    p "1-Registar nova Aposta"
    p "2-Editar Odd de Aposta"
    p "3-Registar Interesse "
    p "4-Listar Apostas Diponiveis para Registar Interesse"
    p "5-Listar Resultado Final das Apostas com Interesse"
    p "6-Listar Notificações de Odds de Apostas Alteradas"
    p "7-Limpar Notificações"
    p "8-Sair"
    opt = gets.chomp

    case opt
      when "1" then
        p "************************************************"
        registar_aposta
      when "2" then
        p "************************************************"
        editar_aposta
      when "3" then
        p "************************************************"
        registar_interesse
      when "4" then
        p "************************************************"
        listar_apostas
      when "5" then
        p "************************************************"
        listar_final_apostas
      when "6" then
        p "************************************************"
        listar_notificacoes_odd
      when "7" then
        p "************************************************"
        limpar_nofificacoes
      when "8" then
        p "************************************************"
        @flag = false


    end


  end

  def self.registar_aposta
  require 'time'
  p "Descrição do Evento"
  des = gets.chomp
  p "Data do evento (dd/MM/yyyy)"
  str = gets.chomp
  p "Hora do evento (HH:mm)"
  str2 = gets.chomp
  str = str + " " + str2
  date = Time.parse(str)
  p "Nome da equipa 1"
  eq1 = gets.chomp
  p "Nome da equipa 2"
  eq2 = gets.chomp
  p "Odd para a equipa 1"
  odd1 = gets.chomp
  p "Odd para a equipa 2"
  odd2 = gets.chomp
  p "Odd para o empate"
  empate = gets.chomp

  a = Evento.initialize(Main.get_contador_evento,des,date,odd1,odd2,empate,eq1,eq2)
  Main.add_contador_evento
  @bookie.novo_evento(a.get_id)
  #bd.addEvento(a.getId(), a);
  a.add_observer(@bookie)

  end

  def editar_aposta

  end
end