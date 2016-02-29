current_folder = File.expand_path('../', __FILE__) # get absolute directory
Dir["#{current_folder}**/*.rb"].each {|f| require f}

class Main

  attr_accessor :utilizadores

  def initialize
    @utilizadores = Hash.new

  end


  def menu_principal
    while true do
      puts "************************************************"
      puts "*                    BetESS                    *"
      puts "************************************************"

      puts "Escolha uma das seguintes opções"
      puts "1-Registar"
      puts "2-Entrar"
      puts "3-Sair"
      opt = gets.chomp


      menu_principal if opt > "3"


      case opt
        when "1" then
           menu_registar_apostador
        when "2" then
          puts"************************************************"
          puts "1-Admin"
          puts "2-Utilizador Regular"
          puts "3-Bookie"
          opt= gets.chop
          if opt == "1"
            menu_admin
          elsif opt == "2"
            menu_user
          elsif opt == "3"
            menu_bookie
          end
        else
         exit(0)
      end

    end
  end

  def menu_bookie
    p "Insira um email: "
    email=gets.chomp
    p "Insira a sua palavra passe"
    pass= gets.chomp
    unless @utilizadores.key?(email)
      puts "Não está registado"
    else
      bookie = @utilizadores[email]
      if bookie.is_a?(Bookie)
        puts "ok"
      else
        puts "not ok"
      end
    end


  end

  def menu_user
    # code here
  end

  def menu_admin
    # code here
  end

  def print_utilzadores
    @utilizadores.each { |k, v| puts " chave #{k}, Valor #{v.getNome}"}

  end
  def menu_registar_apostador
    p "************************************************"
    p "*               Registar Apostador             *"
    p "************************************************"
    p "Insira um email: "
    email = gets.chomp
    p "Insira o nome: "
    nome = gets.chomp
    p "Insira uma palavra passe"
    pass = gets.chomp
    p "Insira a quantia para as apostas"
    valor = gets.chomp
    apos = Apostador.new(email,pass,nome,valor)
    @utilizadores[apos.getEmail]=apos
    #print_utilzadores

  end




end


#=begin
menu = Main.new
book= Bookie.new("raul","123","raul@g.com")
menu.utilizadores[book.getEmail]=book

menu.menu_principal


=begin
menu.menu_registar_apostador

menu.print_utilzadores
=end
