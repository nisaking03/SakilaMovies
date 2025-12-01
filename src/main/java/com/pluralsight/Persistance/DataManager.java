package com.pluralsight.Persistance;

import com.pluralsight.Models.Category;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private BasicDataSource ds;

    public DataManager(BasicDataSource ds) {
        this.ds = ds;
    }

    public List<Category> getAllCategories() throws SQLException {

        // List to store all retrieved categories
        List<Category> categories = new ArrayList<>();

        String query = """
                    SELECT
                    category_id,
                    name
                    FROM category
                    """;

        // Try-with-resources block: automatically closes all resources even if exceptions occur
        try(
                // Get a connection
                Connection connection = this.ds.getConnection();

                // Prepare the SQL statement for execution
                PreparedStatement statement = connection.prepareStatement(query);

                // Execute query and get results
                ResultSet results = statement.executeQuery();
        ){
            // Loop through each row in the result set
            while(results.next()){

                // Extract values from the current row
                int id = results.getInt("category_id");
                String name = results.getString("name");

                // Create a Category object from the database values
                Category c = new Category(id, name);

                // Add to the collection
                categories.add(c);
            }

        }
        return categories;
    }
}