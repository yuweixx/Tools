package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
;


public class GetTop {


	public static void main(String []args) throws IOException
	{
		for(int i = 0;i<2;i++)
		{
		//System.out.println(GetRoson("Cpu"));
		System.out.println(heap("com.fanli.android.apps"));
	
		}
		}

	
	  public static double cpu(String PackageName) throws IOException {
		 

		  double Cpu = 0;
			try{
		   
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell top -n 1| grep "+PackageName);
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
		    String  str3=str1.substring(str1.indexOf(PackageName)-43,str1.indexOf(PackageName));
		    //System.out.println(str3);
			 String cpu= str3.substring(0,4);
			        cpu=cpu.trim(); 
			        Cpu=Double.parseDouble(cpu);

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
				
				System.out.print("请检查设备是否连接");
				
				return -0.1;
			}
			
	            return Cpu;

	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  public static double heap(String PackageName) throws IOException {
			 
			
		  double Heap = 0;
	
		   try{
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys meminfo "+PackageName);
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
		        String str2=str1.substring(str1.indexOf("Objects")-60,str1.indexOf("Objects"));
		        String  str3=str2.substring(0,10);
		        str3=str3.trim();
                Heap=Double.parseDouble(str3);
              

			       
			        

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
	        		System.out.print("请检查设备是否连接");
	        		return -0.1;
	        		
	        	}
		    return Heap;
	  }
	  
	  
	  
	  
	  
	  
	  }
	
	  
  

