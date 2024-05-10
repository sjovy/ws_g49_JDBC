package se.sjovy;

import java.sql.Connection;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        Connection connection = DatabaseHelper.openConnection();
        if (connection != null) {
            System.out.println("Successfully connected to the database!");
            CityDao cityDao = new CityDaoJDBC(connection);

            // Use the addCity method
            City newCity = new City("New City", "SWE", "New District", 1);
            int newCityId = cityDao.addCity(newCity);
            if (newCityId > 0) {
                System.out.println("New city added successfully with ID: " + newCityId);
            } else {
                System.out.println("Failed to add the new city.");
            }

            // Use the findById method
            /*int cityId = 4000; // replace with the id of the city you want to find
            City city = cityDao.findById(cityId);
            if (city != null) {
                printCity(city);
            } else {
                System.out.println("No city found with ID: " + cityId);
            }*/

            // Use the findByCode method
            /*String countryCode = "SWE"; // replace with the country code you want to find
            List<City> citiesByCode = cityDao.findByCode(countryCode);
            for (City eachCity : citiesByCode) {
                printCity(eachCity);
            }*/

            // Use the findByName method
            /*String cityName = "Stockholm"; // replace with the name of the city you want to find
            List<City> citiesByName = cityDao.findByName(cityName);
            for (City eachCity : citiesByName) {
                printCity(eachCity);
            }*/

            // Use the findAll method
            /*List<City> cities = cityDao.findAll();
            for (City eachCity : cities) {
                printCity(eachCity);
            }*/

            // Use the update method (city to update from findById)
            /*city.setName("Stockholm"); // replace with the new name of the city
            city.setCountryCode("SWE"); // replace with the new country code
            city.setDistrict("Stockholms län"); // replace with the new district
            city.setPopulation(1720000); // replace with the new population
            boolean isUpdated = cityDao.update(city);
            if (isUpdated) {
                System.out.println("City updated successfully!");
            } else {
                System.out.println("Failed to update the city.");
            }*/

            // Use the delete method (city to update from findById)
            /*boolean isDeleted = cityDao.delete(city);
            if (isDeleted) {
                System.out.println("City deleted successfully!");
            } else {
                System.out.println("Failed to delete the city.");
            }
            DatabaseHelper.closeConnection();*/

        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    private static void printCity(City city) {
        System.out.println(city);
    }

}