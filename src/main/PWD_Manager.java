package main;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import java.nio.file.Files;
import java.nio.file.Paths;


public class PWD_Manager {

    static boolean login = false;
    static String password1;
    static JSONObject jsonObject;
	public static String phone_number;
	public static String secureCode;

	public static String user_name;
    public static String password;

    static File file;

    public static void send_sms() {
        secureCode = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        System.out.println("Generated Secure Code: " + secureCode);

//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(
//                new PhoneNumber(phone_number),
//                new PhoneNumber(""),
//                secureCode
//        ).create();
    }

    public static boolean isValidPhoneNumber(String phone_number, String regionCode) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phone_number, regionCode);
            return phoneNumberUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Failed Account Creation", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    
    

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        JFrame frame = new JFrame("Password Manager");

        frame.setSize(400, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel_start = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("panel_start.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel panel_auth = new JPanel(new FlowLayout(FlowLayout.CENTER)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("border.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel_auth.setBorder(BorderFactory.createEmptyBorder(100, 50, 0, 50));
        panel_auth.setLayout(new GridLayout(10, 1, 10, 10));


        JPanel panel_auth_login = new JPanel(new FlowLayout(FlowLayout.CENTER)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("border.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel_auth_login.setBorder(BorderFactory.createEmptyBorder(100, 50, 0, 50));
        panel_auth_login.setLayout(new GridLayout(10, 1, 10, 10));


        JPanel panel_initialize = new JPanel(new FlowLayout(FlowLayout.CENTER)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("border.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel_initialize.setBorder(BorderFactory.createEmptyBorder(100, 50, 0, 50));
        panel_initialize.setLayout(new GridLayout(10, 2, 10, 10));


        JPanel panel_login = new JPanel(new FlowLayout(FlowLayout.CENTER)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("border.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel_login.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        panel_login.setLayout(new GridLayout(10, 1, 10, 10));

        JPanel panel_main = new JPanel(new FlowLayout(FlowLayout.CENTER)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("border.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel_main.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        panel_main.setLayout(new GridLayout(10, 1, 10, 10));


        JPanel panel_add = new JPanel(new FlowLayout(FlowLayout.CENTER)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("border.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel_add.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        panel_add.setLayout(new GridLayout(10, 1, 10, 10));

        JPanel panel_password = new JPanel(new FlowLayout(FlowLayout.CENTER)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("border.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel_password.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        panel_password.setLayout(new GridLayout(10, 1, 10, 10));

        //panel_start
        JButton start_button_create = new JButton("Sign Up");
        JButton start_button_login = new JButton("Login");

        //panel_password
        JButton password_back = new JButton("Back");
        password_back.addActionListener(e -> {
            frame.getContentPane().remove(panel_password);
            frame.add(panel_main);
            frame.revalidate();
            frame.repaint();
        });

        //panel_main
        JButton main_button_Add = new JButton("Add Password");
        JButton main_button_sign_out = new JButton("Sign Out");

        //panel_auth_login
        JLabel L_auth_login = new JLabel("Check Your Phone For Authorization Code");
        JButton panel_auth_login_submit = new JButton("Submit");
        JButton panel_auth_login_back = new JButton("Back");
        JTextField panel_auth_login_code = new JTextField(10);


        panel_auth_login_back.addActionListener(e -> {
            frame.getContentPane().remove(panel_auth_login);
            frame.add(panel_login);
            frame.revalidate();
            frame.repaint();
        });
        panel_auth_login_submit.addActionListener(e -> {
            String auth_code = panel_auth_login_code.getText();
            if(login){
                if (secureCode.equals(auth_code)) {
                    File file = new File(user_name + ".json");
                    panel_main.removeAll();
                    panel_main.add(main_button_Add);
                    panel_main.add(main_button_sign_out);
                    frame.getContentPane().remove(panel_login);
                    frame.add(panel_main);
                    frame.revalidate();
                    frame.repaint();

                    if (file.exists()) {
                        PWD_Manager.file = file;
                        String content;

                        try {
                            content = new String(Files.readAllBytes(Paths.get(user_name + ".json")));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        JSONObject jsonObject = new JSONObject(content);
                        for (String key : jsonObject.keySet()) {
                            String storedPassword = jsonObject.getString(key);
                            JButton L_new_button = new JButton(key);

                            L_new_button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JLabel Saved_Website = new JLabel("Website: " + key);
                                    JLabel Saved_Password = new JLabel("Password: " + storedPassword);
                                    panel_password.removeAll();
                                    panel_password.add(Saved_Website);
                                    panel_password.add(Saved_Password);
                                    panel_password.add(password_back);

                                    panel_password.revalidate();
                                    panel_password.repaint();

                                    frame.getContentPane().remove(panel_main);
                                    frame.add(panel_password);
                                    frame.revalidate();
                                    frame.repaint();
                                }
                            });

                            panel_main.add(L_new_button);
                            panel_main.revalidate();
                            panel_main.repaint();
                        }

                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Auth Code Did Not Match", "Failed Login", JOptionPane.INFORMATION_MESSAGE);
                }
            }

           if(secureCode.equals(auth_code)){
               frame.getContentPane().remove(panel_auth_login);
               frame.add(panel_main);
               frame.revalidate();
               frame.repaint();
           }
        });

        //panel_initialize
        JTextField User_Name = new JTextField(20);
        JLabel L_User_Name = new JLabel("User Name");
        
        JTextField Phone_Number = new JTextField(20);
        JLabel L_Phone_Number = new JLabel("Phone Number");
        
        JTextField Password = new JTextField(20);
        JLabel L_Password = new JLabel("Password");
        
        JButton initialize_button_submit= new JButton("Submit");

        JButton initialize_button_back = new JButton("Back");

        
        //panel_auth
        JTextField Auth_Code = new JTextField(20);
        JLabel L_Auth_Code = new JLabel("Check Your Phone For Authorization Code");
        
        JButton auth_button_submit= new JButton("Submit");
        
        JButton Auth_Back_Button = new JButton("Back");
        
        //panel_initialize
        initialize_button_submit.addActionListener(e -> {
            boolean exists = false;  // Assume user does NOT exist initially
            phone_number = Phone_Number.getText();
            user_name = User_Name.getText();
            password = Password.getText();

            File file = new File("users.json");
            if (file.exists()) {
                String content;
                try {
                    content = new String(Files.readAllBytes(Paths.get("users.json")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JSONObject jsonObject = new JSONObject(content);

                // Corrected logic: Check if username already exists
                for (String key : jsonObject.keySet()) {
                    if (key.equals(user_name)) {  // If username matches, user exists
                        exists = true;
                        break;  // No need to check further
                    }
                }

                // If user already exists, show an error message
                if (exists) {
                    JOptionPane.showMessageDialog(null, "User Name Already Exists", "Failed Account Creation", JOptionPane.INFORMATION_MESSAGE);
                    return;  // Stop execution
                }
            }

            // Proceed only if user does NOT exist
            if (!Objects.equals(phone_number, "") && !Objects.equals(user_name, "") && !Objects.equals(password, "")) {
                boolean isValid = isValidPhoneNumber(phone_number, "US");

                if (isValid) {
                    frame.getContentPane().remove(panel_initialize);
                    frame.add(panel_auth);
                    frame.revalidate();
                    frame.repaint();
                    send_sms();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Failed Account Creation", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "One Or More Fields Have Been Left Open", "Failed Account Creation", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        initialize_button_back.addActionListener(e -> {
            frame.getContentPane().remove(panel_initialize);
            frame.add(panel_start);
            frame.revalidate();
            frame.repaint();
        });

        String[] websites = {"Canada", "Australia", "India"};
        JComboBox<String> comboBox = new JComboBox<>(websites);
        comboBox.setBounds(200, 50, 150, 30);
        String selectedWebsite = (String) comboBox.getSelectedItem();
        if(selectedWebsite.equals("Canada")){
            phone_number = "+1" + phone_number;
        }
        if(selectedWebsite.equals("India")){
            phone_number = "+91" + phone_number;
        }
        if(selectedWebsite.equals("Australia")){
            phone_number = "+61" + phone_number;
        }


        //panel_auth
        auth_button_submit.addActionListener(e -> {
            String auth_code = Auth_Code.getText();
            phone_number = Phone_Number.getText();

            File file2 = new File("users.json");

            if(secureCode.equals(auth_code)) {
                try {
                    try (FileReader reader = new FileReader(file2)) {
                        jsonObject = new JSONObject(new JSONTokener(reader));
                    } catch (JSONException | IOException a) {
                        jsonObject = new JSONObject();
                    }
                    JSONObject userDetails = new JSONObject();
                    userDetails.put("password", password);
                    userDetails.put("phone_number", phone_number);
                    jsonObject.put(user_name, userDetails);


                    try (FileWriter writer = new FileWriter(file2)) {
                        writer.write(jsonObject.toString(4));
                        writer.flush();
                        }

                } catch (IOException a) {
                    a.printStackTrace();
                }
                File file = new File(user_name + ".json");
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                PWD_Manager.file = file;
                    frame.getContentPane().remove(panel_auth);
                    frame.add(panel_main);
                    frame.revalidate();
                    frame.repaint();
                }
            
            else {
                JOptionPane.showMessageDialog(null, "Code did not match", "Failed Account creation", JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
        
        Auth_Back_Button.addActionListener(e -> {
            String user_name = User_Name.getText();
            String phone_number = Phone_Number.getText();
            String password = Password.getText();

        	frame.getContentPane().remove(panel_auth);
            frame.add(panel_initialize);
            frame.revalidate();
            frame.repaint();
            
            User_Name.setText(user_name);
            User_Name.setText(phone_number);
            User_Name.setText(password);

            
        });

        
        //panel_start
        start_button_create.addActionListener(e -> {
        	frame.getContentPane().remove(panel_start);
            frame.add(panel_initialize);
            frame.revalidate();
            frame.repaint();
        });
        
        start_button_login.addActionListener(e -> {
            frame.getContentPane().remove(panel_start);
            frame.add(panel_login);
            frame.revalidate();
            frame.repaint();
        });
        
        
        //panel_login
        JTextField login_User_Name = new JTextField(20);
        JLabel L_login_User_Name = new JLabel("User Name");
        
        JTextField Login_Password = new JTextField(20);
        JLabel L_Login_Password = new JLabel("Password");

        JButton login_button_submit= new JButton("Login");
        JButton login_button_back= new JButton("Back");

        login_button_back.addActionListener(e -> {
            frame.getContentPane().remove(panel_login);
            frame.add(panel_start);
            frame.revalidate();
            frame.repaint();
        });

        login_button_submit.addActionListener(e -> {

            File main_file = new File("users.json");
            if(main_file.exists()) {
                String content1 = null;

                user_name = login_User_Name.getText();
                password = Login_Password.getText();

                try {
                    content1 = new String(Files.readAllBytes(Paths.get("users.json")));
                    JSONObject jsonObject = new JSONObject(content1);
                    JSONObject userDetails = null;
                    try {
                        userDetails = jsonObject.getJSONObject(user_name);
                        password1 = userDetails.getString("password");
                        phone_number = userDetails.getString("phone_number");
                        if(password.equals(password1)) {
                            login = true;
                            send_sms();
                            frame.getContentPane().remove(panel_login);
                            frame.add(panel_auth_login);
                            frame.revalidate();
                            frame.repaint();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Invalid Password", "Failed Login", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (JSONException ex) {
                        JOptionPane.showMessageDialog(null, "No Such Account", "Failed Login", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            else{
                JOptionPane.showMessageDialog(null, "Account Not Created", "Failed Login", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        //panel_main

        main_button_sign_out.addActionListener(e -> {
            frame.getContentPane().remove(panel_main);
            frame.add(panel_start);
            frame.revalidate();
            frame.repaint();
        });

        main_button_Add.addActionListener(e -> {
            frame.getContentPane().remove(panel_main);
            frame.add(panel_add);
            frame.revalidate();
            frame.repaint();
        });



        //panel_add
        JTextField Website_Name = new JTextField(20);
        JLabel L_Website_Name = new JLabel("Website Name");

        JTextField Website_Password = new JTextField(20);

        JLabel L_Website_Password = new JLabel("Website Password");
        JButton Website_button_submit= new JButton("Submit");
        JButton Website_button_back= new JButton("Back");

        Website_button_back.addActionListener(e -> {
            frame.getContentPane().remove(panel_add);
            frame.add(panel_main);
            frame.revalidate();
            frame.repaint();
        });


        Website_button_submit.addActionListener(e -> {
            String website_name = Website_Name.getText();
            String website_password = Website_Password.getText();
            if(!Objects.equals(website_name, "") && !Objects.equals(website_password, "")) {
                File file = new File(user_name + ".json");

                if (file.exists()) {
                    try {

                        try (FileReader reader = new FileReader(file)) {
                            jsonObject = new JSONObject(new JSONTokener(reader));
                        } catch (JSONException | IOException a) {
                            jsonObject = new JSONObject();
                        }

                        jsonObject.put(website_name, website_password);

                        try (FileWriter writer = new FileWriter(file)) {
                            writer.write(jsonObject.toString(4));
                            writer.flush();
                        }

                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                }

                JButton newButton = new JButton(website_name);
                newButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_password.removeAll();

                        JLabel Saved_Website = new JLabel("Website: " + website_name);
                        JLabel Saved_Password = new JLabel("Password: " + website_password);

                        panel_password.add(Saved_Website);
                        panel_password.add(Saved_Password);
                        panel_password.add(password_back);

                        panel_password.revalidate();
                        panel_password.repaint();

                        frame.getContentPane().remove(panel_main);
                        frame.add(panel_password);
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                Website_Name.setText("");
                Website_Password.setText("");

                panel_main.add(newButton);
                frame.getContentPane().remove(panel_add);
                frame.add(panel_main);
                frame.revalidate();
                frame.repaint();
            }
            else{
                JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Saving Error", JOptionPane.INFORMATION_MESSAGE);
            }


        });





        frame.setBackground(Color.decode("#E0E0E0"));

        panel_start.setBackground(Color.decode("#F5F5F5"));

        start_button_login.setBounds(160,420,80,30);
        start_button_login.setBackground(Color.decode("#90CAF9"));

        start_button_create.setBackground(Color.decode("#90CAF9"));
        start_button_create.setBounds(160,470,80,30);

        panel_start.add(start_button_login);
        panel_start.add(start_button_create);


        panel_initialize.add(L_User_Name);
        panel_initialize.add(User_Name);
        panel_initialize.add(L_Phone_Number);
        panel_initialize.add(comboBox);
        panel_initialize.add(Phone_Number);
        panel_initialize.add(L_Password);
        panel_initialize.add(Password);
        panel_initialize.add(initialize_button_submit);
        panel_initialize.add(initialize_button_back);
        initialize_button_back.setBackground(Color.decode("#90CAF9"));
        initialize_button_submit.setBackground(Color.decode("#90CAF9"));


        panel_auth.add(L_Auth_Code);
        panel_auth.add(Auth_Code);
        panel_auth.add(auth_button_submit);
        auth_button_submit.setBackground(Color.decode("#90CAF9"));
        panel_auth.add(Auth_Back_Button);
        Auth_Back_Button.setBackground(Color.decode("#90CAF9"));


        panel_login.add(L_login_User_Name);
        panel_login.add(login_User_Name);
        panel_login.add(L_Login_Password);
        panel_login.add(Login_Password);
        panel_login.add(login_button_submit);
        panel_login.add(login_button_back);
        login_button_back.setBackground(Color.decode("#90CAF9"));
        login_button_submit.setBackground(Color.decode("#90CAF9"));



        panel_add.add(L_Website_Name);
        panel_add.add(Website_Name);
        panel_add.add(L_Website_Password);
        panel_add.add(Website_Password);
        panel_add.add(Website_button_submit);
        Website_button_submit.setBackground(Color.decode("#90CAF9"));

        panel_auth_login.add(L_auth_login);
        panel_auth_login.add(panel_auth_login_code);
        panel_auth_login.add(panel_auth_login_submit);
        panel_auth_login.add(panel_auth_login_back);

        panel_main.add(main_button_Add);
        panel_main.add(main_button_sign_out);
        main_button_sign_out.setBackground(Color.decode("#90CAF9"));
        main_button_Add.setBackground(Color.decode("#90CAF9"));

        frame.add(panel_start);
        frame.setVisible(true);

    }
}