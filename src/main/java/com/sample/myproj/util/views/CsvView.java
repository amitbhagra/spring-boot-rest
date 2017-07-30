package com.sample.myproj.util.views;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.sample.myproj.entities.User;

public class CsvView extends AbstractCsvView {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String filename = "users_" + (new Date()) + ".csv";
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		Iterable<User> users = (Iterable<User>) model.get("users");
	    String[] header = (String[]) model.get("headers");
	    //String[] header = {"Firstname","LastName","LastName","JobTitle","Company","Address","City","Country", "PhoneNumber"};
	    ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
	            CsvPreference.STANDARD_PREFERENCE);

	    csvWriter.writeHeader(header);

	    for(User user : users){
	    	this.log.info("Writing CSV for user {}", user.getFirstName() );
	        csvWriter.write(user, header);
	    }
	    csvWriter.close();


	}

}
