package com.yanliang.algo.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanliang
 * @since 2022/7/11 17:02
 */
public class PaymentMethodTest {

    public static void main(String[] args) throws StripeException {

        Stripe.apiKey = "sk_test_51KxnokLKoz6S2lOQSJr5bsRocgURz2as4X2zC11IBqt1kUn6KO2kO80EZFvsAH9TuN85TBR4PUOEh2e2vjS2SK9F00VFNCaxel";

        // 初始化 payment intent
        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        // 默认值
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 100L);
        params.put("currency", "usd");
        params.put("payment_method_types", paymentMethodTypes);

        // 设置当前商家的 stripe account
        //params.put("transfer_data[destination]", "acct_1KxnokLKoz6S2lOQ");
        //params.put("on_behalf_of", "acct_1KxnokLKoz6S2lOQ");

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println(paymentIntent.getId());

//        Map<String, Object> card = new HashMap<>();
//        card.put("number", "4242424242424242");
//        card.put("exp_month", 7);
//        card.put("exp_year", 2023);
//        card.put("cvc", "314");
//        Map<String, Object> methodParams = new HashMap<>();
//        methodParams.put("type", "card");
//        methodParams.put("card", card);
//
//        PaymentMethod paymentMethod =
//                PaymentMethod.create(methodParams);
//
//
//        PaymentMethod method = PaymentMethod.retrieve(paymentMethod.getId());
//        method.detach();
//        PaymentMethod retrieve = PaymentMethod.retrieve(paymentMethod.getId());
//
//        System.out.println();

    }
}
