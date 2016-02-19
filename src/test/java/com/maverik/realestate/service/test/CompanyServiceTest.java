/**
 * 
 */
package com.maverik.realestate.service.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.service.CompanyManagementService;
import com.maverik.realestate.view.bean.CompanyBean;

/**
 * @author jorge
 *
 */
@ContextConfiguration(locations = { "classpath:application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyServiceTest {

    /*
     * 
     * Integration Test for Company Service
     * 
     * CRUD operations
     */

    @Autowired
    private CompanyManagementService companyService;

    private static CompanyBean company;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAInsertCompany() throws GenericException {
	CompanyBean c = new CompanyBean();
	c.setCompanyName("Dummy company");
	c.setAddress("Address field");
	Assert.assertNotNull(companyService);
	company = companyService.insertCompany(c);
	Assert.assertNotNull(company);
	Assert.assertNotNull(company.getId());
    }

    @Test
    public void testBFindAll() throws GenericException {
	List<CompanyBean> lst = companyService.findAllCompanies();
	Assert.assertNotNull(lst);
	Assert.assertNotEquals(0, lst.size());
    }

    @Test
    public void testCSoftDelete() throws GenericException {
	companyService.softDeleteCompany(company.getId());
	Assert.assertNotNull(company);
    }

    @Test
    public void testDFindAndUpdate() throws GenericException {
	CompanyBean c = companyService.findByCompany(company.getId());
	c.setAddress("Address updated");
	c = companyService.updateCompany(c);
	Assert.assertNotEquals("Address field", c.getAddress());
	Assert.assertEquals(company.getId(), c.getId());
    }

    @Test
    public void testEDeleteCompany() throws GenericException {
	companyService.deleteCompany(company);
	Assert.assertNull(companyService.findByCompany(company.getId()));
    }
}
