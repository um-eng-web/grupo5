require_relative 'evento'
require_relative 'bookie'
require_relative 'BetESS'
require 'time'


class MenuBookie

  def initialize(bookie, betEss)
    @betEss=betEss
    @bookie=bookie
    @flag = true
  end

  def start
    while @flag do
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
          listar_final_apostas ######### ver aqui isto tudo
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


  end

  def registar_aposta

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
    @betEss.addEvento(evento, @bookie)

    #p "Evento = #{evento.data_init}, mais #{evento.descricao} mais #{evento.estado}"

    #a.add_observer(@bookie)

  end

  def editar_aposta
    p 'Insira Id da aposta a alterar'
    id = gets.chomp
    if @bookie.criou_evento(id.to_i)
    then
      p 'Odd para a equipa 1'
      odd1 = gets.chomp.to_f
      p 'Odd para a equipa 2'
      odd2 = gets.chomp.to_f
      p 'Odd para o empate'
      empate = gets.chomp.to_f
      @betEss.setOddEvento(id, odd1, empate, odd2)
    else
      p 'Não tem permissões para alterar'
    end
  end


  def registar_interesse
    p 'Insira Id da aposta que deseja registar interesse'
    id = gets.chomp
    if @betEss.existEvento(id.to_i)
    then
      if @betEss.registaInteresse(id, @bookie)
        p "registado o interese"

      else
        p 'Aposta fechada'
      end
    end
  end


  def listar_apostas
    eventos = @betEss.getEventos
    eventos.each do |evento|
      if evento.estado


        if !@bookie.eventos_criados.empty?
          if !@bookie.eventos_criados.include?(evento.id.to_s) and !@bookie.eventos_interesse.include?(evento.id.to_s)

            p "#{evento.to_s}"
          end
        elsif !@bookie.eventos_interesse.include?(evento.id.to_s)

          p "#{evento.to_s}"

        end

      end

    end
  end

  def listar_notificacoes_odd

    if @bookie.not_odd.empty?
      p 'Não existem alterações'
    else
      p ' Novas odds nos seguintes eventos:'
      @bookie.not_odd.each do |idevento|
        p " #{@betEss.getEventos[idevento].to_s}"

      end

    end
  end

  def listar_final_apostas

    if !@bookie.resultados_eventos.empty?
      @bookie.resultados_eventos.each do |id, info|
        p "#{info}"
      end
    end
  end

  def limpar_nofificacoes
    @bookie.limpaNoficacoesOdd
  end


end

=begin
book = Bookie.new('raul', '123', 'raul@g.com')
book2 = Bookie.new('raul2', '123', 'raul2@g.com')
betEss = BetESS.new
betEss.registarBookie('raul@g.com', '123', 'raul')
betEss.registarBookie('raul2@g.com', '123', 'raul2')
even= Evento.new(0, "des", "1999-12-22 12:12:12", 1.1, 2.2, 1.1, "slb", "fcp")
even2= Evento.new(1, "des", "1999-12-22 12:12:12", 1.1, 2.2, 1.1, "zero", "um")
betEss.addEvento(even, book)
betEss.addEvento(even2, book2)

book.update(0,"odd")
menu = MenuBookie.new(book, betEss)
menu.start
=end