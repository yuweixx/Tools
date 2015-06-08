package Monitor;

import java.io.IOException;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.data.time.Millisecond;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  

import GetRoScoer.GetTop;
  
public class CpuChart extends ChartPanel implements Runnable  
{  
    private static TimeSeries timeSeries;  
    private long value=0;  
	static String time =Outlog.log.time();
	static String time1 =Outlog.log.timenow();
    public CpuChart(String chartContent,String title,String yaxisName)  
    {  
        super(createChart(chartContent,title,yaxisName));  
    }  

    
    private static JFreeChart createChart(String chartContent,String title,String yaxisName){  
        //创建时序图对象  
        timeSeries = new TimeSeries(chartContent,Millisecond.class);  
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);  
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,"时间(秒)",yaxisName,timeseriescollection,true,true,false);  
        XYPlot xyplot = jfreechart.getXYPlot();  
        //纵坐标设定  
       /* xyplot.setOpacity();
        xyplot.setBackgroundPaint(Color.LIGHT_GRAY);*/
       
        ValueAxis valueaxis = xyplot.getDomainAxis();  
        //自动设置数据轴数据范围  
        valueaxis.setAutoRange(true);  
        //数据轴固定数据范围 30s  
        valueaxis.setFixedAutoRange(30000D);  
        
        valueaxis = xyplot.getRangeAxis();  
        //valueaxis.setRange(0.0D,200D);  
  
        return jfreechart;  
      }  
  
    public void run()  
    {  
        while(true)  
        {  
        try  
        {  
        	if(GetTop.cpu(Menu.text)==-0.1)
        	{
        		//JOptionPane.showMessageDialog(new JFrame(), "请检查设备连接");
        		break;
        	}
        	else
        	{
            timeSeries.add(new Millisecond(),GetTop.cpu(Menu.text) );  
            Thread.sleep(100);
            log();
          
        } 
        	} 
        catch (InterruptedException e)  {   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        }         
    }  
      
    private double randomNum() throws IOException  
    {     
    	      
        return GetTop.cpu(Menu.text);  
    }  
    
  public static void log() throws IOException, InterruptedException
  {
	  if (Menu.log=="1")
	  {
			 Outlog.log.writelogs("D:/log/Monitor_log",time,"时间: "+time1+"  Cpu："+GetTop.cpu(Menu.text)+"%"+"               Memory: "+HeapChart.randomNum()/1024+"MB"+"                流量："+FlowChart.randomNum()+"Kb");
	  }
	  }
    
}  