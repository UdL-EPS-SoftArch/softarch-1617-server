package cat.udl.eps.softarch.steps;

import cat.udl.eps.softarch.domain.BuyerCounterOffer;
import cat.udl.eps.softarch.domain.SellerOffer;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

/**
 * Created by xavier on 27/09/16.
 */
public class BuyerCounterOfferStepDefs extends AbstractStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(cat.udl.eps.softarch.steps.PostAdvertisementStepDefs.class);

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private ResultActions result;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
    }

    private BuyerCounterOffer buyerOffer= new BuyerCounterOffer();
    private BuyerCounterOffer buyerOffer2= new BuyerCounterOffer();
    private String agent = new String("agent");
    private SellerOffer sellerOffer= new SellerOffer();

    @When("^I post a new buyer counter offer with a lower price value (\\d+\\.\\d+)$")
    public void iPostANewBuyerCounterOfferWithALowerPriceValue(float newValue) throws Throwable {
        buyerOffer.setValue(110);
        buyerOffer.setAgent(agent);
        buyerOffer.setRespondsTo(sellerOffer);
        Assert.assertTrue(newValue < buyerOffer.getValue());
    }

    @Then("^A buyer counter-offer id has been generated$")
    public void aCounterOfferIdHasBeenGenerated() throws Throwable {
        Assert.assertFalse(Objects.equals(buyerOffer, buyerOffer2));
    }

    @And("^There is a new buyer counter offer with a lower price value (\\d+\\.\\d+)$")
    public void thereIsANewBuyerCounterOfferWithALowerPriceValue(float newValue) throws Throwable {
        Assert.assertFalse(Objects.equals(buyerOffer, null));
        buyerOffer2.setValue(newValue);
        Assert.assertTrue(buyerOffer2.getValue() == newValue);
        Assert.assertTrue(buyerOffer.getAgent().equals(agent));
        Assert.assertTrue(buyerOffer.getRespondsTo().equals(sellerOffer));
    }
}
