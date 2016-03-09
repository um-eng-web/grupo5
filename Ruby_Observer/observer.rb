=begin interface :Observer do
  def update(id_evento,evento,resultado); end
  def update_odd(id_evento); end
end


####
=end
class Observer
  def initialize(observable)
    observable.add_observer(self)
  end

  def update(counter)
    puts "Count has increased by: #{counter}"
  end
end