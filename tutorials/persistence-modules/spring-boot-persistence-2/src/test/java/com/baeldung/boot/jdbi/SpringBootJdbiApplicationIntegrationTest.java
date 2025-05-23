package com.baeldung.boot.jdbi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baeldung.boot.jdbi.dao.CarMakerDao;
import com.baeldung.boot.jdbi.dao.CarModelDao;
import com.baeldung.boot.jdbi.domain.CarMaker;
import com.baeldung.boot.jdbi.domain.CarModel;
import com.baeldung.boot.jdbi.service.CarMakerService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {SpringBootJdbiApplication.class, JdbiConfiguration.class})
@Slf4j
class SpringBootJdbiApplicationIntegrationTest {
    
    
    @Autowired
    private CarMakerDao carMakerDao;
    
    @Autowired
    private CarModelDao carModelDao;
    
    @Autowired
    private CarMakerService carMakerService;
 
    @Test
    void givenNewCarMaker_whenInsertNewCarMaker_thenSuccess() {
        
        assertNotNull(carMakerDao);
        
        CarMaker carMaker = CarMaker.builder()
          .name("Diamond Motors")
          .build();
        
        Long generatedId = carMakerDao.insert(carMaker);
        log.info("[I37] generatedId = {}", generatedId);
        assertThat(generatedId).isGreaterThan(0);
    }

    @Test
    void givenNewCarMakers_whenInsertNewCarMakers_thenSuccess() {
        
        assertNotNull(carMakerDao);
        
        CarMaker carMaker1 = CarMaker.builder()
          .name("maker1")
          .build();

        CarMaker carMaker2 = CarMaker.builder()
            .name("maker2")
            .build();
        
        List<CarMaker> makers = new ArrayList<>();
        makers.add(carMaker1);
        makers.add(carMaker2);
        
        List<Long> generatedIds = carMakerDao.bulkInsert(makers);
        log.info("[I37] generatedIds = {}", generatedIds);
        assertThat(generatedIds).size().isEqualTo(makers.size());
    }
    
    
    @Test
    void givenExistingCarMaker_whenFindById_thenReturnExistingCarMaker() {
    
        CarMaker maker = carMakerDao.findById(1L);
        assertThat(maker).isNotNull();
        assertThat(maker.getId()).isEqualTo(1);
        
    }
    
    @Test
    void givenExistingCarMaker_whenBulkInsertFails_thenRollback() {
        
        CarMaker maker = carMakerDao.findById(1L);
        CarModel m1 = CarModel.builder()
          .makerId(maker.getId())
          .name("Model X1")
          .sku("1-M1")
          .yearDate(2019)
          .build();
        maker.getModels().add(m1);

        CarModel m2 = CarModel.builder()
            .makerId(maker.getId())
            .name("Model X1")
            .sku("1-M1")
            .yearDate(2019)
            .build();
        maker.getModels().add(m2);
        
        // This insert fails because we have the same SKU
        try {
            carMakerService.bulkInsert(maker);
            assertTrue("Insert must fail", true);
        }
        catch(Exception ex) {
            log.info("[I113] Exception: {}", ex.getMessage());
        }
        
        CarModel m = carModelDao.findByMakerIdAndSku(maker.getId(), "1-M1");
        assertThat(m).isNull();
            
    }
    
}
