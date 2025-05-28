package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.repository.PointOfSaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PointOfSaleService {

    private static final Log log = LogFactory.getLog(PointOfSaleService.class);
    private PointOfSaleRepository pointOfSaleRepository;


    public PointOfSale getById(Long id) {
        log.info("Получение точки по " + id);
        return pointOfSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Точка с таким id не найдена"));
    }

    public List<PointOfSale> getAll() {
        log.info("Получение списка всех точек");
        return pointOfSaleRepository.findAll();
    }
}
