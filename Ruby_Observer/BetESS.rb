require_relative 'Admin'
require_relative 'Bookie'
require_relative 'Apostador'
require_relative 'Evento'

class BetESS
  @@users = {}
  @@users["admin"] = Admin.new('admin@g.com', 'pass', 'zeArtolas')
  @@eventos = {}

  def self.registarBookie(email, password, nome)
    return nil if @@users[email]
    bookie = Bookie.new(nome, password, email)
    @@users[bookie.email] = bookie
  end

  def self.addEvento(evento, bookiemail)
    bookie = user = @@users[bookiemail]
    if !bookie || !bookie.is_a?(Bookie)
    else
      id = @@eventos.length
      evento.id=id
      evento.add_observer(bookie)
      @bookie.novo_evento(evento.id)
      @@eventos[id] = evento

    end
  end

  def self.registarApostador(apostador)
    return nil if @@users[apostador.email]
    @@users[apostador.email] = apostador
  end

  def self.setOddEvento(id, odd_v, odd_e, odd_d)
    @@eventos[id].set_odd(odd_v, odd_e, odd_d)
    @@eventos[id].notify_observer_odd
  end

  def self.existEvento(id)
    return @@eventos.include?(id)
  end

  def self.registaInteresse(id,bookie)
    if @@eventos[id].estado
      then
      @@eventos[id].add_observer(bookie)
      return true
    else
      return false
    end
  end

  def self.getEventos
    @@eventos.values
  end


end