package com.batch.job.pass;

import com.batch.repository.packaged.Package;
import com.batch.repository.packaged.PackageRepository;
import com.batch.repository.pass.Pass;
import com.batch.repository.pass.PassRepository;
import com.batch.repository.pass.PassStatus;
import com.batch.repository.user.User;
import com.batch.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBatchTest
class ExpirePassesJobConfigTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private PassRepository passRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PackageRepository packageRepository;

    //이용권 세팅
    void addPass(){

        List<User> userList = userRepository.findAll();
        Package aPackage = packageRepository.findById(1L).get();

        List<Pass> passList = new ArrayList<>();

        for (User user : userList) {
            Pass pass = Pass.builder()
                    .user(user)
                    .aPackage(aPackage)
                    .status(PassStatus.PROGRESSED)
                    .remainingCount(2)
                    .startedAt(LocalDateTime.now().minusDays(60))
                    .endedAt(LocalDateTime.now().minusDays(1))
                    .build();
            passList.add(pass);
        }
        passRepository.saveAll(passList);
    }
    @Test
    @DisplayName("이용권 만료 step")
    void expire() throws Exception {
        addPass();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance jobInstance = jobExecution.getJobInstance();

        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertEquals("expirePassesJob", jobInstance.getJobName());
    }
}