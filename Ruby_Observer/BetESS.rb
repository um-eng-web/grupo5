require_relative 'Admin'
require_relative 'Bookie'
require_relative 'Apostador'
require_relative 'Evento'

class BetESS
  @@users = {}
  @@users["admin"] = Admin.new('admin@g.com', 'pass', 'zeArtolas')
  @@eventos = {}

  def self.registarBookie(bookie)
    return nil if @@users[bookie.email]
    @@users[bookie.email] = bookie


  end

  def self.addEvento(evento, bookiemail)
    p "entrou"
    bookie = user = @@users[bookiemail]
    if !bookie || !bookie.is_a?(Bookie)

    else
      id = @@eventos.length
      evento.id=id
      @@eventos[id] = evento
    end

    p "vai por bookiiii = #{@@eventos[id].descricao}"
  end

  def self.registarApostador(apostador)
    return nil if @@users[apostador.email]

    @@users[apostador.email] = apostador
  end

end