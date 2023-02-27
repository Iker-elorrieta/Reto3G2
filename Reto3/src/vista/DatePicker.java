package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.Metodos;
//create class
	class DatePicker 
	{
		Metodos mc=new Metodos();
		//define variables
	        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	        //create object of JLabel with alignment
	        JLabel dia = new JLabel("", JLabel.CENTER);
	        //define variable
	        String day = "";
	        //declaration
	        JDialog d;
	        //create object of JButton
	        JButton[] button = new JButton[49];
	        String [] fechasDisponibles;
	        Calendar calUso=Calendar.getInstance();
	        int ano=calUso.get(Calendar.YEAR);
	        
	        public DatePicker(JFrame parent, String[] fechasPelicula)//create constructor 
	        {
	        	fechasDisponibles=fechasPelicula;
	        	//create object
	                d = new JDialog();
	                //set modal true
	                d.setModal(true);
	                //define string
	                String[] header = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };
	                //create JPanel object and set layout
	                JPanel p1 = new JPanel(new GridLayout(7, 7));
	                //set size
	                p1.setPreferredSize(new Dimension(430, 120));
	                //for loop condition
	                for (int x = 0; x < button.length; x++) 
	                {		
	                	//define variable
	                        final int selection = x;
	                        //create object of JButton
	                        button[x] = new JButton();
	                        //set focus painted false
	                        button[x].setFocusPainted(false);
	                        //set background colour
	                        button[x].setBackground(Color.white);
	                        //if loop condition
	                        if (x > 6)
	                        //add action listener
	                        button[x].addActionListener(new ActionListener() 
	                        {
	                                 public void actionPerformed(ActionEvent ae) 
	                                 {
	                                       day = button[selection].getActionCommand();
	                                       //call dispose() method
	                                       d.dispose();
	                                 }
	                        });
	                        if (x < 7)//if loop condition 
	                        {
	                                button[x].setText(header[x]);
	                                //set fore ground colour
	                                button[x].setForeground(Color.red);
	                        }
	                        p1.add(button[x]);//add button
	                }
	                //create JPanel object with grid layout
	                JPanel p2 = new JPanel(new GridLayout(1, 3));
	                
	                //create object of button for previous month
	                JButton previous = new JButton("Anterior");
	                //add action command
	                previous.addActionListener(new ActionListener() 
	                {
	                        public void actionPerformed(ActionEvent ae) 
	                        {
	                            //decrement month by 1
	                            month--;
	                            //call method
	                            displayDate();
	                        }
	                });
	                p2.add(previous);//add button
	                p2.add(dia);//add label
	                //create object of button for next month
	                JButton next = new JButton("Siguiente");
	                //add action command
	                next.addActionListener(new ActionListener()
	                {
	                        public void actionPerformed(ActionEvent ae) 
	                        {
	                             //increment month by 1
	                             month++;
	                             //call method
	                            displayDate();
	                        }
	                });
	                p2.add(next);// add next button
	                //set border alignment
	                d.add(p1, BorderLayout.CENTER);
	                d.add(p2, BorderLayout.SOUTH);
	                d.pack();
	                //set location
	                d.setLocationRelativeTo(parent);
	                //call method
	                displayDate();
	                //set visible true
	                d.setVisible(true);
	        }

			public void displayDate() 
	        {
	        	for (int x = 7; x < button.length; x++)//for loop
	        	button[x].setText("");//set text
	      	        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");	
	                //create object of SimpleDateFormat 
	                java.util.Calendar cal = java.util.Calendar.getInstance();			
	                //create object of java.util.Calendar 
	        	cal.set(year, month, 1); //set year, month and date
	         	//define variables
	        	int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
	        	int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	        	//condition
	        	for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
	        	//set text
		        	button[x].setText("" + day);
		        	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		        	Date date=null;
		        	 try {
						date = dt.parse(String.valueOf(ano+"-"+(month+1)+"-"+day));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	String fechaUnica=String.valueOf(dt.format(date));
		        	boolean disponibilidad=mc.comprobarBotoneFechasDT(fechasDisponibles,fechaUnica);
		        	button[x].setEnabled(disponibilidad);
		        	button[x].setToolTipText(ano+"-"+(month+1)+"-"+day);
	        	}
	        	dia.setText(sdf.format(cal.getTime()));
	        	//set title
	        	d.setTitle("Date Picker");
	        }

	        public String setPickedDate() 
	        {
	        	//if condition
	        	if (day.equals(""))
	        		return day;
	            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	            java.util.Calendar cal = java.util.Calendar.getInstance();
	            cal.set(year, month, Integer.parseInt(day));
	            return sdf.format(cal.getTime());
	        }
	}

	

