package org.activiti.cloud.audit;

import org.activiti.cloud.services.metadata.MetadataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaAuditApplication.class)
@DirtiesContext
public class ApplicationIT {

	@Autowired
	private MetadataService metadataService;

	@Test
	public void contextLoads() throws Exception {
		Assert.assertNotNull(metadataService);
	}

}