require_relative 'bookie'

current_folder = File.expand_path('../', __FILE__) # get absolute directory
Dir['#{current_folder}/**/*.rb'].each { |f| require f }

class Main

  attr_accessor :utilizadores
  attr_accessor :contador_id_evento

  # método que incializa todas as variáveis e estruturas de dados necessários
  def initialize
    @utilizadores = Hash.new
    @contador_id_evento = 0

  end

  # método que apresenta o menu principal do programa
  def menu_principal
    while true do
      puts '************************************************'
      puts '*                    BetESS                    *'
      puts '************************************************'

      puts 'Escolha uma das seguintes opções'
      puts '1-Registar'
      puts '2-Entrar'
      puts '3-Sair'
      opt = gets.chomp


      menu_principal if opt > '3'


      case opt
        when '1' then
          menu_registar_apostador
        when '2' then
          puts '************************************************'
          puts '1-Admin'
          puts '2-Utilizador Regular'
          puts '3-Bookie'
          opt= gets.chop
          if opt == '1'
            menu_admin
          elsif opt == '2'
            menu_user
          elsif opt == '3'
            menu_bookie
          end
        else
          exit(0)
      end

    end
  end

  # método de login de um Bookie
  def menu_bookie
    p 'Insira um email: '
    email=gets.chomp
    p 'Insira a sua palavra passe'
    pass= gets.chomp
    if @utilizadores.key?(email)
      bookie = @utilizadores[email]
      if bookie.is_a?(Bookie)
        puts 'ok'
        MenuBookie.start
      else
        puts 'not ok'
      end
    else
      puts 'Não está registado'
    end
  end

  # método de login de um Apostador
  def menu_user
    p 'Insira um email: '
    email=gets.chomp
    p 'Insira a sua palavra passe'
    pass= gets.chomp
    if @utilizadores.key?(email)
      apostador = @utilizadores[email]
      if apostador.is_a?(Apostador)
        puts 'ok'
      else
        puts 'not ok'
      end
    else
      puts 'Não está registado'
    end
  end

  # método de login de um Administrador
  def menu_admin
    p 'Insira um email: '
    email=gets.chomp
    p 'Insira a sua palavra passe'
    pass= gets.chomp
    if @utilizadores.key?(email)
      admin = @utilizadores[email]
      if admin.is_a?(Admin)
        puts 'ok'
      else
        puts 'not ok'
      end
    else
      puts 'Não está registado'
    end
  end

  # método que imprime todos os utilizadores do sistema
  def print_utilzadores
    @utilizadores.each { |k, v| puts ' chave #{k}, Valor #{v.getNome}' }
  end

  # método que regista um apostador
  def menu_registar_apostador
    p '************************************************'
    p '*               Registar Apostador             *'
    p '************************************************'
    p 'Insira um email: '
    email = gets.chomp
    p 'Insira o nome: '
    nome = gets.chomp
    p 'Insira uma palavra passe'
    pass = gets.chomp
    p 'Insira a quantia para as apostas'
    valor = gets.chomp
    apos = Apostador.new(email, pass, nome, valor)
    @utilizadores[apos.get_email]=apos
    #print_utilzadores
  end

  # método que incrementa o id de um dado evento
  def self.add_contador_evento
    @contador_id_evento+=1
  end

  # método que retorna o id de um dado evento
  def self.get_contador_evento
    return @contador_id_evento
  end


end


#=begin
menu = Main.new
book = Bookie.new('raul','123','raul@g.com')
#book= Bookie.new('raul','123','raul@g.com')
menu.utilizadores[book.get_email]=book

menu.menu_principal


=begin
menu.menu_registar_apostador

menu.print_utilzadores
=end
