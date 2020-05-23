package com.ak98neon.currencyexchange.web;

import com.ak98neon.currencyexchange.model.Commission;
import com.ak98neon.currencyexchange.model.CurrencyId;
import com.ak98neon.currencyexchange.model.ExchangeRate;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRateDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import com.ak98neon.currencyexchange.web.dto.OperationType;
import com.ak98neon.currencyexchange.web.validate.ValidateCurrencyExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@WithMockUser(username = "admin", roles = "ADMIN")
public class ExchangerControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private ValidateCurrencyExchangeService currencyExchangeService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void setCommissionsToCurrencyPair_expect_is_created() throws Exception {
        final String fromTest = "test";
        final CommissionsDto testCommission = new CommissionsDto(1.0, fromTest, fromTest);

        Mockito.when(currencyExchangeService.setCommission(isA(CommissionsDto.class))).thenReturn(testCommission);

        mockMvc.perform(post("/api/commissions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(testCommission)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void getCommissionList_expect_is_ok() throws Exception {
        final String fromTest = "test";
        final List<Commission> commissionList = new ArrayList<>();
        final Commission testCommission = new Commission(new CurrencyId(fromTest, fromTest), 1.0);
        commissionList.add(testCommission);
        Mockito.when(currencyExchangeService.getCommission()).thenReturn(commissionList);

        final MvcResult mvcResult = mockMvc.perform(get("/api/commissions")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final CommissionsDto commission = objectMapper.readValue(contentAsString, CommissionsDto[].class)[0];

        Assertions.assertThat(testCommission.equals(commission.to())).isTrue();
    }

    @Test
    public void setExchangeRates_expect_is_ok() throws Exception {
        final String fromTest = "test";
        final ExchangeRateDto exchangeRate = new ExchangeRateDto(fromTest, fromTest, 1.0);

        Mockito.when(currencyExchangeService.setExchangeRate(isA(ExchangeRateDto.class))).thenReturn(exchangeRate);

        mockMvc.perform(get("/api/exchange-rates")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(exchangeRate)))
                .andExpect(status().isOk());
    }

    @Test
    public void getExchangeRatesList_expect_is_ok() throws Exception {
        final String fromTest = "test";
        final List<ExchangeRate> exchangeRatesList = new ArrayList<>();
        final ExchangeRate exchangeRate = new ExchangeRate(new CurrencyId(fromTest, fromTest), 1.0);
        exchangeRatesList.add(exchangeRate);
        Mockito.when(currencyExchangeService.getExchangeRate()).thenReturn(exchangeRatesList);

        final MvcResult mvcResult = mockMvc.perform(get("/api/exchange-rates")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final ExchangeRateDto exchangeRateDto = objectMapper.readValue(contentAsString, ExchangeRateDto[].class)[0];

        Assertions.assertThat(exchangeRate.equals(exchangeRateDto.to())).isTrue();
    }

    @Test
    public void exchangeRequestGive() throws Exception {
        final BigDecimal from = new BigDecimal("1");
        final ExchangeRequest exchangeRequest = new ExchangeRequest(from, from, "UAH", "USD", OperationType.GIVE);

        Mockito.when(currencyExchangeService.exchange(isA(ExchangeRequest.class))).thenReturn(exchangeRequest);

        mockMvc.perform(post("/api/exchange")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(exchangeRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void exchangeRequestGet() throws Exception {
        final BigDecimal from = new BigDecimal("1");
        final ExchangeRequest exchangeRequest = new ExchangeRequest(from, from, "UAH", "USD", OperationType.GET);

        Mockito.when(currencyExchangeService.exchange(isA(ExchangeRequest.class))).thenReturn(exchangeRequest);

        mockMvc.perform(post("/api/exchange")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(exchangeRequest)))
                .andExpect(status().isOk());
    }
}
