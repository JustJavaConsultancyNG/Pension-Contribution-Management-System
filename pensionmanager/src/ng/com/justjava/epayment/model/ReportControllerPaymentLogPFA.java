package ng.com.justjava.epayment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import org.openxava.annotations.*;


@View(members ="report;format;fromDate;toDate;corporate")
public class ReportControllerPaymentLogPFA {

	public enum Format {
		PDF, Excel, RTF
	};
    
	public enum Report {
		PaymentLog
	};
	

	@Required
	private Format format;

	@Required
	private Report report;
	
	@Required
	@DisplaySize(10)
	@Column(length=10)
	private Date fromDate;
	
	
	@Required
	@DisplaySize(10)
	@Column(length=10)
	private Date toDate;
	
	@DescriptionsList(descriptionProperties="name")
	@NoCreate
	@NoModify
	@ManyToOne
	private Corporate corporate;


	public Corporate getCorporate() {
		return corporate;
	}



	public void setCorporate(Corporate corporate) {
		this.corporate = corporate;
	}



	public Format getFormat() {
		return format;
	}



	public void setFormat(Format format) {
		this.format = format;
	}



	public Report getReport() {
		return report;
	}



	public void setReport(Report report) {
		this.report = report;
	}



	public Date getFromDate() {
		return fromDate;
	}



	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}



	public Date getToDate() {
		return toDate;
	}



	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
}