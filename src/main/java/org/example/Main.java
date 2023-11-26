package org.example;

import org.example.database.DatabaseQueryService;
import org.example.services.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        // Find Max Salary Worker
        List<Object[]> maxSalaryWorkers = MaxSalaryWorkerQuery.execute(databaseQueryService.getConnection());
        printResults("Max Salary Worker", maxSalaryWorkers, "Name", "Salary");

        // Find Max Projects Client
        List<Object[]> maxProjectCountClients = MaxProjectsClientQuery.execute(databaseQueryService.getConnection());
        printResults("Max Projects Client", maxProjectCountClients, "Client Name", "Project Count");

        // Find Longest Project
        List<Object[]> longestProjects = LongestProjectQuery.execute(databaseQueryService.getConnection());
        printResults("Longest Project", longestProjects, "Project ID", "Client ID", "Client Name", "Start Date", "Finish Date", "Month Count");

        // Find Youngest and Eldest Workers
        List<Object[]> youngestEldestWorkers = YoungestEldestWorkersQuery.execute(databaseQueryService.getConnection());
        printResults("Youngest and Eldest Workers", youngestEldestWorkers, "Type", "Name", "Birthday");

        // Find Project Prices
        ProjectPricesQuery projectPricesQuery = new ProjectPricesQuery(databaseQueryService.getConnection());
        List<Object[]> projectPrices = projectPricesQuery.execute();
        printResults("Project Prices", projectPrices, "Project ID", "Client ID", "Client Name", "Start Date", "Finish Date", "Price");

        databaseQueryService.closeConnection();
    }

    private static void printResults(String queryName, List<Object[]> results, String... columnNames) {
        System.out.println(queryName + " Results:");

        results.forEach(result -> {
            for (int i = 0; i < columnNames.length; i++) {
                System.out.println(columnNames[i] + ": " + result[i]);
            }
            System.out.println("----------------------");
        });
    }
}