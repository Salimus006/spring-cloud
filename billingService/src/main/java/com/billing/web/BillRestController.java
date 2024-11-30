package com.billing.web;

import com.billing.entities.Bill;
import com.billing.models.Customer;
import com.billing.repositories.BillRepository;
import com.billing.services.CustomerRestClient;
import com.billing.services.ProductRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    private final BillRepository billRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillRestController(BillRepository billRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    /*
    Exemple d'appel : http://8888/BILLING-SERVICE/fullBill/1
     */
    @GetMapping(path = "/fullBill/{id}")
    public Bill bill(@PathVariable Long id) {
        Bill billDb = billRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(billDb.getCustomerId());
        billDb.setCustomer(customer);
        billDb.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.findProductById(productItem.getProductId()));
        });
        return billDb;
    }
}
