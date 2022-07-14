package com.yanliang.algo.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanliang
 * @since 2022/7/11 17:02
 */
public class PaymentMethodTest {

    public static void main(String[] args) throws StripeException {

        Stripe.apiKey = "sk_test_51KxnokLKoz6S2lOQSJr5bsRocgURz2as4X2zC11IBqt1kUn6KO2kO80EZFvsAH9TuN85TBR4PUOEh2e2vjS2SK9F00VFNCaxel";

        Map<String, Object> card = new HashMap<>();
        card.put("number", "4242424242424242");
        card.put("exp_month", 7);
        card.put("exp_year", 2023);
        card.put("cvc", "314");
        Map<String, Object> methodParams = new HashMap<>();
        methodParams.put("type", "card");
        methodParams.put("card", card);

        PaymentMethod paymentMethod =
                PaymentMethod.create(methodParams);


        PaymentMethod method = PaymentMethod.retrieve(paymentMethod.getId());
        method.detach();
        PaymentMethod retrieve = PaymentMethod.retrieve(paymentMethod.getId());

        System.out.println();

    }
}
