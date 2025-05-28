package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.Sclad;
import org.example.germesplusmanager.repository.ScladRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScladService {

    private static final Log log = LogFactory.getLog(ScladService.class);
    private final ScladRepository scladRepository;
    private final ProductForIndividualService productForIndividualService;

    public List<Sclad> getByPointOfSale(PointOfSale pointOfSale) {
        log.info("Получение информации со склада " + pointOfSale);
        return scladRepository.findByPointOfSale(pointOfSale);
    }

    public Sclad getByPointOfSaleAndProductId(PointOfSale pointOfSale, Long productId) {
        return scladRepository.findByPointOfSaleAndProductForIndividual(pointOfSale,
                productForIndividualService.getById(productId));
    }

    public List<Sclad> getByProductName(String productName) {
        return scladRepository.findByProductForIndividual_NameContaining(productName);
    }
}
