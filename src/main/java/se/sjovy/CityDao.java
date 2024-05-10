package se.sjovy;

import java.util.List;

public interface CityDao {
    int addCity(City city);
    List<City> findAll();
    City findById(int id);
    List<City> findByCode(String code);
    List<City> findByName(String name);
    boolean update(City city);
    boolean delete(City city);
}
