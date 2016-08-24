package ng.com.justjava.epayment.action;

import java.util.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import ng.com.justjava.epayment.model.*;
import ng.com.justjava.epayment.model.Payment.Status;
import ng.com.justjava.epayment.model.ReportControllerPaymentLogPFA.Format;
import ng.com.justjava.epayment.model.ReportControllerPaymentLogPFA.Report;
import ng.com.justjava.epayment.utility.UserManager;

import org.openxava.actions.*;
import org.openxava.model.MapFacade;
import org.openxava.util.Is;



public class ReportActionPaymentLogPFA extends JasperReportBaseAction implements IChangeControllersAction{

	Report report = null;
	Format format = null;
	Date fromDate = null;
	Date toDate = null;
	//Months toMonth = null;
	
	String reportName = null;
	
	Collection dataSource = null;
	
	Status status = null;
	//Map corporateKeyValue = new HashMap();
	//Map custodianKeyValue = new HashMap();
	Map administratorKeyValue = new HashMap();
	//Map monthKeyValue = new HashMap();
	//Corporate corporate = new Corporate();
	
	Map corporateKeyValue = new HashMap();
	
	@Override
	public String getFormat() throws Exception {
	
		// TODO Auto-generated method stub
		String result = "pdf";
		
		switch (format) {
		case Excel:
			result = "excel";
			break;
		case PDF:
			result = "pdf";
			break;
		default:
			break;
		}
		return result;
	}


	
	public String[] getNextControllers() throws Exception {
		// TODO Auto-generated method stub
		return PREVIOUS_CONTROLLERS;
	}

	@Override
	protected JRDataSource getDataSource() throws Exception {
		// TODO Auto-generated method stub
		return new JRBeanCollectionDataSource(dataSource);
	}

	@Override
	protected String getJRXML() throws Exception {
		return reportName;
	} 

	@Override
	protected Map getParameters() throws Exception {
//		Corporate corporate = UserManager.getCorporateOfLoginUser();
	//	RSAHolder holder = new RSAHolder();
		Map parameters = new HashMap<String, String>();
		//parameters.put("corporate", "The Corporate");
		//parameters.put("rc", corporate.getRcNo());
		//parameters.put("userCompany", corporate.getName());
		//parameters.put("month", "March");
		return parameters;
	}
	
	@Override
	public boolean inNewWindow() {

		boolean result2 = true;
		
		System.out.println("1 The datasource here ===="+dataSource);
		
		if(dataSource==null || dataSource.isEmpty()){
			result2 = false;
			System.out.println("2 The datasource here ===="+dataSource);
		}
		

		return result2;
	}
	
	@Override
	public String getForwardURI() {
		String result2 = "/xava/report.pdf?time=" + System.currentTimeMillis();
		if(dataSource==null || dataSource.isEmpty()){
			result2 = null;
	}

		return result2;
	}
	
	
	public void execute() throws Exception {
		System.out.println("Entering execute method");
		 report = (Report) getView().getValue("report");
		 format  = (Format)getView().getValue("format");
		 
		 fromDate = (Date)getView().getValue("fromDate");
		 toDate = (Date)getView().getValue("toDate");
		 System.out.println("The fromDate is "+fromDate);
		 System.out.println("The toDate is "+toDate);
		 		 
		 corporateKeyValue = (Map)getView().getValue("corporate");
		 
		if (Is.empty(report)||Is.empty(format)||Is.empty(fromDate)||Is.empty(toDate)) {
				addError("Compulsory field must be selected");
				return;
			}
		
		
		System.out.println("=== RSAHolders === Report Action=="
				+ report+"      "+fromDate);		
		
		Long corporateId = 0L;		 
		if ( corporateKeyValue != null){
			 corporateId = (Long)corporateKeyValue.get("id");
		}
		
		
		System.out.println("The report selected is "+report);
		System.out.println("The corporate selected is "+corporateId);
		
		corporateId = (corporateId==null?0:corporateId);

		PensionFundAdministrator pfa = UserManager.getPFAOfLoginUser();
		

			//	reportName ="paymentlog2.jrxml";
		reportName ="report_temp_pfa.jrxml";
				System.out.println("=== RSAHolders === Report Action=="
						+ dataSource);		
				
					dataSource = PaymentLog.getPaymentLogPFA(pfa.getId(), corporateId,fromDate,toDate);
				
						

				System.out.println("=== RSAHolders === After Report Action== "
						+ "Report Action=="+ dataSource);
				if(dataSource.isEmpty()){
					addError("No data for the selected parameter");	
					//break;
					return;
					
				}
			
		
		System.out.println("Test=============");
		
		super.execute();
	}



}
