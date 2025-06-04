package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.Fabric;
import org.example.germesplusmanager.repository.FabricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FabricService {

    private static final Log log = LogFactory.getLog(FabricService.class);
    private FabricRepository fabricRepository;


    public Fabric getById(Long id) {
        log.info("Получение фабрики по " + id);
        return fabricRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Фабрика с таким id не найдена"));
    }

    public List<Fabric> getAll() {
        log.info("Получение списка всех фабрик");
        return fabricRepository.findAll();
    }
}
