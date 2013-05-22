package kr.co.insoft.core.util;

import kr.co.insoft.AbstractTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonPropertiesUtilTest extends AbstractTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CommonPropertiesUtilTest.class);
	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;
	
	@Test
	public void propertieGetTest() {
		logger.info("**************************************");
		logger.info("DEFAULT_PAGE_SIZE {}", commonPropertiesUtil.getInt("DEFAULT_PAGE_SIZE"));
		logger.info("DEFAULT_PAGING_SIZE {}", commonPropertiesUtil.getInt("DEFAULT_PAGING_SIZE"));
		logger.info("**************************************");
	}

}
