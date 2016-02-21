package com.bravofly.salestaxes.business.concrete;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bravofly.salestaxes.Constants;
import com.bravofly.salestaxes.SalesTaxesException;
import com.bravofly.salestaxes.business.SalesTaxesBusinessService;
import com.bravofly.salestaxes.business.concrete.drools.GoodControlFact;
import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.concrete.Receipt;
import com.bravofly.salestaxes.model.concrete.decorator.ReceiptDecorator;

/**
 * Triggers the business rule engine to compile the receipt
 * 
 * @author guido.campopiano
 *
 */

@SuppressWarnings("deprecation")
public class SalesTaxesBusinessServiceDelegate implements SalesTaxesBusinessService {

	private static final Logger logger = LoggerFactory.getLogger(SalesTaxesBusinessServiceDelegate.class);

	public static final String SALES_TAXES_KS = "SalesTaxesKS";

	public static final String[] drlFiles = { "applicableTaxes.drl" };

	public static final KnowledgeBase kbase = initKnowledgeBase();

	@Override
	public void compileReceipt(Receipt theReceipt) throws SalesTaxesException {

		List<Good> flatGoodList = (new ReceiptDecorator(theReceipt)).retrieveFlatGoodList();

		this.applyTaxesToGoodList(flatGoodList);

		BigDecimal totalSalesTaxes = new BigDecimal(Constants.ZERO);
		BigDecimal totalAmount = new BigDecimal(Constants.ZERO);
		for (Good good : flatGoodList) {
			totalSalesTaxes = totalSalesTaxes.add(good.getAppliedTaxesAmount());
			totalAmount = totalAmount.add(good.getTaxedPrice());
		}

		theReceipt.setTotalSalesTaxes(totalSalesTaxes);
		theReceipt.setTotalAmount(totalAmount);
	}

	private static final KnowledgeBase initKnowledgeBase() {
		logger.info("Initing knowledge base...");
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		for (String ruleFile : drlFiles) {
			logger.info(StringUtils.join("Loading file ", ruleFile, " from file system..."));
			kbuilder.add(ResourceFactory.newClassPathResource(ruleFile, SalesTaxesBusinessServiceDelegate.class),
					ResourceType.DRL);
		}

		Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
		kbase.addKnowledgePackages(pkgs);
		return kbase;
	}

	private final void applyTaxesToGoodList(List<Good> flatGoodList) throws SalesTaxesException {
		KieSession ksession = null;
		ArrayList<FactHandle> factHandleList = new ArrayList<>();
		try {

			ksession = kbase.newStatefulKnowledgeSession();
			ksession.setGlobal("logger", logger);

			for (Good good : flatGoodList) {
				factHandleList.add(ksession.insert(new GoodControlFact(good)));
			}

			ksession.fireAllRules();
		} catch (Exception e) {
			logger.error("Following exception occurred applying taxes to good list: ", e);
			throw new SalesTaxesException(e);
		} finally {
			if (ksession != null) {
				logger.info("Disposing kession...");
				ksession.dispose();
			}
		}
	}

}