package com.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Worker;

public class WorkerDaoImpl implements WorkerDao {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/worker_schema";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Manasa1419";

    // SQL queries
    private static final String INSERT_WORKER_SQL = "INSERT INTO workers (name, age, position) VALUES (?, ?, ?)";
    private static final String UPDATE_WORKER_SQL = "UPDATE workers SET name=?, age=?, position=? WHERE id=?";
    private static final String DELETE_WORKER_SQL = "DELETE FROM workers WHERE id=?";
    private static final String SELECT_WORKER_BY_ID_SQL = "SELECT id, name, age, position FROM workers WHERE id=?";
    private static final String SELECT_ALL_WORKERS_SQL = "SELECT id, name, age, position FROM workers";

    @Override
    public void insert(Worker worker) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKER_SQL)) {
            preparedStatement.setString(1, worker.getName());
            preparedStatement.setInt(2, worker.getAge());
            preparedStatement.setString(3, worker.getPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Worker worker) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WORKER_SQL)) {
            preparedStatement.setString(1, worker.getName());
            preparedStatement.setInt(2, worker.getAge());
            preparedStatement.setString(3, worker.getPosition());
            preparedStatement.setLong(4, worker.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long workerId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WORKER_SQL)) {
            preparedStatement.setLong(1, workerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Worker getWorkerById(Long workerId) {
        Worker worker = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WORKER_BY_ID_SQL)) {
            preparedStatement.setLong(1, workerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                worker = new Worker();
                worker.setId(resultSet.getLong("id"));
                worker.setName(resultSet.getString("name"));
                worker.setAge(resultSet.getInt("age"));
                worker.setPosition(resultSet.getString("position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public List<Worker> getAllWorkers() {
        List<Worker> workers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WORKERS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setId(resultSet.getLong("id"));
                worker.setName(resultSet.getString("name"));
                worker.setAge(resultSet.getInt("age"));
                worker.setPosition(resultSet.getString("position"));
                workers.add(worker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }
}

