require_relative 'betEss'
require_relative 'evento'
require_relative 'bookie'
require_relative 'admin'

class Menu_Admin


  def initialize(admin,betEss)
    @betEss=betEss
    @admin=admin
    @flag = true
  end

  def start
    while @flag do
    p '************************************************'
    p '*                    ADMIN                    *'
    p '************************************************'
    p 'Escolha a opção'
    p '1-Registar Bookie'
    p '2-Fechar Aposta'
    p '3-Concluir Aposta'
    p '4-Listar Apostas'
    p '5-Sair'
    opt = gets.chomp

    case opt
      when '1' then
        p '************************************************'
        registar_bookie
      when '2' then
        p '************************************************'
        fechar_aposta
      when '3' then
        p '************************************************'
        concluir_aposta
      when '4' then
        p '************************************************'
        listar_apostas
      when '5' then
        p '************************************************'
        @flag = false

    end
    end

  end

  def registar_bookie
    p'************************************************'
    p'*               Registar Bookie                *'
    p'************************************************'
    p 'Insira um email: '
    email = gets.chomp
    p 'Insira o nome'
    nome = gets.chomp
    p ' Insira a palavra passe'
    pass = gets.chomp
    @betEss.registarBookie(email,pass,nome)

  end

  def fechar_aposta
    p' Introduza o id da aposta a fechar'
    id = gets.chomp
    @betEss.fechaEvento(id)

  end

  def concluir_aposta
    p' Introduza o id da aposta a fechar'
    id = gets.chomp
    p'0-Empate'
    p'1-Equipa 1'
    p'2-Equipa 2'
    res=gets.chomp
   @betEss.concluirEvento(id,res)

  end

  def listar_apostas
    @betEss.getEventos.each do |evento|
      p "# Evento # #{evento.to_s}"
    end
  end
end

=begin
admin= Admin.new("admin","admin","admin")
book = Bookie.new('raul', '123', 'raul@g.com')
betEss = BetESS.new
betEss.registarBookie('raul@g.com', '123','raul' )
even= Evento.new(1,"des","1999-12-22 12:12:12",1.1,2.2,1.1,"slb","fcp")
betEss.addEvento(even,'raul@g.com')

menu = Menu_Admin.new(admin,betEss)
menu.start
=end


