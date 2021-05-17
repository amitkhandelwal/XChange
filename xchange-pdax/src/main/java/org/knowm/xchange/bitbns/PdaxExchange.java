package org.knowm.xchange.bitbns;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitbns.service.PdaxMarketDataService;
import org.knowm.xchange.bitbns.service.PdaxTradeService;
import org.knowm.xchange.utils.nonce.TimestampIncrementingNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class PdaxExchange extends BaseExchange implements Exchange {

  private final SynchronizedValueFactory<Long> nonceFactory =
      new TimestampIncrementingNonceFactory();

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://services-stage.pdax.ph/api/exchange");
    exchangeSpecification.setHost("services-stage.pdax.ph");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("pdax");
    exchangeSpecification.setExchangeDescription("Pdax Exchange custom Exchange");
    return exchangeSpecification;
  }

  @Override
  protected void initServices() {
    this.marketDataService = new PdaxMarketDataService(this);
    this.tradeService = new PdaxTradeService(this);
  }

  //	  @Override
  //	  public void applySpecification(ExchangeSpecification exchangeSpecification) {
  //
  //	    super.applySpecification(exchangeSpecification);
  //
  //	    concludeHostParams(exchangeSpecification);
  //	  }
  //
  //	  /** Adjust host parameters depending on exchange specific parameters */
  //	  private static void concludeHostParams(ExchangeSpecification exchangeSpecification) {
  //
  //	    if (exchangeSpecification.getExchangeSpecificParameters() != null) {
  //	      if (exchangeSpecification.getExchangeSpecificParametersItem("Use_Sandbox").equals(true))
  // {
  //	        exchangeSpecification.setSslUri("https://api.bitbns.com/api/trade");
  //	        exchangeSpecification.setHost("api.bitbns.com");
  //	      }
  //	    }
  //	  }

}
