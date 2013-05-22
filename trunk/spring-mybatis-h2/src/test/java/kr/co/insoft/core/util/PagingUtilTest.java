package kr.co.insoft.core.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.springframework.test.web.ModelAndViewAssert.*;
import kr.co.insoft.AbstractTest;
import kr.co.insoft.board.entity.PagingEntity;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PagingUtilTest extends AbstractTest {

	private static final Logger logger = LoggerFactory
			.getLogger(PagingUtilTest.class);

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;
	
	@Test
	public void test1() {
		int pageSize = commonPropertiesUtil.getInt("DEFAULT_PAGE_SIZE");
		int pagingSize = commonPropertiesUtil.getInt("DEFAULT_PAGING_SIZE");
		int currentPage = 3;
		int totalCount = 33;
		PagingEntity pu = new PagingEntity(totalCount, currentPage, pageSize, pagingSize);
		logger.info("**************************************");
		assertEquals(currentPage, pu.getCurrentPageNum());
		assertEquals(totalCount, pu.getTotalCount());
		logger.info("previous block is {}", pu.getPreviousBlock());
		logger.info("previous page is {}", pu.getPrePageNum());
		for(int page : pu.getPages()) {
			logger.info("page is {}", page);
		}
		logger.info("next page is {}", pu.getNextPageNum());
		logger.info("next block is {}", pu.getNextBlock());
		logger.info("**************************************");
	}
}
