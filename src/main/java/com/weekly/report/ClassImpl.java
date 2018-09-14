package com.weekly.report;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ClassImpl implements Impl {

	@Override
	public void get() throws IOException {
		if(true)
		throw new FileNotFoundException();
		
		// TODO Auto-generated method stub
		return ;
	}

}
