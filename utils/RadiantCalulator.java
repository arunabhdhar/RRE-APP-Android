package org.rehab.app.utils;

import org.rehab.app.models.app.RadiantBudgetSelOption;

/**
 * Created by Krish on 19/6/16.
 */
public class RadiantCalulator {


    //f3InputType :- QLS/LI
    //f12PACCUB : - PB/PU
    private float actualFlipBudget = 0,flipClosingCosts = 0,aRVFlip  = 500000,
            resalePrice = 0,costOfSale = 0;
    private float holdingCostsFinancedSum = 0,flipLoanInterestPaid = 0,flipLoanInterestDiferred = 0,interestPaid = 0,
            interestDiferred = 0,flipPointsSum = 0,flipOtherFinancingCostsSum = 0,purchaseFinanced=0.0f;
    private int totalMonths;
    private boolean financingUsed=true;

    //From Screen 2
    private  String f1PurchasePrice="60000",f2ECC="2000",f3EHC="3000",f3InputType="QLS",f4ICHCosts="Yes",f5RBudget="50000",f5InputType="QLS",f6PRPMonth="5";
    private RadiantBudgetSelOption radiantBudgetSelOption;
    //From Screen 3
    private String f7IsFinancing="Financing",f8LCARVOrCost="Cost",f9MaxPCTBF="80",f10ODP="10",f11OCCPTL="8",
            f12PACCUB="PB",f13InterestRate="20",f14IPDR="No",f15SBEPL="Yes",f16WPPTPDLG="40";
    //From Screen 4
    private String f17ARVFFLIP="500000",f18MTCS="4",f27PRP="10000",f28PCOS="40";
    public String f19TCN,f20M$TCF,f21ATBFNIC,f22CHCIATL,f23TLAm,f24CROLOP,f25TAICER,f26PARV,f29PP,f30ROI,f31ROIAnn;

    private int row24Sum;
    private float[] row28,row29;

    private RadiantCalulator2 radiantCalulator2;

    public RadiantCalulator(){
        if(radiantCalulator2==null){
            radiantCalulator2=new RadiantCalulator2();
        }
    }
    public void setValuesForND2(String f1PurchasePrice,String f2ECC,String f3EHC,String f3InputType,
                                String f4ICHCosts, String f5RBudget,String f6PRPMonth,
                                RadiantBudgetSelOption radiantBudgetSelOption){
        this.f1PurchasePrice=f1PurchasePrice;
        this.f2ECC=f2ECC;
        this.f3EHC=f3EHC;
        this.f3InputType=f3InputType;
        this.f4ICHCosts =f4ICHCosts;
        this.f5RBudget=f5RBudget;
        this.f6PRPMonth=f6PRPMonth;

        this.flipClosingCosts=Float.parseFloat(this.f2ECC);
        totalMonths=Integer.parseInt(this.f18MTCS)+Integer.parseInt(this.f6PRPMonth);
        actualFlipBudget=Float.parseFloat(this.f5RBudget);
        this.radiantBudgetSelOption=radiantBudgetSelOption;
    }

    public void setValueForND3(boolean f7IsFinancing){
        this.financingUsed=f7IsFinancing;

    }
    public void setValueForND3(boolean f7IsFinancing,String f8LCARVOrCost,String f9MaxPCTBF,String f10ODP,
                               String f11OCCPTL,String f12PACCUB,String f13InterestRate,String f14IPDR,
                               String f15SBEPL,String f16WPPTPDLG){
        this.financingUsed=f7IsFinancing;
        this.f8LCARVOrCost=f8LCARVOrCost;
        this.f9MaxPCTBF=f9MaxPCTBF;
        this.f10ODP=f10ODP;
        this.f11OCCPTL=f11OCCPTL;
        this.f12PACCUB=f12PACCUB;
        this.f13InterestRate =f13InterestRate;
        this.f14IPDR=f14IPDR;
        this.f15SBEPL=f15SBEPL;
        this.f16WPPTPDLG=f16WPPTPDLG;
    }

    public boolean isFinancingUsed(){
        return financingUsed;
    }

    public void setValueForND4(String f17ARVFFLIP,String f18MTCS,String f27PRP,String f28PCOS){
        this.f17ARVFFLIP=f17ARVFFLIP;
        this.aRVFlip=Float.parseFloat(f17ARVFFLIP);
        this.f18MTCS=f18MTCS;
        this.f27PRP=f27PRP;
        this.f28PCOS=f28PCOS;
        this.aRVFlip=Float.parseFloat(this.f17ARVFFLIP);
        this.resalePrice=Float.parseFloat(this.f27PRP);
        this.costOfSale=asPercent(this.f28PCOS);
        totalMonths=Integer.parseInt(this.f18MTCS)+Integer.parseInt(this.f6PRPMonth);
    }

    public void init(){
        //For Dummy Check
//        this.flipClosingCosts=Float.parseFloat(f2ECC);
//        this. actualFlipBudget=Float.parseFloat(f5RBudget);
//        this.totalMonths=Integer.parseInt(this.f18MTCS)+Integer.parseInt(this.f6PRPMonth);
//        this.radiantBudgetSelOption=new RadiantBudgetSelOption();
//        this.resalePrice=Float.parseFloat(this.f27PRP);
//        this.costOfSale=asPercent(this.f28PCOS);

        //END
        flipInterestOnLoanPaidMonthly();
        flipDiferredInterestBalanceToPayOffMonthly();
        interestPaid = interestOnDrawsPaidMonthly();
        interestDiferred = interestBalanceToPayOffMonthly();
        calculation();
        radiantCalulator2.initForPreviousValue(f1PurchasePrice,f2ECC,f3EHC,f3InputType,f4ICHCosts,f5RBudget,f6PRPMonth,f7IsFinancing,f8LCARVOrCost,f9MaxPCTBF,f10ODP,
                f11OCCPTL,f12PACCUB,f13InterestRate,f14IPDR,f15SBEPL,f16WPPTPDLG,radiantBudgetSelOption,purchaseFinanced);
    }

    public RadiantCalulator2 getRadiantCalulator2(){
        return radiantCalulator2;
    }

    private void calculation(){
        f19TCN= totalCapitalNeededFlip()+"";
        Logger.e("F19 Values:-->"+f19TCN);
        f20M$TCF=maximumAmountThatCanBeFinanced()+"";
        Logger.e("f20 Values:-->"+f20M$TCF);
        f21ATBFNIC=actualFinancedFlip()+"";
        Logger.e("f21 Values:-->"+f21ATBFNIC);
        f22CHCIATL=pointsInterestRolledUpFlip()+"";
        Logger.e("f22 Values:-->"+f22CHCIATL);
        f23TLAm=totalLoanFlip()+"";
        Logger.e("f23 Values:-->"+f23TLAm);
        f24CROLOP=cashRequiredFlip()+"";
        Logger.e("f24 Values:-->"+f24CROLOP);
        f25TAICER=totalCostBasisFlip()+"";
        Logger.e("f25 Values:-->"+f25TAICER);
        f26PARV=percentageOfAVR()+"";
        Logger.e("f26 Values:-->"+f26PARV);
        f29PP=flipProfit()+"";
        Logger.e("f29 Values:-->"+f29PP);
        f30ROI=flipROI()+"";
        Logger.e("f30 Values:-->"+f30ROI);
        f31ROIAnn=flipROIAnnualized()+"";
        Logger.e("f31 Values:-->"+f31ROIAnn);


    }
    private float totalCapitalNeededFlip(){
        return purchaseAndRehab()+totalCosts();
    }

    private float maximumAmountThatCanBeFinanced(){
        if(!financingUsed){
            return 0.0f;
        }else if(capSelectionARV()){
            return financingCap() * aRVFlip;
        }else{
            return financingCap() * purchaseAndRehab();
        }
    }

    private float purchaseAndRehab(){
        return (Float.parseFloat(f1PurchasePrice)+ actualFlipBudget)  ;
    }

    private float totalCosts(){
        return (Float.parseFloat(f2ECC)+ flipHoldingCosts())  ;
    }

    private float flipHoldingCosts(){
        return  f3InputType.equalsIgnoreCase("DI")?(Float.parseFloat(f3EHC) * totalMonths):
                totalMonths>0? Float.parseFloat(f3EHC):0;
    }


    private boolean capSelectionARV(){
        return  f8LCARVOrCost.equalsIgnoreCase("ARV")?true:false;
    }

    private float financingCap(){
        return  asPercent(f9MaxPCTBF);
    }

    private float asPercent(String value){
        int val=Integer.parseInt(value);
        return  (val/100f);
    }


    //For calculation F21
    private float actualFinancedFlip() {
        return financingUsed? Math.min(maximumAmountThatCanBeFinanced(), purchaseAndRehab()): 0.0f;
    }

    //For CalculationF22
    private float pointsInterestRolledUpFlip(){
        if (financingUsed) {
            return purchaseClosingCostsFinanced() +  holdingCostsFinanced() +
                    (pointsClosingUpfront() ? 0 : (flipPoints() + flipOtherFinancingCosts())) +
                    (interestPaymentsMade() ? 0 : (flipLoanInterest() + flipRehabInterest()));
        } else {
            return 0;
        }
    }

    private int financeClosingHolding(){
        if(f4ICHCosts.equalsIgnoreCase("Yes"))
            return 1;
        return 0;
    }
    private float purchaseClosingCostsFinanced(){
        return  financeClosingHolding()==1 ? Float.parseFloat(f2ECC) : 0;
    }

    private float holdingCostsFinanced(){
        return financeClosingHolding()==1 ? holdingCostsFinancedSum : 0;
    }
    //c_r16
    private float[]  holdingCostsFinancedMonthly() {
        float[] monthlyCosts = new float[totalMonths+1];
        float sum = 0.0f;
        float amount = 0.0f;
        monthlyCosts[0]=0.0f;
        for (int m = 1; m <= totalMonths; m++) {
            amount = flipHoldingCosts() / totalMonths;
            monthlyCosts[m]=amount;
            sum += amount;
        }

        holdingCostsFinancedSum = sum;
        return monthlyCosts;
    }



    private boolean pointsClosingUpfront () {
        if(f12PACCUB.equalsIgnoreCase("PU")){
            return true;
        }return false;
    }

    private float flipPoints () {
        flipPointsSum = rehabDiscountPoints() * actualFinancedFlip();
        return flipPointsSum;
    }
    private float rehabDiscountPoints() {
        return asPercent(f10ODP);
    }
    private float flipOtherFinancingCosts () {
        flipOtherFinancingCostsSum = actualFinancedFlip() * asPercent(f11OCCPTL);
        return flipOtherFinancingCostsSum;
    }
    private boolean interestPaymentsMade () {
        if(f14IPDR.equalsIgnoreCase("YES")){
            return true;
        }
        return false;
    }
    private float flipLoanInterest  () {
        return flipLoanInterestPaid + flipLoanInterestDiferred;
    }
    private float flipRehabInterest () {
        return interestPaid + interestDiferred;
    }


    //c_r29
    private float interestOnDrawsPaidMonthly () {


        float[] interestOnDrawsMonthly = interestOnDrawsMonthly();
        boolean interestPaymentsMade = interestPaymentsMade();
        float[] monthlyAmounts = new float[interestOnDrawsMonthly.length];
        monthlyAmounts[0]=0.0f;
        float sum = 0.0f;
        for (int m = 1; m < interestOnDrawsMonthly.length; m++) {
            float amount = interestPaymentsMade ? interestOnDrawsMonthly[m] : 0.0f;

            monthlyAmounts[m]=amount;
            sum += amount;
        }

        row29 = monthlyAmounts;

        return sum;
//        return 0.0f;
    };


    //c_r28
    private float[] interestOnDrawsMonthly () {
        float[] monthlyAmounts = new float[totalMonths+1];
        monthlyAmounts[0]=0;
//
        float rehabInterestRate = rehabInterestRate();
        float[] cumulativeDrawsFinanced = cumulativeDrawsFinanced();

        for (int m = 1; m <= totalMonths; m++) {
            float amount = cumulativeDrawsFinanced[m - 1] * rehabInterestRate / 12;
            monthlyAmounts[m]=amount;
        }

        row28 = monthlyAmounts;
        return monthlyAmounts;
//        return 0.0f;
    };

    private float rehabInterestRate(){
        return asPercent(f13InterestRate);
    }
    //c_r27
    private float[] cumulativeDrawsFinanced  () {
        float monthlyAmounts[]=new float[totalMonths+1] ;
        int totalMonths = this.totalMonths;
        float[] financedMonthly = financedMonthly();
        monthlyAmounts[0]=financedMonthly[0];

        for (int m = 1; m <= totalMonths; m++) {
            monthlyAmounts[m]=(financedMonthly[m] + monthlyAmounts[m-1]);
        }
        return monthlyAmounts;
    };

    //c_r24
    private  float[] financedMonthly  () {
        int sum = 0;
        float[] rehabDraws = rehabDraws(); //r23
        float monthlyFinance[] = new float[rehabDraws.length];
        float maxCanBeFinanced = maxCanBeFinanced(); //$F$4
        boolean capSelectionARV = capSelectionARV(); //$B$3-arv
        float financingCap = financingCap(); //$B$4
        boolean financingUsed = this.financingUsed; //r9

        float b17 = totalFinancedPriorToRehabDraws(); //B17
        float b11 = purchaseFinanced(); //B11

        for (int m = 0; m < rehabDraws.length; m++) {
            int amount = 0;
            if (financingUsed) {
                amount =(int) (capSelectionARV ? (Math.max(0, Math.min( maxCanBeFinanced -
                        ((m == 0) ? b17 : b11 + sum ), rehabDraws[m]))) :
                        financingCap * rehabDraws[m]);
            }
            sum += amount;
            monthlyFinance[m]=amount;
        }

        row24Sum = sum;

        return monthlyFinance;
//        return 0.0f;
    };

    //c_B17
    private float totalFinancedPriorToRehabDraws() {
        return  (purchaseFinanced() +Float.parseFloat(f2ECC) * financeClosingHolding()) * (financingUsed?1:0);
    };
    //c_B11
    private float purchaseFinanced () {
        purchaseFinanced=financingUsed ? ( capSelectionARV() ? Math.min(Float.parseFloat(f1PurchasePrice), maxCanBeFinanced()) :
                financingCap() * Float.parseFloat(f1PurchasePrice)) : 0;
        return purchaseFinanced;
    };
    // c_r23
    private float[] rehabDraws () {
        float monthlyDraws[]=new float[this.totalMonths+1];
//        // Fill all values with zeros!
        for (int i = 0; i <= this.totalMonths; i++) {
            monthlyDraws[i]=0.0f;
        }
//        //first elem
        if (radiantBudgetSelOption.getOverrideOption()== 1 ||  Integer.parseInt(f6PRPMonth) == 0) {
            monthlyDraws[0] = actualFlipBudget;
        }
//
//        //continue from second elem
        if (radiantBudgetSelOption.getOverrideOption() == 0 && Integer.parseInt(f6PRPMonth)  > 0) { //0-fund ar rehab draw
            if (radiantBudgetSelOption.getEnterBudget() == 1) { // 1 - Detailed input!
                for(int i=0;i<radiantBudgetSelOption.getDetailInput().getItemValues().size();i++){
                    RadiantBudgetSelOption.ItemValue itemValue=radiantBudgetSelOption.getDetailInput().getItemValues().get(i);
                    if(itemValue.getViewType()==2 && itemValue.getBudget()>0 && itemValue.getMonthToPay()>0){
                        monthlyDraws[itemValue.getMonthToPay()] += itemValue.getBudget();
                    }
                }

//                angular.forEach(self.deal.assumption.f05_rehabBudget.groups, function(group) {
//                    angular.forEach(group.items, function(item) {
//                        if (item.budget && item.monthsToBePaid) {
//                            monthlyDraws[item.monthsToBePaid] += parseFloat(item.budget);
//                        }
//                    });
//                });
            } else { // 0 - Quick Lump Sum
                for (int i = 1; i <=  Integer.parseInt(f6PRPMonth); i++) {
                    monthlyDraws[i] = actualFlipBudget / Integer.parseInt(f6PRPMonth);
                }
            }
        }
        return monthlyDraws;
    }


    //c_F4
    private float maxCanBeFinanced () {
        return capSelectionARV() ? financingCap() * aRVFlip : financingCap() * purchaseAndRehab();
    };


    //c_r32
    private float interestBalanceToPayOffMonthly () {
        interestDiferred = diferredInterestBalanceMonthly()[totalMonths];
        return interestDiferred;
    };

    //c_r31
    private float[] diferredInterestBalanceMonthly () {

        float[] interestOnDrawsDiferredMonthly = interestOnDrawsDiferredMonthly();
        float rehabInterestRateMonthly = rehabInterestRate() / 12;
        float[] monthlyInterests = new float[interestOnDrawsDiferredMonthly.length];
        monthlyInterests[0]=0.0f;
        monthlyInterests[1]=(interestOnDrawsDiferredMonthly[1]);

        for (int m = 2; m < interestOnDrawsDiferredMonthly.length; m++) {
            float amount = interestOnDrawsDiferredMonthly[m] + monthlyInterests[m - 1] +
                    monthlyInterests[m - 1] * rehabInterestRateMonthly;

            monthlyInterests[m]=(amount);
        }

        return monthlyInterests;
    };

    //c_r30
    private float[] interestOnDrawsDiferredMonthly () {
        float[] monthlyInterests = new float[row28.length];
        for (int m = 0; m < row28.length; m++) {
            monthlyInterests[m]=(row28[m] - row29[m]);
        }
        return monthlyInterests;
    };

    //For F23
    private float totalLoanFlip(){
        if (financingUsed) {
            return actualFinancedFlip() + pointsInterestRolledUpFlip();
        } else {
            return 0;
        }
    }

    //For F24

    private float cashRequiredFlip(){
        return Math.max(0,Float.parseFloat(f19TCN) + financingCostFlip() -
                Float.parseFloat(f21ATBFNIC) - Float.parseFloat(f22CHCIATL));
    }
    private float financingCostFlip(){
        return  flipPoints() + flipOtherFinancingCosts() +
                flipLoanInterestPaid + flipLoanInterestDiferred +
                interestPaid + interestDiferred;
    }

    //For F25:-
    private float totalCostBasisFlip(){
        return Float.parseFloat(f1PurchasePrice) + flipClosingCosts +
                flipHoldingCosts() + actualFlipBudget + financingCostFlip();
    }

    //For F26:-
    private String percentageOfAVR(){
        return  (((totalCostBasisFlip() / aRVFlip) * 100)+"");
    }

    //For F29
    private float flipProfit(){
        return (resalePrice - totalCostBasisFlip() - costOfSale * resalePrice) *
                (1 - (financingUsed && splitWithLander() ?
                        asPercent(f16WPPTPDLG) : 0));
    }
    private boolean splitWithLander(){
        return f15SBEPL.equalsIgnoreCase("yes")?true:false; /* yes */
    }

    //For F30
    private String flipROI(){
        return Math.floor(Float.parseFloat(f24CROLOP)) > 0 ?
                (ROIPercent()) + "" : "Infinite";
    }
    private float ROIPercent(){
        return (float) 100.0 * Float.parseFloat(f29PP)  / Float.parseFloat(f24CROLOP);
    }

    //For F31

    private String flipROIAnnualized(){
        return Math.floor(Float.parseFloat(f24CROLOP)) > 0 ?
                (ROIPercent() * 12.0 / totalMonths) + "" : "Infinite" ;
    }


    //c_r19
    private float[] flipInterestOnLoanPaidMonthly () {
        float[] flipInterestOnLoanMonthly = flipInterestOnLoanMonthly();
        float[] monthlyInterests = new float[flipInterestOnLoanMonthly.length];
        float sum = 0.0f;
        for (int m = 0; m < flipInterestOnLoanMonthly.length; m++) {
            float amount = 0;
            if (interestPaymentsMade()) {
                amount = flipInterestOnLoanMonthly[m];
            }
            monthlyInterests[m]=amount;
            sum += amount;
        }
        flipLoanInterestPaid = sum;
        return monthlyInterests;
    };

    //c_r18
    private float[] flipInterestOnLoanMonthly() {
        float[] totalFinancedMonthly = totalFinancedMonthly();
        float[] monthlyInterests = new float[totalFinancedMonthly.length];
        float amount = 0.0f;
        monthlyInterests[0]=amount;

        for (int m = 1; m < totalFinancedMonthly.length; m++) {
            amount =  rehabInterestRate() * totalFinancedMonthly[m - 1] / 12;
            monthlyInterests[m]=amount;
        }
        return monthlyInterests;
    };
    //c_r17
    private float[] totalFinancedMonthly () {
        float amount = totalFinancedPriorToRehabDraws();
        float[] holdingCostsFinancedMonthly = holdingCostsFinancedMonthly();
        float[] monthlyTotals = new float[holdingCostsFinancedMonthly.length];
        monthlyTotals[0]=amount;
        for (int i = 1; i < holdingCostsFinancedMonthly.length; i++) {
            amount = (monthlyTotals[i - 1] + (financeClosingHolding()==1 ? holdingCostsFinancedMonthly[i] : 0)) * (financingUsed?1:0);
            monthlyTotals[i]=amount;
        }
        return monthlyTotals;
    };


    //c_r22
    private  float[] flipDiferredInterestBalanceToPayOffMonthly () {
        float sum = 0.0f;
        float[] flipDiferredInterestBalanceMonthly = flipDiferredInterestBalanceMonthly();
        float[] monthlyInterests =new float [flipDiferredInterestBalanceMonthly.length];
        monthlyInterests[0]=0.0f;
        for (int m = 1; m < flipDiferredInterestBalanceMonthly.length; m++) {
            float amount = 0.0f;
            if (m == totalMonths) {
                amount = flipDiferredInterestBalanceMonthly[m];
            }
            monthlyInterests[m]=amount;
            sum += amount;
        }
        flipLoanInterestDiferred = sum;
        return monthlyInterests;
    };

    //c_r21
    private  float[] flipDiferredInterestBalanceMonthly() {
        float[] flipInterestOnLoanDiferredMonthly = flipInterestOnLoanDiferredMonthly();
        float[]  monthlyInterests = new float[flipInterestOnLoanDiferredMonthly.length];
        monthlyInterests[0]=0.0f;
        for (int m = 1; m < flipInterestOnLoanDiferredMonthly.length; m++) {
            float amount = flipInterestOnLoanDiferredMonthly[m] + monthlyInterests[m - 1] +
                    monthlyInterests[m - 1] * rehabInterestRate() / 12;
            monthlyInterests[m]=amount;
        }
        return monthlyInterests;
    };
    //c_r20
    private  float[] flipInterestOnLoanDiferredMonthly  () {
        float[] flipInterestOnLoanMonthly = flipInterestOnLoanMonthly();
        float[] flipInterestOnLoanPaidMonthly = flipInterestOnLoanPaidMonthly();
        float[] monthlyInterests=new float[flipInterestOnLoanMonthly.length];
        for (int m = 0; m < flipInterestOnLoanMonthly.length; m++) {
            monthlyInterests[m]=flipInterestOnLoanMonthly[m] - flipInterestOnLoanPaidMonthly[m];
        }
        return monthlyInterests;
    };
}
