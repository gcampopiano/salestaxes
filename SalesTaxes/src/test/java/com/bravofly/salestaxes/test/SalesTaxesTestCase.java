package com.bravofly.salestaxes.test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bravofly.salestaxes.SalesTaxesException;
import com.bravofly.salestaxes.business.SalesTaxesBusinessService;
import com.bravofly.salestaxes.business.concrete.SalesTaxesBusinessServiceDelegate;
import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.concrete.Book;
import com.bravofly.salestaxes.model.concrete.ChocolateBar;
import com.bravofly.salestaxes.model.concrete.HeadachePills;
import com.bravofly.salestaxes.model.concrete.ImportedChocolateBox;
import com.bravofly.salestaxes.model.concrete.ImportedPerfume;
import com.bravofly.salestaxes.model.concrete.MusicCD;
import com.bravofly.salestaxes.model.concrete.Perfume;
import com.bravofly.salestaxes.model.concrete.Receipt;
import com.bravofly.salestaxes.model.concrete.builder.ReceiptBuilder;

public class SalesTaxesTestCase {

	private static final Logger logger = LoggerFactory.getLogger(SalesTaxesTestCase.class);

	private Receipt theReceipt;

	private SalesTaxesBusinessService businessService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.theReceipt = new Receipt();
		this.businessService = new SalesTaxesBusinessServiceDelegate();
	}

	@After
	public void tearDown() throws Exception {
		this.theReceipt = null;
		this.businessService = null;
	}

	@Test
	public void testInputOne() {

		this.theReceipt = (new ReceiptBuilder()).good(new Book(new BigDecimal("12.49")))
				.good(new MusicCD(new BigDecimal("14.99"))).good(new ChocolateBar(new BigDecimal("0.85"))).build();

		try {
			this.businessService.compileReceipt(theReceipt);
		} catch (SalesTaxesException e) {
			logger.error("Following exception occurred compiling receipt:", e);
			Assert.fail(e.getMessage());
		}

		Map<String, List<Good>> goodMap = this.theReceipt.getGoodMap();
		BigDecimal totalSalesTaxes = this.theReceipt.getTotalSalesTaxes();
		BigDecimal totalAmount = this.theReceipt.getTotalAmount();

		Assert.assertNotNull(goodMap);
		Assert.assertTrue(!goodMap.isEmpty());

		Assert.assertTrue(goodMap.containsKey(Book.class.getCanonicalName()));
		Assert.assertTrue(goodMap.containsKey(MusicCD.class.getCanonicalName()));
		Assert.assertTrue(goodMap.containsKey(ChocolateBar.class.getCanonicalName()));

		Assert.assertEquals(1, goodMap.get(Book.class.getCanonicalName()).size());
		Assert.assertEquals(1, goodMap.get(MusicCD.class.getCanonicalName()).size());
		Assert.assertEquals(1, goodMap.get(ChocolateBar.class.getCanonicalName()).size());

		Book aBook = (Book) (goodMap.get(Book.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("12.49"), aBook.getTaxedPrice());

		MusicCD aMusicCD = (MusicCD) (goodMap.get(MusicCD.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("16.49"), aMusicCD.getTaxedPrice());

		ChocolateBar aChocolateBar = (ChocolateBar) (goodMap.get(ChocolateBar.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("0.85"), aChocolateBar.getTaxedPrice());

		Assert.assertEquals(new BigDecimal("1.50"), totalSalesTaxes);
		Assert.assertEquals(new BigDecimal("29.83"), totalAmount);

		logger.info("Follows the receipt: " + theReceipt.toString());
	}

	@Test
	public void testInputTwo() {

		this.theReceipt = (new ReceiptBuilder()).good(new ImportedChocolateBox(new BigDecimal("10.00")))
				.good(new ImportedPerfume(new BigDecimal("47.50"))).build();

		try {
			this.businessService.compileReceipt(theReceipt);
		} catch (SalesTaxesException e) {
			logger.error("Following exception occurred compiling receipt:", e);
			Assert.fail(e.getMessage());
		}

		Map<String, List<Good>> goodMap = this.theReceipt.getGoodMap();
		BigDecimal totalSalesTaxes = this.theReceipt.getTotalSalesTaxes();
		BigDecimal totalAmount = this.theReceipt.getTotalAmount();

		Assert.assertNotNull(goodMap);
		Assert.assertTrue(!goodMap.isEmpty());

		Assert.assertTrue(goodMap.containsKey(ImportedChocolateBox.class.getCanonicalName()));
		Assert.assertTrue(goodMap.containsKey(ImportedPerfume.class.getCanonicalName()));

		Assert.assertEquals(1, goodMap.get(ImportedChocolateBox.class.getCanonicalName()).size());
		Assert.assertEquals(1, goodMap.get(ImportedPerfume.class.getCanonicalName()).size());

		ImportedChocolateBox icb = (ImportedChocolateBox) (goodMap.get(ImportedChocolateBox.class.getCanonicalName())
				.get(0));
		Assert.assertEquals(new BigDecimal("10.50"), icb.getTaxedPrice());

		ImportedPerfume ip = (ImportedPerfume) (goodMap.get(ImportedPerfume.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("54.65"), ip.getTaxedPrice());

		Assert.assertEquals(new BigDecimal("7.65"), totalSalesTaxes);
		Assert.assertEquals(new BigDecimal("65.15"), totalAmount);

		logger.info("Follows the receipt: " + theReceipt.toString());
	}

	@Test
	public void testInputThree() {
		this.theReceipt = (new ReceiptBuilder()).good(new ImportedPerfume(new BigDecimal("27.99")))
				.good(new Perfume(new BigDecimal("18.99"))).good(new HeadachePills(new BigDecimal("9.75")))
				.good(new ImportedChocolateBox(new BigDecimal("11.25"))).build();

		try {
			this.businessService.compileReceipt(theReceipt);
		} catch (SalesTaxesException e) {
			logger.error("Following exception occurred compiling receipt:", e);
			Assert.fail(e.getMessage());
		}

		Map<String, List<Good>> goodMap = this.theReceipt.getGoodMap();
		BigDecimal totalSalesTaxes = this.theReceipt.getTotalSalesTaxes();
		BigDecimal totalAmount = this.theReceipt.getTotalAmount();

		Assert.assertNotNull(goodMap);
		Assert.assertTrue(!goodMap.isEmpty());

		Assert.assertTrue(goodMap.containsKey(ImportedPerfume.class.getCanonicalName()));
		Assert.assertEquals(1, goodMap.get(ImportedPerfume.class.getCanonicalName()).size());

		Assert.assertTrue(goodMap.containsKey(Perfume.class.getCanonicalName()));
		Assert.assertEquals(1, goodMap.get(Perfume.class.getCanonicalName()).size());

		Assert.assertTrue(goodMap.containsKey(HeadachePills.class.getCanonicalName()));
		Assert.assertEquals(1, goodMap.get(HeadachePills.class.getCanonicalName()).size());

		Assert.assertTrue(goodMap.containsKey(ImportedChocolateBox.class.getCanonicalName()));
		Assert.assertEquals(1, goodMap.get(ImportedChocolateBox.class.getCanonicalName()).size());

		ImportedPerfume ip = (ImportedPerfume) (goodMap.get(ImportedPerfume.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("32.19"), ip.getTaxedPrice());

		Perfume p = (Perfume) (goodMap.get(Perfume.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("20.89"), p.getTaxedPrice());

		HeadachePills hp = (HeadachePills) (goodMap.get(HeadachePills.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("9.75"), hp.getTaxedPrice());
		
		ImportedChocolateBox icb = (ImportedChocolateBox) (goodMap.get(ImportedChocolateBox.class.getCanonicalName()).get(0));
		Assert.assertEquals(new BigDecimal("11.85"), icb.getTaxedPrice());		

		Assert.assertEquals(new BigDecimal("6.70"), totalSalesTaxes);
		Assert.assertEquals(new BigDecimal("74.68"), totalAmount);
		
		logger.info("Follows the receipt: " + theReceipt.toString());
	}

}
