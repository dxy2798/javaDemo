package CalendarTest;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//import java.util.Date;
//import java.util.Calendar;

public class CalendarFrame extends JFrame {

	String [] months = {
			"January","February","March","April",
			"May","June","July","August","September",
			"October","November","December"};
	String [] days = {
			"1","2","3","4","5","6","7","8","9","10","11","12",
			"13","14","15","16","17","18","19","20","21","22","","23",
			"24","25","26","27","28","29","30","31"};
	String [] years = {"2016","2017","2018","2019"};
	
	JComboBox newMonthCombo = null;
	JComboBox newDayCombo = null;
	JComboBox newYearCombo = null;
	
	JTextField titleBox = null;
	JTextArea descriptionBox =null;
	JTextField reminderBox = null;
	
	JComboBox showMonthCombo = null;
    JComboBox showDayCombo = null;
    JComboBox showYearCombo = null;
    JTextArea events = null;
    
    JLabel monthToShow = null;
    JLabel dayToShow = null;
    JLabel yearToShow = null;
    
    String username = null;
    String password = null;
    
	public CalendarFrame(){
		super();
		this.setSize(600,450);
		this.getContentPane().setLayout(new java.awt.GridLayout(1, 2));
		
		//Left-hand panel
		
		JPanel title = new JPanel();
		title.add(new JLabel("<html><h2> New Event</h2></html>"));
		
		//date of the event
		
		JPanel newDate = new JPanel();
		newDate.add(new JLabel("New event for:"));
		
		newMonthCombo = new JComboBox(months);
		newDate.add(newMonthCombo);
		newDayCombo = new JComboBox(days);
		newDate.add(newDayCombo);
		newYearCombo = new JComboBox(years);
		newDate.add(newYearCombo);
		
		//title of the event
		
		JPanel eventTitle = new JPanel();
		eventTitle.add(new JLabel("Title:"));
		titleBox = new JTextField(20);
		eventTitle.add(titleBox);

		
		//description of the event
		
		JPanel description = new JPanel();
		description.add(new JLabel("Description:"));
		descriptionBox = new JTextArea(8,15);
		description.add(descriptionBox);
		
		//reminders to
		
		JPanel reminders = new JPanel();
		reminders.setLayout(new java.awt.GridLayout(2,1));		
		reminders.add(new JLabel("Reminders to:"));
		reminderBox = new JTextField(20);
		reminders.add(reminderBox);
		
		//save button
		
		JPanel save = new JPanel();
		JButton saveButton = new JButton("save");
		saveButton.addActionListener(new SaveListener());
		save.add(saveButton);
		
		//add to panel
		
		JPanel left = new JPanel();
		
		left.add(title);
		left.add(newDate);
		left.add(eventTitle);
		left.add(description);
		left.add(reminders);
		left.add(save);
		
		this.add(left);
		
		//right side
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right,BoxLayout.PAGE_AXIS));
		
		JPanel rightTitle = new JPanel();
		rightTitle.add(new JLabel("<html><h2> Event</h2></html>"));
		
		JPanel showDate = new JPanel();
		showDate.add(new Label("Events for: "));
		
		showMonthCombo = new JComboBox(months);
		showDate.add(showMonthCombo);
		
		showDayCombo = new JComboBox(days);
		showDate.add(showDayCombo);
		
		showYearCombo = new JComboBox(years);
		showDate.add(showYearCombo);
		
		JPanel showButtonPanel = new JPanel();
		JButton showButton = new JButton("show");
		showButton.addActionListener(new ShowListener());
		showButton.setHorizontalAlignment(SwingConstants.CENTER);
		JButton remindButton = new JButton("reminders");
		remindButton.addActionListener(new RemindListener());
		remindButton.setHorizontalAlignment(SwingConstants.CENTER);
		showButtonPanel.add(showButton);
		
		JPanel dateToShow = new JPanel();
		
//		Calendar c = Calendar.getInstance();   //尝试使日期初始值为当日
//		int xyear = c.get(Calendar.YEAR);
//		int xmonth = c.get(Calendar.MONTH);
//		int xday = c.get(Calendar.DATE);
//		JLabel monthToShow = new JLabel(new String().valueOf(xmonth));
//		JLabel dayToShow = new JLabel(new String().valueOf(xday));
//		JLabel yearToShow = new JLabel(new String().valueOf(xyear));
		
		JLabel monthToShow = new JLabel("1");
		JLabel dayToShow = new JLabel("1");
		JLabel yearToShow = new JLabel("2016");
		
		dateToShow.add(monthToShow);
		dateToShow.add(new JLabel("/"));
		dateToShow.add(dayToShow);
		dateToShow.add(new JLabel("/"));
		dateToShow.add(yearToShow);
		
		JPanel showEvents = new JPanel();
		JScrollPane scrollEvents = new JScrollPane();
		events = new JTextArea(10,20);
		
		//scrollEvents.add(events);
		showEvents.add(events);
		
		right.add(rightTitle);
		right.add(showDate);
		right.add(showButtonPanel);
		right.add(dateToShow);
		right.add(showEvents);
		this.add(right);
	
	}
	
	class SaveListener implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent e){
			int month = newMonthCombo.getSelectedIndex()+1;
			int day = newDayCombo.getSelectedIndex()+1;
			int year = Integer.parseInt(
					newYearCombo.getSelectedItem().toString());
			String newTitle = titleBox.getText();
			String newDescription = descriptionBox.getText();
			String newReminders = reminderBox.getText();
			JButton button = (JButton)e.getSource();
			CalendarFrame window = 
					(CalendarFrame)button.getTopLevelAncestor();
			//因为监听器是内部类，必须跳转几次。首先使用事件得到用户单击的按钮的引用和窗口的引用。因为窗口实际上是 CalendarFrame 对象，所以可以访问这 些属性。
			EventClass theNewEvent = null;
			theNewEvent = new EventClass(newTitle,newDescription,
						newReminders,month,day,year,window.username,window.password);
			
				System.out.println("Save　event　for 　"+month+"/"+day+
				 	"/"+year);
		 			System.out.println(newTitle);
		 				System.out.println(newDescription);
		 					System.out.println(newReminders);
		 					JOptionPane.showMessageDialog(null, "保存成功");
		}
	}
	class ShowListener implements java.awt.event.ActionListener
	 {
		 public void actionPerformed(java.awt.event.ActionEvent e)
		 {
			 int month = showMonthCombo.getSelectedIndex()+1;
			 int day = showDayCombo.getSelectedIndex()+1;
			 int year = Integer.parseInt(
					 showYearCombo.getSelectedItem().toString());
			 
			 JButton button = (JButton)e.getSource();//最初发生EVENT的对象,得到句柄
			 CalendarFrame window = 
					 (CalendarFrame)button.getTopLevelAncestor();

			 EventClass[] eventsToShow = Calendar.getEvents(month, day, year,
					 						window.username,window.password);
			 String textToShow = null;
			 if (eventsToShow == null){
				 textToShow = "Nothing for that date";
//				 monthToShow.setText(new String().valueOf(month));
//				 dayToShow.setText(new String().valueOf(day));
//				 yearToShow.setText(new String().valueOf(year));
				 events.setText(textToShow);
			 }else{
				 textToShow = "There are " + eventsToShow.length +
						 " events for this date:\n\n";
			 for (int i=0;i<eventsToShow.length;i++){
				 String thisEventText = eventsToShow[i].getTitle() + "\n" +
						 eventsToShow[i].getDescription() + "\nReminders to: " +
						 eventsToShow[i].getRemindersTo() + "\n";
				 textToShow = textToShow + thisEventText;
			 }
//			 monthToShow.setText(new String().valueOf(month));
//			 dayToShow.setText(new String().valueOf(day));
//			 yearToShow.setText(new String().valueOf(year));
			 events.setText(textToShow);
		    } 
		 }
	 }

	class RemindListener implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
			ShowListener showListener = new ShowListener();
			showListener.actionPerformed(e);
			/*
			 * 这里仅仅向界面中添加了一个按钮，但是希望它完成和 show 按钮同样的工 作，所以首先调用 showListener.actionPerformed() 方法并提供收到的事件。 
			 * 然后可以提取日期信息并创建新的 Reminder 对象，让它发送那一天的事件。
			 * */
			int month = showMonthCombo.getSelectedIndex()+1;
			int day = showDayCombo.getSelectedIndex() +1;
			int year = Integer.parseInt(showYearCombo.getSelectedItem().toString());
			
			JButton button = (JButton)e.getSource();
			CalendarFrame window =
					(CalendarFrame)button.getTopLevelAncestor();
			Reminder reminder = new Reminder();
			reminder.sendAllReminders(month, day, year,
					window.username,window.password);
			
		}
		
	}
	
	public static void main(String[] args) {
		
		CalendarFrame w = new CalendarFrame();
		JOptionPane userBox = new JOptionPane();
		userBox.setWantsInput(true);
		w.username = (String)userBox.showInputDialog(w,
					"Please enter your username:");
		
		JOptionPane passBox = new JOptionPane();
		passBox.setWantsInput(true);
		w.password = (String)passBox.showInputDialog(w, 
					"Please enter your password:");
		w.setLocationRelativeTo(null);
		w.setVisible(true);
	}

}
