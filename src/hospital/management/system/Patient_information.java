package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Patient_information extends JFrame {
    Patient_information(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,875,553);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10,40,900,450);
        table.setBackground(new Color(90,156,163));
        table.setFont(new Font("Tahoma",Font.BOLD,11));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panel.add(table);

        try{
            connect c = new connect();
            String q = "select * from patient_information";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            javax.swing.table.TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(6).setPreferredWidth(150);
            columnModel.getColumn(0).setPreferredWidth(80); // ID
            columnModel.getColumn(1).setPreferredWidth(100); // Phone
            columnModel.getColumn(2).setPreferredWidth(100); // Name
            columnModel.getColumn(3).setPreferredWidth(80); // Gender
            columnModel.getColumn(4).setPreferredWidth(120); // Disease
            columnModel.getColumn(5).setPreferredWidth(60); // Room
            // Cột 6 là Time (150)
            columnModel.getColumn(7).setPreferredWidth(80);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("ID");
        label1.setBounds(31,11,100,14);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);

        JLabel label2 = new JLabel("Phone ");
        label2.setBounds(150,11,100,14);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        JLabel label3 = new JLabel("Name");
        label3.setBounds(270,11,100,14);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel label4 = new JLabel("Gender");
        label4.setBounds(360,11,100,14);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);

        JLabel label5 = new JLabel("Disease");
        label5.setBounds(480,11,100,14);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label5);

        JLabel label6 = new JLabel("Room");
        label6.setBounds(585,11,100,14);
        label6.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label6);

        JLabel label7 = new JLabel("Time");
        label7.setBounds(700,11,100,14);
        label7.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label7);


        JLabel label8 = new JLabel("Deposit");
        label8.setBounds(820,11,100,14);
        label8.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label8);

        JButton button = new JButton("BACK");
        button.setBounds(450,510,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });






        setSize(900,600);
        setLocation(300,200);
        setLayout(null);
        setVisible(true);
    }

    static void main(String[] args) {
        new Patient_information();
    }
}
