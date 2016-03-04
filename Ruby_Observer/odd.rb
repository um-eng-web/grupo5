current_folder = File.expand_path('../', __FILE__) # get absolute directory
Dir["#{current_folder}**/*.rb"].each {|f| require f}

class Odd


  attr_reader :odd_e, :odd_d, :odd_v

  def self.init()
    @odd_v = 0.0
    @odd_e = 0.0
    @odd_d = 0.0
  end

  def self.initialize(odd_vitoria, odd_empate, odd_derrota)
    @odd_v=odd_vitoria
    @odd_e=odd_empate
    @odd_d=odd_derrota
  end

  def self.get_odd_v
    return @odd_v
  end

  def self.get_odd_e
    return @odd_e
  end

  def self.get_odd_d
    return @get_odd_d
  end

  def self.set_odd_v(odd_vitoria)
    @odd_v = odd_vitoria
  end

  def self.set_odd_e(odd_empate)
    @odd_e = odd_empate
  end

  def self.set_odd_d(odd_derrota)
    @odd_d = odd_derrota
  end

end