package CalendarTest;
import javax.swing.JFrame;
import javax.swing.JTable;


public class TestDriver extends JFrame{
 private JTable table;
 public TestDriver(String s){
  
	 
	 
	 super(s);
  this.table = new JTable(5,1);
  this.add(table);
  this.pack();
  this.setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
  
 public static void main(String[] args){
  TestDriver t = new TestDriver("这是一个表格");
 }
}