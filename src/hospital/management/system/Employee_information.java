package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee_information extends JFrame {
    Employee_information(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,990,590);
        panel.setBackground(new Color(109,164,170));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10,34,980,450);
        table.setBackground(new Color(109,164,170));
        table.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(table);

        try{
            connect c =new connect();
            String q = " select * from EMP_INFOR";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Name");
        label1.setBounds(41,11,70,20);
        label1.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(label1);

        JLabel label2 = new JLabel("Age");
        label2.setBounds(180,11,70,20);
        label2.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(label2);

        JLabel label3 = new JLabel("Phone number");
        label3.setBounds(350,11,150,20);
        label3.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(label3);

        JLabel label4 = new JLabel("Salary");
        label4.setBounds(550,11,70,20);
        label4.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(label4);

        JLabel label5 = new JLabel("Email");
        label5.setBounds(730,11,70,20);
        label5.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(label5);

        JLabel label6 = new JLabel("Citizen ID");
        label6.setBounds(830,11,70,20);
        label6.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(label6);

        JButton button = new JButton("BACK");
        button.setBounds(350,500,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


         setSize(1000,600);
         setLocation(350,230);
         setLayout(null);
         setVisible(true);
    }

    static void main(String[] args) {
        new Employee_information();
    }
}
