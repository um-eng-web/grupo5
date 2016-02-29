class MenuBookie

  def initialize(bookie)
    @bookie=bookie
  end
  def start
    p "************************************************"
    p "*                    BOOKIE                    *"
    p "************************************************"
    p "Escolha a opção"
    p "1-Registar nova Aposta"
    p "2-Editar Odd de Aposta"
    p "3-Registar Interesse "
    p "4-Listar Apostas Diponiveis para Registar Interesse"
    p "5-Listar Resultado Final das Apostas com Interesse"
    p "6-Listar Notificações de Odds de Apostas Alteradas"
    p "7-Limpar Notificações"
    p "8-Sair"
    opt = gets.chomp

    case opt
      when "1" then
        p "************************************************"
        registar_aposta
      when "2" then
        p "************************************************"
        editar_aposta
      when "3" then
        p "************************************************"
        registar_interesse
      when "4" then
        p "************************************************"
        listar_apostas
      when "5" then
        p "************************************************"
        listar_final_apostas
      when "6" then
        p "************************************************"
        listar_notificacoes_odd
      when "7" then
        p "************************************************"
        limpar_nofificacoes


    end


  end

  def registar_aposta


  end

  def editar_aposta

  end
end