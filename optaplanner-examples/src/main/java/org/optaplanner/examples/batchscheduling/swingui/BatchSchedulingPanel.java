package org.optaplanner.examples.batchscheduling.swingui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.optaplanner.examples.batchscheduling.domain.Allocation;
import org.optaplanner.examples.batchscheduling.domain.AllocationPath;
import org.optaplanner.examples.batchscheduling.domain.Batch;
import org.optaplanner.examples.batchscheduling.domain.RoutePath;
import org.optaplanner.examples.batchscheduling.domain.Schedule;
import org.optaplanner.examples.batchscheduling.domain.Segment;
import org.optaplanner.examples.common.swingui.SolutionPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchSchedulingPanel extends SolutionPanel<Schedule>
{
	private static final long serialVersionUID = 1L;

	public static final String LOGO_PATH = "/org/optaplanner/examples/batchscheduling/swingui/cloudBalancingLogo.png";
	
    final Logger logger = LoggerFactory.getLogger(BatchSchedulingPanel.class);
	

    public BatchSchedulingPanel() 
    {
    	//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	 setLayout(new BorderLayout());
          
    }

    public void resetPanel(Schedule solution) {
        removeAll();
        repaint(); // When GridLayout doesn't fill up all the space

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Batches", createComponentPanel1(solution));
		tabbedPane.add("RoutePaths", createComponentPanel2(solution));
		tabbedPane.add("Segments", createComponentPanel3(solution));
        tabbedPane.add("Allocations", createComponentPanel4(solution));
        add(tabbedPane, BorderLayout.CENTER);
        setPreferredSize(PREFERRED_SCROLLABLE_VIEWPORT_SIZE);
		
        add(tabbedPane);
		
        return;
    }

    private JScrollPane createComponentPanel1(Schedule solution) 
    {

    	
//    	JPanel componentPanel = new JPanel(new FlowLayout());
		
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = formatter.format(date);

		Vector<String> tableHeader = new Vector<String>();
		Vector<Vector<String>> tableData = new Vector<Vector<String>>();

		tableHeader.add("Batch");
		tableHeader.add("Volume");
		tableHeader.add("DelayRangeValue");
		tableHeader.add("Time Refreshed");
        
		Schedule schedule = (Schedule) solution;
		
        for (Batch batch : schedule.getBatchList()) 
        {
        	Vector<String> rowData = new Vector<String>();
        	rowData.add(batch.getName());
        	rowData.add(batch.getVolume().toString());
        	rowData.add(batch.getDelayRangeValue().toString());
        	rowData.add(strDate);	
            tableData.add(rowData);
        }
		
        JTable table = new JTable(tableData, tableHeader);
        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.getColumnModel().getColumn(1).setMinWidth(300);
        
    	JScrollPane componentPanel = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
        return componentPanel;

    }

    private JScrollPane createComponentPanel2(Schedule solution) 
    {

    	
//    	JPanel componentPanel = new JPanel(new FlowLayout());
		
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = formatter.format(date);

		Vector<String> tableHeader = new Vector<String>();
		Vector<Vector<String>> tableData = new Vector<Vector<String>>();

		tableHeader.add("Batch");
		tableHeader.add("RoutePath");
		tableHeader.add("Selected");
		tableHeader.add("Time Refreshed");
        
		Schedule schedule = (Schedule) solution;
		
        for (Batch batch : schedule.getBatchList()) 
        {
	        for (RoutePath routePath : batch.getRoutePathList()) 
	        {

	        	Vector<String> rowData = new Vector<String>();
	        	String strSelectedRoutePath = "";
	        	rowData.add(batch.getName());
	        	rowData.add(routePath.getPath());

	            if (schedule.getAllocationPathList() != null)
	            {	
	    	        for (AllocationPath allocationPath : schedule.getAllocationPathList() ) 
	    	        {
	    	        	if ((allocationPath.getBatch() !=  null) && (allocationPath.getRoutePath() !=  null))
	    	        	{
	    	        		if ((allocationPath.getBatch().getName().equals(batch.getName())) && (allocationPath.getRoutePath().getPath().equals(routePath.getPath())))
	    	        		{	
	    	        			strSelectedRoutePath = "Selected";
	    	        		} 
	    	        	}
	    	       }
	            } 
	            
	            rowData.add(strSelectedRoutePath);
	            rowData.add(strDate);	
	            tableData.add(rowData);

		    }
        }
		
        JTable table = new JTable(tableData, tableHeader);
        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.getColumnModel().getColumn(1).setMinWidth(300);
        table.getColumnModel().getColumn(2).setMinWidth(30);
        table.getColumnModel().getColumn(3).setMinWidth(30);
        
    	JScrollPane componentPanel = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
        return componentPanel;

    }

    private JScrollPane createComponentPanel3(Schedule solution) 
    {

    	
//    	JPanel componentPanel = new JPanel(new FlowLayout());
		
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = formatter.format(date);

		Vector<String> tableHeader = new Vector<String>();
		Vector<Vector<String>> tableData = new Vector<Vector<String>>();

		tableHeader.add("Batch");
		tableHeader.add("RoutePath");
		tableHeader.add("Segment");
		tableHeader.add("Length");
		tableHeader.add("FlowRate");
		tableHeader.add("Delay");
		tableHeader.add("Start Injection Time");
		tableHeader.add("End Injection Time");
		tableHeader.add("Start Delivery Time");
		tableHeader.add("End Delivery Time");
		tableHeader.add("Predecessor Start Delivery Time");
		tableHeader.add("Time Refreshed");
        
		Schedule schedule = (Schedule) solution;
		
        for (Batch batch : schedule.getBatchList()) 
        {
	        for (RoutePath routePath : batch.getRoutePathList()) 
	        {
		        for (Segment segment : routePath.getSegmentList()) 
		        {

		    		Vector<String> rowData = new Vector<String>();
		        	String strLength = "";
		        	String strFlowRate = "";
		        	String strDelay = "";
		        	String strStartTime1 = "";
		        	String strEndTime1 = "";
		        	String strStartTime2 = "";
		        	String strEndTime2 = "";
		        	String strPEndTime = "";
		        	rowData.add(batch.getName());
		        	rowData.add(routePath.getPath());
		        	rowData.add(segment.getName());
	
		            if (schedule.getAllocationList() != null)
		            {	
		    	        for (Allocation allocation : schedule.getAllocationList() ) 
		    	        {
		    	        	
		    	        	if ((allocation.getBatch() !=  null) && (allocation.getRoutePath() !=  null) && (allocation.getSegment() != null))
		    	        	{
		    	        		if ((allocation.getBatch().getName().equals(batch.getName())) && (allocation.getRoutePath().getPath().equals(routePath.getPath())) && (allocation.getSegment().getName().equals(segment.getName())))
		    	        		{	
	    			        		if (allocation.getSegment() != null)
	    			        		{	
	    			        			strLength = new Float(allocation.getSegment().getLength()).toString();
	    			        			strFlowRate = new Float(allocation.getSegment().getFlowRate()).toString();
	    			        		} 
	    			        		else
	    			        		{
	    			        			strLength= "";
	    			        			strFlowRate= "";
	    			        		}
	    			        		
	    			        		
		    	        			if (allocation.getDelay() != null)
		    			        	{	
		    			        		strDelay = allocation.getDelay().toString();
		    			        	} 
		    	        			else
		    			        	{
		    			        		strDelay = "";
		    			        	}

		    	        			if (allocation.getStartInjectionTime() != null)
		    			        	{	
		    			        		strStartTime1 = allocation.getStartInjectionTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strStartTime1 = "";
		    			        	}

		    	        			if (allocation.getEndInjectionTime() != null)
		    			        	{	
		    			        		strEndTime1 = allocation.getEndInjectionTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strEndTime1 = "";
		    			        	}
		    	        			
		    	        			if (allocation.getStartDeliveryTime() != null)
		    			        	{	
		    			        		strStartTime2 = allocation.getStartDeliveryTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strStartTime2 = "";
		    			        	}

		    	        			if (allocation.getEndDeliveryTime() != null)
		    			        	{	
		    			        		strEndTime2 = allocation.getEndDeliveryTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strEndTime2 = "";
		    			        	}

		    	        			if (allocation.getPredecessorsDoneDate() != null)
		    			        	{	
		    			        		strPEndTime = allocation.getPredecessorsDoneDate().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strPEndTime = "";
		    			        	}

		    	        		} 
		    	        	} 
		    	       }
		            } 
		            
		            rowData.add(strLength);
		            rowData.add(strFlowRate);
		            rowData.add(strDelay);
		            rowData.add(strStartTime1);
		            rowData.add(strEndTime1);
		            rowData.add(strStartTime2);
		            rowData.add(strEndTime2);
		            rowData.add(strPEndTime);	
		            rowData.add(strDate);	
		            tableData.add(rowData);
		            
		        }    
		    }
        }
		
        JTable table = new JTable(tableData, tableHeader);
        
    	JScrollPane componentPanel = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
        return componentPanel;

    }    


 
    private JScrollPane createComponentPanel4(Schedule solution) 
    {

    	
//    	JPanel componentPanel = new JPanel(new FlowLayout());
		
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = formatter.format(date);

		Vector<String> tableHeader = new Vector<String>();
		Vector<Vector<String>> tableData = new Vector<Vector<String>>();

		tableHeader.add("Batch");
		tableHeader.add("RoutePath");
		tableHeader.add("Segment");
		tableHeader.add("Length");
		tableHeader.add("FlowRate");
		tableHeader.add("Delay");
		tableHeader.add("Start Injection Time");
		tableHeader.add("End Injection Time");
		tableHeader.add("Start Delivery Time");
		tableHeader.add("End Delivery Time");
		tableHeader.add("Predecessor Start Delivery Time");
		tableHeader.add("Time Refreshed");
        
		Schedule schedule = (Schedule) solution;
		
        for (Batch batch : schedule.getBatchList()) 
        {
	        for (RoutePath routePath : batch.getRoutePathList()) 
	        {
		        for (Segment segment : routePath.getSegmentList()) 
		        {
		    		Vector<String> rowData = new Vector<String>();
		        	String strLength = "";
		        	String strFlowRate = "";
		        	String strDelay = "";
		        	String strStartTime1 = "";
		        	String strEndTime1 = "";
		        	String strStartTime2 = "";
		        	String strEndTime2 = "";
		        	String strPEndTime = "";
		        	rowData.add(batch.getName());
		        	rowData.add(routePath.getPath());
		        	rowData.add(segment.getName());
	
		            if (schedule.getAllocationList() != null)
		            {	
		    	        for (Allocation allocation : schedule.getAllocationList() ) 
		    	        {
		    	        	if ((allocation.getBatch() !=  null) && (allocation.getRoutePath() !=  null) && (allocation.getSegment() != null))
		    	        	{
		    	        		if ((allocation.getBatch().getName().equals(batch.getName())) && (allocation.getRoutePath().getPath().equals(routePath.getPath())) && (allocation.getSegment().getName().equals(segment.getName())))
		    	        		{	
	    			        		if (allocation.getSegment() != null)
	    			        		{	
	    			        			strLength = new Float(allocation.getSegment().getLength()).toString();
	    			        			strFlowRate = new Float(allocation.getSegment().getFlowRate()).toString();
	    			        		} 
	    			        		else
	    			        		{
	    			        			strLength= "";
	    			        			strFlowRate= "";
	    			        		}
	    			        		
	    			        		
		    	        			if (allocation.getDelay() != null)
		    			        	{	
		    			        		strDelay = allocation.getDelay().toString();
		    			        	} 
		    	        			else
		    			        	{
		    			        		strDelay = "";
		    			        	}

		    	        			if (allocation.getStartInjectionTime() != null)
		    			        	{	
		    			        		strStartTime1 = allocation.getStartInjectionTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strStartTime1 = "";
		    			        	}

		    	        			if (allocation.getEndInjectionTime() != null)
		    			        	{	
		    			        		strEndTime1 = allocation.getEndInjectionTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strEndTime1 = "";
		    			        	}
		    	        			
		    	        			if (allocation.getStartDeliveryTime() != null)
		    			        	{	
		    			        		strStartTime2 = allocation.getStartInjectionTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strStartTime2 = "";
		    			        	}

		    	        			if (allocation.getEndDeliveryTime() != null)
		    			        	{	
		    			        		strEndTime2 = allocation.getEndDeliveryTime().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strEndTime2 = "";
		    			        	}

		    	        			if (allocation.getPredecessorsDoneDate() != null)
		    			        	{	
		    			        		strPEndTime = allocation.getPredecessorsDoneDate().toString();
		    			        	} 
		    	        			else
		    			        	{
		    	        				strPEndTime = "";
		    			        	}

		    	        		} 
		    	        	} 
		    	       }
		            } 
		            

		            if  (!((strDelay.equals("") && strStartTime1.equals("") && strEndTime1.equals("") && strPEndTime.equals(""))))
		            {	
			            rowData.add(strLength);
			            rowData.add(strFlowRate);
			            rowData.add(strDelay);
			            rowData.add(strStartTime1);
			            rowData.add(strEndTime1);
			            rowData.add(strStartTime2);
			            rowData.add(strEndTime2);
			            rowData.add(strPEndTime);	
			            rowData.add(strDate);	
			            tableData.add(rowData);
		            }
		            
		        }    
		    }
        }
		
        JTable table = new JTable(tableData, tableHeader);
        
    	JScrollPane componentPanel = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
        return componentPanel;

    }    
    

}
