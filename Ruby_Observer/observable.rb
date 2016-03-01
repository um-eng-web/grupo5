interface :Observable do
  def add_observer(o); end
  def remove_observer(o); end
  def notify_observer(resultado); end
  def notify_observer_odd(); end
end