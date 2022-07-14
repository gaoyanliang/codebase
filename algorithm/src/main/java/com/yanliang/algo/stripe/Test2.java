package com.yanliang.algo.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;

/**
 * @author yanliang
 * @since 2022/7/8 20:52
 */
public class Test2 {

    public static void main(String[] args) throws StripeException {

        Stripe.apiKey = "sk_test_qTVJqJ7bFg2k2BRcDOLcstXq";
        PaymentIntent paymentIntent = PaymentIntent.retrieve("pi_3LJE7xIZwcIFVLGr1ToStpIF");

        PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentIntent.getPaymentMethod());


        PaymentMethod.Card card = paymentMethod.getCard();
        System.out.println(paymentIntent.getPaymentMethod());
        System.out.println(card.getExpMonth());
        System.out.println(card.getBrand());
        System.out.println(card.getLast4());
        System.out.println(card.getExpYear());



        System.out.println();
    }
}
