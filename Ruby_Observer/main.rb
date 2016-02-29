class Main
  def menu_registar_apostador
    p "************************************************"
    p "*               Registar Apostador             *"
    p "************************************************"
    p "Insira um email: "
    email = gets.chop
    p "Insira o nome: "
    nome = gets.chop
    p "Insira uma palavra passe"
    pass = gets.chop
    p "Insira a quantia para as apostas"
    valor = gets.chop
  end


end
menu = Main.new
menu.menu_registar_apostador
