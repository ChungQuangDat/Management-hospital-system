package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_patient_details extends JFrame {
    Update_patient_details(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,925,450);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient details");
        label1.setBounds(124,11,260,25);
        label1.setFont(new Font("Tahoma",Font.BOLD,20));
        label1.setForeground(Color.white);
        panel.add(label1);


        JLabel label2 = new JLabel("Name:");
        label2.setBounds(25,88,100,14);
        label2.setFont(new Font("Tahoma",Font.PLAIN,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);

        try {
            connect c = new connect();
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_information");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room number");
        label3.setBounds(25,129,100,14);
        label3.setFont(new Font("Tahoma",Font.PLAIN,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248,129,140,20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(25,174,100,14);
        label4.setFont(new Font("Tahoma",Font.PLAIN,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldINTIME = new JTextField();
        textFieldINTIME.setBounds(248,174,140,20);
        panel.add(textFieldINTIME);

        JLabel label5 = new JLabel("Amount Paid:");
        label5.setBounds(25,216,100,14);
        label5.setFont(new Font("Tahoma",Font.PLAIN,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248,216,140,20);
        panel.add(textFieldAmount);


        JLabel label6 = new JLabel("Pending Amount:");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Tahoma",Font.PLAIN,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248,261,140,20);
        panel.add(textFieldPending);


        JButton Check = new JButton("Check");
        Check.setBounds(281,378,89,23);
        Check.setBackground(Color.BLACK);
        Check.setForeground(Color.white);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String q = "select * from Patient_information where Name = '"+id+"'";
                try{
                    connect c = new connect();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    while (resultSet.next()){
                        textFieldR.setText(resultSet.getString("Room_Number"));
                        textFieldINTIME.setText(resultSet.getString("Time"));
                        textFieldAmount.setText(resultSet.getString("Deposit"));
                    }
                    ResultSet resultSet1 = c.statement.executeQuery("select * from Room where vacant_room = '"+textFieldR.getText()+"' ");
                    while(resultSet1.next()){
                       String price = resultSet1.getString("Price");
                       int amountPaid = Integer.parseInt(price) - Integer.parseInt(textFieldAmount.getText());
                       textFieldPending.setText(""+amountPaid);
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });


        JButton Update = new JButton("Update");
        Update.setBounds(56,378,89,23);
        Update.setBackground(Color.BLACK);
        Update.setForeground(Color.white);
        panel.add(Update);
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                   connect c = new connect();
                   String q = choice.getSelectedItem();
                   String room = textFieldR.getText();
                   String time = textFieldINTIME.getText();
                   String amount = textFieldAmount.getText();
                   c.statement.executeUpdate("update Patient_information set Room_Number = '"+room+"', Time ='"+time+"',Deposit='"+amount+"' where Name ='"+q+"'");
                   JOptionPane.showMessageDialog(null,"Updated successfully");
                   setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(168,378,89,23);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });



        setSize(950,500);
         setLayout(null);
         setLocation(400,250);
         setVisible(true);
    }


    static void main(String[] args) {
     new Update_patient_details();
    }
}
