package org.example.germesplusmanager.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.repository.IndividualPersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IndividualPersonService {
    private static final Log log = LogFactory.getLog(IndividualPersonService.class);
    private final IndividualPersonRepository individualPersonRepository;

    private final PasswordEncoder passwordEncoder;

    public IndividualPersonService(IndividualPersonRepository individualPersonRepository,
                                   PasswordEncoder passwordEncoder) {
        this.individualPersonRepository = individualPersonRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerIndividualPerson(IndividualPerson individualPerson) {
        log.info("Регистрация пользователя");
        if (!individualPersonRepository.existsByEmail(individualPerson.getEmail())) {
            IndividualPerson newIndividualPerson = new IndividualPerson();
            newIndividualPerson.setName(individualPerson.getName());
            newIndividualPerson.setSurname(individualPerson.getSurname());
            newIndividualPerson.setPhone(individualPerson.getPhone());
            newIndividualPerson.setCity(individualPerson.getCity());
            newIndividualPerson.setEmail(individualPerson.getEmail());
            newIndividualPerson.setPassword(passwordEncoder.encode(individualPerson.getPassword()));
            individualPersonRepository.save(newIndividualPerson);
        } else {
            log.warn("Пользователь с таким email уже существует");
        }
    }

    public void save(Long id, IndividualPerson newData) {
        log.info("Сохранение данных пользователя");
        IndividualPerson individualPerson = individualPersonRepository.findById(id).orElse(null);
        individualPerson.setEmail(newData.getEmail());
        individualPerson.setCity(newData.getCity());
        individualPerson.setPhone(newData.getPhone());
        individualPerson.setSurname(newData.getSurname());
        individualPerson.setName(newData.getName());
        individualPersonRepository.save(individualPerson);
    }

    public IndividualPerson getById(Long id) {
        log.info("Получение пользователя по id");
        return individualPersonRepository.findById(id).orElse(null);
    }
}
