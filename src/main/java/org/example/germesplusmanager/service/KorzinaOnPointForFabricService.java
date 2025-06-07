package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointForFabric;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointOfSale;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.repository.KorzinaOnPointForFabricRepository;
import org.example.germesplusmanager.repository.KorzinaOnPointOfSaleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class KorzinaOnPointForFabricService {

    private static final Log log = LogFactory.getLog(KorzinaOnPointForFabricService.class);
    private KorzinaOnPointForFabricRepository korzinaOnPointForFabricRepository;
    private ProductForIndividualService productService;

    public KorzinaOnPointForFabric getKorzina(PointManager pointManager) {
        KorzinaOnPointForFabric korzina = korzinaOnPointForFabricRepository.findByPointManager(pointManager);

        if (korzina == null)
            korzina = createKorzina(pointManager);

        return korzina;
    }

    public KorzinaOnPointForFabric createKorzina(PointManager pointManager) {
        log.info("Создание корзины для пользователя");
        KorzinaOnPointForFabric korzina = new KorzinaOnPointForFabric();
        korzina.setPointManager(pointManager);
        korzina.setProducts(new ArrayList<>());
        return korzinaOnPointForFabricRepository.save(korzina);
    }

    public KorzinaOnPointForFabric addProduct(Long productId, PointManager pointManager) {
        log.info("Добавление продукта в корзину");
        ProductForIndividual product = productService.getById(productId);
        KorzinaOnPointForFabric korzina = getKorzina(pointManager);
        korzina.addProduct(product);
        return korzinaOnPointForFabricRepository.save(korzina);
    }

    public KorzinaOnPointForFabric deleteProduct(Long productId, PointManager pointManager) {
        log.info("Удаление продукта из корзины");
        ProductForIndividual product = productService.getById(productId);
        KorzinaOnPointForFabric korzina = getKorzina(pointManager);
        korzina.deleteProduct(product);
        return korzinaOnPointForFabricRepository.save(korzina);
    }

    public boolean isInKorzina(Long productId, PointManager pointManager) {
        log.info("Проверка, находится ли продукт в корзине");
        ProductForIndividual product =  productService.getById(productId);
        KorzinaOnPointForFabric korzina = getKorzina(pointManager);
        return korzina.isInKorzina(product);
    }

    public KorzinaOnPointForFabric clear(PointManager pointManager) {
        log.info("Очистка корзины");
        KorzinaOnPointForFabric korzina = getKorzina(pointManager);
        korzina.getProducts().clear();
        return korzinaOnPointForFabricRepository.save(korzina);
    }
}
