package kr.co.pdca;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.pdca.example.entity.Example;

public class CommonLogging {

	private static final Log logger = LogFactory.getLog(CommonLogging.class);

	protected void logging(List<Example> list) {
		logger.info("*******************************************");
		for (Example e : list) {
			logger.info(e.toString());
		}
		logger.info("*******************************************");
	}
}
