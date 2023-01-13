package com.vouchit.backend.controller;

import com.vouchit.backend.service.impl.analysis.AnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
@Slf4j
public class AnalysisController {

    private final AnalysisService analysisServiceImpl;

    public AnalysisController(AnalysisService analysisServiceImpl) {
        this.analysisServiceImpl = analysisServiceImpl;
    }

    @Transactional
    @GetMapping("/analysis/{companyId}")
    public ResponseEntity<HashMap<String, HashMap<String, Long>>> getAnalysis(@PathVariable Long companyId) {
        log.error("AnalysisController: getAnalysis: companyId: " + companyId);
        return ResponseEntity.ok(this.analysisServiceImpl.analysis(companyId));
    }


}