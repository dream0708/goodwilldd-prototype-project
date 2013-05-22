package kr.co.insoft;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;
import kr.co.insoft.board.service.ICommonBoard;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTest extends AbstractTest {

	private static final Logger logger = LoggerFactory
			.getLogger(DatabaseTest.class);
	
	@Autowired
	ICommonBoard<DefaultDetailEntity> commonBoard;

	DefaultDetailEntity entity;

	@Before
	public void init() {
		entity = new DefaultDetailEntity();
		entity.setBoardName("board1");
		entity.setContent("Test Content");
		entity.setEnabled(1);
		entity.setRegister("goodwilldd");
		entity.setSubject("Test Subject");
		commonBoard.doSave(entity);
	}

	@Test
	public void getListCount() throws Exception {
		logger.info("***** count : " + commonBoard.getListCount());
	}

	@Test
	public void getList() throws Exception {
		commonBoard.getList();
		for (DefaultDetailEntity details : commonBoard.getList()) {
			logger.info("***** details : " + details.toDebug());
		}
	}

	@Test
	public void getListWithPaging() throws Exception {
		DefaultListEntity<DefaultDetailEntity> result = commonBoard
				.getListWithPaging();
		logger.info("***** count : " + result.getCount());
		for (DefaultDetailEntity details : result.getList()) {
			logger.info("***** details : " + details.toDebug());
		}
	}

	@Test
	public void getDetails() throws Exception {
		logger.info(commonBoard.getDetails(1).toDebug());
	}

	@Test
	public void doSave() throws Exception {
		commonBoard.doSave(entity);
	}
}
