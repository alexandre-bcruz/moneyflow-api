package com.alexandrebcruz.moneyflow.adapters.in.web.controller;

import com.alexandrebcruz.moneyflow.adapters.in.web.dto.WeeklySummaryResponse;
import com.alexandrebcruz.moneyflow.application.usecase.weeklysummary.GetWeeklySummaryUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneOffset;

import static com.alexandrebcruz.moneyflow.adapters.in.web.controller.TransactionController.USER_ID;

@RestController
@RequestMapping("/weeks")
public class WeeklySummaryController {

    private final GetWeeklySummaryUseCase getWeeklySummaryUseCase;

    public WeeklySummaryController(GetWeeklySummaryUseCase getWeeklySummaryUseCase) {
        this.getWeeklySummaryUseCase = getWeeklySummaryUseCase;
    }

    @GetMapping("/{start}/summary")
    public ResponseEntity<WeeklySummaryResponse> get(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate start){
        var from = start.atStartOfDay().atOffset(ZoneOffset.UTC).toInstant();
        return ResponseEntity.ok(getWeeklySummaryUseCase.execute(from, USER_ID));
    }
}
