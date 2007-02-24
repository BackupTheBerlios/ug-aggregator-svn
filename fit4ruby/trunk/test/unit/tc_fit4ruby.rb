require 'test/unit'
require 'lib/fit4ruby'

class TestFit4Ruby < Test::Unit::TestCase

  def setup
    @test_csv = <<-END
      test/fixture/calcola_percento.rb
      CalcolaPercento
      percentuale, importo, percento?
      15, 100, 15
      30, 200, 60
      50, 200, 1
    END
  end

  def test_can_load_class
    fit = Fit4Ruby.new
    fit.load 'test/fixture/calcola_percento.rb', 'CalcolaPercento'
    assert_equal('CalcolaPercento', fit.nameLoadedClass?, 'class CalcolaPercento should be loaded')
    assert_not_nil CalcolaPercento.new
  end

  def test_can_call_methods
    fit = Fit4Ruby.new
    fit.load 'test/fixture/calcola_percento.rb', 'CalcolaPercento'
    fit.set 'percentuale', 15
    fit.set 'importo', 100
    assert_equal 15, fit.call('percento?')
  end

  def test_can_parse_csv
    fit = Fit4Ruby.new
    fit.parse @test_csv

    assert_equal 'CalcolaPercento', fit.nameLoadedClass?
    assert_equal 3, fit.number_of_tests?
  end

  def test_can_run_test
    fit = Fit4Ruby.new
    fit.parse @test_csv

    assert_equal 0, fit.number_of_succeded_tests?
    assert_equal 0, fit.number_of_failed_tests?

    fit.run
    assert_equal 2, fit.number_of_succeded_tests?
    assert_equal 1, fit.number_of_failed_tests?
  end

end
