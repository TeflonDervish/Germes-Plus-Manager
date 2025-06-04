package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.dto.OtchetDto;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.othcet.OtchetForPoint;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.repository.OtchetForPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OtchetForPointService {

    private static final Log log = LogFactory.getLog(OtchetForPointService.class);
    private final OtchetForPointRepository otchetForPointRepository;
    private final OrderForIndividualService orderForIndividualService;

    public List<OtchetForPoint> getByPointOfSale(PointOfSale pointOfSale) {
        return otchetForPointRepository.findByPointOfSaleId(pointOfSale.getId());
    }

    public OtchetForPoint getById(Long id) {
        return otchetForPointRepository.findById(id).orElse(null);
    }

    public OtchetForPoint createOtchet(PointManager pointManager, OtchetDto otchetDto) {
        OtchetForPoint otchet = new OtchetForPoint();
        List<OrderForIndividual> orders = orderForIndividualService
                .getByDateBetween(
                        otchetDto.getStartDate(),
                        otchetDto.getEndDate(),
                        pointManager.getPointOfSale());

        otchet.setStartDate(otchetDto.getStartDate());
        otchet.setEndDate(otchetDto.getEndDate());

        otchet.setPointOfSale(pointManager.getPointOfSale());
        otchet.setDescription(otchetDto.getDescription());
        otchet.setName(otchetDto.getName());
        otchet.setDescription(otchetDto.getDescription());

        otchet.setOrders(orders);
        otchet.getOrderCount();
        otchet.getTotalPrice();
        otchet.getMeanPrice();

        return otchetForPointRepository.save(otchet);
    }


    public List<OtchetForPoint> getByPointOfSaleAndNameContains(PointOfSale pointOfSale, String query) {
        return otchetForPointRepository.findByPointOfSaleIdAndNameContaining(pointOfSale.getId(), query);
    }

    public void deleteById(Long id) {
        log.info("Удаление отчета");
        otchetForPointRepository.deleteById(id);
    }
}
