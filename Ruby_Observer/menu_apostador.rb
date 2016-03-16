require_relative 'apostador'
require_relative 'aposta'
require_relative 'BetESS'
require_relative 'evento'
require 'time'


class MenuApostador

  def initialize(apostador, bet_ess)
    @bet_ess=bet_ess
    @apostador=apostador
    @flag = true
  end

  def start
    while @flag do
      p '************************************************'
      p '*                  APOSTADOR ' + "#{@apostador.nome}" + '              *'
      p '************************************************'
      p 'Escolha a opção'
      p '1-Listar Apostas'
      p '2-Apostar'
      p '3-Ganho em apostas fechadas'
      p '4-Valor disponível para apostar'
      p '5-Listar Notificações'
      p '6-Limpar Notificações'
      p '7-Sair'
      opt = gets.chomp

      case opt
        when '1' then
          p '************************************************'
          listar_apostas
        when '2' then
          p '************************************************'
          fazer_aposta
        when '3' then
          p '************************************************'
          ganho_apostas
        when '4' then
          p '************************************************'
          p 'Valor: ' + "#{@apostador.valor}"
        when '5' then
          p '************************************************'
          listar_notificacoes_odd
        when '6' then
          p '************************************************'
          limpar_nofificacoes
        when '7' then
          p '************************************************'
          @flag = false

      end
    end
  end

  def listar_apostas
    eventos = @bet_ess.get_eventos
    eventos.each do |evento|
      if evento.estado
        p "#{evento.to_s}"
      end
    end
  end

  def fazer_aposta
    p 'Id da aposta'
    id = gets.chomp.to_i
    if @bet_ess.exist_evento(id)
      p 'Qual o resultado a apostar:'
      p '0 - Empate'
      p '1 - Equipa 1'
      p '2 - Equipa 2'
      aposta = gets.chomp
      p 'Valor a apostar:'
      valor = gets.chomp.to_f
      if @apostador.valor.to_f < valor
        p 'Não tem valor disponível para essa aposta'
      else
        @apostador.set_valor(@apostador.valor.to_f - valor)
        @bet_ess.get_eventos[id].add_observer(@apostador)
        ap = Aposta.new(@apostador.contador_id_aposta, id, aposta, valor, @bet_ess.get_eventos[id].odd.odd_v, @bet_ess.get_eventos[id].odd.odd_d, @bet_ess.get_eventos[id].odd.odd_e, nil)
        @apostador.add_aposta(ap)
        p 'Registado'
      end
    else
      p 'Aposta fechada'
    end
  end

  def ganho_apostas
    if @apostador.lista_apostas.empty?
      p 'Não existem Apostas'
    else
      @apostador.lista_apostas.values.each do |aposta|
        if aposta.fechada
          ap = aposta
          p 'Ganho: ' + "#{ap.ganho}"
        end
      end
    end
  end

  def listar_notificacoes_odd

    if @apostador.not_odd.empty?
      p 'Não existem alterações'
    else
      p ' Novas odds nos seguintes eventos:'
      @apostador.not_odd.each do |idevento|
        p " #{@bet_ess.get_eventos[idevento].to_s}"

      end

    end
  end

  def limpar_nofificacoes
    @apostador.limpa_noficacoes_odd
  end

end

