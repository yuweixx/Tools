package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Get_Flow {
	
	

	public static void main(String []args) throws IOException, InterruptedException
	{
		
		System.out.print(PID("com.fanli.android.apps"));
	}

	
	public static double Flow(String PackageName) throws IOException, InterruptedException
	{
		    		
		double i1=GetFlow(PackageName);
		Thread.sleep(1000);
		double j =GetFlow(PackageName)-i1;
		//System.out.println(GetFlow()-i1);
		return j ;
				
	}



	  public static double GetFlow(String PackageName) throws IOException {
		    double str3=0;
		    String Pid=PID(PackageName);
		try{
		    Runtime runtime = Runtime.getRuntime();
		 /*   Process proc2 = runtime.exec("");*/
		    Process proc = runtime.exec("adb shell cat /proc/"+Pid+"/net/dev");
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        	//JOptionPane.showMessageDialog(new JFrame(), "哥们抱歉，好像出问题了！关掉重试吧！");
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		            stringBuffer.append(line+" ");    	            
		        }

		    String str1=stringBuffer.toString();
			String str2=str1.substring(str1.indexOf("wlan0:"),str1.indexOf("wlan0:")+90);
           //接收字节：
			String str4=str2.substring(7,16);
			str4 = str4.trim();
			int b=Integer.parseInt(str4);
	        str3=b/1024;
		    //System.out.println(str3);
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
		}
		    catch (Exception StringIndexOutOfBoundsException)
        	{
		    	//System.out.print("请检查设备是否连接");
	
        	}   
		 
			return str3;
	  }
	  
	  
	  //获取PID
	  public static String PID(String PackageName) throws IOException {
		  String str3=null;
		  
		    Runtime runtime = Runtime.getRuntime();
		 /*   Process proc2 = runtime.exec("");*/
		    Process proc = runtime.exec("adb shell ps");
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		            stringBuffer.append(line+" ");
	        }	  
		    String str1=stringBuffer.toString();
			String str2=str1.substring(str1.indexOf(" "+PackageName)-46,str1.indexOf(" "+PackageName));

     		String PID =str2.substring(0,7);
		    PID = PID.trim();
	        str3=PID;
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
			return str3;
	  }
	
	
	  }
  

