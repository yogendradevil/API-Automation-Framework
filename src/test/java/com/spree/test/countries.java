package com.spree.test;

import org.testng.annotations.Test;

import com.spree.testdata.spreecom_testdata;
import com.spree.util.RestRequestUtil;
import com.spree.util.RestResponseUtil;

public class countries extends BaseTest{

	@Test(priority = 1)
	public void T01_getDefaultCountries() {
		test = extent.createTest("Test case 1", "Get default countries");
		res = RestRequestUtil.getResponse("/countries/default");
		RestResponseUtil.checkStatusIs200(res);
		String isoname = RestResponseUtil.getValue(res, "data.attributes.iso_name");
		System.out.println("ISO Name is =>  " + isoname);
	}

	@Test(priority = 2, dataProvider = "country_iso", dataProviderClass = spreecom_testdata.class)
	public void T02_retrieveaCountry(String iso, String iso_name, String iso3) {
		test = extent.createTest("Test case 2", "Retrieve a country");
		res = RestRequestUtil.getResponse("/countries/" + iso);
		RestResponseUtil.checkStatusIs200(res);
		String isoname = RestResponseUtil.getValue(res, "data.attributes.iso_name");
		System.out.println("ISO Name is =>  " + isoname);
	}
    
    @Test(priority=3)
    public void T03_listallCountries() {
    	test = extent.createTest("Test case 3", "List all countries");
        res = RestRequestUtil.getResponse("/countries");
        RestResponseUtil.checkStatusIs200(res);
        String isoname = RestResponseUtil.getValue(res, "data.attributes.iso_name");
		System.out.println("ISO Name is =>  " + isoname);
    }

}
