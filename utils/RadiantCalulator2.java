package org.rehab.app.utils;

import org.rehab.app.models.app.RadiantBudgetSelOption;

import java.math.BigDecimal;

/**
 */
public class RadiantCalulator2 {
    /**
     * Set the previous value
     */
    private float purchasePrice,closingCosts,holdingCosts,maxPcToBeFinanced,rehabDiscountPoints,
            otherPoints,rehabInterestRate,lenderSplit,actualRefiBudget,monthsRefiRehab;

    private float ARVforRent=50000,refiPcAppraisal=.45f,
            newMortgageRate=.13f,
            amortizationYears=11,refiDiscountPts=.08f;
    private int monthsToRefi=3;
    private String operatingIncome="4060.34",operatingExpense="546.034";

    private boolean financeClosingHolding,financingUsed,capSelectionARV,pointsClosingUpfront,f3InputMethod,interestPaymentsMade,splitWithLander,refiFinancing=true;

    private float I3=0.0f,I4=0.0f,B110=0.0f,B157=0.0f,B178=0.0f,B179=0.0f,purchaseFinancedRefi,financingCostRent,refiHoldingCosts,B119=0.0f,purchaseFinanced=0.0f;
    private int totalMonths;
    private RadiantBudgetSelOption radiantBudgetSelOption;
    private float[] r118,r116,r117,r119,r120,r121,r122,r123,r125,r126,r129,r130,r131,r132,r133,r137;
    //Calculated Values
    public String f41_pcOfArv,f55_ROIOnCashInvested,f59_cashOnCash,f60_propertyDCR,f61_paybackPeriod,
            f62_capRateOfPropertyCost,f63_capRateOfPropertyARV;
    public float f53_cashOutAtRefi,f54_profitAtRefi,f39_costsInterestAddedToLoan,f41_cacheRequired,f56_originalMoneyTied,f42_totalAllInCosts,f52_refiLoanAmount,
            f40_totalLoanAmount,f57_equityInDeal,f58_cashFlow,f51_mortgagePayment,f45_netOperatingIncome,f36_totalCapitalNeeded,f37_maxCanBeFinanced,f38_actualToBeFinanced;


    public void initForPreviousValue(String f1Value, String f2Value, String f3Value,String f3InputMethod, String f4Value, String f5Value, String f6Value, String f7Value, String f8Value,
                                     String f9Value, String f10Value, String f11Value, String f12Value, String f13Value, String f14Value,
                                     String f15Value, String f16Value,RadiantBudgetSelOption radiantBudgetSelOption,float purchaseFinanced){
        //input values
        this.purchasePrice         = asFloat(f1Value);
        this.closingCosts          = asFloat(f2Value);
        this.holdingCosts          = asFloat(f3Value);
        this.f3InputMethod         =f3InputMethod.equalsIgnoreCase("DI")?true:false;
        this.financeClosingHolding = f4Value.equalsIgnoreCase("yes")?true:false; /*yes*/
        this.financingUsed         = f7Value.equalsIgnoreCase("Financing")?true:false; /* Financing */
        this.capSelectionARV       = f8Value.equalsIgnoreCase("ARV")?true:false; /* ARV */
        this.maxPcToBeFinanced     = asPercent(f9Value);
        this.rehabDiscountPoints   = asPercent(f10Value);
        this.otherPoints           = asPercent(f11Value);
        this.pointsClosingUpfront  = f12Value.equalsIgnoreCase("PU")?true:false;// parseInt(f12Value) == 0 /* PaidUpFront */;
        this.rehabInterestRate     = asPercent(f13Value);
        this.interestPaymentsMade  = (f14Value.equalsIgnoreCase("yes")?true:false) ;/* Yes */;
        this.splitWithLander       = f15Value.equalsIgnoreCase("yes")?true:false; /* yes */
        this.lenderSplit           = asPercent(f16Value);
        this.actualRefiBudget = asFloat(f5Value);
        this.monthsRefiRehab  = parseInt('0' + f6Value);
        this.radiantBudgetSelOption=radiantBudgetSelOption;
        this.purchaseFinanced=purchaseFinanced;
    }

    public void initCurrentValue(String f34_ARVforRent,String f35_monthsToRent,String f43_operatingIncome,String f44_operatingExpenses,String f46_refiPermanentFinancing,String f47_refiPcOfAppraisal,
                                 String f48_newMortgageRate,String f49_amortizationYears,String f50_refiDiscountPoints){
        this.ARVforRent       = asFloat(f34_ARVforRent);
        this.monthsToRefi     = parseInt('0' + f35_monthsToRent);
        this.operatingIncome  = f43_operatingIncome;
        this.operatingExpense = f44_operatingExpenses;
        this.refiFinancing    = f46_refiPermanentFinancing.equalsIgnoreCase("yes")?true:false; // yes - 1
        this.refiPcAppraisal  = asPercent(f47_refiPcOfAppraisal);
        this.newMortgageRate  = asPercent(f48_newMortgageRate);
        this.amortizationYears = parseInt(f49_amortizationYears);
        this.refiDiscountPts  = asPercent(f50_refiDiscountPoints);
        secondInitValue();
    }
    public void secondInitValue(){
        //init values
        this.totalMonths = (int)(this.monthsRefiRehab + this.monthsToRefi);
        this.refiHoldingCosts =this.f3InputMethod ? this.holdingCosts * this.totalMonths : ((this.totalMonths>0) ? this.holdingCosts : 0); //c24
        I3 = this.purchasePrice + this.actualRefiBudget ;
        I4 = this.capSelectionARV ? this.maxPcToBeFinanced * this.ARVforRent : this.maxPcToBeFinanced * I3;
        this.purchaseFinancedRefi = this.financingUsed ? (this.capSelectionARV ? Math.min(this.purchasePrice, I4) : this.maxPcToBeFinanced * this.purchasePrice) : 0; //B113
        this.financingCostRent = 0.0f; //sum report L32

        onCalculation();
    }
    private void onCalculation(){
        r118 = r118Fn();

        //calculations
        this.f36_totalCapitalNeeded = this.purchasePrice + this.actualRefiBudget + this.closingCosts + this.refiHoldingCosts;
        Logger.e("f36_totalCapitalNeeded:->"+f36_totalCapitalNeeded);
        this.f37_maxCanBeFinanced   = this.financingUsed ? ((this.capSelectionARV) ? (this.maxPcToBeFinanced * this.ARVforRent):
                (this.maxPcToBeFinanced * (this.purchasePrice + this.actualRefiBudget))) : 0;
        Logger.e("f37_maxCanBeFinanced:->"+f37_maxCanBeFinanced);
        this.f38_actualToBeFinanced = this.financingUsed ? Math.min(this.f37_maxCanBeFinanced,
                this.f36_totalCapitalNeeded - (this.closingCosts + this.refiHoldingCosts)) : 0;

        Logger.e("f38_actualToBeFinanced:->"+f38_actualToBeFinanced);
        r116 = r116Fn();
        r117 = r117Fn();
        this.f39_costsInterestAddedToLoan = Float.parseFloat(f39());
        Logger.e("f39_costsInterestAddedToLoan:->"+f39_costsInterestAddedToLoan);
        this.f40_totalLoanAmount = this.f39_costsInterestAddedToLoan  + this.f38_actualToBeFinanced;
        Logger.e("f40_totalLoanAmount:->"+f40_totalLoanAmount);
        this.f41_cacheRequired   = f41();
        Logger.e("f41_cacheRequired:->"+f41_cacheRequired);
        this.f42_totalAllInCosts = (this.purchasePrice + this.closingCosts + this.refiHoldingCosts + this.actualRefiBudget + this.financingCostRent);
        Logger.e("f42_totalAllInCosts:->"+f42_totalAllInCosts);
        this.f41_pcOfArv         = (this.f42_totalAllInCosts / this.ARVforRent * 100) + "%" ;
        Logger.e("f41_pcOfArv:->"+f41_pcOfArv);


        this.f45_netOperatingIncome =(Float.parseFloat(this.operatingIncome) - Float.parseFloat(this.operatingExpense));
        Logger.e("f45_netOperatingIncome:->"+f45_netOperatingIncome);


        this.f52_refiLoanAmount  = (this.refiFinancing ? this.ARVforRent * this.refiPcAppraisal : 0 );
        Logger.e("f52_refiLoanAmount:->"+f52_refiLoanAmount);
        this.f51_mortgagePayment = f51();
        Logger.e("f51_mortgagePayment:->"+f51_mortgagePayment);

        this.f53_cashOutAtRefi   = this.refiFinancing ?
                this.f52_refiLoanAmount- (this.f40_totalLoanAmount+  this.refiDiscountPts * this.f52_refiLoanAmount) : 0 ;
        Logger.e("f53_cashOutAtRefi:->"+f53_cashOutAtRefi);
        this.f54_profitAtRefi    = Math.max(0, this.f53_cashOutAtRefi - this.f41_cacheRequired) *
                (1 - ((this.financingUsed && this.splitWithLander) ? this.lenderSplit : 0));
        Logger.e("f54_profitAtRefi:->"+f54_profitAtRefi);

        this.f55_ROIOnCashInvested = (this.totalMonths == 0 || Math.round(this.f41_cacheRequired) == 0) ? "N/A" :
                round((this.f54_profitAtRefi / this.f41_cacheRequired / this.totalMonths * 12 * 100),2) + "%";
        Logger.e("f55_ROIOnCashInvested:->"+f55_ROIOnCashInvested);

        this.f56_originalMoneyTied = Math.max(0, this.f41_cacheRequired - this.f53_cashOutAtRefi);
        Logger.e("f56_originalMoneyTied:->"+f56_originalMoneyTied);
        this.f57_equityInDeal = this.refiFinancing ? (1 - this.refiPcAppraisal) * this.ARVforRent :
                (this.financingUsed ? this.ARVforRent - this.f40_totalLoanAmount : this.ARVforRent - this.f42_totalAllInCosts) ;
        Logger.e("f57_equityInDeal:->"+f57_equityInDeal);
        this.f58_cashFlow  = Float.parseFloat(this.operatingIncome) - Float.parseFloat(this.operatingExpense) +this.f51_mortgagePayment;
        Logger.e("f58_cashFlow:->"+f58_cashFlow);
        this.f59_cashOnCash = this.refiFinancing ?
                (this.f56_originalMoneyTied > 0 ? round((this.f58_cashFlow * 12 * 100 / this.f56_originalMoneyTied),1) + "%" : "infinite" ) :
                (Math.round(this.f41_cacheRequired) == 0 ? "infinite" : round((this.f58_cashFlow * 12 * 100/ this.f41_cacheRequired),1) + "%");
        Logger.e("f59_cashOnCash:->"+f59_cashOnCash);

        this.f60_propertyDCR   = B157fn() == 0 ? "N/A" : round((-1 * this.f45_netOperatingIncome/ this.f51_mortgagePayment),2)+"";
        Logger.e("f60_propertyDCR:->"+f60_propertyDCR);
        this.f61_paybackPeriod = this.refiFinancing ?
                (this.f56_originalMoneyTied > 0 ? round((this.f56_originalMoneyTied / (this.f58_cashFlow * 12)),2)+"" : "No Cash Tied Up to Pay Back!") :
                (round((this.f41_cacheRequired / (f58_cashFlow * 12)),2))+"";
        Logger.e("f61_paybackPeriod:->"+f61_paybackPeriod);
        String f62CapCal=(this.f45_netOperatingIncome * 12 * 100 / this.ARVforRent)+"";
        if(f62CapCal.equals("Infinity")){
            this.f62_capRateOfPropertyCost =  "Infinity";
        }else{
            this.f62_capRateOfPropertyCost =  round(Float.parseFloat(f62CapCal),2) + "%";
        }
        Logger.e("f62_capRateOfPropertyCost:->"+f62_capRateOfPropertyCost);
        String f62CapRate=(this.f45_netOperatingIncome * 12 * 100 / this.f42_totalAllInCosts)+"";
        if(f62CapRate.equals("Infinity")){
            this.f63_capRateOfPropertyARV =  "Infinity";
        }else{
            this.f63_capRateOfPropertyARV =  round(Float.parseFloat(f62CapRate),2) + "%";
        }
//        this.f63_capRateOfPropertyARV  =round((this.f45_netOperatingIncome * 12 * 100 / this.f42_totalAllInCosts) ,2)+ "%";
        Logger.e("f63_capRateOfPropertyARV:->"+f63_capRateOfPropertyARV);
    }



    /**
     * Round to certain number of decimals
     *
     * @param d
     * @param decimalPlace
     * @return
     */
    private float round(float d, int decimalPlace) {
        try {
            BigDecimal bd = new BigDecimal(Float.toString(d));
            bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
            return bd.floatValue();
        }catch (Exception e){
            return 0;
        }
    }
    private float B157fn() {
        B157 = this.refiFinancing ? this.f52_refiLoanAmount: r137[r137.length-1];
        return B157;
    };
    private float[] r118Fn() {
        float[] monthlyCosts;
        if (this.totalMonths == 0) {
            monthlyCosts=new float[1];
            monthlyCosts[0] = 0;
        } else {
            monthlyCosts=new float[(int)this.totalMonths];
            for (int m=0;  m < this.totalMonths; m ++){
                monthlyCosts[m]=this.refiHoldingCosts/this.totalMonths;
            }
        }
        return monthlyCosts;
    };




    private float[] r116Fn() {
        float[] monthlyCosts =new float[(int)this.totalMonths+1];

        for (int m=0;  m <= this.totalMonths; m ++){
            monthlyCosts[m]=this.pointsClosingUpfront ? ((m == 0) ? this.rehabDiscountPoints *this.f38_actualToBeFinanced : 0 )
                    : ((m == this.totalMonths) ? this.rehabDiscountPoints *this.f38_actualToBeFinanced : 0 );
        }

        return monthlyCosts;
    };

    private float[] r117Fn() {
        float[] monthlyCosts =new float[(int)this.totalMonths+1];

        for (int m=0;  m <= this.totalMonths; m ++){
            monthlyCosts[0]= this.pointsClosingUpfront ? ((m == 0) ? this.otherPoints * this.f38_actualToBeFinanced: 0 )
                    : ((m == this.totalMonths) ? this.otherPoints * this.f38_actualToBeFinanced : 0 );
        }
        return monthlyCosts;
    };


    private String f39() {
        return this.financingUsed ? (this.refiClosingCostsFinanced() + this.refiHoldingCostsFinanced() +
                (this.pointsClosingUpfront ? 0 : (this.rehabDiscountPoints + this.otherPoints) * this.f38_actualToBeFinanced) +
                (this.interestPaymentsMade? 0 : (this.rentLoanInterest() + this.rentRehabInterest())))+"" : "0";

    };

    //c_B178
    private float rentLoanInterest() {
        B119 = this.financingUsed ? this.purchaseFinanced + (this.financeClosingHolding ? this.closingCosts : 0) : 0;
        float[] r119 = new float[this.totalMonths+1];
        r119[0] = B119;
        for (int m=1;  m <= this.totalMonths; m ++){
            r119[m]=(this.financingUsed ? r119[m-1] + (this.financeClosingHolding ?  r118[m-1] : 0) : 0);
        }
        float[] r120 = new float[this.totalMonths];
        for (int m=0;  m < this.totalMonths; m ++){
            r120[m]=(this.rehabInterestRate * r119[m] / 12);
        }

        float[] r121 = new float[this.totalMonths];
        float sum121 = 0.0f;
        for (int m=0;  m < this.totalMonths; m ++){
            r121[m]=(this.interestPaymentsMade? r120[m] : 0);  //1 true
            sum121 += r121[m];
        }

        float[] r122 = new float[this.totalMonths];
        for (int m=0;  m < this.totalMonths; m ++){
            r122[m]=(r120[m] - r121[m]);
        }

        float[] r123 = new float[this.totalMonths+1];
        r123[0] = 0;
        for (int m=1;  m <= this.totalMonths; m ++) {
            if (m == 1) {
                r123[m]=(r122[m-1] - r123[m-1]);
            } else {
                r123[m]=(r122[m-1] + r123[m-1] * (1 + this.rehabInterestRate / 12));
            }
        }

        float sum124 = r123[r123.length-1];

        B178 = sum121 + sum124;
        return B178;
    };


    private void r133Fn() {
        r133 = new float[this.totalMonths];
        r133[0] = r132[0];

        for (int m=1;  m < this.totalMonths; m ++) {
            r133[m]=(r132[m] + r133[m-1] + r133[m-1] * this.rehabInterestRate / 12);
        }
    }

    private float[] r134fn () {
        r133Fn();
        float[] r134 = new float[this.totalMonths];
        for (int m=0;  m < this.totalMonths; m ++) {
            r134[m]=((m == this.totalMonths - 1) ? r133[m] : 0);
        }
        return r134;
    };

    //c_B179
    private float rentRehabInterest() {
        r131fn();
        float sum131 = 0.0f;
        for (int m=0; m < r131.length; m++) {
            sum131 += r131[m];
        }

        float[] r134 = r134fn();
        float sum134 = 0.0f;
        for (int m=0; m < r134.length; m++) {
            sum134 += r134[m];
        }
        B179 = sum131 + sum134;
        return B179;
    };

    private float[] r126fn() {
        r125fn();
        r126 =new float[ this.totalMonths+1];
        float B126 = this.financingUsed ? (this.capSelectionARV ? Math.max(0, Math.min(I4-B119, r125[0])) : r125[0] * this.maxPcToBeFinanced) : 0 ;
        r126[0]=(B126);

        r129 = new float[ this.totalMonths+1];
        r129[0] = B126;
        float[] r136 = new float[ this.totalMonths+1];
        r136[0]=(r136field(r116[0], r117[0], r126[0], r129[0], true));

        for (int m=1;  m <= this.totalMonths; m++){
            if(this.financingUsed) {
                r126[m]=(this.capSelectionARV ? Math.max(0, Math.min(I4 - r136[m-1], r125[m])) : r125[m] * this.maxPcToBeFinanced);
                r129[m]=(r129field(r126[m], r129[m-1]));
                r136[m]=(r136field(r116[m], r117[m], r126[0], r129[m], false));
            } else {
                r126[m]=(0);
                r129[m]=(r126[m] + r129[m-1]);
            }
        }

        return r126;
    };

    private float[] r127fn () {
        float[] r127 =new float[r125.length];

        for (int m=0;  m < r125.length; m ++){
            r127[m]=(r125[m] - r126[m]);
        }
        return r127;
    };


    private float[] r125fn() {
        r125 = new float[this.totalMonths+1];

        // Fill all values with zeros!
        for (int i = 0; i <= this.totalMonths; i++) {
            r125[i]=(0.0f);
        }

        if (radiantBudgetSelOption.getOverrideOption()== 1 ||  this.monthsRefiRehab == 0) {
            r125[0] = this.actualRefiBudget;
        }
//
//        //continue from second elem
        if (radiantBudgetSelOption.getOverrideOption() == 0 &&  this.monthsRefiRehab   > 0) { //0-fund ar rehab draw
            if (radiantBudgetSelOption.getEnterBudget() == 1) { // 1 - Detailed input!
                for(int i=0;i<radiantBudgetSelOption.getDetailInput().getItemValues().size();i++){
                    RadiantBudgetSelOption.ItemValue itemValue=radiantBudgetSelOption.getDetailInput().getItemValues().get(i);
                    if(itemValue.getViewType()==2 && itemValue.getBudget()>0 && itemValue.getMonthToPay()>0){
                        r125[itemValue.getMonthToPay()] += itemValue.getBudget();
                    }
                }
//                 angular.forEach(self.deal.assumption.f05_rehabBudget.groups, function(group) {
//                angular.forEach(group.items, function(item) {
//                    if (item.budget && item.monthsToBePaid) {
//                        r125[item.monthsToBePaid] += parseFloat(item.budget);
//                    }
//                });
//            });
            } else { // 0 - Quick Lump Sum
                for (int i = 1; i <=  this.monthsRefiRehab; i++) {
                    r125[i] =this.actualRefiBudget / this.monthsRefiRehab;
                }
            }
        }
        return r125;
    };

    //c_136
    private  float r136field(float v116, float v117,float v126,float v129,boolean first) {
        if (first)
            return this.purchaseFinancedRefi + v126 + (this.pointsClosingUpfront ? 0 : v116 + v117);

        return this.purchaseFinancedRefi + v129 + (this.pointsClosingUpfront ? 0 : v116 + v117);
    };
    private float r129field(float v126,float v129) {
        return (v126 == 0) ? v129 : v126 + v129;
    };

    private float[] r130fn() {
        r126fn();
        r130 = new float[this.totalMonths];
        for (int m=0;  m < this.totalMonths; m ++) {
            r130[m]=(this.financingUsed ? (r129[m] * this.rehabInterestRate / 12) : 0);
        }
        return r130;
    };


    private float[] r131fn() {
        r130fn();
        r131 = new float[totalMonths];
        r132 = new float[totalMonths];
        float sum131 = 0.0f;

        for (int m=0;  m < this.totalMonths; m ++) {
            r131[m]=(this.interestPaymentsMade ? r130[m] : 0);
            sum131 += r131[m];
            r132[m]=(r130[m] - r131[m]);
        }

        return r131;
    };

    //c_B174
    private float refiClosingCostsFinanced () {
        return this.financeClosingHolding ? this.closingCosts : 0;
    };

    //c_B175
    private float refiHoldingCostsFinanced () {
        if (!this.financeClosingHolding)
            return 0;

        int sum118 = 0;
        for (int m = 0; m < r118.length; m ++ ){
            sum118 += r118[m];
        }
        return sum118;
    };

    private float asPercent(String value){
        int val=Integer.parseInt(value);
        return  (val/100f);
    }


    private float asFloat(String value){
        try{
            return  Float.parseFloat(value);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return 0.0f;
        }
    }

    private int parseInt(String value){
        try{
            return  Integer.parseInt(value);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }


//...........................................

    private float f41() {
        if (this.interestPaymentsMade) {
            this.rentLoanInterest();
            this.rentRehabInterest();
        }
        this.financingCostRent = B176() + B177() + B178 + B179;

        return Math.max(0, this.f36_totalCapitalNeeded - this.f38_actualToBeFinanced ) +
                (this.financingCostRent - this.f39_costsInterestAddedToLoan );
    };

    //c_B176
    private float B176() {
        float sum = 0.0f;
        for (int m=0; m < r116.length; m++){
            sum += r116[m];
        }

        return sum;
    };

    //c_B177
    private float B177() {
        float sum = 0.0f;
        for (int m=0; m < r117.length; m++){
            sum += r117[m];
        }
        return sum;
    };
//.............................................

    private float f51() {
        return this.refiFinancing ? (this.amortizationYears!=0? PMT(this.newMortgageRate / 12, this.amortizationYears * 12, this.f52_refiLoanAmount, 0) :
                -1 * this.f52_refiLoanAmount* this.newMortgageRate / 12) : -1*B145();
    };

    // float type
    private float PMT (float rate, float nper,float  pv, float fv) {
        if (fv==0) fv = 0;
//        if (!type) type = 0;
        int type=0;
        if (rate == 0) return -(pv + fv)/nper;
        float pvif = (float) Math.pow(1 + rate, nper);
        float pmt = rate / (pvif - 1) * -(pv * pvif + fv);
        if (type == 1) {
            pmt /= (1 + rate);
        };
        return pmt;
    };
    //c_B145
    private float B145(){
        float sum118 = 0.0f;
        if(!this.financingUsed && !this.refiFinancing) {
            //init rows data
            this.rentLoanInterest();
            this.rentRehabInterest();
        }

        r137 =new float [ this.totalMonths];
        r137[0] = this.purchaseFinancedRefi + ((this.financingUsed && this.financeClosingHolding) ? this.closingCosts : 0) + r126[0]
                + (this.pointsClosingUpfront ? 0 : r116[0] + r117[0]);
        for (int m=0;  m < this.totalMonths; m ++){
            sum118 += r118[m];
            r137[m]=( this.purchaseFinancedRefi + ((this.financingUsed && this.financeClosingHolding) ? this.closingCosts + sum118 : 0) +
                    r129[m] + (this.pointsClosingUpfront ? 0 : r116[m+1] + r117[m+1]) + r123[m+1] + r133[m]);
        }

        return r137[r137.length -1] * this.rehabInterestRate / 12;
    };
//...............................



}
