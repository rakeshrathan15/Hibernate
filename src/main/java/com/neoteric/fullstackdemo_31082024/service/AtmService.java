package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.model.Atm;

import java.sql.*;
import java.util.Calendar;
import java.util.UUID;

public class AtmService {

    public String createAtm(Account account, Atm atm) throws SQLException {
        // First, validate that the account exists
        if (!isAccountValid(account.getAccountNumber())) {
            throw new SQLException("Account not found for account number: " + account.getAccountNumber());
        }

        // Generate ATM card details
        String cardNumber = UUID.randomUUID().toString();
        String pin = generatePin();
        String cvv = generateCvv();
        Date expiryDate = generateExpiryDate();

        String query = "INSERT INTO bank.atm (cardNumber, pin, accountNumber, cvv, cardExpiry) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, cardNumber);
            pstmt.setString(2, pin);
            pstmt.setString(3, account.getAccountNumber());
            pstmt.setString(4, cvv);
            pstmt.setDate(5, new java.sql.Date(expiryDate.getTime()));

            int status = pstmt.executeUpdate();

            if (status == 1) {
                System.out.println("ATM created successfully with card number: " + cardNumber);
                atm.setCardNumber(cardNumber);
                atm.setPin(pin);
                atm.setCvv(cvv);
                atm.setCardExpiry(expiryDate);
                return cardNumber; // Return the generated card number
            } else {
                throw new SQLException("ATM creation failed for account number: " + account.getAccountNumber());
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
            throw e;
        }
    }

    private boolean isAccountValid(String accountNumber) throws SQLException {
        String query = "SELECT COUNT(*) FROM bank.account WHERE accountNumber = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, accountNumber);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            } else {
                return false;
            }
        }
    }

    private String generatePin() {
        return String.valueOf((int) (Math.random() * 9000) + 1000); // 4-digit PIN
    }

    private String generateCvv() {
        return String.valueOf((int) (Math.random() * 900) + 100); // 3-digit CVV
    }

    private Date generateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 5); // Add 5 years to the current date
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    public String getCardNumberByAccount(String accountNumber) throws SQLException {
        String cardNumber = null;
        String query = "SELECT cardNumber FROM bank.atm WHERE accountNumber = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, accountNumber);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                cardNumber = resultSet.getString("cardNumber");
            } else {
                throw new SQLException("ATM card not found for account number: " + accountNumber);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
            throw e;
        }

        return cardNumber;
    }

    }







