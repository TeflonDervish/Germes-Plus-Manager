package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.feedbacks.FeedbackOnPoint;
import org.example.germesplusmanager.repository.FeedbackForPointRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackOnPointService {

    private static final Log log = LogFactory.getLog(FeedbackOnPointService.class);
    private FeedbackForPointRepository feedbackForPointRepository;
    private PointOfSaleService pointOfSaleService;


    public List<FeedbackOnPoint> getByPointOfSale(Long pointId) {
        log.info("Получение продуктов " + pointId);
        PointOfSale point = pointOfSaleService.getById(pointId);
        return feedbackForPointRepository.findByPointOfSale(point);
    }
}
