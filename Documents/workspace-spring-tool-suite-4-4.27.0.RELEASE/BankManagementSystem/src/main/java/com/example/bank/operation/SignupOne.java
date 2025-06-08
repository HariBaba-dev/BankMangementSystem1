package com.example.bank.operation;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class SignupOne extends JFrame {

	SignupOne() {

		setLayout(null);

		Random ran = new Random();
		long random = Math.abs((ran.nextLong() % 9000L) + 1000L);

		JLabel formno = new JLabel("APPLICATION FORM NO.. " + random);
		formno.setFont(new Font("Raleway", Font.BOLD, 22));
		formno.setBounds(280, 80, 400, 30);
		add(formno);

		JLabel personalDetails = new JLabel("Page 1 : Personal Details ");
		personalDetails.setFont(new Font("Raleway", Font.BOLD, 38));
		personalDetails.setBounds(140, 20, 600, 40);
		add(personalDetails);

		JLabel name = new JLabel("Name:");
		name.setFont(new Font("Raleway", Font.BOLD, 20));
		name.setBounds(100, 140, 100, 30);
		add(name);

		JTextField nameTextField = new JTextField();
		nameTextField.setFont(new Font("Railway", Font.BOLD, 14));
		nameTextField.setBounds(300, 140, 400, 30);
		add(nameTextField);

		JLabel fname = new JLabel("Father's  Name:");
		fname.setFont(new Font("Raleway", Font.BOLD, 20));
		fname.setBounds(100, 190, 200, 30);
		add(fname);

		JTextField fnameTextField = new JTextField();
		fnameTextField.setFont(new Font("Railway", Font.BOLD, 14));
		fnameTextField.setBounds(300, 190, 400, 30);
		add(fnameTextField);

		JLabel dob = new JLabel("Date of Birth:");
		dob.setFont(new Font("Raleway", Font.BOLD, 20));
		dob.setBounds(100, 240, 200, 30);
		add(dob);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(300, 240, 400, 30);
		dateChooser.setForeground(new Color (105 , 105 ,105 ));
		add(dateChooser);

		JLabel gender = new JLabel("Gender:");
		gender.setFont(new Font("Raleway", Font.BOLD, 20));
		gender.setBounds(100, 290, 200, 30);
		add(gender);
		
		JRadioButton male = new JRadioButton(" Male ");
		male.setBounds(300, 290, 60, 30);
		add(male); 
		
		JRadioButton female = new JRadioButton(" Female ");
		female.setBounds(450, 290, 120, 30);
		add(female);
		
		ButtonGroup gendergroup = new ButtonGroup();
		gendergroup.add(male);
		gendergroup.add(female);
		
		
		
		JLabel email = new JLabel("Email Address:");
		email.setFont(new Font("Raleway", Font.BOLD, 20));
		email.setBounds(100, 330, 200, 30);
		add(email);

		JTextField emailTextField = new JTextField();
		emailTextField.setFont(new Font("Railway", Font.BOLD, 14));
		emailTextField.setBounds(300, 330, 400, 30);
		add(emailTextField);

		JLabel marital = new JLabel("Marital Status:");
		marital.setFont(new Font("Raleway", Font.BOLD, 20));
		marital.setBounds(100, 380, 200, 30);
		add(marital);

		JRadioButton married = new JRadioButton(" Married ");
		married.setBounds(300, 390, 100, 30);
		married.setBackground(Color.WHITE);
		add(married); 
		
		JRadioButton unmarried = new JRadioButton(" Unmarried ");
		unmarried.setBounds(450, 390, 100, 30);
		unmarried.setBackground(Color.WHITE);
		add(unmarried);
		
		JRadioButton other = new JRadioButton(" Other ");
		other.setBounds(630, 390, 100, 30);
		other.setBackground(Color.WHITE);
		add(other); 
		
		ButtonGroup maratialGroup = new ButtonGroup();
		maratialGroup.add(married);;
		maratialGroup.add(unmarried);
		maratialGroup.add(other);
		
		JLabel address = new JLabel("Address:");
		address.setFont(new Font("Raleway", Font.BOLD, 20));
		address.setBounds(100, 420, 200, 30);
		add(address);

		JTextField addressTextField = new JTextField();
		addressTextField.setFont(new Font("Railway", Font.BOLD, 14));
		addressTextField.setBounds(300, 420, 400, 30);
		add(addressTextField);

		JLabel city = new JLabel("City:");
		city.setFont(new Font("Raleway", Font.BOLD, 20));
		city.setBounds(100, 470, 200, 30);
		add(city);

		JTextField cityTextField = new JTextField();
		cityTextField.setFont(new Font("Railway", Font.BOLD, 14));
		cityTextField.setBounds(300, 470, 400, 30);
		add(cityTextField);

		JLabel state = new JLabel("State:");
		state.setFont(new Font("Raleway", Font.BOLD, 20));
		state.setBounds(100, 520, 200, 30);
		add(state);

		JTextField stateTextField = new JTextField();
		stateTextField.setFont(new Font("Railway", Font.BOLD, 14));
		stateTextField.setBounds(300, 520, 400, 30);
		add(stateTextField);

		JLabel pincode = new JLabel("Pincode:");
		pincode.setFont(new Font("Raleway", Font.BOLD, 20));
		pincode.setBounds(100, 570, 200, 30);
		add(pincode);

		JTextField pincodeTextField = new JTextField();
		pincodeTextField.setFont(new Font("Railway", Font.BOLD, 14));
		pincodeTextField.setBounds(300, 570, 400, 30);
		add(pincodeTextField);
		
		
		JButton next = new JButton("Next");
		next.setBackground(Color.BLACK);
		next.setForeground(Color.WHITE);
		next.setFont(new Font ("Railway", Font.BOLD,14));
		next.setBounds(620, 660, 80, 30);
		add(next);

		getContentPane().setBackground(Color.WHITE);

		setSize(850, 800);
		setLocation(35, 10);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SignupOne();
	}

}
