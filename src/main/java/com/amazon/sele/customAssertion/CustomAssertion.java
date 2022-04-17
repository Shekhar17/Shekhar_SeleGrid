package com.amazon.sele.customAssertion;

import java.util.List;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Lists;
import com.amazon.sele.reporterLogger.ReportFactory;

public class CustomAssertion extends Assertion {
	  
	  private List<String> assert_messages = Lists.newArrayList();
	   
	    @Override
	    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
	    	ReportFactory.getInstance().error(assertCommand.getMessage());
	    	assert_messages.add("OnlyOnAssertFailure:" + assertCommand.getMessage());
	      
	    }   
	    
	}