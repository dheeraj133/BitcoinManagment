package com.journaldev.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.spring.model.Employee;

/**
 * Handles requests for the Employee service.
 */
@RestController
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	@RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public String getDummyEmployee( @PathVariable("time") String time) {
		try {
            //read url
			System.out.println("xyz"+time);
            URL url = new URL("https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=all");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();

            JSONObject jsonOb = new JSONObject(line);
            JSONObject arrJson=jsonOb.getJSONObject("data");
            JSONArray pricedData=arrJson.getJSONArray("prices");
            for(int i=0;i<pricedData.length();i++){
            //System.out.println(pricedData.get(i));
            	
            	System.out.println(pricedData.getJSONObject(i).getString("time"));
            if(pricedData.getJSONObject(i).getString("time").equals(time)){
            	System.out.println(pricedData.getJSONObject(i).getString("time"));
            	//return time;
            	String pricing =pricedData.getJSONObject(i).getString("price");
            	return pricing ;
            }
            
            }
           // System.out.println(line.length());
          /*  if (line.length() > 0) {
                return line;
            }*/
            reader.close();
        } catch (Exception e) {
           // System.out.println(e.getMessage());
        }
        return null;
    }
	
	
	@RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody double getEmployee(@PathVariable("time1") String time1,@PathVariable("time2") String time2) {
		double v1 = 0,v2 = 0,v3 = 0;
    	try {
            //read url
            URL url = new URL("https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=all");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();

            JSONObject jsonOb = new JSONObject(line);
            JSONObject arrJson=jsonOb.getJSONObject("data");
            JSONArray pricedData=arrJson.getJSONArray("prices");
            
            for(int i=0;i<pricedData.length();i++){
            //System.out.println(pricedData.get(i));
            	
            	System.out.println(pricedData.getJSONObject(i).getString("time"));
            if(pricedData.getJSONObject(i).getString("time").equals(time1)){
            	//System.out.println(pricedData.getJSONObject(i).getString("time"));
            	 v1= Double.parseDouble(pricedData.getJSONObject(i).getString("price"));
            }
            	System.out.println(pricedData.getJSONObject(i).getString("time"));
                if(pricedData.getJSONObject(i).getString("time").equals(time1))
                {
                	System.out.println(pricedData.getJSONObject(i).getString("time"));
                	 v2= Double.parseDouble(pricedData.getJSONObject(i).getString("price"));
                	}
                v3= (v1+v2)/2;
                           }
            
            
            
           // System.out.println(line.length());
          /*  if (line.length() > 0) {
                return line;
            }*/
            reader.close();
        } catch (Exception e) {
           // System.out.println(e.getMessage());
        }
        return v3;
    }
            
	
}
