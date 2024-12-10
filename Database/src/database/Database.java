/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 *
 * @author THAN
 */
public class Database extends JFrame{
private JTextField idField, moneyField, interestRateField, firstNameField, lastNameField, ageField;
    private JComboBox<String> dayOpenCombo, monthOpenCombo, yearOpenCombo;
    private JComboBox<String> dayBirthCombo, monthBirthCombo, yearBirthCombo;
    private JButton saveButton, showButton;
    private static final String FOLDER_NAME = "dataPerson";
    private static final String FILE_NAME = "data.csv";

    public  Database() {
        setTitle("Show Detail of Account");
        setSize(390, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("ACCOUNT MONEY", SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Enter Data Account Money"
        );
        titledBorder.setTitleJustification(TitledBorder.LEFT);
        formPanel.setBorder(titledBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(10);
        formPanel.add(idField, gbc);
        gbc.gridx = 2;
        formPanel.add(new JLabel("MONEY:"), gbc);
        gbc.gridx = 3;
        moneyField = new JTextField(10);
        formPanel.add(moneyField, gbc);
        gbc.gridx = 4;
        formPanel.add(new JLabel("BATH:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("ANNUAL INTEREST RATE:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        interestRateField = new JTextField(20);
        formPanel.add(interestRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("DAY OPEN ACCOUNT:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        dayOpenCombo = new JComboBox<>(new String[]{"day"});
        formPanel.add(dayOpenCombo, gbc);
        gbc.gridx = 3;
        monthOpenCombo = new JComboBox<>(new String[]{"month"});
        formPanel.add(monthOpenCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        yearOpenCombo = new JComboBox<>(new String[]{"year"});
        formPanel.add(yearOpenCombo, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("FIRST NAME:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        firstNameField = new JTextField(20);
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("LAST NAME:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        lastNameField = new JTextField(20);
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("BIRTH DAY:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        dayBirthCombo = new JComboBox<>(new String[]{"day"});
        formPanel.add(dayBirthCombo, gbc);
        gbc.gridx = 3;
        monthBirthCombo = new JComboBox<>(new String[]{"month"});
        formPanel.add(monthBirthCombo, gbc);
        gbc.gridx = 4;
        yearBirthCombo = new JComboBox<>(new String[]{"year"});
        formPanel.add(yearBirthCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("AGE:"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(5);
        formPanel.add(ageField, gbc);
        gbc.gridx = 2;
        formPanel.add(new JLabel("YEAR"), gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("SAVE");
        showButton = new JButton("SHOW");
        buttonPanel.add(saveButton);
        buttonPanel.add(showButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        initializeComboBoxes();

        saveButton.addActionListener(e -> saveData());
        showButton.addActionListener(e -> showData());
    }

    private void initializeComboBoxes() {
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.format("%02d", i);
        }
        dayOpenCombo.setModel(new DefaultComboBoxModel<>(days));
        dayBirthCombo.setModel(new DefaultComboBoxModel<>(days));

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        monthOpenCombo.setModel(new DefaultComboBoxModel<>(months));
        monthBirthCombo.setModel(new DefaultComboBoxModel<>(months));

        String[] years = new String[100];
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        yearOpenCombo.setModel(new DefaultComboBoxModel<>(years));
        yearBirthCombo.setModel(new DefaultComboBoxModel<>(years));
    }

    private void saveData() {
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File fileToSave = new File(folder, FILE_NAME);
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, true))) {
            String data = String.format("%s,%s,%.2f,%s-%s-%s,%s,%s,%s-%s-%s,%d",
                    idField.getText(),
                    moneyField.getText(),
                    Double.parseDouble(interestRateField.getText()),
                    yearOpenCombo.getSelectedItem(),
                    monthOpenCombo.getSelectedItem(),
                    dayOpenCombo.getSelectedItem(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    yearBirthCombo.getSelectedItem(),
                    monthBirthCombo.getSelectedItem(),
                    dayBirthCombo.getSelectedItem(),
                    Integer.parseInt(ageField.getText()));
            writer.println(data);
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    private void showData() {
        File file = new File(FOLDER_NAME, FILE_NAME);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "No data found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> data = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                StringBuilder record = new StringBuilder();

                record.append("ID: ").append(getValueSafely(parts, 0)).append("\n");
                record.append("Name: ").append(getValueSafely(parts, 4)).append(" " + getValueSafely(parts, 5) + "\n");
                record.append("Money: ").append(getValueSafely(parts, 1)).append(" BATH\n");
                record.append("Interest Rate: ").append(getValueSafely(parts, 2)).append("%\n");
                record.append("Open Account Date: ").append(getValueSafely(parts, 3)).append("\n");
                record.append("Birth Date: ").append(getValueSafely(parts, 6, "N/A")).append("\n");
                record.append("Age: ").append(getValueSafely(parts, 7, "N/A")).append(" years");

                data.add(record.toString());
            }

            if (!data.isEmpty()) {
                for (int i = 0; i < data.size(); i += 4) {
                    StringBuilder message = new StringBuilder();
                    for (int j = i; j < Math.min(i + 4, data.size()); j++) {
                        message.append(data.get(j)).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(this, message.toString(), "Account Data", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No data found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading data: " + e.getMessage());
        }
    }

    private String getValueSafely(String[] array, int index, String defaultValue) {
        if (index < array.length) {
            return array[index];
        }
        return defaultValue;
    }

    private String getValueSafely(String[] array, int index) {
        return getValueSafely(array, index, "N/A");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Database().setVisible(true);
        });
    }
    
}
