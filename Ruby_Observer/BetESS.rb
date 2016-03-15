require_relative 'Admin'
require_relative 'Bookie'
require_relative 'Apostador'
require_relative 'Evento'


class BetESS
  def initialize
    @users = Hash.new
    #@users["admin"] = Admin.new('admin@g.com', 'pass', 'zeArtolas')
    @eventos = Hash.new
  end

  def existUser(email)
    (@users[email]) ? true : false
  end

  def getUser(email)
    @users[email]
  end


  def registarBookie(email, password, nome)
    return nil if @users[email]
    bookie = Bookie.new(nome, password, email)
    @users[email] = bookie

  end


  def registarAdmin(email, password, nome)
    admin = Admin.new(nome, password, email)
    @users[email] = admin


  end

  def registarApostador(email, password, nome, valor)
    apos = Apostador.new(nome, password, email, valor)
    @users[email] = apos
  end


  def fechaEvento(id)
    @eventos[id.to_i].estado=false
  end

  def concluirEvento(id, resultado)
    @eventos[id.to_i].set_resultado(resultado)
    @eventos[id.to_i].notify_observers_resultado(resultado)

  end

  def addEvento(evento, bookie)
    #bookie = user = @users[bookiemail]
    if !bookie || !bookie.is_a?(Bookie)
    else
      id = @eventos.length
      evento.id=id
      evento.add_observer(bookie)
      bookie.novo_evento(evento.id)
      @eventos[id.to_i] = evento


    end
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
      bookie.addInteresse(id)
      return true
    else
      return false
    end
  end

  def getEventos
    @eventos.values
  end


end
