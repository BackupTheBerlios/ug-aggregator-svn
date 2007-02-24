require 'test/unit'
require 'test/fixture/calcola_percento'

class TestCalcolaPercento < Test::Unit::TestCase
  def test_simple
    calcola = CalcolaPercento.new
    calcola.percentuale = 15
    calcola.importo = 100
    assert_equal 15, calcola.percento?
  end
end
