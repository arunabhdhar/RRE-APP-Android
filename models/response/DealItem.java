
package org.rehab.app.models.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DealItem implements Serializable{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("property_name")
    private String propertyName;
    @JsonProperty("property_strt_address")
    private String propertyStrtAddress;
    @JsonProperty("property_bedrooms")
    private Integer propertyBedrooms;
    @JsonProperty("property_baths")
    private Integer propertyBaths;
    @JsonProperty("property_sq_feet")
    private String propertySqFeet;
    @JsonProperty("property_built_year")
    private Integer propertyBuiltYear;
    @JsonProperty("property_image")
    private String propertyImage;
    @JsonProperty("agent_name")
    private String agentName;
    @JsonProperty("purchase_price")
    private String purchasePrice;
    @JsonProperty("closeing_cost")
    private String closeingCost;
    @JsonProperty("holding_cost")
    private String holdingCost;
    @JsonProperty("include_closing_holding_cost")
    private String includeClosingHoldingCost;
    @JsonProperty("rehab_budget")
    private String rehabBudget;
    @JsonProperty("project_rehab_period")
    private String projectRehabPeriod;
    @JsonProperty("financing_cash")
    private String financingCash;
    @JsonProperty("arv_cost")
    private String arvCost;
    @JsonProperty("financed_cost_max_per")
    private String financedCostMaxPer;
    @JsonProperty("origination_discount_points")
    private String originationDiscountPoints;
    @JsonProperty("othr_cls_cost_paid_lander")
    private String othrClsCostPaidLander;
    @JsonProperty("costs_upfront_backend")
    private String costsUpfrontBackend;
    @JsonProperty("interest_rate")
    private String interestRate;
    @JsonProperty("interest_payment_during_rehab")
    private String interestPaymentDuringRehab;
    @JsonProperty("split_backend_profits_with_lender")
    private String splitBackendProfitsWithLender;
    @JsonProperty("pre_tax_profit_does_lender_get")
    private String preTaxProfitDoesLenderGet;
    @JsonProperty("arv_for_flip_strgy1")
    private String arvForFlipStrgy1;
    @JsonProperty("months_complete_sale_after_rehab")
    private String monthsCompleteSaleAfterRehab;
    @JsonProperty("total_capital_needed_strgy1")
    private String totalCapitalNeededStrgy1;
    @JsonProperty("max_dollar_financed_strgy1")
    private String maxDollarFinancedStrgy1;
    @JsonProperty("actual_financed_not_closhold_strgy1")
    private String actualFinancedNotClosholdStrgy1;
    @JsonProperty("clos_hold_interest_loan_strgy1")
    private String closHoldInterestLoanStrgy1;
    @JsonProperty("total_loan_amount_strgy1")
    private String totalLoanAmountStrgy1;
    @JsonProperty("cash_required_overlife_project_strgy1")
    private String cashRequiredOverlifeProjectStrgy1;
    @JsonProperty("total_allin_costs_end_rehab_strgy1")
    private String totalAllinCostsEndRehabStrgy1;
    @JsonProperty("arv_per_strgy1")
    private String arvPerStrgy1;
    @JsonProperty("projected_resale_price_strgy1")
    private String projectedResalePriceStrgy1;
    @JsonProperty("projected_cost_sale_per_strgy1")
    private String projectedCostSalePerStrgy1;
    @JsonProperty("projected_profit_after_lender_split_strgy1")
    private String projectedProfitAfterLenderSplitStrgy1;
    @JsonProperty("roci_strgy1")
    private String rociStrgy1;
    @JsonProperty("roi_annualized_strgy1")
    private String roiAnnualizedStrgy1;
    @JsonProperty("arv_rent_strgy2")
    private String arvRentStrgy2;
    @JsonProperty("months_rent_after_rehab_period_over_strgy2")
    private String monthsRentAfterRehabPeriodOverStrgy2;
    @JsonProperty("total_capital_needed_strgy2")
    private String totalCapitalNeededStrgy2;
    @JsonProperty("max_dollar_canbe_financed_strgy2")
    private String maxDollarCanbeFinancedStrgy2;
    @JsonProperty("actual_financed_not_closholding_strgy2")
    private String actualFinancedNotClosholdingStrgy2;
    @JsonProperty("closholding_costs_interest_added_loan_strgy2")
    private String closholdingCostsInterestAddedLoanStrgy2;
    @JsonProperty("total_loan_amount_strgy2")
    private String totalLoanAmountStrgy2;
    @JsonProperty("cash_required_strgy2")
    private String cashRequiredStrgy2;
    @JsonProperty("total_allin_costs_end_rehab_strgy2")
    private String totalAllinCostsEndRehabStrgy2;
    @JsonProperty("arv_per_strgy2")
    private String arvPerStrgy2;
    @JsonProperty("projected_operating_income_strgy2")
    private String projectedOperatingIncomeStrgy2;
    @JsonProperty("projected_operating_expenses_strgy2")
    private String projectedOperatingExpensesStrgy2;
    @JsonProperty("net_operating_income_monthly_strgy2")
    private String netOperatingIncomeMonthlyStrgy2;
    @JsonProperty("refinance_permanent_financing_strgy2")
    private String refinancePermanentFinancingStrgy2;
    @JsonProperty("refinance_per_appraisal_arv_strgy2")
    private String refinancePerAppraisalArvStrgy2;
    @JsonProperty("new_mortgage_rate_strgy2")
    private String newMortgageRateStrgy2;
    @JsonProperty("amortization_years_interest_only_strgy2")
    private String amortizationYearsInterestOnlyStrgy2;
    @JsonProperty("refi_discount_points_misc_costs_strgy2")
    private String refiDiscountPointsMiscCostsStrgy2;
    @JsonProperty("new_mtge_pmnt_monthly_strgy2")
    private String newMtgePmntMonthlyStrgy2;
    @JsonProperty("refi_loan_amount_strgy2")
    private String refiLoanAmountStrgy2;
    @JsonProperty("cash_out_refi_strgy2")
    private String cashOutRefiStrgy2;
    @JsonProperty("profit_refi_after_lender_split_strgy2")
    private String profitRefiAfterLenderSplitStrgy2;
    @JsonProperty("roi_cash_invested_annualized_strgy2")
    private String roiCashInvestedAnnualizedStrgy2;
    @JsonProperty("original_money_tiedup_after_refi_strgy2")
    private String originalMoneyTiedupAfterRefiStrgy2;
    @JsonProperty("equity_left_deal_after_refi_strgy2")
    private String equityLeftDealAfterRefiStrgy2;
    @JsonProperty("cash_flow_monthly_pretax_strgy2")
    private String cashFlowMonthlyPretaxStrgy2;
    @JsonProperty("coc_annual_pretax_strgy2")
    private String cocAnnualPretaxStrgy2;
    @JsonProperty("property_dcr_strgy2")
    private String propertyDcrStrgy2;
    @JsonProperty("payback_period_strgy2")
    private String paybackPeriodStrgy2;
    @JsonProperty("caprate_property_based_costbasis_strgy2")
    private String capratePropertyBasedCostbasisStrgy2;
    @JsonProperty("caprate_property_based_arv_strgy2")
    private String capratePropertyBasedArvStrgy2;
    @JsonProperty("property_state")
    private String propertyState;
    @JsonProperty("property_city")
    private String propertyCity;
    @JsonProperty("property_zip")
    private String propertyZip;
    @JsonProperty("owner_name")
    private String ownerName;
    @JsonProperty("owner_company_name")
    private String ownerCompanyName;
    @JsonProperty("company_strt_address")
    private String companyStrtAddress;
    @JsonProperty("company_address_city")
    private String companyAddressCity;
    @JsonProperty("company_address_state")
    private String companyAddressState;
    @JsonProperty("company_address_zip")
    private String companyAddressZip;
    @JsonProperty("company_phone")
    private String companyPhone;
    @JsonProperty("company_email")
    private String companyEmail;
    @JsonProperty("company_website")
    private String companyWebsite;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("deal_closing_date")
    private String dealClosingDate;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The userId
     */
    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The user_id
     */
    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The propertyName
     */
    @JsonProperty("property_name")
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 
     * @param propertyName
     *     The property_name
     */
    @JsonProperty("property_name")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 
     * @return
     *     The propertyStrtAddress
     */
    @JsonProperty("property_strt_address")
    public String getPropertyStrtAddress() {
        return propertyStrtAddress;
    }

    /**
     * 
     * @param propertyStrtAddress
     *     The property_strt_address
     */
    @JsonProperty("property_strt_address")
    public void setPropertyStrtAddress(String propertyStrtAddress) {
        this.propertyStrtAddress = propertyStrtAddress;
    }

    /**
     * 
     * @return
     *     The propertyBedrooms
     */
    @JsonProperty("property_bedrooms")
    public Integer getPropertyBedrooms() {
        return propertyBedrooms;
    }

    /**
     * 
     * @param propertyBedrooms
     *     The property_bedrooms
     */
    @JsonProperty("property_bedrooms")
    public void setPropertyBedrooms(Integer propertyBedrooms) {
        this.propertyBedrooms = propertyBedrooms;
    }

    /**
     * 
     * @return
     *     The propertyBaths
     */
    @JsonProperty("property_baths")
    public Integer getPropertyBaths() {
        return propertyBaths;
    }

    /**
     * 
     * @param propertyBaths
     *     The property_baths
     */
    @JsonProperty("property_baths")
    public void setPropertyBaths(Integer propertyBaths) {
        this.propertyBaths = propertyBaths;
    }

    /**
     * 
     * @return
     *     The propertySqFeet
     */
    @JsonProperty("property_sq_feet")
    public String getPropertySqFeet() {
        return propertySqFeet;
    }

    /**
     * 
     * @param propertySqFeet
     *     The property_sq_feet
     */
    @JsonProperty("property_sq_feet")
    public void setPropertySqFeet(String propertySqFeet) {
        this.propertySqFeet = propertySqFeet;
    }

    /**
     * 
     * @return
     *     The propertyBuiltYear
     */
    @JsonProperty("property_built_year")
    public Integer getPropertyBuiltYear() {
        return propertyBuiltYear;
    }

    /**
     * 
     * @param propertyBuiltYear
     *     The property_built_year
     */
    @JsonProperty("property_built_year")
    public void setPropertyBuiltYear(Integer propertyBuiltYear) {
        this.propertyBuiltYear = propertyBuiltYear;
    }

    /**
     * 
     * @return
     *     The propertyImage
     */
    @JsonProperty("property_image")
    public String getPropertyImage() {
        return propertyImage;
    }

    /**
     * 
     * @param propertyImage
     *     The property_image
     */
    @JsonProperty("property_image")
    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

    /**
     * 
     * @return
     *     The agentName
     */
    @JsonProperty("agent_name")
    public String getAgentName() {
        return agentName;
    }

    /**
     * 
     * @param agentName
     *     The agent_name
     */
    @JsonProperty("agent_name")
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * 
     * @return
     *     The purchasePrice
     */
    @JsonProperty("purchase_price")
    public String getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * 
     * @param purchasePrice
     *     The purchase_price
     */
    @JsonProperty("purchase_price")
    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * 
     * @return
     *     The closeingCost
     */
    @JsonProperty("closeing_cost")
    public String getCloseingCost() {
        return closeingCost;
    }

    /**
     * 
     * @param closeingCost
     *     The closeing_cost
     */
    @JsonProperty("closeing_cost")
    public void setCloseingCost(String closeingCost) {
        this.closeingCost = closeingCost;
    }

    /**
     * 
     * @return
     *     The holdingCost
     */
    @JsonProperty("holding_cost")
    public String getHoldingCost() {
        return holdingCost;
    }

    /**
     * 
     * @param holdingCost
     *     The holding_cost
     */
    @JsonProperty("holding_cost")
    public void setHoldingCost(String holdingCost) {
        this.holdingCost = holdingCost;
    }

    /**
     * 
     * @return
     *     The includeClosingHoldingCost
     */
    @JsonProperty("include_closing_holding_cost")
    public String getIncludeClosingHoldingCost() {
        return includeClosingHoldingCost;
    }

    /**
     * 
     * @param includeClosingHoldingCost
     *     The include_closing_holding_cost
     */
    @JsonProperty("include_closing_holding_cost")
    public void setIncludeClosingHoldingCost(String includeClosingHoldingCost) {
        this.includeClosingHoldingCost = includeClosingHoldingCost;
    }

    /**
     * 
     * @return
     *     The rehabBudget
     */
    @JsonProperty("rehab_budget")
    public String getRehabBudget() {
        return rehabBudget;
    }

    /**
     * 
     * @param rehabBudget
     *     The rehab_budget
     */
    @JsonProperty("rehab_budget")
    public void setRehabBudget(String rehabBudget) {
        this.rehabBudget = rehabBudget;
    }

    /**
     * 
     * @return
     *     The projectRehabPeriod
     */
    @JsonProperty("project_rehab_period")
    public String getProjectRehabPeriod() {
        return projectRehabPeriod;
    }

    /**
     * 
     * @param projectRehabPeriod
     *     The project_rehab_period
     */
    @JsonProperty("project_rehab_period")
    public void setProjectRehabPeriod(String projectRehabPeriod) {
        this.projectRehabPeriod = projectRehabPeriod;
    }

    /**
     * 
     * @return
     *     The financingCash
     */
    @JsonProperty("financing_cash")
    public String getFinancingCash() {
        return financingCash;
    }

    /**
     * 
     * @param financingCash
     *     The financing_cash
     */
    @JsonProperty("financing_cash")
    public void setFinancingCash(String financingCash) {
        this.financingCash = financingCash;
    }

    /**
     * 
     * @return
     *     The arvCost
     */
    @JsonProperty("arv_cost")
    public String getArvCost() {
        return arvCost;
    }

    /**
     * 
     * @param arvCost
     *     The arv_cost
     */
    @JsonProperty("arv_cost")
    public void setArvCost(String arvCost) {
        this.arvCost = arvCost;
    }

    /**
     * 
     * @return
     *     The financedCostMaxPer
     */
    @JsonProperty("financed_cost_max_per")
    public String getFinancedCostMaxPer() {
        return financedCostMaxPer;
    }

    /**
     * 
     * @param financedCostMaxPer
     *     The financed_cost_max_per
     */
    @JsonProperty("financed_cost_max_per")
    public void setFinancedCostMaxPer(String financedCostMaxPer) {
        this.financedCostMaxPer = financedCostMaxPer;
    }

    /**
     * 
     * @return
     *     The originationDiscountPoints
     */
    @JsonProperty("origination_discount_points")
    public String getOriginationDiscountPoints() {
        return originationDiscountPoints;
    }

    /**
     * 
     * @param originationDiscountPoints
     *     The origination_discount_points
     */
    @JsonProperty("origination_discount_points")
    public void setOriginationDiscountPoints(String originationDiscountPoints) {
        this.originationDiscountPoints = originationDiscountPoints;
    }

    /**
     * 
     * @return
     *     The othrClsCostPaidLander
     */
    @JsonProperty("othr_cls_cost_paid_lander")
    public String getOthrClsCostPaidLander() {
        return othrClsCostPaidLander;
    }

    /**
     * 
     * @param othrClsCostPaidLander
     *     The othr_cls_cost_paid_lander
     */
    @JsonProperty("othr_cls_cost_paid_lander")
    public void setOthrClsCostPaidLander(String othrClsCostPaidLander) {
        this.othrClsCostPaidLander = othrClsCostPaidLander;
    }

    /**
     * 
     * @return
     *     The costsUpfrontBackend
     */
    @JsonProperty("costs_upfront_backend")
    public String getCostsUpfrontBackend() {
        return costsUpfrontBackend;
    }

    /**
     * 
     * @param costsUpfrontBackend
     *     The costs_upfront_backend
     */
    @JsonProperty("costs_upfront_backend")
    public void setCostsUpfrontBackend(String costsUpfrontBackend) {
        this.costsUpfrontBackend = costsUpfrontBackend;
    }

    /**
     * 
     * @return
     *     The interestRate
     */
    @JsonProperty("interest_rate")
    public String getInterestRate() {
        return interestRate;
    }

    /**
     * 
     * @param interestRate
     *     The interest_rate
     */
    @JsonProperty("interest_rate")
    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * 
     * @return
     *     The interestPaymentDuringRehab
     */
    @JsonProperty("interest_payment_during_rehab")
    public String getInterestPaymentDuringRehab() {
        return interestPaymentDuringRehab;
    }

    /**
     * 
     * @param interestPaymentDuringRehab
     *     The interest_payment_during_rehab
     */
    @JsonProperty("interest_payment_during_rehab")
    public void setInterestPaymentDuringRehab(String interestPaymentDuringRehab) {
        this.interestPaymentDuringRehab = interestPaymentDuringRehab;
    }

    /**
     * 
     * @return
     *     The splitBackendProfitsWithLender
     */
    @JsonProperty("split_backend_profits_with_lender")
    public String getSplitBackendProfitsWithLender() {
        return splitBackendProfitsWithLender;
    }

    /**
     * 
     * @param splitBackendProfitsWithLender
     *     The split_backend_profits_with_lender
     */
    @JsonProperty("split_backend_profits_with_lender")
    public void setSplitBackendProfitsWithLender(String splitBackendProfitsWithLender) {
        this.splitBackendProfitsWithLender = splitBackendProfitsWithLender;
    }

    /**
     * 
     * @return
     *     The preTaxProfitDoesLenderGet
     */
    @JsonProperty("pre_tax_profit_does_lender_get")
    public String getPreTaxProfitDoesLenderGet() {
        return preTaxProfitDoesLenderGet;
    }

    /**
     * 
     * @param preTaxProfitDoesLenderGet
     *     The pre_tax_profit_does_lender_get
     */
    @JsonProperty("pre_tax_profit_does_lender_get")
    public void setPreTaxProfitDoesLenderGet(String preTaxProfitDoesLenderGet) {
        this.preTaxProfitDoesLenderGet = preTaxProfitDoesLenderGet;
    }

    /**
     * 
     * @return
     *     The arvForFlipStrgy1
     */
    @JsonProperty("arv_for_flip_strgy1")
    public String getArvForFlipStrgy1() {
        return arvForFlipStrgy1;
    }

    /**
     * 
     * @param arvForFlipStrgy1
     *     The arv_for_flip_strgy1
     */
    @JsonProperty("arv_for_flip_strgy1")
    public void setArvForFlipStrgy1(String arvForFlipStrgy1) {
        this.arvForFlipStrgy1 = arvForFlipStrgy1;
    }

    /**
     * 
     * @return
     *     The monthsCompleteSaleAfterRehab
     */
    @JsonProperty("months_complete_sale_after_rehab")
    public String getMonthsCompleteSaleAfterRehab() {
        return monthsCompleteSaleAfterRehab;
    }

    /**
     * 
     * @param monthsCompleteSaleAfterRehab
     *     The months_complete_sale_after_rehab
     */
    @JsonProperty("months_complete_sale_after_rehab")
    public void setMonthsCompleteSaleAfterRehab(String monthsCompleteSaleAfterRehab) {
        this.monthsCompleteSaleAfterRehab = monthsCompleteSaleAfterRehab;
    }

    /**
     * 
     * @return
     *     The totalCapitalNeededStrgy1
     */
    @JsonProperty("total_capital_needed_strgy1")
    public String getTotalCapitalNeededStrgy1() {
        return totalCapitalNeededStrgy1;
    }

    /**
     * 
     * @param totalCapitalNeededStrgy1
     *     The total_capital_needed_strgy1
     */
    @JsonProperty("total_capital_needed_strgy1")
    public void setTotalCapitalNeededStrgy1(String totalCapitalNeededStrgy1) {
        this.totalCapitalNeededStrgy1 = totalCapitalNeededStrgy1;
    }

    /**
     * 
     * @return
     *     The maxDollarFinancedStrgy1
     */
    @JsonProperty("max_dollar_financed_strgy1")
    public String getMaxDollarFinancedStrgy1() {
        return maxDollarFinancedStrgy1;
    }

    /**
     * 
     * @param maxDollarFinancedStrgy1
     *     The max_dollar_financed_strgy1
     */
    @JsonProperty("max_dollar_financed_strgy1")
    public void setMaxDollarFinancedStrgy1(String maxDollarFinancedStrgy1) {
        this.maxDollarFinancedStrgy1 = maxDollarFinancedStrgy1;
    }

    /**
     * 
     * @return
     *     The actualFinancedNotClosholdStrgy1
     */
    @JsonProperty("actual_financed_not_closhold_strgy1")
    public String getActualFinancedNotClosholdStrgy1() {
        return actualFinancedNotClosholdStrgy1;
    }

    /**
     * 
     * @param actualFinancedNotClosholdStrgy1
     *     The actual_financed_not_closhold_strgy1
     */
    @JsonProperty("actual_financed_not_closhold_strgy1")
    public void setActualFinancedNotClosholdStrgy1(String actualFinancedNotClosholdStrgy1) {
        this.actualFinancedNotClosholdStrgy1 = actualFinancedNotClosholdStrgy1;
    }

    /**
     * 
     * @return
     *     The closHoldInterestLoanStrgy1
     */
    @JsonProperty("clos_hold_interest_loan_strgy1")
    public String getClosHoldInterestLoanStrgy1() {
        return closHoldInterestLoanStrgy1;
    }

    /**
     * 
     * @param closHoldInterestLoanStrgy1
     *     The clos_hold_interest_loan_strgy1
     */
    @JsonProperty("clos_hold_interest_loan_strgy1")
    public void setClosHoldInterestLoanStrgy1(String closHoldInterestLoanStrgy1) {
        this.closHoldInterestLoanStrgy1 = closHoldInterestLoanStrgy1;
    }

    /**
     * 
     * @return
     *     The totalLoanAmountStrgy1
     */
    @JsonProperty("total_loan_amount_strgy1")
    public String getTotalLoanAmountStrgy1() {
        return totalLoanAmountStrgy1;
    }

    /**
     * 
     * @param totalLoanAmountStrgy1
     *     The total_loan_amount_strgy1
     */
    @JsonProperty("total_loan_amount_strgy1")
    public void setTotalLoanAmountStrgy1(String totalLoanAmountStrgy1) {
        this.totalLoanAmountStrgy1 = totalLoanAmountStrgy1;
    }

    /**
     * 
     * @return
     *     The cashRequiredOverlifeProjectStrgy1
     */
    @JsonProperty("cash_required_overlife_project_strgy1")
    public String getCashRequiredOverlifeProjectStrgy1() {
        return cashRequiredOverlifeProjectStrgy1;
    }

    /**
     * 
     * @param cashRequiredOverlifeProjectStrgy1
     *     The cash_required_overlife_project_strgy1
     */
    @JsonProperty("cash_required_overlife_project_strgy1")
    public void setCashRequiredOverlifeProjectStrgy1(String cashRequiredOverlifeProjectStrgy1) {
        this.cashRequiredOverlifeProjectStrgy1 = cashRequiredOverlifeProjectStrgy1;
    }

    /**
     * 
     * @return
     *     The totalAllinCostsEndRehabStrgy1
     */
    @JsonProperty("total_allin_costs_end_rehab_strgy1")
    public String getTotalAllinCostsEndRehabStrgy1() {
        return totalAllinCostsEndRehabStrgy1;
    }

    /**
     * 
     * @param totalAllinCostsEndRehabStrgy1
     *     The total_allin_costs_end_rehab_strgy1
     */
    @JsonProperty("total_allin_costs_end_rehab_strgy1")
    public void setTotalAllinCostsEndRehabStrgy1(String totalAllinCostsEndRehabStrgy1) {
        this.totalAllinCostsEndRehabStrgy1 = totalAllinCostsEndRehabStrgy1;
    }

    /**
     * 
     * @return
     *     The arvPerStrgy1
     */
    @JsonProperty("arv_per_strgy1")
    public String getArvPerStrgy1() {
        return arvPerStrgy1;
    }

    /**
     * 
     * @param arvPerStrgy1
     *     The arv_per_strgy1
     */
    @JsonProperty("arv_per_strgy1")
    public void setArvPerStrgy1(String arvPerStrgy1) {
        this.arvPerStrgy1 = arvPerStrgy1;
    }

    /**
     * 
     * @return
     *     The projectedResalePriceStrgy1
     */
    @JsonProperty("projected_resale_price_strgy1")
    public String getProjectedResalePriceStrgy1() {
        return projectedResalePriceStrgy1;
    }

    /**
     * 
     * @param projectedResalePriceStrgy1
     *     The projected_resale_price_strgy1
     */
    @JsonProperty("projected_resale_price_strgy1")
    public void setProjectedResalePriceStrgy1(String projectedResalePriceStrgy1) {
        this.projectedResalePriceStrgy1 = projectedResalePriceStrgy1;
    }

    /**
     * 
     * @return
     *     The projectedCostSalePerStrgy1
     */
    @JsonProperty("projected_cost_sale_per_strgy1")
    public String getProjectedCostSalePerStrgy1() {
        return projectedCostSalePerStrgy1;
    }

    /**
     * 
     * @param projectedCostSalePerStrgy1
     *     The projected_cost_sale_per_strgy1
     */
    @JsonProperty("projected_cost_sale_per_strgy1")
    public void setProjectedCostSalePerStrgy1(String projectedCostSalePerStrgy1) {
        this.projectedCostSalePerStrgy1 = projectedCostSalePerStrgy1;
    }

    /**
     * 
     * @return
     *     The projectedProfitAfterLenderSplitStrgy1
     */
    @JsonProperty("projected_profit_after_lender_split_strgy1")
    public String getProjectedProfitAfterLenderSplitStrgy1() {
        return projectedProfitAfterLenderSplitStrgy1;
    }

    /**
     * 
     * @param projectedProfitAfterLenderSplitStrgy1
     *     The projected_profit_after_lender_split_strgy1
     */
    @JsonProperty("projected_profit_after_lender_split_strgy1")
    public void setProjectedProfitAfterLenderSplitStrgy1(String projectedProfitAfterLenderSplitStrgy1) {
        this.projectedProfitAfterLenderSplitStrgy1 = projectedProfitAfterLenderSplitStrgy1;
    }

    /**
     * 
     * @return
     *     The rociStrgy1
     */
    @JsonProperty("roci_strgy1")
    public String getRociStrgy1() {
        return rociStrgy1;
    }

    /**
     * 
     * @param rociStrgy1
     *     The roci_strgy1
     */
    @JsonProperty("roci_strgy1")
    public void setRociStrgy1(String rociStrgy1) {
        this.rociStrgy1 = rociStrgy1;
    }

    /**
     * 
     * @return
     *     The roiAnnualizedStrgy1
     */
    @JsonProperty("roi_annualized_strgy1")
    public String getRoiAnnualizedStrgy1() {
        return roiAnnualizedStrgy1;
    }

    /**
     * 
     * @param roiAnnualizedStrgy1
     *     The roi_annualized_strgy1
     */
    @JsonProperty("roi_annualized_strgy1")
    public void setRoiAnnualizedStrgy1(String roiAnnualizedStrgy1) {
        this.roiAnnualizedStrgy1 = roiAnnualizedStrgy1;
    }

    /**
     * 
     * @return
     *     The arvRentStrgy2
     */
    @JsonProperty("arv_rent_strgy2")
    public String getArvRentStrgy2() {
        return arvRentStrgy2;
    }

    /**
     * 
     * @param arvRentStrgy2
     *     The arv_rent_strgy2
     */
    @JsonProperty("arv_rent_strgy2")
    public void setArvRentStrgy2(String arvRentStrgy2) {
        this.arvRentStrgy2 = arvRentStrgy2;
    }

    /**
     * 
     * @return
     *     The monthsRentAfterRehabPeriodOverStrgy2
     */
    @JsonProperty("months_rent_after_rehab_period_over_strgy2")
    public String getMonthsRentAfterRehabPeriodOverStrgy2() {
        return monthsRentAfterRehabPeriodOverStrgy2;
    }

    /**
     * 
     * @param monthsRentAfterRehabPeriodOverStrgy2
     *     The months_rent_after_rehab_period_over_strgy2
     */
    @JsonProperty("months_rent_after_rehab_period_over_strgy2")
    public void setMonthsRentAfterRehabPeriodOverStrgy2(String monthsRentAfterRehabPeriodOverStrgy2) {
        this.monthsRentAfterRehabPeriodOverStrgy2 = monthsRentAfterRehabPeriodOverStrgy2;
    }

    /**
     * 
     * @return
     *     The totalCapitalNeededStrgy2
     */
    @JsonProperty("total_capital_needed_strgy2")
    public String getTotalCapitalNeededStrgy2() {
        return totalCapitalNeededStrgy2;
    }

    /**
     * 
     * @param totalCapitalNeededStrgy2
     *     The total_capital_needed_strgy2
     */
    @JsonProperty("total_capital_needed_strgy2")
    public void setTotalCapitalNeededStrgy2(String totalCapitalNeededStrgy2) {
        this.totalCapitalNeededStrgy2 = totalCapitalNeededStrgy2;
    }

    /**
     * 
     * @return
     *     The maxDollarCanbeFinancedStrgy2
     */
    @JsonProperty("max_dollar_canbe_financed_strgy2")
    public String getMaxDollarCanbeFinancedStrgy2() {
        return maxDollarCanbeFinancedStrgy2;
    }

    /**
     * 
     * @param maxDollarCanbeFinancedStrgy2
     *     The max_dollar_canbe_financed_strgy2
     */
    @JsonProperty("max_dollar_canbe_financed_strgy2")
    public void setMaxDollarCanbeFinancedStrgy2(String maxDollarCanbeFinancedStrgy2) {
        this.maxDollarCanbeFinancedStrgy2 = maxDollarCanbeFinancedStrgy2;
    }

    /**
     * 
     * @return
     *     The actualFinancedNotClosholdingStrgy2
     */
    @JsonProperty("actual_financed_not_closholding_strgy2")
    public String getActualFinancedNotClosholdingStrgy2() {
        return actualFinancedNotClosholdingStrgy2;
    }

    /**
     * 
     * @param actualFinancedNotClosholdingStrgy2
     *     The actual_financed_not_closholding_strgy2
     */
    @JsonProperty("actual_financed_not_closholding_strgy2")
    public void setActualFinancedNotClosholdingStrgy2(String actualFinancedNotClosholdingStrgy2) {
        this.actualFinancedNotClosholdingStrgy2 = actualFinancedNotClosholdingStrgy2;
    }

    /**
     * 
     * @return
     *     The closholdingCostsInterestAddedLoanStrgy2
     */
    @JsonProperty("closholding_costs_interest_added_loan_strgy2")
    public String getClosholdingCostsInterestAddedLoanStrgy2() {
        return closholdingCostsInterestAddedLoanStrgy2;
    }

    /**
     * 
     * @param closholdingCostsInterestAddedLoanStrgy2
     *     The closholding_costs_interest_added_loan_strgy2
     */
    @JsonProperty("closholding_costs_interest_added_loan_strgy2")
    public void setClosholdingCostsInterestAddedLoanStrgy2(String closholdingCostsInterestAddedLoanStrgy2) {
        this.closholdingCostsInterestAddedLoanStrgy2 = closholdingCostsInterestAddedLoanStrgy2;
    }

    /**
     * 
     * @return
     *     The totalLoanAmountStrgy2
     */
    @JsonProperty("total_loan_amount_strgy2")
    public String getTotalLoanAmountStrgy2() {
        return totalLoanAmountStrgy2;
    }

    /**
     * 
     * @param totalLoanAmountStrgy2
     *     The total_loan_amount_strgy2
     */
    @JsonProperty("total_loan_amount_strgy2")
    public void setTotalLoanAmountStrgy2(String totalLoanAmountStrgy2) {
        this.totalLoanAmountStrgy2 = totalLoanAmountStrgy2;
    }

    /**
     * 
     * @return
     *     The cashRequiredStrgy2
     */
    @JsonProperty("cash_required_strgy2")
    public String getCashRequiredStrgy2() {
        return cashRequiredStrgy2;
    }

    /**
     * 
     * @param cashRequiredStrgy2
     *     The cash_required_strgy2
     */
    @JsonProperty("cash_required_strgy2")
    public void setCashRequiredStrgy2(String cashRequiredStrgy2) {
        this.cashRequiredStrgy2 = cashRequiredStrgy2;
    }

    /**
     * 
     * @return
     *     The totalAllinCostsEndRehabStrgy2
     */
    @JsonProperty("total_allin_costs_end_rehab_strgy2")
    public String getTotalAllinCostsEndRehabStrgy2() {
        return totalAllinCostsEndRehabStrgy2;
    }

    /**
     * 
     * @param totalAllinCostsEndRehabStrgy2
     *     The total_allin_costs_end_rehab_strgy2
     */
    @JsonProperty("total_allin_costs_end_rehab_strgy2")
    public void setTotalAllinCostsEndRehabStrgy2(String totalAllinCostsEndRehabStrgy2) {
        this.totalAllinCostsEndRehabStrgy2 = totalAllinCostsEndRehabStrgy2;
    }

    /**
     * 
     * @return
     *     The arvPerStrgy2
     */
    @JsonProperty("arv_per_strgy2")
    public String getArvPerStrgy2() {
        return arvPerStrgy2;
    }

    /**
     * 
     * @param arvPerStrgy2
     *     The arv_per_strgy2
     */
    @JsonProperty("arv_per_strgy2")
    public void setArvPerStrgy2(String arvPerStrgy2) {
        this.arvPerStrgy2 = arvPerStrgy2;
    }

    /**
     * 
     * @return
     *     The projectedOperatingIncomeStrgy2
     */
    @JsonProperty("projected_operating_income_strgy2")
    public String getProjectedOperatingIncomeStrgy2() {
        return projectedOperatingIncomeStrgy2;
    }

    /**
     * 
     * @param projectedOperatingIncomeStrgy2
     *     The projected_operating_income_strgy2
     */
    @JsonProperty("projected_operating_income_strgy2")
    public void setProjectedOperatingIncomeStrgy2(String projectedOperatingIncomeStrgy2) {
        this.projectedOperatingIncomeStrgy2 = projectedOperatingIncomeStrgy2;
    }

    /**
     * 
     * @return
     *     The projectedOperatingExpensesStrgy2
     */
    @JsonProperty("projected_operating_expenses_strgy2")
    public String getProjectedOperatingExpensesStrgy2() {
        return projectedOperatingExpensesStrgy2;
    }

    /**
     * 
     * @param projectedOperatingExpensesStrgy2
     *     The projected_operating_expenses_strgy2
     */
    @JsonProperty("projected_operating_expenses_strgy2")
    public void setProjectedOperatingExpensesStrgy2(String projectedOperatingExpensesStrgy2) {
        this.projectedOperatingExpensesStrgy2 = projectedOperatingExpensesStrgy2;
    }

    /**
     * 
     * @return
     *     The netOperatingIncomeMonthlyStrgy2
     */
    @JsonProperty("net_operating_income_monthly_strgy2")
    public String getNetOperatingIncomeMonthlyStrgy2() {
        return netOperatingIncomeMonthlyStrgy2;
    }

    /**
     * 
     * @param netOperatingIncomeMonthlyStrgy2
     *     The net_operating_income_monthly_strgy2
     */
    @JsonProperty("net_operating_income_monthly_strgy2")
    public void setNetOperatingIncomeMonthlyStrgy2(String netOperatingIncomeMonthlyStrgy2) {
        this.netOperatingIncomeMonthlyStrgy2 = netOperatingIncomeMonthlyStrgy2;
    }

    /**
     * 
     * @return
     *     The refinancePermanentFinancingStrgy2
     */
    @JsonProperty("refinance_permanent_financing_strgy2")
    public String getRefinancePermanentFinancingStrgy2() {
        return refinancePermanentFinancingStrgy2;
    }

    /**
     * 
     * @param refinancePermanentFinancingStrgy2
     *     The refinance_permanent_financing_strgy2
     */
    @JsonProperty("refinance_permanent_financing_strgy2")
    public void setRefinancePermanentFinancingStrgy2(String refinancePermanentFinancingStrgy2) {
        this.refinancePermanentFinancingStrgy2 = refinancePermanentFinancingStrgy2;
    }

    /**
     * 
     * @return
     *     The refinancePerAppraisalArvStrgy2
     */
    @JsonProperty("refinance_per_appraisal_arv_strgy2")
    public String getRefinancePerAppraisalArvStrgy2() {
        return refinancePerAppraisalArvStrgy2;
    }

    /**
     * 
     * @param refinancePerAppraisalArvStrgy2
     *     The refinance_per_appraisal_arv_strgy2
     */
    @JsonProperty("refinance_per_appraisal_arv_strgy2")
    public void setRefinancePerAppraisalArvStrgy2(String refinancePerAppraisalArvStrgy2) {
        this.refinancePerAppraisalArvStrgy2 = refinancePerAppraisalArvStrgy2;
    }

    /**
     * 
     * @return
     *     The newMortgageRateStrgy2
     */
    @JsonProperty("new_mortgage_rate_strgy2")
    public String getNewMortgageRateStrgy2() {
        return newMortgageRateStrgy2;
    }

    /**
     * 
     * @param newMortgageRateStrgy2
     *     The new_mortgage_rate_strgy2
     */
    @JsonProperty("new_mortgage_rate_strgy2")
    public void setNewMortgageRateStrgy2(String newMortgageRateStrgy2) {
        this.newMortgageRateStrgy2 = newMortgageRateStrgy2;
    }

    /**
     * 
     * @return
     *     The amortizationYearsInterestOnlyStrgy2
     */
    @JsonProperty("amortization_years_interest_only_strgy2")
    public String getAmortizationYearsInterestOnlyStrgy2() {
        return amortizationYearsInterestOnlyStrgy2;
    }

    /**
     * 
     * @param amortizationYearsInterestOnlyStrgy2
     *     The amortization_years_interest_only_strgy2
     */
    @JsonProperty("amortization_years_interest_only_strgy2")
    public void setAmortizationYearsInterestOnlyStrgy2(String amortizationYearsInterestOnlyStrgy2) {
        this.amortizationYearsInterestOnlyStrgy2 = amortizationYearsInterestOnlyStrgy2;
    }

    /**
     * 
     * @return
     *     The refiDiscountPointsMiscCostsStrgy2
     */
    @JsonProperty("refi_discount_points_misc_costs_strgy2")
    public String getRefiDiscountPointsMiscCostsStrgy2() {
        return refiDiscountPointsMiscCostsStrgy2;
    }

    /**
     * 
     * @param refiDiscountPointsMiscCostsStrgy2
     *     The refi_discount_points_misc_costs_strgy2
     */
    @JsonProperty("refi_discount_points_misc_costs_strgy2")
    public void setRefiDiscountPointsMiscCostsStrgy2(String refiDiscountPointsMiscCostsStrgy2) {
        this.refiDiscountPointsMiscCostsStrgy2 = refiDiscountPointsMiscCostsStrgy2;
    }

    /**
     * 
     * @return
     *     The newMtgePmntMonthlyStrgy2
     */
    @JsonProperty("new_mtge_pmnt_monthly_strgy2")
    public String getNewMtgePmntMonthlyStrgy2() {
        return newMtgePmntMonthlyStrgy2;
    }

    /**
     * 
     * @param newMtgePmntMonthlyStrgy2
     *     The new_mtge_pmnt_monthly_strgy2
     */
    @JsonProperty("new_mtge_pmnt_monthly_strgy2")
    public void setNewMtgePmntMonthlyStrgy2(String newMtgePmntMonthlyStrgy2) {
        this.newMtgePmntMonthlyStrgy2 = newMtgePmntMonthlyStrgy2;
    }

    /**
     * 
     * @return
     *     The refiLoanAmountStrgy2
     */
    @JsonProperty("refi_loan_amount_strgy2")
    public String getRefiLoanAmountStrgy2() {
        return refiLoanAmountStrgy2;
    }

    /**
     * 
     * @param refiLoanAmountStrgy2
     *     The refi_loan_amount_strgy2
     */
    @JsonProperty("refi_loan_amount_strgy2")
    public void setRefiLoanAmountStrgy2(String refiLoanAmountStrgy2) {
        this.refiLoanAmountStrgy2 = refiLoanAmountStrgy2;
    }

    /**
     * 
     * @return
     *     The cashOutRefiStrgy2
     */
    @JsonProperty("cash_out_refi_strgy2")
    public String getCashOutRefiStrgy2() {
        return cashOutRefiStrgy2;
    }

    /**
     * 
     * @param cashOutRefiStrgy2
     *     The cash_out_refi_strgy2
     */
    @JsonProperty("cash_out_refi_strgy2")
    public void setCashOutRefiStrgy2(String cashOutRefiStrgy2) {
        this.cashOutRefiStrgy2 = cashOutRefiStrgy2;
    }

    /**
     * 
     * @return
     *     The profitRefiAfterLenderSplitStrgy2
     */
    @JsonProperty("profit_refi_after_lender_split_strgy2")
    public String getProfitRefiAfterLenderSplitStrgy2() {
        return profitRefiAfterLenderSplitStrgy2;
    }

    /**
     * 
     * @param profitRefiAfterLenderSplitStrgy2
     *     The profit_refi_after_lender_split_strgy2
     */
    @JsonProperty("profit_refi_after_lender_split_strgy2")
    public void setProfitRefiAfterLenderSplitStrgy2(String profitRefiAfterLenderSplitStrgy2) {
        this.profitRefiAfterLenderSplitStrgy2 = profitRefiAfterLenderSplitStrgy2;
    }

    /**
     * 
     * @return
     *     The roiCashInvestedAnnualizedStrgy2
     */
    @JsonProperty("roi_cash_invested_annualized_strgy2")
    public String getRoiCashInvestedAnnualizedStrgy2() {
        return roiCashInvestedAnnualizedStrgy2;
    }

    /**
     * 
     * @param roiCashInvestedAnnualizedStrgy2
     *     The roi_cash_invested_annualized_strgy2
     */
    @JsonProperty("roi_cash_invested_annualized_strgy2")
    public void setRoiCashInvestedAnnualizedStrgy2(String roiCashInvestedAnnualizedStrgy2) {
        this.roiCashInvestedAnnualizedStrgy2 = roiCashInvestedAnnualizedStrgy2;
    }

    /**
     * 
     * @return
     *     The originalMoneyTiedupAfterRefiStrgy2
     */
    @JsonProperty("original_money_tiedup_after_refi_strgy2")
    public String getOriginalMoneyTiedupAfterRefiStrgy2() {
        return originalMoneyTiedupAfterRefiStrgy2;
    }

    /**
     * 
     * @param originalMoneyTiedupAfterRefiStrgy2
     *     The original_money_tiedup_after_refi_strgy2
     */
    @JsonProperty("original_money_tiedup_after_refi_strgy2")
    public void setOriginalMoneyTiedupAfterRefiStrgy2(String originalMoneyTiedupAfterRefiStrgy2) {
        this.originalMoneyTiedupAfterRefiStrgy2 = originalMoneyTiedupAfterRefiStrgy2;
    }

    /**
     * 
     * @return
     *     The equityLeftDealAfterRefiStrgy2
     */
    @JsonProperty("equity_left_deal_after_refi_strgy2")
    public String getEquityLeftDealAfterRefiStrgy2() {
        return equityLeftDealAfterRefiStrgy2;
    }

    /**
     * 
     * @param equityLeftDealAfterRefiStrgy2
     *     The equity_left_deal_after_refi_strgy2
     */
    @JsonProperty("equity_left_deal_after_refi_strgy2")
    public void setEquityLeftDealAfterRefiStrgy2(String equityLeftDealAfterRefiStrgy2) {
        this.equityLeftDealAfterRefiStrgy2 = equityLeftDealAfterRefiStrgy2;
    }

    /**
     * 
     * @return
     *     The cashFlowMonthlyPretaxStrgy2
     */
    @JsonProperty("cash_flow_monthly_pretax_strgy2")
    public String getCashFlowMonthlyPretaxStrgy2() {
        return cashFlowMonthlyPretaxStrgy2;
    }

    /**
     * 
     * @param cashFlowMonthlyPretaxStrgy2
     *     The cash_flow_monthly_pretax_strgy2
     */
    @JsonProperty("cash_flow_monthly_pretax_strgy2")
    public void setCashFlowMonthlyPretaxStrgy2(String cashFlowMonthlyPretaxStrgy2) {
        this.cashFlowMonthlyPretaxStrgy2 = cashFlowMonthlyPretaxStrgy2;
    }

    /**
     * 
     * @return
     *     The cocAnnualPretaxStrgy2
     */
    @JsonProperty("coc_annual_pretax_strgy2")
    public String getCocAnnualPretaxStrgy2() {
        return cocAnnualPretaxStrgy2;
    }

    /**
     * 
     * @param cocAnnualPretaxStrgy2
     *     The coc_annual_pretax_strgy2
     */
    @JsonProperty("coc_annual_pretax_strgy2")
    public void setCocAnnualPretaxStrgy2(String cocAnnualPretaxStrgy2) {
        this.cocAnnualPretaxStrgy2 = cocAnnualPretaxStrgy2;
    }

    /**
     * 
     * @return
     *     The propertyDcrStrgy2
     */
    @JsonProperty("property_dcr_strgy2")
    public String getPropertyDcrStrgy2() {
        return propertyDcrStrgy2;
    }

    /**
     * 
     * @param propertyDcrStrgy2
     *     The property_dcr_strgy2
     */
    @JsonProperty("property_dcr_strgy2")
    public void setPropertyDcrStrgy2(String propertyDcrStrgy2) {
        this.propertyDcrStrgy2 = propertyDcrStrgy2;
    }

    /**
     * 
     * @return
     *     The paybackPeriodStrgy2
     */
    @JsonProperty("payback_period_strgy2")
    public String getPaybackPeriodStrgy2() {
        return paybackPeriodStrgy2;
    }

    /**
     * 
     * @param paybackPeriodStrgy2
     *     The payback_period_strgy2
     */
    @JsonProperty("payback_period_strgy2")
    public void setPaybackPeriodStrgy2(String paybackPeriodStrgy2) {
        this.paybackPeriodStrgy2 = paybackPeriodStrgy2;
    }

    /**
     * 
     * @return
     *     The capratePropertyBasedCostbasisStrgy2
     */
    @JsonProperty("caprate_property_based_costbasis_strgy2")
    public String getCapratePropertyBasedCostbasisStrgy2() {
        return capratePropertyBasedCostbasisStrgy2;
    }

    /**
     * 
     * @param capratePropertyBasedCostbasisStrgy2
     *     The caprate_property_based_costbasis_strgy2
     */
    @JsonProperty("caprate_property_based_costbasis_strgy2")
    public void setCapratePropertyBasedCostbasisStrgy2(String capratePropertyBasedCostbasisStrgy2) {
        this.capratePropertyBasedCostbasisStrgy2 = capratePropertyBasedCostbasisStrgy2;
    }

    /**
     * 
     * @return
     *     The capratePropertyBasedArvStrgy2
     */
    @JsonProperty("caprate_property_based_arv_strgy2")
    public String getCapratePropertyBasedArvStrgy2() {
        return capratePropertyBasedArvStrgy2;
    }

    /**
     * 
     * @param capratePropertyBasedArvStrgy2
     *     The caprate_property_based_arv_strgy2
     */
    @JsonProperty("caprate_property_based_arv_strgy2")
    public void setCapratePropertyBasedArvStrgy2(String capratePropertyBasedArvStrgy2) {
        this.capratePropertyBasedArvStrgy2 = capratePropertyBasedArvStrgy2;
    }

    /**
     * 
     * @return
     *     The propertyState
     */
    @JsonProperty("property_state")
    public String getPropertyState() {
        return propertyState;
    }

    /**
     * 
     * @param propertyState
     *     The property_state
     */
    @JsonProperty("property_state")
    public void setPropertyState(String propertyState) {
        this.propertyState = propertyState;
    }

    /**
     * 
     * @return
     *     The propertyCity
     */
    @JsonProperty("property_city")
    public String getPropertyCity() {
        return propertyCity;
    }

    /**
     * 
     * @param propertyCity
     *     The property_city
     */
    @JsonProperty("property_city")
    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    /**
     * 
     * @return
     *     The propertyZip
     */
    @JsonProperty("property_zip")
    public String getPropertyZip() {
        return propertyZip;
    }

    /**
     * 
     * @param propertyZip
     *     The property_zip
     */
    @JsonProperty("property_zip")
    public void setPropertyZip(String propertyZip) {
        this.propertyZip = propertyZip;
    }

    /**
     * 
     * @return
     *     The ownerName
     */
    @JsonProperty("owner_name")
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 
     * @param ownerName
     *     The owner_name
     */
    @JsonProperty("owner_name")
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 
     * @return
     *     The ownerCompanyName
     */
    @JsonProperty("owner_company_name")
    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    /**
     * 
     * @param ownerCompanyName
     *     The owner_company_name
     */
    @JsonProperty("owner_company_name")
    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
    }

    /**
     * 
     * @return
     *     The companyStrtAddress
     */
    @JsonProperty("company_strt_address")
    public String getCompanyStrtAddress() {
        return companyStrtAddress;
    }

    /**
     * 
     * @param companyStrtAddress
     *     The company_strt_address
     */
    @JsonProperty("company_strt_address")
    public void setCompanyStrtAddress(String companyStrtAddress) {
        this.companyStrtAddress = companyStrtAddress;
    }

    /**
     * 
     * @return
     *     The companyAddressCity
     */
    @JsonProperty("company_address_city")
    public String getCompanyAddressCity() {
        return companyAddressCity;
    }

    /**
     * 
     * @param companyAddressCity
     *     The company_address_city
     */
    @JsonProperty("company_address_city")
    public void setCompanyAddressCity(String companyAddressCity) {
        this.companyAddressCity = companyAddressCity;
    }

    /**
     * 
     * @return
     *     The companyAddressState
     */
    @JsonProperty("company_address_state")
    public String getCompanyAddressState() {
        return companyAddressState;
    }

    /**
     * 
     * @param companyAddressState
     *     The company_address_state
     */
    @JsonProperty("company_address_state")
    public void setCompanyAddressState(String companyAddressState) {
        this.companyAddressState = companyAddressState;
    }

    /**
     * 
     * @return
     *     The companyAddressZip
     */
    @JsonProperty("company_address_zip")
    public String getCompanyAddressZip() {
        return companyAddressZip;
    }

    /**
     * 
     * @param companyAddressZip
     *     The company_address_zip
     */
    @JsonProperty("company_address_zip")
    public void setCompanyAddressZip(String companyAddressZip) {
        this.companyAddressZip = companyAddressZip;
    }

    /**
     * 
     * @return
     *     The companyPhone
     */
    @JsonProperty("company_phone")
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * 
     * @param companyPhone
     *     The company_phone
     */
    @JsonProperty("company_phone")
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * 
     * @return
     *     The companyEmail
     */
    @JsonProperty("company_email")
    public String getCompanyEmail() {
        return companyEmail;
    }

    /**
     * 
     * @param companyEmail
     *     The company_email
     */
    @JsonProperty("company_email")
    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    /**
     * 
     * @return
     *     The companyWebsite
     */
    @JsonProperty("company_website")
    public String getCompanyWebsite() {
        return companyWebsite;
    }

    /**
     * 
     * @param companyWebsite
     *     The company_website
     */
    @JsonProperty("company_website")
    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The dealClosingDate
     */
    @JsonProperty("deal_closing_date")
    public String getDealClosingDate() {
        return dealClosingDate;
    }

    /**
     * 
     * @param dealClosingDate
     *     The deal_closing_date
     */
    @JsonProperty("deal_closing_date")
    public void setDealClosingDate(String dealClosingDate) {
        this.dealClosingDate = dealClosingDate;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
