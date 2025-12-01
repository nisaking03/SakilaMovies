package com.pluralsight.UserInterface;

import com.pluralsight.Models.Category;
import com.pluralsight.Persistance.DataManager;

import java.sql.SQLException;
import java.util.List;

public class SakilaConsoleApp {

    private DataManager dm;

    public SakilaConsoleApp(DataManager dm) {
        this.dm = dm;
    }

    public void start(){
        String prompt = """
                Please select from one of the following:
                   1) List all Categories
                   2) List all Films
                   3) List Films by Category
                   0) Quit
                Command
                """;

        // Initialize choice to -1 (non-zero value to enter the loop)
        int choice = -1;

        // Loop until user selects 0 (Quit)
        while (choice != 0){
            choice = ConsoleHelper.promptForInt(prompt);

            switch (choice){
                case 1:
                    processListAllCategories();
                    break;

                // Additional cases for menu options 2, 3 would be added here
                // case 2:
                //     processListAllFilms();
                //     break;
                // case 3:
                //     processListFilmsByCategory();
                //     break;

            }
        }
    }

    private void processListAllCategories() {

        // Retrieve all categories from the database via the data access layer
        try{
            List<Category> categories = dm.getAllCategories();

            // Display the results in a formatted list on the console
            ConsoleHelper.displayList(categories);
        }
        catch (SQLException e) {

            // If something goes wrong with the database query, inform the user
            System.out.println("There was a SQL error: " + e.getMessage());
        }
    }
}