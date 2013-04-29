package kr.co.insoft;

import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class AnnotationScanTest extends AbstractTest {

	@Test
	public void annotationScan() {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Repository.class));
		scanner.addExcludeFilter(new AnnotationTypeFilter(Controller.class));
		scanner.addExcludeFilter(new AnnotationTypeFilter(Service.class));
		for (BeanDefinition bd : scanner
				.findCandidateComponents("kr.co.insoft"))
			System.out.println(bd.getBeanClassName());
	}
}
