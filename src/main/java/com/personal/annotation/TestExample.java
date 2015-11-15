package com.personal.annotation;

import com.personal.annotation.TesterInfo.Priority;

@TesterInfo(priority = Priority.HIGH, createdBy = "piyush.com", tags = {
		"sales", "test" })
public class TestExample {

	@Test
	void testA() {
		if (true)
			throw new RuntimeException("This test always failed");
	}

	@Test(enabled = false)
	void testB() {
	}

	@Test(enabled = true)
	void testC() {
		if (10 > 1) {
			// do nothing, this test always passed.
		}
	}

}