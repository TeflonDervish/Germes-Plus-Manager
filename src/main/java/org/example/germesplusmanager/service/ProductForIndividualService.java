package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.repository.ProductForIndividualRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductForIndividualService {

    private static final Log log = LogFactory.getLog(ProductForIndividualService.class);
    private ProductForIndividualRepository productForIndividualRepository;


    public List<ProductForIndividual> getAll() {
        log.info("Получение списка всех товаров");
        return productForIndividualRepository.findAll();
    }

    public ProductForIndividual getById(Long id) {
        log.info("Получение продукта по " + id);
        return productForIndividualRepository.findById(id).orElse(null);
    }

    public List<ProductForIndividual> getBySearch(String search) {
        log.info("Поиск по имени");
        return productForIndividualRepository.findByNameContainingIgnoreCase(search);
    }



    public List<ProductForIndividual> getBySort(String sort) {
        log.info("Сортировка продуктов по цене");
        List<ProductForIndividual> productForIndividuals;
        if (sort.equals("price_asc")) {
            productForIndividuals = productForIndividualRepository.findAllByOrderByPriceAsc();
        } else {
            productForIndividuals = productForIndividualRepository.findAllByOrderByPriceDesc();
        }
        return productForIndividuals;
    }
}
