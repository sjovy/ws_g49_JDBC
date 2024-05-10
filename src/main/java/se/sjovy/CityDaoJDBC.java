package se.sjovy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {
    private Connection connection;

    public CityDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addCity(City city) {
        int id = getMaxId() + 1;
        String SQL_INSERT = "INSERT INTO city (ID, Name, CountryCode, District, Population) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, city.getName());
            preparedStatement.setString(3, city.getCountryCode());
            preparedStatement.setString(4, city.getDistrict());
            preparedStatement.setInt(5, city.getPopulation());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                id = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private int getMaxId() {
        int maxId = 0;
        String SQL_MAX_ID = "SELECT MAX(ID) FROM city";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_MAX_ID)) {

            if (resultSet.next()) {
                maxId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxId;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        String SQL_FIND_ALL = "SELECT * FROM city";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String countryCode = resultSet.getString("CountryCode");
                String district = resultSet.getString("District");
                int population = resultSet.getInt("Population");

                City city = new City(id, name, countryCode, district, population);
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public City findById(int id) {
        City city = null;
        String SQL_FIND_BY_ID = "SELECT * FROM city WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int cityId = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String countryCode = resultSet.getString("CountryCode");
                String district = resultSet.getString("District");
                int population = resultSet.getInt("Population");

                city = new City(cityId, name, countryCode, district, population);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cities = new ArrayList<>();
        String SQL_FIND_BY_CODE = "SELECT * FROM city WHERE CountryCode = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CODE)) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String countryCode = resultSet.getString("CountryCode");
                String district = resultSet.getString("District");
                int population = resultSet.getInt("Population");

                City city = new City(id, name, countryCode, district, population);
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cities = new ArrayList<>();
        String SQL_FIND_BY_NAME = "SELECT * FROM city WHERE Name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String cityName = resultSet.getString("Name");
                String countryCode = resultSet.getString("CountryCode");
                String district = resultSet.getString("District");
                int population = resultSet.getInt("Population");

                City city = new City(id, cityName, countryCode, district, population);
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public boolean update(City city) {
        boolean rowUpdated = false;
        String SQL_UPDATE = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(City city) {
        boolean rowDeleted = false;
        String SQL_DELETE = "DELETE FROM city WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, city.getId());

            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return rowDeleted;
}
}