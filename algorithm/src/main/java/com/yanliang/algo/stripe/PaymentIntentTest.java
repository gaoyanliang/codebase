package com.yanliang.algo.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentSource;
import com.stripe.model.Token;
import com.stripe.model.issuing.Card;
import com.stripe.net.RequestOptions;
import com.stripe.param.AccountCreateParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanliang
 * @since 2022/7/6 17:56
 */
public class PaymentIntentTest {

    public static void main(String[] args) throws StripeException {

        Stripe.apiKey = "sk_test_51KxnokLKoz6S2lOQSJr5bsRocgURz2as4X2zC11IBqt1kUn6KO2kO80EZFvsAH9TuN85TBR4PUOEh2e2vjS2SK9F00VFNCaxel";

        // stripe account
//        AccountCreateParams params =
//                AccountCreateParams
//                        .builder()
//                        .setType(AccountCreateParams.Type.STANDARD)
//                        .build();
//        Map<String, Object> cardPayments =
//                new HashMap<>();
//        cardPayments.put("requested", true);
//        Map<String, Object> transfers = new HashMap<>();
//        transfers.put("requested", true);
//        Map<String, Object> capabilities =
//                new HashMap<>();
//        capabilities.put("card_payments", cardPayments);
//        capabilities.put("transfers", transfers);
//        Map<String, Object> accountParams = new HashMap<>();
//        accountParams.put("type", "custom");
//        accountParams.put("country", "US");
//        accountParams.put("email", "yanliang@moego.pet");
//        accountParams.put("capabilities", capabilities);
//        Account account = Account.create(accountParams);

        // payment method
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


        // customer
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put(
                "description",
                "My First Test Customer (created for API docs at https://www.stripe.com/docs/api)"
        );
        customerParams.put("email", "yanliang@moego.pet");
        customerParams.put("name", "yanliang");
        customerParams.put("phone", "123456789");
        Customer customer = Customer.create(customerParams);


        // payment intent
        List<Object> paymentMethodTypes =
                new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> intentParams = new HashMap<>();
        intentParams.put("amount", 2000);
        intentParams.put("currency", "usd");
        intentParams.put(
                "payment_method_types",
                paymentMethodTypes
        );
        intentParams.put("payment_method", paymentMethod.getId());
//        intentParams.put("transfer_data[destination]", "acct_1KxnokLKoz6S2lOQ");
//        intentParams.put("on_behalf_of", "acct_1KxnokLKoz6S2lOQ");

        RequestOptions options = RequestOptions
                .builder()
                .setIdempotencyKey("wFprRyCQRtlhYIVc")
                .build();

        PaymentIntent paymentIntent =
                PaymentIntent.create(intentParams);
//        paymentIntent.setPaymentMethod(paymentMethod.getId());
        paymentIntent.setCustomer("cus_M1HCTaXyblKTZ5");
        paymentIntent.setPaymentMethod("card_1LJEk4LKoz6S2lOQb70cup0t");

        System.out.println("payment intent id: " + paymentIntent.getId());

//        System.out.println(paymentIntent.toJson());


        PaymentMethod retrieve = PaymentMethod.retrieve(paymentIntent.getPaymentMethod());

        retrieve.detach();

        retrieve = PaymentMethod.retrieve(paymentIntent.getPaymentMethod());


        System.out.println(retrieve.toJson());

        System.out.println(paymentIntent.getPaymentMethodObject().getCard().getBrand());
        System.out.println(paymentIntent.getPaymentMethodObject().getCard().getExpMonth());
        System.out.println(paymentIntent.getPaymentMethodObject().getCard().getExpYear());

        // 支付
        paymentIntent.confirm();

        System.out.println(paymentIntent.getPaymentMethodObject().getCard().getBrand());
        System.out.println(paymentIntent.getPaymentMethodObject().getCard().getExpMonth());
        System.out.println(paymentIntent.getPaymentMethodObject().getCard().getExpYear());
//        paymentIntent.confirm();
    }
}
