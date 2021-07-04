package pers.pk;

import pers.pk.exception.UtilException;
import pers.pk.util.format.KVFormatUtil;
import pers.pk.util.json.JsonUtil;

import java.util.Map;

public class Test {

    public static void main(String[] args) throws UtilException {

        String input = "payerId=283***82,payeeId=1002***334,payModel=CASHIER,cashierInfo=,extInfo={FINANCIAL_CODE=AF1189, FINANCE_CHANNEL=2, RCS_FINANCE_ORIGIN=card, GOODS_NAME=东证6号X十五类35天7期, IS_RISK=true, RCS_CARD_BIND_TIME=20190506100951, DEPOSIT_METHOD=00, FINANCIAL_TYPE=03, RCS_SP_NAME=基金代销, RCS_IS_SAFETY_CARD_PURCHASE=1, RCS_SAFETY_NO=6217GazOiHFGq+zJSTdbauu+4g==7000, FINANCE_TYPE=111, FINANCE_BALANCE_AMOUNT=0, GOODS_ID=88000481, FIRST_FINANCE_TIME=20140325213411, DISTRIBUTOR_CODE=165},needDecryptData=true,requestId=20210628100212139104612382,bizNo=2021062810026223341000254dbd5382,bizDefine=brokerPurchase,payAmount=10000000,currency=156,createTime=20210628100213,context={logonId=,userId=28374382,ip=,mac=,certStatus=,login=false,requestFrom=LICAI,accessChannel=APP,appVersion=1,partner=,needNotify=true,noitfyUrl=http://xxxxx},sure=false,accountSubject=";

        Map<String, Object> map = KVFormatUtil.equalSign2Map(input);

        System.out.println(JsonUtil.mapToJson(map));
    }
}
