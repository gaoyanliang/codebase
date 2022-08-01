package com.yanliang.algo.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentSource;
import com.stripe.model.Token;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanliang
 * @since 2022/7/11 14:53
 */
public class TokenTest {

    public static void main(String[] args) throws StripeException {
        Stripe.apiKey = "sk_test_51KxnokLKoz6S2lOQSJr5bsRocgURz2as4X2zC11IBqt1kUn6KO2kO80EZFvsAH9TuN85TBR4PUOEh2e2vjS2SK9F00VFNCaxel";

        Map<String, Object> cards = new HashMap<>();
        cards.put("number", "4242424242424242");
        cards.put("exp_month", 7);
        cards.put("exp_year", 2023);
        cards.put("cvc", "314");
        Map<String, Object> params = new HashMap<>();
        params.put("card", cards);

        Token token = Token.create(params);

        Map<String, Object> paramss = new HashMap<>();
        paramss.put("source", token.getId());


        // customer
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put(
                "description",
                "My First Test Customer (created for API docs at https://www.stripe.com/docs/api)"
        );
        customerParams.put("email", "yanliang123@moego.pet");
        customerParams.put("name", "yanliang213");
        customerParams.put("phone", "12345632141789");
        Customer customer = Customer.create(customerParams);


        PaymentSource paymentSource = customer.getSources().create(paramss);
        System.out.println(paymentSource.getId());

    }
}
