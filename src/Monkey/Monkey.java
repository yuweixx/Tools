package Monkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Monitor.Menu;
import Outlog.log;


public class Monkey {


	public static void main(String []args) throws IOException, InterruptedException
	{
		
		//Monkey(" ", "com.taobao.taobao", "500", "", "", "", "", "", "", "500","D:/log8.txt");
	}

	public static void Monkey( String DevicesName,String PackageName ,String zscount,String throttle,String anyevent,String touch,String motion,String trackball,String syskeys,String appswitch,String count,String path){
		Runtime runtime1 = Runtime.getRuntime();
		String errorMSG = "";
	    String cmd="adb "+DevicesName+" shell   monkey -p "+PackageName+" -s "+zscount+" "+throttle+" "+anyevent+" "+touch+" "+motion +" "+trackball +" "+syskeys+" " +appswitch+" -v-v "+count+" > "+path+log.time()+".txt";
	    System.out.println(cmd);
		try {
			String[] args = new String[]{"cmd","/c",cmd};
			//String[] args = new String[]{"sh","-?/c",command};
			
			Process pro1 = runtime1.exec(args);
			//Process pro = runtime.exec("c://///////.exe");
	
		
			//检查命令是否失败
			try {
				if(pro1.waitFor()!=0){
					System.err.println("exit value:" + pro1.exitValue());
					JOptionPane.showMessageDialog(new JFrame(), "哥们抱歉，好像出问题了！关掉重试吧！");
				}
			} catch (InterruptedException e) {
				System.err.println();
				e.printStackTrace();
				
			}

		} catch (IOException e) {
			System.out.println("error Message:"+e.getMessage());
			e.printStackTrace();
		} finally{
			
		}
		
		
	  }

	
}
  

