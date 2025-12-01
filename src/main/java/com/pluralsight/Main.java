package com.pluralsight;


import com.pluralsight.Models.Category;
import com.pluralsight.Persistance.DataManager;
import com.pluralsight.UserInterface.ConsoleHelper;
import com.pluralsight.UserInterface.SakilaConsoleApp;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args)  {

        // Make sure the user typed in the right information
        if (!ensureArgs(args)) { return; }

        try{

            // Create the database connection using the info the user gave us
            BasicDataSource ds = getDataSource(args);

            // Create a DataManager to handle all database stuff
            DataManager dm = new DataManager(ds);

            // Create the app and start it
            SakilaConsoleApp app = new SakilaConsoleApp(dm);

            app.start();

        } catch (Exception e) {
            System.out.println("There was a SQL exception: "+ e.getMessage());
        }
    }



    private static boolean ensureArgs(String[] args){
        //     * ensureArgs - Checks that the user gave us 3 things when running the program
        //     * We need: username, password, and database URL
        //     * If they didn't give us all 3, print an error message and return false

        if(args.length < 3){
            System.out.println("You need to provide a username, password, and URL when running this command.");
            System.out.println("For example:");
            System.out.println("Main.exe myUsername myPassword myURL");
            return false;
        }
        return true;
    }



    private static BasicDataSource getDataSource(String username, String password, String URL) throws SQLException {
        //     * getDataSource - Creates the connection to the database
        //     * Takes the username, password, and URL and sets up a connection so we can talk to the database

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        return basicDataSource;

    }



    private static BasicDataSource getDataSource(String[] args) throws SQLException {
        //     * getDataSource - Takes the user's input and pulls out the username, password, and URL
        //     * This is just a helper that takes the array from main()
        //       and extracts the 3 pieces we need, then calls the other getDataSource method
        
        String username = args[0];
        String password = args[1];
        String URL = args[2];
        return getDataSource(username, password, URL);
    }
}
