require_relative 'evento'
require_relative 'bookie'
require_relative 'BetESS'
require 'time'


class MenuBookie

  def initialize(bookie)

    @bookie=bookie
    @flag = true
  end

  def start
    p '************************************************'
    p '*                    BOOKIE                    *'
    p '************************************************'
    p 'Escolha a opção'
    p '1-Registar nova Aposta'
    p '2-Editar Odd de Aposta'
    p '3-Registar Interesse '
    p '4-Listar Apostas Diponiveis para Registar Interesse'
    p '5-Listar Resultado Final das Apostas com Interesse'
    p '6-Listar Notificações de Odds de Apostas Alteradas'
    p '7-Limpar Notificações'
    p '8-Sair'
    opt = gets.chomp

    case opt
      when '1' then
        p '************************************************'
        registar_aposta
      when '2' then
        p '************************************************'
        editar_aposta
      when '3' then
        p '************************************************'
        registar_interesse
      when '4' then
        p '************************************************'
        listar_apostas
      when '5' then
        p '************************************************'
        listar_final_apostas
      when '6' then
        p '************************************************'
        listar_notificacoes_odd
      when '7' then
        p '************************************************'
        limpar_nofificacoes
      when '8' then
        p '************************************************'
        @flag = false

    end

  end

  def registar_aposta
    require 'time'
    p 'Descrição do Evento'
    des = gets.chomp
    p 'Data e hora do evento YYYY-MM-dd HH:mm:ss'
    time = Time.parse(gets.chomp)
    p 'Nome da equipa 1'
    eq1 = gets.chomp
    p 'Nome da equipa 2'
    eq2 = gets.chomp
    p 'Odd para a equipa 1'
    odd1 = gets.chomp.to_f
    p 'Odd para a equipa 2'
    odd2 = gets.chomp.to_f
    p 'Odd para o empate'
    empate = gets.chomp.to_f


    evento = Evento.new(1, des, time, odd1, odd2, empate, eq1, eq2)
    @bookie.novo_evento(evento.id)
    BetESS.addEvento(evento, @bookie.email)

    #p "Evento = #{evento.data_init}, mais #{evento.descricao} mais #{evento.estado}"

    #a.add_observer(@bookie)

  end

  def editar_aposta

  end
end


book = Bookie.new('raul', '123', 'raul@g.com')
BetESS.registarBookie(book)
menu = MenuBookie.new(book)
menu.start