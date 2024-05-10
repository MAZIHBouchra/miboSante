package model;

import View.FormView;
import com.raven.main.Main;
import java.sql.*;
import javax.swing.JOptionPane;
public class Form {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Integer weight;
    private Integer sizeInCm;
    private String mainGoal;
    private String mostDoneExercise;
    private String leafyGreensIntake;
    private Integer exerciseFrequency;
    private String foodAllergies;
    private String longTermHealthProblems;

    // Getters and setters for each field
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSizeInCm() {
        return sizeInCm;
    }

    public void setSizeInCm(Integer sizeInCm) {
        this.sizeInCm = sizeInCm;
    }

    public String getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(String mainGoal) {
        this.mainGoal = mainGoal;
    }

    public String getMostDoneExercise() {
        return mostDoneExercise;
    }

    public void setMostDoneExercise(String mostDoneExercise) {
        this.mostDoneExercise = mostDoneExercise;
    }

    public String getLeafyGreensIntake() {
        return leafyGreensIntake;
    }

    public void setLeafyGreensIntake(String leafyGreensIntake) {
        this.leafyGreensIntake = leafyGreensIntake;
    }

    public Integer getExerciseFrequency() {
        return exerciseFrequency;
    }

    public void setExerciseFrequency(Integer exerciseFrequency) {
        this.exerciseFrequency = exerciseFrequency;
    }

    public String getFoodAllergies() {
        return foodAllergies;
    }

    public void setFoodAllergies(String foodAllergies) {
        this.foodAllergies = foodAllergies;
    }

    public String getLongTermHealthProblems() {
        return longTermHealthProblems;
    }

    public void setLongTermHealthProblems(String longTermHealthProblems) {
        this.longTermHealthProblems = longTermHealthProblems;
    }
public boolean insertIntoDatabase() {
    boolean dataInserted = false;
    // Check if all fields are filled
    if (firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() || weight == 0 || sizeInCm == 0 || mainGoal.isEmpty() || mostDoneExercise.isEmpty() || leafyGreensIntake.isEmpty() || exerciseFrequency == 0 || foodAllergies.isEmpty() || longTermHealthProblems.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Missing Information", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    try {
        // Create a connection to the database
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginschema", "root", "1234");
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mibosanté", "root", "");
        // Prepare the SQL query
        String query = "INSERT INTO personal_info (first_name, last_name, date_of_birth, gender, weight, size_in_cm) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        // Set the parameters
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setDate(3, new java.sql.Date(dateOfBirth.getTime()));
        statement.setString(4, gender);
        statement.setInt(5, weight);
        statement.setInt(6, sizeInCm);

        // Execute the query
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                // Insert into health_goals table
                query = "INSERT INTO health_goals (user_id, main_goal, most_done_exercise, leafy_greens_intake, exercise_frequency, food_allergies) VALUES (?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);

                // Set the parameters
                statement.setInt(1, userId);
                statement.setString(2, mainGoal);
                statement.setString(3, mostDoneExercise);
                statement.setString(4, leafyGreensIntake);
                statement.setInt(5, exerciseFrequency);
                statement.setString(6, foodAllergies);

                // Execute the query
                affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating health goals failed, no rows affected.");
                } else {
                    JOptionPane.showMessageDialog(null, "Data inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                 // This will hide and dispose the FormView window
                    dataInserted = true;
                }
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        // Close the statement and connection
        statement.close();
        connection.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return dataInserted;
}
    public void fetchFromDatabase(String username) {
        try {
            // Create a connection to the database
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginschema", "root", "1234");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mibosanté", "root", "");

            // Prepare the SQL query
            String query = "SELECT * FROM personal_info WHERE first_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the parameters
            statement.setString(1, username);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Fetch the user's data
            if (resultSet.next()) {
                setFirstName(resultSet.getString("first_name"));
                // Fetch other fields as needed...
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
