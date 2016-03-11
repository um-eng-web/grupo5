require_relative 'Admin'
require_relative 'Bookie'
require_relative 'Apostador'
require_relative 'Evento'


class BetESS
  def initialize
    @users = Hash.new
    @users["admin"] = Admin.new('admin@g.com', 'pass', 'zeArtolas')
    @eventos = Hash.new
  end


  def registarBookie(email, password, nome)
    return nil if @users[email]
    bookie = Bookie.new(nome, password, email)
    @users[email] = bookie


  end


  def fechaEvento(id)
    @eventos[id.to_i].estado=false
  end

  def concluirEvento(id, resultado)
    @eventos[id.to_i].set_resultado(resultado)
    @eventos[id.to_i].notify_observers(resultado)

  end

  def addEvento(evento, bookiemail)
    bookie = user = @users[bookiemail]
    if !bookie || !bookie.is_a?(Bookie)
    else
      id = @eventos.length
      evento.id=id
      evento.add_observer_evento(bookie)
      bookie.novo_evento(evento.id)
      @eventos[id.to_i] = evento


    end
  end

  def registarApostador(apostador)
    return nil if @users[apostador.email]
    @users[apostador.email] = apostador
  end

  def setOddEvento(id, odd_v, odd_e, odd_d)
    @eventos[id.to_i].set_odd(odd_v, odd_e, odd_d)
    @eventos[id.to_i].notify_observer_odd
  end

  def existEvento(id)
    return @eventos.include?(id.to_i)
  end

  def registaInteresse(id, bookie)
    if @eventos[id.to_i].estado
    then
      @eventos[id.to_i].add_observer(bookie)
      return true
    else
      return false
    end
  end

  def getEventos
    @eventos.values
  end


end