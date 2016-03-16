require_relative 'bookie'
require_relative 'BetESS'
require_relative 'Menu_Bookie'
require_relative 'Menu_Admin'
require_relative 'Menu_Apostador'

current_folder = File.expand_path('../', __FILE__) # get absolute directory
Dir['#{current_folder}/**/*.rb'].each { |f| require f }

class Main


  attr_accessor :contador_id_evento
  attr_accessor :bet_ess

  # método que incializa todas as variáveis e estruturas de dados necessários
  def initialize
    @bet_ess = BetESS.new
    @bet_ess.registar_admin('admin', 'pass', 'zeArtolas')
    book = Bookie.new('book', '123', 'ze')
    # book2 = Bookie.new('b','123','ze')
    @bet_ess.registar_bookie('book', '123', 'ze')
    @bet_ess.registar_bookie('b', '123', 'ze')
    @bet_ess.registar_apostador("ze", "123", "ze", "20")


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

    if @bet_ess.exist_user(email)
      bookie = @bet_ess.get_user(email)
      if bookie.is_a?(Bookie) and bookie.password==pass
        menu_bookie= MenuBookie.new(bookie, @bet_ess)
        menu_bookie.start
      else
        puts 'sem autorização'
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
    if @bet_ess.exist_user(email)
      apostador = @bet_ess.get_user(email)
      if apostador.is_a?(Apostador) and apostador.password==pass
        m_apostador= MenuApostador.new(apostador, @bet_ess)
        m_apostador.start

      else
        puts 'Sem autorização'
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
    if @bet_ess.exist_user(email)
      admin = @bet_ess.get_user(email)
      if admin.is_a?(Admin) and admin.password==pass
        menu_admin= Menu_Admin.new(admin, @bet_ess)
        menu_admin.start
      else
        puts 'sem autorização'
      end
    else
      puts 'Não está registado'
    end
  end

  # método que imprime todos os utilizadores do sistema
  def print_utilzadores
    @utilizadores.each { |k, v| puts " chave #{k}, Valor #{v.getNome}" }
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
    valor = gets.chomp.to_f
    @bet_ess.registar_apostador(email, pass, nome, valor)

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


menu = Main.new
menu.menu_principal



