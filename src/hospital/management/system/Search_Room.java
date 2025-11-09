package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Search_Room extends JFrame {
    Choice choice;
    JTable table;
    Search_Room(){
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 675, 452);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel forRoom = new JLabel("Search for room");
        forRoom.setBounds(250,11,186,31);
        forRoom.setForeground(Color.white);
        forRoom.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(forRoom);

        JLabel status = new JLabel("Status:");
        status.setBounds(70,70,80,20);
        status.setForeground(Color.white);
        status.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(status);

        choice = new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Availability");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(0,187,700,210);
        table.setBackground(new Color(90,156,163));
        table.setForeground(Color.white);
        panel.add(table);

        try{
            connect c = new connect();
            String q = "select * from Room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel roomNumber = new JLabel("Room number:");
        roomNumber.setBounds(23,162,150,20);
        roomNumber.setForeground(Color.white);
        roomNumber.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(roomNumber);

        JLabel available = new JLabel("Availability:");
        available.setBounds(175,162,150,20);
        available.setForeground(Color.white);
        available.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(available);

        JLabel price = new JLabel("Price:");
        price.setBounds(389,162,150,20);
        price.setForeground(Color.white);
        price.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(price);

        JLabel Bed = new JLabel("Bed type:");
        Bed.setBounds(580,162,150,20);
        Bed.setForeground(Color.white);
        Bed.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Bed);


        JButton search = new JButton("Search");
        search.setBounds(200,420,120,25);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.white);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from Room where Availability = '"+choice.getSelectedItem()+"'";
                try{
                    connect c =new connect();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(380,420,120,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });



        setSize(700,500);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);
    }
    static void main(String[] args) {
        new Search_Room();
    }
}
